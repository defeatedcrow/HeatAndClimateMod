package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.DonutJellyModel;
import defeatedcrow.hac.food.client.model.DonutModel;
import defeatedcrow.hac.food.material.entity.DonutItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderDonut<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected DonutModel<FoodEntityBase> mainModel;
	protected DonutJellyModel<FoodEntityBase> jellyModel;

	public RenderDonut(Context ctx) {
		super(ctx);
		this.mainModel = new DonutModel<>(ctx.bakeLayer(DonutItem.SUGAR.getLayerLocation()));
		this.jellyModel = new DonutJellyModel<>(ctx.bakeLayer(DonutItem.JELLY.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return DonutItem.SUGAR.getTextureLocation();
	}

	@Override
	public EntityModel<FoodEntityBase> getModel(FoodEntityBase entity, IEntityItem item) {
		DonutItem.ModelType type = DonutItem.ModelType.getType((Item) item);
		if (type == DonutItem.ModelType.JELLY) {
			return jellyModel;
		} else {
			return mainModel;
		}
	}

}
