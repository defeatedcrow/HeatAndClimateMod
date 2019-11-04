package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelNacho;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DishNachosRenderer extends DCRenderFoodBase<FoodEntityBase> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/dish_nachos.png");
	private static final ModelNacho MODEL = new ModelNacho(false);

	public DishNachosRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(FoodEntityBase entity, double x, double y, double z, float yaw, float partialTicks) {
		float height = entity.height * 0.5F;
		float f = getScale();
		float f2 = getOffset();

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + f2, (float) z);
		GlStateManager.scale(-f, -f, f);
		this.bindTexture(TEX);

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.renderChips(0.0625F);

		GlStateManager.rotate(rotY + 0.25F, 0.0F, 1.0F, 0.0F);

		MODEL.renderChips(0.0625F);

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, yaw, partialTicks);
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
