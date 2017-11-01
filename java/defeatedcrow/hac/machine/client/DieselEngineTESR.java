package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.client.model.ModelOilEngine;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DieselEngineTESR extends DCTorqueTESRBase {

	private static final DCTileModelBase MODEL = new ModelOilEngine();

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/oil_engine.png";
	}

	@Override
	protected DCTileModelBase getModel(TileTorqueBase te) {
		return MODEL;
	}

	@Override
	public void render(TileTorqueBase te, DCTileModelBase model, float rot, float speed, float tick) {
		EnumFacing base = te.getBaseSide();
		EnumFacing face = te.getFaceSide();
		float x = 0F;
		float y = 0F;
		float z = 0F;

		switch (base) {
		case DOWN:
			y = -90F;
			x = 90F;
			break;
		case UP:
			y = -90F;
			x = -90F;
			break;
		case NORTH:
			y = -90F;
			break;
		case SOUTH:
			y = 90F;
			break;
		case EAST:
			y = 0F;
			break;
		case WEST:
			y = 180F;
			break;
		default:
			break;
		}

		GlStateManager.rotate(x, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(z, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(y, 0.0F, 1.0F, 0.0F);

		model.render(rot, speed, tick);
	}

	@Override
	public void renderTileEntityAt(TileTorqueBase te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		DCTileModelBase model = this.getModel(te);
		if (model == null)
			return;
		float speed = te.prevSpeed + (te.currentSpeed - te.prevSpeed) * partialTicks;
		float rot = te.prevRotation + (te.currentRotation - te.prevRotation) * partialTicks;

		int r = MathHelper.floor_float(rot);
		r = r & 1;

		this.bindTexture(new ResourceLocation(getTexPass(0)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F + 0.0125F * r, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		this.render(te, model, rot, speed, partialTicks);

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

}
