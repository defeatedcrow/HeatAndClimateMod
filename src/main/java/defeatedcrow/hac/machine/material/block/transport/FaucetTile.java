package defeatedcrow.hac.machine.material.block.transport;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import defeatedcrow.hac.api.machine.IFluidPipe;
import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class FaucetTile extends BlockEntity implements IRenderBlockData {

	public FaucetTile(BlockPos pos, BlockState state) {
		super(MachineInit.FAUCET_TILE.get(), pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, FaucetTile tile) {
		tile.onTickProcess(level, pos, state);
	}

	public boolean isActive() {
		return DCState.getBool(this.getBlockState(), DCState.POWERED);
	}

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (isActive()) {
			BlockPos p2 = this.getBlockPos().below();
			BlockEntity targetEntity = getLevel().getBlockEntity(p2);
			if (targetEntity != null) {
				targetEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, Direction.UP).ifPresent(handler -> {
					if (handler instanceof IFluidPipe sided) {
						if (sided.getFace(Direction.UP).canReceive()) {
							int ret = sided.fill(tank.fluid.copy(), FluidAction.EXECUTE, Direction.UP);
						}
					} else if (handler != null) {
						int ret = handler.fill(tank.fluid.copy(), FluidAction.EXECUTE);
					}
				});
			}
		}
		return false;
	}

	// cap

	protected FluidFauset tank = new FluidFauset(1000);

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

	protected class FluidFauset implements IFluidHandler {

		private final FluidStack fluid;
		private final int rate;

		protected FluidFauset(int cap) {
			fluid = new FluidStack(Fluids.WATER, cap);
			rate = cap;
		}

		@Override
		public int getTanks() {
			return 1;
		}

		public int getFluidAmount() {
			return fluid.getAmount();
		}

		public int getCapacity() {
			return rate;
		}

		@Override
		public @NotNull FluidStack getFluidInTank(int tank) {
			return fluid;
		}

		@Override
		public int getTankCapacity(int tank) {
			return 128;
		}

		@Override
		public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
			return true;
		}

		@Override
		public int fill(FluidStack get, FluidAction action) {
			return 0;
		}

		@Override
		public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
			if (DCFluidUtil.isSameFluid(fluid, resource)) {
				return drain(resource.getAmount(), action);
			}
			return FluidStack.EMPTY;
		}

		@Override
		public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
			if (!fluid.isEmpty()) {
				int ret = Math.min(maxDrain, rate);
				if (ret > 0) {
					FluidStack f = new FluidStack(fluid.getFluid(), ret);
					return f;
				}
			}
			return FluidStack.EMPTY;
		}

	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == MachineInit.FAUCET_A.get())
			return TYPE_A;
		if (block == MachineInit.FAUCET_B.get())
			return TYPE_B;
		if (block == MachineInit.FAUCET_C.get())
			return TYPE_C;
		if (block == MachineInit.FAUCET_D.get())
			return TYPE_D;
		return TYPE_A;
	}

	public static final EntityRenderData TYPE_A = new EntityRenderData("tile/faucet_sus_a", 1F, 0F);
	public static final EntityRenderData TYPE_B = new EntityRenderData("tile/faucet_sus_b", 1F, 0F);
	public static final EntityRenderData TYPE_C = new EntityRenderData("tile/faucet_sus_c", 1F, 0F);
	public static final EntityRenderData TYPE_D = new EntityRenderData("tile/faucet_sus_d", 1F, 0F);

}
