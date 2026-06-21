package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.MinidishModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.MinidishItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderMinidish<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected MinidishModel<FoodEntityBase> leavesModel;
	protected MinidishModel<FoodEntityBase> umeModel;

	public RenderMinidish(Context ctx) {
		super(ctx);
		this.model = new MinidishModel<>(ctx.bakeLayer(MinidishItem.TSUKEMONO.getLayerLocation()));
		this.leavesModel = new MinidishModel<>(ctx.bakeLayer(MinidishItem.SAUERKRAUT.getLayerLocation()));
		this.umeModel = new MinidishModel<>(ctx.bakeLayer(MinidishItem.UMEBOSHI.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return MinidishItem.TSUKEMONO.getTextureLocation();
	}

	@Override
	public EntityModel<FoodEntityBase> getModel(FoodEntityBase entity, IEntityItem item) {
		MinidishItem.ModelType type = MinidishItem.ModelType.getType((Item) item);
		if (type == MinidishItem.ModelType.LEAVES) {
			return leavesModel;
		} else if (type == MinidishItem.ModelType.UME) {
			return umeModel;
		} else {
			return model;
		}
	}

}
