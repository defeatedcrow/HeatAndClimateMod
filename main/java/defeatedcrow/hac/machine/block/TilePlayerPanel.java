package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TilePlayerPanel extends TileEntity {

	public int cooltime = 20;

	// pair
	protected BlockPos pairPos = null;

	public BlockPos getPairPos() {
		return pairPos;
	}

	public void setPairPos(BlockPos pos) {
		pairPos = pos;
	}

	public boolean isActive() {
		IBlockState state = this.world.getBlockState(pos);
		if (state != null) {
			return DCState.getInt(state, DCState.TYPE4) > 0;
		}
		return false;
	}

	public boolean isTargetActive() {
		if (targetBlock() != null && DCState.getInt(targetBlock(), DCState.TYPE4) > 0) {
			return true;
		}
		return false;
	}

	IBlockState targetBlock() {
		if (pairPos != null && world.isAreaLoaded(pairPos, 1, true) && world.getBlockState(pairPos) != null) {
			IBlockState state = world.getBlockState(pairPos);
			return state.getBlock() instanceof BlockPlayerPanel ? state : null;
		}
		return null;
	}

	public BlockPos getWarpPos() {
		if (targetBlock() != null) {
			if (world.isAirBlock(pairPos.up())) {
				return pairPos;
			} else {
				for (EnumFacing f : EnumFacing.HORIZONTALS) {
					BlockPos p = pairPos.offset(f);
					if (world.getBlockState(p).isSideSolid(world, p.down(), EnumFacing.UP) && world
							.isAirBlock(p) && world.isAirBlock(p.up())) {
						return p;
					}
				}
			}
		}
		return null;
	}

	// NBT

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		setNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		getNBT(tag);
		return tag;
	}

	public NBTTagCompound getNBT(NBTTagCompound tag) {
		if (pairPos != null) {
			tag.setInteger("pairX", pairPos.getX());
			tag.setInteger("pairY", pairPos.getY());
			tag.setInteger("pairZ", pairPos.getZ());
			tag.setBoolean("dcs.haspair", true);
			tag.setInteger("cool", cooltime);
		}
		return tag;
	}

	public void setNBT(NBTTagCompound tag) {
		if (tag.hasKey("dcs.haspair") && tag.getBoolean("dcs.haspair")) {
			int px = tag.getInteger("pairX");
			int py = tag.getInteger("pairY");
			int pz = tag.getInteger("pairZ");
			BlockPos pos = new BlockPos(px, py, pz);
			pairPos = pos;
			cooltime = tag.getInteger("cool");
		}
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
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}
}
