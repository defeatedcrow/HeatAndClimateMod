package defeatedcrow.hac.food.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.food.client.model.SandwichModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.SandwichItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderSandwich<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderSandwich(Context ctx) {
		super(ctx);
		this.model = new SandwichModel<>(ctx.bakeLayer(SandwichItem.FRUIT.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return SandwichItem.FRUIT.getTextureLocation();
	}

	@Override
	public void render(FoodEntityBase entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		super.render(entity, yaw + 180F, partialTicks, poseStack, buffer, packedLight);
	}

}
