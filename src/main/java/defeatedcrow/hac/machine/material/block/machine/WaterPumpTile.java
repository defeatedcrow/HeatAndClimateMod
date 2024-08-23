package defeatedcrow.hac.machine.material.block.machine;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.machine.IFluidPipe;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
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
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class WaterPumpTile extends EnergyMachineBaseDC {

	public WaterPumpTile(BlockPos pos, BlockState state) {
		super(MachineInit.WATER_PUMP_TILE.get(), pos, state);
	}

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (this.isActive(level, pos, state)) {
			// pumping
			if (!tank.isFull()) {
				FluidState pumpState = state.getFluidState();
				FluidStack fill = FluidStack.EMPTY;
				BlockPos lastPos = getBlockPos();
				int cost = 0;
				// WATER
				if (DCState.getBool(getBlockState(), EntityBlockDC.WATERLOGGED) || pumpState.is(Fluids.WATER) || pumpState.is(Fluids.FLOWING_WATER)) {
					fill = new FluidStack(Fluids.WATER, getEnergyHandler().getEnergyStored()).copy();
					if (fill.getAmount() > 32) {
						// 水のみ吸い上げにエネルギーを必要としない
						fill.setAmount(32);
					}
				} else if (getEnergyHandler().getEnergyStored() >= 100) {
					// LIQUID
					if (isPumpableWater(pumpState)) {
						fill = new FluidStack(pumpState.getType(), 1000);
					} else {
						// Scan
						int count = 16;
						FluidState last = Fluids.EMPTY.defaultFluidState();
						BlockPos p2 = getBlockPos();
						while (count > 0 && fill.isEmpty()) {
							for (Direction dir : Direction.values()) {
								if (dir == Direction.DOWN)
									continue;
								BlockPos p3 = lastPos.relative(dir);
								FluidState check = getLevel().getBlockState(p3).getFluidState();
								if (isPumpableWater(check)) {
									if (check.getAmount() > last.getAmount()) {
										last = check;
										p2 = p3;
									} else if (dir == Direction.UP) {
										last = check;
										p2 = p3;
									}
								}
							}
							if (!last.isEmpty() && last.isSource()) {
								fill = new FluidStack(last.getType(), 1000);
								cost = 100;
							}
							if (lastPos.equals(p2)) {
								break;
							} else {
								lastPos = p2;
							}
							count--;
						}
					}
				}

				if (!fill.isEmpty()) {
					int ret = tank.fill(fill, FluidAction.SIMULATE);
					if (ret == fill.getAmount()) {
						tank.fill(fill, FluidAction.EXECUTE);
						BlockState s2 = getLevel().getBlockState(lastPos);
						if (fill.getFluid() != Fluids.WATER && s2.getBlock() instanceof BucketPickup pickup) {
							pickup.pickupBlock(getLevel(), lastPos, s2);
						}
						getEnergyHandler().consumeEnergy(cost);
					}
				}
			}

			BlockEntity target = getLevel().getBlockEntity(getBlockPos().above());
			// 方向偽装
			if (getEnergyHandler().getEnergyStored() >= 8 && target != null && target.getCapability(ForgeCapabilities.FLUID_HANDLER).isPresent()) {
				if (!tank.isEmpty()) {
					boolean b = target.getCapability(ForgeCapabilities.FLUID_HANDLER, Direction.UP).filter(handler -> !(handler instanceof IFluidPipe)).map((handler) -> {
						FluidStack drain = tank.getFluid().copy();
						if (drain.getAmount() > 1000) {
							drain.setAmount(1000);
						}
						int ret = handler.fill(drain, FluidAction.SIMULATE);
						if (ret > 0) {
							drain.setAmount(ret);
							int consume = handler.fill(drain, FluidAction.EXECUTE);
							tank.drain(consume, FluidAction.EXECUTE);
							getEnergyHandler().consumeEnergy(8);
							return true;
						}
						return false;
					}).orElse(false);

					if (!b) {
						target.getCapability(ForgeCapabilities.FLUID_HANDLER, Direction.DOWN).filter(handler -> handler instanceof IFluidPipe).ifPresent(handler -> {
							IFluidPipe pipe = (DCHeadTank) handler;
							FluidStack drain = tank.getFluid().copy();
							if (drain.getAmount() > 1000) {
								drain.setAmount(1000);
							}
							int ret = pipe.fill(drain, FluidAction.SIMULATE, Direction.DOWN);
							if (ret > 0) {
								// head
								int add = 0;
								// water depth
								for (int i = 0; i < 10; i++) {
									FluidState above = getLevel().getFluidState(getBlockPos().above(i));
									if (above.is(Fluids.WATER)) {
										add++;
									} else {
										break;
									}
								}
								float f1 = 1F - (add * 0.05F);
								float h = add * f1;
								// viscosity
								float f2 = (17000F - drain.getFluid().getFluidType().getViscosity(drain)) / 16000F;
								if (f2 < 0.0625F)
									f2 = 0.0625F;
								if (f2 > 4F)
									f2 = 4F;
								h *= f2;
								int cost = Mth.ceil(10F / f2);
								int head = Mth.ceil(h) + 6;
								if (head > 0) {
									drain.setAmount(ret);
									drain = DCFluidUtil.addHead(drain, head);
									int consume = handler.fill(drain, FluidAction.EXECUTE);
									tank.drain(consume, FluidAction.EXECUTE);
									getEnergyHandler().consumeEnergy(cost);
								}
							}
						});
					}
				}

			}
		}
		return super.onTickProcess(level, pos, state);
	}

	private static boolean isPumpableWater(FluidState fluid) {
		return !fluid.isEmpty() && !fluid.getFluidType().isLighterThanAir();
	}

	public SidedEnergyTankDC battery = new SidedEnergyReceiver(this, getMaxEnergy(), 128);

	protected int getMaxEnergy() {
		return 1000;
	}

	@Override
	public SidedEnergyTankDC getEnergyHandler() {
		return battery;
	}

	public DCHeadTank tank = new DCHeadTank(4000, 1000).setDefaultFace(NonNullList.of(FaceIO.OUTPUT, FaceIO.INPUT, FaceIO.INPUT, FaceIO.INPUT, FaceIO.INPUT, FaceIO.INPUT));

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

}
