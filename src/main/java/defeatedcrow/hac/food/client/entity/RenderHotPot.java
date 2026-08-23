package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.HotPotModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.potfoods.HotPotItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderHotPot<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	public RenderHotPot(Context ctx) {
		super(ctx);
		model = new HotPotModel<>(ctx.bakeLayer(HotPotItem.TOFU.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return HotPotItem.TOFU.getTextureLocation();
	}

}
