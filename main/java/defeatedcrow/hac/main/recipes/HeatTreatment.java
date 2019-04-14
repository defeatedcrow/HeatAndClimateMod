package defeatedcrow.hac.main.recipes;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IHeatTreatment;
import net.minecraft.item.ItemStack;

public class HeatTreatment implements IHeatTreatment {

	public final List<ItemStack> input1 = Lists.newArrayList();
	public final ItemStack input2;
	public final ItemStack input3;
	public final ItemStack output;
	public final ItemStack failure;

	public final List<DCHeatTier> temp1 = Lists.newArrayList();
	public final List<DCHumidity> hum1 = Lists.newArrayList();
	public final List<DCAirflow> air1 = Lists.newArrayList();

	public final List<DCHeatTier> temp2 = Lists.newArrayList();
	public final List<DCHumidity> hum2 = Lists.newArrayList();
	public final List<DCAirflow> air2 = Lists.newArrayList();

	public final List<DCHeatTier> temp3 = Lists.newArrayList();
	public final List<DCHumidity> hum3 = Lists.newArrayList();
	public final List<DCAirflow> air3 = Lists.newArrayList();

	public int time1 = 100;
	public int time2 = 100;
	public int time3 = 100;

	public HeatTreatment(List<ItemStack> inputs, IClimate climate1, ItemStack secondary, IClimate climate2,
			ItemStack tertialy, IClimate climate3, ItemStack out, ItemStack fail) {
		input1.addAll(inputs);
		if (climate1 != null) {
			temp1.add(climate1.getHeat());
			hum1.add(climate1.getHumidity());
			air1.add(climate1.getAirflow());
		}
		input2 = secondary;
		if (climate2 != null) {
			temp2.add(climate2.getHeat());
			hum2.add(climate2.getHumidity());
			air2.add(climate2.getAirflow());
		}
		input3 = tertialy;
		if (climate3 != null) {
			temp3.add(climate3.getHeat());
			hum3.add(climate3.getHumidity());
			air3.add(climate3.getAirflow());
		}
		output = out;
		failure = fail;
	}

	@Override
	public List<ItemStack> getInput1() {
		return input1;
	}

	@Override
	public List<DCHeatTier> getTemp1() {
		return temp1;
	}

	@Override
	public List<DCHumidity> getHum1() {
		return hum1;
	}

	@Override
	public List<DCAirflow> getAir1() {
		return air1;
	}

	@Override
	public int getBurnTime1() {
		return time1;
	}

	@Override
	public ItemStack getInput2() {
		return input2;
	}

	@Override
	public List<DCHeatTier> getTemp2() {
		return temp2;
	}

	@Override
	public List<DCHumidity> getHum2() {
		return hum2;
	}

	@Override
	public List<DCAirflow> getAir2() {
		return air2;
	}

	@Override
	public int getBurnTime2() {
		return time2;
	}

	@Override
	public ItemStack getInput3() {
		return input3;
	}

	@Override
	public List<DCHeatTier> getTemp3() {
		return temp3;
	}

	@Override
	public List<DCHumidity> getHum3() {
		return hum3;
	}

	@Override
	public List<DCAirflow> getAir3() {
		return air3;
	}

	@Override
	public int getBurnTime3() {
		return time3;
	}

	@Override
	public ItemStack getOutput() {
		return output;
	}

	@Override
	public ItemStack getFail() {
		return failure;
	}

	@Override
	public ItemStack getCurrentOutput(ItemStack in, IClimate climate) {
		if (matchClimate1(climate))
			for (ItemStack i1 : input1) {
				if (DCUtil.isSameItem(in, i1, false)) {
					if (!DCUtil.isEmpty(input2)) {
						return input2.copy();
					} else if (!DCUtil.isEmpty(input3)) {
						return input3.copy();
					} else if (!DCUtil.isEmpty(output)) {
						return output.copy();
					}
				}
			}
		if (matchClimate2(climate) && DCUtil.isSameItem(in, input2, false)) {
			if (!DCUtil.isEmpty(input3)) {
				return input3.copy();
			} else if (!DCUtil.isEmpty(output)) {
				return output.copy();
			}
		}
		if (matchClimate3(climate) && DCUtil.isSameItem(in, input3, false)) {
			if (!DCUtil.isEmpty(output)) {
				return output.copy();
			}
		}
		return DCUtil.isEmpty(failure) ? ItemStack.EMPTY : failure;
	}

	@Override
	public boolean matchClimate1(IClimate clm) {
		if (clm == null)
			return false;
		boolean a = temp1.isEmpty() || temp1.contains(clm.getHeat());
		boolean b = hum1.isEmpty() || hum1.contains(clm.getHumidity());
		boolean c = air1.isEmpty() || air1.contains(clm.getAirflow());
		return a && b && c;
	}

	@Override
	public boolean matchClimate2(IClimate clm) {
		if (clm == null)
			return false;
		boolean a = temp2.isEmpty() || temp2.contains(clm.getHeat());
		boolean b = hum2.isEmpty() || hum2.contains(clm.getHumidity());
		boolean c = air2.isEmpty() || air2.contains(clm.getAirflow());
		return a && b && c;
	}

	@Override
	public boolean matchClimate3(IClimate clm) {
		if (clm == null)
			return false;
		boolean a = temp3.isEmpty() || temp3.contains(clm.getHeat());
		boolean b = hum3.isEmpty() || hum3.contains(clm.getHumidity());
		boolean c = air3.isEmpty() || air3.contains(clm.getAirflow());
		return a && b && c;
	}

}
