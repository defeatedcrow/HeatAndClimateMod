package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.magic.proj.EntityMagicProjBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MagicDaggerRenderer extends Render<EntityMagicProjBase> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/magic/dagger_silver.png");
	private static final ModelDagger MODEL = new ModelDagger(false);

	public MagicDaggerRenderer(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.15F;
		this.shadowOpaque = 0.75F;
	}

	@Override
	public void doRender(EntityMagicProjBase entity, double x, double y, double z, float yaw, float partialTicks) {
		ItemStack itemstack = entity.getDropStack();

		float height = entity.height * 0.5F;
		DCFoodModelBase model = this.getEntityModel();
		ResourceLocation tex = this.getTexture();

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.bindTexture(tex);

		float rotX = -(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks);
		float rotY = 180.0F - (entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks);
		float rotZ = 0.0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		model.render(0.0625F);

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMagicProjBase entity) {
		return TEX;
	}

	protected ResourceLocation getTexture() {
		return TEX;
	}

	protected DCFoodModelBase getEntityModel() {
		return MODEL;
	}
}
