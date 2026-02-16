package defeatedcrow.hac.machine.material.block.transport;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.machine.IFacingTile;
import defeatedcrow.hac.api.machine.IFluidPipe;
import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.machine.material.fluid.DCHeadTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

public abstract class FluidPipeTileBaseDC extends OwnableBaseTileDC implements IFacingTile {

	public FluidPipeTileBaseDC(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, FluidPipeTileBaseDC tile) {
		tile.onTickProcess(level, pos, state);
	}

	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		return true;
	}

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		return false;
	}

	@Override
	public FaceIO getFace(Direction dir) {
		return getFluidHandler().getFace(dir);
	}

	@Override
	public FaceIO switchFace(Direction dir) {
		FaceIO next = getFluidHandler().switchFace(dir);
		return next;
	}

	@Override
	public FaceIO setFace(Direction dir, FaceIO face) {
		return getFluidHandler().setFace(dir, face);
	}

	@Override
	public NonNullList<FaceIO> getFaces() {
		return getFluidHandler().getFaces();
	}

	// nbt

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);

		getFluidHandler().readFromNBT(tag);

	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);

		getFluidHandler().writeToNBT(tag);
	}

	// caps

	private LazyOptional<? extends IFluidPipe> tank = LazyOptional.of(() -> getFluidHandler());

	public abstract PipeTank getFluidHandler();

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && capability == ForgeCapabilities.FLUID_HANDLER) {
			if (facing == null) {
				return tank.cast();
			} else if (getFluidHandler().getFace(facing) != FaceIO.NONE) {
				return tank.cast();
			} else {
				return LazyOptional.empty();
			}
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		tank.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		tank = LazyOptional.of(() -> getFluidHandler());
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.fluid_pipe.with_owner", this.ownerName) : Component.translatable("dcs.container.fluid_pipe");
	}

	public class PipeTank extends DCHeadTank {
		final FluidPipeAlloyTile pipe;

		protected PipeTank(int cap, int flow, FluidPipeAlloyTile pipeIn) {
			super(cap, flow);
			pipe = pipeIn;
		}

		// @Override
		// public int fill(FluidStack get, FluidAction action, Direction from) {
		// if (get == null || get.isEmpty() || get.getAmount() == 0)
		// return 0;
		// int amo = get.getAmount();
		// int ret = super.fill(get, action, from);
		// if (ret == amo)
		// return ret;
		// // overflow
		// else if (from != Direction.UP && isAlmostFull()) {
		// FluidStack copy = get.copy();
		// copy.setAmount(amo - ret);
		// BlockEntity upwardEntity = getLevel().getBlockEntity(pipe.getBlockPos().above());
		//
		// DCFluidUtil.addHead(copy, DCFluidUtil.getDirectionHead(from.getOpposite()));
		// if (upwardEntity != null) {
		// int ret2 = upwardEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, Direction.DOWN).map(targetHandler -> {
		// if (targetHandler instanceof IFluidPipe pipe) {
		// return pipe.getFace(Direction.DOWN).canReceive() ? pipe.fill(copy, action, Direction.DOWN) : 0;
		// } else {
		// return targetHandler.fill(get, action);
		// }
		// }).orElse(0);
		// return ret = ret2;
		// }
		// }
		// return ret;
		// }
	}

}
