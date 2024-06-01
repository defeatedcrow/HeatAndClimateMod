package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.StickVegiModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.StickVegiItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderStickVegi<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderStickVegi(Context ctx) {
		super(ctx);
		this.model = new StickVegiModel<>(ctx.bakeLayer(StickVegiItem.VEGI_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return StickVegiItem.VEGI_RAW.getTextureLocation();
	}

}
