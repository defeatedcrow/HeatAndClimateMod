package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.BreadSquareModel;
import defeatedcrow.hac.food.material.entity.BreadSquareItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderBreadSquare<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderBreadSquare(Context ctx) {
		super(ctx);
		this.model = new BreadSquareModel<>(ctx.bakeLayer(BreadSquareItem.BREAD_SQUARE_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return BreadSquareItem.BREAD_SQUARE_RAW.getTextureLocation();
	}

}
