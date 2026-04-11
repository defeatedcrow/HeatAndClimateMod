package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.FriedEggModel;
import defeatedcrow.hac.food.client.model.OmeletModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.OmeletItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderOmelet<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected OmeletModel<FoodEntityBase> omeletModel;
	protected FriedEggModel<FoodEntityBase> eggModel;

	public RenderOmelet(Context ctx) {
		super(ctx);
		omeletModel = new OmeletModel<>(ctx.bakeLayer(OmeletItem.BASIC.getLayerLocation()));
		eggModel = new FriedEggModel<>(ctx.bakeLayer(OmeletItem.FRIED_EGG.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return OmeletItem.BASIC.getTextureLocation();
	}

	@Override
	public EntityModel<FoodEntityBase> getModel(FoodEntityBase entity, IEntityItem item) {
		OmeletItem.ModelType type = OmeletItem.ModelType.getType((Item) item);
		if (type == OmeletItem.ModelType.EGG) {
			return eggModel;
		} else {
			return omeletModel;
		}
	}

}
