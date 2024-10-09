package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.BreadFlatModel;
import defeatedcrow.hac.food.material.entity.BreadFlatItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderBreadFlat<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderBreadFlat(Context ctx) {
		super(ctx);
		this.model = new BreadFlatModel<>(ctx.bakeLayer(BreadFlatItem.BREAD_FLAT_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return BreadFlatItem.BREAD_FLAT_RAW.getTextureLocation();
	}

}
