package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageOscillator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class TileOscillator extends TileTorqueBase implements ITorqueReceiver {

	public int prevProgress = 0;
	public int currentProgress = 0;
	public static final int MAX_PROGRESS = 128;
	public int power = 0;

	@Override
	public void updateTile() {
		super.updateTile();

		if (!world.isRemote) {

			prevProgress = currentProgress;
			currentProgress += MathHelper.floor(prevTorque);
			if (currentProgress >= MAX_PROGRESS) {
				power++;
				if (power > 15) {
					power = 0;
				}

				// 要パケット同期
				DCMainPacket.INSTANCE.sendToAll(new MessageOscillator(pos, power));
				world.markBlockRangeForRenderUpdate(pos, pos);
				world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.1F, 2.0F);
				world.notifyNeighborsOfStateChange(pos, MachineInit.oscillator, false);
			}

			int ret = currentProgress % MAX_PROGRESS;
			currentProgress = ret;

		}
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return !isInputSide(side);
	}

	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 64.0F;
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque())
			return false;
		return this.isInputSide(side.getOpposite());
	}

	@Override
	public float receiveTorque(float amount, EnumFacing side, boolean sim) {
		float f = maxTorque() - currentTorque;
		float ret = Math.min(amount, f);
		if (!sim) {
			currentTorque += ret;
		}
		return ret;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.currentProgress = tag.getInteger("BurnTime");
		this.power = tag.getInteger("Power");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentProgress);
		tag.setInteger("Power", this.power);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentProgress);
		tag.setInteger("Power", this.power);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		this.currentProgress = tag.getInteger("BurnTime");
		this.power = tag.getInteger("Power");
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
