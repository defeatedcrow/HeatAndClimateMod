package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraft.block.Block;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class DCPluginFluid {

	public static final DCPluginFluid INSTANCE = new DCPluginFluid();

	private DCPluginFluid() {}

	public static void load() {

		// ic2
		Fluid f1 = FluidRegistry.getFluid("ic2hydrogen");
		registerPotion(f1, MobEffects.HASTE);

		Fluid f2 = FluidRegistry.getFluid("ic2oxygen");
		registerPotion(f2, MobEffects.WATER_BREATHING);

		Fluid f3 = FluidRegistry.getFluid("ic2coolant");
		if (f3 != null) {
			Block b3 = f3.getBlock();
			if (b3 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b3, 32767, DCHeatTier.COLD);
			}
		}

		Fluid f4 = FluidRegistry.getFluid("ic2air");
		registerPotion(f4, MobEffects.WATER_BREATHING);
		if (f4 != null) {
			Block b4 = f4.getBlock();
			if (b4 != null) {
				ClimateAPI.registerBlock.registerAirBlock(b4, 32767, DCAirflow.NORMAL);
			}
		}

		Fluid f5 = FluidRegistry.getFluid("ic2steam");
		if (f5 != null) {
			Block b5 = f5.getBlock();
			if (b5 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b5, 32767, DCHeatTier.OVEN);
				ClimateAPI.registerBlock.registerAirBlock(b5, 32767, DCAirflow.NORMAL);
			}
		}

		Fluid f6 = FluidRegistry.getFluid("ic2biomass");
		registerPotion(f6, MobEffects.NAUSEA);

		Fluid f7 = FluidRegistry.getFluid("ic2uu_matter");
		registerPotion(f7, MobEffects.GLOWING);

		Fluid f8 = FluidRegistry.getFluid("ic2hot_water");
		if (f8 != null) {
			Block b8 = f8.getBlock();
			if (b8 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b8, 32767, DCHeatTier.HOT);
			}
		}

		Fluid f9 = FluidRegistry.getFluid("ic2hot_coolant");
		if (f9 != null) {
			Block b9 = f9.getBlock();
			if (b9 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b9, 32767, DCHeatTier.HOT);
			}
		}

		Fluid f10 = FluidRegistry.getFluid("ic2weed_ex");
		registerPotion(f10, MobEffects.POISON);

		Fluid f11 = FluidRegistry.getFluid("ic2superheated_steam");
		registerPotion(f11, MobEffects.SPEED);
		if (f11 != null) {
			Block b11 = f11.getBlock();
			if (b11 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b11, 32767, DCHeatTier.KILN);
			}
		}

		Fluid f12 = FluidRegistry.getFluid("ic2pahoehoe_lava");
		registerPotion(f12, MobEffects.FIRE_RESISTANCE);
		if (f12 != null) {
			Block b12 = f12.getBlock();
			if (b12 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b12, 32767, DCHeatTier.KILN);
			}
		}

		// forestry
		Fluid f13 = FluidRegistry.getFluid("juice");
		registerPotion(f13, MobEffects.LUCK);

		Fluid f14 = FluidRegistry.getFluid("ice");
		if (f14 != null) {
			registerPotion(f14, MobEffects.WATER_BREATHING);
			Block b14 = f14.getBlock();
			if (b14 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b14, 32767, DCHeatTier.FROSTBITE);
			}
		}

		Fluid f15 = FluidRegistry.getFluid("honey");
		registerPotion(f15, MobEffects.RESISTANCE);

		Fluid f16 = FluidRegistry.getFluid("seed.oil");
		registerPotion(f16, MobEffects.HASTE);

		Fluid f17 = FluidRegistry.getFluid("for.honey");
		registerPotion(f17, MobEffects.RESISTANCE);

		Fluid f18 = FluidRegistry.getFluid("bio.ethanol");
		registerPotion(f18, MobEffects.BLINDNESS);

		Fluid f33 = FluidRegistry.getFluid("biomass");
		registerPotion(f33, MobEffects.NAUSEA);

		Fluid f19 = FluidRegistry.getFluid("glass");
		if (f19 != null) {
			registerPotion(f19, MobEffects.FIRE_RESISTANCE);
			Block b19 = f19.getBlock();
			if (b19 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b19, 32767, DCHeatTier.KILN);
			}
		}

		// bop
		Fluid f20 = FluidRegistry.getFluid("sand");
		if (f20 != null) {
			registerPotion(f20, MobEffects.SLOWNESS);
			Block b20 = f20.getBlock();
			if (b20 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b20, 32767, DCHumidity.DRY);
			}
		}

		Fluid f21 = FluidRegistry.getFluid("blood");
		registerPotion(f21, MobEffects.STRENGTH);

		Fluid f22 = FluidRegistry.getFluid("poison");
		registerPotion(f22, MobEffects.WITHER);

		Fluid f23 = FluidRegistry.getFluid("hot_spring_water");
		registerPotion(f23, MobEffects.RESISTANCE);

		// mek
		Fluid f24 = FluidRegistry.getFluid("bline");
		registerPotion(f24, MobEffects.RESISTANCE);

		Fluid f25 = FluidRegistry.getFluid("chlorine");
		registerPotion(f25, MobEffects.LEVITATION);

		Fluid f26 = FluidRegistry.getFluid("sulfurdioxidegas");
		registerPotion(f26, MobEffects.WEAKNESS);
		if (f26 != null) {
			Block b26 = f26.getBlock();
			if (b26 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b26, 32767, DCHumidity.DRY);
			}
		}

		Fluid f27 = FluidRegistry.getFluid("sulfurtrioxidegas");
		registerPotion(f27, MobEffects.WITHER);
		if (f27 != null) {
			Block b27 = f27.getBlock();
			if (b27 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b27, 32767, DCHumidity.DRY);
			}
		}

		Fluid f28 = FluidRegistry.getFluid("sodium");
		registerPotion(f28, MobEffects.INSTANT_DAMAGE);

		Fluid f29 = FluidRegistry.getFluid("lithium");
		registerPotion(f29, MobEffects.INSTANT_DAMAGE);

		Fluid f30 = FluidRegistry.getFluid("fusionfueldt");
		registerPotion(f30, MobEffects.INVISIBILITY);

		Fluid f31 = FluidRegistry.getFluid("sulfuricacid");
		registerPotion(f31, MobEffects.MINING_FATIGUE);
		if (f31 != null) {
			Block b31 = f31.getBlock();
			if (b31 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b31, 32767, DCHumidity.DRY);
			}
		}

		Fluid f32 = FluidRegistry.getFluid("steam");
		registerPotion(f32, MobEffects.MINING_FATIGUE);
		if (f32 != null) {
			Block b32 = f32.getBlock();
			if (b32 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b32, 32767, DCHeatTier.OVEN);
			}
		}

		Fluid f35 = FluidRegistry.getFluid("heavywater");
		registerPotion(f35, MainInit.gravity);

		Fluid f36 = FluidRegistry.getFluid("creosote");
		registerPotion(f36, MobEffects.NAUSEA);

		Fluid f37 = FluidRegistry.getFluid("oxigen");
		registerPotion(f37, MobEffects.WATER_BREATHING);

		Fluid f38 = FluidRegistry.getFluid("dcs.hydrogen");
		registerPotion(f38, MobEffects.SPEED);

		Fluid f39 = FluidRegistry.getFluid("dcs.ammonia");
		registerPotion(f39, MobEffects.LEVITATION);

		Fluid f40 = FluidRegistry.getFluid("dcs.fuel_oil");
		registerPotion(f40, MobEffects.HASTE);

		Fluid f41 = FluidRegistry.getFluid("dcs.fuel_gas");
		registerPotion(f41, MobEffects.STRENGTH);

		Fluid f42 = FluidRegistry.getFluid("dcs.nitric_acid");
		registerPotion(f42, MobEffects.NAUSEA);

		Fluid f43 = FluidRegistry.getFluid("dcs.sulfuric_acid");
		registerPotion(f43, MobEffects.POISON);

		// fuel
		MainAPIManager.fuelRegister.registerFuel("ic2biomass", 100);
		MainAPIManager.fuelRegister.registerFuel("seed.oil", 60);
		MainAPIManager.fuelRegister.registerFuel("bio.ethanol", 100);
		MainAPIManager.fuelRegister.registerFuel("biomass", 60);
		MainAPIManager.fuelRegister.registerFuel("fusionfueldt", 120);
		MainAPIManager.fuelRegister.registerFuel("ethene", 100);
		MainAPIManager.fuelRegister.registerFuel("creosote", 60);

		MainAPIManager.fuelRegister.registerFuel("dcs.fuel_oil", 120);
		MainAPIManager.fuelRegister.registerFuel("dcs.fuel_gas", 150);

	}

	static void registerPotion(Fluid f, Potion p) {
		if (f != null) {
			DrinkPotionType.registerPotion(f.getName(), p);
		}
	}

}
