package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.TempuraIkatenModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.TempuraIkatenItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderTempuraIkaten<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderTempuraIkaten(Context ctx) {
		super(ctx);
		this.model = new TempuraIkatenModel<>(ctx.bakeLayer(TempuraIkatenItem.IKATEN.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return TempuraIkatenItem.IKATEN.getTextureLocation();
	}

}
