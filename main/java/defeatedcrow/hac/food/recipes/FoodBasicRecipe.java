package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.recipes.LoadingContRecipe;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FoodBasicRecipe {

	public static void load(RecipeResourcesMain res) {
		// devices
		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.potteryPot, 1, 0), new Object[] {
				"XYX",
				"X X",
				"XXX",
				'X',
				new ItemStack(Blocks.HARDENED_CLAY, 1, 0),
				'Y',
				"itemCloth"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.skillet, 1, 0), new Object[] {
				"XXX",
				"XXY",
				'X',
				"ingotIron",
				'Y',
				"itemCloth"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.steelPot, 1, 0), new Object[] {
				"XYX",
				"X X",
				"XXX",
				'X',
				"ingotSteel",
				'Y',
				new ItemStack(Blocks.GLASS, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.teaPot, 1, 0), new Object[] {
				" X ",
				"XYX",
				" XX",
				'X',
				"ingotSilver",
				'Y',
				"itemCloth"
		});

		DCRecipe.jsonShapedRecipe("food", 3, new ItemStack(FoodInit.teaPot, 1, 0), new Object[] {
				" X ",
				"XYX",
				" XX",
				'X',
				"ingotNickelsilver",
				'Y',
				"itemCloth"
		});

		DCRecipe.jsonShapedRecipe("food", 3, new ItemStack(FoodInit.teaPot, 1, 0), new Object[] {
				" X ",
				"XYX",
				" XX",
				'X',
				"ingotNickelsilver",
				'Y',
				"itemCloth"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.cupSilver, 1, 0), new Object[] {
				"XXX",
				"XX ",
				'X',
				"ingotNickelsilver"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.cupSilver, 1, 1), new Object[] {
				"XXX",
				"XX ",
				'X',
				new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.cupSilver, 1, 2), new Object[] {
				"XXX",
				"XX ",
				'X',
				new ItemStack(Blocks.GLASS, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.paperPack, 4, 0), new Object[] {
				" X ",
				"X X",
				" X ",
				'X',
				"paper"
		});

		// 中身リセット
		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.potteryPot, 1, 0), new Object[] {
				new ItemStack(FoodInit.potteryPot, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.skillet, 1, 0), new Object[] {
				new ItemStack(FoodInit.skillet, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.steelPot, 1, 0), new Object[] {
				new ItemStack(FoodInit.steelPot, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.teaPot, 1, 0), new Object[] {
				new ItemStack(FoodInit.teaPot, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.dish, 1, 0), new Object[] {
				"XXX",
				" X ",
				'X',
				"gemChalcedony"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.dish, 1, 1), new Object[] {
				"XXX",
				" X ",
				'X',
				"ingotSilver"
		});

		DCRecipe.jsonShapedRecipe("food", 2, new ItemStack(FoodInit.dish, 1, 1), new Object[] {
				"XXX",
				" X ",
				'X',
				"ingotNickelSilver"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.steakplate, 3, 0), new Object[] {
				"X X",
				" Y ",
				'X',
				"ingotIron",
				'Y',
				new ItemStack(MainInit.plate, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.silkwormBox, 1, 0), new Object[] {
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

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.silkworm, 8, 0), new Object[] {
				new ItemStack(MainInit.silkworm, 1, 3),
				new ItemStack(MainInit.silkworm, 1, 3),
				"paper"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.silkworm, 1, 3), new Object[] {
				new ItemStack(MainInit.silkworm, 1, 2)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cutlery, 1, 0), new Object[] {
				"stickWood",
				"stickWood",
				"gemChalcedony"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.cutlery, 1, 1), new Object[] {
				"X",
				"Y",
				'X',
				"ingotSilver",
				'Y',
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.cutlery, 1, 1), new Object[] {
				new ItemStack(FoodInit.cutlery, 1, 2)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cutlery, 1, 2), new Object[] {
				new ItemStack(FoodInit.cutlery, 1, 1)
		});

		// materials

		// DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dropCream, 2, 0), new Object[] {
		// "toolNormalYagen",
		// "bucketMilk"
		// });
		//
		// DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dropCream, 2, 0), new Object[] {
		// "toolNormalYagen",
		// new ItemStack(FoodInit.paperPack, 1, 2)
		// });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.meat, 1, 1), new Object[] {
				"foodViscera"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dip, 3, 0), new Object[] {
				"foodViscera",
				"listAllmilk",
				"cropOnion",
				"cropGarlic"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dip, 3, 1), new Object[] {
				"foodRaisins",
				"listAllnut",
				"foodButter"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dip, 3, 2), new Object[] {
				"cropTomato",
				"cropChilipepper",
				"cropOnion",
				"cropBellpepper"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.dip, 3, 2), new Object[] {
				"cropTomato",
				"cropChilipepper",
				"cropOnion",
				"cropLemon"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastry, 4, 0), new Object[] {
				"dustFlour",
				"foodButter",
				"egg",
				"dustSalt"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.pastry, 2, 0), new Object[] {
				"foodFlour",
				"foodOil",
				"egg",
				"dustSalt"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastry, 1, 1), new Object[] {
				"foodDough",
				"foodDough"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dairy, 1, 2), new Object[] {
				"foodCream",
				"egg",
				"dustFlour"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.dairy, 1, 2), new Object[] {
				"foodCream",
				"egg",
				"dustSugar"
		});

		// foods

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 2, 0), new Object[] {
				"foodFlour",
				"dustSalt",
				"bucketWater"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 2), new Object[] {
				"foodDough",
				"paper"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 3, 4), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"foodButter"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 6), new Object[] {
				"foodDough",
				"cropTomato",
				"foodCheese",
				"cropHerb"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 8), new Object[] {
				"bread",
				"foodCustard"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 10), new Object[] {
				"bread",
				"foodButter",
				"cropGarlic"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 2, 12), new Object[] {
				"foodFlour",
				"dustSalt",
				"dustSugar",
				"bucketWater"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 2, 14), new Object[] {
				"foodFlour",
				"dustSugar",
				"bucketMilk",
				"egg",
				"foodBakingSoda"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 16), new Object[] {
				"foodDough",
				"cropWalnut"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 2, 18), new Object[] {
				"foodFlour",
				"dustSugar",
				"foodButter",
				"egg",
				"cropGinger"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.bread, 2, 18), new Object[] {
				"foodFlour",
				"dropMolasses",
				"foodButter",
				"egg",
				"cropGinger"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 3, 20), new Object[] {
				"cropCorn",
				"dustLime",
				"foodOil",
				"bucketWater"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.bread, 3, 20), new Object[] {
				"foodFlour",
				"foodOil",
				"dustSalt",
				"bucketWater"
		});

		DCRecipe.jsonShapelessRecipe("food", 3, new ItemStack(FoodInit.bread, 3, 20), new Object[] {
				"cropCorn",
				"dustAsh",
				"foodOil",
				"bucketWater"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 22), new Object[] {
				"foodDough",
				"foodRaisins"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 24), new Object[] {
				"foodDough",
				"foodSausage"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 2, 26), new Object[] {
				"dustBran",
				"foodFlour",
				"foodOil",
				"listAllsugar"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.bread, 2, 26), new Object[] {
				"dustBran",
				"foodFlour",
				"foodOil",
				"dropHoney"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.bread, 2, 28), new Object[] {
				"foodDough",
				"cropCinnamon",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.bread, 1, 30), new Object[] {
				"foodDough",
				"foodAnko"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pizza, 1, 0), new Object[] {
				"foodDough",
				"foodRoe",
				"foodRicecake",
				"foodCheese",
				"cropSeaweed"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pizza, 1, 2), new Object[] {
				"foodDough",
				"listAllchickenraw",
				"egg",
				"foodSoysauce",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.pizza, 1, 2), new Object[] {
				"foodDough",
				"listAllchickenraw",
				"egg",
				"foodSoysauce",
				"dropHoney"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cookie, 4, 0), new Object[] {
				"foodFlour",
				"foodButter",
				"dustSugar",
				"listAllnut"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 2), new Object[] {
				"stickWood",
				"listAllchickenraw"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 4), new Object[] {
				"stickWood",
				"listAllporkraw"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 6), new Object[] {
				"stickWood",
				"listAllbeefraw"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 8), new Object[] {
				"stickWood",
				"listAllmuttonraw"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 10), new Object[] {
				"stickWood",
				"foodCalamariraw"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 0), new Object[] {
				"stickWood",
				"listAllfishraw"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 12), new Object[] {
				"stickWood",
				"foodBowlofrice",
				"foodMiso"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 14), new Object[] {
				"stickWood",
				new ItemStack(FoodInit.nonEntity, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.sticks, 1, 14), new Object[] {
				"stickWood",
				"foodMarshmellows"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 16), new Object[] {
				"stickWood",
				"foodViscera",
				"cropGinger",
				"cropGarlic"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.sticks, 1, 16), new Object[] {
				"stickWood",
				"foodViscera",
				"cropGinger",
				"cropLemon"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.mochi, 1, 0), new Object[] {
				"foodBowlofrice"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 0), new Object[] {
				"foodPastry",
				"cropApple",
				"dustSugar",
				"foodButter"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.pastryRound, 1, 0), new Object[] {
				"foodPastry",
				"cropApple",
				"dropHoney",
				"foodButter"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 2), new Object[] {
				"foodPastry",
				"cropLemon",
				"dustSugar",
				"foodCream"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.pastryRound, 1, 2), new Object[] {
				"foodPastry",
				"cropLemon",
				"dropHoney",
				"foodCream"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 4), new Object[] {
				"foodPastry",
				"cropSpinach",
				"listAllmeatraw",
				"foodCustard",
				"foodCheese"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 6), new Object[] {
				"foodPastry",
				"cropPotato",
				"cropOnion",
				"listAllfishraw",
				"foodCustard",
				"foodCheese"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"foodButter",
				"cropMulberry"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"foodButter",
				"cropCherry"
		});

		DCRecipe.jsonShapelessRecipe("food", 3, new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"foodButter",
				"cropGrape"
		});

		DCRecipe.jsonShapelessRecipe("food", 4, new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"foodButter",
				"listAllberry"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 10), new Object[] {
				"foodPastry",
				"dustSugar",
				"foodButter",
				"listAllnut",
				"cropCocoa"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 12), new Object[] {
				"foodPastry",
				"foodCustard",
				"foodRaisins"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.pastryRound, 1, 12), new Object[] {
				"foodPastry",
				"foodCustard",
				"cropCherry"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 14), new Object[] {
				"foodPastry",
				"foodLiverpaste",
				"cropPotato",
				"cropHerb"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 2, 0), new Object[] {
				"foodPastry",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 2, 2), new Object[] {
				"foodPastry",
				"listAllmeatraw",
				"dustSalt",
				"cropOnion"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.pastrySquare, 2, 2), new Object[] {
				"foodPastry",
				"listAllmeatraw",
				"dustSalt",
				"cropHerb"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 2, 4), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropCocoa"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.pastrySquare, 2, 4), new Object[] {
				"foodPastry",
				"dustSugar",
				new ItemStack(Items.DYE, 1, 3)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 2, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropApple"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.pastrySquare, 2, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropCherry"
		});

		DCRecipe.jsonShapelessRecipe("food", 3, new ItemStack(FoodInit.pastrySquare, 2, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropPeach"
		});

		DCRecipe.jsonShapelessRecipe("food", 4, new ItemStack(FoodInit.pastrySquare, 2, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropOrange"
		});

		DCRecipe.jsonShapelessRecipe("food", 5, new ItemStack(FoodInit.pastrySquare, 2, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropPair"
		});

		DCRecipe.jsonShapelessRecipe("food", 6, new ItemStack(FoodInit.pastrySquare, 2, 6), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropMulberry"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 3, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropLotusSeed",
				"egg"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.pastrySquare, 2, 8), new Object[] {
				"foodPastry",
				"dustSugar",
				"cropLotusSeed"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 2, 10), new Object[] {
				"foodPastry",
				"foodCustard"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 0), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"cropApple",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 1), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"egg"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 2), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"cropLemon",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.sandwich, 2, 2), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"cropLemon",
				"dropHoney"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 3), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"listAllgreenveggie",
				"cropOnion",
				"listAllmeatcooked"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.sandwich, 2, 3), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"cropHerb",
				"cropTomato",
				"listAllmeatcooked"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 4), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"foodLiverpaste",
				"cropTomato",
				"listAllgreenveggie"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 5), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"cropOnion",
				"foodSmokedsalmon"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.clubsandwich, 1, 0), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"listAllgreenveggie",
				"cropTomato",
				"listAllbeefcooked"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.clubsandwich, 1, 1), new Object[] {
				new ItemStack(FoodInit.bread, 1, 1),
				"listAllgreenveggie",
				"cropTomato",
				"listAllfishcooked"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 0), new Object[] {
				"listAllgreenveggie",
				"cropTomato",
				"listAllveggie"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 1), new Object[] {
				"egg",
				"listAllgreenveggie",
				new ItemStack(Items.BAKED_POTATO, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 2, 5), new Object[] {
				"cropSpinach",
				"foodMiso"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 9), new Object[] {
				"listAllgreenveggie",
				"foodSmokedsalmon",
				"cropOnion",
				"cropOlive"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 10), new Object[] {
				"listAllgreenveggie",
				"foodFirmtofu",
				"cropSeaweed"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 11), new Object[] {
				"listAllgreenveggie",
				"listAllnut",
				"cropBean"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.salad, 1, 11), new Object[] {
				"listAllgreenveggie",
				"listAllnut",
				"cropLotusSeed"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.salad, 1, 12), new Object[] {
				"listAllgreenveggie",
				"cropTomato",
				"cropMelon"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.ricebowl, 1, 2), new Object[] {
				"foodBowlofrice",
				"dustSalt"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.ricebowl, 1, 3), new Object[] {
				"foodBowlofrice",
				"dustSalt",
				"cropSeaweed"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.ricebowl, 1, 4), new Object[] {
				"foodBowlofrice",
				"foodMiso",
				"cropHerb"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.ricebowl, 1, 5), new Object[] {
				"foodBowlofrice",
				"dustSalt",
				"foodRoe",
				"cropSeaweed"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.ricebowl, 1, 8), new Object[] {
				"foodBowlofrice",
				"dustSalt",
				"foodSalmonraw",
				"foodGreentea"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.ricebowl, 1, 9), new Object[] {
				"foodBowlofrice",
				"cropSeaweed",
				"foodRoe",
				"foodGreentea"
		});

		String[] meats = {
				"listAllbeefraw",
				"listAllporkraw",
				"listAllchickenraw",
				"listAllfishraw"
		};
		for (int i = 0; i < meats.length; i++) {
			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateMeal, 1, i * 2), new Object[] {
					new ItemStack(FoodInit.steakplate, 1, 0),
					"cropTomato",
					"listAllveggie",
					meats[i]
			});

			DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.plateMeal, 1, i * 2), new Object[] {
					new ItemStack(FoodInit.steakplate, 1, 0),
					"cropPotato",
					"listAllveggie",
					meats[i]
			});
		}

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateMeal, 1, 8), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"cropGarlic",
				"foodButter",
				"listAllbeefraw"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.plateMeal, 1, 8), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"cropGarlic",
				"foodButter",
				"listAllvenisonraw"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateMeal, 1, 10), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"cropGarlic",
				"cropOnion",
				"cropCarrot",
				"listAllchickenraw"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.plateMeal, 1, 10), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"cropGarlic",
				"cropOnion",
				"foodRice",
				"listAllchickenraw"
		});

		DCRecipe.jsonShapelessRecipe("food", 3, new ItemStack(FoodInit.plateMeal, 1, 10), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"cropGarlic",
				"cropOnion",
				"cropCelery",
				"listAllchickenraw"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 0), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"cropPotato",
				"foodButter",
				"dustSalt"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 2), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"listAllveggie",
				"cropTomato",
				"foodCheese"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 4), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"cropPotato",
				"foodFlour",
				"listAllmilk",
				"foodCheese"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.plateSoup, 1, 4), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"foodShrimpraw",
				"foodFlour",
				"listAllmilk",
				"foodCheese"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 6), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"foodShrimpraw",
				"foodCalamariraw",
				"foodRice",
				"cropTomato",
				"foodStock"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.plateSoup, 1, 6), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"foodShrimpraw",
				"listAllfishraw",
				"foodRice",
				"cropTomato",
				"foodStock"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 8), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"listAllchickenraw",
				"foodRice",
				"cropTomato",
				"cropBean",
				"foodStock"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.plateSoup, 1, 8), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"listAllrabbitraw",
				"foodRice",
				"cropTomato",
				"cropBean",
				"foodStock"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 10), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"listAllporkraw",
				"cropGarlic",
				"cropGinger",
				"listAllgreenveggie",
				"foodDough"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.snack, 2, 1), new Object[] {
				new ItemStack(FoodInit.snack, 1, 0),
				new ItemStack(FoodInit.deepFry, 1, 2),
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.snack, 3, 4), new Object[] {
				"foodPita",
				"listAllchickencooked",
				"listAllgreenveggie"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.snack, 3, 4), new Object[] {
				"foodPita",
				"listAllbeefcooked",
				"listAllgreenveggie"
		});

		DCRecipe.jsonShapelessRecipe("food", 3, new ItemStack(FoodInit.snack, 3, 4), new Object[] {
				"foodPita",
				"listAllmuttoncooked",
				"listAllgreenveggie"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.snack, 3, 5), new Object[] {
				"foodPita",
				new ItemStack(FoodInit.deepFry, 1, 3),
				"listAllgreenveggie",
				"cropTomato"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.setMeal, 1, 0), new Object[] {
				"bread",
				"foodSausage",
				"egg",
				"listAllmushroom",
				"cropTomato",
				new ItemStack(FoodInit.salad, 1, 6),
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.setMeal, 1, 1), new Object[] {
				"foodBowlofrice",
				new ItemStack(FoodInit.bowlSoup, 1, 11),
				new ItemStack(FoodInit.salad, 1, 7),
				"listAllveggie"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.setMeal, 1, 2), new Object[] {
				"foodBowlofrice",
				new ItemStack(FoodInit.bowlSoup, 1, 11),
				"listAllfishcooked",
				"listAllveggie"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.nonEntity, 2, 1), new Object[] {
				"listAllnut",
				"cropDate"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.nonEntity, 3, 1), new Object[] {
				"listAllnut",
				"cropDate",
				"foodCheese"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.nonEntity, 2, 6), new Object[] {
				"egg",
				"dustSugar",
				"dustStarch",
				"listAllmilk"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.nonEntity, 3, 7), new Object[] {
				"foodRice",
				"dustBran",
				"dropMolasses",
				"listAllberry",
				"listAllnut"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 2, 0), new Object[] {
				"cropTomato",
				"listAllherb",
				"foodCheese"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 2, 1), new Object[] {
				"foodGarlicbread",
				"cropTomato",
				"foodSmokedsalmon"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.dishSq, 2, 1), new Object[] {
				"bread",
				"cropGarlic",
				"cropTomato",
				"foodSmokedsalmon"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 2, 2), new Object[] {
				"foodSmokedsalmon",
				"foodCheese"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 1, 3), new Object[] {
				"foodBowlofrice",
				"listAllfishraw",
				"listAllfishraw",
				"egg"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 1, 4), new Object[] {
				"listAllfishraw",
				"listAllfishraw",
				"listAllherb"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 1, 6), new Object[] {
				"foodStock",
				"egg"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 1, 7), new Object[] {
				"foodFirmtofu",
				"foodSoysauce",
				"listAllherb"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishBig, 2, 2), new Object[] {
				"foodTortilla",
				"listAllgreenveggie",
				"foodSalsa",
				"listAllmeatcooked"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.dishBig, 2, 2), new Object[] {
				"foodTortilla",
				"cropLettuce",
				"cropTomato",
				"cropChilipepper",
				"listAllmeatcooked"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishBig, 2, 3), new Object[] {
				"foodBowlofrice",
				"listAllgreenveggie",
				"foodSalsa",
				"listAllmeatcooked"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.dishBig, 2, 3), new Object[] {
				"foodBowlofrice",
				"cropLettuce",
				"cropTomato",
				"cropChilipepper",
				"listAllmeatcooked"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishBig, 2, 4), new Object[] {
				"foodTortilla",
				"foodCheese",
				"cropTomato",
				"cropChilipepper"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.dishBig, 2, 4), new Object[] {
				"foodTortilla",
				"foodCheese",
				"foodChili"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.curry, 1, 7), new Object[] {
				"foodBowlofrice",
				"foodCurry"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 2, 0), new Object[] {
				"bucketWater",
				"cropGinger",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.drink, 2, 0), new Object[] {
				"bucketWater",
				"cropGinger",
				"dropHoney"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 2, 1), new Object[] {
				"bucketWater",
				"dustStarch",
				"cropGinger"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.drink, 2, 1), new Object[] {
				"bucketWater",
				"dustStarch",
				"cropLemon"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 2, 2), new Object[] {
				"bread",
				"dustSalt",
				"cropTomato",
				"cropGarlic"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.drink, 2, 2), new Object[] {
				"bread",
				"dustSalt",
				"cropTomato",
				"cropCucumber"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 1, 3), new Object[] {
				"bucketWater",
				"cropApple",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 1, 4), new Object[] {
				"bucketWater",
				"cropGrape",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 1, 7), new Object[] {
				"bucketWater",
				"dustSakeLees",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.drink, 1, 7), new Object[] {
				"bucketWater",
				"foodKoji"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 2, 8), new Object[] {
				new ItemStack(FoodInit.liquorBottle, 1, 6),
				"egg",
				"dustSugar",
				"listAllheavycream",
				"listAllspice"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.drink, 2, 8), new Object[] {
				new ItemStack(FoodInit.liquorBottle, 1, 11),
				"egg",
				"dustSugar",
				"listAllheavycream",
				"listAllspice"
		});

		// cake

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 1, 0), new Object[] {
				"foodButter",
				"foodFlour",
				"dustSugar",
				"egg"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 1, 2), new Object[] {
				new ItemStack(FoodInit.cake, 1, 1),
				"cropCocoa",
				"foodCream"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 1, 8), new Object[] {
				"egg",
				"cropSpinach"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 3, 10), new Object[] {
				"foodFlour",
				"dropMolasses",
				"cropDate",
				"egg",
				"foodButter"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 1, 11), new Object[] {
				"foodPancakes",
				"foodPancakes",
				"foodPancakes",
				"foodPancakes",
				"foodButter",
				"listAllheavycream",
				"listAllberry"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.cake, 1, 11), new Object[] {
				"foodPancakes",
				"foodPancakes",
				"foodPancakes",
				"foodPancakes",
				"foodButter",
				"dropHoney",
				"listAllberry"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 2, 13), new Object[] {
				"foodCheese",
				"foodStock",
				"cropOnion"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 2, 15), new Object[] {
				"foodCrackers",
				new ItemStack(FoodInit.sticks, 1, 15),
				"cropCocoa"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 2, 16), new Object[] {
				"foodCrackers",
				"foodRaisinbutter"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 2, 17), new Object[] {
				"foodPlainyogurt",
				"listAllheavycream",
				"cropLemon",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 2, 18), new Object[] {
				"foodFlour",
				"dropMolasses",
				"foodGreenteaLeaf",
				"listAllheavycream",
				"egg"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.icecream, 1, 6), new Object[] {
				new ItemStack(FoodInit.icecream, 1, 2),
				"listAllheavycream",
				"cropGrape",
				"listAllberry"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.icecream, 1, 6), new Object[] {
				new ItemStack(FoodInit.icecream, 1, 2),
				"foodPlainyogurt",
				"cropGrape",
				"listAllberry"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.icecream, 1, 7), new Object[] {
				new ItemStack(FoodInit.icecream, 1, 3),
				"foodCheese",
				"cropLemon",
				"listAllnut"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.icecream, 1, 7), new Object[] {
				new ItemStack(FoodInit.icecream, 1, 3),
				"foodCheese",
				"cropOrange",
				"listAllnut"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.icecream, 1, 8), new Object[] {
				new ItemStack(FoodInit.icecream, 1, 0),
				"cropCocoa",
				"cropMelon",
				"listAllnut"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.icecream, 1, 9), new Object[] {
				new ItemStack(FoodInit.icecream, 1, 0),
				"foodAzukianko",
				"foodGreenteaLeaf",
				"foodRicecake"
		});

		// soymeats

		DCRecipe.jsonShapelessRecipe("food", 3, new ItemStack(FoodInit.sandwich, 2, 3), new Object[] {
				new ItemStack(FoodInit.bread, 1, 3),
				"listAllgreenveggie",
				"cropOnion",
				new ItemStack(FoodInit.meat, 1, 5)
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.clubsandwich, 1, 1), new Object[] {
				new ItemStack(FoodInit.bread, 1, 1),
				"listAllgreenveggie",
				"cropTomato",
				new ItemStack(FoodInit.meat, 1, 5)
		});

		// sweets

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.icecream, 1, 1), new Object[] {
				new ItemStack(FoodInit.icecream, 1, 0),
				"cropSoybean",
				"dropMolasses"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 0), new Object[] {
				"foodRicecake",
				"cropSoybean"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 1), new Object[] {
				"foodRicecake",
				"foodSoysauce",
				"dustSugar",
				"cropSeaweed"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 2), new Object[] {
				"foodRicecake",
				new ItemStack(MainInit.bakedApple, 1, 6),
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 3), new Object[] {
				"foodRicecake",
				"foodButter",
				"dustSugar",
				"egg"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 4), new Object[] {
				"foodRice",
				"foodSiroanko",
				"dustSugar",
				"listAllberry"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 5), new Object[] {
				"foodRice",
				"foodSoysauce",
				"dustSugar",
				"cropWalnut"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 6), new Object[] {
				"foodDough",
				"foodSiroanko",
				"egg"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.wagashi, 2, 6), new Object[] {
				"foodDough",
				"foodSiroanko",
				"egg",
				"cropChestnut"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 7), new Object[] {
				"foodRice",
				"foodSiroanko",
				"dustSugar",
				"dyeRed"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 8), new Object[] {
				"foodRicecake",
				"cropSoybean",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 9), new Object[] {
				"dustSugar",
				"foodAzukianko"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 11), new Object[] {
				"foodRicecake",
				"foodAzukianko"
		});

		// yagen

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dropOil, 1, 0), new Object[] {
				"toolNormalYagen",
				"cropOlive",
				"cropOlive"
		});

		// smelting
		GameRegistry.addSmelting(new ItemStack(FoodInit.seeds, 1, 4), new ItemStack(FoodInit.teaLeaves, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(FoodInit.crops, 1, 8), new ItemStack(FoodInit.teaLeaves, 1, 1), 0.1F);
	}

	public static void loadCrops(RecipeResourcesMain res) {

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 0), new Object[] {
				new ItemStack(FoodInit.crops, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 2, 1), new Object[] {
				new ItemStack(FoodInit.crops, 1, 1)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 2, 2), new Object[] {
				new ItemStack(FoodInit.crops, 1, 2)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 2, 3), new Object[] {
				new ItemStack(FoodInit.crops, 1, 3)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 4), new Object[] {
				new ItemStack(FoodInit.crops, 1, 4)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 2, 5), new Object[] {
				new ItemStack(FoodInit.crops, 1, 5)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 6), new Object[] {
				new ItemStack(FoodInit.crops, 1, 10)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 7), new Object[] {
				new ItemStack(FoodInit.crops, 1, 9)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 9), new Object[] {
				new ItemStack(FoodInit.crops, 1, 12)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 11), new Object[] {
				new ItemStack(FoodInit.crops, 1, 13)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 4, 12), new Object[] {
				new ItemStack(FoodInit.crops, 1, 14)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 13), new Object[] {
				new ItemStack(FoodInit.crops, 1, 15)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 4, 15), new Object[] {
				new ItemStack(FoodInit.crops, 1, 19)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 16), new Object[] {
				new ItemStack(FoodInit.crops, 1, 20)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 0), new Object[] {
				new ItemStack(FoodInit.crops, 1, 6),
				new ItemStack(Blocks.SAPLING, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 1), new Object[] {
				new ItemStack(FoodInit.crops, 1, 7),
				new ItemStack(Blocks.SAPLING, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 2), new Object[] {
				new ItemStack(FoodInit.crops, 1, 8),
				new ItemStack(Blocks.SAPLING, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 3), new Object[] {
				new ItemStack(FoodInit.crops, 1, 11),
				new ItemStack(Blocks.SAPLING, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings2, 1, 0), new Object[] {
				new ItemStack(FoodInit.crops, 1, 16),
				new ItemStack(Blocks.SAPLING, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings2, 1, 1), new Object[] {
				new ItemStack(FoodInit.crops, 1, 17),
				new ItemStack(Blocks.SAPLING, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.crops, 4, 18), new Object[] {
				new ItemStack(MainInit.builds, 1, 11)
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 0), new Object[] {
				new ItemStack(Items.WHEAT_SEEDS, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 1), new Object[] {
				new ItemStack(Items.POTATO, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 2), new Object[] {
				new ItemStack(Items.CARROT, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 3), new Object[] {
				new ItemStack(Items.BEETROOT, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 4), new Object[] {
				new ItemStack(Items.DYE, 1, 3),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 5), new Object[] {
				new ItemStack(Items.REEDS, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 6), new Object[] {
				new ItemStack(Blocks.WATERLILY, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 8), new Object[] {
				new ItemStack(Items.MELON_SEEDS, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.saplings, 1, 0), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 1),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.saplings, 1, 1), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 2),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.saplings, 1, 2), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.saplings, 1, 3), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 4),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 7), new Object[] {
				"blockTallgrass",
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 9), new Object[] {
				new ItemStack(Items.PUMPKIN_SEEDS, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 10), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 1),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 11), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 4),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 3, new ItemStack(FoodInit.seeds, 1, 11), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 5),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 4, new ItemStack(FoodInit.seeds, 1, 11), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 6),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 5, new ItemStack(FoodInit.seeds, 1, 11), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 7),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 12), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 13), new Object[] {
				new ItemStack(Blocks.YELLOW_FLOWER, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.seeds, 1, 15), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 3),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.saplings2, 1, 0), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 5),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.saplings2, 1, 1), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 3),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cropWisteria, 1, 0), new Object[] {
				new ItemStack(Blocks.VINE, 1, 0),
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.cropGrape, 1, 0), new Object[] {
				new ItemStack(FoodInit.cropWisteria, 1, 0),
				"gemChalcedony"
		});

		LoadingContRecipe.addCompressionRecipe(MainInit.cropBasket, "food");
		LoadingContRecipe.addCompressionRecipe(MainInit.cropJute, "food");

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.paperPack, 4, 1), new Object[] {
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(Items.WATER_BUCKET)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.paperPack, 4, 2), new Object[] {
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(Items.MILK_BUCKET)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.oreItem, 1, 5), new Object[] {
				"oreGold",
				"petalLotus",
				"dyeRed"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.oreItem, 1, 6), new Object[] {
				"oreGold",
				"petalLotus",
				"dyeGreen"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.oreItem, 1, 7), new Object[] {
				"oreGold",
				"petalLotus",
				"dyeBlue"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.oreItem, 1, 8), new Object[] {
				"oreGold",
				"petalLotus",
				"dyeWhite"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.oreItem, 1, 9), new Object[] {
				"oreGold",
				"petalLotus",
				"dyeBlack"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.oreItem, 1, 10), new Object[] {
				"oreGold",
				"petalBlackLotus",
				"dyeRed"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.oreItem, 1, 11), new Object[] {
				"oreGold",
				"petalBlackLotus",
				"dyeGreen"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.oreItem, 1, 12), new Object[] {
				"oreGold",
				"petalBlackLotus",
				"dyeBlue"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.oreItem, 1, 13), new Object[] {
				"oreGold",
				"petalBlackLotus",
				"dyeWhite"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.oreItem, 1, 14), new Object[] {
				"oreGold",
				"petalBlackLotus",
				"dyeBlack"
		});

	}

	public static void loadAdvanced(RecipeResourcesMain res) {

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.incubator, 1, 0), new Object[] {
				"XXX",
				"YZW",
				"XXX",
				'X',
				"ingotSUS",
				'Y',
				new ItemStack(DCInit.climate_checker, 1, 0),
				'Z',
				"blockGlass",
				'W',
				"gearSteel"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.liquorBottle, 1, 0), new Object[] {
				" Y ",
				"X X",
				"XXX",
				'X',
				"blockGlass",
				'Y',
				"ingotAluminum"
		});

		DCRecipe.jsonShapedRecipe("food", 2, new ItemStack(FoodInit.liquorBottle, 1, 0), new Object[] {
				" Y ",
				"X X",
				"XXX",
				'X',
				"blockGlass",
				'Y',
				"ingotTin"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.roseWaterBottle, 1, 2), new Object[] {
				new ItemStack(FoodInit.roseWaterBottle, 1, 1),
				new ItemStack(FoodInit.paperPack, 1, 6)
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.brewingTankWood, 1, 0), new Object[] {
				"YYY",
				"X X",
				"XXX",
				'X',
				"logWood",
				'Y',
				"itemCloth"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.brewingTank, 1, 0), new Object[] {
				"YYY",
				"YZY",
				"XYX",
				'X',
				"ingotSteel",
				'Y',
				"ingotSUS",
				'Z',
				"bucketEmpty"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.distillator, 1, 0), new Object[] {
				"YYY",
				"Y Y",
				"XXX",
				'X',
				"ingotSUS",
				'Y',
				"ingotCopper"
		});

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.barrel, 1, 0), new Object[] {
				"YXY",
				"Y Y",
				"YXY",
				'X',
				"ingotSteel",
				'Y',
				"logWood"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.medium, 1, 4), new Object[] {
				new ItemStack(FoodInit.medium, 1, 1),
				new ItemStack(FoodInit.coliformes, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.broth, 6, 0), new Object[] {
				"dustPeptone",
				"foodViscera",
				"dustSalt"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.broth, 3, 0), new Object[] {
				"dustPeptone",
				"listAllmeatraw",
				"dustSalt"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.broth, 3, 1), new Object[] {
				"dustPeptone",
				"dustYeast",
				"dustSugar"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.baseFertilizer, 1, 0), new Object[] {
				"dirt",
				"dustPresscake",
				new ItemStack(FoodInit.bacillus, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(FoodInit.baseFertilizer, 1, 0), new Object[] {
				"dirt",
				"dustDraff",
				new ItemStack(FoodInit.bacillus, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MachineInit.reagent, 1, 5), new Object[] {
				"cropCotton",
				new ItemStack(FoodInit.slimeMold, 1, 1)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MachineInit.reagent, 1, 6), new Object[] {
				"dropGlycerin",
				new ItemStack(FoodInit.slimeMold, 1, 1)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0), new Object[] {
				new ItemStack(FoodInit.mushroom, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(Blocks.RED_MUSHROOM, 1, 0), new Object[] {
				new ItemStack(FoodInit.mushroom, 1, 1)
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(Items.EXPERIENCE_BOTTLE, 1, 0), new Object[] {
				new ItemStack(Items.GLASS_BOTTLE, 1, 0),
				"dustMana"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(Items.NETHER_WART, 1, 0), new Object[] {
				"listAllmushroom",
				"dustMana"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(Items.GHAST_TEAR, 1, 0), new Object[] {
				"gemOpal",
				"dustMana"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(Items.GHAST_TEAR, 1, 0), new Object[] {
				"gemAlexandrite",
				"dustMana"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(Items.ENDER_PEARL, 1, 0), new Object[] {
				new ItemStack(Items.EMERALD, 1, 0),
				"dustMana"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(Items.PRISMARINE_SHARD, 1, 0), new Object[] {
				"gemSapphire",
				"dustMana"
		});

		DCRecipe.jsonShapelessRecipe("food", 2, new ItemStack(Items.PRISMARINE_SHARD, 1, 0), new Object[] {
				"gemLarimar",
				"dustMana"
		});

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(Items.PRISMARINE_CRYSTALS, 1, 0), new Object[] {
				"gemAquamarine",
				"dustMana"
		});
	}

}
