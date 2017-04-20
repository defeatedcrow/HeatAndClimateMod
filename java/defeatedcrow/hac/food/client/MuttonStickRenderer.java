package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelYakitoriStick;
import defeatedcrow.hac.food.entity.YakitoriStickEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MuttonStickRenderer extends DCRenderFoodBase<YakitoriStickEntity> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/stick_mutton_raw.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/stick_mutton_cooked.png");
	private static final ModelYakitoriStick RAW_MODEL = new ModelYakitoriStick(false);
	private static final ModelYakitoriStick BAKED_MODEL = new ModelYakitoriStick(true);

	public MuttonStickRenderer(RenderManager renderManager) {
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
