package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.DumplingModel;
import defeatedcrow.hac.food.material.entity.DumplingItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderDumpling<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected DumplingModel<FoodEntityBase> mainModel;

	public RenderDumpling(Context ctx) {
		super(ctx);
		this.mainModel = new DumplingModel<>(ctx.bakeLayer(DumplingItem.TUBERS.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return DumplingItem.TUBERS.getTextureLocation();
	}

	@Override
	public EntityModel<FoodEntityBase> getModel(FoodEntityBase entity, IEntityItem item) {
		return mainModel;
	}

}
