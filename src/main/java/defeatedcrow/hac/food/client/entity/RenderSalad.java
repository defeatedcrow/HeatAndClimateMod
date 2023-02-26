package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.SaladModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.SaladItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderSalad<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderSalad(Context ctx) {
		super(ctx);
		this.model = new SaladModel<>(ctx.bakeLayer(SaladItem.GREEN.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return SaladItem.GREEN.getTextureLocation();
	}

}
