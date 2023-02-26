package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.StickBeefModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.StickBeefItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderStickBeef<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderStickBeef(Context ctx) {
		super(ctx);
		this.model = new StickBeefModel<>(ctx.bakeLayer(StickBeefItem.BEEF_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return StickBeefItem.BEEF_RAW.getTextureLocation();
	}

}
