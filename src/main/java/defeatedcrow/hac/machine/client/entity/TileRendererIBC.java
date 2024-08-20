package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.block.transport.IBCTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

@OnlyIn(Dist.CLIENT)
public class TileRendererIBC implements BlockEntityRenderer<IBCTile> {

	protected IBCModel model;
	private BlockRenderDispatcher renderer;

	public TileRendererIBC(BlockEntityRendererProvider.Context ctx) {
		this.model = new IBCModel(ctx.bakeLayer(IBCTile.NORMAL.getLayerLocation()));
		renderer = ctx.getBlockRenderDispatcher();
	}

	@Override
	public void render(IBCTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			EntityRenderData data = tile.getRenderData(block);
			ResourceLocation tex = IBCTile.NORMAL.getTextureLocation();
			ResourceLocation tex2 = IBCTile.INNER.getTextureLocation();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING);
			float f1 = data.getModelScale();
			float f2 = data.getAdjustY();

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F + f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
			poseStack.scale(f1, f1, f1);
			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.popPose();

			if (!tile.tank.getFluid().isEmpty()) {
				FluidStack copy = tile.tank.getFluid().copy();
				float h = copy.getAmount() * 0.9F / tile.tank.getCapacity();
				renderFluid(poseStack, buffer, copy, packedLight, 0.5F, 0.05F, 0.5F, 0.45F, h);
			}

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F + f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
			poseStack.scale(f1, f1, f1);
			this.model.renderInner(poseStack, buffer.getBuffer(RenderType.entityTranslucent(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
			poseStack.popPose();

		}
	}

	public static void renderFluid(PoseStack pose, MultiBufferSource buffer, FluidStack fluid, int light, float x, float y, float z, float w, float h) {
		IClientFluidTypeExtensions ext = IClientFluidTypeExtensions.of(fluid.getFluid().getFluidType());
		ResourceLocation res = ext.getStillTexture(fluid);
		TextureAtlasSprite tex = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(res);
		int color = ext.getTintColor(fluid);
		float[] colors = getColor(color);

		pose.pushPose();
		pose.translate(x, y, z);
		Matrix4f m4f = pose.last().pose();
		Matrix3f m3f = pose.last().normal();
		VertexConsumer vertex = buffer.getBuffer(RenderType.translucent());
		RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);

		drawQuad(m4f, m3f, vertex, light, colors, -w, 0, -w, w, h, w, tex);
		pose.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		drawQuad(m4f, m3f, vertex, light, colors, -w, 0, -w, w, h, w, tex);
		pose.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		drawQuad(m4f, m3f, vertex, light, colors, -w, 0, -w, w, h, w, tex);
		pose.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		drawQuad(m4f, m3f, vertex, light, colors, -w, 0, -w, w, h, w, tex);
		pose.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		drawQuad(m4f, m3f, vertex, light, colors, 0, -w, -w, h, w, w, tex);

		pose.popPose();
	}

	public static void drawQuad(Matrix4f m4f, Matrix3f m3f, VertexConsumer vertex, int light, float[] colors, float x1, float y1, float z1, float x2, float y2, float z2, TextureAtlasSprite sprite) {
		float uMin = sprite.getU0();
		float uMax = sprite.getU1();
		float vMin = sprite.getV0();
		float vMax = sprite.getV1();

		// e
		vertex.vertex(m4f, x2, y2, z1).color(colors[0], colors[1], colors[2], colors[3]).uv(uMin, vMax).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
		vertex.vertex(m4f, x2, y2, z2).color(colors[0], colors[1], colors[2], colors[3]).uv(uMax, vMax).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
		vertex.vertex(m4f, x2, y1, z2).color(colors[0], colors[1], colors[2], colors[3]).uv(uMax, vMin).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
		vertex.vertex(m4f, x2, y1, z1).color(colors[0], colors[1], colors[2], colors[3]).uv(uMin, vMin).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
	}

	public static float[] getColor(int color) {
		float red = (color >> 16 & 0xFF) / 255.0F;
		float green = (color >> 8 & 0xFF) / 255.0F;
		float blue = (color & 0xFF) / 255.0F;
		float alpha = ((color >> 24) & 0xFF) / 255F;
		return new float[] { red, green, blue, alpha };
	}

}
