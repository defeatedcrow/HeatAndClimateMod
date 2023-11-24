package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.BottleModel_Wine;
import defeatedcrow.hac.food.material.entity.BottleWineItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderBottleWine<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderBottleWine(Context ctx) {
		super(ctx);
		this.model = new BottleModel_Wine<>(ctx.bakeLayer(BottleWineItem.WINE.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return BottleWineItem.WINE.getTextureLocation();
	}

}
