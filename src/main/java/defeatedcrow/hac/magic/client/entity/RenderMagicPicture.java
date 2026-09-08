package defeatedcrow.hac.magic.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.magic.client.model.MagicPictureModel;
import defeatedcrow.hac.magic.material.entity.MagicPictureEntity;
import defeatedcrow.hac.magic.material.item.entity.MagicPictureItem;
import defeatedcrow.hac.magic.material.item.entity.PictureItemWR;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderMagicPicture extends EntityRenderer<MagicPictureEntity> {

	protected MagicPictureModel model;

	public RenderMagicPicture(Context ctx) {
		super(ctx);
		this.model = new MagicPictureModel(ctx.bakeLayer(PictureItemWR.PICTURE.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(MagicPictureEntity entity) {
		return PictureItemWR.PICTURE.getTextureLocation();
	}

	@Override
	public void render(MagicPictureEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity != null) {
			Item item = entity.getItem();
			if (item instanceof MagicPictureItem pic && pic.getRenderData(item) != null) {
				EntityRenderData data = pic.getRenderData(item);
				ResourceLocation tex = data.getTextureLocation();
				float f1 = data.getModelScale();
				float f2 = data.getAdjustY();

				poseStack.pushPose();
				poseStack.translate(0D, f2, 0D);
				poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.scale(f1, f1, f1);
				model.setupAnim(entity, 180.0F - yaw, partialTicks, packedLight, f1, f2);
				VertexConsumer vertex = buffer.getBuffer(model.renderType(tex));
				model.renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				poseStack.popPose();
			}
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}

}
