package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
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
		registerPotion(FluidRegistry.getFluid("ic2hydrogen"), MobEffects.HASTE);

		registerPotion(FluidRegistry.getFluid("ic2oxygen"), MobEffects.WATER_BREATHING);

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
				ClimateAPI.registerBlock.registerAirBlock(b4, 32767, DCAirflow.FLOW);
			}
		}

		Fluid f5 = FluidRegistry.getFluid("ic2steam");
		if (f5 != null) {
			Block b5 = f5.getBlock();
			if (b5 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b5, 32767, DCHeatTier.BOIL);
				ClimateAPI.registerBlock.registerAirBlock(b5, 32767, DCAirflow.NORMAL);
			}
		}

		registerPotion(FluidRegistry.getFluid("ic2biomass"), MobEffects.NAUSEA);

		registerPotion(FluidRegistry.getFluid("ic2uu_matter"), MobEffects.GLOWING);

		Fluid f8 = FluidRegistry.getFluid("ic2hot_water");
		if (f8 != null) {
			Block b8 = f8.getBlock();
			if (b8 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b8, 32767, DCHeatTier.BOIL);
			}
		}

		Fluid f9 = FluidRegistry.getFluid("ic2hot_coolant");
		if (f9 != null) {
			Block b9 = f9.getBlock();
			if (b9 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b9, 32767, DCHeatTier.BOIL);
			}
		}

		registerPotion(FluidRegistry.getFluid("ic2weed_ex"), MobEffects.POISON);

		Fluid f11 = FluidRegistry.getFluid("ic2superheated_steam");
		registerPotion(f11, MobEffects.SPEED);
		if (f11 != null) {
			Block b11 = f11.getBlock();
			if (b11 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b11, 32767, DCHeatTier.OVEN);
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
		registerPotion(FluidRegistry.getFluid("juice"), MobEffects.LUCK);

		Fluid f14 = FluidRegistry.getFluid("ice");
		if (f14 != null) {
			registerPotion(f14, DCInit.prevFreeze);
			Block b14 = f14.getBlock();
			if (b14 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b14, 32767, DCHeatTier.FROSTBITE);
			}
		}

		registerPotion(FluidRegistry.getFluid("honey"), MobEffects.RESISTANCE);

		Fluid f16 = FluidRegistry.getFluid("seed.oil");
		registerPotion(f16, MobEffects.HASTE);
		if (f16 != null) {
			FluidDictionaryDC.registerFluidDic(f16, "plant_oil");
		}

		registerPotion(FluidRegistry.getFluid("for.honey"), MobEffects.RESISTANCE);

		Fluid f18 = FluidRegistry.getFluid("bio.ethanol");
		registerPotion(f18, MobEffects.BLINDNESS);
		if (f18 != null) {
			FluidDictionaryDC.registerFluidDic(f18, "ethanol");
		}

		registerPotion(FluidRegistry.getFluid("biomass"), MobEffects.NAUSEA);

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

		registerPotion(FluidRegistry.getFluid("blood"), MobEffects.STRENGTH);

		registerPotion(FluidRegistry.getFluid("poison"), MobEffects.WITHER);

		registerPotion(FluidRegistry.getFluid("hot_spring_water"), MobEffects.RESISTANCE);

		// mek
		registerPotion(FluidRegistry.getFluid("brine"), MobEffects.RESISTANCE);

		registerPotion(FluidRegistry.getFluid("liquidchlorine"), MobEffects.LEVITATION);

		Fluid f26 = FluidRegistry.getFluid("liquidsulfurdioxide");
		registerPotion(f26, MobEffects.WEAKNESS);
		if (f26 != null) {
			Block b26 = f26.getBlock();
			if (b26 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b26, 32767, DCHumidity.DRY);
			}
		}

		Fluid f27 = FluidRegistry.getFluid("liquidsulfurtrioxide");
		registerPotion(f27, MobEffects.WITHER);
		if (f27 != null) {
			Block b27 = f27.getBlock();
			if (b27 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b27, 32767, DCHumidity.DRY);
			}
		}

		registerPotion(FluidRegistry.getFluid("liquidsodium"), MobEffects.INSTANT_DAMAGE);

		registerPotion(FluidRegistry.getFluid("liquidlithium"), MobEffects.INSTANT_DAMAGE);

		registerPotion(FluidRegistry.getFluid("liquidfusionfuel"), MobEffects.INVISIBILITY);

		Fluid f31 = FluidRegistry.getFluid("sulfuricacid");
		registerPotion(f31, MobEffects.MINING_FATIGUE);
		if (f31 != null) {
			Block b31 = f31.getBlock();
			if (b31 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b31, 32767, DCHumidity.DRY);
			}
		}

		Fluid f32 = FluidRegistry.getFluid("steam");
		registerPotion(f32, DCInit.prevFreeze);
		if (f32 != null) {
			Block b32 = f32.getBlock();
			if (b32 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b32, 32767, DCHeatTier.BOIL);
			}
		}

		registerPotion(FluidRegistry.getFluid("heavywater"), MainInit.gravity);

		registerPotion(FluidRegistry.getFluid("liquiddeuterium"), MainInit.heavyboots);

		registerPotion(FluidRegistry.getFluid("liquidtritium"), MainInit.heavyboots);

		registerPotion(FluidRegistry.getFluid("creosote"), MobEffects.NAUSEA);

		registerPotion(FluidRegistry.getFluid("liquidoxygen"), MobEffects.WATER_BREATHING);

		// dcs
		registerPotion(FluidRegistry.getFluid("dcs.hydrogen"), MobEffects.SPEED);

		registerPotion(FluidRegistry.getFluid("dcs.ammonia"), MobEffects.LEVITATION);

		registerPotion(FluidRegistry.getFluid("dcs.fuel_oil"), MobEffects.HASTE);

		registerPotion(FluidRegistry.getFluid("dcs.fuel_gas"), MobEffects.STRENGTH);

		registerPotion(FluidRegistry.getFluid("dcs.nitric_acid"), MobEffects.NAUSEA);

		registerPotion(FluidRegistry.getFluid("dcs.sulfuric_acid"), MobEffects.POISON);

		registerPotion(FluidRegistry.getFluid("dcs.nitrogen"), DCInit.prevFreeze);

		registerPotion(FluidRegistry.getFluid("dcs.ethanol"), MobEffects.HASTE);

		registerPotion(FluidRegistry.getFluid("dcs.milk_cream"), MobEffects.INSTANT_HEALTH);

		registerPotion(FluidRegistry.getFluid("dcs.hotspring"), MobEffects.REGENERATION);

		// animania
		registerPotion(FluidRegistry.getFluid("milk_holstein"), MobEffects.REGENERATION);

		registerPotion(FluidRegistry.getFluid("milk_friesian"), MobEffects.REGENERATION);

		registerPotion(FluidRegistry.getFluid("milk_jersey"), MobEffects.REGENERATION);

		registerPotion(FluidRegistry.getFluid("milk_goat"), MobEffects.REGENERATION);

		registerPotion(FluidRegistry.getFluid("milk_sheep"), MobEffects.REGENERATION);

		registerPotion(FluidRegistry.getFluid("fuelium"), MobEffects.HASTE);

		// fuel
		MainAPIManager.fuelRegister.registerFuel("ic2biomass", 100);
		MainAPIManager.fuelRegister.registerFuel("seed.oil", 60);
		MainAPIManager.fuelRegister.registerFuel("bio.ethanol", 100);
		MainAPIManager.fuelRegister.registerFuel("biomass", 60);
		MainAPIManager.fuelRegister.registerFuel("liquidethene", 120);
		MainAPIManager.fuelRegister.registerFuel("creosote", 60);
		MainAPIManager.fuelRegister.registerFuel("fuelium", 100);

		MainAPIManager.fuelRegister.registerFuel("dcs.fuel_oil", 150);
		MainAPIManager.fuelRegister.registerFuel("dcs.fuel_gas", 120);
		MainAPIManager.fuelRegister.registerFuel("dcs.ethanol", 60);

	}

	public static void registerPotion(Fluid f, Potion p) {
		if (f != null) {
			DrinkPotionType.registerPotion(f.getName(), p);
		}
	}

}
