package defeatedcrow.hac.food.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.SoupModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.potfoods.PorridgeItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderSoup<T extends FoodEntityBase> extends EntityRenderer<T> {

	protected EntityModel<T> model;
	protected EntityModel<T> outer = null;

	public RenderSoup(Context ctx) {
		super(ctx);
		this.model = new SoupModel<>(ctx.bakeLayer(PorridgeItem.PORRIDGE.getLayerLocation()));
		this.outer = new SoupModel<>(ctx.bakeLayer(PorridgeItem.PORRIDGE.getOuterLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return PorridgeItem.PORRIDGE.getTextureLocation();
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

				if (data.hasOuter()) {
					poseStack.pushPose();
					poseStack.translate(0F, f2, 0F);
					poseStack.mulPose(Vector3f.YP.rotationDegrees(360.0F - yaw));
					poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
					poseStack.scale(f1, f1, f1);
					outer.setupAnim(entity, 360.0F - yaw, partialTicks, packedLight, f1, f2);
					VertexConsumer vertex2 = buffer.getBuffer(RenderType.entityTranslucent(tex));
					this.outer.renderToBuffer(poseStack, vertex2, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
					poseStack.popPose();
				}
			}
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}

}
