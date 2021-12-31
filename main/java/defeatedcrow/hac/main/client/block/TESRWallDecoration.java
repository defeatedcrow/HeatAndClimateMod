package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.client.model.ModelWallDecoration_A;
import defeatedcrow.hac.main.client.model.ModelWallDecoration_B;
import defeatedcrow.hac.main.client.model.ModelWallDecoration_C;
import defeatedcrow.hac.main.client.model.ModelWallDecoration_D;
import defeatedcrow.hac.main.client.model.ModelWallDecoration_E;
import defeatedcrow.hac.main.client.model.ModelWallDecoration_F;
import defeatedcrow.hac.main.client.model.ModelWallDecoration_G;
import defeatedcrow.hac.main.client.model.ModelWallDecoration_H;
import defeatedcrow.hac.main.client.model.ModelWallDecoration_I;
import defeatedcrow.hac.main.client.model.ModelWallDecoration_J;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRWallDecoration extends TileEntitySpecialRenderer<TileEntity> {

	private static final String TEX_A = "dcs_climate:textures/tiles/wall_deco_a.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/wall_deco_b.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/wall_deco_c.png";
	private static final String TEX_D = "dcs_climate:textures/tiles/wall_deco_d.png";
	private static final String TEX_E = "dcs_climate:textures/tiles/wall_deco_e.png";
	private static final String TEX_F1 = "dcs_climate:textures/tiles/wall_deco_f1.png";
	private static final String TEX_F2 = "dcs_climate:textures/tiles/wall_deco_f2.png";
	private static final String TEX_F3 = "dcs_climate:textures/tiles/wall_deco_f3.png";
	private static final String TEX_G = "dcs_climate:textures/tiles/wall_deco_g.png";
	private static final String TEX_H = "dcs_climate:textures/tiles/wall_deco_h.png";
	private static final String TEX_I = "dcs_climate:textures/tiles/wall_deco_i.png";
	private static final String TEX_J = "dcs_climate:textures/tiles/wall_deco_j.png";
	private static final DCTileModelBase MODEL_A = new ModelWallDecoration_A();
	private static final DCTileModelBase MODEL_B = new ModelWallDecoration_B();
	private static final DCTileModelBase MODEL_C = new ModelWallDecoration_C();
	private static final DCTileModelBase MODEL_D = new ModelWallDecoration_D();
	private static final DCTileModelBase MODEL_E = new ModelWallDecoration_E();
	private static final DCTileModelBase MODEL_F = new ModelWallDecoration_F();
	private static final DCTileModelBase MODEL_G = new ModelWallDecoration_G();
	private static final DCTileModelBase MODEL_H = new ModelWallDecoration_H();
	private static final DCTileModelBase MODEL_I = new ModelWallDecoration_I();
	private static final DCTileModelBase MODEL_J = new ModelWallDecoration_J();

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

		model.render(0.0625F);

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected String getTexPass(int i) {
		switch (i) {
		case 1:
			return TEX_A;
		case 2:
			return TEX_B;
		case 3:
			return TEX_C;
		case 4:
			return TEX_D;
		case 5:
			return TEX_E;
		case 6:
			return TEX_F1;
		case 7:
			return TEX_F2;
		case 8:
			return TEX_F3;
		case 9:
			return TEX_H;
		case 10:
			return TEX_I;
		case 11:
			return TEX_J;
		}
		return TEX_G;
	}

	protected DCTileModelBase getModel(int i) {
		switch (i) {
		case 1:
			return MODEL_A;
		case 2:
			return MODEL_B;
		case 3:
			return MODEL_C;
		case 4:
			return MODEL_D;
		case 5:
			return MODEL_E;
		case 6:
		case 7:
		case 8:
			return MODEL_F;
		case 9:
			return MODEL_H;
		case 10:
			return MODEL_I;
		case 11:
			return MODEL_J;
		}
		return MODEL_G;
	}
}
