package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.BottleModel_Beer;
import defeatedcrow.hac.food.material.entity.BottleBeerItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderBottleBeer<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderBottleBeer(Context ctx) {
		super(ctx);
		this.model = new BottleModel_Beer<>(ctx.bakeLayer(BottleBeerItem.BEER.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return BottleBeerItem.BEER.getTextureLocation();
	}

}
