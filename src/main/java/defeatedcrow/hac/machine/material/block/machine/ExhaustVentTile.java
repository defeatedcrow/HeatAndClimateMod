package defeatedcrow.hac.machine.material.block.machine;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.network.packet.message.MsgTileFluidToC;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCTank;
import defeatedcrow.hac.machine.material.fluid.IFluidTankTileDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class ExhaustVentTile extends BlockEntity implements IFluidTankTileDC {

	public ExhaustVentTile(BlockPos pos, BlockState state) {
		super(MachineInit.EXHAUST_VENT_TILE.get(), pos, state);
	}

	public boolean isActive() {
		return !DCState.getBool(this.getBlockState(), DCState.POWERED)
				&& !DCState.getBool(this.getBlockState(), EntityBlockDC.WATERLOGGED);
	}

	boolean lastFlag = false;
	int count = 7;
	private int lastHash = 0;

	public static boolean serverTick(Level level, BlockPos pos, BlockState state, ExhaustVentTile tile) {
		if (tile.count > 0) {
			tile.count--;
		} else {
			tile.lastFlag = DCState.getBool(tile.getBlockState(), DCState.FLAG);
			boolean flag = !tile.tank.getFluidInTank(0).isEmpty();
			if (flag != tile.lastFlag) {
				ExhaustVentBlock.changeLitState(tile.getLevel(), tile.getBlockPos(), flag);
			}
			if (tile.isActive()) {
				tile.tank.drain(4, FluidAction.EXECUTE);
			} else {
				tile.tank.drain(1, FluidAction.EXECUTE);
			}

			int hash = tile.tank.getFluidInTank(0).hashCode();
			if (tile.lastHash != hash) {
				tile.lastHash = hash;
				flag = true;
			}

			if (flag && level instanceof ServerLevel) {
				tile.setChanged(level, pos, state);
				NonNullList<FluidStack> list = NonNullList.withSize(3, FluidStack.EMPTY);
				list.set(0, tile.tank.getFluidInTank(0));
				MsgTileFluidToC.sendToClient((ServerLevel) level, pos, list);
			}

			tile.count = 7;
		}
		return false;
	}

	// fluid

	protected DCTank tank = new DCTank(1000) {
		@Override
		public boolean isFluidValid(FluidStack stack) {
			return super.isFluidValid(stack) && stack.getFluid().getFluidType().isLighterThanAir();
		}
	};

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
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		if (tag.contains(TagKeyDC.getTankKey(1), 10)) {
			CompoundTag tankTag = tag.getCompound(TagKeyDC.getTankKey(1));
			tank.readFromNBT(tankTag);
		}
	}

	@Override
	public void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		CompoundTag tankTag = new CompoundTag();
		tank.writeToNBT(tankTag);
		tag.put(TagKeyDC.getTankKey(1), tankTag);
	}

	// cap

	LazyOptional<? extends IFluidHandler> fluidhandler = LazyOptional.of(() -> tank);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction dirIn) {
		Direction dir = DCState.getFace(getBlockState(), DCState.DIRECTION);
		if (!this.remove && capability == ForgeCapabilities.FLUID_HANDLER && dirIn == dir) {
			return fluidhandler.cast();
		}
		return super.getCapability(capability, dir);
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

}
