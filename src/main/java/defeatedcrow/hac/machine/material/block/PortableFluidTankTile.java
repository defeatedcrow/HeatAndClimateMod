package defeatedcrow.hac.machine.material.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.network.packet.message.MsgTileFluidToC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.fluid.DCTank;
import defeatedcrow.hac.machine.material.fluid.IFluidTankTileDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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

public abstract class PortableFluidTankTile extends ProcessTileBaseDC implements IFluidTankTileDC {

	public PortableFluidTankTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	public abstract int getTankCap();

	public DCTank tank = new DCTank(getTankCap());

	/* processはスロットの液体容器処理 */

	@Override
	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		return false;
	}

	@Override
	public boolean isInProcess() {
		return false;
	}

	@Override
	public boolean continueProcess(Level level, BlockPos pos, BlockState state) {
		return false;
	}

	@Override
	public boolean finishProcess(Level level, BlockPos pos, BlockState state) {
		return false;
	}

	@Override
	public boolean consumeInputs() {
		return false;
	}

	@Override
	public boolean resetProcess() {
		return false;
	}

	@Override
	public boolean startProcess(Level level, BlockPos pos, BlockState state) {
		return false;
	}

	int count = 5;
	private int lastHash = 0;

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
			return false;
		} else {
			count = 5;

			boolean flag = false;
			if (!DCUtil.isEmpty(this.inventory.getItem(0)) && !this.inventory.isMaxStack(1)) {
				ItemStack copy = this.inventory.getItem(0).copy();
				copy.setCount(1);
				flag = FluidUtil.getFluidHandler(copy)
					.map(handler -> {
						FluidStack fluid = handler.getFluidInTank(0);
						if (fluid.isEmpty()) {
							int space = Math.min(tank.getSpace(), handler.getTankCapacity(0));
							int d = handler.fill(tank.drain(space, FluidAction.SIMULATE), FluidAction.EXECUTE);
							if (d > 0 && inventory.canInsertResult(handler.getContainer(), 1, 2) != 0) {
								// drain
								tank.drain(d, FluidAction.EXECUTE);
								ItemStack ret = handler.getContainer();
								if (!ret.isEmpty()) {
									ret.setCount(1);
									inventory.incrStackInSlot(1, ret);
								}
								inventory.removeItem(0, 1);
								return true;
							}
						} else if (handler.isFluidValid(getTankCap(), fluid)) {
							FluidStack drain = handler.drain(fluid, FluidAction.EXECUTE);
							int f = tank.fill(drain, FluidAction.SIMULATE);
							if (f > 0 && inventory.canInsertResult(handler.getContainer(), 1, 2) != 0) {
								// fill
								tank.fill(drain, FluidAction.EXECUTE);
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

			return flag;
		}

	}

	// sided inv
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

	// fluid

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
		return this.hasOwner() ? Component.translatable("dcs.container.tank.with_owner", this.ownerName) : Component.translatable("dcs.container.tank");
	}

}
