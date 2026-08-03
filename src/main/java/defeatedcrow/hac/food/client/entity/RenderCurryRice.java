package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.LargeBowlModel_Rice;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Rice;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderCurryRice extends RenderCurryBase<FoodEntityBase> {

	public RenderCurryRice(Context ctx) {
		super(ctx);
		this.curryModel = new LargeBowlModel_Rice<>(ctx.bakeLayer(CurryItem_Rice.CURRY_RICE.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return CurryItem_Rice.CURRY_RICE.getTextureLocation();
	}

}
