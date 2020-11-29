package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.magic.entity.EntityFlowerTurret;
import defeatedcrow.hac.magic.entity.EntityMagicCushion;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageMagicParticle;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorCard2 extends DCItem {

	private final int maxMeta;

	private static String[] names = { "ug2", "gb2", "ru2", "bw2", "wr2" };

	public ItemColorCard2() {
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
					onEffect_UG1(world, player, f);
					break;
				case 1:
					onEffect_GB1(world, player, f);
					break;
				case 2:
					onEffect_RU1(world, player, f);
					break;
				case 3:
					onEffect_BW1(world, player, f);
					break;
				case 4:
					onEffect_WR1(world, player, f);
					break;
				default:
					onEffect_UG1(world, player, f);
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

	private boolean onEffect_UG1(World world, EntityPlayer player, float f) {
		EnumFacing playerFacing = player.getHorizontalFacing();
		BlockPos pos = player.getPosition().offset(playerFacing);
		IBlockState cur = player.world.getBlockState(pos);
		IBlockState set = MagicInit.clusterIce.getDefaultState();
		if (cur.getMaterial().isReplaceable() && player.world.setBlockState(pos, set))
			return true;
		return false;
	}

	private boolean onEffect_GB1(World world, EntityPlayer player, float f) {
		EnumFacing playerFacing = player.getHorizontalFacing();
		BlockPos pos = player.getPosition().offset(playerFacing);

		BlockPos set = null;
		for (int i = -1; i < 2; i++) {
			BlockPos p = pos.up(i);
			IBlockState up = player.world.getBlockState(p);
			IBlockState down = player.world.getBlockState(p.down());
			if (down.getCollisionBoundingBox(world, p.down()) != null && up.getMaterial().isReplaceable()) {
				set = p;
				break;
			}
		}

		if (set != null) {
			EntityFlowerTurret flower = new EntityFlowerTurret(world);
			flower.setPosition(set.getX() + 0.5D, set.getY(), set.getZ() + 0.5D);
			flower.setTamedBy(player);
			if (!world.isRemote && world.spawnEntity(flower)) {
				if (world instanceof WorldServer) {
					((WorldServer) world).spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, set.getX() + 0.5D, set
							.getY() + 0.5D, set.getZ() + 0.5D, 8, 0.5D, 0.5D, 0.5D, 0.5D, new int[0]);
				}
			}
		}

		return true;
	}

	private boolean onEffect_RU1(World world, EntityPlayer player, float f) {
		int r = MathHelper.floor(16 + 8 * f);
		AxisAlignedBB aabb = new AxisAlignedBB(player.posX - r, player.posY - 2D, player.posZ - r, player.posX + r,
				player.posY + 2D, player.posZ + r);
		List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
		if (list.isEmpty())
			return false;
		else {
			for (int i = 0; i < list.size(); i++) {
				EntityLivingBase mob = list.get(i);
				if (!mob.isRiding() && !(mob instanceof EntityPlayer) && !(mob instanceof EntityTameable)) {
					EntityMagicCushion cushion = new EntityMagicCushion(world);
					cushion.setPosition(mob.posX, mob.posY, mob.posZ);
					cushion.setMaxLivingTime((int) (60 * f));
					if (!world.isRemote && world.spawnEntity(cushion)) {
						mob.startRiding(cushion);
					}
				}
			}

			player.world.addWeatherEffect(new EntityLightningBolt(player.world, player.posX, player.posY + 1.0D,
					player.posZ, true));
		}
		return true;
	}

	private boolean onEffect_BW1(World world, EntityPlayer player, float f) {
		int r = MathHelper.floor(12000 * f);
		player.addPotionEffect(new PotionEffect(MainInit.clairvoyance, r, 0));
		return true;
	}

	private boolean onEffect_WR1(World world, EntityPlayer player, float f) {
		EnumFacing playerFacing = player.getHorizontalFacing();
		BlockPos pos = player.getPosition().offset(playerFacing);
		IBlockState cur = player.world.getBlockState(pos);
		IBlockState set = MagicInit.infernalFlame.getDefaultState();
		if (cur.getMaterial().isReplaceable() && player.world.setBlockState(pos, set))
			return true;
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
		tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.card2." + meta));
	}

	public enum CardType {
		SELF_BUFF,
		PROJECTILE,
		AREA,
		SPAWN;

		public static CardType getType(int meta) {
			switch (meta) {
			case 2:
				return AREA;
			case 3:
				return SELF_BUFF;
			default:
				return SPAWN;
			}
		}
	}

}
