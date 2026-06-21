package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.DrinkDemitasseModel;
import defeatedcrow.hac.food.material.entity.DrinkDemitasseItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderDrinkDemitasse<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	public RenderDrinkDemitasse(Context ctx) {
		super(ctx);
		this.model = new DrinkDemitasseModel<>(ctx.bakeLayer(DrinkDemitasseItem.COFFEE.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return DrinkDemitasseItem.COFFEE.getTextureLocation();
	}

}
