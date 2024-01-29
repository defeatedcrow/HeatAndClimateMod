package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.TartModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.TartItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderTart<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderTart(Context ctx) {
		super(ctx);
		this.model = new TartModel<>(ctx.bakeLayer(TartItem.TART_APPLE_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return TartItem.TART_APPLE_RAW.getTextureLocation();
	}

}
