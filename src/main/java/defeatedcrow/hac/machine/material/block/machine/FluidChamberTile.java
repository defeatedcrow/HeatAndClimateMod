package defeatedcrow.hac.machine.material.block.machine;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.FuelTypeDC;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.network.packet.message.MsgTileFluidToC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.client.gui.FluidChamberMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCTank;
import defeatedcrow.hac.machine.material.fluid.IFluidTankTileDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class FluidChamberTile extends HeatSourceTile implements IFluidTankTileDC {

	public FluidChamberTile(BlockPos pos, BlockState state) {
		super(MachineInit.FUEL_BURNER_TILE.get(), pos, state);
	}

	public FluidChamberTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return FluidChamberTile.this.currentProgress;
			case 1:
				return FluidChamberTile.this.totalProgress;
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				FluidChamberTile.this.currentProgress = data;
				break;
			case 1:
				FluidChamberTile.this.totalProgress = data;
				break;
			}

		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	int count = 4;
	private int lastHash = 0;

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = 4;

			boolean flag = false;
			if (!DCUtil.isEmpty(this.inventory.getItem(0)) && !this.inventory.isMaxStack(1)) {
				ItemStack copy = this.inventory.getItem(0).copy();
				copy.setCount(1);
				flag = FluidUtil.getFluidHandler(copy)
						.map(handler -> {
							FluidStack fluid = handler.getFluidInTank(0);
							if (fluid.isEmpty() || tank.isFull()) {
								int space = Math.min(tank.getFluidAmount(), handler.getTankCapacity(0));
								int d = handler.fill(tank.drain(space, FluidAction.SIMULATE), FluidAction.EXECUTE);
								if (d > 0 && inventory.canInsertResult(handler.getContainer(), 1, 1) != 0) {
									// drain
									tank.drain(d, FluidAction.EXECUTE);
									ItemStack ret = handler.getContainer().copy();
									if (!ret.isEmpty()) {
										ret.setCount(1);
										inventory.incrStackInSlot(1, ret);
									}
									inventory.removeItem(0, 1);
									return true;
								}
							} else if (handler.isFluidValid(getTankCap(), fluid)) {
								FluidStack drain = handler.drain(fluid, FluidAction.SIMULATE);
								int f = tank.fill(drain, FluidAction.SIMULATE);
								if (f > 0 && inventory.canInsertResult(handler.getContainer(), 1, 1) != 0) {
									// fill
									drain.setAmount(f);
									tank.fill(drain, FluidAction.EXECUTE);
									handler.drain(drain, FluidAction.EXECUTE);
									ItemStack ret = handler.getContainer().copy();
									if (!ret.isEmpty()) {
										ret.setCount(1);
										inventory.incrStackInSlot(1, ret);
									}
									inventory.removeItem(0, 1);
									return true;
								}
							}
							return false;
						}).orElse(false);
			}

			int hash = tank.getFluid().hashCode();
			if (lastHash != hash) {
				lastHash = hash;
				flag = true;
			}

			if (flag && level instanceof ServerLevel) {
				this.setChanged(level, pos, state);
				NonNullList<FluidStack> list = NonNullList.withSize(3, FluidStack.EMPTY);
				list.set(0, tank.getFluid());
				MsgTileFluidToC.sendToClient((ServerLevel) level, pos, list);
			}
		}
		return super.onTickProcess(level, pos, state);
	}

	@Override
	public int getContainerSize() {
		return 2;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 0 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 1 };
	}

	@Override
	protected int[] getSideSlots() {
		return new int[] { 0, 1 };
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new FluidChamberMenu(MachineInit.FLUID_CHAMBER_MENU.get(), i, inv, this, this.dataAccess);
	}

	@Override
	public boolean isInProcess() {
		return this.totalProgress > 0;
	}

	@Override
	public boolean continueProcess(Level level, BlockPos pos, BlockState state) {
		if (DCState.getBool(state, ProcessTileBlock.WATERLOGGED))
			return false;
		else if (level.isRainingAt(pos))
			return false;
		return true;
	}

	@Override
	public boolean finishProcess(Level level, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean consumeInputs() {
		this.totalProgress = 0;
		this.currentProgress = 0;
		this.lastProgress = 0;
		return true;
	}

	@Override
	public boolean resetProcess() {
		this.totalProgress = 0;
		this.currentProgress = 0;
		this.lastProgress = 0;
		return true;
	}

	@Override
	public boolean startProcess(Level level, BlockPos pos, BlockState state) {
		// 灰を排出できるか
		if (!isInProcess()) {
			FluidStack inp = tank.getFluid();
			int fuel = getFuel(inp);
			if (fuel > 0) {
				// chamberはスタート時に燃料を消費する
				tank.drain(1, FluidAction.EXECUTE);
				this.totalProgress += fuel;
				this.setChanged();
				return true;
			}
		}
		return false;
	}

	@Override
	public DCHeatTier baseHeatTier() {
		return DCHeatTier.SMELTING;
	}

	protected int getFuel(FluidStack item) {
		return DCRecipes.getFluidFuelBurnTime(FuelTypeDC.FLUID, item);
	}

	// fluid

	public int getTankCap() {
		return 4000;
	}

	public DCTank tank = new DCTank(getTankCap());

	@Override
	public int getTanks() {
		return 1;
	}

	@Override
	public DCTank getTank(int id) {
		return tank;
	}

	@Override
	public DCTank getTank(Direction dir) {
		return tank;
	}

	// nbt

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		if (tag.contains(TagKeyDC.getTankKey(1), 10)) {
			CompoundTag tankTag = tag.getCompound(TagKeyDC.getTankKey(1));
			tank.readFromNBT(tankTag);
		}
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		CompoundTag tankTag = new CompoundTag();
		tank.writeToNBT(tankTag);
		tag.put(TagKeyDC.getTankKey(1), tankTag);
	}

	// cap

	LazyOptional<? extends IFluidHandler> fluidhandler = LazyOptional.of(() -> tank);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && capability == ForgeCapabilities.FLUID_HANDLER) {
			return fluidhandler.cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		fluidhandler.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		this.fluidhandler = LazyOptional.of(() -> tank);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.chamber.with_owner", this.ownerName) : Component.translatable("dcs.container.chamber");
	}

}
