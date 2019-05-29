package defeatedcrow.hac.plugin.jei;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class DCInfoCategory implements IRecipeCategory {

	private final IDrawableStatic background;

	public DCInfoCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("dcs_climate", "textures/gui/info_gui_jei.png");
		background = guiHelper.createDrawable(location, 21, 18, 134, 122);
	}

	@Override
	public String getUid() {
		return "dcs_climate.info";
	}

	@Override
	public String getTitle() {
		return I18n.format(getUid());
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft mc) {}

	@Override
	public IDrawable getIcon() {
		return null;
	}

	@Override
	public void setRecipe(IRecipeLayout layout, IRecipeWrapper wrapper, IIngredients ing) {
		if (ing != null) {

			List<ItemStack> in = ing.getInputs(VanillaTypes.ITEM).get(0);
			List<ItemStack> out = ing.getOutputs(VanillaTypes.ITEM).get(0);
			List<ItemStack> mac = ing.getInputs(VanillaTypes.ITEM).get(1);
			List<FluidStack> f = ing.getOutputs(VanillaTypes.FLUID).get(0);

			if (!in.isEmpty()) {
				layout.getItemStacks().init(0, true, 36, 20);
				layout.getItemStacks().set(0, in);
			}

			if (!out.isEmpty()) {
				layout.getItemStacks().init(1, false, 80, 20);
				layout.getItemStacks().set(1, out);
			}

			if (!mac.isEmpty()) {
				layout.getItemStacks().init(2, true, 58, 5);
				layout.getItemStacks().set(2, mac);
			}

			// if (!f.isEmpty()) {
			layout.getFluidStacks().init(3, false, 81, 6, 16, 16, 1000, false, null);
			layout.getFluidStacks().set(3, f.get(0));
			// }
		}

	}

	@Override
	public String getModName() {
		return ClimateCore.MOD_NAME;
	}

}
