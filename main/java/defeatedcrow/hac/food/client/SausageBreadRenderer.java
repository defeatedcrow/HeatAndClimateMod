package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelSausageBread;
import defeatedcrow.hac.food.entity.BreadRoundEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SausageBreadRenderer extends DCRenderFoodBase<BreadRoundEntity> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/sausage_bread.png");
	private static final ModelSausageBread RAW_MODEL = new ModelSausageBread(false);
	private static final ModelSausageBread BAKED_MODEL = new ModelSausageBread(true);

	public SausageBreadRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return baked ? BAKED_MODEL : RAW_MODEL;
	}

	@Override
	protected float getScale() {
		return 1.5F;
	}
}
