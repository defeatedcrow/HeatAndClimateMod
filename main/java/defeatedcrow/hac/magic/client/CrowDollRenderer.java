package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.magic.entity.EntityCrowDoll;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CrowDollRenderer extends RenderLivingBase<EntityCrowDoll> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/crow_doll.png");
	private static final ModelCrowDoll MODEL = new ModelCrowDoll();

	public CrowDollRenderer(RenderManager renderManager) {
		super(renderManager, new ModelCrowDoll(), 0F);
		this.addLayer(new LayerArrow(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCrowDoll entity) {
		return TEX;
	}

	@Override
	protected boolean canRenderName(EntityCrowDoll entity) {
		return false;
	}

}
