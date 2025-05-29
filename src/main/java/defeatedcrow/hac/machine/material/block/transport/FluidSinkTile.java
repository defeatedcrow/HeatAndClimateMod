package defeatedcrow.hac.machine.material.block.transport;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class FluidSinkTile extends BlockEntity implements IRenderBlockData {

	public FluidSinkTile(BlockPos pos, BlockState state) {
		super(MachineInit.FLUID_SINK_TILE.get(), pos, state);
	}

	// fluid

	protected FluidSink tank = new FluidSink();

	// nbt
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
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

	public static class FluidSink implements IFluidHandler {

		protected FluidSink() {}

		@Override
		public int getTanks() {
			return 1;
		}

		@Override
		public @NotNull FluidStack getFluidInTank(int tank) {
			return FluidStack.EMPTY;
		}

		@Override
		public int getTankCapacity(int tank) {
			return 1000;
		}

		@Override
		public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
			return true;
		}

		@Override
		public int fill(FluidStack get, FluidAction action) {
			if (!get.isEmpty()) {
				return Math.min(get.getAmount(), 1000);
			}
			return 0;
		}

		@Override
		public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
			return FluidStack.EMPTY;
		}

		@Override
		public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
			return FluidStack.EMPTY;
		}

	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == MachineInit.KICHEN_SINK_WOOD.get())
			return WOOD;
		if (block == MachineInit.KICHEN_SINK_BLACK.get())
			return BLACK;
		if (block == MachineInit.KICHEN_SINK_SUS.get())
			return SUS;
		if (block == MachineInit.HALF_SINK_SUS.get())
			return SUS_HALF;
		return LAB;
	}

	public static final EntityRenderData WOOD = new EntityRenderData("tile/sink_wood", 1F, -0.5F);
	public static final EntityRenderData BLACK = new EntityRenderData("tile/sink_black", 1F, -0.5F);
	public static final EntityRenderData LAB = new EntityRenderData("tile/sink_lab", 1F, -0.5F);
	public static final EntityRenderData SUS = new EntityRenderData("tile/sink_sus", 1F, -0.5F);
	public static final EntityRenderData SUS_HALF = new EntityRenderData("tile/sink_sus_half", 1F, -0.5F);

}
