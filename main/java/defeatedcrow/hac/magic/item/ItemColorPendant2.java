package defeatedcrow.hac.magic.item;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.plugin.baubles.CharmItemBase;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorPendant2 extends CharmItemBase {

	private final int maxMeta;

	/*
	 * U: 水中呼吸・採掘
	 * G: 木こり
	 * R: 精錬採掘
	 * B: 吸血
	 * W: 爆破ダメージ軽減
	 */
	private static String[] names = {
			"u2",
			"g2",
			"r2",
			"b2",
			"w2"
	};

	public ItemColorPendant2() {
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
			return CharmType.CONSTANT;
		case 1:
			return CharmType.TOOL;
		case 2:
			return CharmType.SPECIAL;
		case 3:
			return CharmType.ATTACK;
		case 4:
			return CharmType.DEFFENCE;
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
		if (getColor(charm.getItemDamage()) == MagicColor.WHITE && source.isExplosion()) {
			float f = charm.getCount() * 10F;
			return f;
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
		if (getColor(charm.getItemDamage()) == MagicColor.BLACK) {
			int f = charm.getCount();
			float eff = MainUtil.magicSuitEff(owner) * 0.5F;
			DCLogger.debugInfoLog("stats " + f);
			if (owner != null && target != null) {
				if (owner instanceof EntityPlayer) {
					((EntityPlayer) owner).getFoodStats().addStats(f, eff);
				} else {
					owner.heal(f * eff);
				}
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
		if (getColor(charm.getItemDamage()) == MagicColor.GREEN) {
			ItemStack item = new ItemStack(state.getBlock(), 1, state.getBlock().damageDropped(state));
			if (state.getBlock().isWood(owner.world, pos) || MainUtil.hasDic(item, "logWood")) {
				if (!owner.world.isRemote) {
					/**
					 * @date 2021.09.24
					 * @author Fixed by SkyTheory
					 */
					Set<BlockPos> set = MainUtil.getLumberTargetList(owner.world, pos, state.getBlock(), 192);
					if (set.isEmpty()) {
						set = Collections.singleton(pos);
					}
					int count = 0;
					for (BlockPos p2 : set) {
						owner.world.setBlockToAir(p2);
						count++;
					}

					while (count > 0) {
						int i = 0;
						if (count > 64) {
							i = 64;
						} else {
							i = count;
						}
						count -= i;
						ItemStack drop = item.copy();
						drop.setCount(i);
						EntityItem dropE = new EntityItem(owner.world, owner.posX, owner.posY + 0.5D, owner.posZ,
								drop);
						if (owner.world.spawnEntity(dropE))
							owner.world.setBlockToAir(pos);
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public void constantEffect(EntityLivingBase owner, ItemStack charm) {
		if (getColor(charm.getItemDamage()) == MagicColor.BLUE) {
			owner.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 205, 0));
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
		if (meta == 1 || meta == 2) {
			tooltip.add(TextFormatting.BOLD.toString() + "PLAYER ONLY");
		}
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.color_pendant2." + meta));
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.allcharm"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_pendant2." + meta));
			tooltip.add(TextFormatting.GOLD.toString() + TextFormatting.BOLD.toString() + "=== Boost ===");
			tooltip.add(TextFormatting.GOLD.toString() + I18n.format("dcs.tip.buff1") + "," + I18n
					.format("dcs.tip.buff2") + TextFormatting.WHITE.toString() + I18n
							.format("dcs.comment.buff.color_pendant2." + meta));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
	}
}
