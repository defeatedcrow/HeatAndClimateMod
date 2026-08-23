package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.FriedEggModel;
import defeatedcrow.hac.food.client.model.GrilledCrabModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.FriedEggItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderFriedEgg<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected GrilledCrabModel<FoodEntityBase> crabModel;

	public RenderFriedEgg(Context ctx) {
		super(ctx);
		this.model = new FriedEggModel<>(ctx.bakeLayer(FriedEggItem.BACON_EGG.getLayerLocation()));
		this.crabModel = new GrilledCrabModel<>(ctx.bakeLayer(FriedEggItem.GRILLED_CRAB.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return FriedEggItem.BACON_EGG.getTextureLocation();
	}

	@Override
	public EntityModel<FoodEntityBase> getModel(FoodEntityBase entity, IEntityItem item) {
		FriedEggItem.ModelType type = FriedEggItem.ModelType.getType((Item) item);
		if (type == FriedEggItem.ModelType.CRAB) {
			return crabModel;
		} else {
			return model;
		}
	}

}
