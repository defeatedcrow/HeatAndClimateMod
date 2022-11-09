package defeatedcrow.hac.plugin.jei;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

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
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.crop.ICropData.SoilType;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.ClimateCropBaseBlock;
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
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class CropDataCategory implements IRecipeCategory<ClimateCropBaseBlock> {
	protected IDrawable background;
	protected IDrawable icon;

	public CropDataCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableItemStack(new ItemStack(FoodInit.CROP_AL_WILD.get()));
		background = guiHelper.drawableBuilder(PluginTexDC.CROP.getLocation(), 21, 18, 134, 123)
			.addPadding(0, 0, 10, 8)
			.build();
	}

	@Override
	public RecipeType<ClimateCropBaseBlock> getRecipeType() {
		return JEIPluginDC.CROP_DATA;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("dcs.gui.jei.crop_data");
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
	public void setRecipe(IRecipeLayoutBuilder builder, ClimateCropBaseBlock recipe, IFocusGroup focuses) {
		CropTier tier = recipe.getTier();

		builder.addSlot(RecipeIngredientRole.INPUT, 21, 11).addItemStack(new ItemStack(recipe.getSeedItem(tier)));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 40, 11).addItemStack(new ItemStack(recipe.getCropItem(tier)));

		// mutation
		Optional<Block> wild = recipe.getMutationTarget(CropTier.WILD);
		if (wild.isPresent())
			builder.addSlot(RecipeIngredientRole.OUTPUT, 27, 88).addItemStack(new ItemStack(wild.get()));

		Optional<Block> common = recipe.getMutationTarget(CropTier.COMMON);
		if (common.isPresent())
			builder.addSlot(RecipeIngredientRole.OUTPUT, 60, 88).addItemStack(new ItemStack(common.get()));

		Optional<Block> rare = recipe.getMutationTarget(CropTier.RARE);
		if (rare.isPresent())
			builder.addSlot(RecipeIngredientRole.OUTPUT, 81, 88).addItemStack(new ItemStack(rare.get()));

		Optional<Block> epic = recipe.getMutationTarget(CropTier.EPIC);
		if (epic.isPresent())
			builder.addSlot(RecipeIngredientRole.OUTPUT, 102, 88).addItemStack(new ItemStack(epic.get()));

		List<DCHeatTier> heats = recipe.getSuitableTemp(tier);
		for (DCHeatTier heat : heats) {
			builder.addSlot(RecipeIngredientRole.INPUT, 42 + heat.getID() * 6, 34).addIngredient(IngredientTypeDC.HEAT_TIER, heat).setCustomRenderer(IngredientTypeDC.HEAT_TIER,
				new HeatTierRenderer(6, 3));
		}

		List<DCHumidity> hums = recipe.getSuitableHum(tier);
		for (DCHumidity hum : hums) {
			builder.addSlot(RecipeIngredientRole.INPUT, 42 + hum.getID() * 21, 40).addIngredient(IngredientTypeDC.HUMIDITY, hum).setCustomRenderer(IngredientTypeDC.HUMIDITY,
				new HumidityRenderer(21, 3));
		}

		List<DCAirflow> airs = recipe.getSuitableAir(tier);
		for (DCAirflow air : airs) {
			builder.addSlot(RecipeIngredientRole.INPUT, 42 + air.getID() * 21, 46).addIngredient(IngredientTypeDC.AIRFLOW, air).setCustomRenderer(IngredientTypeDC.AIRFLOW,
				new AirflowRenderer(21, 3));
		}
	}

	@Override
	public List<Component> getTooltipStrings(ClimateCropBaseBlock recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		CropTier tier = recipe.getTier();
		List<Component> list = Lists.newArrayList();
		// list.add(Component.translatable("X :" + mouseX + ", Y: " + mouseY));

		if (mouseX > 16 && mouseX < 25 && mouseY > 105 && mouseY < 114) {
			list.add(Component.translatable("dcs.gui.jei.crop_tip1"));
			list.add(Component.translatable("dcs.gui.jei.crop_tip2"));
		}

		return list;
	}

	@Override
	public void draw(ClimateCropBaseBlock recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
		CropTier tier = recipe.getTier();
		CropType type = recipe.getFamily();
		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;

		MutableComponent name = recipe.getName();
		font.draw(stack, name, 70, 9, 0xFF000000);

		MutableComponent com = type.localize();
		int c = 0xFF808080;
		if (tier == CropTier.WILD) {
			c = ChatFormatting.BLACK.getColor();
			com.append(" WILD");
		} else if (tier == CropTier.COMMON) {
			c = ChatFormatting.GRAY.getColor();
			com.append(" COMMON");
		} else if (tier == CropTier.RARE) {
			c = ChatFormatting.GOLD.getColor();
			com.append(" RARE");
		} else if (tier == CropTier.EPIC) {
			c = ChatFormatting.DARK_AQUA.getColor();
			com.append(" EPIC");
		}
		font.draw(stack, com, 65, 22, c);

		MutableComponent text4 = Component.translatable("dcs.gui.jei.habitat");
		text4.append(":");
		if (!recipe.getGeneratedBiomeTag(tier).isEmpty()) {
			for (String s : recipe.getGeneratedBiomeTag(tier)) {
				text4.append(" " + s);
			}
		} else {
			text4.append(" ").append(Component.translatable("dcs.gui.jei.no_habitat"));
		}
		font.draw(stack, text4, 25, 72, 0xFF000000);

		int chance1 = CropTier.COMMON.getMutationChance();
		String text = chance1 + "%";
		font.draw(stack, text, 60, 106, 0xFF000000);

		int chance2 = CropTier.RARE.getMutationChance();
		String text2 = chance2 + "%";
		font.draw(stack, text2, 81, 106, 0xFF000000);

		if (recipe.getMutationTarget(CropTier.EPIC).isPresent()) {
			int chance3 = CropTier.EPIC.getMutationChance();
			String text3 = chance3 + "%";
			font.draw(stack, text3, 102, 106, 0xFF000000);
		}

		RenderSystem.setShaderTexture(0, PluginTexDC.CROP.getLocation());
		RenderSystem.setShader(GameRenderer::getPositionTexShader);

		List<SoilType> soils = recipe.getSoilTypes(tier);
		for (SoilType soil : soils) {
			if (soil == SoilType.FARMLAND) {
				drawTexturedModalRect(stack.last().pose(), 35, 53, 0, 182, 16, 16);
			} else if (soil == SoilType.DIRT) {
				drawTexturedModalRect(stack.last().pose(), 52, 53, 16, 182, 16, 16);
			} else if (soil == SoilType.SAND) {
				drawTexturedModalRect(stack.last().pose(), 69, 53, 32, 182, 16, 16);
			} else if (soil == SoilType.MUD) {
				drawTexturedModalRect(stack.last().pose(), 86, 53, 48, 182, 16, 16);
			} else if (soil == SoilType.WATER) {
				drawTexturedModalRect(stack.last().pose(), 103, 53, 64, 182, 16, 16);
			}
		}

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
