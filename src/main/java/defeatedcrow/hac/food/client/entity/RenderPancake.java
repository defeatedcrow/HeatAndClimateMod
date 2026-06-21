package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PancakeModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PancakeItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPancake<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	public RenderPancake(Context ctx) {
		super(ctx);
		model = new PancakeModel<>(ctx.bakeLayer(PancakeItem.HONEY.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PancakeItem.HONEY.getTextureLocation();
	}

}
