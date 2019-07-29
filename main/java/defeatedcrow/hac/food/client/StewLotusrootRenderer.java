package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelStewBowl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class StewLotusrootRenderer extends DCRenderFoodBase<FoodEntityBase> {

	private static final ResourceLocation BOWL_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/stewbowl_base.png");
	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/stew_lotusroot.png");
	private static final ModelStewBowl MODEL = new ModelStewBowl(false);

	public StewLotusrootRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(FoodEntityBase entity, double x, double y, double z, float yaw, float partialTicks) {
		// super.doRender(entity, x, y, z, yaw, partialTicks);

		// 1
		this.bindTexture(BOWL_TEX);

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.0625F, (float) z);
		GlStateManager.scale(-0.5F, -1.0F, 0.5F);

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.render(0.0625F, entity);

		GlStateManager.popMatrix();

		// 2
		this.bindTexture(TEX);

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.0625F, (float) z);
		GlStateManager.scale(-0.5F, -1.0F, 0.5F);

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.renderSoup1(0.0625F);

		GlStateManager.popMatrix();

		// 3
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.0625F, (float) z);
		GlStateManager.enableBlend();
		GlStateManager
				.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.95F);
		GlStateManager.scale(-0.5F, -1.0F, 0.5F);

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.renderSoup2(0.0625F);

		GlStateManager.disableBlend();
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
