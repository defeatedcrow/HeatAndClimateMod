package defeatedcrow.hac.main.client.block;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.client.model.ModelDoorHikido;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRDoorHikido extends TileEntitySpecialRenderer<TileEntity> {

	private static final String TEX_A = "dcs_climate:textures/tiles/hikido_shoji.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/hikido_yukimi.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/hikido_glass.png";
	private static final String TEX_D = "dcs_climate:textures/tiles/hikido_husuma_1.png";
	private static final String TEX_E = "dcs_climate:textures/tiles/hikido_husuma_2.png";
	private static final String TEX_F = "dcs_climate:textures/tiles/hikido_husuma_3.png";
	private static final String TEX_G = "dcs_climate:textures/tiles/hikido_husuma_4.png";
	private static final String TEX_H = "dcs_climate:textures/tiles/hikido_husuma_5.png";
	private static final String TEX_I = "dcs_climate:textures/tiles/hikido_glass_gray.png";
	private static final String TEX_J = "dcs_climate:textures/tiles/hikido_glass_black.png";
	private static final String TEX_K = "dcs_climate:textures/tiles/hikido_steel.png";
	private static final ModelDoorHikido MODEL = new ModelDoorHikido();

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
			half = ((type & 1) != 0);
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

		if (!half) {
			int color = 0;
			if (te instanceof IColorChangeTile)
				color = ((IColorChangeTile) te).getColor();

			this.bindTexture(new ResourceLocation(getTexPass(color)));

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			if (color == 2) {
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);

			if (open) {
				MODEL.open();
			} else {
				MODEL.close();
			}

			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);

			MODEL.render(0.0625F);

			GlStateManager.disableRescaleNormal();
			if (color == 2) {
				GL11.glDisable(GL11.GL_BLEND);
			}
			GlStateManager.popMatrix();
		}
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
		case 4:
			return TEX_G;
		case 5:
			return TEX_H;
		case 6:
			return TEX_I;
		case 7:
			return TEX_J;
		case 8:
			return TEX_K;
		}
		return TEX_A;
	}

	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
