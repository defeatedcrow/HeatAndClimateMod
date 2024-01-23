package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.DrinkCupModel;
import defeatedcrow.hac.food.material.entity.DrinkCupItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderDrinkCup<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderDrinkCup(Context ctx) {
		super(ctx);
		this.model = new DrinkCupModel<>(ctx.bakeLayer(DrinkCupItem.TEA_BLACK.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return DrinkCupItem.TEA_BLACK.getTextureLocation();
	}

}
