package defeatedcrow.hac.magic.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.magic.client.model.LightCauldronModel;
import defeatedcrow.hac.magic.material.entity.OwnableMagicEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderLightCauldron extends EntityRenderer<OwnableMagicEntity> {

	protected LightCauldronModel model;

	public RenderLightCauldron(Context ctx) {
		super(ctx);
		this.model = new LightCauldronModel(ctx.bakeLayer(TEX.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(OwnableMagicEntity entity) {
		return TEX.getTextureLocation();
	}

	@Override
	public void render(OwnableMagicEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity != null) {
			poseStack.pushPose();
			poseStack.translate(0F, 0F, 0F);
			poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - yaw));
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.scale(1F, 1F, 1F);
			VertexConsumer vertex = buffer.getBuffer(model.renderType(TEX.getTextureLocation()));
			this.model.renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.popPose();
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}

	public static final EntityRenderData TEX = new EntityRenderData("magic/light_cauldron", 1.0F, 0F);

}
