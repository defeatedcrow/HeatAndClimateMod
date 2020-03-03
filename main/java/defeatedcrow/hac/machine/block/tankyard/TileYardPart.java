package defeatedcrow.hac.machine.block.tankyard;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileYardPart extends TileEntity {

	public BlockPos parent = null;

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (parent == null)
				return false;
			TileEntity te = world.getTileEntity(parent);
			if (te instanceof TileTankYard) {
				TileTankYard yard = (TileTankYard) te;
				return yard.multi;
			}
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && parent != null) {
			TileEntity te = world.getTileEntity(parent);
			if (te instanceof TileTankYard) {
				TileTankYard yard = (TileTankYard) te;
				if (yard.multi) {
					return (T) yard.getTank();
				}
			}
		}
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
		this.setNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		this.getNBT(tag);
		return tag;
	}

	public NBTTagCompound getNBT(NBTTagCompound tag) {
		tag.setInteger("parent_x", parent == null ? 0 : parent.getX());
		tag.setInteger("parent_y", parent == null ? 0 : parent.getY());
		tag.setInteger("parent_z", parent == null ? 0 : parent.getZ());
		return tag;
	}

	public void setNBT(NBTTagCompound tag) {
		int x = tag.getInteger("parent_x");
		int y = tag.getInteger("parent_y");
		int z = tag.getInteger("parent_z");
		parent = new BlockPos(x, y, z);
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

}
