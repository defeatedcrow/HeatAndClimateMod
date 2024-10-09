package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PlatePumpkinModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PlatePumpkinItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPlatePumpkin<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPlatePumpkin(Context ctx) {
		super(ctx);
		this.model = new PlatePumpkinModel<>(ctx.bakeLayer(PlatePumpkinItem.PUMPKIN_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PlatePumpkinItem.PUMPKIN_RAW.getTextureLocation();
	}

}
