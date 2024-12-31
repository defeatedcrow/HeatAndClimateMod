package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.WaBowlModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.potfoods.WaBowlItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderWaBowl<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderWaBowl(Context ctx) {
		super(ctx);
		this.model = new WaBowlModel<>(ctx.bakeLayer(WaBowlItem.MISO_TOFU.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return WaBowlItem.MISO_TOFU.getTextureLocation();
	}

}
