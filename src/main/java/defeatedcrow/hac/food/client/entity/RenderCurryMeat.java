package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.LargeBowlModel_Meat;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Meat;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderCurryMeat extends RenderCurryBase<FoodEntityBase> {

	public RenderCurryMeat(Context ctx) {
		super(ctx);
		this.curryModel = new LargeBowlModel_Meat<>(ctx.bakeLayer(CurryItem_Meat.LARGE_BOWL_LAMB.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return CurryItem_Meat.LARGE_BOWL_LAMB.getTextureLocation();
	}

}
