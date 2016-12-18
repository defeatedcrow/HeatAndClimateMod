package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.energy.ITorqueProvider;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileCreativeBox extends TileTorqueBase implements ITorqueProvider {

	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 64.0F;
	}

	@Override
	public void updateTile() {
		super.updateTile();

		this.currentTorque += 128.0F;

		// provider
		this.provideTorque(worldObj, getPos().offset(getOutputSide()), getOutputSide(), false);
	}

	@Override
	public EnumFacing getOutputSide() {
		return this.getBaseSide().getOpposite();
	}

	@Override
	public float getAmount() {
		return this.getCurrentTorque();
	}

	@Override
	public boolean canProvideTorque(World world, BlockPos outputPos, EnumFacing output) {
		TileEntity tile = world.getTileEntity(outputPos);
		float amo = this.getCurrentTorque();
		if (tile != null && tile instanceof ITorqueReceiver && amo > 0F) {
			return ((ITorqueReceiver) tile).canReceiveTorque(amo, output.getOpposite());
		}
		return false;
	}

	@Override
	public float provideTorque(World world, BlockPos outputPos, EnumFacing output, boolean sim) {
		float amo = this.getCurrentTorque();
		if (canProvideTorque(world, outputPos, output)) {
			ITorqueReceiver target = (ITorqueReceiver) world.getTileEntity(outputPos);
			float ret = target.receiveTorque(amo, output, sim);
			return ret;
		}
		return 0;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return !isOutputSide(side);
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side == getOutputSide();
	}

}
