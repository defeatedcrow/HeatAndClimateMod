package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelDishSashimi;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DishSashimiRenderer extends DCRenderFoodBase<FoodEntityBase> {

	private static final ResourceLocation TEX0 = new ResourceLocation("dcs_climate",
			"textures/entity/food/dish_sashimi_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation("dcs_climate",
			"textures/entity/food/dish_sashimi_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation("dcs_climate",
			"textures/entity/food/dish_sashimi_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation("dcs_climate",
			"textures/entity/food/dish_sashimi_3.png");
	private static final ModelDishSashimi MODEL = new ModelDishSashimi(false);

	public DishSashimiRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(FoodEntityBase entity, double x, double y, double z, float yaw, float partialTicks) {
		// super.doRender(entity, x, y, z, yaw, partialTicks);

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.0625F, (float) z);
		GlStateManager.scale(-0.65F, -0.65F, 0.65F);

		int num = entity.getIndividual() & 3;

		this.bindTexture(TEX0);
		if (num == 1) {
			this.bindTexture(TEX1);
		} else if (num == 2) {
			this.bindTexture(TEX2);
		} else if (num == 3) {
			this.bindTexture(TEX3);
		}

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.render(0.0625F, entity);

		GlStateManager.popMatrix();

	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return TEX0;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return MODEL;
	}

	protected int meta() {
		return 0;
	}
}
