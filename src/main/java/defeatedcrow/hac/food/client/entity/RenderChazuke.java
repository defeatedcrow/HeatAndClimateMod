package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.ChazukeModel;
import defeatedcrow.hac.food.material.entity.ChazukeItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderChazuke<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	public RenderChazuke(Context ctx) {
		super(ctx);
		this.model = new ChazukeModel<>(ctx.bakeLayer(ChazukeItem.UME.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return ChazukeItem.UME.getTextureLocation();
	}

}
