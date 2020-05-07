package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelGoheiStick;
import defeatedcrow.hac.food.entity.StickPorkEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GoheiStickRenderer extends DCRenderFoodBase<StickPorkEntity> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate", "textures/entity/food/stick_gohei_raw.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate", "textures/entity/food/stick_gohei_cooked.png");
	private static final ModelGoheiStick RAW_MODEL = new ModelGoheiStick(false);
	private static final ModelGoheiStick BAKED_MODEL = new ModelGoheiStick(true);

	public GoheiStickRenderer(RenderManager renderManager) {
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
