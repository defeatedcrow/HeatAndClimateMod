package defeatedcrow.hac.food.client.entity;

import defeatedcrow.hac.food.client.model.PlateBoneMarrowModel;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PlateBoneMarrowItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderPlateBone<T extends FoodEntityBase> extends RenderFoodBase {

	public RenderPlateBone(Context ctx) {
		super(ctx);
		this.model = new PlateBoneMarrowModel<>(ctx.bakeLayer(PlateBoneMarrowItem.BONE_MARROW_RAW.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(FoodEntityBase entity) {
		return PlateBoneMarrowItem.BONE_MARROW_RAW.getTextureLocation();
	}

}
