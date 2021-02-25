package defeatedcrow.hac.magic.item;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.plugin.baubles.CharmItemBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageMagicParticle;
import defeatedcrow.hac.main.util.CustomExplosion;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorPendant extends CharmItemBase {

	private final int maxMeta;

	/*
	 * U: 透明化 / AI妨害
	 * G: 味方回復
	 * R: 採掘速度
	 * B: 雷撃
	 * W: 味方巻き込み防止
	 */
	private static String[] names = { "u1", "g1", "r1", "b1", "w1" };

	public ItemColorPendant() {
		super();
		maxMeta = 4;
		this.setMaxStackSize(1);
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
	public CharmType getCharmType(int meta) {
		switch (meta) {
		case 0:
			return CharmType.KEY;
		case 1:
			return CharmType.KEY;
		case 2:
			return CharmType.TOOL;
		case 3:
			return CharmType.ATTACK;
		case 4:
			return CharmType.SPECIAL;
		default:
			return CharmType.SPECIAL;
		}
	}

	@Override
	public MagicType getType(int meta) {
		return MagicType.PENDANT;
	}

	@Override
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

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/magic/pendant_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public float reduceDamage(DamageSource source, ItemStack charm) {
		return 0;
	}

	@Override
	public boolean onDiffence(DamageSource source, EntityLivingBase owner, float damage, ItemStack charm) {
		return false;
	}

	@Override
	public float increaceDamage(EntityLivingBase target, DamageSource source, ItemStack charm) {
		return 1F;
	}

	@Override
	public boolean onAttacking(EntityLivingBase owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		if (getColor(charm.getItemDamage()) == MagicColor.BLACK) {
			if (owner != null && target != null && !source.isExplosion() && source.isProjectile()) {
				float pow = charm.getCount() + 1.0F;
				if (ClimateCore.isDebug)
					pow += 1.0F;
				pow *= MainUtil.magicSuitEff(owner);
				CustomExplosion explosion = new CustomExplosion(owner.world, owner, owner, target.posX,
						target.posY + 0.25D, target.posZ, pow, CustomExplosion.Type.Normal, true);
				explosion.doExplosion();
				owner.world.addWeatherEffect(new EntityLightningBolt(owner.world, target.posX, target.posY - 0.25D,
						target.posZ, !owner.isSneaking()));
			}
		}
		return false;
	}

	@Override
	public boolean onPlayerAttacking(EntityPlayer owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		return this.onAttacking(owner, target, source, damage, charm);
	}

	@Override
	public boolean onToolUsing(EntityLivingBase owner, BlockPos pos, IBlockState state, ItemStack charm) {
		return false;
	}

	@Override
	public void constantEffect(EntityLivingBase owner, ItemStack charm) {}

	@Override
	public boolean onUsing(EntityPlayer owner, ItemStack charm) {
		if (owner != null && !DCUtil.isEmpty(charm) && !owner.world.isRemote) {
			float eff = MainUtil.magicSuitEff(owner);
			DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(owner.posX, owner.posY, owner.posZ));
			if (getColor(charm.getItemDamage()) == MagicColor.BLUE) {
				owner.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, (int) (600 * charm.getCount() * eff),
						0));
				double a = 4D + charm.getCount() * 2;
				a *= eff;
				AxisAlignedBB aabb = new AxisAlignedBB(owner.posX - a, owner.posY - 2D, owner.posZ - a, owner.posX + a,
						owner.posY + 2D, owner.posZ + a);
				List<EntityMob> list = owner.world.getEntitiesWithinAABB(EntityMob.class, aabb);
				if (list.isEmpty())
					return false;
				else {
					for (EntityMob liv : list) {
						Set<EntityAITaskEntry> set = liv.targetTasks.taskEntries;
						for (EntityAITaskEntry ai : set) {
							if (ai == null || ai.action == null)
								continue;
							if (ai.action instanceof EntityAITarget) {
								EntityAITarget tai = (EntityAITarget) ai.action;
								tai.resetTask();
							}
						}
						liv.setAttackTarget(null);
						DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(liv.posX, liv.posY, liv.posZ));
					}
				}
			} else if (getColor(charm.getItemDamage()) == MagicColor.GREEN) {
				DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(owner.posX, owner.posY, owner.posZ));
				MainUtil.removeBadPotion(owner);
				float h = 2.0F * charm.getCount() * eff;
				owner.heal(h);
				double a = 8D + charm.getCount() * 4;
				AxisAlignedBB aabb = new AxisAlignedBB(owner.posX - a, owner.posY - 2D, owner.posZ - a, owner.posX + a,
						owner.posY + 2D, owner.posZ + a);
				List<EntityLivingBase> list = owner.world.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
				if (list.isEmpty())
					return false;
				else {
					for (EntityLivingBase liv : list) {
						if (liv instanceof IMob)
							continue;
						MainUtil.removeBadPotion(liv);
						liv.heal(h);
						DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(liv.posX, liv.posY, liv.posZ));
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean isActive(ItemStack charm) {
		return true;
	}

	@Override
	public void setActive(ItemStack charm, boolean flag) {}

	@Override
	public ItemStack consumeCharmItem(ItemStack stack) {
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		String s = "";
		int meta = stack.getMetadata();
		if (meta != 3 && meta != 4) {
			tooltip.add(TextFormatting.BOLD.toString() + "PLAYER ONLY");
		}
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.color_pendant." + meta));
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.allcharm"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_pendant." + meta));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
	}
}
