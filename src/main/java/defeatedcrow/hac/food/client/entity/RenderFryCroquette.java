package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.DeepfryCroquetteModel;
import defeatedcrow.hac.food.material.entity.DeepfryCroquetteItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderFryCroquette<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderFryCroquette(Context ctx) {
		super(ctx);
		this.model = new DeepfryCroquetteModel<>(ctx.bakeLayer(DeepfryCroquetteItem.POTATO.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return DeepfryCroquetteItem.POTATO.getTextureLocation();
	}

}
