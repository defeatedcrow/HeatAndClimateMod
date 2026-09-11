package defeatedcrow.hac.plugin.jei;

import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.machine.material.MachineInit;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class DeviceMillCategory implements IRecipeCategory<IDeviceRecipe> {
	protected IDrawable background;
	protected IDrawable icon;

	public DeviceMillCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableItemStack(new ItemStack(MachineInit.STONE_MILL.get()));
		background = guiHelper.drawableBuilder(PluginTexDC.MILL.getLocation(), 20, 10, 135, 60).addPadding(0, 0, 0, 0).build();
	}

	@Override
	public RecipeType<IDeviceRecipe> getRecipeType() {
		return JEIPluginDC.MILL_DATA;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("dcs.gui.jei.mill_data");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, IDeviceRecipe recipe, IFocusGroup focuses) {

		builder.addSlot(RecipeIngredientRole.INPUT, 11, 4).addIngredients(recipe.getInputs().get(0));

		ItemStack output = recipe.getOutput().copy();
		builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 32).addItemStack(output);

		if (!recipe.getSecondaryOutput().isEmpty())
			builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 32).addItemStack(recipe.getSecondaryOutput().copy());
	}

	@Override
	public void getTooltip(ITooltipBuilder tooltip, IDeviceRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {}

	@Override
	public void draw(IDeviceRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;
		int chance1 = recipe.getSecondaryRate();
		if (chance1 > 0) {
			String text = chance1 + "%";
			graphics.drawString(font, text, 108, 16, 0xFF000000, false);
		}
	}
}
