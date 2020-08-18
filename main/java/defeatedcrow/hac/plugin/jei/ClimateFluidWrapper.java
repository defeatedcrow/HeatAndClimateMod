package defeatedcrow.hac.plugin.jei;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.plugin.jei.ingredients.ClimateTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class ClimateFluidWrapper implements IRecipeWrapper {

	private final DCFluidInfo recipe;
	public final DCHeatTier temp;
	public final DCHumidity hum;
	public final DCAirflow air;
	public final FluidStack fluidstack;

	public ClimateFluidWrapper(DCFluidInfo info) {
		recipe = info;
		temp = recipe.temp;
		hum = recipe.hum;
		air = recipe.air;
		fluidstack = recipe.stack;
	}

	@Override
	public void getIngredients(IIngredients ing) {
		ing.setInput(ClimateTypes.TEMP, temp);
		ing.setInput(ClimateTypes.HUM, hum);
		ing.setInput(ClimateTypes.AIR, air);
		ing.setInput(VanillaTypes.FLUID, fluidstack);
	}

	public DCHeatTier getTemp() {
		return temp;
	}

	public DCHumidity getHum() {
		return hum;
	}

	public DCAirflow getAir() {
		return air;
	}

	public FluidStack getFluidInput() {
		return fluidstack;
	}

	@Override
	public void drawInfo(Minecraft mc, int wid, int hei, int mouseX, int mouseY) {
		int baseY = 0;

		ResourceLocation res = new ResourceLocation("dcs_climate", "textures/gui/c_effective_gui.png");
		mc.getTextureManager().bindTexture(res);
		if (temp != null) {
			if (temp.getID() > 6) {
				mc.currentScreen.drawTexturedModalRect(51, baseY + 16, temp.getID() * 20 - 140, 174, 20, 3);
				mc.currentScreen.drawTexturedModalRect(71, baseY + 16, temp.getID() * 20 - 140, 174, 20, 3);
			} else {
				mc.currentScreen.drawTexturedModalRect(51, baseY + 16, temp.getID() * 20, 170, 20, 3);
				mc.currentScreen.drawTexturedModalRect(71, baseY + 16, temp.getID() * 20, 170, 20, 3);
			}
		}
		if (hum != null) {
			mc.currentScreen.drawTexturedModalRect(51, baseY + 27, hum.getID() * 40, 178, 40, 3);
		}
		if (air != null) {
			mc.currentScreen.drawTexturedModalRect(51, baseY + 38, air.getID() * 40, 182, 40, 3);
		}

		String t = temp == null ? " -" : temp.localize();
		mc.fontRenderer.drawString(t, 100, baseY + 13, 0x993030, false);

		String h = hum == null ? "  -" : hum.localize();
		mc.fontRenderer.drawString(h, 100, baseY + 24, 0x303099, false);

		String a = air == null ? "  -" : air.localize();
		mc.fontRenderer.drawString(a, 100, baseY + 35, 0x309930, false);

		mc.fontRenderer.drawString(recipe.getFluidName(), 5, baseY + 3, 0x000000, false);

		mc.fontRenderer.drawString(recipe.getDrinkEffect(), 35, baseY + 46, 0x000000, false);
	}

	@Override
	public List<String> getTooltipStrings(int x, int y) {
		List<String> s = new ArrayList<String>();
		return null;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}

}
