package defeatedcrow.hac.magic.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.magic.client.model.CrowTurretModel;
import defeatedcrow.hac.magic.material.entity.CrowTurretEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RenderCrowTurret extends EntityRenderer<CrowTurretEntity> {

	protected CrowTurretModel model;

	public RenderCrowTurret(Context ctx) {
		super(ctx);
		this.model = new CrowTurretModel(ctx.bakeLayer(TEX.getLayerLocation()));
	}

	@Override
	public void render(CrowTurretEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity != null) {
			float headY = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
			float headP = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
			float bodyY = entity.getBodyPose().getY();
			VertexConsumer vertex = buffer.getBuffer(model.renderType(TEX.getTextureLocation()));

			poseStack.pushPose();
			poseStack.translate(0F, 0F, 0F);
			poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - bodyY));
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.scale(1F, 1F, 1F);
			this.model.renderBody(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.popPose();

			poseStack.pushPose();
			poseStack.translate(0F, 0F, 0F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.scale(1F, 1F, 1F);
			this.model.setupAnim(entity, 1.0F, 1.0F, partialTicks, headY, headP);
			this.model.renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.popPose();
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(CrowTurretEntity entity) {
		return TEX.getTextureLocation();
	}

	public static final EntityRenderData TEX = new EntityRenderData("magic/crow_turret", 1.0F, 0F);

}
