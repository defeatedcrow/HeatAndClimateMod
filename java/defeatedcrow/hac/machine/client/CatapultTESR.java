package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.block.TileCatapult;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CatapultTESR extends DCTorqueTESRBase {

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/catapult.png";
	}

	private static final DCTileModelBase MODEL = new ModelCatapult();

	@Override
	protected DCTileModelBase getModel(TileTorqueBase te) {
		return MODEL;
	}

	@Override
	public void renderTileEntityAt(TileTorqueBase te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		TileCatapult cat = null;
		if (te != null && te instanceof TileCatapult) {
			cat = (TileCatapult) te;
		}
		if (cat == null)
			return;

		float rad = cat.getRad() * 1.0F;

		this.bindTexture(new ResourceLocation(getTexPass(0)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		this.render(te, MODEL, rad, 0.0F, partialTicks);

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

	}

	@Override
	public void render(TileTorqueBase te, DCTileModelBase model, float rot, float speed, float tick) {
		EnumFacing base = te.getBaseSide();
		float x = 0F;
		float y = 0F;
		float z = 0F;

		if (base == EnumFacing.NORTH) {
			y = 0F;
		}
		if (base == EnumFacing.SOUTH) {
			y = 180F;
		}
		if (base == EnumFacing.EAST) {
			y = 90F;
		}
		if (base == EnumFacing.WEST) {
			y = -90F;
		}

		GlStateManager.rotate(x, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(z, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(y, 0.0F, 1.0F, 0.0F);

		model.render(rot, speed, tick);
	}

}
