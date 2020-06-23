package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelFishSteak;
import defeatedcrow.hac.food.client.model.ModelSteakPlate;
import defeatedcrow.hac.food.entity.PlateFishEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlateFishRenderer extends DCRenderFoodBase<PlateFishEntity> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/steak_fish_raw.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/steak_fish_baked.png");
	private static final ModelFishSteak RAW_MODEL = new ModelFishSteak(false);
	private static final ModelFishSteak BAKED_MODEL = new ModelFishSteak(true);
	private static final ResourceLocation PLATE_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/foodplate_steel.png");
	private static final ModelSteakPlate PLATE_MODEL = new ModelSteakPlate(true);

	@Override
	public void doRender(PlateFishEntity entity, double x, double y, double z, float yaw, float partialTicks) {
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

	public PlateFishRenderer(RenderManager renderManager) {
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
