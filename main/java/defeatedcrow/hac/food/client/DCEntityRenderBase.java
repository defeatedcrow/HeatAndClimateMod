package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/* FoodEntityBase用のRender */
@SideOnly(Side.CLIENT)
public abstract class DCEntityRenderBase<T extends DCEntityBase> extends Render<T> {

	protected DCEntityRenderBase(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float partialTicks) {
		float height = entity.height * 0.5F;
		EnumFacing side = entity.getSide();
		DCFoodModelBase model = this.getEntityModel();
		ResourceLocation tex = this.getFoodTexture();

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.bindTexture(tex);

		float rotX = 0F;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		model.render(0.0625F, null);

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(DCEntityBase entity) {
		return getFoodTexture();
	}

	protected abstract ResourceLocation getFoodTexture();

	protected abstract DCFoodModelBase getEntityModel();

}
