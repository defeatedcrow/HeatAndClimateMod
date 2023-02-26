package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.SandwichModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.SandwichItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderSandwich<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderSandwich(Context ctx) {
		super(ctx);
		this.model = new SandwichModel<>(ctx.bakeLayer(SandwichItem.FRUIT.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return SandwichItem.FRUIT.getTextureLocation();
	}

}
