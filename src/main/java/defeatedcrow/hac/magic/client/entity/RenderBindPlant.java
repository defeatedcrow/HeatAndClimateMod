package defeatedcrow.hac.magic.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.client.entity.model.ChairBindModel;
import defeatedcrow.hac.core.material.entity.ChairEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderBindPlant extends EntityRenderer<ChairEntity> {

	protected EntityModel<ChairEntity> model;

	public RenderBindPlant(Context ctx) {
		super(ctx);
		this.model = new ChairBindModel(ctx.bakeLayer(PLANT.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(ChairEntity entity) {
		return PLANT.getTextureLocation();
	}

	@Override
	public void render(ChairEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity != null) {
			int count = entity.getAge() & 3;
			String texName = "dcs_climate:textures/entity/magic/chair_bind_" + count + ".png";
			ResourceLocation tex = new ResourceLocation(texName);

			poseStack.pushPose();
			poseStack.translate(0F, 0.0F, 0F);
			poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - yaw));
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.scale(1.5F, 1.5F, 1.5F);
			model.setupAnim(entity, 180.0F - yaw, partialTicks, packedLight, partialTicks, 0F);
			VertexConsumer vertex = buffer.getBuffer(model.renderType(tex));
			this.model.renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.popPose();
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}

	public static final EntityRenderData PLANT = new EntityRenderData("magic/chair_bind_0", 1.0F, 0F);

}
