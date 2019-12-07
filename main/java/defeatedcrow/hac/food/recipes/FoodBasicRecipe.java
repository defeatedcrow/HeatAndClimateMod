package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
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
			"itemCloth" });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.steelPot, 1, 0), new Object[] {
			"XYX",
			"X X",
			"XXX",
			'X',
			"ingotSteel",
			'Y',
			new ItemStack(Blocks.GLASS, 1, 0) });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.teaPot, 1, 0), new Object[] {
			" X ",
			"XYX",
			" XX",
			'X',
			"ingotSilver",
			'Y',
			"itemCloth" });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.teaPot, 1, 0), new Object[] {
			" X ",
			"XYX",
			" XX",
			'X',
			"ingotNickelsilver",
			'Y',
			"itemCloth" });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.cupSilver, 1, 0), new Object[] {
			"XXX",
			"XX ",
			'X',
			"ingotNickelsilver" });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.cupSilver, 1, 1), new Object[] {
			"XXX",
			"XX ",
			'X',
			new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0) });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.cupSilver, 1, 2), new Object[] {
			"XXX",
			"XX ",
			'X',
			new ItemStack(Blocks.GLASS, 1, 0) });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.paperPack, 4, 0), new Object[] {
			" X ",
			"X X",
			" X ",
			'X',
			"paper" });

		// 中身リセット
		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.potteryPot, 1, 0), new Object[] {
			new ItemStack(FoodInit.potteryPot, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.steelPot, 1, 0), new Object[] {
			new ItemStack(FoodInit.steelPot, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.teaPot, 1, 0), new Object[] {
			new ItemStack(FoodInit.teaPot, 1, 0) });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.dish, 1, 0), new Object[] {
			"XXX",
			" X ",
			'X',
			"gemChalcedony" });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.dish, 1, 1), new Object[] {
			"XXX",
			" X ",
			'X',
			"ingotSilver" });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.dish, 1, 1), new Object[] {
			"XXX",
			" X ",
			'X',
			"ingotNickelSilver" });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.steakplate, 3, 0), new Object[] {
			"X X",
			" Y ",
			'X',
			"ingotIron",
			'Y',
			new ItemStack(MainInit.plate, 1, 0) });

		DCRecipe.jsonShapedRecipe("food", new ItemStack(FoodInit.silkwormBox, 1, 0), new Object[] {
			"XXX",
			"XZX",
			"YYY",
			'X',
			"stickWood",
			'Y',
			"plankWood",
			'Z',
			"string" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.silkworm, 8, 0), new Object[] {
			new ItemStack(MainInit.silkworm, 1, 3),
			new ItemStack(MainInit.silkworm, 1, 3),
			"paper" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(MainInit.silkworm, 1, 3), new Object[] {
			new ItemStack(MainInit.silkworm, 1, 2) });

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

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.meat, 1, 1), new Object[] { "foodViscera" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastry, 4, 0), new Object[] {
			"dustFlour",
			"foodButter",
			"egg",
			"dustSalt" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastry, 2, 0), new Object[] {
			"foodFlour",
			"foodOil",
			"egg",
			"dustSalt" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastry, 1, 1), new Object[] {
			"foodDough",
			"foodDough" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dairy, 1, 2), new Object[] {
			"foodCream",
			"egg",
			"dustFlour" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dairy, 1, 2), new Object[] {
			"foodCream",
			"egg",
			"dustSugar" });

		// foods

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 2, 0), new Object[] {
			"foodFlour",
			"dustSalt",
			"bucketWater" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 2), new Object[] {
			"foodDough",
			"paper" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 3, 4), new Object[] {
			new ItemStack(FoodInit.bread, 1, 3),
			"foodButter" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 6), new Object[] {
			"foodDough",
			"cropTomato",
			"foodCheese",
			"cropHerb" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 8), new Object[] {
			"bread",
			"foodCustard" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 10), new Object[] {
			"bread",
			"foodButter",
			"cropGarlic" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 2, 12), new Object[] {
			"foodFlour",
			"dustSalt",
			"dustSugar",
			"bucketWater" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 2, 14), new Object[] {
			"foodFlour",
			"dustSugar",
			"bucketMilk",
			"egg",
			"foodBakingSoda" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 16), new Object[] {
			"foodDough",
			"cropWalnut" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 2, 18), new Object[] {
			"foodFlour",
			"dustSugar",
			"foodButter",
			"egg",
			"cropGinger" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 2, 18), new Object[] {
			"foodFlour",
			"dropMolasses",
			"foodButter",
			"egg",
			"cropGinger" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 3, 20), new Object[] {
			"cropCorn",
			"dustLime",
			"foodOil",
			"bucketWater" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 3, 20), new Object[] {
			"foodFlour",
			"foodOil",
			"dustSalt",
			"bucketWater" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.bread, 1, 22), new Object[] {
			"foodDough",
			"foodRaisins" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 2), new Object[] {
			"stickWood",
			"listAllchickenraw" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 4), new Object[] {
			"stickWood",
			"listAllporkraw" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 6), new Object[] {
			"stickWood",
			"listAllbeefraw" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 8), new Object[] {
			"stickWood",
			"listAllmuttonraw" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 10), new Object[] {
			"stickWood",
			"foodCalamariraw" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 0), new Object[] {
			"stickWood",
			"listAllfishraw" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 12), new Object[] {
			"stickWood",
			"foodBowlofrice",
			"foodMiso" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 14), new Object[] {
			"stickWood",
			new ItemStack(FoodInit.nonEntity, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 14), new Object[] {
			"stickWood",
			"foodMarshmellows" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 16), new Object[] {
			"stickWood",
			"foodViscera",
			"cropGinger",
			"cropGarlic" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sticks, 1, 16), new Object[] {
			"stickWood",
			"foodViscera",
			"cropGinger",
			"cropLemon" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.mochi, 1, 0), new Object[] { "foodBowlofrice" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 0), new Object[] {
			"foodPastry",
			"cropApple",
			"dustSugar",
			"foodButter" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 0), new Object[] {
			"foodPastry",
			"cropApple",
			"dropHoney",
			"foodButter" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 2), new Object[] {
			"foodPastry",
			"cropLemon",
			"dustSugar",
			"foodCream" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 2), new Object[] {
			"foodPastry",
			"cropLemon",
			"dropHoney",
			"foodCream" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 4), new Object[] {
			"foodPastry",
			"cropSpinach",
			"listAllmeatraw",
			"foodCustard",
			"foodCheese" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 6), new Object[] {
			"foodPastry",
			"cropPotato",
			"cropOnion",
			"listAllfishraw",
			"foodCustard",
			"foodCheese" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
			"foodPastry",
			"dustSugar",
			"foodButter",
			"cropMulberry" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
			"foodPastry",
			"dustSugar",
			"foodButter",
			"cropCherry" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
			"foodPastry",
			"dustSugar",
			"foodButter",
			"cropGrape" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastryRound, 1, 8), new Object[] {
			"foodPastry",
			"dustSugar",
			"foodButter",
			"listAllberry" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 0), new Object[] {
			"foodPastry",
			"dustSugar" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 2), new Object[] {
			"foodPastry",
			"listAllmeatraw",
			"dustSalt",
			"cropOnion" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 2), new Object[] {
			"foodPastry",
			"listAllmeatraw",
			"dustSalt",
			"cropHerb" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 4), new Object[] {
			"foodPastry",
			"dustSugar",
			"cropCocoa" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 4), new Object[] {
			"foodPastry",
			"dustSugar",
			new ItemStack(Items.DYE, 1, 3) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
			"foodPastry",
			"dustSugar",
			"cropApple" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
			"foodPastry",
			"dustSugar",
			"cropCherry" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
			"foodPastry",
			"dustSugar",
			"cropPeach" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
			"foodPastry",
			"dustSugar",
			"cropOrange" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
			"foodPastry",
			"dustSugar",
			"cropPair" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 6), new Object[] {
			"foodPastry",
			"dustSugar",
			"cropMulberry" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 8), new Object[] {
			"foodPastry",
			"dustSugar",
			"cropLotusSeed",
			"egg" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 8), new Object[] {
			"foodPastry",
			"dustSugar",
			"cropLotusSeed" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.pastrySquare, 1, 10), new Object[] {
			"foodPastry",
			"foodCustard" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 0), new Object[] {
			new ItemStack(FoodInit.bread, 1, 3),
			"cropApple",
			"dustSugar" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 1), new Object[] {
			new ItemStack(FoodInit.bread, 1, 3),
			"egg" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 2), new Object[] {
			new ItemStack(FoodInit.bread, 1, 3),
			"cropLemon",
			"dustSugar" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 2), new Object[] {
			new ItemStack(FoodInit.bread, 1, 3),
			"cropLemon",
			"dropHoney" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 3), new Object[] {
			new ItemStack(FoodInit.bread, 1, 3),
			"cropSpinach",
			"cropOnion",
			new ItemStack(Items.COOKED_PORKCHOP, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 3), new Object[] {
			new ItemStack(FoodInit.bread, 1, 3),
			"cropHerb",
			"cropTomato",
			new ItemStack(Items.COOKED_CHICKEN, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.clubsandwich, 2, 0), new Object[] {
			new ItemStack(FoodInit.bread, 1, 3),
			"listAllgreenveggie",
			"cropTomato",
			new ItemStack(Items.COOKED_BEEF, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.clubsandwich, 2, 1), new Object[] {
			new ItemStack(FoodInit.bread, 1, 1),
			"listAllgreenveggie",
			"cropTomato",
			new ItemStack(Items.COOKED_FISH, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 0), new Object[] {
			"listAllgreenveggie",
			"cropTomato",
			"listAllveggie" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 1), new Object[] {
			"egg",
			"listAllgreenveggie",
			new ItemStack(Items.BAKED_POTATO, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 2, 2), new Object[] {
			"cropLotusRoot",
			"cropCarrot",
			"foodSoysauce",
			"cropChilipepper" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 2, 3), new Object[] {
			"cropSoybean",
			"cropSeaweed" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 2, 4), new Object[] {
			"listAllchickenraw",
			"cropSoybean",
			new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0),
			"cropCarrot",
			"listAllrootveggie" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 2, 5), new Object[] {
			"cropSpinach",
			"foodMiso" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 2, 6), new Object[] {
			"cropBean",
			"cropTomato" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 2, 8), new Object[] {
			"cropPumpkin",
			"foodSoysauce",
			"dustSugar" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 9), new Object[] {
			"listAllgreenveggie",
			"foodSmokedsalmon",
			"cropOnion",
			"cropOlive" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 10), new Object[] {
			"listAllgreenveggie",
			"foodFirmtofu",
			"cropSeaweed" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 11), new Object[] {
			"listAllgreenveggie",
			"listAllnut",
			"cropBean" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 1, 11), new Object[] {
			"listAllgreenveggie",
			"listAllnut",
			"cropLotusSeed" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.ricebowl, 1, 2), new Object[] {
			"foodBowlofrice",
			"dustSalt" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.ricebowl, 1, 3), new Object[] {
			"foodBowlofrice",
			"dustSalt",
			"cropSeaweed" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.ricebowl, 1, 4), new Object[] {
			"foodBowlofrice",
			"foodMiso",
			"cropHerb" });

		String[] meats = { "listAllbeefraw", "listAllporkraw", "listAllchickenraw", "listAllfishraw" };
		for (int i = 0; i < meats.length; i++) {
			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateMeal, 1, i * 2), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"cropTomato",
				"listAllveggie",
				meats[i] });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateMeal, 1, i * 2), new Object[] {
				new ItemStack(FoodInit.steakplate, 1, 0),
				"cropPotato",
				"listAllveggie",
				meats[i] });
		}

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateMeal, 1, 8), new Object[] {
			new ItemStack(FoodInit.steakplate, 1, 0),
			"cropGarlic",
			"foodButter",
			"listAllbeefraw" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateMeal, 1, 8), new Object[] {
			new ItemStack(FoodInit.steakplate, 1, 0),
			"cropGarlic",
			"foodButter",
			"listAllvenisonraw" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 0), new Object[] {
			new ItemStack(FoodInit.steakplate, 1, 0),
			"cropPotato",
			"foodButter",
			"dustSalt" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 2), new Object[] {
			new ItemStack(FoodInit.steakplate, 1, 0),
			"listAllveggie",
			"cropTomato",
			"foodCheese" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 4), new Object[] {
			new ItemStack(FoodInit.steakplate, 1, 0),
			"cropPotato",
			"foodFlour",
			"listAllmilk",
			"foodCheese" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 4), new Object[] {
			new ItemStack(FoodInit.steakplate, 1, 0),
			"foodShrimpraw",
			"foodFlour",
			"listAllmilk",
			"foodCheese" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 6), new Object[] {
			new ItemStack(FoodInit.steakplate, 1, 0),
			"foodShrimpraw",
			"foodCalamariraw",
			"foodRice",
			"cropTomato",
			"foodStock" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 6), new Object[] {
			new ItemStack(FoodInit.steakplate, 1, 0),
			"foodShrimpraw",
			"listAllfishraw",
			"foodRice",
			"cropTomato",
			"foodStock" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 8), new Object[] {
			new ItemStack(FoodInit.steakplate, 1, 0),
			"listAllchickenraw",
			"foodRice",
			"cropTomato",
			"cropBean",
			"foodStock" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.plateSoup, 1, 8), new Object[] {
			new ItemStack(FoodInit.steakplate, 1, 0),
			"listAllrabbitraw",
			"foodRice",
			"cropTomato",
			"cropBean",
			"foodStock" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.snack, 2, 1), new Object[] {
			new ItemStack(FoodInit.snack, 1, 0),
			new ItemStack(FoodInit.deepFry, 1, 2), });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.snack, 3, 4), new Object[] {
			"foodPita",
			"listAllchickencooked",
			"listAllgreenveggie" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.snack, 3, 4), new Object[] {
			"foodPita",
			"listAllbeefcooked",
			"listAllgreenveggie" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.snack, 3, 4), new Object[] {
			"foodPita",
			"listAllmuttoncooked",
			"listAllgreenveggie" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.snack, 3, 5), new Object[] {
			"foodPita",
			new ItemStack(FoodInit.deepFry, 1, 3),
			"listAllgreenveggie",
			"cropTomato" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.setMeal, 1, 0), new Object[] {
			"bread",
			"foodSausage",
			"egg",
			"listAllmushroom",
			"cropTomato",
			new ItemStack(FoodInit.salad, 1, 6), });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.setMeal, 1, 1), new Object[] {
			"foodBowlofrice",
			new ItemStack(FoodInit.bowlSoup, 1, 11),
			new ItemStack(FoodInit.salad, 1, 7),
			"listAllveggie" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.nonEntity, 1, 1), new Object[] {
			"listAllnut",
			"cropDate" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.nonEntity, 2, 1), new Object[] {
			"listAllnut",
			"cropDate",
			"foodCheese" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 2, 0), new Object[] {
			"cropTomato",
			"listAllherb",
			"foodCheese" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 2, 1), new Object[] {
			"foodGarlicbread",
			"cropTomato",
			"foodSmokedsalmon" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 2, 1), new Object[] {
			"bread",
			"cropGarlic",
			"cropTomato",
			"foodSmokedsalmon" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 2, 2), new Object[] {
			"foodSmokedsalmon",
			"foodCheese" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 1, 3), new Object[] {
			"foodBowlofrice",
			"listAllfishraw",
			"listAllfishraw",
			"egg" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 1, 4), new Object[] {
			"listAllfishraw",
			"listAllfishraw",
			"listAllherb" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 1, 6), new Object[] { "foodStock", "egg" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishSq, 1, 7), new Object[] {
			"foodFirmtofu",
			"foodSoysauce",
			"listAllherb" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishBig, 3, 0), new Object[] {
			"foodFirmtofu",
			"cropGinger",
			"cropGarlic",
			"cropChilipepper",
			"listAllbeefraw" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishBig, 2, 1), new Object[] {
			"foodBowlofrice",
			"egg",
			"cropTomato",
			"foodButter" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishBig, 2, 2), new Object[] {
			"foodTortilla",
			"cropLettuce",
			"cropTomato",
			"cropChilipepper",
			"listAllmeatcooked" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishBig, 2, 3), new Object[] {
			"foodBowlofrice",
			"cropLettuce",
			"cropTomato",
			"cropChilipepper",
			"listAllmeatcooked" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishBig, 2, 4), new Object[] {
			"foodTortilla",
			"foodCheese",
			"cropTomato",
			"cropChilipepper" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dishBig, 2, 4), new Object[] {
			"foodTortilla",
			"foodCheese",
			"foodChili" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 2, 0), new Object[] {
			"bucketWater",
			"cropGinger",
			"dustSugar" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 2, 0), new Object[] {
			"bucketWater",
			"cropGinger",
			"dropHoney" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 2, 1), new Object[] {
			"bucketWater",
			"dustStarch",
			"cropGinger" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 2, 1), new Object[] {
			"bucketWater",
			"dustStarch",
			"cropLemon" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 2, 2), new Object[] {
			"bread",
			"dustSalt",
			"cropTomato",
			"cropGarlic" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.drink, 2, 2), new Object[] {
			"bread",
			"dustSalt",
			"cropTomato",
			"cropCucumber" });

		// cake

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 1, 0), new Object[] {
			"foodButter",
			"foodFlour",
			"dustSugar",
			"egg" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 1, 2), new Object[] {
			new ItemStack(FoodInit.cake, 1, 1),
			"cropCocoa",
			"foodCream" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 1, 8), new Object[] { "egg", "cropSpinach" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 3, 10), new Object[] {
			"foodFlour",
			"dropMolasses",
			"cropDate",
			"egg",
			"foodButter" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 1, 11), new Object[] {
			"foodPancakes",
			"foodPancakes",
			"foodPancakes",
			"foodPancakes",
			"foodButter",
			"listAllheavycream",
			"listAllberry" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cake, 1, 11), new Object[] {
			"foodPancakes",
			"foodPancakes",
			"foodPancakes",
			"foodPancakes",
			"foodButter",
			"dropHoney",
			"listAllberry" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.icecream, 1, 6), new Object[] {
			new ItemStack(FoodInit.icecream, 1, 2),
			"listAllheavycream",
			"cropGrape",
			"listAllberry" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.icecream, 1, 7), new Object[] {
			new ItemStack(FoodInit.icecream, 1, 3),
			"foodCheese",
			"cropLemon",
			"listAllnut" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.icecream, 1, 7), new Object[] {
			new ItemStack(FoodInit.icecream, 1, 3),
			"foodCheese",
			"cropOrange",
			"listAllnut" });

		// soymeats

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.sandwich, 2, 3), new Object[] {
			new ItemStack(FoodInit.bread, 1, 3),
			"listAllgreenveggie",
			"cropOnion",
			new ItemStack(FoodInit.meat, 1, 5) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.clubsandwich, 1, 1), new Object[] {
			new ItemStack(FoodInit.bread, 1, 1),
			"listAllgreenveggie",
			"cropTomato",
			new ItemStack(FoodInit.meat, 1, 5) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.salad, 2, 4), new Object[] {
			new ItemStack(FoodInit.meat, 1, 5),
			"cropSoybean",
			new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0),
			"cropCarrot",
			"listAllrootveggie" });

		// sweets

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.icecream, 1, 1), new Object[] {
			new ItemStack(FoodInit.icecream, 1, 0),
			"cropSoybean",
			"dropMolasses" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 0), new Object[] {
			"foodRicecake",
			"cropSoybean" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 1), new Object[] {
			"foodRicecake",
			"foodSoysauce",
			"dustSugar",
			"cropSeaweed" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 2), new Object[] {
			"foodRicecake",
			new ItemStack(MainInit.bakedApple, 1, 6),
			"dustSugar" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 3), new Object[] {
			"foodRicecake",
			"foodButter",
			"dustSugar",
			"egg" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 4), new Object[] {
			"foodRice",
			"foodSiroanko",
			"dustSugar",
			"listAllberry" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 5), new Object[] {
			"foodRice",
			"foodSoysauce",
			"dustSugar",
			"cropWalnut" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 6), new Object[] {
			"foodDough",
			"foodSiroanko",
			"egg" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 2, 6), new Object[] {
			"foodDough",
			"foodSiroanko",
			"egg",
			"cropChestnut" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 7), new Object[] {
			"foodRice",
			"foodSiroanko",
			"dustSugar",
			"dyeRed" });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.wagashi, 1, 8), new Object[] {
			"foodRicecake",
			"cropSoybean",
			"dustSugar" });

		// yagen

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.dropOil, 1, 0), new Object[] {
			"toolNormalYagen",
			"cropOlive",
			"cropOlive" });

		// smelting
		GameRegistry.addSmelting(new ItemStack(FoodInit.seeds, 1, 4), new ItemStack(FoodInit.teaLeaves, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(FoodInit.crops, 1, 8), new ItemStack(FoodInit.teaLeaves, 1, 1), 0.1F);
	}

	public static void loadCrops(RecipeResourcesMain res) {

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 0), new Object[] {
			new ItemStack(FoodInit.crops, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 2, 1), new Object[] {
			new ItemStack(FoodInit.crops, 1, 1) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 2, 2), new Object[] {
			new ItemStack(FoodInit.crops, 1, 2) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 2, 3), new Object[] {
			new ItemStack(FoodInit.crops, 1, 3) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 4), new Object[] {
			new ItemStack(FoodInit.crops, 1, 4) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 2, 5), new Object[] {
			new ItemStack(FoodInit.crops, 1, 5) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 7), new Object[] {
			new ItemStack(FoodInit.crops, 1, 9) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 9), new Object[] {
			new ItemStack(FoodInit.crops, 1, 12) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 11), new Object[] {
			new ItemStack(FoodInit.crops, 1, 13) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 4, 12), new Object[] {
			new ItemStack(FoodInit.crops, 1, 14) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 13), new Object[] {
			new ItemStack(FoodInit.crops, 1, 15) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 4, 15), new Object[] {
			new ItemStack(FoodInit.crops, 1, 19) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 16), new Object[] {
			new ItemStack(FoodInit.crops, 1, 20) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 0), new Object[] {
			new ItemStack(FoodInit.crops, 1, 6),
			new ItemStack(Blocks.SAPLING, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 1), new Object[] {
			new ItemStack(FoodInit.crops, 1, 7),
			new ItemStack(Blocks.SAPLING, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 2), new Object[] {
			new ItemStack(FoodInit.crops, 1, 8),
			new ItemStack(Blocks.SAPLING, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 3), new Object[] {
			new ItemStack(FoodInit.crops, 1, 11),
			new ItemStack(Blocks.SAPLING, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings2, 1, 0), new Object[] {
			new ItemStack(FoodInit.crops, 1, 16),
			new ItemStack(Blocks.SAPLING, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings2, 1, 1), new Object[] {
			new ItemStack(FoodInit.crops, 1, 17),
			new ItemStack(Blocks.SAPLING, 1, 0) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.crops, 4, 18), new Object[] {
			new ItemStack(MainInit.builds, 1, 11) });

		// seeds another recipes
		if (ModuleConfig.agri) {
			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 0), new Object[] {
				new ItemStack(Items.WHEAT_SEEDS, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 1), new Object[] {
				new ItemStack(Items.POTATO, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 2), new Object[] {
				new ItemStack(Items.CARROT, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 3), new Object[] {
				new ItemStack(Items.BEETROOT, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 4), new Object[] {
				new ItemStack(Items.DYE, 1, 3),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 5), new Object[] {
				new ItemStack(Items.REEDS, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 6), new Object[] {
				new ItemStack(Blocks.WATERLILY, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 8), new Object[] {
				new ItemStack(Items.MELON_SEEDS, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 0), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 1),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 1), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 2),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 2), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings, 1, 3), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 4),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 7), new Object[] {
				"blockTallgrass",
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 9), new Object[] {
				new ItemStack(Items.PUMPKIN_SEEDS, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 10), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 1),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 11), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 4),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 11), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 5),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 11), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 6),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 11), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 7),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 12), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 2),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 13), new Object[] {
				new ItemStack(Blocks.YELLOW_FLOWER, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.seeds, 1, 15), new Object[] {
				new ItemStack(Blocks.RED_FLOWER, 1, 3),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings2, 1, 0), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 5),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.saplings2, 1, 1), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 3),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cropWisteria, 1, 0), new Object[] {
				new ItemStack(Blocks.VINE, 1, 0),
				"gemChalcedony" });

			DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.cropGrape, 1, 0), new Object[] {
				new ItemStack(FoodInit.cropWisteria, 1, 0),
				"gemChalcedony" });

		}

		LoadingContRecipe.addCompressionRecipe(MainInit.cropBasket, "food");
		LoadingContRecipe.addCompressionRecipe(MainInit.cropJute, "food");

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.paperPack, 4, 1), new Object[] {
			new ItemStack(FoodInit.paperPack, 1, 0),
			new ItemStack(FoodInit.paperPack, 1, 0),
			new ItemStack(FoodInit.paperPack, 1, 0),
			new ItemStack(FoodInit.paperPack, 1, 0),
			new ItemStack(Items.WATER_BUCKET) });

		DCRecipe.jsonShapelessRecipe("food", new ItemStack(FoodInit.paperPack, 4, 2), new Object[] {
			new ItemStack(FoodInit.paperPack, 1, 0),
			new ItemStack(FoodInit.paperPack, 1, 0),
			new ItemStack(FoodInit.paperPack, 1, 0),
			new ItemStack(FoodInit.paperPack, 1, 0),
			new ItemStack(Items.MILK_BUCKET) });

	}

}
