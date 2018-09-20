package defeatedcrow.hac.food.client;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelTart;
import defeatedcrow.hac.food.entity.PotatoQuicheEntity;

@SideOnly(Side.CLIENT)
public class PotatoQuicheRenderer extends DCRenderFoodBase<PotatoQuicheEntity> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/quiche_potato_raw.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/quiche_potato_baked.png");
	private static final ModelTart RAW_MODEL = new ModelTart(false);
	private static final ModelTart BAKED_MODEL = new ModelTart(true);

	public PotatoQuicheRenderer(RenderManager renderManager) {
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
