package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.TempuraFishModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.TempuraFishItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderTempuraFish<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderTempuraFish(Context ctx) {
		super(ctx);
		this.model = new TempuraFishModel<>(ctx.bakeLayer(TempuraFishItem.FISH.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return TempuraFishItem.FISH.getTextureLocation();
	}

}
