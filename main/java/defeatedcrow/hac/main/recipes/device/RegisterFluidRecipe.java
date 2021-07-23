package defeatedcrow.hac.main.recipes.device;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.FluidCraftRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class RegisterFluidRecipe {

	public static boolean addEthanolRecipe = !ModuleConfig.liquor;

	public static void load() {

		loadCommonRecipe();
		loadFoodRecipe();
		loadSteelRecipe();
		loadAdvancedRecipe();
		loadBrewingRecipe();
	}

	public static void loadCommonRecipe() {
		regNonFoodRecipe(null, null, 0F, new FluidStack(MainInit.hotSpring,
				1000), DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSalt"
		});

		regNonFoodRecipe(null, null, 0F, new FluidStack(MainInit.hotSpring,
				1000), DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSulfur"
		});

		regNonFoodRecipe(new ItemStack(Items.PAPER, 6, 0), null, 0F, new FluidStack(MainInit.blackLiquor,
				50), DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"dustWood",
						"dustWood",
						"dustAsh"
		});

		regNonFoodRecipe(new ItemStack(Items.PAPER, 6, 0), null, 0F, new FluidStack(MainInit.blackLiquor,
				50), DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"dustWood",
						"dustWood",
						"dustLime"
		});

		regNonFoodRecipe(new ItemStack(Items.PAPER, 6, 0), null, 0F, new FluidStack(MainInit.blackLiquor,
				50), DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"dustWood",
						"dustWood",
						"dustAlkali"
		});

		regNonFoodRecipe(new ItemStack(Items.PAPER, 4,
				0), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								"dustPlant",
								"dustPlant",
								"dustLime"
		});

		regNonFoodRecipe(new ItemStack(Items.PAPER, 4,
				0), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								"dustPlant",
								"dustPlant",
								"dustAlkali"
		});

		regNonFoodRecipe(new ItemStack(MainInit.repairPutty, 1,
				2), null, 0F, null, DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.oil,
						100), new Object[] {
								"dustAsh"
		});

		regNonFoodRecipe(new ItemStack(MainInit.repairPutty, 1,
				2), null, 0F, null, DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.oil,
						100), new Object[] {
								"dustLime"
		});

		regNonFoodRecipe(new ItemStack(MainInit.repairPutty, 2,
				2), null, 0F, null, DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.oil,
						100), new Object[] {
								"dustAlkali"
		});

		regReactorRecipe(new ItemStack(MainInit.gems_layer, 1,
				5), null, 0F, null, DCHeatTier.OVEN, null, null, false, null, new Object[] {
						"dustBismuth"
		});

		regReactorRecipe(new ItemStack(MainInit.gems_layer, 1,
				6), null, 0F, null, DCHeatTier.OVEN, null, null, false, null, new Object[] {
						"dustApatite"
		});

		regReactorRecipe(new ItemStack(MainInit.miscDust, 1,
				9), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
						100), new Object[] {
								new ItemStack(Items.DYE, 1, 15)
		});

		regReactorRecipe(new ItemStack(Items.SLIME_BALL, 1,
				0), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
						100), new Object[] {
								"dustBorax",
								new ItemStack(MachineInit.reagent, 1, 2)
		});

		regReactorRecipe(new ItemStack(Items.SLIME_BALL, 1,
				0), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
						100), new Object[] {
								"cropHerb",
								"dustCrystal",
								new ItemStack(MachineInit.reagent, 1, 1)
		});

		// その他
		regReactorRecipe(new ItemStack(MainInit.gems_layer, 1,
				0), null, 0F, null, DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.sulfuricAcid,
						200), new Object[] {
								"dustLime"
		});

		regNonFoodRecipe(new ItemStack(MainInit.clothes, 1,
				6), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								new ItemStack(MainInit.silkworm, 1, 2),
								new ItemStack(MainInit.silkworm, 1, 2),
								new ItemStack(MainInit.silkworm, 1, 2)
		});

		regReactorRecipe(new ItemStack(MainInit.clothes, 1,
				8), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(MainInit.hotSpring,
						1000), new Object[] {
								new ItemStack(MainInit.clothes, 1, 6),
								"dustSilver",
								"dustMica"
		});

		regReactorRecipe(new ItemStack(MainInit.clothes, 1,
				8), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(MainInit.hotSpring,
						1000), new Object[] {
								new ItemStack(MainInit.clothes, 1, 6),
								"dustSilver",
								"dustMana"
		});

		regReactorRecipe(new ItemStack(MainInit.clothes, 1,
				9), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(MainInit.hotSpring,
						1000), new Object[] {
								new ItemStack(MainInit.clothes, 1, 7),
								new ItemStack(Items.CHORUS_FRUIT, 1, 0),
								new ItemStack(Items.GHAST_TEAR, 1, 0)
		});

		regReactorRecipe(new ItemStack(MainInit.clothes, 1,
				9), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(MainInit.hotSpring,
						1000), new Object[] {
								new ItemStack(MainInit.clothes, 1, 7),
								new ItemStack(Blocks.DOUBLE_PLANT, 1, 1),
								"dustMana"
		});

		// drinks
		regBoilRecipe(null, null, 0F, new FluidStack(MainInit.mazai, 500), null, null, false, new FluidStack(
				MainInit.ethanol, 1000), new Object[] {
						"cropHerb",
						"seedHerb",
						new ItemStack(Blocks.RED_MUSHROOM, 1, 0)
		});

		regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1,
				6), null, 0F, null, DCHeatTier.SMELTING, null, null, false, new FluidStack(FluidRegistry.WATER,
						1000), new Object[] {
								"dustSalt",
								"dustLime"
		});

		regReactorRecipe(new ItemStack(MainInit.miscDust, 1,
				13), null, 0F, null, DCHeatTier.UHT, null, null, false, null, new Object[] {
						"dustChrysotile",
						"dustLime"
		});

		regReactorRecipe(new ItemStack(MainInit.miscDust, 1,
				13), null, 0F, null, DCHeatTier.UHT, null, null, false, null, new Object[] {
						"dustCrystal",
						"dustLime"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(MainInit.fuelGas,
				500), DCHeatTier.NORMAL, null, null, false, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						"gemCarbide"
		});

		if (ModuleConfig.food) {
			regNonFoodRecipe(new ItemStack(FoodInit.meat, 1,
					4), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									new ItemStack(Items.LEATHER, 1, 0)
			});

			regNonFoodRecipe(new ItemStack(FoodInit.meat, 1,
					1), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(
							FluidRegistry.WATER, 1000), new Object[] {
									"dustSugar",
									"listAllmushroom"
			});

			// vine
			regNonFoodRecipe(new ItemStack(FoodInit.crops, 1,
					18), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"treeSapling",
									"dustAsh"
			});

			regNonFoodRecipe(new ItemStack(FoodInit.crops, 1,
					18), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"vineLeaves",
									"dustAsh"
			});

			regNonFoodRecipe(new ItemStack(FoodInit.crops, 1,
					18), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"treeSapling",
									"dustAsh"
			});

		}

		if (ModuleConfig.machine) {
			// 合成
			regReactorRecipe(new ItemStack(MachineInit.reagent, 1, 10), new ItemStack(MainInit.gems_layer, 1,
					0), 1F, null, DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER,
							100), new Object[] {
									"dustApatite",
									"sand",
									"gemCoal"
			});

			regReactorRecipe(new ItemStack(MachineInit.reagent, 2, 10), new ItemStack(MainInit.gems_layer, 1,
					0), 1.0F, null, DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER,
							100), new Object[] {
									"dustApatite",
									"sand",
									"fuelCoke"
			});

			regReactorRecipe(new ItemStack(MachineInit.reagent, 1,
					11), null, 0, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							100), new Object[] {
									new ItemStack(MachineInit.reagent, 1, 10)
			});
		}

		if (ModuleConfig.magic) {
			regReactorRecipe(new ItemStack(MagicInit.colorDrop, 1,
					5), null, 0, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"dropBlue",
									"dropBlue",
									"dustMica"
			});

			regReactorRecipe(new ItemStack(MagicInit.colorDrop, 1,
					6), null, 0, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"dropGreen",
									"dropGreen",
									"dustMica"
			});

			regReactorRecipe(new ItemStack(MagicInit.colorDrop, 1,
					7), null, 0, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"dropRed",
									"dropRed",
									"dustMica"
			});

			regReactorRecipe(new ItemStack(MagicInit.colorDrop, 1,
					8), null, 0, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"dropBlack",
									"dropBlack",
									"dustMica"
			});

			regReactorRecipe(new ItemStack(MagicInit.colorDrop, 1,
					9), null, 0, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"dropWhite",
									"dropWhite",
									"dustMica"
			});
		}
	}

	public static void loadFoodRecipe() {
		// materials
		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(MainInit.cream,
				200), DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.milk, 1000), (Object[]) null);

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

		regBoilRecipe(null, new ItemStack(MainInit.foodDust, 1, 5), 0.25F, new FluidStack(MainInit.soyMilk,
				500), null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"cropSoybean"
		});

		if (ModuleConfig.food) {

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
					10), null, 0F, null, DCHeatTier.NORMAL, null, null, false, null, new Object[] {
							"foodCodraw",
							"dustSalt"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
					10), null, 0F, null, DCHeatTier.NORMAL, null, null, false, null, new Object[] {
							"foodSalmonraw",
							"dustSalt"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
					10), null, 0F, null, DCHeatTier.NORMAL, null, null, false, null, new Object[] {
							"foodHerringraw",
							"dustSalt"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
					10), null, 0F, null, DCHeatTier.NORMAL, null, null, false, null, new Object[] {
							"foodTroutraw",
							"dustSalt"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 3,
					11), null, 0F, null, DCHeatTier.NORMAL, null, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"cropRedbean",
									"dustSugar",
									"dustSalt"
			});
		}

		// おだし

		regBoilRecipe(null, null, 0F, new FluidStack(MainInit.stock, 5000), null, null, false, new FluidStack(
				FluidRegistry.WATER, 5000), new Object[] {
						"listAllmeatraw",
						"cropHerb",
						"listAllveggie"
		});

		regBoilRecipe(null, null, 0F, new FluidStack(MainInit.stock, 1000), null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"cropSeaweed"
		});

		regBoilRecipe(null, null, 0F, new FluidStack(MainInit.stock, 1000), null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"bone",
						"listAllspice"
		});

		regBoilRecipe(null, null, 0F, new FluidStack(MainInit.stock, 1000), null, null, false, new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
						"foodShrimpraw"
		});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FluidRegistry.WATER,
				1000), DCHeatTier.HOT, null, null, false, null, new Object[] {
						new ItemStack(Blocks.ICE, 1, 0)
		});

		FluidCraftRecipe salt = new FluidCraftRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), null, null,
				DCHeatTier.HOT, DCHumidity.DRY, null, 0, false, new FluidStack(FluidRegistry.WATER, 1000),
				(Object[]) null);
		RecipeAPI.registerFluidRecipes.addRecipe(salt);

		FluidCraftRecipe salt2 = new FluidCraftRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), null, null,
				DCHeatTier.OVEN, DCHumidity.DRY, null, 0, false, new FluidStack(FluidRegistry.WATER, 1000),
				(Object[]) null);
		salt2.requiredHum().add(DCHumidity.NORMAL);
		salt2.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerFluidRecipes.addRecipe(salt2);

		// food
		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 1,
				2), null, 0F, null, DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
						"dustSalt",
						new ItemStack(Items.ROTTEN_FLESH)
		});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 3,
				2), null, 0F, null, DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
						"dustSalt",
						"listAllmeatraw"
		});

		regBoilRecipe(new ItemStack(MainInit.bakedApple, 1, 1), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 100), new Object[] {
						"egg"
		});

		regBoilRecipe(new ItemStack(MainInit.bakedApple, 1, 5), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 100), new Object[] {
						"cropLotusSeed",
						"dustSalt"
		});

		regBoilRecipe(new ItemStack(MainInit.bakedApple, 1, 6), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 100), new Object[] {
						"cropGreenSoybeans",
						"dustSalt"
		});

		regBoilRecipe(new ItemStack(MainInit.bakedApple, 3, 3), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 100), new Object[] {
						"dustSalt",
						"cropHerb",
						"listAllmeatraw"
		});

		regBoilRecipe(new ItemStack(MainInit.bakedApple, 3, 3), null, 0F, null, null, null, false, new FluidStack(
				FluidRegistry.WATER, 100), new Object[] {
						"dustSalt",
						"cropHerb",
						"foodViscera"
		});

		if (ModuleConfig.food) {

			regBoilRecipe(new ItemStack(FoodInit.dairy, 1, 3), null, 0F, null, null, null, false, new FluidStack(
					MainInit.soyMilk, 1000), new Object[] {
							"dustSalt"
			});

			// tea
			regBoilRecipe(null, null, 0F, new FluidStack(MainInit.coffee, 1000), null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"foodCoffeeBean"
			});

			regBoilRecipe(null, null, 0F, new FluidStack(MainInit.greenTea, 1000), null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"foodGreenteaLeaf"
			});

			regBoilRecipe(null, null, 0F, new FluidStack(MainInit.blackTea, 1000), null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"foodTeaLeaf"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.nonEntity, 1,
					5), null, 0F, null, DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
							"cropGrape"
			});

			regBoilRecipe(new ItemStack(FoodInit.teaLeaves, 1,
					0), null, 0F, null, null, null, false, null, new Object[] {
							"seedCoffee"
			});

			regBoilRecipe(new ItemStack(FoodInit.teaLeaves, 1,
					1), null, 0F, null, null, null, false, null, new Object[] {
							"cropTea"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.teaLeaves, 1,
					2), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, null, new Object[] {
							"foodGreenteaLeaf"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
					0), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(MainInit.cream,
							500), new Object[] {
									"dustSalt"
			});

			if (ModuleConfig.food_advanced) {
				RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 4, 1), new ItemStack(
						FoodInit.residue, 1, 6), 1F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(
								MainInit.milk, 1000), new Object[] {
										new ItemStack(FoodInit.lab, 1, 1)
				});
			}

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 4, 1), new ItemStack(
					FoodInit.residue, 1, 6), 1F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(
							MainInit.milk, 1000), new Object[] {
									"foodRennet"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
					1), null, 0F, null, DCHeatTier.BOIL, DCHumidity.WET, null, false, null, new Object[] {
							"dustWhey",
							"cropLemon"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
					3), null, 0F, null, DCHeatTier.NORMAL, DCHumidity.WET, null, false, new FluidStack(MainInit.oil,
							500), new Object[] {
									"dustSalt",
									"bucketWater"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 2,
					4), null, 0F, null, DCHeatTier.HOT, null, null, false, new FluidStack(MainInit.oil,
							1000), new Object[] {
									"foodCream"
			});

			// cooking

			regBoilRecipe(new ItemStack(FoodInit.bread, 1, 9), null, 0F, null, null, null, false, null, new Object[] {
					"bread",
					"egg",
					"bucketMilk"
			});

			regBoilRecipe(new ItemStack(FoodInit.ricebowl, 1, 0), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 200), new Object[] {
							"foodRice"
			});

			regBoilRecipe(new ItemStack(FoodInit.ricebowl, 1, 1), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 200), new Object[] {
							"foodRice",
							"listAllmushroom"
			});

			regBoilRecipe(new ItemStack(FoodInit.ricebowl, 1, 6), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 200), new Object[] {
							"foodRice",
							"cropRedbean",
							"dustSalt"
			});

			regBoilRecipe(new ItemStack(FoodInit.ricebowl, 1, 7), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 500), new Object[] {
							"foodAzukianko",
							"foodRicecake"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 0), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"cropOnion",
							"cropCarrot",
							"listAllveggie"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 1), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"egg",
							"cropSpinach"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 1), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"egg",
							"cropSeaweed"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 2), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"foodRice",
							"foodCheese"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 2), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"foodRice",
							"cropHerb",
							"cropSeaweed"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 3), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"foodRice",
							"cropTomato"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 4), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"cropPumpkin",
							"cropOnion",
							"foodCream"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 4), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"cropPumpkin",
							"cropOnion",
							"foodButter"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 5), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"listAllveggie",
							"listAllmeatraw",
							"cropBeetroot"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 6), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0),
							new ItemStack(Blocks.RED_MUSHROOM, 1, 0),
							"foodCream"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 7), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"foodCream",
							new ItemStack(Items.CHORUS_FRUIT, 1, 0)
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 8), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"listAllgreenveggie",
							"cropLotusRoot"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 9), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"cropTomato",
							"foodRice",
							"foodSquid"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 10), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"cropSeaweed",
							"foodRice"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 11), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"listAllgreenveggie",
							"cropSeaweed",
							"foodMiso"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 11), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"foodFirmtofu",
							"cropSeaweed",
							"foodMiso"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 12), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"foodViscera",
							"listAllrootveggie",
							"foodMiso"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3,
					13), null, 0F, null, null, null, false, null, new Object[] {
							"listAllbeefcooked",
							"cropChilipepper",
							"cropBean"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 14), null, 0F, null, null, null, false, new FluidStack(
					MainInit.oil, 1000), new Object[] {
							"listAllmushroom",
							"cropChilipepper",
							"cropGarlic"
			});

			regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 15), null, 0F, null, null, null, false, new FluidStack(
					MainInit.oil, 200), new Object[] {
							new ItemStack(FoodInit.deepFry, 1, 1),
							"cropChilipepper",
							"cropHerb"
			});

			if (MainInit.milk != null) {
				regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 6), null, 0F, null, null, null, false, new FluidStack(
						MainInit.milk, 1000), new Object[] {
								new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0),
								new ItemStack(Blocks.RED_MUSHROOM, 1, 0),
								"foodButter"
				});

				regBoilRecipe(new ItemStack(FoodInit.bowlSoup, 3, 7), null, 0F, null, null, null, false, new FluidStack(
						MainInit.milk, 1000), new Object[] {
								"cropOnion",
								new ItemStack(Items.CHORUS_FRUIT, 1, 0)
				});
			}

			// frying

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.deepFry, 1,
					0), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							200), new Object[] {
									"listAllporkraw",
									"foodFlour",
									"egg"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.deepFry, 1,
					1), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							200), new Object[] {
									"listAllchickenraw",
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

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.deepFry, 1,
					4), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							200), new Object[] {
									"listAllfishraw",
									"cropPotato",
									"egg"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.deepFry, 1,
					5), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							100), new Object[] {
									"listAllporkraw",
									"cropGinger",
									"foodSoysauce"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.deepFry, 1,
					6), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							200), new Object[] {
									"foodShrimpraw",
									"foodFlour",
									"egg"
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

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 3), null, 0F, null, null, null, false, new FluidStack(
					MainInit.coffee, 1000), new Object[] {
							"dustSugar",
							"foodCream",
							"foodAgar"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 3), null, 0F, null, null, null, false, new FluidStack(
					MainInit.coffee, 1000), new Object[] {
							"dustSugar",
							"foodCream",
							"foodGelatine"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 4), null, 0F, null, null, null, false, new FluidStack(
					MainInit.cream, 1000), new Object[] {
							"dustSugar",
							"cropLemon",
							"foodAgar"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 4), null, 0F, null, null, null, false, new FluidStack(
					MainInit.cream, 1000), new Object[] {
							"dustSugar",
							"cropLemon",
							"foodGelatine"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 5), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 200), new Object[] {
							"dustSugar",
							"foodCustard"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"dustSugar",
							"listAllberry",
							"foodAgar"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"dustSugar",
							"listAllberry",
							"foodGelatine"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"dustSugar",
							"listApple",
							"foodAgar"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 6), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"dustSugar",
							"listApple",
							"foodGelatine"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 7), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"dustSugar",
							"dustStarch",
							"dropMolasses"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 12), null, 0F, null, null, null, false, new FluidStack(
					MainInit.cream, 1000), new Object[] {
							"dustSugar",
							"cropGrape",
							"foodGelatine"
			});

			regBoilRecipe(new ItemStack(FoodInit.cake, 3, 19), null, 0F, null, null, null, false, null, new Object[] {
					"dustSugar",
					"cropApple",
					"foodButter",
					"foodPastry"
			});

			regBoilRecipe(new ItemStack(FoodInit.nonEntity, 3, 0), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 200), new Object[] {
							"egg",
							"dustSugar",
							"foodGelatine"
			});

			regBoilRecipe(new ItemStack(FoodInit.nonEntity, 3,
					2), null, 0F, null, null, null, false, null, new Object[] {
							"listAllnut",
							"dustSugar",
							"foodButter"
			});

			regBoilRecipe(new ItemStack(FoodInit.nonEntity, 3,
					2), null, 0F, null, null, null, false, null, new Object[] {
							"listAllnut",
							"dropMolasses",
							"foodButter"
			});

			regBoilRecipe(new ItemStack(FoodInit.nonEntity, 3, 3), null, 0F, null, null, null, false, new FluidStack(
					MainInit.soyMilk, 1000), new Object[] {
							"gemGypsum"
			});

			regBoilRecipe(new ItemStack(FoodInit.nonEntity, 3, 3), null, 0F, null, null, null, false, new FluidStack(
					MainInit.soyMilk, 1000), new Object[] {
							"dustLime"
			});

			regBoilRecipe(new ItemStack(FoodInit.nonEntity, 3,
					4), null, 0F, null, null, null, false, null, new Object[] {
							"foodSalmonraw",
							"dustWood"
			});

			regBoilRecipe(new ItemStack(FoodInit.dishSq, 2, 5), null, 0F, null, null, null, false, null, new Object[] {
					"listAllfishraw",
					"cropGinger",
					"foodMiso"
			});

			regBoilRecipe(new ItemStack(FoodInit.udon, 2, 0), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"foodNoodles",
							"foodSoysauce",
							"listAllmeatraw"
			});

			regBoilRecipe(new ItemStack(FoodInit.udon, 2, 1), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"foodNoodles",
							"foodSoysauce",
							"cropSeaweed"
			});

			regBoilRecipe(new ItemStack(FoodInit.udon, 2, 2), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"foodNoodles",
							"foodSoysauce",
							"egg"
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

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
					8), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"cropBean",
									"dustSugar"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
					8), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"cropLotusSeed",
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

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.icecream, 3,
					5), null, 0F, null, DCHeatTier.COLD, null, null, false, new FluidStack(MainInit.cream,
							1000), new Object[] {
									"dustSugar",
									"cropCocoa"
			});

		}
	}

	public static void loadSteelRecipe() {
		if (ModuleConfig.food) {
			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.pasta, 2,
					0), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							100), new Object[] {
									"foodNoodles",
									"cropChilipepper",
									"cropGarlic"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.pasta, 2,
					1), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							100), new Object[] {
									"foodNoodles",
									"cropHerb",
									"cropGarlic",
									"listAllnut"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.pasta, 2,
					2), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							100), new Object[] {
									"foodNoodles",
									"cropHerb",
									"cropTomato",
									"foodCheese"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.pasta, 2,
					3), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							100), new Object[] {
									"foodNoodles",
									"cropOnion",
									"cropGarlic",
									"cropTomato",
									"foodShrimpraw"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.pasta, 2,
					4), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							100), new Object[] {
									"foodNoodles",
									"listAllheavycream",
									"listAllgreenveggie",
									"listAllmushroom",
									"listAllfishraw"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.pasta, 2,
					5), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							100), new Object[] {
									"foodNoodles",
									"cropTomato",
									"cropGarlic",
									"cropChilipepper",
									"foodCalamariraw"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.pasta, 2,
					6), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							100), new Object[] {
									"foodNoodles",
									"cropTomato",
									"cropOnion",
									"cropCarrot",
									"listAllbeefraw",
									"foodCheese"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.pasta, 2,
					7), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							100), new Object[] {
									"foodNoodles",
									"foodRoe",
									"foodSoysauce",
									"foodButter",
									"cropHerb"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dishBig, 3,
					0), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							200), new Object[] {
									"foodFirmtofu",
									"cropGinger",
									"cropGarlic",
									"cropChilipepper",
									"listAllbeefraw"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dishBig, 2,
					1), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.oil,
							100), new Object[] {
									"foodBowlofrice",
									"egg",
									"cropTomato",
									"foodButter"
			});

			regBoilRecipe(new ItemStack(FoodInit.dishBig, 1, 5), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"foodCalamariraw",
							"foodSoysauce",
							"foodRice"
			});

			regBoilRecipe(new ItemStack(FoodInit.dishBig, 3, 6), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"listAllmeatraw",
							"foodRice",
							"cropGarlic",
							"foodPlainyogurt",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.dishBig, 3, 6), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"listAllmeatraw",
							"foodRice",
							"cropGarlic",
							"foodRaisins",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.dishBig, 2, 7), null, 0F, null, null, null, false, new FluidStack(
					MainInit.stock, 1000), new Object[] {
							"foodRice",
							"listAllchickenraw",
							"cropGinger",
							"cropGarlic",
							"listAllgreenveggie"
			});

			regBoilRecipe(new ItemStack(FoodInit.dishBig, 2, 8), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"foodShrimpraw",
							"cropLemon",
							"cropChilipepper",
							"cropGinger",
							"listAllmushroom"
			});

			regBoilRecipe(new ItemStack(FoodInit.curry, 3, 0), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"listAllbean",
							"listAllveggie",
							"listAllgreenveggie",
							"seedHerb",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.curry, 3, 1), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"listAllbean",
							"cropTomato",
							"foodButter",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.curry, 3, 2), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"listAllfishraw",
							"foodOil",
							"cropOnion",
							"cropGinger",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.curry, 3, 2), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"listAllfishraw",
							"cropMustard",
							"cropOnion",
							"cropGinger",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.curry, 3, 3), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"cropSpinach",
							"foodCheese",
							"listAllheavycream",
							"cropTomato",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.curry, 3, 4), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"listAllchickenraw",
							"foodButter",
							"listAllheavycream",
							"cropTomato",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.curry, 3, 5), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"listAllporkraw",
							"bottleWine",
							"cropGarlic",
							"cropTomato",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.curry, 3, 5), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"listAllporkraw",
							"foodVinegar",
							"cropGarlic",
							"cropTomato",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.curry, 3, 6), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"listAllchickenraw",
							"listAllmushroom",
							"cropHerb",
							"cropChilipepper",
							"foodGarammasala"
			});

			regBoilRecipe(new ItemStack(FoodInit.curry, 3, 6), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 500), new Object[] {
							"listAllchickenraw",
							"cropBambooshoot",
							"cropCoconut",
							"cropChilipepper",
							"foodGarammasala"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.salad, 3,
					2), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"cropLotusRoot",
									"cropCarrot",
									"foodSoysauce",
									"cropChilipepper"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.salad, 3,
					3), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"cropSoybean",
									"cropSeaweed"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.salad, 3,
					4), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"foosVegimeat",
									"cropSoybean",
									"listAllmushroom",
									"cropCarrot",
									"listAllrootveggie"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.salad, 3,
					4), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"listAllchickenraw",
									"cropSoybean",
									"listAllmushroom",
									"cropCarrot",
									"listAllrootveggie"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.salad, 3,
					6), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"cropBean",
									"cropTomato",
									"dustSugar"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.salad, 3,
					6), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"cropBean",
									"cropTomato",
									"dropMolasses"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.salad, 3,
					8), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"cropPumpkin",
									"foodSoysauce",
									"dustSugar"
			});

			regBoilRecipe(new ItemStack(FoodInit.wagashi, 3, 10), null, 0F, null, null, null, false, new FluidStack(
					FluidRegistry.WATER, 200), new Object[] {
							"dustSugar",
							"foodRice",
							"foodAzukianko",
							"cropCinnamon"
			});

			regBoilRecipe(new ItemStack(FoodInit.drink, 2, 5), null, 0F, null, null, null, false, new FluidStack(
					MainInit.milk, 500), new Object[] {
							"foodTeaLeaf",
							"dustSugar",
							"cropCinnamon"
			});
		}
	}

	public static void loadAdvancedRecipe() {
		if (ModuleConfig.food_advanced) {
			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.medium, 4,
					0), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"foodMalt",
									"foodAgar"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.medium, 4,
					1), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"dustYeast",
									"dustPeptone",
									"dustSugar",
									"foodAgar"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.medium, 4,
					2), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"cropPotato",
									"dustSugar",
									"foodAgar",
									new ItemStack(FoodInit.antibiotic, 1, 1)
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.medium, 4,
					3), null, 0F, null, DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"foodViscera",
									"dustPeptone",
									"dustWhey",
									"dustSalt",
									"foodAgar"
			});

			// drinks
			regBoilRecipe(null, null, 0F, new FluidStack(FoodInit.tonic, 1000), null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"foodBakingSoda",
							"cropHerb",
							"dustSugar"
			});

			regBoilRecipe(null, null, 0F, new FluidStack(FoodInit.lemon_squash,
					1000), null, null, false, new FluidStack(
							FoodInit.tonic, 1000), new Object[] {
									"cropLemon",
									"dustSugar"
			});

			regBoilRecipe(null, null, 0F, new FluidStack(FoodInit.cola, 1000), null, null, false, new FluidStack(
					FluidRegistry.WATER, 1000), new Object[] {
							"cropLemon",
							"dustSugar",
							"cropHerb",
							"cropCinnamon",
							"foodBubblywater"
			});

			regBoilRecipe(new ItemStack(FoodInit.drink, 1, 6), null, 0F, null, null, null, false, new FluidStack(
					MainInit.milk, 500), new Object[] {
							new ItemStack(FoodInit.liquorBottle, 1, 2),
							"cropLemon",
							"listAllspice"
			});

			regBoilRecipe(new ItemStack(FoodInit.drink, 1, 6), null, 0F, null, null, null, false, new FluidStack(
					MainInit.milk, 500), new Object[] {
							new ItemStack(FoodInit.liquorBottle, 1, 2),
							"listAllcitrus",
							"listAllspice"
			});
		}
	}

	public static void loadBrewingRecipe() {
		// 麦芽
		regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1,
				3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								"cropWheat"
		});

		regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1,
				3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								new ItemStack(Items.WHEAT_SEEDS, 1, 0)
		});

		regNonFoodRecipe(new ItemStack(MainInit.foodDust, 9,
				3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
						200), new Object[] {
								new ItemStack(Blocks.HAY_BLOCK, 1, 0)
		});

		// 大豆加工品
		if (ModuleConfig.food) {
			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1, 6), new ItemStack(FoodInit.meat, 1,
					7), 0.5F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"cropSoybean",
									"dustSalt",
									"foodMalt"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1,
					7), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(
							FluidRegistry.WATER,
							200), new Object[] {
									new ItemStack(MainInit.foodDust, 1, 4),
									"dustSalt",
									"foodMalt"
			});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.salad, 1,
					7), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, null, new Object[] {
							"cropSoybean",
							"cropRice"
			});
		}

		// 醸造
		if (addEthanolRecipe) {
			regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
					300), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"dustStarch",
									"foodMalt"
			});

			regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
					200), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"dustSugar",
									"foodMalt"
			});

			regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
					150), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"cropPotato",
									"foodMalt"
			});

			regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
					100), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"cropWheat",
									"foodMalt"
			});

			regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
					150), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"foodRice",
									"foodMalt"
			});

			regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
					100), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"dropMolasses",
									"foodMalt"
			});

			regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(MainInit.ethanol,
					200), DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(FluidRegistry.WATER,
							1000), new Object[] {
									"cropDate",
									"foodMalt"
			});

		}
	}

	public static void regNonFoodRecipe(ItemStack out, ItemStack sec, float chance, FluidStack outF, DCHeatTier heat,
			DCHumidity hum, DCAirflow air, boolean cooling, FluidStack inF, Object... input) {
		RecipeAPI.registerFluidRecipes.addRecipe(out, sec, chance, outF, heat, hum, air, cooling, inF, input);
		if (ModuleConfig.machine && ModuleConfig.r_reactor) {
			RecipeAPI.registerReactorRecipes
					.addRecipe(out, sec, chance, outF, null, heat, (ItemStack) null, inF, null, input);
		}
	}

	public static void regReactorRecipe(ItemStack out, ItemStack sec, float chance, FluidStack outF, DCHeatTier heat,
			DCHumidity hum, DCAirflow air, boolean cooling, FluidStack inF, Object... input) {
		if (ModuleConfig.machine && ModuleConfig.r_reactor) {
			RecipeAPI.registerReactorRecipes
					.addRecipe(out, sec, chance, outF, null, heat, (ItemStack) null, inF, null, input);
		} else {
			RecipeAPI.registerFluidRecipes.addRecipe(out, sec, chance, outF, heat, hum, air, cooling, inF, input);
		}
	}

	public static void regBoilRecipe(ItemStack out, ItemStack sec, float chance, FluidStack outF, DCHumidity hum,
			DCAirflow air, boolean cooling, FluidStack inF, Object... input) {
		RecipeAPI.registerFluidRecipes.addRecipe(out, sec, chance, outF, ImmutableList
				.of(DCHeatTier.BOIL, DCHeatTier.OVEN, DCHeatTier.KILN), hum, air, cooling, inF, input);
	}
}
