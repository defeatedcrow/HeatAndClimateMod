package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.ZarusobaModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.ZarusobaItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderZarusoba<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	public RenderZarusoba(Context ctx) {
		super(ctx);
		this.model = new ZarusobaModel<>(ctx.bakeLayer(ZarusobaItem.SIMPLE.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return ZarusobaItem.SIMPLE.getTextureLocation();
	}

}
