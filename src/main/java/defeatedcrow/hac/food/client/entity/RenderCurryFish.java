package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.LargeBowlModel_Fish;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Fish;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderCurryFish extends RenderCurryBase<FoodEntityBase> {

	public RenderCurryFish(Context ctx) {
		super(ctx);
		this.curryModel = new LargeBowlModel_Fish<>(ctx.bakeLayer(CurryItem_Fish.CURRY_FISH.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return CurryItem_Fish.CURRY_FISH.getTextureLocation();
	}

}
