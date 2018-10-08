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

		regNonFoodrecipe(null, null, 0F, new FluidStack(MainInit.hotSpring,
				1000), DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSalt"
		});

		regNonFoodrecipe(null, null, 0F, new FluidStack(MainInit.hotSpring,
				1000), DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSulfur"
		});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 4, 0), null, 0F, new FluidStack(MainInit.blackLiquor,
				50), DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"pulpWood",
						"pulpWood",
						"dustAsh"
		});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 4, 0), null, 0F, new FluidStack(MainInit.blackLiquor,
				50), DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"pulpWood",
						"pulpWood",
						"dustLime"
		});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 4, 0), null, 0F, new FluidStack(MainInit.blackLiquor,
				50), DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"pulpWood",
						"pulpWood",
						"dustAlkali"
		});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 2,
				0), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								"dustPlant",
								"dustPlant",
								"dustLime"
		});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 2,
				0), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								"dustPlant",
								"dustPlant",
								"dustAlkali"
		});

		regNonFoodrecipe(new ItemStack(MainInit.repairPutty, 1,
				2), null, 0F, null, DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.oil,
						500), new Object[] {
								"dustAsh"
		});

		regNonFoodrecipe(new ItemStack(MainInit.repairPutty, 1,
				2), null, 0F, null, DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.oil,
						500), new Object[] {
								"dustLime"
		});

		regNonFoodrecipe(new ItemStack(MainInit.repairPutty, 1,
				2), null, 0F, null, DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.oil,
						500), new Object[] {
								"dustAlkali"
		});

		regNonFoodrecipe(new ItemStack(MainInit.gems, 1,
				17), null, 0F, null, DCHeatTier.OVEN, null, null, false, null, new Object[] {
						"dustBismuth"
		});

		regNonFoodrecipe(new ItemStack(MainInit.gems, 1,
				18), null, 0F, null, DCHeatTier.OVEN, null, null, false, null, new Object[] {
						"dustApatite"
		});

		regNonFoodrecipe(new ItemStack(MainInit.miscDust, 1,
				9), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
						100), new Object[] {
								new ItemStack(Items.DYE, 1, 15)
		});

		regNonFoodrecipe(new ItemStack(Items.SLIME_BALL, 1,
				0), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
						100), new Object[] {
								"cropHerb",
								"dustCrystal",
								new ItemStack(MachineInit.reagent, 1, 1)
		});

		regNonFoodrecipe(new ItemStack(FoodInit.meat, 1,
				4), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								new ItemStack(Items.LEATHER, 1, 0)
		});

		// 醸造
		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
				300), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						1000), new Object[] {
								"dustStarch",
								"foodMalt"
		});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
				200), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						1000), new Object[] {
								"dustSugar",
								"foodMalt"
		});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
				150), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						1000), new Object[] {
								"cropPotato",
								"foodMalt"
		});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
				100), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						1000), new Object[] {
								"cropWheat",
								"foodMalt"
		});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
				150), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						1000), new Object[] {
								"foodRice",
								"foodMalt"
		});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
				100), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						1000), new Object[] {
								"dropMolasses",
								"foodMalt"
		});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
				200), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						1000), new Object[] {
								"cropDate",
								"foodMalt"
		});

		// 麦芽
		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1,
				3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								"cropWheat"
		});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1,
				3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								new ItemStack(Items.WHEAT_SEEDS, 1, 0)
		});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 9,
				3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								new ItemStack(Blocks.HAY_BLOCK, 1, 0)
		});

		// その他
		regNonFoodrecipe(new ItemStack(MainInit.gems, 1,
				3), null, 0F, null, DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.sulfuricAcid,
						200), new Object[] {
								"dustLime"
		});

		regNonFoodrecipe(new ItemStack(MainInit.clothes, 1,
				6), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								new ItemStack(MainInit.silkworm, 1, 2),
								new ItemStack(MainInit.silkworm, 1, 2),
								new ItemStack(MainInit.silkworm, 1, 2)
		});

		regNonFoodrecipe(new ItemStack(MainInit.clothes, 1,
				8), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(MainInit.hotSpring,
						1000), new Object[] {
								new ItemStack(MainInit.clothes, 1, 6),
								"dustSilver",
								"dustMica"
		});

		regNonFoodrecipe(new ItemStack(MainInit.clothes, 1,
				9), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(MainInit.hotSpring,
						1000), new Object[] {
								new ItemStack(MainInit.clothes, 1, 7),
								new ItemStack(Items.CHORUS_FRUIT, 1, 0),
								new ItemStack(Items.GHAST_TEAR, 1, 0)
		});

		regBoilrecipe(null, null, 0F, new FluidStack(MainInit.mazai, 500), null, null, false, new FluidStack(
				MainInit.ethanol, 1000), new Object[] {
						"cropHerb",
						"seedHerb",
						new ItemStack(Blocks.RED_MUSHROOM, 1, 0)
		});

		regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1,
				6), null, 0F, null, DCHeatTier.SMELTING, null, null, false, new FluidStack(FluidRegistry.WATER,
						1000), new Object[] {
								"dustSalt",
								"dustLime"
		});

		regNonFoodrecipe(new ItemStack(MainInit.miscDust, 1,
				13), null, 0F, null, DCHeatTier.UHT, null, null, false, null, new Object[] {
						"dustChrysotile",
						"dustLime"
		});

		regNonFoodrecipe(new ItemStack(MainInit.miscDust, 1,
				13), null, 0F, null, DCHeatTier.UHT, null, null, false, null, new Object[] {
						"dustCrystal",
						"dustLime"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
				1), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						1000), new Object[] {
								"dustSugar",
								new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0)
		});

		if (ModuleConfig.machine_advanced && ModuleConfig.r_reactor) {
			RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(MainInit.fuelGas,
					500), DCHeatTier.NORMAL, null, null, false, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
							"gemCarbide"
			});
		}

	}

	static void loadCookingRecipes() {
		// materials
		if (MainInit.milk != null) {
			RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(MainInit.cream,
					200), DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.milk, 1000), (Object[]) null);
		}

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(MainInit.tomatoJuice,
				200), DCHeatTier.BOIL, null, null, false, null, new Object[] {
						"listAllveggie"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(MainInit.lemon,
				200), DCHeatTier.BOIL, null, null, false, null, new Object[] {
						"cropLemon",
						"dustSugar"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(MainInit.lemon,
				200), DCHeatTier.BOIL, null, null, false, null, new Object[] {
						"cropLemon",
						"dropHoney"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(Items.SUGAR, 3,
				0), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
						100), new Object[] {
								"cropDate"
		});

		regBoilrecipe(null, new ItemStack(MainInit.foodDust, 1, 5), 0.25F, new FluidStack(MainInit.soyMilk,
				500), null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"cropSoybean"
		});

		regBoilrecipe(new ItemStack(FoodInit.dairy, 1, 3), null, 0F, null, null, null, false, new FluidStack(
				MainInit.soyMilk, 1000), new Object[] {
						"dustSalt"
		});

		// tea

		regBoilrecipe(null, null, 0F, new FluidStack(MainInit.coffee, 1000), null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 0)
		});

		regBoilrecipe(null, null, 0F, new FluidStack(MainInit.greenTea, 1000), null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 1)
		});

		regBoilrecipe(null, null, 0F, new FluidStack(MainInit.blackTea, 1000), null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 2)
		});

		regBoilrecipe(null, null, 0F, new FluidStack(MainInit.stock, 1000), null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"foodAnyMeat",
						"cropHerb",
						"listAllveggie"
		});

		regBoilrecipe(null, null, 0F, new FluidStack(MainInit.stock, 1000), null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"cropSeaweed"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FluidRegistry.WATER,
				1000), DCHeatTier.HOT, null, null, false, null, new Object[] {
						new ItemStack(Blocks.ICE, 1, 0)
		});

		FluidCraftRecipe salt = new FluidCraftRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), null, null,
				DCHeatTier.HOT, DCHumidity.DRY, null, 0, false, new FluidStack(FluidRegistry.WATER, 1000),
				(Object[]) null);
		RecipeAPI.registerFluidRecipes.addRecipe(salt, DCHeatTier.BOIL);

		FluidCraftRecipe salt2 = new FluidCraftRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), null, null,
				DCHeatTier.OVEN, DCHumidity.DRY, null, 0, false, new FluidStack(FluidRegistry.WATER, 1000),
				(Object[]) null);
		salt2.requiredHum().add(DCHumidity.NORMAL);
		salt2.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerFluidRecipes.addRecipe(salt2, DCHeatTier.BOIL);

		// food

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 1,
				2), null, 0F, null, DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
						"dustSalt",
						new ItemStack(Items.ROTTEN_FLESH)
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 3,
				2), null, 0F, null, DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
						"dustSalt",
						"foodAnyMeat"
		});

		regBoilrecipe(new ItemStack(MainInit.bakedApple, 1, 1), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 100), new Object[] {
						new ItemStack(Items.EGG)
		});

		regBoilrecipe(new ItemStack(MainInit.bakedApple, 3, 3), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 100), new Object[] {
						"dustSalt",
						"cropHerb",
						"foodAnyMeat"
		});

		regBoilrecipe(new ItemStack(MainInit.bakedApple, 3, 3), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 100), new Object[] {
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

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.teaLeaves, 1,
				2), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, null, new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 1)
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
				0), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(MainInit.cream,
						500), new Object[] {
								"dustSalt"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
				1), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(MainInit.cream,
						1000), new Object[] {
								"foodRennet"
		});

		if (MainInit.milk != null) {
			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
					1), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(MainInit.milk,
							1000), new Object[] {
									"foodRennet"
			});
		}

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
				3), null, 0F, null, DCHeatTier.NORMAL, DCHumidity.WET, null, false, new FluidStack(MainInit.oil,
						500), new Object[] {
								"dustSalt",
								"bucketWater"
		});

		// cooking

		regBoilrecipe(new ItemStack(FoodInit.bread, 1, 9), null, 0F, null, null, null, false, null, new Object[] {
				"bread",
				"egg",
				"bucketMilk"
		});

		regBoilrecipe(new ItemStack(FoodInit.ricebowl, 1, 0), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 200), new Object[] {
						"foodRice"
		});

		regBoilrecipe(new ItemStack(FoodInit.ricebowl, 1, 1), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 200), new Object[] {
						"foodRice",
						new ItemStack(Blocks.BROWN_MUSHROOM)
		});

		regBoilrecipe(new ItemStack(FoodInit.ricebowl, 1, 1), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 200), new Object[] {
						"foodRice",
						new ItemStack(Blocks.RED_MUSHROOM)
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 0), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"cropOnion",
						"cropCarrot",
						"listAllveggie"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 1), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"egg",
						"cropSpinach"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 1), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"egg",
						"cropSeaweed"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 2), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"foodRice",
						"foodCheese"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 2), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"foodRice",
						"cropHerb",
						"cropSeaweed"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 3), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"foodRice",
						"cropTomato"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 4), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"cropPumpkin",
						"cropOnion",
						"foodCream"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 4), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"cropPumpkin",
						"cropOnion",
						"foodButter"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 5), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"listAllveggie",
						"foodAnyMeat",
						"cropBeetroot"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 6), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0),
						new ItemStack(Blocks.RED_MUSHROOM, 1, 0),
						"foodCream"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 7), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"foodCream",
						new ItemStack(Items.CHORUS_FRUIT, 1, 0)
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 8), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"listAllgreenveggie",
						"cropLotusRoot"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 9), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"cropTomato",
						"foodRice",
						"foodSquid"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 10), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"cropSeaweed",
						"foodRice"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 11), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"listAllgreenveggie",
						"cropSeaweed",
						"foodMiso"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 11), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"foodFirmtofu",
						"cropSeaweed",
						"foodMiso"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 12), null, 0F, null, null, null, false, new FluidStack(
				MainInit.stock, 1000), new Object[] {
						"foodViscera",
						"listAllrootveggie",
						"foodMiso"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 13), null, 0F, null, null, null, false, null, new Object[] {
				new ItemStack(Items.COOKED_BEEF),
				"cropChilipepper",
				"cropBean"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 14), null, 0F, null, null, null, false, new FluidStack(
				MainInit.oil, 1000), new Object[] {
						"cropMushroom",
						"cropChilipepper",
						"cropGarlic"
		});

		regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 15), null, 0F, null, null, null, false, new FluidStack(
				MainInit.oil, 200), new Object[] {
						new ItemStack(FoodInit.deepFry, 1, 1),
						"cropChilipepper",
						"cropHerb"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.salad, 2,
				7), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, null, new Object[] {
						"cropSoybean",
						"cropRice"
		});

		if (MainInit.milk != null) {
			regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 6), null, 0F, null, null, null, false, new FluidStack(
					MainInit.milk, 1000), new Object[] {
							new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0),
							new ItemStack(Blocks.RED_MUSHROOM, 1, 0),
							"foodButter"
			});

			regBoilrecipe(new ItemStack(FoodInit.bowlSoup, 3, 7), null, 0F, null, null, null, false, new FluidStack(
					MainInit.milk, 1000), new Object[] {
							"cropOnion",
							new ItemStack(Items.CHORUS_FRUIT, 1, 0)
			});
		}

		// frying

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.deepFry, 1,
				0), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
						200), new Object[] {
								new ItemStack(Items.PORKCHOP),
								"foodFlour",
								"egg"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.deepFry, 1,
				1), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
						200), new Object[] {
								new ItemStack(Items.CHICKEN),
								"foodFlour"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.deepFry, 1,
				2), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
						200), new Object[] {
								"foodFish",
								"foodFlour",
								"egg"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.deepFry, 1,
				2), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
						200), new Object[] {
								"listAllfishraw",
								"foodFlour",
								"egg"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.deepFry, 1,
				3), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
						200), new Object[] {
								"cropBean",
								"cropOnion",
								"cropGarlic"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.snack, 1,
				0), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
						200), new Object[] {
								"cropPotato",
								"dustSalt"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.snack, 1,
				2), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
						200), new Object[] {
								"cropPotato",
								"foodFlour",
								"bread"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.snack, 1,
				3), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
						200), new Object[] {
								"cropPumpkin",
								"foodFlour",
								"bread"
		});

		// sweets

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 3), null, 0F, null, null, null, false, new FluidStack(
				MainInit.coffee, 1000), new Object[] {
						"dustSugar",
						"foodCream",
						"foodAgar"
		});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 3), null, 0F, null, null, null, false, new FluidStack(
				MainInit.coffee, 1000), new Object[] {
						"dustSugar",
						"foodCream",
						"foodGelatine"
		});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 4), null, 0F, null, null, null, false, new FluidStack(
				MainInit.cream, 1000), new Object[] {
						"dustSugar",
						"cropLemon",
						"foodAgar"
		});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 4), null, 0F, null, null, null, false, new FluidStack(
				MainInit.cream, 1000), new Object[] {
						"dustSugar",
						"cropLemon",
						"foodGelatine"
		});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 5), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 200), new Object[] {
						"dustSugar",
						"foodCustard"
		});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"listAllberry",
						"foodAgar"
		});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"listAllberry",
						"foodGelatine"
		});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"listApple",
						"foodAgar"
		});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"listApple",
						"foodGelatine"
		});

		regBoilrecipe(new ItemStack(FoodInit.cake, 3, 7), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"dustSugar",
						"dustStarch",
						"dropMolasses"
		});

		regBoilrecipe(new ItemStack(FoodInit.nonEntity, 3, 0), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 200), new Object[] {
						"egg",
						"dustSugar",
						"foodGelatine"
		});

		regBoilrecipe(new ItemStack(FoodInit.nonEntity, 3, 2), null, 0F, null, null, null, false, null, new Object[] {
				"listAllnut",
				"dustSugar",
				"foodButter"
		});

		regBoilrecipe(new ItemStack(FoodInit.nonEntity, 3, 2), null, 0F, null, null, null, false, null, new Object[] {
				"listAllnut",
				"dropMolasses",
				"foodButter"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
				3), null, 0F, null, DCHeatTier.COLD, DCHumidity.DRY, null, false, null, new Object[] {
						"cropSeaweed"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
				5), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								new ItemStack(MainInit.foodDust, 1, 4)
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1, 6), new ItemStack(FoodInit.meat, 1,
				7), 0.5F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								"cropSoybean",
								"dustSalt",
								"foodMalt"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
				7), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								new ItemStack(MainInit.foodDust, 1, 4),
								"dustSalt",
								"foodMalt"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
				8), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								"cropBean",
								"dustSugar"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.icecream, 3,
				0), null, 0F, null, DCHeatTier.COLD, null, null, false, new FluidStack(MainInit.cream,
						1000), new Object[] {
								"dustSugar",
								"egg"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.icecream, 3,
				2), null, 0F, null, DCHeatTier.COLD, null, null, false, new FluidStack(MainInit.cream,
						1000), new Object[] {
								"dustSugar",
								"listAllberry"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.icecream, 3,
				3), null, 0F, null, DCHeatTier.COLD, null, null, false, new FluidStack(MainInit.cream,
						1000), new Object[] {
								"dustSugar",
								"cropLemon"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.icecream, 3,
				4), null, 0F, null, DCHeatTier.COLD, null, null, false, new FluidStack(MainInit.cream,
						1000), new Object[] {
								"dustSugar",
								new ItemStack(Items.COOKIE, 1, 0)
		});

	}

	public static void regNonFoodrecipe(ItemStack out, ItemStack sec, float chance, FluidStack outF, DCHeatTier heat,
			DCHumidity hum, DCAirflow air, boolean cooling, FluidStack inF, Object... input) {
		RecipeAPI.registerFluidRecipes.addRecipe(out, sec, chance, outF, heat, hum, air, cooling, inF, input);
		if (ModuleConfig.machine)
			RecipeAPI.registerReactorRecipes
					.addRecipe(out, sec, chance, outF, null, heat, (ItemStack) null, inF, null, input);
	}

	public static void regBoilrecipe(ItemStack out, ItemStack sec, float chance, FluidStack outF, DCHumidity hum,
			DCAirflow air, boolean cooling, FluidStack inF, Object... input) {
		FluidCraftRecipe recipe = new FluidCraftRecipe(out, sec, outF, DCHeatTier.OVEN, hum, air, chance, true, inF,
				input);
		recipe.requiredHeat().add(DCHeatTier.BOIL);
		RecipeAPI.registerFluidRecipes.addRecipe(recipe, DCHeatTier.OVEN);
	}

}
