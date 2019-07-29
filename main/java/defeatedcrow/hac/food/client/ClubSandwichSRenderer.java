package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelClubSand;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClubSandwichSRenderer extends DCRenderFoodBase<FoodEntityBase> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/clubsand_square.png");
	private static final ModelClubSand MODEL = new ModelClubSand(false);

	public ClubSandwichSRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected float getScale() {
		return 0.75F;
	}

	@Override
	protected float getOffset() {
		return 0.0625F;
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return MODEL;
	}

	protected int meta() {
		return 0;
	}
}
