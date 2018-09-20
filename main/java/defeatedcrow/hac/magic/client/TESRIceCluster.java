package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.magic.block.TileIceCluster;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRIceCluster extends TileEntitySpecialRenderer<TileIceCluster> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/magic/crystal_ice.png");
	private static final ModelCluster MODEL = new ModelCluster();

	@Override
	public void render(TileIceCluster te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		this.bindTexture(TEX);

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.0F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
		MODEL.render(0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected void render(DCTileModelBase model, float f) {
		model.render(f);
	}
}
