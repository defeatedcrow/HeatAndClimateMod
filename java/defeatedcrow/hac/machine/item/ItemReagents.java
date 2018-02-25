package defeatedcrow.hac.machine.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import net.minecraft.item.ItemStack;

public class ItemReagents extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"drop_coaltar",
			"drop_glycerine",
			"drop_synthetic",
			"dust_alkali",
			"dust_ammonium_nitrate",
			"dust_nitrocellulose",
			"bottle_nitroglycerin",
			"bottle_carbon_black",
			"smokeless_gunpowder",
			"gem_carbide",
			"bottle_phosphorus",
			"bottle_phosphoric_acid",
			"bottle_ammonium_phosphate",
			"gem_coke"
	};

	private static String[] tex_names = {
			"drop_coaltar",
			"drop_glycerine",
			"drop_synthetic",
			"white_powder_bottle",
			"white_powder_bottle",
			"white_powder_bottle",
			"orange_water_bottle",
			"black_water_bottle",
			"dust_smokeless",
			"gem_carbide",
			"red_powder_bottle",
			"clear_water_bottle",
			"white_powder_bottle",
			"gem_coke"
	};

	public ItemReagents() {
		super();
		maxMeta = names.length - 1;
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
		String s = "items/misc/" + tex_names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	public static int getItemBurnTime(ItemStack stack) {
		int i = stack.getMetadata();
		if (i == 0)
			return 1600;
		else if (i == 1)
			return 800;
		else if (i == 9)
			return 12800;
		else if (i == 13)
			return 5400;
		else
			return 0;
	}

}
