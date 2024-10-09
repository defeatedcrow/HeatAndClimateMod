package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.DeepfryKaraageModel;
import defeatedcrow.hac.food.material.entity.DeepfryKaraageItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderFryKaraage<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderFryKaraage(Context ctx) {
		super(ctx);
		this.model = new DeepfryKaraageModel<>(ctx.bakeLayer(DeepfryKaraageItem.KARAAGE.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return DeepfryKaraageItem.KARAAGE.getTextureLocation();
	}

}
