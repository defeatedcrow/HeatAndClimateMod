package defeatedcrow.hac.main.block.device;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimateTileEntity;
import defeatedcrow.hac.core.base.ClimateReceiveTile;
import defeatedcrow.hac.core.base.ClimateReceiverLockable;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TileGeyser extends DCTileEntity implements IClimateTileEntity {

	@Override
	public void updateTile() {
		super.updateTile();
		if (!world.isRemote) {
			// 蒸気がランダムに出てくる
			IBlockState state = world.getBlockState(getPos());
			EnumSide side = DCState.getSide(state, DCState.SIDE);
			if (side == null)
				side = EnumSide.UP;
			TileEntity toTE = world.getTileEntity(getPos().offset(side.face));
			if (toTE != null && toTE.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.face
					.getOpposite())) {
				IFluidHandler outtank = toTE.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.face
						.getOpposite());
				if (outtank != null) {
					int limit = 20 * world.rand.nextInt(6); // 0 ~ 100mBずつ
					// 引き出せる量
					FluidStack get = new FluidStack(WorldGenConfig.geyserProduct, limit);
					int ret = outtank.fill(get, false);
					if (ret > 0) {
						outtank.fill(get, true);
					}
				}
			}

			for (int i = 1; i < 6; i++) {
				BlockPos target = this.getPos().offset(side.getFacing(), i);
				TileEntity targetTE = world.getTileEntity(target);
				if (targetTE != null && targetTE instanceof ClimateReceiveTile) {
					if (!((ClimateReceiveTile) targetTE).getHeatTilePos().contains(getPos())) {
						((ClimateReceiveTile) targetTE).addHeatTilePos(getPos());
					}
					break;
				} else if (targetTE != null && targetTE instanceof ClimateReceiverLockable) {
					if (!((ClimateReceiverLockable) targetTE).getHeatTilePos().contains(getPos())) {
						((ClimateReceiverLockable) targetTE).addHeatTilePos(getPos());
					}
					break;
				} else if (world.getBlockState(target).isFullBlock()) {
					break;
				}
			}
		}
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new SPacketUpdateTileEntity(pos, -50, nbtTagCompound);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public DCHeatTier getHeatTier(BlockPos target) {
		return DCHeatTier.OVEN;
	}

	@Override
	public DCHumidity getHumidity(BlockPos target) {
		return ClimateAPI.calculator.getHumidity(world, getPos());
	}

	@Override
	public DCAirflow getAirflow(BlockPos target) {
		return DCAirflow.WIND;
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
