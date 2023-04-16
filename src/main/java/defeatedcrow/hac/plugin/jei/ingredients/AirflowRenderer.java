package defeatedcrow.hac.plugin.jei.ingredients;

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
import defeatedcrow.hac.core.ClimateCore;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.TooltipFlag;

public class AirflowRenderer implements IIngredientRenderer<DCAirflow> {

	private final int width;
	private final int height;

	public AirflowRenderer() {
		this(16, 16);
	}

	public AirflowRenderer(int w, int h) {
		width = w;
		height = h;
	}

	@Override
	public void render(PoseStack stack, DCAirflow ingredient) {
		RenderSystem.enableBlend();

		RenderSystem.setShaderTexture(0, new ResourceLocation(ClimateCore.MOD_ID, "textures/gui/icon_base.png"));
		Matrix4f matrix = stack.last().pose();
		setGLColorFromInt(ingredient.getColorInt());

		drawTexturedModalRect(matrix, 0, 0, 0, 0, 21, 3);

		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.disableBlend();
	}

	private static void setGLColorFromInt(int color) {
		float red = (color >> 16 & 0xFF) / 255.0F;
		float green = (color >> 8 & 0xFF) / 255.0F;
		float blue = (color & 0xFF) / 255.0F;

		RenderSystem.setShaderColor(red, green, blue, 1F);
	}

	@Override
	public List<Component> getTooltip(DCAirflow ingredient, TooltipFlag tooltipFlag) {
		List<Component> tooltip = Lists.newArrayList();
		tooltip.add(ingredient.localize());
		return tooltip;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	private static void drawTexturedModalRect(Matrix4f mat, int x, int y, int tX, int tY, int wid, int hei) {
		float f = 1F / 16F;
		float f1 = 1F / 16F;
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(mat, x + 0, y + hei, 90.0F).uv((tX + 0) * f, (tY + 16) * f1).endVertex();
		bufferbuilder.vertex(mat, x + wid, y + hei, 90.0F).uv((tX + 16) * f, (tY + 16) * f1).endVertex();
		bufferbuilder.vertex(mat, x + wid, y + 0, 90.0F).uv((tX + 16) * f, (tY + 0) * f1).endVertex();
		bufferbuilder.vertex(mat, x + 0, y + 0, 90.0F).uv((tX + 0) * f, (tY + 0) * f1).endVertex();
		Tesselator.getInstance().end();
	}

}
