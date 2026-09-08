package defeatedcrow.hac.core.client;

import org.joml.Matrix4f;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class WRRHelper {

	public static void blitSingleIcon(GuiGraphics render, ResourceLocation tex, int x, int y, int z, int texWid, int texH, float um, float vm) {
		innerBlit(render, tex, x, x + texWid, y, y + texH, z, 0F, 1F, 0F, 1F, 1F, 1F, 1F, 1F);
	}

	public static void blitSprite(GuiGraphics render, ResourceLocation tex, int x, int y, int z, int texWid, int texH, float um, float vm) {
		innerBlit(render, tex, x, x + texWid, y, y + texH, z, (um + 0.0F) / 256F, (um + texWid) / 256F, (vm + 0.0F) / 256F, (vm + texH) / 256F, 1F, 1F, 1F, 1F);
	}

	public static void blitSprite(GuiGraphics render, ResourceLocation tex, int x1, int y1, int z, int texWid, int texH, float um, float vm, int width, int height) {
		innerBlit(render, tex, x1, x1 + texWid, y1, y1 + texH, z, (um + 0.0F) / width, (um + texWid) / width, (vm + 0.0F) / height, (vm + texH) / height, 1F, 1F, 1F, 1F);
	}

	public static void innerBlit(GuiGraphics render, ResourceLocation tex, int x1, int x2, int y1, int y2, int z, float uMin, float uMax, float vMin, float vMax, float red, float green, float blue, float alpha) {
		RenderSystem.setShaderTexture(0, tex);
		RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
		RenderSystem.enableBlend();
		Matrix4f matrix4f = render.pose().last().pose();
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
		bufferbuilder.vertex(matrix4f, x1, y1, z).color(red, green, blue, alpha).uv(uMin, vMin).endVertex();
		bufferbuilder.vertex(matrix4f, x1, y2, z).color(red, green, blue, alpha).uv(uMin, vMax).endVertex();
		bufferbuilder.vertex(matrix4f, x2, y2, z).color(red, green, blue, alpha).uv(uMax, vMax).endVertex();
		bufferbuilder.vertex(matrix4f, x2, y1, z).color(red, green, blue, alpha).uv(uMax, vMin).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());
		RenderSystem.disableBlend();
	}

}
