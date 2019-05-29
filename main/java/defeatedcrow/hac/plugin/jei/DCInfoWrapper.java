package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.main.api.IDCInfoData;
import defeatedcrow.hac.main.recipes.DCInfoData;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class DCInfoWrapper implements IRecipeWrapper {

	public final List<ItemStack> IN = Lists.newArrayList();
	public final List<ItemStack> OUT = Lists.newArrayList();
	public final List<ItemStack> MACHINE = Lists.newArrayList();
	public final List<ItemStack> IN2 = Lists.newArrayList();
	public final List<ItemStack> OUT2 = Lists.newArrayList();
	public final FluidStack INF;

	private final List<List<ItemStack>> ins = Lists.newArrayList();
	private final List<List<ItemStack>> outs = Lists.newArrayList();

	public final String TITLE;
	public final String DISC;

	@SuppressWarnings("unchecked")
	public DCInfoWrapper(IDCInfoData recipe) {
		if (!recipe.getInputs().isEmpty()) {
			IN.addAll(recipe.getInputs());
		}
		if (!recipe.getOutputs().isEmpty()) {
			OUT.addAll(recipe.getOutputs());
		}
		if (!recipe.getMachines().isEmpty()) {
			MACHINE.addAll(recipe.getMachines());
		}
		if (!recipe.getInputFluid().isEmpty()) {
			INF = recipe.getInputFluid().get(0);
		} else {
			INF = null;
		}
		TITLE = recipe.getTitle();
		DISC = recipe.getDisc();
		ins.add(IN);
		ins.add(MACHINE);
		ins.add(IN2);
		outs.add(OUT);
		outs.add(OUT2);
		outs.add(MACHINE);

		if (recipe instanceof DCInfoData) {
			if (!((DCInfoData) recipe).input2.isEmpty()) {
				IN2.addAll(((DCInfoData) recipe).input2);
			}
			if (!((DCInfoData) recipe).output2.isEmpty()) {
				OUT2.addAll(((DCInfoData) recipe).output2);
			}
		}
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, ins);
		ingredients.setOutputLists(VanillaTypes.ITEM, outs);
		ingredients.setOutput(VanillaTypes.FLUID, INF);
	}

	@Override
	public void drawInfo(Minecraft mc, int wid, int hei, int mouseX, int mouseY) {
		ResourceLocation res = new ResourceLocation("dcs_climate", "textures/gui/info_gui_jei.png");
		mc.getTextureManager().bindTexture(res);

		if (!IN.isEmpty()) {
			mc.currentScreen.drawTexturedModalRect(36, 20, 176, 0, 18, 18);
		}

		if (!OUT.isEmpty()) {
			mc.currentScreen.drawTexturedModalRect(80, 20, 176, 0, 18, 18);
		}

		if (!MACHINE.isEmpty()) {
			mc.currentScreen.drawTexturedModalRect(58, 5, 176, 0, 18, 18);
		}

		if (INF != null) {
			mc.currentScreen.drawTexturedModalRect(80, 5, 176, 0, 18, 18);
		}

		if (!IN.isEmpty() && !OUT.isEmpty()) {
			mc.currentScreen.drawTexturedModalRect(55, 22, 194, 0, 24, 18);
		}

		String s = I18n.format(TITLE);
		mc.fontRenderer.drawSplitString(s, 10, 45, 110, 0x000000);

		mc.fontRenderer.drawSplitString(I18n.format(DISC), 10, 60, 115, 0x000000);
	}

}
