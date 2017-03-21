package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WatermillTESR extends DCTorqueTESRBase {

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/watermill.png";
	}

	private static final ModelWatermill MODEL = new ModelWatermill();

	@Override
	protected DCTileModelBase getModel(TileTorqueBase te) {
		return MODEL;
	}

	@Override
	public void render(TileTorqueBase te, DCTileModelBase model, float rot, float speed, float tick) {
		EnumFacing base = te.getBaseSide();
		float x = 0F;
		float y = 0F;
		float z = 0F;

		switch (base) {
		case DOWN:
			break;
		case UP:
			x = 180F;
			break;
		case NORTH:
			x = 90F;
			break;
		case SOUTH:
			x = 90F;
			z = 180F;
			break;
		case EAST:
			x = 90F;
			z = -90F;
			break;
		case WEST:
			x = 90F;
			z = 90F;
			break;
		default:
			break;
		}

		GlStateManager.rotate(x, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(z, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(y, 0.0F, 1.0F, 0.0F);

		MODEL.render(rot, speed, tick);

		for (int i = 0; i < 4; i++) {
			GlStateManager.rotate(i * 90.0F, 0.0F, 1.0F, 0.0F);
			MODEL.renderWing(rot, speed, tick);
		}

	}

}
