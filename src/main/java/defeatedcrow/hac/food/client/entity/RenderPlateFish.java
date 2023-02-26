package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PlateFishModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PlateFishItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPlateFish<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPlateFish(Context ctx) {
		super(ctx);
		this.model = new PlateFishModel<>(ctx.bakeLayer(PlateFishItem.FISH_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PlateFishItem.FISH_RAW.getTextureLocation();
	}

}
