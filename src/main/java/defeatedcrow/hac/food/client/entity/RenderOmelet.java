package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.OmeletModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.OmeletItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderOmelet<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase>{

	public RenderOmelet(Context ctx) {
		super(ctx);
		this.model = new OmeletModel<>(ctx.bakeLayer(OmeletItem.BASIC.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return OmeletItem.BASIC.getTextureLocation();
	}

}
