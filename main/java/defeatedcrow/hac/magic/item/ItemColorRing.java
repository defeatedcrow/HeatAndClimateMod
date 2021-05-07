package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.plugin.baubles.CharmItemBase;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorRing extends CharmItemBase {

	private final int maxMeta;

	/*
	 * U: 辞書確認/エキス採取
	 * G: アイテム吸引
	 * R: 火耐性/重力
	 * B: 暗視
	 * W: スリップ無効/落下耐性
	 */
	private static String[] names = { "u1", "g1", "r1", "b1", "w1" };

	public ItemColorRing() {
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
	public String getTexPath(int meta, boolean f) {
		String s = "items/magic/ring_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public CharmType getCharmType(int meta) {
		switch (meta) {
		case 0:
			return CharmType.SPECIAL;
		case 1:
			return CharmType.TOOL;
		case 2:
			return CharmType.CONSTANT;
		case 3:
			return CharmType.CONSTANT;
		case 4:
			return CharmType.DEFFENCE;
		default:
			return CharmType.SPECIAL;
		}
	}

	@Override
	public MagicType getType(int meta) {
		return MagicType.RING;
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
	public float reduceDamage(DamageSource source, ItemStack charm) {
		if (getColor(charm.getItemDamage()) == MagicColor.WHITE) {
			return 1.0F * charm.getCount();
		}
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
		return false;
	}

	@Override
	public boolean onPlayerAttacking(EntityPlayer owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		return onAttacking(owner, target, source, damage, charm);
	}

	@Override
	public boolean onToolUsing(EntityLivingBase owner, BlockPos pos, IBlockState state, ItemStack charm) {
		if (getColor(charm.getItemDamage()) == MagicColor.GREEN) {
			if (!owner.world.isRemote && state != null) {
				double a = MainUtil.magicSuitEff(owner) * 3;
				a += charm.getCount() * 2;
				AxisAlignedBB aabb = new AxisAlignedBB(pos.getX() - a, (double) pos.getY() - 2, pos.getZ() - a, pos
						.getX() + a, (double) pos.getY() + 3, pos.getZ() + a);
				List<EntityItem> drops = owner.world.getEntitiesWithinAABB(EntityItem.class, aabb);
				for (EntityItem drop : drops) {
					drop.setPosition(owner.posX, owner.posY + 0.5D, owner.posZ);
				}
			}
		}
		return false;
	}

	@Override
	public void constantEffect(EntityLivingBase owner, ItemStack charm) {
		int l = charm.getCount() - 1;
		if (l < 0) {
			l = 0;
		}
		if (MainUtil.magicSuitEff(owner) > 1.0F) {
			l += 1;
		}
		if (getColor(charm.getItemDamage()) == MagicColor.RED) {
			owner.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 205, 0));
			owner.addPotionEffect(new PotionEffect(MainInit.heavyboots, 205, l));
		} else if (getColor(charm.getItemDamage()) == MagicColor.BLACK) {
			owner.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 205, 0));
		} else if (getColor(charm.getItemDamage()) == MagicColor.WHITE) {
			owner.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 205, l));
		}
	}

	@Override
	public boolean onUsing(EntityPlayer owner, ItemStack charm) {
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
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.color_ring." + meta));
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.allcharm"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_ring." + meta));
			tooltip.add(TextFormatting.GOLD.toString() + TextFormatting.BOLD.toString() + "=== Boost ===");
			tooltip.add(TextFormatting.GOLD.toString() + I18n.format("dcs.tip.buff1") + "," + I18n
					.format("dcs.tip.buff2") + TextFormatting.WHITE.toString() + I18n
							.format("dcs.comment.buff.color_ring." + meta));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
	}

}
