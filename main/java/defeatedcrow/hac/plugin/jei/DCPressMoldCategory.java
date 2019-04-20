package defeatedcrow.hac.plugin.jei;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DCPressMoldCategory implements IRecipeCategory {

	private final IDrawableStatic background;

	public DCPressMoldCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("dcs_climate", "textures/gui/press_machine_gui_jei.png");
		background = guiHelper.createDrawable(location, 16, 16, 142, 112);
	}

	@Override
	public String getUid() {
		return "dcs_climate.pressmold";
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
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if (!(recipeWrapper instanceof DCPressMoldWrapper))
			return;
		if (ingredients != null) {
			DCPressMoldWrapper wrapper = ((DCPressMoldWrapper) recipeWrapper);
			List<ItemStack> in = wrapper.getInput();
			List<ItemStack> outputs = wrapper.getOutputs();
			ItemStack mold = wrapper.getMold();

			recipeLayout.getItemStacks().init(0, false, 8, 7);
			recipeLayout.getItemStacks().set(0, mold);

			recipeLayout.getItemStacks().init(1, false, 8, 51);
			recipeLayout.getItemStacks().set(1, mold);

			if (!in.isEmpty()) {
				for (int i = 0; i < 3; i++) {
					for (int k = 0; k < 3; k++) {
						if ((k + i * 3) < in.size()) {
							recipeLayout.getItemStacks().init(2 + k + i * 3, true, 28 + k * 18, 51 + i * 18);
							recipeLayout.getItemStacks().set(2 + k + i * 3, in.get(k + i * 3));
						}
					}
				}
			}

			recipeLayout.getItemStacks().init(11, false, 120, 60);
			recipeLayout.getItemStacks().set(11, outputs.get(0));

		}

	}

	@Override
	public String getModName() {
		return ClimateCore.MOD_NAME;
	}

}
