package defeatedcrow.hac.main.block.device;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.base.ITagGetter;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import defeatedcrow.hac.main.api.ISidedTankChecker;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileCookingStove extends DCTileEntity implements ITagGetter, IInventory, ISidedTankChecker {

	public DCTank inputT = new DCTank(5000);

	protected int currentBurnTime = 0;
	protected int maxBurnTime = 1;
	protected int currentClimate = DCHeatTier.SMELTING.getID();

	@Override
	public void updateTile() {
		if (!getWorld().isRemote) {

			this.checkSideTank();

			if (this.currentBurnTime == 0) {
				FluidStack f = inputT.getFluid();
				if (f != null && f.getFluid() != null && inputT.getFluidAmount() > 0) {
					int i = getBurnTime(f.getFluid());
					if (i > 0) {
						this.currentBurnTime = i;
						this.maxBurnTime = i;
						inputT.drain(1, true);
						this.markDirty();
					}
				}
			}

			if (BlockCookingStove.isLit(getWorld(), getPos()) != this.isActive()) {
				BlockCookingStove.changeLitState(getWorld(), getPos(), isActive());
			}
		}
		super.updateTile();
	}

	/* 隣接tankから燃料液体を吸い取る */
	@Override
	public void checkSideTank() {
		for (EnumFacing face : EnumFacing.HORIZONTALS) {
			int cap = inputT.getCapacity();
			int amo = inputT.getFluidAmount();
			int mov = 50; // 50mBずつ

			if (amo >= cap) {
				break;
			}

			TileEntity tile = world.getTileEntity(getPos().offset(face));
			if (tile != null && !(tile instanceof ISidedTankChecker) && tile.hasCapability(
					CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face.getOpposite())) {
				IFluidHandler tank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
						face.getOpposite());
				if (tank != null && tank.getTankProperties() != null && tank.getTankProperties().length > 0) {
					FluidStack target = tank.getTankProperties()[0].getContents();
					if (target != null && target.getFluid() != null && getBurnTime(target.getFluid()) > 0) {
						int i = Math.min(mov, cap - amo);
						FluidStack ret = tank.drain(i, false);
						int fill = inputT.fill(ret, false);
						if (fill > 0) {
							ret = tank.drain(fill, true);
							inputT.fill(ret, true);
							tile.markDirty();
							this.markDirty();
							break;
						}
					}
				}
			}
		}
	}

	public boolean isActive() {
		return this.currentBurnTime > 0;
	}

	public int getCurrentBurnTime() {
		return this.currentBurnTime;
	}

	public int getMaxBurnTime() {
		return this.maxBurnTime;
	}

	public int getCurrentHeatID() {
		return this.currentClimate;
	}

	public void setCurrentBurnTime(int i) {
		this.currentBurnTime = i;
	}

	public void setMaxBurnTime(int i) {
		this.maxBurnTime = i;
	}

	public void setCurrentHeatID(int i) {
		this.currentClimate = i;
	}

	public DCHeatTier getCurrentHeatTier() {
		return DCHeatTier.getTypeByID(currentClimate);
	}

	/* 燃焼判定 */

	public static int getBurnTime(Fluid fluid) {
		int burn = MainAPIManager.fuelRegister.getBurningTime(fluid);
		return burn;
	}

	/* NBT, Packet */

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return false;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) inputT;
		else if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return null;
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

		this.currentBurnTime = tag.getInteger("BurnTime");
		this.maxBurnTime = tag.getInteger("MaxTime");
		this.currentClimate = tag.getByte("Climate");

		inputT = inputT.readFromNBT(tag, "Tank");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setInteger("BurnTime", this.currentBurnTime);
		tag.setInteger("MaxTime", this.maxBurnTime);
		tag.setByte("Climate", (byte) this.currentClimate);

		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);

		tag.setInteger("BurnTime", this.currentBurnTime);
		tag.setInteger("MaxTime", this.maxBurnTime);
		tag.setByte("Climate", (byte) this.currentClimate);

		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		this.currentBurnTime = tag.getInteger("BurnTime");
		this.maxBurnTime = tag.getInteger("MaxTime");
		this.currentClimate = tag.getByte("Climate");

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

	/* fieldがIInventoryにしかないとかいうクソ仕様のため、GUI表示用にダミーInventoryをつける。インベントリは実際無い */

	@Override
	public String getName() {
		return "dcs.gui.device.fuel_stove";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (getWorld().getTileEntity(this.pos) != this || player == null)
			return false;
		else
			return Math.sqrt(player.getDistanceSq(pos)) < 256D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.currentBurnTime;
		case 1:
			return this.maxBurnTime;
		case 2:
			return this.currentClimate;
		case 3:
			return this.inputT.getFluidType() == null ? -1 : FluidIDRegisterDC.getID(inputT.getFluidType());
		case 4:
			return this.inputT.getFluidAmount();
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.currentBurnTime = value;
			break;
		case 1:
			this.maxBurnTime = value;
			break;
		case 2:
			this.currentClimate = value;
			break;
		case 3:
			inputT.setFluidById(value);
			break;
		case 4:
			this.inputT.setAmount(value);
			break;
		default:
			return;
		}
	}

	@Override
	public int getFieldCount() {
		return 5;
	}

	@Override
	public void clear() {}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
