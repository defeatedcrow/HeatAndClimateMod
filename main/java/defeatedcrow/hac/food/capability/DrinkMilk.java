package defeatedcrow.hac.food.capability;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;

public enum DrinkMilk {
	NONE(0, 1.0F),
	MILK(1, 2.0F),
	CREAM(2, 4.0F),
	SOY(3, 3.0F),
	CITRUS(4, 3.0F);

	public final float effect;
	public final int id;

	private DrinkMilk(int i, float param) {
		effect = param;
		id = i;
	}

	public static DrinkMilk getFromId(int i) {
		if (i == 0) {
			return NONE;
		} else if (i == 1) {
			return MILK;
		} else if (i == 2) {
			return CREAM;
		} else if (i == 3) {
			return SOY;
		} else if (i == 4) {
			return CITRUS;
		}
		return NONE;
	}

	public static String getTagKey() {
		return "dcs.drink.milk";
	}

	public static DrinkMilk isMilkItem(ItemStack item) {
		if (DCUtil.isEmpty(item)) {
			return NONE;
		} else {
			if (item.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				IFluidHandler cont = item.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
				FluidStack f = cont.getTankProperties()[0].getContents();
				if (f != null && f.getFluid() != null) {
					String name = FluidRegistry.getFluidName(f.getFluid());
					if (name.contains("soy") || name.contains("Soy")) {
						return SOY;
					} else if (name.contains("cream") || name.contains("Cream")) {
						return CREAM;
					} else if (name.contains("milk") || name.contains("Milk")) {
						return MILK;
					} else if (name.contains("lemon") || name.contains("lime")) {
						return CITRUS;
					}
				}
			}
			int[] ids = OreDictionary.getOreIDs(item);
			DrinkMilk ret = DrinkMilk.NONE;
			for (int i : ids) {
				String name = OreDictionary.getOreName(i);
				if (name.contains("soy") || name.contains("Soy")) {
					ret = SOY;
				} else if (name.contains("cream") || name.contains("Cream")) {
					ret = CREAM;
				} else if (name.contains("milk") || name.contains("Milk")) {
					if (ret != SOY)
						ret = MILK;
				} else if (name.contains("lemon") || name.contains("lime")) {
					ret = CITRUS;
				}
			}
			return ret;
		}
	}
}
