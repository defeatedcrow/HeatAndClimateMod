package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.DeepfryTonkatsuModel;
import defeatedcrow.hac.food.material.entity.DeepfryTonkatsuItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderFryTonkatsu<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderFryTonkatsu(Context ctx) {
		super(ctx);
		this.model = new DeepfryTonkatsuModel<>(ctx.bakeLayer(DeepfryTonkatsuItem.TONKATSU.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return DeepfryTonkatsuItem.TONKATSU.getTextureLocation();
	}

}
