package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.block.build.TileLowChest;
import defeatedcrow.hac.main.client.model.ModelMetalChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class TESRMetalChest extends DCLockableTESRBase {

	private final ModelMetalChest model = new ModelMetalChest();

	@Override
	public void renderTileEntityAt(DCLockableTE te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		int type = 0;
		int face = 0;
		float f = 0.0F;
		boolean open = false;
		if (te instanceof TileLowChest && ((TileLowChest) te).isOpen) {
			open = true;
		}

		if (te.hasWorldObj()) {
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

		DCTileModelBase model = this.getModel(type);

		this.bindTexture(new ResourceLocation(getTexPass(type)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

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
		return "dcs_climate:textures/tiles/metalchest_steel.png";
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return model;
	}

}
