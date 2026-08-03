package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.LargeBowlModel_Sashimi;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Sashimi;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderCurrySashimi extends RenderCurryBase<FoodEntityBase> {

	public RenderCurrySashimi(Context ctx) {
		super(ctx);
		this.curryModel = new LargeBowlModel_Sashimi<>(ctx.bakeLayer(CurryItem_Sashimi.LARGE_BOWL_CAPRESE.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return CurryItem_Sashimi.LARGE_BOWL_CAPRESE.getTextureLocation();
	}

}
