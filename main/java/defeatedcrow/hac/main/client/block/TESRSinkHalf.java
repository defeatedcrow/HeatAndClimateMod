package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.client.model.ModelHalfSink;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRSinkHalf extends TileEntitySpecialRenderer<TileEntity> {

	private static final String TEX_A = "dcs_climate:textures/tiles/sink_half_sus.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/sink_half_clay.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/sink_half_brick.png";
	private static final String TEX_D = "dcs_climate:textures/tiles/sink_half_stone.png";
	private static final ModelHalfSink MODEL = new ModelHalfSink();

	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		int type = 0;
		int face = 0;
		float f = 0.0F;

		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();

			type = meta & 3;
			face = 5 - (meta >> 2);
			if (face == 2) {
				f = 0F;
			}
			if (face == 3) {
				f = 180F;
			}
			if (face == 4) {
				f = -90F;
			}
			if (face == 5) {
				f = 90F;
			}
		}

		int color = 0;
		if (te instanceof IColorChangeTile)
			color = ((IColorChangeTile) te).getColor();

		DCTileModelBase model = this.getModel(color);

		this.bindTexture(new ResourceLocation(getTexPass(color)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
		model.render(0.0F);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected String getTexPass(int i) {
		switch (i) {
		case 0:
			return TEX_A;
		case 1:
			return TEX_B;
		case 2:
			return TEX_C;
		case 3:
			return TEX_D;
		}
		return TEX_A;
	}

	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
