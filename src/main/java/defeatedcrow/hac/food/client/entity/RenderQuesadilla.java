package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.QuesadillaModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.QuesadillaItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderQuesadilla<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	public RenderQuesadilla(Context ctx) {
		super(ctx);
		this.model = new QuesadillaModel<>(ctx.bakeLayer(QuesadillaItem.QUESADILLA.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return QuesadillaItem.QUESADILLA.getTextureLocation();
	}

}
