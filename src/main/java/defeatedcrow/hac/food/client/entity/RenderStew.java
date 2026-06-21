package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.BowlStewModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.potfoods.PorridgeItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderStew<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected BowlStewModel<FoodEntityBase> model;

	public RenderStew(Context ctx) {
		super(ctx);
		this.model = new BowlStewModel<>(ctx.bakeLayer(PorridgeItem.PORRIDGE.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PorridgeItem.PORRIDGE.getTextureLocation();
	}

}
