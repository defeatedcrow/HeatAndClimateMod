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
import defeatedcrow.hac.api.recipe.IHeatTreatment;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.material.CoreInit;
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

public class HeatTreatmentCategory implements IRecipeCategory<IHeatTreatment> {
	protected IDrawable background;
	protected IDrawable icon;

	public HeatTreatmentCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableItemStack(new ItemStack(CoreInit.METALBLOCK_STEEL.get()));
		background = guiHelper.drawableBuilder(PluginTexDC.HEAT_TREATMENT.getLocation(), 18, 26, 140, 124)
				.addPadding(0, 0, 9, 3)
				.build();
	}

	@Override
	public RecipeType<IHeatTreatment> getRecipeType() {
		return JEIPluginDC.HEAT_TREATMENT_DATA;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("dcs.gui.jei.heat_treatment_data");
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
	public void setRecipe(IRecipeLayoutBuilder builder, IHeatTreatment recipe, IFocusGroup focuses) {

		builder.addSlot(RecipeIngredientRole.INPUT, 23, 4).addIngredients(recipe.getHeatingInput());

		if (ConfigCommonBuilder.INSTANCE.enHeatTreatment.get())
			builder.addSlot(RecipeIngredientRole.INPUT, 23, 38).addItemStack(new ItemStack(recipe.getHeatingOutput()));

		if (ConfigCommonBuilder.INSTANCE.enHeatTreatment.get())
			builder.addSlot(RecipeIngredientRole.INPUT, 23, 72).addItemStack(new ItemStack(recipe.getCoolingOutput()));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 23, 103).addItemStack(new ItemStack(recipe.getOutput()));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 103).addItemStack(new ItemStack(recipe.getFail()));

		List<DCHeatTier> heats = recipe.getHeatingTemp();
		for (DCHeatTier heat : heats) {
			builder.addSlot(RecipeIngredientRole.INPUT, 51 + heat.getID() * 6, 5).addIngredient(IngredientTypeDC.HEAT_TIER, heat).setCustomRenderer(IngredientTypeDC.HEAT_TIER,
					new HeatTierRenderer(6, 3));
		}

		List<DCHumidity> hums = recipe.getHeatingHum();
		for (DCHumidity hum : hums) {
			builder.addSlot(RecipeIngredientRole.INPUT, 51 + hum.getID() * 21, 11).addIngredient(IngredientTypeDC.HUMIDITY, hum).setCustomRenderer(IngredientTypeDC.HUMIDITY,
					new HumidityRenderer(21, 3));
		}

		List<DCAirflow> airs = recipe.getHeatingAir();
		for (DCAirflow air : airs) {
			builder.addSlot(RecipeIngredientRole.INPUT, 51 + air.getID() * 21, 17).addIngredient(IngredientTypeDC.AIRFLOW, air).setCustomRenderer(IngredientTypeDC.AIRFLOW,
					new AirflowRenderer(21, 3));
		}

		if (!recipe.getCoolingTemp().isEmpty() && ConfigCommonBuilder.INSTANCE.enHeatTreatment.get()) {
			List<DCHeatTier> heats2 = recipe.getCoolingTemp();
			for (DCHeatTier heat : heats2) {
				builder.addSlot(RecipeIngredientRole.INPUT, 51 + heat.getID() * 6, 39).addIngredient(IngredientTypeDC.HEAT_TIER, heat).setCustomRenderer(IngredientTypeDC.HEAT_TIER,
						new HeatTierRenderer(6, 3));
			}

			List<DCHumidity> hums2 = recipe.getCoolingHum();
			for (DCHumidity hum : hums2) {
				builder.addSlot(RecipeIngredientRole.INPUT, 51 + hum.getID() * 21, 45).addIngredient(IngredientTypeDC.HUMIDITY, hum).setCustomRenderer(IngredientTypeDC.HUMIDITY,
						new HumidityRenderer(21, 3));
			}

			List<DCAirflow> airs2 = recipe.getCoolingAir();
			for (DCAirflow air : airs2) {
				builder.addSlot(RecipeIngredientRole.INPUT, 51 + air.getID() * 21, 51).addIngredient(IngredientTypeDC.AIRFLOW, air).setCustomRenderer(IngredientTypeDC.AIRFLOW,
						new AirflowRenderer(21, 3));
			}
		}

		if (!recipe.getAnnealingTemp().isEmpty() && ConfigCommonBuilder.INSTANCE.enHeatTreatment.get()) {
			List<DCHeatTier> heats3 = recipe.getAnnealingTemp();
			for (DCHeatTier heat : heats3) {
				builder.addSlot(RecipeIngredientRole.INPUT, 51 + heat.getID() * 6, 73).addIngredient(IngredientTypeDC.HEAT_TIER, heat).setCustomRenderer(IngredientTypeDC.HEAT_TIER,
						new HeatTierRenderer(6, 3));
			}

			List<DCHumidity> hums3 = recipe.getAnnealingHum();
			for (DCHumidity hum : hums3) {
				builder.addSlot(RecipeIngredientRole.INPUT, 51 + hum.getID() * 21, 77).addIngredient(IngredientTypeDC.HUMIDITY, hum).setCustomRenderer(IngredientTypeDC.HUMIDITY,
						new HumidityRenderer(21, 3));
			}

			List<DCAirflow> airs3 = recipe.getAnnealingAir();
			for (DCAirflow air : airs3) {
				builder.addSlot(RecipeIngredientRole.INPUT, 51 + air.getID() * 21, 85).addIngredient(IngredientTypeDC.AIRFLOW, air).setCustomRenderer(IngredientTypeDC.AIRFLOW,
						new AirflowRenderer(21, 3));
			}
		}
	}

	@Override
	public List<Component> getTooltipStrings(IHeatTreatment recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		List<Component> list = Lists.newArrayList();
		return list;
	}

	@Override
	public void draw(IHeatTreatment recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;

		RenderSystem.setShaderTexture(0, PluginTexDC.HEAT_TREATMENT.getLocation());
		RenderSystem.setShader(GameRenderer::getPositionTexShader);

		MutableComponent text1 = Component.literal(recipe.getHeatingTime() + "Tick");

		if (recipe.getCoolingTemp().isEmpty() || !ConfigCommonBuilder.INSTANCE.enHeatTreatment.get()) {
			drawTexturedModalRect(stack.last().pose(), 11, 25, 0, 190, 137, 40);
		}

		if (recipe.getAnnealingTemp().isEmpty() || !ConfigCommonBuilder.INSTANCE.enHeatTreatment.get()) {
			drawTexturedModalRect(stack.last().pose(), 11, 59, 0, 190, 137, 40);
		}

		if (!recipe.getCoolingTemp().isEmpty() && ConfigCommonBuilder.INSTANCE.enHeatTreatment.get()) {
			font.draw(stack, text1, 45, 26, 0xFF000000);
		}

		if (!recipe.getAnnealingTemp().isEmpty() && ConfigCommonBuilder.INSTANCE.enHeatTreatment.get()) {
			font.draw(stack, text1, 45, 60, 0xFF000000);
		}

		font.draw(stack, text1, 45, 93, 0xFF000000);
		MutableComponent text2 = Component.literal("Failure");
		font.draw(stack, text2, 80, 106, 0xFF000000);

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
