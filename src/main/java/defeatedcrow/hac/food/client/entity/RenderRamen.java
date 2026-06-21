package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.RamenModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.RamenItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderRamen<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected RamenModel<FoodEntityBase> model;

	public RenderRamen(Context ctx) {
		super(ctx);
		this.model = new RamenModel<>(ctx.bakeLayer(RamenItem.SHOYU.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return RamenItem.SHOYU.getTextureLocation();
	}

}
