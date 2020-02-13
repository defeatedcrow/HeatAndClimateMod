package defeatedcrow.hac.main.api;

import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface IHeatTreatment {

	List<ItemStack> getInput1();

	List<DCHeatTier> getTemp1();

	List<DCHumidity> getHum1();

	List<DCAirflow> getAir1();

	boolean matchClimate1(IClimate clm);

	int getBurnTime1();

	ItemStack getInput2();

	List<DCHeatTier> getTemp2();

	List<DCHumidity> getHum2();

	List<DCAirflow> getAir2();

	boolean matchClimate2(IClimate clm);

	int getBurnTime2();

	ItemStack getInput3();

	List<DCHeatTier> getTemp3();

	List<DCHumidity> getHum3();

	List<DCAirflow> getAir3();

	boolean matchClimate3(IClimate clm);

	int getBurnTime3();

	ItemStack getOutput();

	ItemStack getFail();

	ActionResult<ItemStack> getCurrentOutput(ItemStack in, IClimate climate);

}
