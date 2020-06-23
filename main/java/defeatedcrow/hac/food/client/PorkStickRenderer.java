package defeatedcrow.hac.food.client;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelMeatStick;
import defeatedcrow.hac.food.entity.StickPorkEntity;

@SideOnly(Side.CLIENT)
public class PorkStickRenderer extends DCRenderFoodBase<StickPorkEntity> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/stick_pork_raw.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/stick_pork_cooked.png");
	private static final ModelMeatStick RAW_MODEL = new ModelMeatStick(false);
	private static final ModelMeatStick BAKED_MODEL = new ModelMeatStick(true);

	public PorkStickRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return baked ? BAKED_TEX : RAW_TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return baked ? BAKED_MODEL : RAW_MODEL;
	}
}
