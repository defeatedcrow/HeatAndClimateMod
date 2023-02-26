package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.BreadSausageModel;
import defeatedcrow.hac.food.material.entity.BreadSausageItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderBreadSausage<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderBreadSausage(Context ctx) {
		super(ctx);
		this.model = new BreadSausageModel<>(ctx.bakeLayer(BreadSausageItem.BREAD_SAUSAGE_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return BreadSausageItem.BREAD_SAUSAGE_RAW.getTextureLocation();
	}

}
