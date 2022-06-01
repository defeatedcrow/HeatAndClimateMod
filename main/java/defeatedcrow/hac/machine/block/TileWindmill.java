package defeatedcrow.hac.machine.block;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.energy.ITorqueProvider;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileWindmill extends TileTorqueBase implements ITorqueProvider {

	@Override
	public void updateTile() {
		if (!world.isRemote) {
			// Airflowチェック
			ClimateSupplier clm = new ClimateSupplier(world, pos);
			DCAirflow air = clm.get().getAirflow();
			float wind = getGearTier() * 0.125F;
			switch (air) {
			case TIGHT:
				wind *= 0F;
				break;
			case NORMAL:
				wind *= 0.5F;
				break;
			case FLOW:
				wind *= 1.0F;
				break;
			case WIND:
				wind *= 2.0F;
				break;
			default:
			}
			if (this.getBaseSide() == DCUtil.getWorldWind(world).getOpposite()) {
				wind *= 2.0F;
			}
			this.currentTorque = wind;

			// provider
			for (EnumFacing side : getOutputSide()) {
				this.provideTorque(world, getPos().offset(side), side, false);
			}
		}
		super.updateTile();
	}

	@Override
	public float getGearTier() {
		return 8.0F;
	}

	@Override
	public List<EnumFacing> getOutputSide() {
		List<EnumFacing> ret = Lists.newArrayList();
		ret.add(getBaseSide());
		return ret;
	}

	@Override
	public float getAmount() {
		return this.getCurrentTorque();
	}

	@Override
	public boolean canProvideTorque(World world, BlockPos outputPos, EnumFacing output) {
		TileEntity tile = world.getTileEntity(outputPos);
		float amo = this.getCurrentTorque();
		if (tile != null && tile instanceof ITorqueReceiver && amo > 0F)
			return ((ITorqueReceiver) tile).canReceiveTorque(amo, output.getOpposite());
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
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return getOutputSide().contains(side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox() {
		net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		bb = new net.minecraft.util.math.AxisAlignedBB(getPos().add(-1, -1, -1), getPos().add(1, 1, 1));
		return bb;
	}

}
