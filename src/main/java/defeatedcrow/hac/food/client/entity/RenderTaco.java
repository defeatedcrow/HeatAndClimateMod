package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.TacoModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.TacoItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderTaco<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	public RenderTaco(Context ctx) {
		super(ctx);
		this.model = new TacoModel<>(ctx.bakeLayer(TacoItem.TACO_MEAT.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return TacoItem.TACO_MEAT.getTextureLocation();
	}

}
