package defeatedcrow.hac.machine.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

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
			"bottle_carbon_black"
	};

	private static String[] tex_names = {
			"drop_coaltar",
			"drop_glycerine",
			"drop_synthetic",
			"white_powder_bottle",
			"white_powder_bottle",
			"white_powder_bottle",
			"orange_water_bottle",
			"black_water_bottle"
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

}
