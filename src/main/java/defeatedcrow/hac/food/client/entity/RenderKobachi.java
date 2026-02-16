package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.food.client.model.KobachiModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.KobachiItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RenderKobachi<T extends FoodEntityBase> extends RenderFoodBase<FoodEntityBase> {

	protected KobachiModel<FoodEntityBase> mainModel;
	protected KobachiModel<FoodEntityBase> pasteModel;
	protected KobachiModel<FoodEntityBase> tunaModel;
	protected KobachiModel<FoodEntityBase> chickenModel;

	public RenderKobachi(Context ctx) {
		super(ctx);
		this.mainModel = new KobachiModel<>(ctx.bakeLayer(KobachiItem.PUMPKIN.getLayerLocation()));
		this.pasteModel = new KobachiModel<>(ctx.bakeLayer(KobachiItem.NAMEROU.getLayerLocation()));
		this.tunaModel = new KobachiModel<>(ctx.bakeLayer(KobachiItem.TUNA_AVOCADO.getLayerLocation()));
		this.chickenModel = new KobachiModel<>(ctx.bakeLayer(KobachiItem.CHICKEN.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return KobachiItem.PUMPKIN.getTextureLocation();
	}

	@Override
	public EntityModel<FoodEntityBase> getModel(FoodEntityBase entity, IEntityItem item) {
		KobachiItem.ModelType type = KobachiItem.ModelType.getType((Item) item);
		if (type == KobachiItem.ModelType.CHICKEN) {
			return chickenModel;
		} else if (type == KobachiItem.ModelType.PASTE) {
			return pasteModel;
		} else if (type == KobachiItem.ModelType.TUNA) {
			return tunaModel;
		} else {
			return mainModel;
		}
	}

}
