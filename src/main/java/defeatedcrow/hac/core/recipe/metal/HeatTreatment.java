package defeatedcrow.hac.core.recipe.metal;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.IHeatTreatment;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class HeatTreatment implements IHeatTreatment {

	public final Ingredient input1;
	public final Block input2;
	public final Block input3;
	public final Block output;
	public final Block failure;

	public final List<DCHeatTier> heatingTemp = Lists.newArrayList();
	public final List<DCHumidity> heatingHum = Lists.newArrayList();
	public final List<DCAirflow> heatingAir = Lists.newArrayList();

	public final List<DCHeatTier> coolingTemp = Lists.newArrayList();
	public final List<DCHumidity> coolingHum = Lists.newArrayList();
	public final List<DCAirflow> coolingAir = Lists.newArrayList();

	public final List<DCHeatTier> annealingTemp = Lists.newArrayList();
	public final List<DCHumidity> annealingHum = Lists.newArrayList();
	public final List<DCAirflow> annealingAir = Lists.newArrayList();

	public int time = 100;

	public HeatTreatment(Ingredient inputs, IClimate climate1, Block secondary, IClimate climate2,
			Block tertialy, IClimate climate3, Block out, Block fail) {
		this(inputs, secondary, tertialy, out, fail);
		if (climate1 != null) {
			heatingTemp.add(climate1.getHeat());
			heatingHum.add(climate1.getHumidity());
			heatingAir.add(climate1.getAirflow());
		}
		if (climate2 != null) {
			coolingTemp.add(climate2.getHeat());
			coolingHum.add(climate2.getHumidity());
			coolingAir.add(climate2.getAirflow());
		}
		if (climate3 != null) {
			annealingTemp.add(climate3.getHeat());
			annealingHum.add(climate3.getHumidity());
			annealingAir.add(climate3.getAirflow());
		}
	}

	public HeatTreatment(Ingredient inputs, Block secondary, Block tertialy, Block out, Block fail) {
		input1 = inputs;
		input2 = secondary;
		input3 = tertialy;
		output = out;
		failure = fail;
	}

	public HeatTreatment addHeatingParam(List<DCHeatTier> temps, List<DCHumidity> hums, List<DCAirflow> airs) {
		heatingTemp.addAll(temps);
		heatingHum.addAll(hums);
		heatingAir.addAll(airs);
		return this;
	}

	public HeatTreatment addCoolingParam(List<DCHeatTier> temps, List<DCHumidity> hums, List<DCAirflow> airs) {
		coolingTemp.addAll(temps);
		coolingHum.addAll(hums);
		coolingAir.addAll(airs);
		return this;
	}

	public HeatTreatment addAnnealingParam(List<DCHeatTier> temps, List<DCHumidity> hums, List<DCAirflow> airs) {
		annealingTemp.addAll(temps);
		annealingHum.addAll(hums);
		annealingAir.addAll(airs);
		return this;
	}

	@Override
	public Ingredient getHeatingInput() {
		return input1;
	}

	@Override
	public List<DCHeatTier> getHeatingTemp() {
		return ImmutableList.copyOf(heatingTemp);
	}

	@Override
	public List<DCHumidity> getHeatingHum() {
		return ImmutableList.copyOf(heatingHum);
	}

	@Override
	public List<DCAirflow> getHeatingAir() {

		return ImmutableList.copyOf(heatingAir);
	}

	@Override
	public boolean matchHeatingClimate(IClimate clm) {
		if (clm == null)
			return false;
		boolean a = heatingTemp.isEmpty() || heatingTemp.contains(clm.getHeat());
		boolean b = heatingHum.isEmpty() || heatingHum.contains(clm.getHumidity());
		boolean c = heatingAir.isEmpty() || heatingAir.contains(clm.getAirflow());
		return a && b && c;
	}

	@Override
	public int getHeatingTime() {
		return time;
	}

	@Override
	public Block getHeatingOutput() {
		return input2;
	}

	@Override
	public List<DCHeatTier> getCoolingTemp() {
		return ImmutableList.copyOf(coolingTemp);
	}

	@Override
	public List<DCHumidity> getCoolingHum() {
		return ImmutableList.copyOf(coolingHum);
	}

	@Override
	public List<DCAirflow> getCoolingAir() {
		return ImmutableList.copyOf(coolingAir);
	}

	@Override
	public boolean matchCoolingClimate(IClimate clm) {
		if (clm == null)
			return false;
		boolean a = coolingTemp.isEmpty() || coolingTemp.contains(clm.getHeat());
		boolean b = coolingHum.isEmpty() || coolingHum.contains(clm.getHumidity());
		boolean c = coolingAir.isEmpty() || coolingAir.contains(clm.getAirflow());
		return a && b && c;
	}

	@Override
	public Block getCoolingOutput() {
		return input3;
	}

	@Override
	public List<DCHeatTier> getAnnealingTemp() {
		return ImmutableList.copyOf(annealingTemp);
	}

	@Override
	public List<DCHumidity> getAnnealingHum() {
		return ImmutableList.copyOf(annealingHum);
	}

	@Override
	public List<DCAirflow> getAnnealingAir() {
		return ImmutableList.copyOf(annealingAir);
	}

	@Override
	public boolean matchAnnealingClimate(IClimate clm) {
		if (clm == null)
			return false;
		boolean a = annealingTemp.isEmpty() || annealingTemp.contains(clm.getHeat());
		boolean b = annealingHum.isEmpty() || annealingHum.contains(clm.getHumidity());
		boolean c = annealingAir.isEmpty() || annealingAir.contains(clm.getAirflow());
		return a && b && c;
	}

	@Override
	public Block getOutput() {
		return output;
	}

	@Override
	public Block getFail() {
		return failure;
	}

	@Override
	public ItemStack getCurrentOutput(ItemStack in, IClimate climate) {
		if (input1.test(in)) {
			if (matchHeatingClimate(climate)) {
				if (getHeatingOutput() != null && ConfigCommonBuilder.INSTANCE.enHeatTreatment.get()) {
					return new ItemStack(getHeatingOutput());
				} else {
					return new ItemStack(getOutput());
				}
			} else if (climate.getHeat().getTier() > DCHeatTier.OVEN.getTier()) {
				return new ItemStack(getFail());
			} else {
				return ItemStack.EMPTY;
			}
		} else if (getCoolingOutput() != null && in.getItem() == getHeatingOutput().asItem()) {
			if (matchCoolingClimate(climate)) {
				return new ItemStack(getCoolingOutput());
			} else {
				return new ItemStack(getFail());
			}
		} else if (in.getItem() == getCoolingOutput().asItem()) {
			if (matchAnnealingClimate(climate)) {
				return new ItemStack(getOutput());
			} else {
				return new ItemStack(getFail());
			}
		} else {
			return ItemStack.EMPTY;
		}
	}

}
