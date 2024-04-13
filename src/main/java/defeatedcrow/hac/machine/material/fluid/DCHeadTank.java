package defeatedcrow.hac.machine.material.fluid;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.machine.IFacingTile;
import defeatedcrow.hac.api.machine.IFluidPipe;
import defeatedcrow.hac.api.util.TagKeyDC;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class DCHeadTank extends DCTank implements IFluidPipe, IFacingTile, ICapabilityProvider {

	protected int flowRate;
	protected final NonNullList<FaceIO> faces = NonNullList.withSize(6, FaceIO.NONE);

	public DCHeadTank(int cap) {
		this(cap, 32);
	}

	public DCHeadTank setDefaultFace(Direction dir, FaceIO face) {
		int i = dir.get3DDataValue();
		if (i >= 0 && i < 6) {
			faces.set(i, face);
		}
		return this;
	}

	public DCHeadTank(int cap, int rate) {
		super(cap);
		this.flowRate = rate;
	}

	@Override
	public int getFlowRate() {
		return flowRate;
	}

	@Override
	public FaceIO getFace(Direction dir) {
		int i = dir.get3DDataValue();
		if (i >= 0 && i < 6) {
			if (faces.size() <= i) {
				faces.add(i, FaceIO.NONE);
			}
			return faces.get(i);
		}
		return FaceIO.NONE;
	}

	@Override
	public FaceIO switchFace(Direction dir) {
		int i = dir.get3DDataValue();
		FaceIO f = FaceIO.NONE;
		if (i >= 0 && i < 6 && i < faces.size()) {
			f = faces.get(i);
		}
		FaceIO next = FaceIO.getIO(f.getID() + 1);
		faces.set(i, next);
		return next;
	}

	@Override
	public FaceIO setFace(Direction dir, FaceIO face) {
		int i = dir.get3DDataValue();
		if (i >= 0 && i < 6) {
			faces.set(i, face);
		}
		return face;
	}

	@Override
	public NonNullList<FaceIO> getFaces() {
		return faces;
	}

	@Override
	public void readFromNBT(CompoundTag nbt) {
		FluidStack f = FluidStack.loadFluidStackFromNBT(nbt);
		fluid = f;
		for (int i = 0; i < 6; i++) {
			if (nbt.contains(TagKeyDC.FACE_IO + i)) {
				int id = nbt.getInt(TagKeyDC.FACE_IO + i);
				faces.set(i, FaceIO.getIO(id));
			}
		}
	}

	@Override
	public CompoundTag writeToNBT(CompoundTag nbt) {
		fluid.writeToNBT(nbt);
		for (int i = 0; i < 6; i++) {
			if (faces.size() <= i) {
				faces.add(i, FaceIO.NONE);
			}
			int id = faces.get(i).getID();
			nbt.putInt(TagKeyDC.FACE_IO + i, id);
		}
		return nbt;
	}

	@Override
	public int fill(FluidStack get, FluidAction action) {
		if (!get.isEmpty() && isFluidValid(get)) {
			int vac = capacity - this.getFluidAmount();
			int ret = Math.min(vac, get.getAmount());
			// ret = Math.min(ret, flowRate);
			if (!action.simulate() && ret > 0) {
				get.setAmount(ret);
				if (fluid.isEmpty()) {
					setFluid(get);
				} else {
					CompoundTag tag = DCFluidUtil.combineTag(fluid, get);
					if (tag != null) {
						fluid.setTag(tag);
					}
					fluid.grow(ret);
				}
			}
			return ret;
		}
		return 0;
	}

	@Override
	public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
		if (!fluid.isEmpty()) {
			int ret = Math.min(maxDrain, fluid.getAmount());
			ret = Math.min(ret, flowRate);
			if (ret > 0) {
				FluidStack f = fluid.copy();
				f.setAmount(ret);
				if (!action.simulate()) {
					fluid.shrink(ret);
					if (fluid.getAmount() <= 0) {
						setFluid(FluidStack.EMPTY);
					}
				}
				return f;
			}
		}
		return FluidStack.EMPTY;

	}

	@Override
	public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
		if (DCFluidUtil.isSameFluid(fluid, resource)) {
			return drain(resource.getAmount(), action);
		}
		return FluidStack.EMPTY;
	}

	@Override
	public int fill(FluidStack get, FluidAction action, Direction dir) {
		if (getFace(dir).canReceive()) {
			return fill(get, action);
		}
		return 0;
	}

	@Override
	public @NotNull FluidStack drain(FluidStack resource, FluidAction action, Direction dir) {
		if (DCFluidUtil.isSameFluid(fluid, resource)) {
			return drain(resource.getAmount(), action, dir);
		}
		return FluidStack.EMPTY;
	}

	@Override
	public @NotNull FluidStack drain(int maxDrain, FluidAction action, Direction dir) {
		if (getFace(dir).canExtract()) {
			if (dir != Direction.UP || DCFluidUtil.getHead(fluid) > 0 || DCFluidUtil.isGas(fluid)) {
				FluidStack ret = drain(maxDrain, action);
				ret = DCFluidUtil.addHead(ret, DCFluidUtil.getDirectionHead(dir));
				return ret;
			}
		}
		return FluidStack.EMPTY;
	}

	private final LazyOptional<IFluidHandler> holder = LazyOptional.of(() -> this);

	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction dir) {
		if (dir == null) {
			return ForgeCapabilities.FLUID_HANDLER.orEmpty(cap, holder);
		} else if (getFace(dir) != FaceIO.NONE) {
			return ForgeCapabilities.FLUID_HANDLER.orEmpty(cap, holder);
		}
		return LazyOptional.empty();
	}

	@Override
	public boolean isFluidValid(FluidStack stack) {
		return fluid.isEmpty() || DCFluidUtil.isSameFluid(stack, fluid);
	}

}
