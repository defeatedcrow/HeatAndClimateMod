package defeatedcrow.hac.main.block.device;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimateTileEntity;
import defeatedcrow.hac.core.base.ClimateReceiveTile;
import defeatedcrow.hac.core.base.ClimateReceiverLockable;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageColorID;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileKitchenHood extends DCTileEntity implements IClimateTileEntity, IColorChangeTile {

	@Override
	public void updateTile() {
		super.updateTile();
		if (!world.isRemote) {
			// 下方向に通気FLOWを与える
			IBlockState state = world.getBlockState(getPos());
			for (int i = 1; i < 6; i++) {
				BlockPos target = this.getPos().offset(EnumFacing.DOWN, i);
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
		this.color = tag.getInteger("Color");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("Color", this.color);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		this.color = tag.getInteger("Color");
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		tag.setInteger("Color", this.color);
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
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public DCHeatTier getHeatTier(BlockPos target) {
		return DCHeatTier.NORMAL;
	}

	@Override
	public DCHumidity getHumidity(BlockPos target) {
		return DCHumidity.NORMAL;
	}

	@Override
	public DCAirflow getAirflow(BlockPos target) {
		return DCAirflow.WIND;
	}

	@Override
	public boolean isActive() {
		return true;
	}

	// color

	protected int color = 0;

	@Override
	public int getColor() {
		return color;
	}

	@Override
	public void setColor(int num) {
		color = num;
		if (color < 0 || color > 3) {
			color = 0;
		}
	}

	@Override
	public boolean rotateColor() {
		int c = color + 1;
		setColor(c);
		if (!world.isRemote) {
			DCMainPacket.INSTANCE.sendToAll(new MessageColorID(pos, color));
		}
		return true;
	}

}
