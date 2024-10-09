package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.TempuraVegiModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.TempuraVegiItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderTempuraVegi<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderTempuraVegi(Context ctx) {
		super(ctx);
		this.model = new TempuraVegiModel<>(ctx.bakeLayer(TempuraVegiItem.VEGI.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return TempuraVegiItem.VEGI.getTextureLocation();
	}

}
