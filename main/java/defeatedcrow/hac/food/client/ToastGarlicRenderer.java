package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelGarlicToast;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ToastGarlicRenderer extends DCRenderFoodBase<FoodEntityBase> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/garlic_toast_raw.png");
	private static final ResourceLocation BAKED = new ResourceLocation("dcs_climate",
			"textures/entity/food/garlic_toast_baked.png");
	private static final ModelGarlicToast MODEL = new ModelGarlicToast(false);

	public ToastGarlicRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return baked ? BAKED : TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return MODEL;
	}

	protected int meta() {
		return 0;
	}
}
