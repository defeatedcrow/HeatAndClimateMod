package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.IcecreamFloatModel;
import defeatedcrow.hac.food.client.model.IcecreamModel;
import defeatedcrow.hac.food.client.model.IcecreamPuddingModel;
import defeatedcrow.hac.food.client.model.IcecreamSandaeModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.IcecreamItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderIcecream<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected IcecreamSandaeModel<FoodEntityBase> sandaeModel;
	protected IcecreamFloatModel<FoodEntityBase> floatModel;
	protected IcecreamPuddingModel<FoodEntityBase> puddingModel;

	public RenderIcecream(Context ctx) {
		super(ctx);
		this.model = new IcecreamModel<>(ctx.bakeLayer(IcecreamItem.BERRY.getLayerLocation()));
		this.sandaeModel = new IcecreamSandaeModel<>(ctx.bakeLayer(IcecreamItem.SUNDAE_COCOA.getLayerLocation()));
		this.floatModel = new IcecreamFloatModel<>(ctx.bakeLayer(IcecreamItem.CREAMSODA_MELON.getLayerLocation()));
		this.puddingModel = new IcecreamPuddingModel<>(ctx.bakeLayer(IcecreamItem.PUDDING.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return IcecreamItem.SUNDAE_COCOA.getTextureLocation();
	}

	@Override
	public EntityModel<FoodEntityBase> getModel(FoodEntityBase entity, IEntityItem item) {
		IcecreamItem.ModelType type = IcecreamItem.ModelType.getType((Item) item);
		if (type == IcecreamItem.ModelType.PUDDING) {
			return puddingModel;
		} else if (type == IcecreamItem.ModelType.FLOAT) {
			return floatModel;
		} else if (type == IcecreamItem.ModelType.SUNDAE) {
			return sandaeModel;
		} else {
			return model;
		}
	}

}
