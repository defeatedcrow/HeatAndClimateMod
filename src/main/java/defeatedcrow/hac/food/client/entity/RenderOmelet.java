package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.OmeletModel;
import defeatedcrow.hac.food.client.model.SausageCurryModel;
import defeatedcrow.hac.food.client.model.SausageModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.OmeletItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderOmelet<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected SausageModel<FoodEntityBase> sausageModel;
	protected SausageCurryModel<FoodEntityBase> sausageCurryModel;

	public RenderOmelet(Context ctx) {
		super(ctx);
		this.model = new OmeletModel<>(ctx.bakeLayer(OmeletItem.BASIC.getLayerLocation()));
		this.sausageModel = new SausageModel<>(ctx.bakeLayer(OmeletItem.SAUSAGE.getLayerLocation()));
		this.sausageCurryModel = new SausageCurryModel<>(ctx.bakeLayer(OmeletItem.SAUSAGE_CURRY.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return OmeletItem.BASIC.getTextureLocation();
	}

	@Override
	public EntityModel<FoodEntityBase> getModel(FoodEntityBase entity, IEntityItem item) {
		OmeletItem.ModelType type = OmeletItem.ModelType.getType((Item) item);
		if (type == OmeletItem.ModelType.SAUSAGE) {
			return sausageModel;
		} else if (type == OmeletItem.ModelType.CURRY) {
			return sausageCurryModel;
		} else {
			return model;
		}
	}

}
