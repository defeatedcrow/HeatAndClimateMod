package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.SauteModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.SauteItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderSaute<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected SauteModel<FoodEntityBase> tofuModel;
	protected SauteModel<FoodEntityBase> eggModel;
	protected SauteModel<FoodEntityBase> nasuModel;
	protected SauteModel<FoodEntityBase> chickenModel;

	public RenderSaute(Context ctx) {
		super(ctx);
		this.tofuModel = new SauteModel<>(ctx.bakeLayer(SauteItem.GREEN.getLayerLocation()));
		this.eggModel = new SauteModel<>(ctx.bakeLayer(SauteItem.CRAB_EGG.getLayerLocation()));
		this.nasuModel = new SauteModel<>(ctx.bakeLayer(SauteItem.NASU.getLayerLocation()));
		this.chickenModel = new SauteModel<>(ctx.bakeLayer(SauteItem.WHITE_CHICKEN.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return SauteItem.MABO.getTextureLocation();
	}

	@Override
	public EntityModel<FoodEntityBase> getModel(FoodEntityBase entity, IEntityItem item) {
		SauteItem.ModelType type = SauteItem.ModelType.getType((Item) item);
		if (type == SauteItem.ModelType.TOFU) {
			return tofuModel;
		} else if (type == SauteItem.ModelType.EGG) {
			return eggModel;
		} else if (type == SauteItem.ModelType.CHICKEN) {
			return chickenModel;
		} else {
			return nasuModel;
		}
	}

}
