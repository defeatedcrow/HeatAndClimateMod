package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelDishPasta;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PastaTomatoRenderer extends DCRenderFoodBase<FoodEntityBase> {

	private static final ResourceLocation DISH_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/dish_white.png");
	private static final ResourceLocation PASTA_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/pasta_tomato.png");
	private static final ModelDishPasta MODEL = new ModelDishPasta(false);

	public PastaTomatoRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected float getOffset() {
		return 0.0F;
	}

	@Override
	public void doRender(FoodEntityBase entity, double x, double y, double z, float yaw, float partialTicks) {
		float height = entity.height * 0.5F;

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.bindTexture(DISH_TEX);

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.renderDish(0.0625F);

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return PASTA_TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return MODEL;
	}

	protected int meta() {
		return 0;
	}
}
