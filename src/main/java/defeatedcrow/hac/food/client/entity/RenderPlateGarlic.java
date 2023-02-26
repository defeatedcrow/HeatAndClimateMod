package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PlateGarlicModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PlateGarlicItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPlateGarlic<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPlateGarlic(Context ctx) {
		super(ctx);
		this.model = new PlateGarlicModel<>(ctx.bakeLayer(PlateGarlicItem.GARLIC_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PlateGarlicItem.GARLIC_RAW.getTextureLocation();
	}

}
