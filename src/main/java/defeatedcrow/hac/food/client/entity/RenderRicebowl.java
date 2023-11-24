package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.RiceModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.potfoods.RiceBowlItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderRicebowl<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderRicebowl(Context ctx) {
		super(ctx);
		this.model = new RiceModel<>(ctx.bakeLayer(RiceBowlItem.NORMAL.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return RiceBowlItem.NORMAL.getTextureLocation();
	}

}
