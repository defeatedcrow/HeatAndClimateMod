package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.StickPotatoModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.StickPotatoItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderStickPotato<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	public RenderStickPotato(Context ctx) {
		super(ctx);
		this.model = new StickPotatoModel<>(ctx.bakeLayer(StickPotatoItem.POTATO_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return StickPotatoItem.POTATO_RAW.getTextureLocation();
	}

}
