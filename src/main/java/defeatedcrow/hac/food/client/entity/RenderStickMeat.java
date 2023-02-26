package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.StickChickenModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.StickMeatItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderStickMeat<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderStickMeat(Context ctx) {
		super(ctx);
		this.model = new StickChickenModel<>(ctx.bakeLayer(StickMeatItem.MUTTON_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return StickMeatItem.MUTTON_RAW.getTextureLocation();
	}

}
