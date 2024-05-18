package defeatedcrow.hac.food.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.client.model.LargeBowlModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderCurry<T extends FoodEntityBase> extends EntityRenderer<T> {

	protected LargeBowlModel model;

	public RenderCurry(Context ctx) {
		super(ctx);
		this.model = new LargeBowlModel<>(ctx.bakeLayer(BOWL_METAL.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return BOWL_METAL.getTextureLocation();
	}

	@Override
	public void render(T entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity != null) {
			Item item = entity.getItem().getItem();
			if (item instanceof IEntityItem && ((IEntityItem) item).getRenderData(item) != null) {
				EntityRenderData data = ((IEntityItem) item).getRenderData(item);
				ResourceLocation tex = entity.getItem().is(TagDC.ItemTag.HAC_CURRY) ? BOWL_METAL.getTextureLocation() : BOWL_WHITE.getTextureLocation();
				float f1 = data.getModelScale();
				float f2 = data.getAdjustY();

				poseStack.pushPose();
				poseStack.translate(0F, f2, 0F);
				poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - yaw));
				poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
				poseStack.scale(f1, f1, f1);
				model.setupAnim(entity, 360.0F - yaw, partialTicks, packedLight, f1, f2);
				VertexConsumer vertex = buffer.getBuffer(model.renderType(tex));
				this.model.renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				poseStack.popPose();

			}
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}

	public static final EntityRenderData BOWL_METAL = new EntityRenderData("food/large_bowl_metal", 0.75F, 0F);

	public static final EntityRenderData BOWL_WHITE = new EntityRenderData("food/large_bowl_white", 0.75F, 0F);

}
