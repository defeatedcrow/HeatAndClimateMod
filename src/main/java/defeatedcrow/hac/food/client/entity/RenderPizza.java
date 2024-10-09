package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PizzaModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PizzaItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPizza<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPizza(Context ctx) {
		super(ctx);
		this.model = new PizzaModel<>(ctx.bakeLayer(PizzaItem.PIZZA_TOMATO_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PizzaItem.PIZZA_TOMATO_RAW.getTextureLocation();
	}

}
