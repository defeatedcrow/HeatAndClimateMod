package defeatedcrow.hac.machine.material.fluid;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class FluidItemWrapperDC implements IFluidHandlerItem, ICapabilityProvider {

	@NotNull
	protected ItemStack container;
	protected int capacity;

	public FluidItemWrapperDC(@NotNull ItemStack cont, int cap) {
		this.container = cont;
		this.capacity = cap;
	}

	@Override
	public int getTanks() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public @NotNull FluidStack getFluidInTank(int tank) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int getTankCapacity(int tank) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public int fill(FluidStack resource, FluidAction action) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public @NotNull ItemStack getContainer() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
