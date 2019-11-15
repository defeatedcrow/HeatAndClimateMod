package defeatedcrow.hac.main.block.build;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileBedDC extends TileEntity {

	public BlockPos sleep = BlockPos.ORIGIN;
	public BlockPos lastPos = BlockPos.ORIGIN;

	public BlockPos getSleepPos() {
		return sleep;
	}

	public void setSleepPos(BlockPos p) {
		sleep = p;
	}

	public BlockPos getRespawnPos() {
		return lastPos;
	}

	public void setRespawnPos(BlockPos p) {
		lastPos = p;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		int x = tag.getInteger("posX");
		int y = tag.getInteger("posY");
		int z = tag.getInteger("posZ");
		sleep = new BlockPos(x, y, z);

		int x2 = tag.getInteger("2posX");
		int y2 = tag.getInteger("2posY");
		int z2 = tag.getInteger("2posZ");
		lastPos = new BlockPos(x2, y2, z2);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// アイテムの書き込み
		if (sleep == null) {
			sleep = BlockPos.ORIGIN;
		}
		tag.setInteger("posX", pos.getX());
		tag.setInteger("posY", pos.getY());
		tag.setInteger("posZ", pos.getZ());

		if (lastPos == null) {
			lastPos = BlockPos.ORIGIN;
		}
		tag.setInteger("2posX", lastPos.getX());
		tag.setInteger("2posY", lastPos.getY());
		tag.setInteger("2posZ", lastPos.getZ());
		return tag;
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
