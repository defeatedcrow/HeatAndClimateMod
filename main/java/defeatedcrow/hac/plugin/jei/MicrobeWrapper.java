package defeatedcrow.hac.plugin.jei;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.plugin.jei.ingredients.ClimateTypes;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.api.brewing.EnumHabitat;
import defeatedcrow.hac.main.api.brewing.EnumMedium;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class MicrobeWrapper implements IRecipeWrapper {

	public final IMicrobe recipe;
	public final List<DCHeatTier> temp;
	public final List<DCHumidity> hum;
	public final List<DCAirflow> air;
	public final List<ItemStack> outputs;
	public final List<ItemStack> mediums;

	public MicrobeWrapper(IMicrobe info) {
		recipe = info;
		temp = Lists.newArrayList();
		hum = Lists.newArrayList();
		air = Lists.newArrayList();
		outputs = Lists.newArrayList();
		mediums = Lists.newArrayList();
		temp.addAll(info.getHeats());
		hum.addAll(info.getHums());
		air.addAll(info.getAirs());
		for (EnumMedium medium : info.getMediums()) {
			mediums.add(new ItemStack(FoodInit.medium, 1, medium.id));
		}
		outputs.add(new ItemStack(info.getMicrobeItem(), 1, 0));
		outputs.add(new ItemStack(info.getMicrobeItem(), 1, 1));
		outputs.add(new ItemStack(info.getMicrobeItem(), 1, 2));
	}

	@Override
	public void getIngredients(IIngredients ing) {
		ing.setInputs(ClimateTypes.TEMP, temp);
		ing.setInputs(ClimateTypes.HUM, hum);
		ing.setInputs(ClimateTypes.AIR, air);
		ing.setInputs(VanillaTypes.ITEM, mediums);
		ing.setOutputs(VanillaTypes.ITEM, outputs);
	}

	public List<ItemStack> getOutput() {
		return outputs;
	}

	public List<ItemStack> getMedium() {
		return mediums;
	}

	public List<DCHeatTier> getTemp() {
		return temp;
	}

	public List<DCHumidity> getHum() {
		return hum;
	}

	public List<DCAirflow> getAir() {
		return air;
	}

	public IMicrobe getRecipe() {
		return recipe;
	}

	@Override
	public void drawInfo(Minecraft mc, int wid, int hei, int mouseX, int mouseY) {
		ResourceLocation res = new ResourceLocation("dcs_climate", "textures/gui/microbe_gui_jei.png");
		mc.getTextureManager().bindTexture(res);
		int baseX = 33;
		int baseY = 35;
		// cycle1
		for (DCHeatTier h : temp) {
			mc.currentScreen.drawTexturedModalRect(baseX + h.getID() * 6, baseY, h.getID() * 6, 170, 6, 3);
		}
		for (DCHumidity h : hum) {
			mc.currentScreen.drawTexturedModalRect(baseX + h.getID() * 21, baseY + 6, h.getID() * 21, 174, 21, 3);
		}
		for (DCAirflow a : air) {
			mc.currentScreen.drawTexturedModalRect(baseX + a.getID() * 21, baseY + 12, a.getID() * 21, 178, 21, 3);
		}

		for (int i = 0; i < EnumHabitat.values().length; i++) {
			EnumHabitat hub = EnumHabitat.values()[i];
			if (recipe.getChance(hub) > 0)
				mc.currentScreen.drawTexturedModalRect(23 + i * 17, 62, i * 16, 190, 16, 16);
		}

		if (recipe.getGramStaining()) {
			mc.currentScreen.drawTexturedModalRect(17, 12, 177, 50, 16, 16);
		} else {
			mc.currentScreen.drawTexturedModalRect(17, 12, 177, 34, 16, 16);
		}

		String s1 = getOutput().get(0).getDisplayName();
		mc.fontRenderer.drawSplitString(s1, 45, 10, 110, 0x000000);

		String s2 = recipe.getType().localize();
		mc.fontRenderer.drawSplitString(s2, 45, 22, 110, 0x000000);

		String s3 = I18n.format("dcs.tip.habitat");
		mc.fontRenderer.drawSplitString(s3, 15, 52, 110, 0x000000);

		String s4 = I18n.format("dcs.tip.medium");
		mc.fontRenderer.drawSplitString(s4, 15, 81, 110, 0x000000);

	}

	@Override
	public List<String> getTooltipStrings(int x, int y) {
		List<String> s = new ArrayList<String>();
		// s.add(x + "," + y);
		if (y > 62 && y < 77) {
			if (x > 22 && x < 38) {
				int c = recipe.getChance(EnumHabitat.SOIL);
				if (c > 0) {
					s.add(EnumHabitat.SOIL.localize() + ": " + c + "%");
				}
			}
			if (x > 39 && x < 55) {
				int c = recipe.getChance(EnumHabitat.FLOWER);
				if (c > 0) {
					s.add(EnumHabitat.FLOWER.localize() + ": " + c + "%");
				}
			}
			if (x > 56 && x < 72) {
				int c = recipe.getChance(EnumHabitat.CROP);
				if (c > 0) {
					s.add(EnumHabitat.CROP.localize() + ": " + c + "%");
				}
			}
			if (x > 73 && x < 89) {
				int c = recipe.getChance(EnumHabitat.ANIMAL);
				if (c > 0) {
					s.add(EnumHabitat.ANIMAL.localize() + ": " + c + "%");
				}
			}
			if (x > 90 && x < 106) {
				int c = recipe.getChance(EnumHabitat.WATER);
				if (c > 0) {
					s.add(EnumHabitat.WATER.localize() + ": " + c + "%");
				}
			}
		}
		return s;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}

}
