package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewel;
import defeatedcrow.hac.api.magic.IMagicCost;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorGauntlet extends DCItem implements IJewel, IMagicCost {

	private final int maxMeta;

	private static String[] names = {
			"ub",
			"gw",
			"rg",
			"br",
			"wu"
	};

	public ItemColorGauntlet() {
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
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/magic/gauntlet_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public CharmType getCharmType(int meta) {
		return CharmType.CONSTANT;
	}

	@Override
	public MagicType getType(int meta) {
		return MagicType.OFFHAND;
	}

	@Override
	public MagicColor getColor(int meta) {
		switch (meta) {
		case 0:
			return MagicColor.BLUE_BLACK;
		case 1:
			return MagicColor.GREEN_WHITE;
		case 2:
			return MagicColor.RED_GREEN;
		case 3:
			return MagicColor.BLACK_RED;
		case 4:
			return MagicColor.WHITE_BLUE;
		default:
			return MagicColor.NONE;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		String s = "";
		int meta = stack.getMetadata();
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.color_gauntlet." + meta));
		if (getCost(stack) > 0) {
			float f = getCost(stack);
			tooltip.add(TextFormatting.BLUE.toString() + TextFormatting.BOLD.toString() + "=== Magic Cost ===");
			tooltip.add(TextFormatting.WHITE.toString() + DCName.getMagicCost() + ": " + String.format("%.1f F", f));
		}
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.offhand_tool"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_gauntlet." + meta));
			tooltip.add(TextFormatting.GOLD.toString() + TextFormatting.BOLD.toString() + "=== Boost ===");
			tooltip.add(TextFormatting.GOLD.toString() + I18n.format("dcs.tip.buff2") + TextFormatting.WHITE
					.toString() + I18n.format("dcs.comment.buff.color_gauntlet." + meta));
			tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.BOLD.toString() + "============");
			tooltip.add(TextFormatting.GRAY.toString() + I18n.format("dcs.comment.flavor.color_gauntlet." + meta));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
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
		if (!DCUtil.isEmpty(item)) {
			int i = item.getItemDamage();
			float f = (float) CoreConfigDC.harderMagicCostAmount;
			if (i == 3) {
				return f * 2F;
			}
			if (i == 0 || i == 1) {
				return f;
			}
		}
		return 0;
	}

}
