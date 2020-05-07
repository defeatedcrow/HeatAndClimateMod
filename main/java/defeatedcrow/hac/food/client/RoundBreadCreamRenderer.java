package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.core.client.base.ModelRoundBread;
import defeatedcrow.hac.food.entity.BreadRoundEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RoundBreadCreamRenderer extends DCRenderFoodBase<BreadRoundEntity> {

	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/round_bread_cream.png");
	private static final ModelRoundBread BAKED_MODEL = new ModelRoundBread(true);

	public RoundBreadCreamRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return BAKED_TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return BAKED_MODEL;
	}
}
