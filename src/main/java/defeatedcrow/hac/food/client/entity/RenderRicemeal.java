package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.Rice_ChaofanModel;
import defeatedcrow.hac.food.client.model.Rice_HainanChickenModel;
import defeatedcrow.hac.food.client.model.Rice_JambalayaModel;
import defeatedcrow.hac.food.client.model.Rice_JollofModel;
import defeatedcrow.hac.food.client.model.Rice_PaelliaModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.RicemealItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderRicemeal<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected Rice_HainanChickenModel<FoodEntityBase> chickenModel;
	protected Rice_JambalayaModel<FoodEntityBase> jambalayaModel;
	protected Rice_JollofModel<FoodEntityBase> jollofModel;
	protected Rice_PaelliaModel<FoodEntityBase> paelliaModel;

	public RenderRicemeal(Context ctx) {
		super(ctx);
		this.model = new Rice_ChaofanModel<>(ctx.bakeLayer(RicemealItem.CHAOFAN.getLayerLocation()));
		chickenModel = new Rice_HainanChickenModel<>(ctx.bakeLayer(RicemealItem.HAINAN.getLayerLocation()));
		jambalayaModel = new Rice_JambalayaModel<>(ctx.bakeLayer(RicemealItem.JAMBALAYA.getLayerLocation()));
		jollofModel = new Rice_JollofModel<>(ctx.bakeLayer(RicemealItem.JOLLOF.getLayerLocation()));
		paelliaModel = new Rice_PaelliaModel<>(ctx.bakeLayer(RicemealItem.PAELLIA.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return RicemealItem.CHAOFAN.getTextureLocation();
	}

	@Override
	public EntityModel<FoodEntityBase> getModel(FoodEntityBase entity, IEntityItem item) {
		RicemealItem.ModelType type = RicemealItem.ModelType.getType((Item) item);
		if (type == RicemealItem.ModelType.HAINAN) {
			return chickenModel;
		} else if (type == RicemealItem.ModelType.JAMBALAYA) {
			return jambalayaModel;
		} else if (type == RicemealItem.ModelType.JOLLOF) {
			return jollofModel;
		} else if (type == RicemealItem.ModelType.PAELLIA) {
			return paelliaModel;
		} else {
			return model;
		}
	}

}
