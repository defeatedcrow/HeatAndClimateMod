package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelSquareBread;
import defeatedcrow.hac.food.entity.SquareBreadEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SquareBreadRenderer extends DCRenderFoodBase<SquareBreadEntity> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/square_bread_raw.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/square_bread_baked.png");
	private static final ModelSquareBread RAW_MODEL = new ModelSquareBread(false);
	private static final ModelSquareBread BAKED_MODEL = new ModelSquareBread(true);

	public SquareBreadRenderer(RenderManager renderManager) {
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

	@Override
	public void doRender(SquareBreadEntity entity, double x, double y, double z, float yaw, float partialTicks) {
		float height = entity.height * 0.5F;
		EnumFacing side = entity.getSide();
		boolean baked = !entity.getRaw();
		boolean burnt = entity.getBURNT();
		boolean mold = entity.getMOLD();
		DCFoodModelBase model = this.getEntityModel(baked);
		ResourceLocation tex = this.getFoodTexture(baked);

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.bindTexture(tex);
		if (burnt) {
			GlStateManager.color(0.2F, 0.2F, 0.2F);
		}

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		model.render(0.0625F, entity);
		if (mold) {
			((ModelSquareBread) model).renderMold(0.0625F);
		}

		if (burnt) {
			GlStateManager.color(1F, 1F, 1F);
		}
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

}
