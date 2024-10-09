package defeatedcrow.hac.plugin.jei;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.api.recipe.RecipeTypeDC;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class DeviceCrusherCategory implements IRecipeCategory<IDeviceRecipe> {
	protected IDrawable background;
	protected IDrawable icon;
	protected final String recipeName;
	protected final Ingredient catalyst;

	public DeviceCrusherCategory(IGuiHelper guiHelper, Supplier<ItemStack> cat, String name) {
		icon = guiHelper.createDrawableItemStack(cat.get());
		background = guiHelper.drawableBuilder(PluginTexDC.CRUSHER.getLocation(), 8, 8, 154, 68)
				.addPadding(0, 0, 0, 0)
				.build();
		catalyst = Ingredient.of(cat.get());
		recipeName = name;
	}

	@Override
	public RecipeType<IDeviceRecipe> getRecipeType() {
		return JEIPluginDC.CRUSHER_PULVERISE_DATA;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("dcs.gui.jei.crusher_" + recipeName + "_data");
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

		builder.addSlot(RecipeIngredientRole.INPUT, 6, 3).addIngredients(recipe.getInputs().get(0));

		builder.addSlot(RecipeIngredientRole.INPUT, 6, 49).addIngredients(catalyst);

		if (!recipe.getOutput().isEmpty() && (recipe.getType() != RecipeTypeDC.SQUEEZE || recipe.getOutputFluid().isEmpty()))
			builder.addSlot(RecipeIngredientRole.OUTPUT, 31, 42).addItemStack(recipe.getOutput());

		if (!recipe.getSecondaryOutput().isEmpty())
			builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 42).addItemStack(recipe.getSecondaryOutput());

		if (!recipe.getTertiaryOutput().isEmpty())
			builder.addSlot(RecipeIngredientRole.OUTPUT, 87, 42).addItemStack(recipe.getTertiaryOutput());

		if (!recipe.getOutputFluid().isEmpty()) {
			builder.addSlot(RecipeIngredientRole.OUTPUT, 126, 42).addFluidStack(recipe.getOutputFluid().getFluid(), recipe.getOutputFluid().getAmount());
		}
	}

	@Override
	public List<Component> getTooltipStrings(IDeviceRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		List<Component> list = Lists.newArrayList();
		return list;
	}

	@Override
	public void draw(IDeviceRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;
		int chance1 = recipe.getSecondaryRate();
		if (chance1 > 0) {
			String text = chance1 + "%";
			font.draw(stack, text, 58, 28, 0xFF000000);
		}
		int chance2 = recipe.getTertiaryRate();
		if (chance2 > 0) {
			String text = chance2 + "%";
			font.draw(stack, text, 86, 28, 0xFF000000);
		}
	}
}
