package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelToast;
import defeatedcrow.hac.food.entity.BreadToastEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ToastRenderer extends DCRenderFoodBase<BreadToastEntity> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/butter_toast_raw.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/butter_toast_baked.png");
	private static final ModelToast RAW_MODEL = new ModelToast(false);
	private static final ModelToast BAKED_MODEL = new ModelToast(true);

	public ToastRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(BreadToastEntity entity, double x, double y, double z, float yaw, float partialTicks) {
		super.doRender(entity, x, y, z, yaw, partialTicks);
		boolean baked = !entity.getRaw();
		if (baked) {
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y, (float) z);
			GlStateManager.scale(-0.75F, -0.75F, 0.75F);
			GlStateManager.enableBlend();
			GlStateManager
					.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.35F);

			this.bindTexture(BAKED_TEX);

			float rotX = -entity.rotationPitch;
			float rotY = entity.rotationYaw;
			float rotZ = 0F;

			GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

			BAKED_MODEL.renderButter(0.0625F);

			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}
	}

	@Override
	protected float getScale() {
		return 0.75F;
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
