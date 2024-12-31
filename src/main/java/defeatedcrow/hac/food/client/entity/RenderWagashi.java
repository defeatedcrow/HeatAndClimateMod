package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.WagashiModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.WagashiItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderWagashi<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderWagashi(Context ctx) {
		super(ctx);
		this.model = new WagashiModel<>(ctx.bakeLayer(WagashiItem.BOTA.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return WagashiItem.BOTA.getTextureLocation();
	}

}
