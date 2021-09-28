package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.client.model.ModelBlockTeaPot_A;
import defeatedcrow.hac.food.client.model.ModelBlockTeaPot_B;
import defeatedcrow.hac.food.client.model.ModelBlockTeaPot_C;
import defeatedcrow.hac.food.client.model.ModelBlockTeaPot_D;
import defeatedcrow.hac.main.api.IColorChangeTile;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRTeaPot extends DCLockableTESRBase {

	private static final String TEX_A = "dcs_climate:textures/tiles/teapot_silver.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/teapot_white.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/teapot_orange.png";
	private static final String TEX_D = "dcs_climate:textures/tiles/teapot_black.png";
	private static final ModelBlockTeaPot_A MODEL_A = new ModelBlockTeaPot_A();
	private static final ModelBlockTeaPot_B MODEL_B = new ModelBlockTeaPot_B();
	private static final ModelBlockTeaPot_C MODEL_C = new ModelBlockTeaPot_C();
	private static final ModelBlockTeaPot_D MODEL_D = new ModelBlockTeaPot_D();

	@Override
	public void render(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
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

		if (te instanceof IColorChangeTile)
			type = ((IColorChangeTile) te).getColor();

		DCTileModelBase model = this.getModel(type);

		this.bindTexture(new ResourceLocation(getTexPass(type)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
		this.render(model, 0.0F);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Override
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

	@Override
	protected DCTileModelBase getModel(int i) {
		switch (i) {
		case 0:
			return MODEL_A;
		case 1:
			return MODEL_B;
		case 2:
			return MODEL_C;
		case 3:
			return MODEL_D;
		}
		return MODEL_A;
	}
}
