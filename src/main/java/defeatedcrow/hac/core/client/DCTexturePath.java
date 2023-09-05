package defeatedcrow.hac.core.client;

import net.minecraft.resources.ResourceLocation;

public enum DCTexturePath {
	POTION("textures/gui/icons_potion.png"),
	HUD("textures/gui/hud_climate.png"),
	GRAY("textures/blocks/gray_effect.png"),
	HOT_DISP("textures/gui/hud_hot.png"),
	COLD_DISP("textures/gui/hud_cold.png"),
	GUI_INV_SINGLE("textures/gui/simple_inv_single.png"),
	GUI_INV_DOUBLE("textures/gui/simple_inv_double.png"),
	GUI_CHAMBER_ITEM("textures/gui/chamber_item_inv.png");

	private final String name;

	private DCTexturePath(String nameIn) {
		name = nameIn;
	}

	public String getName() {
		return name;
	}

	public ResourceLocation getLocation() {
		return new ResourceLocation("dcs_climate", name);
	}
}
