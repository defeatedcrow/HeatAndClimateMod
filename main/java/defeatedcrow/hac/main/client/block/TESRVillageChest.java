package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.block.build.TileLowChest;
import defeatedcrow.hac.main.client.model.ModelMetalChest_A;
import defeatedcrow.hac.main.client.model.ModelMetalChest_E;
import defeatedcrow.hac.main.client.model.ModelMetalChest_F;
import defeatedcrow.hac.main.client.model.ModelMetalChest_G;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class TESRVillageChest extends DCLockableTESRBase {

	private static final String TEX_A = "dcs_climate:textures/tiles/metalchest_village.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/metalchest_village_2.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/metalchest_village_3.png";
	private static final String TEX_D = "dcs_climate:textures/tiles/metalchest_village_4.png";
	private static final ModelMetalChest_A MODEL_A = new ModelMetalChest_A();
	private static final ModelMetalChest_E MODEL_E = new ModelMetalChest_E();
	private static final ModelMetalChest_F MODEL_F = new ModelMetalChest_F();
	private static final ModelMetalChest_G MODEL_G = new ModelMetalChest_G();

	@Override
	public void render(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		int type = 0;
		int face = 0;
		float f = 0.0F;
		boolean open = false;
		if (te instanceof TileLowChest && ((TileLowChest) te).isOpen) {
			open = true;
		}

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
		GlStateManager.scale(0.99F, -0.99F, -0.99F);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);

		if (open) {
			this.render(model, -75.0F);
		} else {
			this.render(model, 0.0F);
		}
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
			return MODEL_E;
		case 2:
			return MODEL_F;
		case 3:
			return MODEL_G;
		}
		return MODEL_A;
	}

}
