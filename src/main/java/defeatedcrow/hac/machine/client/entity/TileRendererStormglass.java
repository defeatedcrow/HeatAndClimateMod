package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.machine.material.block.monitor.StormglassTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererStormglass implements BlockEntityRenderer<StormglassTile> {

	protected StormglassModel model;
	private BlockRenderDispatcher renderer;

	public TileRendererStormglass(BlockEntityRendererProvider.Context ctx) {
		this.model = new StormglassModel(ctx.bakeLayer(TEX.getLayerLocation()));
		renderer = ctx.getBlockRenderDispatcher();
	}

	@Override
	public void render(StormglassTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			ResourceLocation tex = TEX.getTextureLocation();
			float f1 = TEX.getModelScale();
			float f2 = TEX.getAdjustY();
			int i = tile.getCurrentAmount();
			boolean cold = (i & 32) != 0;
			boolean sun = (i & 16) != 0;
			boolean rainy = (i & 8) != 0;
			boolean bottom = (i & 4) != 0;
			boolean snow = (i & 2) != 0;
			boolean storm = (i & 1) != 0;

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F + f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(0F));
			poseStack.scale(f1, f1, f1);
			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			if (bottom)
				this.model.renderBottomCrystal(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
			if (snow)
				this.model.renderSnowyCrystal(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
			if (storm)
				this.model.renderStormyCrystal(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
			poseStack.popPose();

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F + f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(0F));
			poseStack.scale(f1, f1, f1);
			this.model.renderWater(cold, poseStack, buffer.getBuffer(RenderType.entityTranslucent(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
			poseStack.popPose();

			if (sun || rainy) {
				poseStack.pushPose();
				poseStack.translate(0.5F, 0.5F + f2, 0.5F);
				poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
				poseStack.mulPose(Vector3f.YP.rotationDegrees(0F));
				poseStack.scale(f1, f1, f1);
				if (sun)
					this.model.renderSunnyLayer(poseStack, buffer.getBuffer(RenderType.entityTranslucent(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
				if (rainy)
					this.model.renderRainyLayer(poseStack, buffer.getBuffer(RenderType.entityTranslucent(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
				poseStack.popPose();
			}

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F + f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(0F));
			poseStack.scale(f1, f1, f1);

			this.model.renderGlass(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.popPose();

		}
	}

	public static final EntityRenderData TEX = new EntityRenderData("tile/stormglass", 1F, -0.5F);

}
