package defeatedcrow.hac.api.recipe;

import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public interface IHeatTreatment {

	Ingredient getHeatingInput();

	List<DCHeatTier> getHeatingTemp();

	List<DCHumidity> getHeatingHum();

	List<DCAirflow> getHeatingAir();

	boolean matchHeatingClimate(IClimate clm);

	int getHeatingTime();

	Block getHeatingOutput();

	List<DCHeatTier> getCoolingTemp();

	List<DCHumidity> getCoolingHum();

	List<DCAirflow> getCoolingAir();

	boolean matchCoolingClimate(IClimate clm);

	Block getCoolingOutput();

	List<DCHeatTier> getAnnealingTemp();

	List<DCHumidity> getAnnealingHum();

	List<DCAirflow> getAnnealingAir();

	boolean matchAnnealingClimate(IClimate clm);

	Block getOutput();

	Block getFail();

	ItemStack getCurrentOutput(ItemStack in, IClimate climate);

}
