package defeatedcrow.hac.main.block.fluid;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

/* DCTile持ちBlockとの互換用 */
public class FluidItemBlockWrapper extends FluidHandlerItemStack {
	public final String key;

	public FluidItemBlockWrapper(ItemStack container, int capacity, String k) {
		super(container, capacity);
		this.key = k;
	}

	@Override
	@Nullable
	public FluidStack getFluid() {
		NBTTagCompound nbt = container.getTagCompound();
		if (nbt != null && nbt.hasKey(key)) {
			NBTTagList list = nbt.getTagList(key, 10);
			NBTTagCompound nbt2 = list.getCompoundTagAt(0);
			if (!nbt2.hasKey("Empty")) {
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt2);
				return fluid;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	protected void setFluid(FluidStack fluid) {
		if (!container.hasTagCompound()) {
			container.setTagCompound(new NBTTagCompound());
		}
		NBTTagList list = new NBTTagList();
		NBTTagCompound nbt2 = new NBTTagCompound();
		if (fluid != null) {
			fluid.writeToNBT(nbt2);
		} else {
			nbt2.setString("Empty", "");
		}
		list.appendTag(nbt2);
		container.getTagCompound().setTag(key, list);
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new FluidTankProperties[] {
				new FluidTankProperties(getFluid(), capacity)
		};
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (container.getCount() != 1 || resource == null || resource.amount <= 0 || !canFillFluidType(resource)) {
			return 0;
		}

		FluidStack contained = getFluid();
		if (contained == null) {
			int fillAmount = Math.min(capacity, resource.amount);

			if (doFill) {
				FluidStack filled = resource.copy();
				filled.amount = fillAmount;
				setFluid(filled);
			}

			return fillAmount;
		} else {
			if (contained.isFluidEqual(resource)) {
				int fillAmount = Math.min(capacity - contained.amount, resource.amount);

				if (doFill && fillAmount > 0) {
					contained.amount += fillAmount;
					setFluid(contained);
				}

				return fillAmount;
			}

			return 0;
		}
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if (container.getCount() != 1 || resource == null || resource.amount <= 0
				|| !resource.isFluidEqual(getFluid())) {
			return null;
		}
		return drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (container.getCount() != 1 || maxDrain <= 0) {
			return null;
		}

		FluidStack contained = getFluid();
		if (contained == null || contained.amount <= 0 || !canDrainFluidType(contained)) {
			return null;
		}

		final int drainAmount = Math.min(contained.amount, maxDrain);

		FluidStack drained = contained.copy();
		drained.amount = drainAmount;

		if (doDrain) {
			contained.amount -= drainAmount;
			if (contained.amount == 0) {
				setContainerToEmpty();
			} else {
				setFluid(contained);
			}
		}

		return drained;
	}

	@Override
	public boolean canFillFluidType(FluidStack fluid) {
		return true;
	}

	@Override
	public boolean canDrainFluidType(FluidStack fluid) {
		return true;
	}

	@Override
	protected void setContainerToEmpty() {
		container.getTagCompound().removeTag(key);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T) this : null;
	}
}
