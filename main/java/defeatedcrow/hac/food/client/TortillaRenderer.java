package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelPitaBread;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TortillaRenderer extends DCRenderFoodBase<FoodEntityBase> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/tortilla_raw.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/tortilla_baked.png");
	private static final ModelPitaBread RAW_MODEL = new ModelPitaBread(false);
	private static final ModelPitaBread BAKED_MODEL = new ModelPitaBread(true);

	public TortillaRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return baked ? BAKED_TEX : RAW_TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return baked ? RAW_MODEL : BAKED_MODEL;
	}

}
