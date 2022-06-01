package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.client.model.ModelKineticMotor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KineticMotorTESR extends DCTorqueTESRBase {

	private static final DCTileModelBase MODEL = new ModelKineticMotor();

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/kinetic_motor.png";
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
			y = 90F;
			x = -90F;
			break;
		case UP:
			y = 90F;
			x = 90F;
			break;
		case NORTH:
			y = 90F;
			break;
		case SOUTH:
			y = -90F;
			break;
		case EAST:
			y = 180F;
			break;
		case WEST:
			break;
		default:
			break;
		}

		GlStateManager.rotate(x, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(z, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(y, 0.0F, 1.0F, 0.0F);

		model.render(rot, speed, tick);
	}

}
