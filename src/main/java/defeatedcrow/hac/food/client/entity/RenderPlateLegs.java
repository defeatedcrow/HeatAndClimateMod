package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PlateLegsModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PlateLegsItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPlateLegs<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPlateLegs(Context ctx) {
		super(ctx);
		this.model = new PlateLegsModel<>(ctx.bakeLayer(PlateLegsItem.LEGS_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PlateLegsItem.LEGS_RAW.getTextureLocation();
	}

}
