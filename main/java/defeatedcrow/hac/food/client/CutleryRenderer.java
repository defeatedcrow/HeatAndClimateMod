package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.client.model.ModelCutlery;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CutleryRenderer extends DCRenderFoodBase<FoodEntityBase> {

	protected static final ResourceLocation TEX1 = new ResourceLocation("dcs_climate",
			"textures/entity/food/cutlery_chopsticks.png");
	protected static final ResourceLocation TEX2 = new ResourceLocation("dcs_climate",
			"textures/entity/food/cutlery_spoon.png");
	protected static final ModelCutlery MODEL = new ModelCutlery(false);

	public CutleryRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(FoodEntityBase entity, double x, double y, double z, float yaw, float partialTicks) {
		// super.doRender(entity, x, y, z, yaw, partialTicks);

		int meta = 0;
		if (entity != null && !DCUtil.isEmpty(entity.getDropItem())) {
			ItemStack stack = entity.getDropItem().copy();
			meta = stack.getItemDamage();
		}

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.3F, (float) z);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.scale(-0.6F, -0.6F, 0.6F);

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		if (meta == 0) {
			this.bindTexture(TEX1);
			MODEL.render(0.0625F, entity);

		} else if (meta == 1) {
			this.bindTexture(TEX2);
			MODEL.renderCutlery(0.0625F);
			MODEL.renderSpoon(0.0625F);
		} else if (meta == 2) {
			this.bindTexture(TEX2);
			MODEL.renderCutlery(0.0625F);
			MODEL.renderFork(0.0625F);
		}

		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return TEX1;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return MODEL;
	}
}
