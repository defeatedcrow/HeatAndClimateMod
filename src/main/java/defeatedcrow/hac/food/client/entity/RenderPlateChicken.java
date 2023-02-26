package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PlateChickenModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PlateChickenItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPlateChicken<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPlateChicken(Context ctx) {
		super(ctx);
		this.model = new PlateChickenModel<>(ctx.bakeLayer(PlateChickenItem.CHICKEN_BIG_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PlateChickenItem.CHICKEN_BIG_RAW.getTextureLocation();
	}

}
