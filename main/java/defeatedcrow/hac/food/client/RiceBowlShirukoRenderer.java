package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelRiceBowl2;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RiceBowlShirukoRenderer extends DCRenderFoodBase<FoodEntityBase> {

	protected static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/ricebowl_shiruko.png");
	protected static final ModelRiceBowl2 MODEL = new ModelRiceBowl2(false);

	public RiceBowlShirukoRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(FoodEntityBase entity, double x, double y, double z, float yaw, float partialTicks) {
		// super.doRender(entity, x, y, z, yaw, partialTicks);

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.35F, (float) z);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.scale(-0.75F, -0.75F, 0.75F);

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		this.bindTexture(TEX);
		MODEL.setRotationAngles(0, 0, 0, 0, 0, 0, entity);
		MODEL.render(0.0625F, entity);
		MODEL.renderSoup(0.0625F);
		MODEL.renderMochi(0.0625F);

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
}
