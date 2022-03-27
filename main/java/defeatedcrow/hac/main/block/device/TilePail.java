package defeatedcrow.hac.main.block.device;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.base.ITagGetter;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageSingleTank;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TilePail extends DCTileEntity implements ITagGetter,
		IColorChangeTile {

	public DCTank inputT = new DCTank(18000);

	private int lastInT = 0;
	private int count = 20;

	@Override
	protected void onServerUpdate() {
		if (count > 0) {
			count--;
		} else {
			boolean flag = false;
			if (inputT.getFluidIdName().hashCode() + inputT.getFluidAmount() != lastInT) {
				flag = true;
				lastInT = inputT.getFluidIdName().hashCode() + inputT.getFluidAmount();
			}

			if (flag) {
				if (!this.hasWorld())
					return;
				DCMainPacket.INSTANCE.sendToAll(new MessageSingleTank(pos, inputT.getFluidIdName(), inputT
						.getFluidAmount()));
			}
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) inputT;
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		inputT = inputT.readFromNBT(tag, "Tank");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);

		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		inputT = inputT.readFromNBT(tag, "Tank");
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
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}

	// color

	@Override
	public int getColor() {
		int color = DCState.getInt(world.getBlockState(getPos()), DCState.TYPE4);
		return color;
	}

	@Override
	public void setColor(int num) {
		if (num < 0 || num > 3) {
			num = 0;
		}
		IBlockState current = world.getBlockState(pos);
		IBlockState next = current.withProperty(DCState.TYPE4, num);
		world.setBlockState(pos, next, 3);
	}

	@Override
	public boolean rotateColor() {
		int c = getColor();
		c++;
		setColor(c);
		return true;
	}

}
