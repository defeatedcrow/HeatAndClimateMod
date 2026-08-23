package defeatedcrow.hac.machine.material.block.machine;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.machine.IFluidPipe;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.machine.energy.SidedEnergyReceiver;
import defeatedcrow.hac.machine.energy.SidedEnergyTankDC;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import defeatedcrow.hac.machine.material.fluid.DCHeadTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class VolutePumpTile extends EnergyMachineBaseDC {

	public VolutePumpTile(BlockPos pos, BlockState state) {
		super(MachineInit.VOLUTE_PUMP_TILE.get(), pos, state);
	}

	@Override
	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		return !DCState.getBool(state, DCState.POWERED);
	}

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (this.isActive(level, pos, state)) {
			// 隣接マシンからの吸い上げ (上面以外)
			for (Direction dir : Direction.values()) {
				if (tank.isFull())
					break;
				if (dir == Direction.UP)
					continue;
				BlockPos p2 = pos.relative(dir);
				BlockEntity targetTile = level.getBlockEntity(p2);
				if (targetTile != null) {
					targetTile.getCapability(ForgeCapabilities.FLUID_HANDLER, dir.getOpposite())
					    .ifPresent(handler -> {
						    FluidStack drain = handler.drain(1000, FluidAction.SIMULATE);
						    if (isPumpableWater(drain) && (tank.isEmpty() || DCFluidUtil.isSameFluid(drain, tank.getFluid()))) {
							    int ret = tank.fill(drain, FluidAction.SIMULATE);
							    if (ret > 0) {
								    drain.setAmount(ret);
								    // default head: 32block
								    float h = 32F + DCFluidUtil.getHead(drain);
								    // viscosity
								    float f2 = (2000F - drain.getFluid()
								        .getFluidType()
								        .getViscosity(drain)) / 1000F;
								    if (f2 < 0.25F)
									    f2 = 0F;
								    if (f2 > 4F)
									    f2 = 4F;
								    h *= f2;
								    int head = Mth.ceil(h);
								    if (head > 0) {
									    DCFluidUtil.addHead(drain, head);
									    handler.drain(drain, FluidAction.EXECUTE);
									    ret = tank.fill(drain, FluidAction.EXECUTE);
								    }
							    }
						    }
					    });
				}
			}

			BlockEntity target = getLevel().getBlockEntity(getBlockPos().above());
			boolean flag = false;
			// 方向偽装
			if (getEnergyHandler().getEnergyStored() >= 32 && target != null && target.getCapability(ForgeCapabilities.FLUID_HANDLER)
			    .isPresent()) {
				if (!tank.isEmpty()) {
					flag = target.getCapability(ForgeCapabilities.FLUID_HANDLER, Direction.UP)
					    .filter(handler -> !(handler instanceof IFluidPipe))
					    .map(handler -> {
						    FluidStack drain = tank.getFluid()
						        .copy();
						    if (drain.getAmount() > 1000) {
							    drain.setAmount(1000);
						    }
						    int ret = handler.fill(drain, FluidAction.SIMULATE);
						    if (ret > 0) {
							    drain.setAmount(ret);
							    int consume = handler.fill(drain, FluidAction.EXECUTE);
							    tank.drain(consume, FluidAction.EXECUTE);
							    getEnergyHandler().consumeEnergy(32);
							    return true;
						    }
						    return false;
					    })
					    .orElse(false);

					if (!flag) {
						flag = target.getCapability(ForgeCapabilities.FLUID_HANDLER, Direction.DOWN)
						    .filter(IFluidPipe.class::isInstance)
						    .map(handler -> {
							    IFluidPipe pipe = (DCHeadTank) handler;
							    FluidStack drain = tank.getFluid()
							        .copy();
							    if (drain.getAmount() > 1000) {
								    drain.setAmount(1000);
							    }
							    int ret = pipe.fill(drain, FluidAction.SIMULATE, Direction.DOWN);
							    if (ret > 0) {
								    drain.setAmount(ret);
								    int consume = handler.fill(drain, FluidAction.EXECUTE);
								    tank.drain(consume, FluidAction.EXECUTE);
								    getEnergyHandler().consumeEnergy(32);
								    return true;
							    }
							    return false;
						    })
						    .orElse(false);
					}
				}

			}

			boolean on = DCState.getBool(getBlockState(), DCState.FLAG);
			if (flag != on) {
				VolutePumpBlock.changePowerState(level, pos, flag);
			}
		}
		return super.onTickProcess(level, pos, state);

	}

	private static boolean isPumpableWater(FluidStack fluid) {
		return !fluid.isEmpty();
	}

	public SidedEnergyTankDC battery = new SidedEnergyReceiver(this, getMaxEnergy(), 128);

	protected int getMaxEnergy() {
		return 1000;
	}

	@Override
	public SidedEnergyTankDC getEnergyHandler() {
		return battery;
	}

	public PumpTank tank = new PumpTank(4000, 1000);

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

	LazyOptional<? extends IFluidPipe> fluidhandler = LazyOptional.of(() -> tank);

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
		return null;
	}

	@Override
	public boolean hasMenu() {
		return false;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.machine.with_owner", this.ownerName) : Component.translatable("dcs.container.machine");
	}

	public class PumpTank extends DCHeadTank {
		protected final NonNullList<FaceIO> pumpFaces;

		protected PumpTank(int cap, int flow) {
			super(cap, flow);
			pumpFaces = NonNullList.of(FaceIO.OUTPUT, FaceIO.INPUT, FaceIO.INPUT, FaceIO.INPUT, FaceIO.INPUT, FaceIO.INPUT);
		}

		@Override
		public FaceIO getFace(Direction dir) {
			int i = dir.get3DDataValue();
			if (i >= 0 && i < 6) {
				if (pumpFaces.size() <= i) {
					return FaceIO.NONE;
				}
				return pumpFaces.get(i);
			}
			return FaceIO.NONE;
		}

		@Override
		public int fill(FluidStack get, FluidAction action, Direction from) {
			if (from == Direction.UP) {
				return 0;
			}
			return super.fill(get, action, from);
		}

		@Override
		public @NotNull FluidStack drain(FluidStack resource, FluidAction action, Direction to) {
			if (to != Direction.UP) {
				return FluidStack.EMPTY;
			}
			return super.drain(resource, action, to);
		}

		@Override
		public NonNullList<FaceIO> getFaces() {
			return pumpFaces;
		}
	}

}
