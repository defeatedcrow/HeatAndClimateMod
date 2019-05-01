package defeatedcrow.hac.plugin.jei;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.plugin.jei.ingredients.ClimateTypes;
import defeatedcrow.hac.main.api.IHeatTreatment;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DCHeatTreatmentWrapper implements IRecipeWrapper {

	private final IHeatTreatment rec;
	private final List<ItemStack> input1;
	private final List<ItemStack> input2;
	private final List<ItemStack> input3;
	private final List<ItemStack> output;
	private final List<List<DCHeatTier>> temps;
	private final List<List<DCHumidity>> hums;
	private final List<List<DCAirflow>> airs;
	public int time1 = 0;
	public int time2 = 0;
	public int time3 = 0;

	@SuppressWarnings("unchecked")
	public DCHeatTreatmentWrapper(IHeatTreatment recipe) {
		rec = recipe;
		input1 = new ArrayList<ItemStack>();
		input2 = new ArrayList<ItemStack>();
		input3 = new ArrayList<ItemStack>();
		output = new ArrayList<ItemStack>();
		output.add(recipe.getOutput());
		output.add(recipe.getFail());

		temps = new ArrayList<List<DCHeatTier>>();
		hums = new ArrayList<List<DCHumidity>>();
		airs = new ArrayList<List<DCAirflow>>();

		input1.addAll(rec.getInput1());
		time1 = recipe.getBurnTime1();
		if (recipe.getTemp1().isEmpty()) {
			temps.add(DCHeatTier.createList());
		} else {
			temps.add(recipe.getTemp1());
		}
		if (recipe.getHum1().isEmpty()) {
			hums.add(DCHumidity.createList());
		} else {
			hums.add(recipe.getHum1());
		}
		if (recipe.getAir1().isEmpty()) {
			airs.add(DCAirflow.createList());
		} else {
			airs.add(recipe.getAir1());
		}

		if (!recipe.getInput2().isEmpty()) {
			input2.add(recipe.getInput2());
			time2 = recipe.getBurnTime2();
			if (recipe.getTemp2().isEmpty()) {
				temps.add(DCHeatTier.createList());
			} else {
				temps.add(recipe.getTemp2());
			}
			if (recipe.getHum2().isEmpty()) {
				hums.add(DCHumidity.createList());
			} else {
				hums.add(recipe.getHum2());
			}
			if (recipe.getAir2().isEmpty()) {
				airs.add(DCAirflow.createList());
			} else {
				airs.add(recipe.getAir2());
			}
		}

		if (!recipe.getInput3().isEmpty()) {
			input3.add(recipe.getInput3());
			time3 = recipe.getBurnTime3();
			if (recipe.getTemp3().isEmpty()) {
				temps.add(DCHeatTier.createList());
			} else {
				temps.add(recipe.getTemp3());
			}
			if (recipe.getHum3().isEmpty()) {
				hums.add(DCHumidity.createList());
			} else {
				hums.add(recipe.getHum3());
			}
			if (recipe.getAir3().isEmpty()) {
				airs.add(DCAirflow.createList());
			} else {
				airs.add(recipe.getAir3());
			}
		}

	}

	@Override
	public void getIngredients(IIngredients ing) {
		List<List<ItemStack>> in = new ArrayList<List<ItemStack>>();
		in.add(input1);
		in.add(input2);
		in.add(input3);
		ing.setInputLists(VanillaTypes.ITEM, in);
		ing.setOutputs(VanillaTypes.ITEM, output);
		ing.setInputLists(ClimateTypes.TEMP, temps);
		ing.setInputLists(ClimateTypes.HUM, hums);
		ing.setInputLists(ClimateTypes.AIR, airs);
	}

	public List<List<DCAirflow>> getAirs() {
		return airs;
	}

	public List<List<DCHumidity>> getHums() {
		return hums;
	}

	public List<List<DCHeatTier>> getTemps() {
		return temps;
	}

	public List<ItemStack> getInput1() {
		return input1;
	}

	public List<ItemStack> getInput2() {
		return input2;
	}

	public List<ItemStack> getInput3() {
		return input3;
	}

	public List<ItemStack> getOutputs() {
		return output;
	}

	@Override
	public void drawInfo(Minecraft mc, int wid, int hei, int mouseX, int mouseY) {
		ResourceLocation res = new ResourceLocation("dcs_climate", "textures/gui/metal_treatment_gui_jei.png");
		mc.getTextureManager().bindTexture(res);
		int baseX = 43;
		int baseY = 6;
		// cycle1
		for (DCHeatTier h : temps.get(0)) {
			mc.currentScreen.drawTexturedModalRect(baseX + h.getID() * 6, baseY, h.getID() * 6, 170, 6, 3);
		}
		for (DCHumidity h : hums.get(0)) {
			mc.currentScreen.drawTexturedModalRect(baseX + h.getID() * 21, baseY + 6, h.getID() * 21, 174, 21, 3);
		}
		for (DCAirflow a : airs.get(0)) {
			mc.currentScreen.drawTexturedModalRect(baseX + a.getID() * 21, baseY + 12, a.getID() * 21, 178, 21, 3);
		}

		if (input2.isEmpty()) {
			mc.currentScreen.drawTexturedModalRect(2, baseY + 20, 0, 190, 137, 40);
		} else {
			for (DCHeatTier h : temps.get(1)) {
				mc.currentScreen.drawTexturedModalRect(baseX + h.getID() * 6, baseY + 34, h.getID() * 6, 170, 6, 3);
			}
			for (DCHumidity h : hums.get(1)) {
				mc.currentScreen.drawTexturedModalRect(baseX + h.getID() * 21, baseY + 40, h.getID() * 21, 174, 21, 3);
			}
			for (DCAirflow a : airs.get(1)) {
				mc.currentScreen.drawTexturedModalRect(baseX + a.getID() * 21, baseY + 46, a.getID() * 21, 178, 21, 3);
			}
		}

		if (input3.isEmpty()) {
			mc.currentScreen.drawTexturedModalRect(2, baseY + 54, 0, 190, 137, 40);
		} else {
			for (DCHeatTier h : temps.get(2)) {
				mc.currentScreen.drawTexturedModalRect(baseX + h.getID() * 6, baseY + 68, h.getID() * 6, 170, 6, 3);
			}
			for (DCHumidity h : hums.get(2)) {
				mc.currentScreen.drawTexturedModalRect(baseX + h.getID() * 21, baseY + 74, h.getID() * 21, 174, 21, 3);
			}
			for (DCAirflow a : airs.get(2)) {
				mc.currentScreen.drawTexturedModalRect(baseX + a.getID() * 21, baseY + 80, a.getID() * 21, 178, 21, 3);
			}
		}
		String s = I18n.format("dcs.tip.heat_treatment_jei1");
		if (!input2.isEmpty())
			mc.fontRenderer.drawString(String.format("Within %d ticks", time1), 35, 26, 0xFF5050, false);
		if (!input3.isEmpty())
			mc.fontRenderer.drawString(String.format("Within %d ticks", time2), 35, 60, 0xFF5050, false);
		mc.fontRenderer.drawString(String.format(I18n.format("dcs.tip.heat_treatment_jei2")), 70, 105, 0xFF0000, false);
	}

}
