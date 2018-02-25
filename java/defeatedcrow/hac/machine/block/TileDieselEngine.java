package defeatedcrow.hac.machine.block;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.energy.ITorqueProvider;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.base.ITagGetter;
import defeatedcrow.hac.core.energy.TileTorqueBase;
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
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileDieselEngine extends TileTorqueBase
		implements ITorqueProvider, ITorqueReceiver, ITagGetter, IInventory, ISidedTankChecker {

	public DCTank inputT = new DCTank(5000);

	protected int currentBurnTime = 0;
	protected int maxBurnTime = 1;
	protected int currentClimate = DCHeatTier.NORMAL.getID();

	@Override
	public float maxTorque() {
		return 512.0F;
	}

	@Override
	public float getGearTier() {
		return 64.0F;
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

	public void setCurrentBurnTime(int i) {
		this.currentBurnTime = i;
	}

	public void setMaxBurnTime(int i) {
		this.maxBurnTime = i;
	}

	@Override
	public void updateTile() {
		currentClimate = ClimateAPI.calculator.getAverageTemp(worldObj, getPos()).getID();

		if (!getWorld().isRemote) {

			this.checkSideTank();

			IBlockState state = worldObj.getBlockState(pos);
			if (!DCState.getBool(state, DCState.POWERED) && isSuitableClimate()) {

				if (this.currentBurnTime == 0) {
					FluidStack f = inputT.getFluid();
					if (f != null && f.getFluid() != null && inputT.getFluidAmount() >= 10) {
						int i = getBurnTime(f.getFluid());
						if (i > 0) {
							this.currentBurnTime = i;
							this.maxBurnTime = i;
							inputT.drain(10, true);

							this.markDirty();
						}
					}
				} else if (this.currentBurnTime > 0) {
					this.currentBurnTime--;
					this.currentTorque += 128.0F;
					if (currentTorque > maxTorque()) {
						currentTorque = maxTorque();
					}
				} else {
					this.currentBurnTime = 0;
				}
			}

			// provider
			for (EnumFacing side : getOutputSide()) {
				this.provideTorque(worldObj, getPos().offset(side), side, false);
			}
		}

		super.updateTile();
	}

	/* 燃焼判定 */

	public static int getBurnTime(Fluid fluid) {
		int burn = MainAPIManager.fuelRegister.getBurningTime(fluid);
		return burn;
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

			TileEntity tile = worldObj.getTileEntity(getPos().offset(face));
			if (tile != null && !(tile instanceof ISidedTankChecker)
					&& tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face.getOpposite())) {
				IFluidHandler tank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
						face.getOpposite());
				if (tank != null && tank.getTankProperties() != null) {
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

	@Override
	public List<EnumFacing> getOutputSide() {
		List<EnumFacing> ret = Lists.newArrayList();
		ret.add(getBaseSide());
		return ret;
	}

	@Override
	public float getAmount() {
		return this.getCurrentTorque();
	}

	@Override
	public boolean canProvideTorque(World world, BlockPos outputPos, EnumFacing output) {
		TileEntity tile = world.getTileEntity(outputPos);
		float amo = getAmount();
		if (tile != null && tile instanceof ITorqueReceiver && amo > 0F)
			return ((ITorqueReceiver) tile).canReceiveTorque(amo, output.getOpposite());
		return false;
	}

	@Override
	public float provideTorque(World world, BlockPos outputPos, EnumFacing output, boolean sim) {
		float amo = this.getCurrentTorque();
		if (canProvideTorque(world, outputPos, output)) {
			ITorqueReceiver target = (ITorqueReceiver) world.getTileEntity(outputPos);
			float ret = target.receiveTorque(amo, output, sim);
			return ret;
		}
		return 0;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return getOutputSide().contains(side);
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		IBlockState state = worldObj.getBlockState(pos);
		if (DCState.getBool(state, DCState.POWERED))
			return false;
		if (this.currentTorque >= this.maxTorque())
			return false;
		return this.isInputSide(side);
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

	/* Climateチェック */
	public boolean isSuitableClimate() {
		return currentClimate > DCHeatTier.FROSTBITE.getID();
	}

	public List<String> climateSuitableMassage() {
		List<String> list = new ArrayList<String>();
		if (isSuitableClimate()) {
			list.add(I18n.translateToLocal("dcs.gui.message.suitableclimate"));
		} else {
			list.add(I18n.translateToLocal("dcs.gui.message.pottery.toocold"));
		}
		return list;
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

	/* GUI表示用にダミーInventoryをつける。インベントリは実際無い */

	@Override
	public String getName() {
		return "dcs.gui.device.diesel_engine";
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
	public boolean isUseableByPlayer(EntityPlayer player) {
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
			return this.inputT.getFluidType() == null ? -1 : FluidIDRegisterDC.getID(inputT.getFluidType());
		case 3:
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
			inputT.setFluidById(value);
			break;
		case 3:
			this.inputT.setAmount(value);
			break;
		default:
			return;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}

}
