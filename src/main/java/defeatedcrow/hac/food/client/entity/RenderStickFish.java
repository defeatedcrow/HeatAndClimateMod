package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.StickFishModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.StickFishItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderStickFish<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderStickFish(Context ctx) {
		super(ctx);
		this.model = new StickFishModel<>(ctx.bakeLayer(StickFishItem.FISH_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return StickFishItem.FISH_RAW.getTextureLocation();
	}

}
