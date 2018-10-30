package defeatedcrow.hac.main.recipes;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.main.api.IFluidFuel;
import defeatedcrow.hac.main.api.IFluidFuelRegister;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class DCFluidFuelRegister implements IFluidFuelRegister {

	public IFluidFuelRegister instance() {
		return MainAPIManager.fuelRegister;
	}

	private static List<IFluidFuel> list;

	public DCFluidFuelRegister() {
		list = new ArrayList<IFluidFuel>();
	}

	@Override
	public List<IFluidFuel> getFuelList() {
		return list;
	}

	@Override
	public void registerFuel(String name, Integer burnTime) {
		Fluid fluid = FluidRegistry.getFluid(name);
		if (fluid != null) {
			registerFuel(fluid, burnTime);
		}
	}

	@Override
	public void registerFuel(Fluid fluid, Integer burnTime) {
		if (!isRegistered(fluid) && burnTime > 0) {
			list.add(new FluidFuel(fluid, burnTime));
		}
	}

	@Override
	public boolean isRegistered(Fluid fluid) {
		for (IFluidFuel f : list) {
			if (f.content(fluid)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getBurningTime(Fluid fluid) {
		for (IFluidFuel f : list) {
			if (f.content(fluid)) {
				return f.getBurnTime();
			}
		}
		return 0;
	}

	public class FluidFuel implements IFluidFuel {

		private final Fluid fluid;
		private final int time;

		public FluidFuel(Fluid f, int i) {
			fluid = f;
			time = i;
		}

		@Override
		public Fluid getFluid() {
			return fluid;
		}

		@Override
		public int getBurnTime() {
			return time;
		}

		@Override
		public boolean content(Fluid f) {
			return f.equals(fluid) || FluidDictionaryDC.matchFluid(f, fluid);
		}

	}

}
