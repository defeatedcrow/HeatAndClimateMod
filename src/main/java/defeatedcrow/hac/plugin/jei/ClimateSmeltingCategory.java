package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.food.material.FoodInit;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

public class ClimateSmeltingCategory implements IRecipeCategory<IClimateSmelting> {
	protected IDrawable background;
	protected IDrawable icon;

	public ClimateSmeltingCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableItemStack(new ItemStack(FoodInit.BREAD_ROUND_BAKED_ITEM.get()));
		background = guiHelper.drawableBuilder(PluginTexDC.SMELTING.getLocation(), 21, 20, 127, 68)
				.addPadding(0, 0, 10, 3)
				.build();
	}

	@Override
	public RecipeType<IClimateSmelting> getRecipeType() {
		return JEIPluginDC.SMELTING_DATA;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("dcs.gui.jei.smelting_data");
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
	public void setRecipe(IRecipeLayoutBuilder builder, IClimateSmelting recipe, IFocusGroup focuses) {

		builder.addSlot(RecipeIngredientRole.INPUT, 43, 5).addIngredients(recipe.getInput());

		builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 5).addItemStack(recipe.getOutput().copy());

		List<DCHeatTier> heats = recipe.requiredHeat();
		if (heats.isEmpty()) {
			heats.addAll(DCHeatTier.elements());
		}
		for (DCHeatTier heat : heats) {
			builder.addSlot(RecipeIngredientRole.INPUT, 34 + heat.getID() * 6, 28).addIngredient(IngredientTypeDC.HEAT_TIER, heat).setCustomRenderer(IngredientTypeDC.HEAT_TIER,
					new HeatTierRenderer(6, 3));
		}

		List<DCHumidity> hums = recipe.requiredHum();
		if (hums.isEmpty()) {
			hums.addAll(DCHumidity.elements());
		}
		for (DCHumidity hum : hums) {
			builder.addSlot(RecipeIngredientRole.INPUT, 34 + hum.getID() * 21, 38).addIngredient(IngredientTypeDC.HUMIDITY, hum).setCustomRenderer(IngredientTypeDC.HUMIDITY,
					new HumidityRenderer(21, 3));
		}

		List<DCAirflow> airs = recipe.requiredAir();
		if (airs.isEmpty()) {
			airs.addAll(DCAirflow.elements());
		}
		for (DCAirflow air : airs) {
			builder.addSlot(RecipeIngredientRole.INPUT, 34 + air.getID() * 21, 48).addIngredient(IngredientTypeDC.AIRFLOW, air).setCustomRenderer(IngredientTypeDC.AIRFLOW,
					new AirflowRenderer(21, 3));
		}
	}

	@Override
	public List<Component> getTooltipStrings(IClimateSmelting recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		List<Component> list = Lists.newArrayList();
		// list.add(Component.translatable("X :" + mouseX + ", Y: " + mouseY));

		if (recipe.hasBlockProcess())
			if (mouseX > 33 && mouseX < 40 && mouseY > 56 && mouseY < 65) {
				list.add(Component.translatable("dcs.gui.jei.smelting_tip1"));
			}

		if (recipe.hasEntityProcess())
			if (mouseX > 42 && mouseX < 49 && mouseY > 56 && mouseY < 65) {
				list.add(Component.translatable("dcs.gui.jei.smelting_tip2"));
			}

		if (recipe.hasDropItemProcess())
			if (mouseX > 51 && mouseX < 58 && mouseY > 56 && mouseY < 65) {
				list.add(Component.translatable("dcs.gui.jei.smelting_tip3"));
			}

		return list;
	}

	@Override
	public void draw(IClimateSmelting recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;

		MutableComponent text4 = Component.literal(recipe.recipeFrequency() + "Tick");
		font.draw(stack, text4, 70, 56, 0xFF000000);

		RenderSystem.setShaderTexture(0, PluginTexDC.SMELTING.getLocation());
		RenderSystem.setShader(GameRenderer::getPositionTexShader);

		if (recipe.hasBlockProcess())
			drawTexturedModalRect(stack.last().pose(), 33, 56, 0, 184, 7, 9);

		if (recipe.hasEntityProcess())
			drawTexturedModalRect(stack.last().pose(), 42, 56, 9, 184, 7, 9);

		if (recipe.hasDropItemProcess())
			drawTexturedModalRect(stack.last().pose(), 51, 56, 18, 184, 7, 9);

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
