package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PastaModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PastaItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPasta<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPasta(Context ctx) {
		super(ctx);
		this.model = new PastaModel<>(ctx.bakeLayer(PastaItem.OIL.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PastaItem.OIL.getTextureLocation();
	}

}
