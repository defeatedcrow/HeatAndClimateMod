package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.SweetPotatoModel;
import defeatedcrow.hac.food.material.entity.CookedSweetpotatoItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderSweetpotato<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderSweetpotato(Context ctx) {
		super(ctx);
		this.model = new SweetPotatoModel<>(ctx.bakeLayer(CookedSweetpotatoItem.RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return CookedSweetpotatoItem.RAW.getTextureLocation();
	}

}
