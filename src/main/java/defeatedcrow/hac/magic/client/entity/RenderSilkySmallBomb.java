package defeatedcrow.hac.magic.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.magic.client.model.SilkyBombModel;
import defeatedcrow.hac.magic.material.entity.SilkySmallBombEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderSilkySmallBomb extends EntityRenderer<SilkySmallBombEntity> {

	protected SilkyBombModel model;

	public RenderSilkySmallBomb(Context ctx) {
		super(ctx);
		this.model = new SilkyBombModel(ctx.bakeLayer(TEX.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(SilkySmallBombEntity entity) {
		return TEX.getTextureLocation();
	}

	@Override
	public void render(SilkySmallBombEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity != null) {
			poseStack.pushPose();
			poseStack.translate(0F, 0F, 0F);
			poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - yaw));
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			float i = 0.5F + entity.getSize() * 0.25F;
			int f = entity.getCount();
			float scale = 1.0F;
			if (f > 0) {
				scale += (float) (Math.cos(f / 5D) * 0.1D);
			}

			poseStack.scale(i * scale, i * scale, i * scale);
			VertexConsumer vertex = buffer.getBuffer(model.renderType(TEX.getTextureLocation()));
			this.model.renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.popPose();
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}

	public static final EntityRenderData TEX = new EntityRenderData("magic/silky_bomb", 1.0F, 0F);

}
