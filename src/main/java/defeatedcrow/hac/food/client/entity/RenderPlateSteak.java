package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PlateSteakModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PlateBeefItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPlateSteak<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPlateSteak(Context ctx) {
		super(ctx);
		this.model = new PlateSteakModel<>(ctx.bakeLayer(PlateBeefItem.BEEF_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PlateBeefItem.BEEF_RAW.getTextureLocation();
	}

}
