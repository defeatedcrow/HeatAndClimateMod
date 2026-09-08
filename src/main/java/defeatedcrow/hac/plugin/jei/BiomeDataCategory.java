package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import org.joml.Matrix4f;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.tag.TagDC;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;

public class BiomeDataCategory implements IRecipeCategory<Biome> {
	protected IDrawable background;
	protected IDrawable icon;

	public BiomeDataCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableItemStack(new ItemStack(MachineInit.STORMGLASS.get()));
		background = guiHelper.drawableBuilder(PluginTexDC.BIOME.getLocation(), 21, 19, 134, 125).addPadding(0, 0, 10, 8).build();
	}

	@Override
	public RecipeType<Biome> getRecipeType() {
		return JEIPluginDC.BIOME_DATA;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("dcs.gui.jei.biome_data");
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
	public void setRecipe(IRecipeLayoutBuilder builder, Biome recipe, IFocusGroup focuses) {
		float temp = recipe.getBaseTemperature();
		float tempOffset = ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(DCTimeHelper.staticSeason);
		temp -= tempOffset;
		float rainfall = recipe.getModifiedClimateSettings().downfall();
		List<TagKey<Biome>> tags = tagList(recipe);

		boolean isNether = tags.contains(BiomeTags.IS_NETHER);
		boolean isEnd = tags.contains(BiomeTags.IS_END);
		if (isNether)
			temp += ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(EnumSeason.SCORCHER);
		if (isEnd)
			temp += ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(EnumSeason.ABSOLUTE);

		DCHeatTier spr = DCHeatTier.getTypeByBiomeTemp(temp + ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(EnumSeason.SPRING_EARLY));
		DCHeatTier smr = DCHeatTier.getTypeByBiomeTemp(temp + ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(EnumSeason.SUMMER_EARLY));
		DCHeatTier aut = DCHeatTier.getTypeByBiomeTemp(temp + ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(EnumSeason.AUTUMN_EARLY));
		DCHeatTier wtr = DCHeatTier.getTypeByBiomeTemp(temp + ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(EnumSeason.WINTER_EARLY));
		builder.addSlot(RecipeIngredientRole.INPUT, 57, 39).addIngredient(IngredientTypeDC.HEAT_TIER, spr).setCustomRenderer(IngredientTypeDC.HEAT_TIER, new HeatTierRenderer(6, 3));
		builder.addSlot(RecipeIngredientRole.INPUT, 78, 39).addIngredient(IngredientTypeDC.HEAT_TIER, smr).setCustomRenderer(IngredientTypeDC.HEAT_TIER, new HeatTierRenderer(6, 3));
		builder.addSlot(RecipeIngredientRole.INPUT, 99, 39).addIngredient(IngredientTypeDC.HEAT_TIER, aut).setCustomRenderer(IngredientTypeDC.HEAT_TIER, new HeatTierRenderer(6, 3));
		builder.addSlot(RecipeIngredientRole.INPUT, 120, 39).addIngredient(IngredientTypeDC.HEAT_TIER, wtr).setCustomRenderer(IngredientTypeDC.HEAT_TIER, new HeatTierRenderer(6, 3));

		DCHumidity hum = DCHumidity.NORMAL;
		if (tags.contains(Tags.Biomes.IS_WET) || tags.contains(Tags.Biomes.IS_WATER) || rainfall > 0.8F)
			hum = DCHumidity.WET;
		else if (tags.contains(Tags.Biomes.IS_DRY) || rainfall <= 0.3F)
			hum = DCHumidity.DRY;
		DCAirflow air = tags.contains(BiomeTags.IS_HILL) || tags.contains(BiomeTags.IS_MOUNTAIN) ? DCAirflow.FLOW : DCAirflow.NORMAL;

		builder.addSlot(RecipeIngredientRole.INPUT, 42 + hum.getID() * 21, 57).addIngredient(IngredientTypeDC.HUMIDITY, hum).setCustomRenderer(IngredientTypeDC.HUMIDITY, new HumidityRenderer(21, 3));

		builder.addSlot(RecipeIngredientRole.INPUT, 42 + air.getID() * 21, 75).addIngredient(IngredientTypeDC.AIRFLOW, air).setCustomRenderer(IngredientTypeDC.AIRFLOW, new AirflowRenderer(21, 3));
	}

	@Override
	public List<Component> getTooltipStrings(Biome recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		List<TagKey<Biome>> tags = tagList(recipe);
		List<Component> list = Lists.newArrayList();

		if (mouseIsIn(mouseX, mouseY, 20, 92, 41, 107)) {
			StringBuilder string = new StringBuilder();
			if (tags.contains(Tags.Biomes.IS_PLAINS))
				string.append(" PLAINS");
			if (tags.contains(Tags.Biomes.IS_SPARSE))
				string.append(" SPARSE");
			if (tags.contains(BiomeTags.IS_SAVANNA))
				string.append(" SAVANNA");
			if (string.length() > 1) {
				list.add(Component.literal("WHITE COLOR"));
				list.add(Component.literal(string.toString()));
			}

		}

		if (mouseIsIn(mouseX, mouseY, 43, 92, 64, 107)) {
			StringBuilder string = new StringBuilder();
			if (tags.contains(Tags.Biomes.IS_COLD))
				string.append(" COLD");
			if (tags.contains(BiomeTags.IS_HILL))
				string.append(" HILL");
			if (tags.contains(Tags.Biomes.IS_CONIFEROUS))
				string.append(" CONIFEROUS");
			if (tags.contains(BiomeTags.IS_TAIGA))
				string.append(" TAIGA");
			if (string.length() > 1) {
				list.add(Component.literal("BLUE COLOR"));
				list.add(Component.literal(string.toString()));
			}

		}

		if (mouseIsIn(mouseX, mouseY, 66, 92, 85, 107)) {
			StringBuilder string = new StringBuilder();
			if (tags.contains(Tags.Biomes.IS_WATER))
				string.append(" WATER");
			if (tags.contains(Tags.Biomes.IS_SPOOKY))
				string.append(" SPOOKY");
			if (tags.contains(Tags.Biomes.IS_SWAMP))
				string.append(" SWAMP");
			if (tags.contains(BiomeTags.IS_NETHER))
				string.append(" NETHER");
			if (string.length() > 1) {
				list.add(Component.literal("BLACK COLOR"));
				list.add(Component.literal(string.toString()));
			}
		}

		if (mouseIsIn(mouseX, mouseY, 89, 92, 110, 107)) {
			StringBuilder string = new StringBuilder();
			if (tags.contains(Tags.Biomes.IS_SANDY))
				string.append(" SANDY");
			if (tags.contains(Tags.Biomes.IS_DRY))
				string.append(" DRY");
			if (tags.contains(Tags.Biomes.IS_HOT))
				string.append(" HOT");
			if (tags.contains(BiomeTags.IS_MOUNTAIN))
				string.append(" MOUNTAIN");
			if (tags.contains(BiomeTags.IS_BADLANDS))
				string.append(" BADLANDS");
			if (string.length() > 1) {
				list.add(Component.literal("RED COLOR"));
				list.add(Component.literal(string.toString()));
			}
		}

		if (mouseIsIn(mouseX, mouseY, 112, 92, 133, 107)) {
			StringBuilder string = new StringBuilder();
			if (tags.contains(Tags.Biomes.IS_DENSE))
				string.append(" DENSE");
			if (tags.contains(BiomeTags.IS_FOREST))
				string.append(" FOREST");
			if (tags.contains(Tags.Biomes.IS_LUSH))
				string.append(" LUSH");
			if (tags.contains(BiomeTags.IS_JUNGLE))
				string.append(" JUNGLE");
			if (string.length() > 1) {
				list.add(Component.literal("GREEN COLOR"));
				list.add(Component.literal(string.toString()));
			}
		}

		// list.add(Component.translatable("X :" + mouseX + ", Y: " + mouseY));

		return list;
	}

	private boolean mouseIsIn(double x, double y, double xm, double ym, double xx, double yx) {
		return x > xm && x < xx && y > ym && y < yx;
	}

	@Override
	public void draw(Biome recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
		float temp = recipe.getBaseTemperature();
		float tempOffset = ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(DCTimeHelper.staticSeason);
		temp -= tempOffset;
		float rainfall = recipe.getModifiedClimateSettings().downfall();
		List<TagKey<Biome>> tags = tagList(recipe);
		boolean isNether = tags.contains(BiomeTags.IS_NETHER);
		boolean isEnd = tags.contains(BiomeTags.IS_END);
		if (isNether)
			temp += ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(EnumSeason.SCORCHER);
		if (isEnd)
			temp += ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(EnumSeason.ABSOLUTE);

		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;

		Registry<Biome> biomeReg = ClimateCore.proxy.getClientLevel().get().registryAccess().registryOrThrow(Registries.BIOME);
		ResourceLocation key = biomeReg.getKey(recipe);

		MutableComponent name = Component.translatable("biome." + key.getNamespace() + "." + key.getPath());
		if (name != null && !name.getString().isBlank()) {
			graphics.drawString(font, name, 22, 10, 0xFF000000);
		} else {
			Component.literal(key.getPath());
			graphics.drawString(font, name, 22, 10, 0xFF000000);
		}

		String mod_id = key.getNamespace();
		graphics.drawString(font, mod_id, 22, 22, 0xFF000000);

		graphics.drawString(font, temp + " F", 80, 43, 0xFF505050);
		graphics.drawString(font, rainfall + " F", 78, 61, 0xFF505050);

		RenderSystem.setShaderTexture(0, PluginTexDC.BIOME.getLocation());
		RenderSystem.setShader(GameRenderer::getPositionTexShader);

		if (tags.contains(TagDC.BiomeTag.WHITE_BIOME)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 20, 92, 0, 182, 21, 15);
		}
		if (tags.contains(TagDC.BiomeTag.BLUE_BIOME)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 43, 92, 21, 182, 21, 15);
		}
		if (tags.contains(TagDC.BiomeTag.BLACK_BIOME)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 66, 92, 42, 182, 21, 15);
		}
		if (tags.contains(TagDC.BiomeTag.RED_BIOME)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 89, 92, 63, 182, 21, 15);
		}
		if (tags.contains(TagDC.BiomeTag.GREEN_BIOME)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 112, 92, 84, 182, 21, 15);
		}

		if (tags.contains(Tags.Biomes.IS_PLAINS) || tags.contains(Tags.Biomes.IS_SPARSE)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 24, 98, 0, 198, 6, 6);
		}
		if (tags.contains(BiomeTags.IS_SAVANNA)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 31, 98, 6, 198, 6, 6);
		}

		if (tags.contains(Tags.Biomes.IS_COLD) || tags.contains(BiomeTags.IS_HILL)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 47, 98, 12, 198, 6, 6);
		}
		if (tags.contains(Tags.Biomes.IS_CONIFEROUS) || tags.contains(BiomeTags.IS_TAIGA)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 54, 98, 18, 198, 6, 6);
		}

		if (tags.contains(Tags.Biomes.IS_WATER)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 70, 98, 24, 198, 6, 6);
		}
		if (tags.contains(Tags.Biomes.IS_SPOOKY) || tags.contains(Tags.Biomes.IS_SWAMP) || tags.contains(BiomeTags.IS_NETHER)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 77, 98, 30, 198, 6, 6);
		}

		if (tags.contains(Tags.Biomes.IS_SANDY) || tags.contains(Tags.Biomes.IS_DRY) || tags.contains(Tags.Biomes.IS_HOT)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 93, 98, 36, 198, 6, 6);
		}
		if (tags.contains(BiomeTags.IS_MOUNTAIN) || tags.contains(BiomeTags.IS_BADLANDS)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 100, 98, 42, 198, 6, 6);
		}

		if (tags.contains(Tags.Biomes.IS_DENSE) || tags.contains(BiomeTags.IS_FOREST)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 116, 98, 48, 198, 6, 6);
		}
		if (tags.contains(Tags.Biomes.IS_LUSH) || tags.contains(BiomeTags.IS_JUNGLE)) {
			drawTexturedModalRect(graphics.pose().last().pose(), 123, 98, 54, 198, 6, 6);
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

	private List<TagKey<Biome>> tagList(Biome biome) {
		List<TagKey<Biome>> list = ClimateCore.proxy.getBiomeTags(null, biome);
		return list;
	}

}
