package defeatedcrow.hac.machine.item.plating;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.item.IPlatingTool;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemPlatingBase extends DCItem implements IPlatingTool {

	public ItemPlatingBase() {
		super();
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		return super.getUnlocalizedName() + "_" + getItemName(meta);
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/coating_" + getItemName(meta);
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		if (!DCUtil.isEmpty(stack)) {
			int meta = stack.getItemDamage();
			if (getEnchantments(meta) != null && getEnchantments(meta).length > 0) {
				tooltip.add(TextFormatting.BOLD.toString() + "Enchantments: ");
				for (Enchantment enc : getEnchantments(meta)) {
					tooltip.add(TextFormatting.AQUA.toString() + enc.getTranslatedName(1));
				}
			}
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add("Use on the anvil.");
		}
	}

	public static boolean canAddEnchantment(ItemStack target, Enchantment enc) {
		int i = EnchantmentHelper.getEnchantmentLevel(enc, target);
		int max = enc.getMaxLevel();
		if (i > 0)
			return i < max;
		else if (enc.canApply(target))
			return true;
		return false;
	}

	public abstract String getItemName(int meta);

	@Override
	public abstract Enchantment[] getEnchantments(int meta);

	@Override
	public boolean canEnchant(ItemStack target, ItemStack tool) {
		if (!DCUtil.isEmpty(target) && !DCUtil.isEmpty(tool)) {
			int meta = tool.getItemDamage();
			byte b = 0;
			boolean flag = false;
			if (target.hasTagCompound() && target.getTagCompound().hasKey("dcs.plated")) {
				b = target.getTagCompound().getByte("dcs.plated");
			}
			for (Enchantment enc : getEnchantments(meta)) {
				if (canAddEnchantment(target, enc)) {
					flag = true;
				}
			}
			return flag && b < 3;
		}
		return false;
	}

	@Override
	public ItemStack getEnchantedItem(ItemStack target, ItemStack tool) {
		int meta = tool.getItemDamage();
		ItemStack ret = target.copy();
		Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(ret);
		for (Enchantment enc : getEnchantments(meta)) {
			if (canAddEnchantment(target, enc)) {
				int i = EnchantmentHelper.getEnchantmentLevel(enc, ret);
				int max = enc.getMaxLevel();
				if (i > 0 && i < max) {
					map.put(enc, i + 1);
				} else {
					if (enc.canApply(ret)) {
						map.put(enc, 1);
					}
				}
			}
		}
		if (!map.isEmpty()) {
			EnchantmentHelper.setEnchantments(map, ret);
			byte b = target.hasTagCompound() ? target.getTagCompound().getByte("dcs.plated") : (byte) 0;
			ret.getTagCompound().setByte("dcs.plated", (byte) (b + 1));
			return ret;
		}
		return ItemStack.EMPTY;
	}

}
