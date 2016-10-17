package defeatedcrow.hac.main.recipes;

import java.util.HashMap;
import java.util.Map;

import defeatedcrow.hac.main.api.IFluidFuelRegister;
import defeatedcrow.hac.main.api.MainAPIManager;

public class DCFluidFuelRegister implements IFluidFuelRegister {

	public IFluidFuelRegister instance() {
		return MainAPIManager.fuelRegister;
	}

	private static Map<String, Integer> list;

	public DCFluidFuelRegister() {
		list = new HashMap<String, Integer>();
	}

	@Override
	public Map<String, Integer> getFuelMap() {
		return list;
	}

	@Override
	public void registerFuel(String fluidName, Integer burnTime) {
		if (!isRegistered(fluidName) && burnTime > 0) {
			list.put(fluidName, burnTime);
		}
	}

	@Override
	public boolean isRegistered(String fluidName) {
		if (list.containsKey(fluidName)) {
			return true;
		}
		return false;
	}

	@Override
	public int getBurningTime(String fluidName) {
		if (list.containsKey(fluidName)) {
			Integer time = list.get(fluidName);
			if (time != null) {
				return time.intValue();
			}
		}
		return 0;
	}

}
