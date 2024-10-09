package defeatedcrow.hac.plugin.jei;

import net.minecraft.resources.ResourceLocation;

public enum PluginTexDC {
	SMELTING("textures/gui/climate_smelting_jei.png"),
	HEAT_TREATMENT("textures/gui/metal_treatment_jei.png"),
	COOKING("textures/gui/cooking_pot_jei.png"),
	FRYING("textures/gui/cooking_pot_fryer_jei.png"),
	TEA("textures/gui/tea_pot_jei.png"),
	FERMENTATION("textures/gui/fermentation_jar_jei.png"),
	MILL("textures/gui/stonemill_jei.png"),
	CRUSHER("textures/gui/roll_crusher_jei.png"),
	CROP("textures/gui/crop_info_jei.png"),
	TREE("textures/gui/crop_info_tree_jei.png"),
	FUEL("textures/gui/fuel_jei.png");

	private final String name;

	private PluginTexDC(String nameIn) {
		name = nameIn;
	}

	public String getName() {
		return name;
	}

	public ResourceLocation getLocation() {
		return new ResourceLocation("dcs_climate", name);
	}
}
