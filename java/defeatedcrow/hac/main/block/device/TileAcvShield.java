package defeatedcrow.hac.main.block.device;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.DCLogger;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileAcvShield extends TileEntity {

	private String owner = "NONE";

	public void setOwner(String player) {
		if (player != null) {
			DCLogger.debugLog("tag tile");
			DCLogger.debugLog("name tile: " + player);
			owner = player.toString();
		}
	}

	public String getOwnerName() {
		return owner;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		owner = tag.getString("owner");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		if (owner != null) {
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			tag.setString("owner", owner);
		}
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

}
