package defeatedcrow.hac.food.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.ChazukeModel;
import defeatedcrow.hac.food.material.entity.ChazukeItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderChazuke<T extends FoodEntityBase> extends EntityRenderer<T> {

	protected ChazukeModel model;

	public RenderChazuke(Context ctx) {
		super(ctx);
		this.model = new ChazukeModel<>(ctx.bakeLayer(ChazukeItem.UME.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return ChazukeItem.UME.getTextureLocation();
	}

	@Override
	public void render(T entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity != null) {
			Item item = entity.getItem().getItem();
			if (item instanceof IEntityItem && ((IEntityItem) item).getRenderData(item) != null) {
				EntityRenderData data = ((IEntityItem) item).getRenderData(item);
				ResourceLocation tex = data.getTextureLocation();
				float f1 = data.getModelScale();
				float f2 = data.getAdjustY();

				poseStack.pushPose();
				poseStack.translate(0F, f2, 0F);
				poseStack.mulPose(Vector3f.YP.rotationDegrees(360.0F - yaw));
				poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
				poseStack.scale(f1, f1, f1);
				model.setupAnim(entity, 360.0F - yaw, partialTicks, packedLight, f1, f2);
				VertexConsumer vertex = buffer.getBuffer(model.renderType(tex));
				this.model.renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				poseStack.popPose();

				poseStack.pushPose();
				poseStack.translate(0F, f2, 0F);
				poseStack.mulPose(Vector3f.YP.rotationDegrees(360.0F - yaw));
				poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
				poseStack.scale(f1, f1, f1);
				model.setupAnim(entity, 360.0F - yaw, partialTicks, packedLight, f1, f2);
				VertexConsumer vertex2 = buffer.getBuffer(RenderType.entityTranslucent(tex));
				this.model.renderSoup(poseStack, vertex2, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				poseStack.popPose();
			}
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}

}
