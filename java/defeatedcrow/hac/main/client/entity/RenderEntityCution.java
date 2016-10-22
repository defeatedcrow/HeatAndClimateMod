package defeatedcrow.hac.main.client.entity;

import defeatedcrow.hac.main.entity.EntityCution;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityCution extends Render<EntityCution> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate", "textures/entity/cution_white.png");

	protected RenderEntityCution(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityCution entity, double x, double y, double z, float yaw, float partialTicks) {
		// なにもしない
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCution entity) {
		return TEX;
	}

}
