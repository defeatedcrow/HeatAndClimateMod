package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.energy.ITorqueProvider;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
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
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileBoilerTurbine extends TileTorqueBase implements ITorqueProvider, IInventory {

	public DCTank inputT = new DCTank(5000);

	// process
	public int currentBurnTime = 0;
	public int maxBurnTime = -1;
	protected int currentClimate = DCHeatTier.NORMAL.getID();

	private int lastTier = 0;
	private int lastBurn = 0;

	@Override
	public void updateTile() {
		super.updateTile();

		if (!worldObj.isRemote) {
			// 水の取り込み
			this.checkSideTank();

			// HeatTier更新
			DCHeatTier heat = ClimateAPI.calculator.getAverageTemp(worldObj, pos);
			currentClimate = heat.getID();

			// 燃焼処理
			boolean f = false;
			int red = this.getRequiredWater(heat);
			if (red > 0) {
				// 水を減らす
				FluidStack flu = inputT.getContents();
				if (flu != null && flu.getFluid() == FluidRegistry.WATER && inputT.getFluidAmount() > red) {
					inputT.drain(red, true);
					f = true;
				}
			}

			if (f) {
				this.currentBurnTime = 1;
			} else {
				this.currentBurnTime = 0;
			}

			if (currentBurnTime > 0) {
				this.currentTorque += this.getProvideTorque(heat);
			}

			// provider
			this.provideTorque(worldObj, getPos().offset(getOutputSide()), getOutputSide(), false);

			// DCLogger.debugLog("current torque: " + currentTorque);
			// DCLogger.debugLog("sent torque: " + prevTorque);
			// DCLogger.debugLog("current burntime: " + currentBurnTime);
			// DCLogger.debugLog("current temp: " + currentClimate);

			// 外見更新
			// if (BlockBoilerTurbine.isLit(getWorld(), getPos()) != this.isActive()) {
			// BlockBoilerTurbine.changeLitState(getWorld(), getPos(), isActive());
			// }

		}

	}

	@Override
	protected void onServerUpdate() {}

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
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 16.0F;
	}

	/* 燃焼判定 */

	public int getRequiredWater(DCHeatTier tier) {
		switch (tier) {
		case OVEN:
			return 1;
		case KILN:
			return 10;
		case SMELTING:
			return 20;
		case UHT:
			return 50;
		default:
			return 0;
		}
	}

	public int getBurnTime() {
		FluidStack f = inputT.getContents();
		if (f != null && f.getFluid() != null && inputT.getFluidAmount() > 0) {
			if (f.getFluid() == FluidRegistry.WATER) {
				DCHeatTier tier = DCHeatTier.getTypeByID(currentClimate);
				switch (tier) {
				case OVEN:
					return 20;
				case KILN:
					return 4;
				case SMELTING:
					return 2;
				case UHT:
					return 1;
				default:
					return 0;
				}
			}
		}
		return 0;
	}

	public float getProvideTorque(DCHeatTier tier) {
		switch (tier) {
		case OVEN:
			return 8.0F;
		case KILN:
			return 32.0F;
		case SMELTING:
			return 64.0F;
		case UHT:
			return 128.0F;
		default:
			return 0F;
		}
	}

	/* 隣接tankから燃料液体を吸い取る */
	private void checkSideTank() {
		for (EnumFacing face : EnumFacing.HORIZONTALS) {
			int cap = inputT.getCapacity();
			int amo = inputT.getFluidAmount();
			int mov = 50; // 50mBずつ

			if (amo >= cap) {
				break;
			}

			TileEntity tile = worldObj.getTileEntity(getPos().offset(face));
			if (tile != null
					&& tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face.getOpposite())) {
				IFluidHandler tank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
						face.getOpposite());
				if (tank != null && tank.getTankProperties() != null) {
					FluidStack target = tank.getTankProperties()[0].getContents();
					if (target != null && target.getFluid() != null && target.getFluid() == FluidRegistry.WATER) {
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

	protected void onProcess() {

	}

	/* ITorqueProvider */

	@Override
	public boolean isInputSide(EnumFacing side) {
		return false;
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side == getBaseSide();
	}

	@Override
	public EnumFacing getOutputSide() {
		return this.getBaseSide();
	}

	@Override
	public float getAmount() {
		return this.getCurrentTorque();
	}

	@Override
	public boolean canProvideTorque(World world, BlockPos outputPos, EnumFacing output) {
		TileEntity tile = world.getTileEntity(outputPos);
		float amo = this.getCurrentTorque();
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
		if (facing != null && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) inputT;
		else if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
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
		return "dcs.gui.device.boiler_turbine";
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
		return getWorld().getTileEntity(this.pos) != this ? false
				: player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
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

}
