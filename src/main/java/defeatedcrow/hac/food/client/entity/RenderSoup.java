package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.BowlSoupModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.potfoods.SoupItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderSoup<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected BowlSoupModel<FoodEntityBase> model;

	public RenderSoup(Context ctx) {
		super(ctx);
		this.model = new BowlSoupModel<>(ctx.bakeLayer(SoupItem.SOUP_CREAM_POTATO.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return SoupItem.SOUP_CREAM_POTATO.getTextureLocation();
	}

}
