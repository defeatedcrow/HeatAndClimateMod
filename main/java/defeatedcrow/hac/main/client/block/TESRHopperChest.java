package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.block.device.TileHopperChest;
import defeatedcrow.hac.main.client.model.ModelHopperChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TESRHopperChest extends DCLockableTESRBase {

	private final ModelHopperChest model = new ModelHopperChest();

	@Override
	public void render(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		int type = 0;
		int face = 0;
		float f = 0.0F;
		EnumFacing output = EnumFacing.DOWN;
		boolean open = false;
		if (te instanceof TileHopperChest) {
			if (((TileHopperChest) te).isOpen) {
				open = true;
			}
			output = ((TileHopperChest) te).getFaceSide();
		}

		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();
			int m = meta & 7;
			EnumSide side = EnumSide.fromIndex(m);

			switch (side) {
			case NORTH:
				f = 0F;
				break;
			case SOUTH:
				f = 180F;
				break;
			case EAST:
				f = 90F;
				break;
			case WEST:
				f = -90F;
				break;
			default:
				break;
			}
		}

		this.bindTexture(new ResourceLocation(getTexPass(type)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);

		if (open) {
			this.render(model, 75.0F);
		} else {
			this.render(model, 0.0F);
		}

		if (output == EnumFacing.DOWN) {
			model.renderOutputA();
		} else {
			float of = 0F;
			switch (output) {
			case NORTH:
				of = f;
				break;
			case SOUTH:
				of = f + 180F;
				break;
			case EAST:
				of = f + 90F;
				break;
			case WEST:
				of = f - 90F;
				break;
			default:
				break;
			}
			model.renderOutputB(of);
		}
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/hopper_chest.png";
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return model;
	}

}
