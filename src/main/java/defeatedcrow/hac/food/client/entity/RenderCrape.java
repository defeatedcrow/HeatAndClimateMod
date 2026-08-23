package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.CrapeModel;
import defeatedcrow.hac.food.material.entity.CrapeItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderCrape<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	public RenderCrape(Context ctx) {
		super(ctx);
		model = new CrapeModel<>(ctx.bakeLayer(CrapeItem.SUGAR.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return CrapeItem.SUGAR.getTextureLocation();
	}

}
