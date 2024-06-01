package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.StickCornModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.StickCornItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderStickCorn<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderStickCorn(Context ctx) {
		super(ctx);
		this.model = new StickCornModel<>(ctx.bakeLayer(StickCornItem.CORN_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return StickCornItem.CORN_RAW.getTextureLocation();
	}

}
