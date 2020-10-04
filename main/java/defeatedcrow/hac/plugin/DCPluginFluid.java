package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.block.Block;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class DCPluginFluid {

	public static final DCPluginFluid INSTANCE = new DCPluginFluid();

	private DCPluginFluid() {}

	public static void load() {

		// dic
		registerFluidDic("milk", "milk");
		registerFluidDic("ic2steam", "steam");
		registerFluidDic("steam", "steam");
		registerFluidDic("ic2hot_water", "hot_spring");
		registerFluidDic("hot_spring_water", "hot_spring");
		registerFluidDic("ic2weed_ex", "poison");
		registerFluidDic("poison", "poison");
		registerFluidDic("honey", "honey");
		registerFluidDic("for.honey", "honey");
		registerFluidDic("blood", "blood");
		registerFluidDic("nitricacid", "nitricacid");
		registerFluidDic("sulfuricacid", "sulfuricacid");
		registerFluidDic("milk_holstein", "milk");
		registerFluidDic("milk_friesian", "milk");
		registerFluidDic("milk_jersey", "milk");
		registerFluidDic("milk_goat", "milk");
		registerFluidDic("milk_sheep", "milk");

		// dcs
		registerFluidDic("dcs.nitric_acid", "nitricacid");
		registerFluidDic("dcs.sulfuric_acid", "sulfuricacid");
		registerFluidDic("dcs.ammonia", "ammonia");
		registerFluidDic("dcs.nitrogen", "nitrogen");
		registerFluidDic("dcs.green_tea", "greentea");
		registerFluidDic("dcs.black_tea", "blacktea");
		registerFluidDic("dcs.black_coffee", "coffee");
		registerFluidDic("dcs.milk_cream", "cream");
		registerFluidDic("dcs.lemonade", "lemonade");
		registerFluidDic("dcs.vegetable_juice", "vegetable");
		registerFluidDic("dcs.mazai", "mazai");
		registerFluidDic("dcs.hotspring", "hot_spring");
		registerFluidDic("dcs.stock", "stock");
		registerFluidDic("dcs.soy_milk", "soymilk");
		registerFluidDic("dcs.raw_milk", "milk");
		registerFluidDic("dcs.steam", "steam");
		if (ModuleConfig.food && ModuleConfig.food_advanced) {
			registerFluidDic("dcs.beer", "beer");
			registerFluidDic("dcs.wine", "wine");
		}

		// fuel
		registerFuel("ic2hydrogen", "hydrogen", 100);
		registerFuel("liquidhydrogen", "hydrogen", 100);
		registerFuel("coal", "coal", 20);
		registerFuel("oil", "coal", 20);
		registerFuel("ic2biomass", "biomass", 60);
		registerFuel("biomass", "biomass", 60);
		registerFuel("biocrude", "biomass", 60);
		registerFuel("seed.oil", "seed_oil", 30);
		registerFuel("tree_oil", "tree_oil", 30);
		registerFuel("creosote", "creosote", 30);
		registerFuel("bio.ethanol", "ethanol", 80);
		registerFuel("hootch", "ethanol", 80);
		registerFuel("fire_water", "fire_water", 100);
		registerFuel("refined_oil", "naphtha", 100);
		registerFuel("liquidethene", "fuel_gaseous", 120);
		registerFuel("fuel_gaseous", "fuel_gaseous", 120);
		registerFuel("fuelium", "fuelium", 150);
		registerFuel("rocket_fuel", "fuel_oil", 150);
		registerFuel("refined_fuel", "fuel_oil", 150);
		registerFuel("fuel_light", "fuel_oil", 150);
		registerFuel("refined_biofuel", "fuel_dence", 120);
		registerFuel("dense", "fuel_dence", 120);

		// dcs
		registerFuel("dcs.hydrogen", "hydrogen", 100);
		registerFuel("dcs.fuel_oil", "fuel_oil", 150);
		registerFuel("dcs.fuel_gas", "fuel_gaseous", 120);
		registerFuel("dcs.seed_oil", "seed_oil", 30);
		registerFuel("dcs.ethanol", "ethanol", 80);
		registerFuel("dcs.black_liquor", "black_liquor", 30);

		// potion
		registerPotion("lava", MobEffects.FIRE_RESISTANCE);
		registerPotion("ic2hydrogen", MobEffects.HASTE);
		registerPotion("ic2air", MobEffects.WATER_BREATHING);
		registerPotion("ic2oxygen", MobEffects.WATER_BREATHING);
		registerPotion("ic2steam", DCInit.prevFreeze);
		registerPotion("ic2biomass", MobEffects.NAUSEA);
		registerPotion("ic2uu_matter", MobEffects.GLOWING);
		registerPotion("ic2weed_ex", MobEffects.POISON);
		registerPotion("ic2superheated_steam", MobEffects.SPEED);
		registerPotion("ic2pahoehoe_lava", MobEffects.FIRE_RESISTANCE);
		registerPotion("juice", MobEffects.LUCK);
		registerPotion("ice", DCInit.prevFreeze);
		registerPotion("honey", MobEffects.RESISTANCE);
		registerPotion("seed.oil", MobEffects.HASTE);
		registerPotion("for.honey", MobEffects.RESISTANCE);
		registerPotion("bio.ethanol", MobEffects.BLINDNESS);
		registerPotion("biomass", MobEffects.NAUSEA);
		registerPotion("glass", MobEffects.FIRE_RESISTANCE);
		registerPotion("sand", MobEffects.SLOWNESS);
		registerPotion("blood", MobEffects.STRENGTH);
		registerPotion("poison", MobEffects.WITHER);
		registerPotion("hot_spring_water", MobEffects.RESISTANCE);
		registerPotion("brine", MobEffects.RESISTANCE);
		registerPotion("liquidchlorine", MobEffects.LEVITATION);
		registerPotion("liquidsulfurdioxide", MobEffects.WEAKNESS);
		registerPotion("liquidsulfurtrioxide", MobEffects.WITHER);
		registerPotion("liquidsodium", MobEffects.INSTANT_DAMAGE);
		registerPotion("liquidlithium", MobEffects.INSTANT_DAMAGE);
		registerPotion("liquidfusionfuel", MobEffects.INVISIBILITY);
		registerPotion("sulfuricacid", MobEffects.MINING_FATIGUE);
		registerPotion("steam", DCInit.prevFreeze);
		registerPotion("heavywater", MainInit.gravity);
		registerPotion("liquiddeuterium", MainInit.heavyboots);
		registerPotion("liquidtritium", MainInit.heavyboots);
		registerPotion("creosote", MobEffects.NAUSEA);
		registerPotion("liquidoxygen", MobEffects.WATER_BREATHING);
		registerPotion("liquidhydrogen", MobEffects.HASTE);
		registerPotion("milk_holstein", MobEffects.REGENERATION);
		registerPotion("milk_friesian", MobEffects.REGENERATION);
		registerPotion("milk_jersey", MobEffects.REGENERATION);
		registerPotion("milk_goat", MobEffects.REGENERATION);
		registerPotion("milk_sheep", MobEffects.REGENERATION);
		registerPotion("fuelium", MobEffects.HASTE);
		registerPotion("hootch", MobEffects.HASTE);
		registerPotion("fire_water", MobEffects.STRENGTH);
		registerPotion("rocket_fuel", MobEffects.STRENGTH);
		registerPotion("crude_oil", MainInit.gravity);
		registerPotion("coal", MainInit.gravity);
		registerPotion("resin", MobEffects.WEAKNESS);
		registerPotion("tree_oil", MobEffects.RESISTANCE);
		registerPotion("refined_oil", MobEffects.HASTE);
		registerPotion("refined_fuel", MobEffects.HASTE);
		registerPotion("refined_biofuel", MobEffects.SPEED);
		registerPotion("biocrude", MobEffects.SLOWNESS);
		registerPotion("redstone", MobEffects.JUMP_BOOST);
		registerPotion("glowstone", MobEffects.GLOWING);
		registerPotion("ender", MobEffects.NAUSEA);
		registerPotion("pyrotheum", MobEffects.FIRE_RESISTANCE);
		registerPotion("cryotheum", DCInit.prevFreeze);
		registerPotion("aerotheum", MainInit.bird);
		registerPotion("petrotheum", MainInit.heavyboots);
		registerPotion("mana", MainInit.ocean);
		registerPotion("oil", MobEffects.WITHER);
		registerPotion("fuel_gaseous", MobEffects.LEVITATION);
		registerPotion("fuel_light", MobEffects.SPEED);
		registerPotion("dense", MobEffects.STRENGTH);
		registerPotion("oil_heavy", MobEffects.BLINDNESS);
		registerPotion("oil_dense", MobEffects.BLINDNESS);
		registerPotion("oil_distilled", MobEffects.HUNGER);
		registerPotion("oil_residue", MobEffects.BLINDNESS);

		// dcs
		registerPotion("dcs.hydrogen", MobEffects.SPEED);
		registerPotion("dcs.ammonia", MobEffects.LEVITATION);
		registerPotion("dcs.fuel_oil", MobEffects.HASTE);
		registerPotion("dcs.fuel_gas", MobEffects.STRENGTH);
		registerPotion("dcs.nitric_acid", MobEffects.NAUSEA);
		registerPotion("dcs.sulfuric_acid", MobEffects.POISON);
		registerPotion("dcs.nitrogen", DCInit.prevFreeze);
		registerPotion("dcs.ethanol", MobEffects.HASTE);
		registerPotion("dcs.milk_cream", MobEffects.INSTANT_HEALTH);
		registerPotion("dcs.hotspring", MobEffects.REGENERATION);
		registerPotion("dcs.steam", MobEffects.LEVITATION);
		registerPotion("dcs.green_tea", MobEffects.HASTE);
		registerPotion("dcs.black_tea", MobEffects.RESISTANCE);
		registerPotion("dcs.black_coffee", MobEffects.NIGHT_VISION);
		registerPotion("dcs.milk_crean", DCInit.prevFreeze);
		registerPotion("dcs.seed_oil", MobEffects.SPEED);
		registerPotion("dcs.stock", MobEffects.FIRE_RESISTANCE);
		registerPotion("dcs.lemonade", MobEffects.JUMP_BOOST);
		registerPotion("dcs.black_liquor", MobEffects.POISON);
		if (ModuleConfig.food && ModuleConfig.food_advanced) {
			registerPotion("dcs.beer", MainInit.wideMining);
			registerPotion("dcs.wine", MainInit.wideMining);
		}

		Fluid f1 = FluidRegistry.getFluid("ic2coolant");
		if (f1 != null) {
			Block b1 = f1.getBlock();
			if (b1 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b1, 32767, DCHeatTier.COLD);
			}
		}

		Fluid f2 = FluidRegistry.getFluid("ic2air");

		if (f2 != null) {
			Block b2 = f2.getBlock();
			if (b2 != null) {
				ClimateAPI.registerBlock.registerAirBlock(b2, 32767, DCAirflow.FLOW);
			}
		}

		Fluid f3 = FluidRegistry.getFluid("ic2steam");
		if (f3 != null) {
			Block b3 = f3.getBlock();
			if (b3 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b3, 32767, DCHeatTier.BOIL);
				ClimateAPI.registerBlock.registerAirBlock(b3, 32767, DCAirflow.NORMAL);
			}
		}

		Fluid f4 = FluidRegistry.getFluid("ic2hot_water");
		if (f4 != null) {
			Block b4 = f4.getBlock();
			if (b4 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b4, 32767, DCHeatTier.BOIL);
			}
		}

		Fluid f5 = FluidRegistry.getFluid("ic2hot_coolant");
		if (f5 != null) {
			Block b5 = f5.getBlock();
			if (b5 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b5, 32767, DCHeatTier.BOIL);
			}
		}

		Fluid f6 = FluidRegistry.getFluid("ic2superheated_steam");
		if (f6 != null) {
			Block b6 = f6.getBlock();
			if (b6 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b6, 32767, DCHeatTier.OVEN);
			}
		}

		Fluid f7 = FluidRegistry.getFluid("ic2pahoehoe_lava");
		if (f7 != null) {
			Block b7 = f7.getBlock();
			if (b7 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b7, 32767, DCHeatTier.KILN);
			}
		}

		Fluid f8 = FluidRegistry.getFluid("aerotheum");
		if (f8 != null) {
			Block b8 = f8.getBlock();
			if (b8 != null) {
				ClimateAPI.registerBlock.registerAirBlock(b8, 32767, DCAirflow.FLOW);
			}
		}

		// forestry
		Fluid f14 = FluidRegistry.getFluid("ice");
		if (f14 != null) {
			Block b14 = f14.getBlock();
			if (b14 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b14, 32767, DCHeatTier.FROSTBITE);
			}
		}

		Fluid f19 = FluidRegistry.getFluid("glass");
		if (f19 != null) {
			Block b19 = f19.getBlock();
			if (b19 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b19, 32767, DCHeatTier.KILN);
			}
		}

		// bop
		Fluid f20 = FluidRegistry.getFluid("sand");
		if (f20 != null) {
			Block b20 = f20.getBlock();
			if (b20 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b20, 32767, DCHumidity.DRY);
			}
		}

		// mek
		Fluid f26 = FluidRegistry.getFluid("liquidsulfurdioxide");
		if (f26 != null) {
			Block b26 = f26.getBlock();
			if (b26 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b26, 32767, DCHumidity.DRY);
			}
		}

		Fluid f27 = FluidRegistry.getFluid("liquidsulfurtrioxide");
		if (f27 != null) {
			Block b27 = f27.getBlock();
			if (b27 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b27, 32767, DCHumidity.DRY);
			}
		}

		Fluid f31 = FluidRegistry.getFluid("sulfuricacid");
		if (f31 != null) {
			Block b31 = f31.getBlock();
			if (b31 != null) {
				ClimateAPI.registerBlock.registerHumBlock(b31, 32767, DCHumidity.DRY);
			}
		}

		Fluid f32 = FluidRegistry.getFluid("steam");
		if (f32 != null) {
			Block b32 = f32.getBlock();
			if (b32 != null) {
				ClimateAPI.registerBlock.registerHeatBlock(b32, 32767, DCHeatTier.BOIL);
			}
		}

		Fluid f33 = FluidRegistry.getFluid("liquidoxygen");
		if (f33 != null) {
			Block b33 = f33.getBlock();
			if (b33 != null) {
				ClimateAPI.registerBlock.registerAirBlock(b33, 32767, DCAirflow.FLOW);
			}
		}

	}

	public static void registerPotion(String name, Potion p) {
		if (FluidRegistry.getFluid(name) != null) {
			Fluid f = FluidRegistry.getFluid(name);
			DrinkPotionType.registerPotion(f.getName(), p);
		}
	}

	public static void registerFluidDic(String name, String dic) {
		if (FluidRegistry.getFluid(name) != null) {
			Fluid f = FluidRegistry.getFluid(name);
			FluidDictionaryDC.registerFluidDic(f, dic);
		}
	}

	public static void registerFuel(String name, String dic, int amount) {
		if (FluidRegistry.getFluid(name) != null) {
			Fluid f = FluidRegistry.getFluid(name);
			MainAPIManager.fuelRegister.registerFuel(name, amount);
			FluidDictionaryDC.registerFluidDic(f, dic);
		}
	}

}
