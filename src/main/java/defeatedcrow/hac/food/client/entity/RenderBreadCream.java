package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.BreadCreamModel;
import defeatedcrow.hac.food.material.entity.BreadCreamItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderBreadCream<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderBreadCream(Context ctx) {
		super(ctx);
		this.model = new BreadCreamModel<>(ctx.bakeLayer(BreadCreamItem.BREAD_CREAM_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return BreadCreamItem.BREAD_CREAM_RAW.getTextureLocation();
	}

}
