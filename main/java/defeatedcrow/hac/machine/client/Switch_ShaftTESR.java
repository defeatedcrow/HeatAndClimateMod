package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.client.model.ModelShaft_Switch;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Switch_ShaftTESR extends DCTorqueTESRBase {

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/shaft_brass_switch.png";
	}

	private static final DCTileModelBase MODEL = new ModelShaft_Switch();

	@Override
	protected DCTileModelBase getModel(TileTorqueBase te) {
		return MODEL;
	}

	@Override
	public void render(TileTorqueBase te, DCTileModelBase model, float rot, float speed, float tick) {
		IBlockState state = te.getWorld().getBlockState(te.getPos());
		if (DCState.getBool(state, DCState.POWERED)) {
			speed = 0F;
		} else {
			speed = 1F;
		}
		super.render(te, model, rot, speed, tick);

	}

}
