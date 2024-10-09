package defeatedcrow.hac.food.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.CakeModel;
import defeatedcrow.hac.food.material.entity.CakeItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;

public class RenderCake<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderCake(Context ctx) {
		super(ctx);
		this.model = new CakeModel<>(ctx.bakeLayer(CakeItem.BUTTER.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return CakeItem.BUTTER.getTextureLocation();
	}

	@Override
	public void render(FoodEntityBase entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity != null) {
			Item item = entity.getItem().getItem();
			if (item instanceof IEntityItem && ((IEntityItem) item).getRenderData(item) != null) {
				EntityRenderData data = ((IEntityItem) item).getRenderData(item);
				if (data == CakeItem.GRAPE || data == CakeItem.ROSE) {
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
					((CakeModel<Entity>) this.model).renderJelly1(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
					poseStack.popPose();

					poseStack.pushPose();
					poseStack.translate(0F, f2, 0F);
					poseStack.mulPose(Vector3f.YP.rotationDegrees(360.0F - yaw));
					poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
					poseStack.scale(f1, f1, f1);
					model.setupAnim(entity, 360.0F - yaw, partialTicks, packedLight, f1, f2);
					VertexConsumer vertex2 = buffer.getBuffer(RenderType.entityTranslucent(tex));
					((CakeModel<Entity>) this.model).renderJelly2(poseStack, vertex2, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.75F);
					poseStack.popPose();

				} else {
					super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
				}

			}
		}
	}

}
