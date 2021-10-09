package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.client.model.ModelWindowBlinds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRWindowBlinds extends TileEntitySpecialRenderer<TileEntity> {

	private static final String TEX_A = "dcs_climate:textures/tiles/window_blinds_white.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/window_blinds_black.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/window_blinds_wood.png";
	private static final ModelWindowBlinds MODEL_A = new ModelWindowBlinds(false);
	private static final ModelWindowBlinds MODEL_B = new ModelWindowBlinds(true);

	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		int type = 0;
		int face = 0;
		boolean half = false;
		boolean open = false;
		float f = 0.0F;

		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();

			type = meta & 3;
			open = type > 1;
			face = 5 - (meta >> 2);
			if (face == 2) {
				f = 180F;
			}
			if (face == 3) {
				f = 0F;
			}
			if (face == 4) {
				f = 90F;
			}
			if (face == 5) {
				f = -90F;
			}

			IBlockState upper = te.getWorld().getBlockState(te.getPos().up());
			if (upper != null && upper.getBlock() == MainInit.windowBlinds) {
				half = true;
			}
		}

		int color = 0;
		if (te instanceof IColorChangeTile)
			color = ((IColorChangeTile) te).getColor();

		this.bindTexture(new ResourceLocation(getTexPass(color)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F);

		ModelWindowBlinds model = MODEL_A;
		if (open) {
			model = MODEL_B;
		}

		model.render(f);
		model.renderBottom();
		if (!half) {
			model.renderTop();
		}

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
		}
		return TEX_A;
	}

	protected DCTileModelBase getModel(int i) {
		return MODEL_A;
	}
}
