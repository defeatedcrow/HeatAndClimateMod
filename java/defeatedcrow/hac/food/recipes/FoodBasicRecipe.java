package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FoodBasicRecipe {

	public static void load(RecipeResourcesMain res) {
		// devices
		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.potteryPot, 1, 0), new Object[] {
				"XYX",
				"X X",
				"XXX",
				'X',
				new ItemStack(Blocks.HARDENED_CLAY, 1, 0),
				'Y',
				"itemCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.steelPot, 1, 0), new Object[] {
				"XYX",
				"X X",
				"XXX",
				'X',
				"ingotSteel",
				'Y',
				new ItemStack(Blocks.GLASS, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.teaPot, 1, 0), new Object[] {
				" X ",
				"XYX",
				" XX",
				'X',
				"ingotSilver",
				'Y',
				"itemCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.teaPot, 1, 0), new Object[] {
				" X ",
				"XYX",
				" XX",
				'X',
				"ingotNickelsilver",
				'Y',
				"itemCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.cupSilver, 1, 0), new Object[] {
				"XXX",
				"XX ",
				'X',
				"ingotNickelsilver"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.cupSilver, 1, 1), new Object[] {
				"XXX",
				"XX ",
				'X',
				new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.cupSilver, 1, 2), new Object[] {
				"XXX",
				"XX ",
				'X',
				new ItemStack(Blocks.GLASS, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.silkwormBox, 1, 0), new Object[] {
				"XXX",
				"XZX",
				"YYY",
				'X',
				"stickWood",
				'Y',
				"plankWood",
				'Z',
				"string"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.silkworm, 8, 0), new Object[] {
				new ItemStack(MainInit.silkworm, 1, 3),
				new ItemStack(MainInit.silkworm, 1, 3),
				"paper"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.silkworm, 1, 3), new Object[] {
				new ItemStack(MainInit.silkworm, 1, 2)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.paperPack, 4, 0), new Object[] {
				" X ",
				"X X",
				" X ",
				'X',
				new ItemStack(Items.PAPER, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.dish, 1, 0), new Object[] {
				"XXX",
				" X ",
				'X',
				"gemChalcedony"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.dish, 1, 1), new Object[] {
				"XXX",
				" X ",
				'X',
				"ingotSilver"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.dish, 1, 1), new Object[] {
				"XXX",
				" X ",
				'X',
				"ingotNickelSilver"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(FoodInit.steakplate, 3, 0), new Object[] {
				"X X",
				" Y ",
				'X',
				"ingotIron",
				'Y',
				new ItemStack(MainInit.plate, 1, 0)
		});

		// materials

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.dropCream, 2, 0), new Object[] {
				"toolNormalYagen",
				"bucketMilk"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.meat, 1, 1), new Object[] {
				"foodViscera"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastry, 4, 0), new Object[] {
				"dustFlour",
				"foodButter",
				"egg",
				"dustSalt"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastry, 2, 0), new Object[] {
				"foodFlour",
				"foodOil",
				"bucketWater",
				"dustSalt"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.dairy, 1, 2), new Object[] {
				"foodCream",
				"egg",
				"dustFlour"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.dairy, 1, 2), new Object[] {
				"foodCream",
				"egg",
				"dustSugar"
		});

		// foods

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.bread, 2, 0), new Object[] {
				"foodFlour",
				"dustSalt",
				"bucketWater"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.bread, 1, 2), new Object[] {
				new ItemStack(FoodInit.bread, 1, 0),
				new ItemStack(Items.PAPER, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.bread, 3, 4), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"foodButter"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.bread, 1, 6), new Object[] {
				new ItemStack(FoodInit.bread, 1, 0),
				"cropTomato",
				"foodCheese",
				"cropHerb"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.bread, 1, 8), new Object[] {
				new ItemStack(FoodInit.bread, 1, 1),
				"foodCustard"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sticks, 1, 0), new Object[] {
				"stickWood",
				new ItemStack(Items.FISH, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sticks, 1, 2), new Object[] {
				"stickWood",
				new ItemStack(Items.CHICKEN, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sticks, 1, 4), new Object[] {
				"stickWood",
				new ItemStack(Items.PORKCHOP, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sticks, 1, 6), new Object[] {
				"stickWood",
				new ItemStack(Items.BEEF, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sticks, 1, 8), new Object[] {
				"stickWood",
				new ItemStack(Items.MUTTON, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sticks, 1, 10), new Object[] {
				"stickWood",
				"fishSquid"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastryRound, 1, 0), new Object[] {
				"foodPastry",
				"cropApple",
				"dustSugar",
				"foodButter"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastryRound, 1, 0), new Object[] {
				"foodPastry",
				"cropApple",
				"dropHoney",
				"foodButter"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastryRound, 1, 2), new Object[] {
				"foodPastry",
				"cropLemon",
				"dustSugar",
				"foodCream"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastryRound, 1, 2), new Object[] {
				"foodPastry",
				"cropLemon",
				"dropHoney",
				"foodCream"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastryRound, 1, 4), new Object[] {
				"foodPastry",
				"cropSpinach",
				"foodAnyMeat",
				"foodCustard",
				"foodCheese"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastryRound, 1, 6), new Object[] {
				"foodPastry",
				"cropPotato",
				"cropOnion",
				"foodFish",
				"foodCustard",
				"foodCheese"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"foodButter",
				"cropMulberry"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"foodButter",
				"cropCherry"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"foodButter",
				"listAllberry"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 0), new Object[] {
				"foodPastry",
				"dustSugar"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 2), new Object[] {
				"foodPastry",
				"foodAnyMeat",
				"dustSalt",
				"cropOnion"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 2), new Object[] {
				"foodPastry",
				"foodAnyMeat",
				"dustSalt",
				"cropHerb"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 4), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropCocoa"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 4), new Object[] {
				"foodPastry",
				"dustSugar",
				new ItemStack(Items.DYE, 1, 3)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropApple"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropCherry"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropPeach"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropOrange"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropPair"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropLotusSeed",
				"egg"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropLotusSeed"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.pastrySquare, 1, 10), new Object[] {
				"foodPastry",
				"foodCustard"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sandwich, 2, 0), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"cropApple",
				"dustSugar"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sandwich, 2, 1), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"egg"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sandwich, 2, 2), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"cropLemon",
				"dustSugar"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sandwich, 2, 2), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"cropLemon",
				"dropHoney"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sandwich, 2, 3), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"cropSpinach",
				"cropOnion",
				new ItemStack(Items.COOKED_PORKCHOP, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.sandwich, 2, 3), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"cropHerb",
				"cropTomato",
				new ItemStack(Items.COOKED_CHICKEN, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.clubsandwich, 2, 0), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"listAllveggie",
				"cropTomato",
				new ItemStack(Items.COOKED_BEEF, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.clubsandwich, 2, 1), new Object[] {
				new ItemStack(FoodInit.bread, 1, 1),
				"listAllveggie",
				"cropTomato",
				new ItemStack(Items.COOKED_FISH, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.salad, 1, 0), new Object[] {
				"cropSpinach",
				"cropTomato",
				"listAllveggie"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.salad, 1, 0), new Object[] {
				"cropCabagge",
				"cropTomato",
				"listAllveggie"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.salad, 1, 0), new Object[] {
				"cropLettuce",
				"cropTomato",
				"listAllveggie"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.salad, 1, 1), new Object[] {
				"egg",
				"listAllveggie",
				new ItemStack(Items.BAKED_POTATO, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.salad, 1, 2), new Object[] {
				"cropLotusRoot",
				"cropCarrot"
		});

		ItemStack[] meats = {
				new ItemStack(Items.BEEF),
				new ItemStack(Items.PORKCHOP),
				new ItemStack(Items.CHICKEN),
				new ItemStack(Items.FISH, 1, 32767)
		};
		for (int i = 0; i < meats.length; i++) {
			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.plateMeal, 1, i * 2), new Object[] {
					new ItemStack(FoodInit.steakplate, 1, 0),
					"cropTomato",
					"listAllveggie",
					meats[i]
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.plateMeal, 1, i * 2), new Object[] {
					new ItemStack(FoodInit.steakplate, 1, 0),
					"cropPotato",
					"listAllveggie",
					meats[i]
			});
		}

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.plateSoup, 1, 0), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"cropPotato",
				"foodButter",
				"dustSalt"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.plateSoup, 1, 2), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"listAllveggie",
				"cropTomato",
				"foodCheese"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.cake, 1, 0), new Object[] {
				"foodButter",
				"foodFlour",
				"dustSugar",
				"egg"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.cake, 1, 2), new Object[] {
				new ItemStack(FoodInit.cake, 1, 1),
				"cropCocoa",
				"foodCream"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.cake, 1, 8), new Object[] {
				"egg",
				"cropSpinach"
		});

		// yagen

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(FoodInit.dropOil, 1, 0), new Object[] {
				"toolNormalYagen",
				"cropOlive",
				"cropOlive"
		});

		// smelting
		GameRegistry.addSmelting(new ItemStack(FoodInit.seeds, 1, 4), new ItemStack(FoodInit.teaLeaves, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(FoodInit.crops, 1, 8), new ItemStack(FoodInit.teaLeaves, 1, 1), 0.1F);
	}

	public static void loadCrops(RecipeResourcesMain res) {

		String[] crop2 = {
				"Rice",
				"Onion",
				"Spinach",
				"Tomato",
				"Coffee",
				"Cotton"
		};
		for (int i = 0; i < 6; i++) {
			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, i), new Object[] {
					new ItemStack(FoodInit.crops, 1, i)
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, i), new Object[] {
					"crop" + crop2[i]
			});
		}

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.saplings, 1, 0), new Object[] {
				new ItemStack(FoodInit.crops, 1, 6),
				new ItemStack(Blocks.SAPLING, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.saplings, 1, 1), new Object[] {
				new ItemStack(FoodInit.crops, 1, 7),
				new ItemStack(Blocks.SAPLING, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.saplings, 1, 2), new Object[] {
				new ItemStack(FoodInit.crops, 1, 8),
				new ItemStack(Blocks.SAPLING, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, 7), new Object[] {
				new ItemStack(FoodInit.crops, 1, 9)
		});

		// seeds another recipes
		if (ModuleConfig.agri) {
			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, 0), new Object[] {
					new ItemStack(Items.WHEAT_SEEDS, 1, 0),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, 1), new Object[] {
					new ItemStack(Items.POTATO, 1, 0),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, 2), new Object[] {
					new ItemStack(Items.CARROT, 1, 0),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, 3), new Object[] {
					new ItemStack(Items.BEETROOT, 1, 0),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, 4), new Object[] {
					new ItemStack(Items.DYE, 1, 3),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, 5), new Object[] {
					new ItemStack(Items.REEDS, 1, 0),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, 6), new Object[] {
					new ItemStack(Blocks.WATERLILY, 1, 0),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, 8), new Object[] {
					new ItemStack(Items.MELON_SEEDS, 1, 0),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.saplings, 1, 0), new Object[] {
					new ItemStack(Blocks.SAPLING, 1, 1),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.saplings, 1, 1), new Object[] {
					new ItemStack(Blocks.SAPLING, 1, 2),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.saplings, 1, 2), new Object[] {
					new ItemStack(Blocks.SAPLING, 1, 0),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.saplings, 1, 3), new Object[] {
					new ItemStack(Blocks.SAPLING, 1, 4),
					"gemChalcedony"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.seeds, 1, 7), new Object[] {
					"blockTallGrass",
					"gemChalcedony"
			});
		}

		String[] crops = new String[] {
				"cropRice",
				"cropOnion",
				"cropSpinach",
				"cropTomato",
				"cropCoffee",
				"cropCotton",
				"cropLemon",
				"cropOlive",
				"cropTea"
		};
		for (int i = 0; i < crops.length; i++) {
			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.cropBasket, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					crops[i]
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.crops, 8, i), new Object[] {
					new ItemStack(MainInit.cropBasket, 1, i)
			});
		}

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.cropBasket, 1, 9), new Object[] {
				"XXX",
				"X X",
				"XXX",
				'X',
				"cropLotusSeed"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.crops, 8, 10), new Object[] {
				new ItemStack(MainInit.cropBasket, 1, 9)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.cropBasket, 1, 10), new Object[] {
				"XXX",
				"X X",
				"XXX",
				'X',
				"cropMulberry"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.crops, 8, 11), new Object[] {
				new ItemStack(MainInit.cropBasket, 1, 10)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.paperPack, 4, 1), new Object[] {
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(Items.WATER_BUCKET)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(FoodInit.paperPack, 4, 2), new Object[] {
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(Items.MILK_BUCKET)
		});

	}

}
