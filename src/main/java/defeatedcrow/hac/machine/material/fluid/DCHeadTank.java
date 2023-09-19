package defeatedcrow.hac.machine.material.fluid;

import org.jetbrains.annotations.NotNull;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.fluids.FluidStack;

public class DCHeadTank extends DCTank {

	public DCHeadTank(int cap) {
		super(cap);
	}

	@Override
	public int fill(FluidStack get, FluidAction action) {
		if (!get.isEmpty() && isFluidValid(get)) {
			int vac = capacity - this.getFluidAmount();
			int ret = Math.min(vac, get.getAmount());
			int h = DCFluidUtil.combineHead(fluid, get);
			if (!action.simulate()) {
				if (fluid.isEmpty()) {
					setFluid(get);
				} else {
					CompoundTag tag = DCFluidUtil.combineTag(fluid, get);
					if (tag != null) {
						fluid.setTag(tag);
					}
					fluid.grow(ret);
				}
				if (h > 0) {
					DCFluidUtil.setHead(fluid, h);
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

}
