package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PlateMeatModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PlateMeatItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPlateMeat<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPlateMeat(Context ctx) {
		super(ctx);
		this.model = new PlateMeatModel<>(ctx.bakeLayer(PlateMeatItem.MEAT_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PlateMeatItem.MEAT_RAW.getTextureLocation();
	}

}
