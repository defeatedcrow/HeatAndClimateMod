package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.plugin.jei.ingredients.AirflowRenderer;
import defeatedcrow.hac.plugin.jei.ingredients.HeatTierRenderer;
import defeatedcrow.hac.plugin.jei.ingredients.HumidityRenderer;
import defeatedcrow.hac.plugin.jei.ingredients.IngredientTypeDC;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

public class DeviceFermentationCategory implements IRecipeCategory<IDeviceRecipe> {
	protected IDrawable background;
	protected IDrawable icon;

	public DeviceFermentationCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableItemStack(new ItemStack(MachineInit.FERMANTATION_JAR_NORMAL.get()));
		background = guiHelper.drawableBuilder(PluginTexDC.FERMENTATION.getLocation(), 15, 6, 142, 92)
			.addPadding(0, 0, 8, 3)
			.build();
	}

	@Override
	public RecipeType<IDeviceRecipe> getRecipeType() {
		return JEIPluginDC.FERMENTATION_DATA;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("dcs.gui.jei.fermentation_data");
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

		int l = Math.min(3, recipe.getInputs().size());
		for (int l1 = 0; l1 < l; l1++) {
			int y = l1 * 18;
			builder.addSlot(RecipeIngredientRole.INPUT, 66, 5 + y).addIngredients(recipe.getInputs().get(l1));
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 13).addItemStack(recipe.getOutput().copy());

		if (!recipe.getSecondaryOutput().isEmpty())
			builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 36).addItemStack(recipe.getSecondaryOutput().copy());

		if (!recipe.getInputFluids().isEmpty()) {
			TagKey<Fluid> tag = recipe.getInputFluids().get(0);
			List<Fluid> list = TagUtil.getFluidList(tag);
			if (!list.isEmpty()) {
				Fluid f = list.get(0);
				builder.addSlot(RecipeIngredientRole.INPUT, 43, 36).addFluidStack(f, 1000);
			}
		}

		if (!recipe.getOutputFluid().isEmpty()) {
			builder.addSlot(RecipeIngredientRole.OUTPUT, 127, 37).addFluidStack(recipe.getOutputFluid().getFluid(), recipe.getOutputFluid().getAmount());
		}

		List<DCHeatTier> heats = recipe.requiredHeat();
		if (heats.isEmpty()) {
			heats.addAll(DCHeatTier.elements());
		}
		for (DCHeatTier heat : heats) {
			builder.addSlot(RecipeIngredientRole.INPUT, 38 + heat.getID() * 6, 66).addIngredient(IngredientTypeDC.HEAT_TIER, heat).setCustomRenderer(IngredientTypeDC.HEAT_TIER,
				new HeatTierRenderer(6, 3));
		}

		List<DCHumidity> hums = recipe.requiredHum();
		if (hums.isEmpty()) {
			hums.addAll(DCHumidity.elements());
		}
		for (DCHumidity hum : hums) {
			builder.addSlot(RecipeIngredientRole.INPUT, 38 + hum.getID() * 21, 75).addIngredient(IngredientTypeDC.HUMIDITY, hum).setCustomRenderer(IngredientTypeDC.HUMIDITY,
				new HumidityRenderer(21, 3));
		}

		List<DCAirflow> airs = recipe.requiredAir();
		if (airs.isEmpty()) {
			airs.addAll(DCAirflow.elements());
		}
		for (DCAirflow air : airs) {
			builder.addSlot(RecipeIngredientRole.INPUT, 38 + air.getID() * 21, 85).addIngredient(IngredientTypeDC.AIRFLOW, air).setCustomRenderer(IngredientTypeDC.AIRFLOW,
				new AirflowRenderer(21, 3));
		}
	}

	@Override
	public List<Component> getTooltipStrings(IDeviceRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		List<Component> list = Lists.newArrayList();
		// list.add(Component.translatable("X :" + mouseX + ", Y: " + mouseY));

		if (!recipe.getInputFluids().isEmpty()) {
			TagKey<Fluid> tag = recipe.getInputFluids().get(0);
			if (mouseX > 37 && mouseX < 53 && mouseY > 12 && mouseY < 54) {
				list.add(Component.literal("Fluid Tag: " + tag.location().toString()));
			}
		}

		return list;
	}

	@Override
	public void draw(IDeviceRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {

	}

	private static void drawTexturedModalRect(Matrix4f mat, int x, int y, int tX, int tY, int wid, int hei) {
		float f = 1F / 256F;
		float f1 = 1F / 256F;
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(mat, x + 0, y + hei, 90.0F).uv((tX + 0) * f, (tY + hei) * f1).endVertex();
		bufferbuilder.vertex(mat, x + wid, y + hei, 90.0F).uv((tX + wid) * f, (tY + hei) * f1).endVertex();
		bufferbuilder.vertex(mat, x + wid, y + 0, 90.0F).uv((tX + wid) * f, (tY + 0) * f1).endVertex();
		bufferbuilder.vertex(mat, x + 0, y + 0, 90.0F).uv((tX + 0) * f, (tY + 0) * f1).endVertex();
		Tesselator.getInstance().end();
	}

}
