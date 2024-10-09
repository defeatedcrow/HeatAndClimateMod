package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PlateStuffedVegiModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PlateStuffedVegiItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPlateStuffedVegi<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPlateStuffedVegi(Context ctx) {
		super(ctx);
		this.model = new PlateStuffedVegiModel<>(ctx.bakeLayer(PlateStuffedVegiItem.STUFFED_BELL_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PlateStuffedVegiItem.STUFFED_BELL_RAW.getTextureLocation();
	}

}
