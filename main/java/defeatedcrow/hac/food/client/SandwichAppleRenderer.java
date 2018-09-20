package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelSandwich;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SandwichAppleRenderer extends DCRenderFoodBase<FoodEntityBase> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/sandwich.png");
	private static final ModelSandwich MODEL = new ModelSandwich(false);

	public SandwichAppleRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(FoodEntityBase entity, double x, double y, double z, float yaw, float partialTicks) {
		super.doRender(entity, x, y, z, yaw, partialTicks);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);

		this.bindTexture(TEX);

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.render(0.0625F, meta());

		GlStateManager.popMatrix();
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
