package defeatedcrow.hac.machine.material.block.transport;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.network.packet.message.MsgTileFluidToC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.client.gui.PortableTankMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCDummyTank;
import defeatedcrow.hac.machine.material.fluid.DCLimitedTank;
import defeatedcrow.hac.machine.material.fluid.DCTank;
import defeatedcrow.hac.machine.material.fluid.SidedFluidWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class WaterIntakeBasinTile extends PortableFluidTankTile {

	public WaterIntakeBasinTile(BlockPos pos, BlockState state) {
		super(MachineInit.WATER_BASIN_TILE.get(), pos, state);
	}

	@Override
	public int getTankCap() {
		return 16000;
	}

	public DCLimitedTank limitedTank = new DCLimitedTank(Fluids.WATER, getTankCap());

	@Override
	public DCTank getTank() {
		return limitedTank;
	}

	/* processはスロットの液体容器処理 */

	int count = 4;
	private int lastHash = 0;

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
			return false;
		} else {
			count = 4;

			// Collect Water
			if (!getTank().isFull()) {
				if (level.getFluidState(pos).is(FluidTags.WATER)) {
					getTank().fill(new FluidStack(Fluids.WATER, 16000), FluidAction.EXECUTE);
				} else {
					FluidState above = level.getFluidState(pos.above());
					int flow = above.getAmount();
					if (above.is(FluidTags.WATER) && flow > 0) {
						getTank().fill(new FluidStack(Fluids.WATER, 250 * flow), FluidAction.EXECUTE);
					} else if (level.isRaining() && level.canSeeSky(pos.above())) {
						getTank().fill(new FluidStack(Fluids.WATER, 50), FluidAction.EXECUTE);
					}
				}
			}

			// Drain Only
			boolean flag = false;
			if (!DCUtil.isEmpty(this.inventory.getItem(0)) && !this.inventory.isMaxStack(1)) {
				ItemStack copy = this.inventory.getItem(0).copy();
				copy.setCount(1);
				flag = FluidUtil.getFluidHandler(copy).map(handler -> {
					FluidStack fluid = handler.getFluidInTank(0);
					if (fluid.isEmpty() || getTank().isFull()) {
						int space = Math.min(getTank().getFluidAmount(), handler.getTankCapacity(0));
						int d = handler.fill(getTank().drain(space, FluidAction.SIMULATE), FluidAction.EXECUTE);
						if (d > 0 && inventory.canInsertResult(handler.getContainer(), 1, 1) != 0) {
							// drain
							getTank().drain(d, FluidAction.EXECUTE);
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

			int hash = getTank().getFluid().hashCode();
			if (lastHash != hash) {
				lastHash = hash;
				flag = true;
			}

			if (flag && level instanceof ServerLevel) {
				BlockEntity.setChanged(level, pos, state);
				NonNullList<FluidStack> list = NonNullList.withSize(3, FluidStack.EMPTY);
				list.set(0, getTank().getFluid());
				MsgTileFluidToC.sendToClient((ServerLevel) level, pos, list);

				float f = getTank().getFluidAmount() * 8F / getTank().getCapacity();
				int m = Mth.ceil(f);
				m = Math.min(m, 7);
				WaterIntakeBasinBlock.changeLitState(getLevel(), getBlockPos(), m);
			}
			return flag;
		}
	}

	// cap

	private DCDummyTank dummy = new DCDummyTank();

	LazyOptional<? extends IFluidHandler> fluidhandler = LazyOptional.of(() -> new SidedFluidWrapper(dummy, limitedTank));

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
		this.fluidhandler = LazyOptional.of(() -> new SidedFluidWrapper(dummy, limitedTank));
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.tank.with_owner", this.ownerName) : Component.translatable("dcs.container.tank");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return PortableTankMenu.getMenu(i, inv, this);
	}

}
