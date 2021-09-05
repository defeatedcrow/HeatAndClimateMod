package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.entity.EntityFTDog;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageMagicParticle;
import defeatedcrow.hac.main.util.CustomExplosion;
import defeatedcrow.hac.main.util.MainUtil;
import defeatedcrow.hac.main.util.portal.DCDimChangeHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorCard3 extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"ub1",
			"gw1",
			"rg1",
			"br1",
			"wu1"
	};

	public ItemColorCard3() {
		super();
		maxMeta = 4;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/magic/card_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	public MagicColor getColor(int meta) {
		switch (meta) {
		case 0:
			return MagicColor.BLUE;
		case 1:
			return MagicColor.GREEN;
		case 2:
			return MagicColor.RED;
		case 3:
			return MagicColor.BLACK;
		case 4:
			return MagicColor.WHITE;
		default:
			return MagicColor.NONE;
		}
	}

	/* 雪玉に似た動作をする */

	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		return EnumActionResult.FAIL;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase living, int timeLeft) {
		if (stack != null && living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			boolean flag = !player.capabilities.isCreativeMode;
			int meta = stack.getItemDamage();

			if (!world.isRemote) {
				float f = MainUtil.magicSuitEff(player);
				switch (meta) {
				case 0:
					onEffect_UB1(world, player, f);
					break;
				case 1:
					onEffect_GW1(world, player, f);
					break;
				case 2:
					onEffect_RG1(world, player, f);
					break;
				case 3:
					onEffect_BR1(world, player, f);
					break;
				case 4:
					onEffect_WU1(world, player, f);
					break;
				default:
					onEffect_UB1(world, player, f);
					break;
				}
				DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(living.posX, living.posY, living.posZ));
			}
			world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.NEUTRAL, 0.8F, 1.5F);
			if (flag) {
				DCUtil.reduceStackSize(stack, 1);
			}
			player.addStat(StatList.getObjectUseStats(this));
		}
	}

	private boolean onEffect_UB1(World world, EntityPlayer player, float f) {
		List<EntityLivingBase> list = Lists.newArrayList();
		for (Entity e : world.getLoadedEntityList()) {
			if (e instanceof EntityLivingBase && e instanceof IMob) {
				if (e.isEntityAlive()) {
					double d = e.getDistanceSq(player);
					double d2 = 32 * 32 * f;
					if (d < d2) {
						list.add((EntityLivingBase) e);
					}
				}
			}
		}
		if (!list.isEmpty()) {
			for (EntityLivingBase l : list) {
				l.setDead();
			}
		}
		return false;
	}

	private boolean onEffect_GW1(World world, EntityPlayer player, float f) {
		List<EntityLivingBase> list = Lists.newArrayList();
		for (Entity e : world.getLoadedEntityList()) {
			if (e instanceof EntityLivingBase && e.isEntityAlive()) {
				if (e instanceof IAnimals || e instanceof IEntityOwnable) {
					double d = e.getDistanceSq(player);
					double d2 = 32 * 32 * f;
					if (d < d2) {
						list.add((EntityLivingBase) e);
					}
				} else if (e instanceof EntityPlayer) {
					list.add((EntityLivingBase) e);
				}
			}
		}
		if (!list.isEmpty()) {
			for (EntityLivingBase l : list) {
				float heal = l.getMaxHealth() * 0.5F + f;
				l.heal(heal);
				MainUtil.removeBadPotion(l);
			}
		}

		return true;
	}

	private boolean onEffect_RG1(World world, EntityPlayer player, float f) {
		EntityFTDog dog = new EntityFTDog(world);
		dog.copyLocationAndAnglesFrom(player);
		dog.setTamedBy(player);
		dog.heal(20.0F);
		int l = MathHelper.floor(6000 * f);
		dog.setGrowingAge(l);
		return world.spawnEntity(dog);
	}

	private boolean onEffect_BR1(World world, EntityPlayer player, float f) {
		CustomExplosion explosion = new CustomExplosion(world, player, player, player.posX, player.posY, player.posZ,
				8.0F * f, CustomExplosion.Type.Friends, true);
		explosion.doExplosion();
		explosion.doExplosionEffect(false, false);
		DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(player.posX, player.posY, player.posZ,
				(byte) EnumParticleTypes.EXPLOSION_HUGE.getParticleID(), 1.0F, 0.0F, 0.0F));
		float damage = player.getHealth() * 0.5F;
		if (damage > 1.0F) {
			player.attackEntityFrom(DamageSource.causeExplosionDamage(explosion), damage);
		}
		return true;
	}

	private boolean onEffect_WU1(World world, EntityPlayer player, float f) {
		if (world instanceof WorldServer) {
			DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(player.posX, player.posY, player.posZ));
			int dim = player.world.provider.getDimension();
			BlockPos pos = null;
			if (player.getBedLocation(dim) != null) {
				pos = player.getBedLocation(dim);
				player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D);
				player.fallDistance = 0.0F;
				return true;
			} else if (player.getBedLocation(0) != null) {
				pos = player.getBedLocation(0);
				DCDimChangeHelper.INSTANCE.addCoord(player, 0, pos);
				return true;
			} else {
				pos = world.provider.getSpawnPoint();
				if (pos != null) {
					player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D);
					player.fallDistance = 0.0F;
					return true;
				}
			}
		}
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick2(World world, EntityPlayer player, EnumHand hand) {
		player.setActiveHand(hand);
		return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		String s = "";
		int meta = stack.getMetadata();
		CardType type = CardType.getType(meta);
		tooltip.add(TextFormatting.BOLD.toString() + "PLAYER ONLY");
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.card_type." + type.toString()));
		tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
		tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.card3." + meta));
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.GOLD.toString() + TextFormatting.BOLD.toString() + "=== Boost ===");
			tooltip.add(TextFormatting.GOLD.toString() + I18n.format("dcs.tip.buff2") + TextFormatting.WHITE
					.toString() + I18n.format("dcs.comment.buff.color_card3." + meta));
			tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.BOLD.toString() + "============");
			tooltip.add(TextFormatting.GRAY.toString() + I18n.format("dcs.comment.flavor.color_card3." + meta));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
	}

	public enum CardType {
		SELF_BUFF,
		PROJECTILE,
		AREA,
		SPAWN;

		public static CardType getType(int meta) {
			switch (meta) {
			case 2:
				return SPAWN;
			case 4:
				return SELF_BUFF;
			default:
				return AREA;
			}
		}
	}

}
