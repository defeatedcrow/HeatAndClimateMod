package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.FriedEggModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.FriedEggItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderFriedEgg<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	public RenderFriedEgg(Context ctx) {
		super(ctx);
		this.model = new FriedEggModel<>(ctx.bakeLayer(FriedEggItem.BACON_EGG.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return FriedEggItem.BACON_EGG.getTextureLocation();
	}

}
