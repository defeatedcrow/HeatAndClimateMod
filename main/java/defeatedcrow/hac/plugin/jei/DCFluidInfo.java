package defeatedcrow.hac.plugin.jei;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.fluid.FluidDic;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.plugin.DrinkPotionType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class DCFluidInfo {

	public final DCHeatTier temp;
	public final DCHumidity hum;
	public final DCAirflow air;
	public final Fluid fluid;
	public final FluidStack stack;
	public final Potion potion;
	public final PotionEffect effect;
	public final boolean clearPotion;
	public final String dictionary;

	public DCFluidInfo(Fluid recipe) {
		fluid = recipe;
		stack = new FluidStack(recipe, 1000);
		Block block = fluid.getBlock();
		if (block != null) {
			if (ClimateAPI.registerBlock.isRegisteredHeat(block, 0)) {
				temp = ClimateAPI.registerBlock.getHeatTier(block, 0);
			} else {
				temp = DCHeatTier.getTypeByTemperature(fluid.getTemperature());
			}
		} else {
			temp = null;
		}
		if (block != null) {
			if (ClimateAPI.registerBlock.isRegisteredHum(block, 0)) {
				hum = ClimateAPI.registerBlock.getHumidity(block, 0);
			} else if (block.getDefaultState().getMaterial() == Material.WATER) {
				hum = DCHumidity.UNDERWATER;
			} else {
				hum = DCHumidity.NORMAL;
			}
		} else {
			hum = null;
		}
		if (block != null) {
			if (ClimateAPI.registerBlock.isRegisteredAir(block, 0)) {
				air = ClimateAPI.registerBlock.getAirflow(block, 0);
			} else {
				air = DCAirflow.NORMAL;
			}
		} else {
			air = null;
		}
		FluidDic dic = FluidDictionaryDC.getDic(fluid);
		if (dic != null) {
			dictionary = dic.dicName;
		} else {
			dictionary = "No DicName";
		}
		if (DrinkPotionType.isRegistered(fluid)) {
			potion = DrinkPotionType.getPotion(recipe);
			if (potion == MobEffects.INSTANT_HEALTH || potion == MobEffects.INSTANT_DAMAGE) {
				effect = new PotionEffect(potion, 1, 0);
			} else if (potion.isBadEffect()) {
				effect = new PotionEffect(potion, 600, 0);
			} else {
				effect = new PotionEffect(potion, 1200, 0);
			}

		} else {
			potion = null;
			effect = null;
		}
		if (FluidDictionaryDC
				.matchFluidName(fluid, "milk") || fluid == MainInit.soyMilk || fluid == MainInit.tomatoJuice) {
			clearPotion = true;
		} else {
			clearPotion = false;
		}
	}

	public String getFluidName() {
		return fluid.getLocalizedName(new FluidStack(fluid, 1000)) + " (Dic: " + dictionary + ")";
	}

	public String getDrinkEffect() {
		String drink = "";
		if (clearPotion) {
			drink += I18n.format("dcs.tip.clear_potion");
		} else if (effect != null) {
			drink += I18n.format(effect.getEffectName()) + " " + I18n.format("potion.potency." + effect.getAmplifier())
					.trim() + "(" + Potion.getPotionDurationString(effect, 1.0F) + ")";
		} else {
			drink += I18n.format("No Effect");
		}
		return drink;
	}

}
