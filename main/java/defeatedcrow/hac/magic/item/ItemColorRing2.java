package defeatedcrow.hac.magic.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IMagicCost;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.plugin.baubles.CharmItemBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.DCName;
import defeatedcrow.hac.main.util.MainUtil;
import defeatedcrow.hac.main.worldgen.vein.SkarnGenPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemColorRing2 extends CharmItemBase implements IMagicCost {

	private final int maxMeta;

	/*
	 * U: インベントリ開示
	 * G: 鉱石索敵
	 * R: 攻撃速度上昇
	 * B: ドロップ増加
	 * W: 悪性ポーション・炎上解除
	 */
	private static String[] names = {
			"u2",
			"g2",
			"r2",
			"b2",
			"w2"
	};

	public ItemColorRing2() {
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
			return CharmType.KEY;
		case 2:
			return CharmType.CONSTANT;
		case 3:
			return CharmType.ATTACK;
		case 4:
			return CharmType.CONSTANT;
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
		if (getColor(charm.getItemDamage()) == MagicColor.BLACK && target != null && !owner.world.isRemote) {
			int r = itemRand.nextInt(3);
			if (r > 0) {
				EntityXPOrb orb = new EntityXPOrb(owner.world, target.posX, target.posY, target.posZ, r);
				owner.world.spawnEntity(orb);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean onPlayerAttacking(EntityPlayer owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		return onAttacking(owner, target, source, damage, charm);
	}

	@Override
	public boolean onToolUsing(EntityLivingBase owner, BlockPos pos, IBlockState state, ItemStack charm) {
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
			owner.addPotionEffect(new PotionEffect(MainInit.nimble, 205, l));
		} else if (getColor(charm.getItemDamage()) == MagicColor.WHITE) {
			if (owner.isBurning()) {
				owner.extinguish();
			}
			List<PotionEffect> removes = new ArrayList<PotionEffect>();
			Collection<PotionEffect> target = owner.getActivePotionEffects();
			for (PotionEffect check : target) {
				Potion p = check.getPotion();
				if (p != null && p.isBadEffect()) {
					removes.add(check);
				}
			}
			for (PotionEffect ret : removes) {
				owner.removePotionEffect(ret.getPotion());
			}
		}
	}

	@Override
	public boolean onUsing(EntityPlayer owner, ItemStack charm) {
		if (getColor(charm.getItemDamage()) == MagicColor.GREEN) {
			int l = charm.getCount();
			int x = MathHelper.floor(owner.posX);
			int y = MathHelper.floor(owner.posY);
			int z = MathHelper.floor(owner.posZ);
			BlockPos p = new BlockPos(x, y, z);
			Map<BlockPos, ItemStack> map = new LinkedHashMap<>();
			BlockPos air = null;
			int count = 0;
			for (int i = 1; i < y; i++) {
				BlockPos pos = p.down(i);
				IBlockState state = owner.getEntityWorld().getBlockState(pos);
				if (state == null)
					continue;
				if (i > 1 && air == null && owner.getEntityWorld().isAirBlock(pos)) {
					air = pos;
				}
				ItemStack target = new ItemStack(Item.getItemFromBlock(state.getBlock()), 1, state.getBlock()
						.getMetaFromState(state));
				if (isOre(target)) {
					map.put(pos, target);
					count++;
				}
				if (count > l)
					break;
			}

			float eff = MainUtil.magicSuitEff(owner);
			int cx = x >> 4;
			int cz = z >> 4;
			BlockPos center = SkarnGenPos.getNearestPoint(cx, cz, owner.world, (int) (8 * eff));
			if (center != null && !SkarnGenPos.isDupe(center, owner.world)) {
				owner.sendMessage(new TextComponentString("== Nearby Skarn detected =="));
				owner.sendMessage(new TextComponentString("* Center Coodinate: " + center.getX() + ", 40, " + center
						.getZ() + " *"));
			}

			if (air != null) {
				owner.sendMessage(new TextComponentString("== Cavity ditected: Y=" + air.getY() + " =="));
			}
			if (!map.isEmpty()) {
				owner.sendMessage(new TextComponentString("== Ore block detected =="));
				for (Entry<BlockPos, ItemStack> v : map.entrySet()) {
					owner.sendMessage(new TextComponentString("* " + v.getValue().getDisplayName() + ": Y=" + v.getKey()
							.getY() + " *"));
				}
			} else {
				owner.sendMessage(new TextComponentString("== No ore block detected in this coordinate =="));
			}
			return true;
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
		if (meta == 0 || meta == 1) {
			tooltip.add(TextFormatting.BOLD.toString() + "PLAYER ONLY");
		}
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.color_ring2." + meta));
		if (getCost(stack) > 0) {
			float f = getCost(stack);
			tooltip.add(TextFormatting.BLUE.toString() + TextFormatting.BOLD.toString() + "=== Magic Cost ===");
			tooltip.add(TextFormatting.WHITE.toString() + DCName.getMagicCost() + ": " + String.format("%.1f F", f));
		}
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.allcharm"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_ring2." + meta));
			tooltip.add(TextFormatting.GOLD.toString() + TextFormatting.BOLD.toString() + "=== Boost ===");
			tooltip.add(TextFormatting.GOLD.toString() + I18n.format("dcs.tip.buff1") + "," + I18n
					.format("dcs.tip.buff2") + TextFormatting.WHITE.toString() + I18n
							.format("dcs.comment.buff.color_ring2." + meta));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
	}

	private boolean isOre(ItemStack target) {
		if (DCUtil.isEmpty(target))
			return false;
		int[] ids = OreDictionary.getOreIDs(target);
		for (int i : ids) {
			String name = OreDictionary.getOreName(i);
			if (name != null && name.contains("ore")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canUseMagic(EntityPlayer player, ItemStack stack) {
		return true;
	}

	@Override
	public boolean beforeConsumption(EntityPlayer player, ItemStack stack) {
		return true;
	}

	@Override
	public float getCost(ItemStack item) {
		if (!DCUtil.isEmpty(item) && CoreConfigDC.harderMagic) {
			int i = item.getItemDamage();
			float f = (float) CoreConfigDC.harderMagicCostAmount;
			if (i == 1) {
				return f * 0.5F;
			}
			if (i == 3) {
				return f * 0.25F;
			}
		}
		return 0;
	}

}
