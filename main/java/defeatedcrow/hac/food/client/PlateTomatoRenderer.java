package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelSoup;
import defeatedcrow.hac.food.client.model.ModelSteakPlate;
import defeatedcrow.hac.food.entity.PlateSoupEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlateTomatoRenderer extends DCRenderFoodBase<PlateSoupEntity> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/soup_tomato_raw.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/soup_tomato_baked.png");
	private static final ModelSoup RAW_MODEL = new ModelSoup(false);
	private static final ModelSoup BAKED_MODEL = new ModelSoup(true);
	private static final ResourceLocation PLATE_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/foodplate_steel.png");
	private static final ModelSteakPlate PLATE_MODEL = new ModelSteakPlate(true);

	@Override
	public void doRender(PlateSoupEntity entity, double x, double y, double z, float yaw, float partialTicks) {
		float height = entity.height * 0.5F;

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.bindTexture(PLATE_TEX);

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		PLATE_MODEL.render(0.0625F, entity);

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	public PlateTomatoRenderer(RenderManager renderManager) {
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
