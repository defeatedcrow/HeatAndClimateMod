package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.FluidCraftRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FoodFluidRecipe {

	public static void load() {
		loadFluidRecipes();
		loadCookingRecipes();
	}

	static void loadFluidRecipes() {

		regNonFoodrecipe(null, null, 0F, new FluidStack(FoodInit.hotSpring, 1000), DCHeatTier.HOT, null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSalt"
				});

		regNonFoodrecipe(null, null, 0F, new FluidStack(FoodInit.hotSpring, 1000), DCHeatTier.HOT, null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSulfur"
				});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 4, 0), null, 0F, new FluidStack(FoodInit.blackLiquor, 50),
				DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"pulpWood",
						"pulpWood",
						"dustAsh"
				});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 4, 0), null, 0F, new FluidStack(FoodInit.blackLiquor, 50),
				DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"pulpWood",
						"pulpWood",
						"dustLime"
				});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 4, 0), null, 0F, new FluidStack(FoodInit.blackLiquor, 50),
				DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"dustPlant",
						"dustPlant",
						"dustAsh"
				});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 4, 0), null, 0F, new FluidStack(FoodInit.blackLiquor, 50),
				DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"dustPlant",
						"dustPlant",
						"dustLime"
				});

		regNonFoodrecipe(new ItemStack(MainInit.repairPutty, 1, 2), null, 0F, null, DCHeatTier.NORMAL, null, null,
				false, new FluidStack(FoodInit.oil, 500), new Object[] {
						"dustAsh"
				});

		regNonFoodrecipe(new ItemStack(MainInit.repairPutty, 1, 2), null, 0F, null, DCHeatTier.NORMAL, null, null,
				false, new FluidStack(FoodInit.oil, 500), new Object[] {
						"dustLime"
				});

		regNonFoodrecipe(new ItemStack(MainInit.repairPutty, 1, 2), null, 0F, null, DCHeatTier.NORMAL, null, null,
				false, new FluidStack(FoodInit.oil, 500), new Object[] {
						"dustAlkali"
				});

		regNonFoodrecipe(new ItemStack(MainInit.gems, 1, 17), null, 0F, null, DCHeatTier.KILN, null, null, false, null,
				new Object[] {
						"dustBismuth"
				});

		regNonFoodrecipe(new ItemStack(MainInit.gems, 1, 18), null, 0F, null, DCHeatTier.KILN, null, null, false, null,
				new Object[] {
						"dustApatite"
				});

		regNonFoodrecipe(new ItemStack(MainInit.miscDust, 1, 9), null, 0F, null, DCHeatTier.OVEN, null, null, false,
				new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						new ItemStack(Items.DYE, 1, 15)
				});

		regNonFoodrecipe(new ItemStack(Items.SLIME_BALL, 1, 0), null, 0F, null, DCHeatTier.OVEN, null, null, false,
				new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						"cropHerb",
						"dustCrystal",
						new ItemStack(MachineInit.reagent, 1, 1)
				});

		regNonFoodrecipe(new ItemStack(FoodInit.meat, 1, 4), null, 0F, null, DCHeatTier.OVEN, null, null, false,
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"itemLeather"
				});

		// 醸造
		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MachineInit.ethanol, 300),
				DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustStarch",
						"foodMalt"
				});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MachineInit.ethanol, 200),
				DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"foodMalt"
				});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MachineInit.ethanol, 150),
				DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"cropPotato",
						"foodMalt"
				});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MachineInit.ethanol, 100),
				DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"foodFlour",
						"foodMalt"
				});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MachineInit.ethanol, 150),
				DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"foodRice",
						"foodMalt"
				});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MachineInit.ethanol, 100),
				DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dropMolasses",
						"foodMalt"
				});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null,
				false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"cropWheat"
				});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null,
				false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						new ItemStack(Items.WHEAT_SEEDS, 1, 0)
				});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 9, 3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null,
				false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						new ItemStack(Blocks.HAY_BLOCK, 1, 0)
				});

		regNonFoodrecipe(new ItemStack(MainInit.gems, 1, 3), null, 0F, null, DCHeatTier.NORMAL, null, null, false,
				new FluidStack(MachineInit.sulfuricAcid, 200), new Object[] {
						"dustLime"
				});

		regNonFoodrecipe(new ItemStack(MainInit.clothes, 1, 6), null, 0F, null, DCHeatTier.OVEN, null, null, false,
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						new ItemStack(MainInit.silkworm, 1, 2),
						new ItemStack(MainInit.silkworm, 1, 2),
						new ItemStack(MainInit.silkworm, 1, 2)
				});

		regNonFoodrecipe(new ItemStack(MainInit.clothes, 1, 8), null, 0F, null, DCHeatTier.OVEN, null, null, false,
				new FluidStack(FoodInit.hotSpring, 1000), new Object[] {
						new ItemStack(MainInit.clothes, 1, 6),
						"dustSilver",
						"dustMica"
				});

		regNonFoodrecipe(new ItemStack(MainInit.clothes, 1, 9), null, 0F, null, DCHeatTier.OVEN, null, null, false,
				new FluidStack(FoodInit.hotSpring, 1000), new Object[] {
						new ItemStack(MainInit.clothes, 1, 7),
						new ItemStack(Items.CHORUS_FRUIT, 1, 0),
						new ItemStack(Items.GHAST_TEAR, 1, 0)
				});

	}

	static void loadCookingRecipes() {
		if (MainInit.milk != null) {
			RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.cream, 200),
					DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.milk, 1000), (Object[]) null);
		}

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.tomatoJuice, 200),
				DCHeatTier.OVEN, null, null, false, null, new Object[] {
						"listAllveggie"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.lemon, 200), DCHeatTier.HOT,
				null, null, false, null, new Object[] {
						"cropLemon",
						"dustSugar"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.lemon, 200), DCHeatTier.HOT,
				null, null, false, null, new Object[] {
						"cropLemon",
						"dropHoney"
				});

		regBoilrecipe(null, null, 0F, new FluidStack(FoodInit.coffee, 1000), null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 0)
				});

		regBoilrecipe(null, null, 0F, new FluidStack(FoodInit.greenTea, 1000), null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 1)
				});

		regBoilrecipe(null, null, 0F, new FluidStack(FoodInit.blackTea, 1000), null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 2)
				});

		regBoilrecipe(null, null, 0F, new FluidStack(FoodInit.stock, 1000), null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"foodAnyMeat",
						"cropHerb",
						"listAllveggie"
				});

		regBoilrecipe(null, null, 0F, new FluidStack(FoodInit.stock, 1000), null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"cropSeaweed"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FluidRegistry.WATER, 1000),
				DCHeatTier.HOT, null, null, false, null, new Object[] {
						new ItemStack(Blocks.ICE, 1, 0)
				});

		FluidCraftRecipe salt = new FluidCraftRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), null, null,
				DCHeatTier.HOT, DCHumidity.DRY, null, 0, false, new FluidStack(FluidRegistry.WATER, 1000),
				(Object[]) null);
		RecipeAPI.registerFluidRecipes.addRecipe(salt, DCHeatTier.HOT);

		FluidCraftRecipe salt2 = new FluidCraftRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), null, null,
				DCHeatTier.KILN, DCHumidity.DRY, null, 0, false, new FluidStack(FluidRegistry.WATER, 1000),
				(Object[]) null);
		salt2.requiredHum().add(DCHumidity.NORMAL);
		salt2.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerFluidRecipes.addRecipe(salt2, DCHeatTier.KILN);

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 1, 2), null, 0F, null,
				DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
						"dustSalt",
						new ItemStack(Items.ROTTEN_FLESH)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 3, 2), null, 0F, null,
				DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
						"dustSalt",
						new ItemStack(Items.BEEF)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 3, 2), null, 0F, null,
				DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
						"dustSalt",
						new ItemStack(Items.PORKCHOP)
				});

		regBoilrecipe(new ItemStack(MainInit.bakedApple, 1, 1), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						"dustSalt",
						new ItemStack(Items.EGG)
				});

		regBoilrecipe(new ItemStack(MainInit.bakedApple, 3, 3), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						"dustSalt",
						"cropHerb",
						"foodAnyMeat"
				});

		regBoilrecipe(new ItemStack(MainInit.bakedApple, 3, 3), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						"dustSalt",
						"cropHerb",
						"foodViscera"
				});

		regBoilrecipe(new ItemStack(FoodInit.teaLeaves, 1, 0), null, 0F, null, null, null, false, null, new Object[] {
				"seedCoffee"
		});

		regBoilrecipe(new ItemStack(FoodInit.teaLeaves, 1, 1), null, 0F, null, null, null, false, null, new Object[] {
				"cropTea"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.teaLeaves, 1, 2), null, 0F, null,
				DCHeatTier.WARM, DCHumidity.WET, null, false, null, new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 1)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1, 0), null, 0F, null, DCHeatTier.WARM,
				DCHumidity.WET, null, false, new FluidStack(FoodInit.cream, 1000), new Object[] {
						"dustSalt"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1, 1), null, 0F, null, DCHeatTier.WARM,
				DCHumidity.WET, null, false, new FluidStack(FoodInit.cream, 1000), new Object[] {
						"foodRennet"
				});

		if (MainInit.milk != null) {
			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1, 1), null, 0F, null,
					DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(MainInit.milk, 1000), new Object[] {
							"foodRennet"
					});
		}

		regBoilrecipe(new ItemStack(FoodInit.ricebowl, 1, 0), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"foodRice"
				});

		regBoilrecipe(new ItemStack(FoodInit.ricebowl, 1, 1), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"foodRice",
						new ItemStack(Blocks.BROWN_MUSHROOM)
				});

		regBoilrecipe(new ItemStack(FoodInit.ricebowl, 1, 1), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"foodRice",
						new ItemStack(Blocks.RED_MUSHROOM)
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 0), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropOnion",
						"cropCarrot",
						"listAllveggie"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 1), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"egg",
						"cropSpinach"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 1), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"egg",
						"cropSeaweed"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 2), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"foodRice",
						"foodCheese"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 2), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"foodRice",
						"cropHerb"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 3), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"foodRice",
						"cropTomato"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 4), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropPumpkin",
						"cropOnion",
						"foodCream"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 4), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropPumpkin",
						"cropOnion",
						"foodButter"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 5), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"listAllveggie",
						"foodAnyMeat",
						"cropBeetroot"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 6), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0),
						new ItemStack(Blocks.RED_MUSHROOM, 1, 0),
						"foodCream"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 7), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"foodCream",
						new ItemStack(Items.CHORUS_FRUIT, 1, 0)
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 8), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropSpinach",
						"cropLotusRoot"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 9), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropTomato",
						"foodRice",
						"fishSquid"
				});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 10), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropSeaweed",
						"foodRice"
				});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 3), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.coffee, 1000), new Object[] {
						"dustSugar",
						"foodCream",
						"foodAgar"
				});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 3), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.coffee, 1000), new Object[] {
						"dustSugar",
						"foodCream",
						"foodGelatine"
				});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 4), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.cream, 1000), new Object[] {
						"dustSugar",
						"cropLemon",
						"foodAgar"
				});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 4), null, 0F, null, null, null, false,
				new FluidStack(FoodInit.cream, 1000), new Object[] {
						"dustSugar",
						"cropLemon",
						"foodGelatine"
				});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 5), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"dustSugar",
						"foodCustard"
				});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"listAllberry",
						"foodAgar"
				});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"listAllberry",
						"foodGelatine"
				});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"listApple",
						"foodAgar"
				});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"listApple",
						"foodGelatine"
				});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 7), null, 0F, null, null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"dustStarch",
						"dropMolasses"
				});

		if (MainInit.milk != null) {
			regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 6), null, 0F, null, null, null, false,
					new FluidStack(MainInit.milk, 1000), new Object[] {
							new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0),
							new ItemStack(Blocks.RED_MUSHROOM, 1, 0),
							"foodButter"
					});

			regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 7), null, 0F, null, null, null, false,
					new FluidStack(MainInit.milk, 1000), new Object[] {
							"cropOnion",
							new ItemStack(Items.CHORUS_FRUIT, 1, 0)
					});
		}

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1, 3), null, 0F, null, DCHeatTier.COLD,
				DCHumidity.DRY, null, false, null, new Object[] {
						"cropSeaweed"
				});

		regBoilrecipe(null, null, 0F, new FluidStack(FoodInit.mazai, 500), null, null, false,
				new FluidStack(MachineInit.ethanol, 1000), new Object[] {
						"cropHerb",
						"seedHerb",
						new ItemStack(Blocks.RED_MUSHROOM, 1, 0)
				});

		if (ModuleConfig.machine_advanced && ModuleConfig.r_reactor) {
			RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(MachineInit.fuelGas, 500),
					DCHeatTier.NORMAL, null, null, false, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
							"gemCarbide"
					});
		}

	}

	static void regNonFoodrecipe(ItemStack out, ItemStack sec, float chance, FluidStack outF, DCHeatTier heat,
			DCHumidity hum, DCAirflow air, boolean cooling, FluidStack inF, Object... input) {
		RecipeAPI.registerFluidRecipes.addRecipe(out, sec, chance, outF, heat, hum, air, cooling, inF, input);
		RecipeAPI.registerReactorRecipes.addRecipe(out, sec, chance, outF, null, heat, null, inF, null, input);
	}

	static void regBoilrecipe(ItemStack out, ItemStack sec, float chance, FluidStack outF, DCHumidity hum,
			DCAirflow air, boolean cooling, FluidStack inF, Object... input) {
		FluidCraftRecipe recipe = new FluidCraftRecipe(out, sec, outF, DCHeatTier.KILN, hum, air, chance, true, inF,
				input);
		recipe.requiredHeat().add(DCHeatTier.OVEN);
		RecipeAPI.registerFluidRecipes.addRecipe(recipe, DCHeatTier.KILN);
	}

}
