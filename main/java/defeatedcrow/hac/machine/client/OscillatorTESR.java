package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.block.TileOscillator;
import defeatedcrow.hac.machine.client.model.ModelOscillator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OscillatorTESR extends DCTorqueTESRBase {

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/oscillator.png";
	}

	private static final DCTileModelBase MODEL = new ModelOscillator();

	@Override
	protected DCTileModelBase getModel(TileTorqueBase te) {
		return MODEL;
	}

	@Override
	public void render(TileTorqueBase te, DCTileModelBase model, float rot, float speed, float tick) {
		int c = 0;
		if (te instanceof TileOscillator) {
			c = ((TileOscillator) te).power;
		}
		speed = c;
		super.render(te, model, rot, speed, tick);

	}

}
