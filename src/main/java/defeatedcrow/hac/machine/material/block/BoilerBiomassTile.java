package defeatedcrow.hac.machine.material.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.machine.IPowerSource;
import defeatedcrow.hac.api.recipe.FuelTypeDC;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.network.packet.message.MsgTileFluidToC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.client.gui.BoilerBiomassMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCTank;
import defeatedcrow.hac.machine.material.fluid.IFluidTankTileDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
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

public class BoilerBiomassTile extends HeatSourceTile implements IPowerSource, IFluidTankTileDC {

	public BoilerBiomassTile(BlockPos pos, BlockState state) {
		super(MachineInit.BOILER_BIOMASS_TILE.get(), pos, state);
	}

	public BoilerBiomassTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return BoilerBiomassTile.this.currentProgress;
			case 1:
				return BoilerBiomassTile.this.totalProgress;
			case 2:
				return BoilerBiomassTile.this.lastPow;
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				BoilerBiomassTile.this.currentProgress = data;
				break;
			case 1:
				BoilerBiomassTile.this.totalProgress = data;
			case 2:
				BoilerBiomassTile.this.lastPow = data;
				break;
			}

		}

		@Override
		public int getCount() {
			return 3;
		}
	};

	@Override
	public DCHeatTier baseHeatTier() {
		return DCHeatTier.OVEN;
	}

	int count = 5;
	private int lastHash = 0;
	private int lastPow = 0;
	private int power = 0;

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		super.onTickProcess(level, pos, state);

		int pow = 0;
		lastPow = 0;
		if (this.currentProgress > 0 && this.isActive(level, pos, state) && tank.getFluidAmount() > 0) {
			if (!tank.getFluid().isEmpty() && tank.getFluid().getRawFluid().is(FluidTags.WATER) && !tank.drain(1, FluidAction.EXECUTE).isEmpty()) {
				int tier = output.getTier() - 1;
				pow = tier * 2;
				if (pow > 32)
					pow = 32;
			}
		}
		power = pow;
		lastPow = pow;

		if (count > 0) {
			count--;
			return false;
		} else {
			count = 5;
			// fluid
			boolean flag = false;
			if (!DCUtil.isEmpty(this.inventory.getItem(7)) && !this.inventory.isMaxStack(8)) {
				ItemStack copy = this.inventory.getItem(7).copy();
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
									inventory.incrStackInSlot(8, ret);
								}
								inventory.removeItem(7, 1);
								return true;
							}
						} else if (handler.isFluidValid(8000, fluid)) {
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
									inventory.incrStackInSlot(8, ret);
								}
								inventory.removeItem(7, 1);
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
		return false;
	}

	@Override
	public int getSteam() {
		return power;
	}

	@Override
	public int consumeSteam(int i) {
		int ret = Math.min(i, power);
		power -= ret;
		return ret;
	}

	@Override
	public int getContainerSize() {
		return 8;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 0, 1, 2, 3, 4, 5, 7 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 6, 8 };
	}

	@Override
	protected int[] getSideSlots() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	}

	// fluid

	public DCTank tank = new DCTank(8000);

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
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new BoilerBiomassMenu(MachineInit.BOILER_BIOMASS_MENU.get(), i, inv, this, this.dataAccess);
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
		if (!isInProcess() && this.getInventory().canIncrSlot(6, new ItemStack(CoreInit.DUST_ASH.get())) > 0) {
			for (int i = 0; i < 6; i++) {
				ItemStack inp = this.getInventory().getItem(i);
				int fuel = getFuel(inp);
				if (fuel > 0) {
					// chamberはスタート時に燃料を消費する
					this.getInventory().getItem(i).split(1);
					this.getInventory().incrStackInSlot(6, new ItemStack(CoreInit.DUST_ASH.get()));
					this.totalProgress += fuel;
					this.setChanged();
					return true;
				}
			}
		}
		return false;
	}

	protected int getFuel(ItemStack item) {
		return DCRecipes.getFuelBurnTime(FuelTypeDC.BIOMASS, item);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.boiler_biomass.with_owner", this.ownerName) : Component.translatable("dcs.container.boiler_biomass");
	}

}
