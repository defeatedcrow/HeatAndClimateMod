package defeatedcrow.hac.food.item.brewing;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

/**
 * 空容器にPAPERを返却するコンテナアイテム
 */
public class FluidBottleContDC implements IFluidHandlerItem, ICapabilityProvider {

	protected ItemStack container;

	public FluidBottleContDC(ItemStack container) {
		this.container = container;
	}

	/* Fluid */

	@Nullable
	public FluidStack getFluid() {
		Fluid fluid = getFluid(container);
		if (fluid != null) {
			return new FluidStack(fluid, 500);
		}
		return null;
	}

	protected void setFluid(FluidStack resource) {
		if (container.getCount() != 1 || resource == null || !canFillFluidType(resource)) {
			return;
		}
		container = getItemFromFluid(resource.getFluid());
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new FluidTankProperties[] { new FluidTankProperties(getFluid(), 500) };
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (container.getCount() != 1 || resource == null || resource.amount < 500 || !canFillFluidType(resource)) {
			return 0;
		}

		int fillAmo = 500;
		ItemStack ret = getItemFromFluid(resource.getFluid());

		if (DCUtil.isEmpty(ret)) {
			return 0;
		}

		if (doFill) {
			container = ret;
		}
		return fillAmo;

	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if (container.getCount() != 1 || resource == null || resource.amount < 500 || !resource
				.isFluidEqual(getFluid())) {
			return null;
		}
		return drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (container.getCount() != 1 || maxDrain < 500) {
			return null;
		}

		FluidStack contained = getFluid();
		if (contained == null || !canDrainFluidType(contained)) {
			return null;
		}

		final int drainAmount = 500;
		FluidStack drained = contained.copy();
		drained.amount = drainAmount;

		if (doDrain) {
			setContainerToEmpty();
		}

		return drained;
	}

	public boolean canFillFluidType(FluidStack fluid) {
		if (DCUtil.isEmpty(container))
			return false;
		if (container.getItemDamage() == 0 && container.getItem() == FoodInit.liquorBottle) {
			return true;
		}
		return false;
	}

	public boolean canDrainFluidType(FluidStack fluid) {
		return true;
	}

	protected void setContainerToEmpty() {
		container = new ItemStack(FoodInit.liquorBottle);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY ? (T) this : null;
	}

	@Override
	public ItemStack getContainer() {
		return container;
	}

	public static Fluid getFluid(ItemStack item) {
		if (!DCUtil.isEmpty(item)) {
			if (item.getItem() == FoodInit.liquorBottle) {
				String name = ItemLiquorBottle.getFluidName(item.getItemDamage());
				return FluidRegistry.getFluid(name);
			} else if (item.getItem() == FoodInit.roseWaterBottle) {
				String name = ItemRoseWaterBottle.getFluidName(item.getItemDamage());
				return FluidRegistry.getFluid(name);
			}
		}
		return null;
	}

	public static ItemStack getItemFromFluid(Fluid fluid) {
		if (fluid == FoodInit.roseWater) {
			return new ItemStack(FoodInit.roseWaterBottle, 1, 0);
		}
		if (fluid == FoodInit.tonic) {
			return new ItemStack(FoodInit.roseWaterBottle, 1, 1);
		}
		if (fluid == FoodInit.lemon_squash) {
			return new ItemStack(FoodInit.roseWaterBottle, 1, 2);
		}
		if (fluid == FoodInit.cola) {
			return new ItemStack(FoodInit.roseWaterBottle, 1, 3);
		}

		int meta = -1;
		if (fluid == FoodInit.beer) {
			meta = 1;
		} else if (fluid == FoodInit.wine) {
			meta = 2;
		} else if (fluid == FoodInit.sake) {
			meta = 3;
		} else if (fluid == FoodInit.dateWine) {
			meta = 4;
		} else if (fluid == FoodInit.whisky) {
			meta = 5;
		} else if (fluid == FoodInit.brandy) {
			meta = 6;
		} else if (fluid == FoodInit.pomaceBrandy) {
			meta = 7;
		} else if (fluid == FoodInit.shotyu) {
			meta = 8;
		} else if (fluid == FoodInit.araq) {
			meta = 9;
		} else if (fluid == FoodInit.whiteRum) {
			meta = 10;
		} else if (fluid == FoodInit.darkRum) {
			meta = 11;
		} else if (fluid == FoodInit.akvavit) {
			meta = 12;
		} else if (fluid == FoodInit.vodka) {
			meta = 13;
		} else if (fluid == FoodInit.netherWine) {
			meta = 14;
		} else if (fluid == FoodInit.chorusLiquor) {
			meta = 15;
		} else if (fluid == FoodInit.awamori) {
			meta = 16;
		}
		if (meta > 0) {
			return new ItemStack(FoodInit.liquorBottle, 1, meta);
		} else {
			return ItemStack.EMPTY;
		}
	}

}
