package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.CakeModel;
import defeatedcrow.hac.food.material.entity.CakeItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderCake<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderCake(Context ctx) {
		super(ctx);
		this.model = new CakeModel<>(ctx.bakeLayer(CakeItem.BUTTER.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return CakeItem.BUTTER.getTextureLocation();
	}

}
