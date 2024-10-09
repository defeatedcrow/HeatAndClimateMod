package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.TempuraPrawnModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.TempuraPrawnItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderTempuraPrawn<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderTempuraPrawn(Context ctx) {
		super(ctx);
		this.model = new TempuraPrawnModel<>(ctx.bakeLayer(TempuraPrawnItem.PRAWN.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return TempuraPrawnItem.PRAWN.getTextureLocation();
	}

}
