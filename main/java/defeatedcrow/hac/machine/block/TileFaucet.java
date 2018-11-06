package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.DCTileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TileFaucet extends DCTileEntity {

	@Override
	protected void onServerUpdate() {
		super.onServerUpdate();
		if (!world.isRemote) {
			// 液体を流す
			IBlockState state = world.getBlockState(getPos());
			if (!DCState.getBool(state, DCState.POWERED))
				return;
			else {
				EnumFacing face = DCState.getSide(state, DCState.SIDE).face;
				TileEntity fromTE = world.getTileEntity(getPos().offset(face));
				if (fromTE == null
						|| !fromTE.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face.getOpposite())) {
					fromTE = world.getTileEntity(getPos().offset(face, 2));
				}
				TileEntity toTE = world.getTileEntity(getPos().down());
				if (fromTE != null && toTE != null
						&& fromTE.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face.getOpposite())
						&& toTE.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
					IFluidHandler intank = fromTE.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
							face.getOpposite());
					IFluidHandler outtank = toTE.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
							EnumFacing.UP);
					if (intank != null && outtank != null) {

						int limit = 200; // 200mBまでしか送らない

						// 引き出せる量
						FluidStack get = intank.drain(limit, false);
						if (get == null || get.getFluid() == null || get.amount == 0)
							return;

						int ret = outtank.fill(get, false);
						if (ret > 0) {
							intank.drain(ret, true);
							outtank.fill(get, true);
						}
					}
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

}
