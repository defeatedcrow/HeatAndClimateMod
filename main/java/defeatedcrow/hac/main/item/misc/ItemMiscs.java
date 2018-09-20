package defeatedcrow.hac.main.item.misc;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Deprecated
public class ItemMiscs extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"string_linen",
			"string_cotton",
			"cloth_linen",
			"cloth_cotton",
			"spindle_wood",
			"spindle_brass",
			"spindle_alloy",
			"spindle_steel",
			"string_chrysotile",
			"cloth_chrysotile"
	};

	public ItemMiscs(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: 亜麻糸
	 * 1: 木綿糸
	 * 2: 亜麻布
	 * 3: 木綿布
	 * 4: 木の回転体
	 * 5: 真鍮の回転体
	 * 6: 合金の回転体
	 * 7: 鋼の回転体
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(TextFormatting.RED.toString() + TextFormatting.BOLD.toString() + "DEPRECATED ITEM");
		tooltip.add("Convert to a new item with a single craft.");
	}

}
