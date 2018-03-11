package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.api.cultivate.CropAPI;
import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.recipe.ConvertTargetList;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class FoodRecipes {

	private static final RecipeResourcesMain res = new RecipeResourcesMain("food");

	public static void load() {
		FoodBasicRecipe.load(res);
		FoodBasicRecipe.loadCrops(res);
		FoodClimateRecipe.load();
		if (ModuleConfig.r_mill) {
			loadMillRecipe();
		}
		if (ModuleConfig.r_fluid) {
			FoodFluidRecipe.load();
		}
		loadCropData();
		loadFuelData();
	}

	static void loadMillRecipe() {
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), "seedRice");

		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1, 4),
				0.25F, new ItemStack(FoodInit.seeds, 2, 5));

		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1, 4),
				0.25F, new ItemStack(FoodInit.crops, 1, 7));

		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 8, 0), new ItemStack(MainInit.miscDust, 1, 4),
				0.25F, new ItemStack(MainInit.cropBasket, 1, 7));

		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1, 4),
				0.25F, new ItemStack(Items.WHEAT_SEEDS, 8, 0));

	}

	static void loadCropData() {
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropRice);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropOnion);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropSpinach);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropTomato);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropCoffee);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropCotton);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.leavesLemon);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.leavesOlive);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.leavesTea);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropLotus);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropHerb);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropSeaweed);
	}

	public static void loadOres() {
		OreDictionary.registerOre("cropRice", new ItemStack(FoodInit.crops, 1, 0));
		OreDictionary.registerOre("cropOnion", new ItemStack(FoodInit.crops, 1, 1));
		OreDictionary.registerOre("cropSpinach", new ItemStack(FoodInit.crops, 1, 2));
		OreDictionary.registerOre("cropTomato", new ItemStack(FoodInit.crops, 1, 3));
		OreDictionary.registerOre("cropCoffee", new ItemStack(FoodInit.crops, 1, 4));
		OreDictionary.registerOre("cropCotton", new ItemStack(FoodInit.crops, 1, 5));
		OreDictionary.registerOre("cropLemon", new ItemStack(FoodInit.crops, 1, 6));
		OreDictionary.registerOre("cropOlive", new ItemStack(FoodInit.crops, 1, 7));
		OreDictionary.registerOre("listAllveggie", new ItemStack(FoodInit.crops, 1, 1));
		OreDictionary.registerOre("listAllveggie", new ItemStack(FoodInit.crops, 1, 2));
		OreDictionary.registerOre("listAllveggie", new ItemStack(FoodInit.crops, 1, 3));
		OreDictionary.registerOre("listAllveggie", new ItemStack(FoodInit.seeds, 1, 6));
		OreDictionary.registerOre("listAllrootveggie", new ItemStack(FoodInit.seeds, 1, 6));
		OreDictionary.registerOre("cropTea", new ItemStack(FoodInit.crops, 1, 8));
		OreDictionary.registerOre("cropHerb", new ItemStack(FoodInit.crops, 1, 9));
		OreDictionary.registerOre("cropLotusSeed", new ItemStack(FoodInit.crops, 1, 10));
		OreDictionary.registerOre("cropLotusRoot", new ItemStack(FoodInit.seeds, 1, 6));
		OreDictionary.registerOre("seedRice", new ItemStack(FoodInit.seeds, 1, 0));
		OreDictionary.registerOre("seedOnion", new ItemStack(FoodInit.seeds, 1, 1));
		OreDictionary.registerOre("seedSpinach", new ItemStack(FoodInit.seeds, 1, 2));
		OreDictionary.registerOre("seedTomato", new ItemStack(FoodInit.seeds, 1, 3));
		OreDictionary.registerOre("seedCoffee", new ItemStack(FoodInit.seeds, 1, 4));
		OreDictionary.registerOre("seedCotton", new ItemStack(FoodInit.seeds, 1, 5));
		OreDictionary.registerOre("seedLotus", new ItemStack(FoodInit.seeds, 1, 6));
		OreDictionary.registerOre("seedHerb", new ItemStack(FoodInit.seeds, 1, 7));
		OreDictionary.registerOre("cropSeaweed", new ItemStack(FoodInit.seeds, 1, 8));
		OreDictionary.registerOre("saplingLemon", new ItemStack(FoodInit.saplings, 1, 0));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings, 1, 0));
		OreDictionary.registerOre("saplingOlive", new ItemStack(FoodInit.saplings, 1, 1));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings, 1, 1));
		OreDictionary.registerOre("saplingTea", new ItemStack(FoodInit.saplings, 1, 2));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings, 1, 2));

		OreDictionary.registerOre("bucketWater", new ItemStack(FoodInit.paperPack, 1, 1));
		OreDictionary.registerOre("bucketMilk", new ItemStack(FoodInit.paperPack, 1, 2));

		OreDictionary.registerOre("foodCream", new ItemStack(FoodInit.paperPack, 1, 3));
		OreDictionary.registerOre("foodCream", new ItemStack(FoodInit.dropCream, 1, 0));
		OreDictionary.registerOre("foodOil", new ItemStack(FoodInit.paperPack, 1, 4));
		OreDictionary.registerOre("foodOil", new ItemStack(FoodInit.dropOil, 1, 0));
		OreDictionary.registerOre("foodButter", new ItemStack(FoodInit.dairy, 1, 0));
		OreDictionary.registerOre("foodCheese", new ItemStack(FoodInit.dairy, 1, 1));
		OreDictionary.registerOre("foodCustard", new ItemStack(FoodInit.dairy, 1, 2));
		OreDictionary.registerOre("foodViscera", new ItemStack(FoodInit.meat, 1, 0));
		OreDictionary.registerOre("foodRennet", new ItemStack(FoodInit.meat, 1, 1));
		OreDictionary.registerOre("foodSquid", new ItemStack(FoodInit.meat, 1, 2));
		OreDictionary.registerOre("fishSquid", new ItemStack(FoodInit.meat, 1, 2));
		OreDictionary.registerOre("foodAgar", new ItemStack(FoodInit.meat, 1, 3));
		OreDictionary.registerOre("foodGelatine", new ItemStack(FoodInit.meat, 1, 4));
		OreDictionary.registerOre("foodPastry", new ItemStack(FoodInit.pastry, 1, 0));

		ConvertTargetList.addExclusing(new ItemStack(FoodInit.paperPack, 1, 1));
		ConvertTargetList.addExclusing(new ItemStack(FoodInit.paperPack, 1, 2));

		OreDictionary.registerOre("listAllveggie", new ItemStack(Items.CARROT, 1, 0));
		OreDictionary.registerOre("listAllveggie", new ItemStack(Items.POTATO, 1, 0));
		OreDictionary.registerOre("listAllveggie", new ItemStack(Items.BEETROOT, 1, 0));
		OreDictionary.registerOre("listAllveggie", new ItemStack(Blocks.PUMPKIN, 1, 0));
	}

	static void loadFuelData() {
		MainAPIManager.fuelRegister.registerFuel(FoodInit.oil, 60);
		MainAPIManager.fuelRegister.registerFuel(FoodInit.blackLiquor, 60);
	}
}
