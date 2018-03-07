package defeatedcrow.hac.magic.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.ClimateReceiveTile;
import defeatedcrow.hac.core.base.ITagGetter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TileMaceBase extends ClimateReceiveTile implements ITagGetter {

	private boolean lastActive = false;
	public int currentEnergy = 0;
	public int enchant = 0;

	public void setEnergy(int i) {
		currentEnergy = i;
	}

	public int getEnergy() {
		return currentEnergy;
	}

	public int getEnchant() {
		return enchant;
	}

	public void setEnchant(int i) {
		enchant = i;
	}

	@Override
	public void updateTile() {
		if (getWorld() != null && !getWorld().isRemote) {

			boolean active = this.checkEnvironment() && currentEnergy < 640;

			if (active) {
				currentEnergy++;
			}

			if (BlockMace.isLit(getWorld(), getPos()) != active) {
				BlockMace.changeLitState(getWorld(), getPos(), active);
			}
		}
		super.updateTile();
	}

	protected abstract boolean checkEnvironment();

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.currentEnergy = tag.getInteger("dcs.mace.energy");
		this.enchant = tag.getInteger("dcs.mace.enchant");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("dcs.mace.energy", this.currentEnergy);
		tag.setInteger("dcs.mace.enchant", this.enchant);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		tag.setInteger("dcs.mace.energy", this.currentEnergy);
		tag.setInteger("dcs.mace.enchant", this.enchant);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		this.currentEnergy = tag.getInteger("dcs.mace.energy");
		this.enchant = tag.getInteger("dcs.mace.enchant");
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
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}

}
