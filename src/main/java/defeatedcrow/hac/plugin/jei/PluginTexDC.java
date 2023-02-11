package defeatedcrow.hac.plugin.jei;

import net.minecraft.resources.ResourceLocation;

public enum PluginTexDC {
	SMELTING("textures/gui/climate_smelting_jei.png"),
	CROP("textures/gui/crop_info_jei.png"),
	TREE("textures/gui/crop_info_tree_jei.png");

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