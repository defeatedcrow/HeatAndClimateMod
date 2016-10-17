package defeatedcrow.hac.main.block.fluid;

import defeatedcrow.hac.core.fluid.DCTank;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

/* 無限にfillできるダミータンク
 * 液体破棄用 */
public class EmptyFluidHandlerWrapper extends DCTank {

	public EmptyFluidHandlerWrapper() {
		super(1000);
	}

	@Override
	public EmptyFluidHandlerWrapper readFromNBT(NBTTagCompound nbt, String key) {
		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt, String key) {
		return new NBTTagCompound();
	}

	/* tank */

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public Fluid getFluidType() {
		return null;
	}

	@Override
	public String getFluidName() {
		return "Empty";
	}

	@Override
	public FluidStack getFluid() {
		return null;
	}

	@Override
	public int getFluidAmount() {
		return 0;
	}

	@Override
	public int getCapacity() {
		return 1000;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public DCTank setFluid(FluidStack f) {
		return this;
	}

	@Override
	public void setAmount(int par1) {
	}

	@Override
	public void setFluidById(int par1) {
	}

	@Override
	public int fill(FluidStack get, boolean doFill) {
		if (get != null && get.getFluid() != null) {
			return get.amount;
		}
		return 0;
	}

	@Override
	public FluidStack drain(int drain, boolean doDrain) {
		return null;
	}

	/* property */

	@Override
	public FluidStack getContents() {
		return null;
	}

	@Override
	public boolean canFill() {
		return true;
	}

	@Override
	public boolean canDrain() {
		return false;
	}

	@Override
	public boolean canFillFluidType(FluidStack get) {
		return get != null;
	}

	@Override
	public boolean canDrainFluidType(FluidStack get) {
		return get != null;
	}

	/* Handler */

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[] {
				this };
	}

	@Override
	public FluidStack drain(FluidStack get, boolean doDrain) {
		if (canDrainTarget(get)) {
			return drain(get.amount, doDrain);
		}
		return null;
	}

	/* other method */

	@Override
	public boolean canFillTarget(FluidStack get) {
		if (get != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canDrainTarget(FluidStack get) {
		return false;
	}

}
