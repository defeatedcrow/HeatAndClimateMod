package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.magic.block.TileTimeCage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRTimeCage extends TileEntitySpecialRenderer<TileTimeCage> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/magic/butterfly_cage.png");
	private static final ModelButterflyCage MODEL = new ModelButterflyCage();

	@Override
	public void renderTileEntityAt(TileTimeCage te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		this.bindTexture(TEX);
		float r = 0.0F;
		int type = 0;
		int face = 0;

		if (te.hasWorldObj()) {
			int meta = te.getBlockMetadata();

			type = meta & 3;
			face = 5 - (meta >> 2);
			if (face == 2) {
				r = 0F;
			}
			if (face == 3) {
				r = 180F;
			}
			if (face == 4) {
				r = -90F;
			}
			if (face == 5) {
				r = 90F;
			}
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(r, 0.0F, 1.0F, 0.0F);
		MODEL.render(0.0625F);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(r, 0.0F, 1.0F, 0.0F);
		MODEL.renderGlass(0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected void render(DCTileModelBase model, float f) {
		model.render(f);
	}
}
