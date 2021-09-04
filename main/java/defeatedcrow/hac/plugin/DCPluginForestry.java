package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.DamageAPI;
import defeatedcrow.hac.api.module.HaCModule;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.core.base.ClimateCropBase;
import defeatedcrow.hac.core.base.ClimateDoubleCropBase;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.recipes.device.RegisterBrewingDC;
import defeatedcrow.hac.main.recipes.device.RegisterCrusherRecipe;
import defeatedcrow.hac.plugin.forestry.DCFarmable;
import defeatedcrow.hac.plugin.forestry.DCFarmableDouble;
import forestry.api.core.ForestryAPI;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FermenterFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.recipes.RecipeManagers;
import forestry.lepidopterology.entities.EntityButterfly;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class DCPluginForestry {

	public static final DCPluginForestry INSTANCE = new DCPluginForestry();

	private DCPluginForestry() {}

	public static void loadInit() {

		Item mulch = Item.REGISTRY.getObject(new ResourceLocation("forestry", "mulch"));
		Item fer = Item.REGISTRY.getObject(new ResourceLocation("forestry", "fertilizer_compound"));
		Fluid seed = FluidRegistry.getFluid("seed.oil");
		Fluid juice = FluidRegistry.getFluid("juice");
		Fluid honey = FluidRegistry.getFluid("for.honey");
		Fluid bio = FluidRegistry.getFluid("biomass");
		Fluid eta = FluidRegistry.getFluid("bio.ethanol");

		if (ModuleConfig.food) {
			if (mulch == null || seed == null || juice == null)
				return;

			// squeeze
			NonNullList<ItemStack> l1 = NonNullList.create();
			l1.add(new ItemStack(FoodInit.crops, 1, 3));
			RecipeManagers.squeezerManager.addRecipe(10, l1, new FluidStack(juice, 200), new ItemStack(mulch), 10);

			NonNullList<ItemStack> l2 = NonNullList.create();
			l2.add(new ItemStack(FoodInit.crops, 1, 4));
			RecipeManagers.squeezerManager.addRecipe(20, l2, new FluidStack(juice, 100), new ItemStack(mulch), 10);

			NonNullList<ItemStack> l3 = NonNullList.create();
			l3.add(new ItemStack(FoodInit.crops, 1, 5));
			RecipeManagers.squeezerManager.addRecipe(20, l3, new FluidStack(seed, 50), new ItemStack(mulch), 10);

			NonNullList<ItemStack> l4 = NonNullList.create();
			l4.add(new ItemStack(FoodInit.crops, 1, 6));
			RecipeManagers.squeezerManager.addRecipe(10, l4, new FluidStack(MainInit.lemon, 400), new ItemStack(
					mulch), 10);

			NonNullList<ItemStack> l5 = NonNullList.create();
			l5.add(new ItemStack(FoodInit.crops, 1, 7));
			RecipeManagers.squeezerManager.addRecipe(10, l5, new FluidStack(MainInit.oil, 200), new ItemStack(
					mulch), 10);

			NonNullList<ItemStack> l6 = NonNullList.create();
			l6.add(new ItemStack(FoodInit.seeds, 1, 0));
			RecipeManagers.squeezerManager.addRecipe(20, l6, new FluidStack(seed, 50), new ItemStack(mulch), 10);

			NonNullList<ItemStack> l7 = NonNullList.create();
			l7.add(new ItemStack(FoodInit.seeds, 1, 1));
			RecipeManagers.squeezerManager.addRecipe(20, l7, new FluidStack(seed, 10), new ItemStack(mulch), 10);

			NonNullList<ItemStack> l8 = NonNullList.create();
			l8.add(new ItemStack(FoodInit.seeds, 1, 2));
			RecipeManagers.squeezerManager.addRecipe(20, l8, new FluidStack(seed, 10), new ItemStack(mulch), 10);

			NonNullList<ItemStack> l9 = NonNullList.create();
			l9.add(new ItemStack(FoodInit.seeds, 1, 3));
			RecipeManagers.squeezerManager.addRecipe(20, l9, new FluidStack(seed, 10), new ItemStack(mulch), 10);

			NonNullList<ItemStack> l10 = NonNullList.create();
			l10.add(new ItemStack(FoodInit.seeds, 1, 4));
			RecipeManagers.squeezerManager.addRecipe(20, l10, new FluidStack(seed, 20), new ItemStack(mulch), 10);

			NonNullList<ItemStack> l11 = NonNullList.create();
			l11.add(new ItemStack(FoodInit.seeds, 1, 5));
			RecipeManagers.squeezerManager.addRecipe(20, l11, new FluidStack(seed, 100), new ItemStack(mulch), 10);

			// fermentation
			if (honey != null && bio != null) {
				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.seeds, 1,
						0), 100, 1.0F, new FluidStack(bio, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1,
						1), 100, 1.0F, new FluidStack(bio, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 2), 50, 1.0F, new FluidStack(
						bio, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 8), 50, 1.0F, new FluidStack(
						bio, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 9), 50, 1.0F, new FluidStack(
						bio, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1,
						0), 250, 1.0F, new FluidStack(bio, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1,
						1), 250, 1.0F, new FluidStack(bio, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1,
						2), 250, 1.0F, new FluidStack(bio, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.seeds, 1,
						0), 100, 1.5F, new FluidStack(bio, 100), new FluidStack(honey, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1,
						1), 100, 1.5F, new FluidStack(bio, 100), new FluidStack(honey, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 2), 50, 1.5F, new FluidStack(
						bio, 50), new FluidStack(honey, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 8), 50, 1.5F, new FluidStack(
						bio, 50), new FluidStack(honey, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 9), 50, 1.5F, new FluidStack(
						bio, 50), new FluidStack(honey, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1,
						0), 250, 1.5F, new FluidStack(bio, 250), new FluidStack(honey, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1,
						1), 250, 1.5F, new FluidStack(bio, 250), new FluidStack(honey, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1,
						2), 250, 1.5F, new FluidStack(bio, 250), new FluidStack(honey, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.seeds, 1,
						0), 100, 1.5F, new FluidStack(bio, 100), new FluidStack(juice, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1,
						1), 100, 1.5F, new FluidStack(bio, 100), new FluidStack(juice, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 2), 50, 1.5F, new FluidStack(
						bio, 50), new FluidStack(juice, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 8), 50, 1.5F, new FluidStack(
						bio, 50), new FluidStack(juice, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 9), 50, 1.5F, new FluidStack(
						bio, 50), new FluidStack(juice, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1,
						0), 250, 1.5F, new FluidStack(bio, 250), new FluidStack(juice, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1,
						1), 250, 1.5F, new FluidStack(bio, 250), new FluidStack(juice, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1,
						2), 250, 1.5F, new FluidStack(bio, 250), new FluidStack(juice, 250));

			}

			Item slice = Item.REGISTRY.getObject(new ResourceLocation("forestry", "honeyed_slice"));
			if (slice != null) {
				DCRecipe.jsonShapedRecipe(HaCModule.getPlugin("forestry"), "plugin", new ItemStack(slice, 4,
						0), new Object[] {
								"XXX",
								"XYX",
								"XXX",
								'Y',
								"bread",
								'X',
								"dropHoney"
				});
			}

			if (fer != null) {
				DCRecipe.jsonShapelessRecipe(HaCModule.getPlugin("forestry"), "plugin", new ItemStack(fer, 1,
						0), new Object[] {
								"dustPresscake",
								"dustAsh",
								new ItemStack(Items.DYE, 1, 15)
				});
			}

			ItemStack oilcake = new ItemStack(MainInit.miscDust, 1, 4);
			FuelManager.fermenterFuel.put(oilcake, new FermenterFuel(oilcake, 48, 200));
			ForestryAPI.farmRegistry.registerFertilizer(oilcake, 200);

			Fluid oil = MainInit.oil;
			if (oil != null) {
				FuelManager.bronzeEngineFuel.put(oil, new EngineBronzeFuel(oil, 30, 2500, 1));
			}

			Fluid black = MainInit.blackLiquor;
			if (black != null) {
				FuelManager.bronzeEngineFuel.put(black, new EngineBronzeFuel(black, 30, 2500, 1));
			}

			Fluid liq = MainInit.fuelGas;
			if (liq != null) {
				FuelManager.bronzeEngineFuel.put(liq, new EngineBronzeFuel(liq, 40, 4000, 1));
			}

			Fluid gas = MainInit.fuelGas;
			if (gas != null) {
				FuelManager.bronzeEngineFuel.put(gas, new EngineBronzeFuel(gas, 60, 2500, 1));
			}

			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmable(
					(ClimateCropBase) FoodInit.cropRice));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmable(
					(ClimateCropBase) FoodInit.cropOnion));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmable(
					(ClimateCropBase) FoodInit.cropSpinach));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmable(
					(ClimateCropBase) FoodInit.cropHerb));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmable(
					(ClimateCropBase) FoodInit.cropBean));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmable(
					(ClimateCropBase) FoodInit.cropChili));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmable(
					(ClimateCropBase) FoodInit.cropGarlic));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmable(
					(ClimateCropBase) FoodInit.cropLettuce));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmable(
					(ClimateCropBase) FoodInit.cropGinger));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmableDouble(
					(ClimateDoubleCropBase) FoodInit.cropTomato));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmableDouble(
					(ClimateDoubleCropBase) FoodInit.cropCoffee));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmableDouble(
					(ClimateDoubleCropBase) FoodInit.cropCotton));
			ForestryAPI.farmRegistry.registerFarmables("farmCrops", new DCFarmableDouble(
					(ClimateDoubleCropBase) FoodInit.cropSoy));
		}

		DamageAPI.resistantData.registerEntityResistant(EntityButterfly.class, 2.0F, 2.0F);

		// HaC側のレシピ

		if (ModuleConfig.r_mill && ModuleConfig.food) {
			if (!OreDictionary.getOres("cropChestnut").isEmpty()) {
				RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 2, 0), new ItemStack(
						MainInit.miscDust, 1, 4), 0.25F, "cropChestnut");
			}

			if (!OreDictionary.getOres("cropHazelnut").isEmpty()) {
				RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(
						MainInit.miscDust, 1, 4), 0.25F, "cropHazelnut");
			}

			if (!OreDictionary.getOres("cropAlmond").isEmpty()) {
				RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(
						MainInit.miscDust, 1, 4), 0.25F, "cropAlmond");
			}
		}

		if (ModuleConfig.r_crusher) {
			if (!OreDictionary.getOres("cropChestnut").isEmpty()) {
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
						MainInit.oil, 200), RegisterCrusherRecipe.SUS_Blade, "cropChestnut");
			}

			if (!OreDictionary.getOres("cropHazelnut").isEmpty()) {
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
						MainInit.oil, 200), RegisterCrusherRecipe.SUS_Blade, "cropHazelnut");
			}

			if (!OreDictionary.getOres("cropAlmond").isEmpty()) {
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
						MainInit.oil, 200), RegisterCrusherRecipe.SUS_Blade, "cropAlmond");
			}
		}

		if (ModuleConfig.r_reactor) {
			if (bio != null && eta != null) {
				// Pt エタノール蒸留
				RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(eta,
						30), null, DCHeatTier.HOT, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(bio,
								100), null, new Object[] {});
			}
		}

		if (ModuleConfig.food) {
			if (ModuleConfig.food_advanced && ModuleConfig.r_brewing) {
				if (bio != null) {
					RegisterBrewingDC.brewng(ItemStack.EMPTY, new FluidStack(MainInit.fuelGas, 500), new FluidStack(bio,
							1000), new Object[] {
									new ItemStack(FoodInit.methanogen, 1, 0)
					});
				}
			}
		}

	}

}
