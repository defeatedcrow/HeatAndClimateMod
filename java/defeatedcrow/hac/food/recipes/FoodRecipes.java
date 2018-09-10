package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.api.cultivate.CropAPI;
import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.core.recipe.ConvertTargetList;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import defeatedcrow.hac.plugin.DCPluginFluid;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
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
		loadDrinkData();

	}

	static void loadMillRecipe() {
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), "seedRice");

		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1,
				4), 0.25F, new ItemStack(FoodInit.seeds, 2, 5));

		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.foodDust, 1,
				4), 0.25F, new ItemStack(FoodInit.seeds, 2, 9));

		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1,
				4), 0.25F, "cropOlive");

		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 8, 0), new ItemStack(MainInit.miscDust, 1,
				4), 0.25F, new ItemStack(MainInit.cropBasket, 1, 7));

		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1,
				5), 0.25F, new ItemStack(Items.WHEAT_SEEDS, 8, 0));

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
		CropAPI.register.addCropData((IClimateCrop) FoodInit.leavesMorus);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropLotus);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropHerb);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropSeaweed);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropSoy);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropBean);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropChili);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropGarlic);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.cropLettuce);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.leavesWalnut);
		CropAPI.register.addCropData((IClimateCrop) FoodInit.leavesDatesCrop);
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
		OreDictionary.registerOre("cropMulberry", new ItemStack(FoodInit.crops, 1, 11));
		OreDictionary.registerOre("cropGreenSoybeans", new ItemStack(FoodInit.crops, 1, 12));
		OreDictionary.registerOre("cropChilipepper", new ItemStack(FoodInit.crops, 1, 13));
		OreDictionary.registerOre("cropGarlic", new ItemStack(FoodInit.crops, 1, 14));
		OreDictionary.registerOre("cropLettuce", new ItemStack(FoodInit.crops, 1, 15));
		OreDictionary.registerOre("cropWalnut", new ItemStack(FoodInit.crops, 1, 16));
		OreDictionary.registerOre("cropDate", new ItemStack(FoodInit.crops, 1, 17));
		OreDictionary.registerOre("listAllveggie", new ItemStack(FoodInit.crops, 1, 1));
		OreDictionary.registerOre("listAllveggie", new ItemStack(FoodInit.crops, 1, 2));
		OreDictionary.registerOre("listAllgreenveggie", new ItemStack(FoodInit.crops, 1, 2));
		OreDictionary.registerOre("listAllveggie", new ItemStack(FoodInit.crops, 1, 3));
		OreDictionary.registerOre("listAllveggie", new ItemStack(FoodInit.crops, 1, 12));
		OreDictionary.registerOre("listAllveggie", new ItemStack(FoodInit.seeds, 1, 6));
		OreDictionary.registerOre("listAllrootveggie", new ItemStack(FoodInit.seeds, 1, 6));
		OreDictionary.registerOre("listAllfruit", new ItemStack(FoodInit.crops, 1, 6));
		OreDictionary.registerOre("listAllfruit", new ItemStack(FoodInit.crops, 1, 11));
		OreDictionary.registerOre("listAllberry", new ItemStack(FoodInit.crops, 1, 11));
		OreDictionary.registerOre("listAllpepper", new ItemStack(FoodInit.crops, 1, 13));
		OreDictionary.registerOre("listAllherb", new ItemStack(FoodInit.crops, 1, 14));
		OreDictionary.registerOre("listAllgreenveggie", new ItemStack(FoodInit.crops, 1, 15));
		OreDictionary.registerOre("listAllnut", new ItemStack(FoodInit.crops, 1, 16));
		OreDictionary.registerOre("listAllfruit", new ItemStack(FoodInit.crops, 1, 17));
		OreDictionary.registerOre("cropTea", new ItemStack(FoodInit.crops, 1, 8));
		OreDictionary.registerOre("cropHerb", new ItemStack(FoodInit.crops, 1, 9));
		OreDictionary.registerOre("listAllspice", new ItemStack(FoodInit.crops, 1, 9));
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
		OreDictionary.registerOre("cropSoybean", new ItemStack(FoodInit.seeds, 1, 9));
		OreDictionary.registerOre("cropSoy", new ItemStack(FoodInit.seeds, 1, 9));
		OreDictionary.registerOre("seedSoybean", new ItemStack(FoodInit.seeds, 1, 9));
		OreDictionary.registerOre("cropBean", new ItemStack(FoodInit.seeds, 1, 10));
		OreDictionary.registerOre("seedBean", new ItemStack(FoodInit.seeds, 1, 10));
		OreDictionary.registerOre("seedChilipepper", new ItemStack(FoodInit.seeds, 1, 11));
		OreDictionary.registerOre("seedGarlic", new ItemStack(FoodInit.seeds, 1, 12));
		OreDictionary.registerOre("seedLettuce", new ItemStack(FoodInit.seeds, 1, 13));
		OreDictionary.registerOre("saplingLemon", new ItemStack(FoodInit.saplings, 1, 0));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings, 1, 0));
		OreDictionary.registerOre("saplingOlive", new ItemStack(FoodInit.saplings, 1, 1));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings, 1, 1));
		OreDictionary.registerOre("saplingTea", new ItemStack(FoodInit.saplings, 1, 2));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings, 1, 2));
		OreDictionary.registerOre("saplingMorus", new ItemStack(FoodInit.saplings, 1, 3));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings, 1, 3));
		OreDictionary.registerOre("saplingWalnut", new ItemStack(FoodInit.saplings2, 1, 0));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings2, 1, 0));
		OreDictionary.registerOre("saplingDate", new ItemStack(FoodInit.saplings2, 1, 1));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings2, 1, 1));

		OreDictionary.registerOre("bucketWater", new ItemStack(FoodInit.paperPack, 1, 1));
		OreDictionary.registerOre("bucketMilk", new ItemStack(FoodInit.paperPack, 1, 2));
		OreDictionary.registerOre("foodCream", new ItemStack(FoodInit.paperPack, 1, 3));
		OreDictionary.registerOre("listAllheavycream", new ItemStack(FoodInit.paperPack, 1, 3));
		OreDictionary.registerOre("foodCream", new ItemStack(FoodInit.dropCream, 1, 0));
		OreDictionary.registerOre("foodOil", new ItemStack(FoodInit.paperPack, 1, 4));
		OreDictionary.registerOre("foodOliveoil", new ItemStack(FoodInit.paperPack, 1, 4));
		OreDictionary.registerOre("foodOil", new ItemStack(FoodInit.dropOil, 1, 0));
		OreDictionary.registerOre("foodVegetablejuice", new ItemStack(FoodInit.paperPack, 1, 5));
		OreDictionary.registerOre("foodLemonaide", new ItemStack(FoodInit.paperPack, 1, 6));
		OreDictionary.registerOre("foodAbsince", new ItemStack(FoodInit.paperPack, 1, 7));
		OreDictionary.registerOre("foodGreentea", new ItemStack(FoodInit.paperPack, 1, 8));
		OreDictionary.registerOre("foodTea", new ItemStack(FoodInit.paperPack, 1, 9));
		OreDictionary.registerOre("foodCoffee", new ItemStack(FoodInit.paperPack, 1, 10));
		OreDictionary.registerOre("foodStock", new ItemStack(FoodInit.paperPack, 1, 11));
		OreDictionary.registerOre("foodLiquor", new ItemStack(FoodInit.paperPack, 1, 12));
		OreDictionary.registerOre("bucketMilk", new ItemStack(FoodInit.paperPack, 1, 13));

		OreDictionary.registerOre("foodButter", new ItemStack(FoodInit.dairy, 1, 0));
		OreDictionary.registerOre("foodCheese", new ItemStack(FoodInit.dairy, 1, 1));
		OreDictionary.registerOre("foodCustard", new ItemStack(FoodInit.dairy, 1, 2));
		OreDictionary.registerOre("foodCream", new ItemStack(FoodInit.dairy, 1, 3));
		OreDictionary.registerOre("foodButter", new ItemStack(FoodInit.dairy, 1, 4));
		OreDictionary.registerOre("listAllheavycream", new ItemStack(FoodInit.dairy, 1, 3));
		OreDictionary.registerOre("foodViscera", new ItemStack(FoodInit.meat, 1, 0));
		OreDictionary.registerOre("foodRennet", new ItemStack(FoodInit.meat, 1, 1));
		OreDictionary.registerOre("foodSquid", new ItemStack(FoodInit.meat, 1, 2));
		OreDictionary.registerOre("foodCalamariraw", new ItemStack(FoodInit.meat, 1, 2));
		OreDictionary.registerOre("foodAgar", new ItemStack(FoodInit.meat, 1, 3));
		OreDictionary.registerOre("foodGelatine", new ItemStack(FoodInit.meat, 1, 4));
		OreDictionary.registerOre("foodAnyMeat", new ItemStack(FoodInit.meat, 1, 5));
		OreDictionary.registerOre("listAllmeatraw", new ItemStack(FoodInit.meat, 1, 5));
		OreDictionary.registerOre("foodMiso", new ItemStack(FoodInit.meat, 1, 6));
		OreDictionary.registerOre("foodSoysauce", new ItemStack(FoodInit.meat, 1, 7));
		OreDictionary.registerOre("foodSiroanko", new ItemStack(FoodInit.meat, 1, 8));
		OreDictionary.registerOre("foodPastry", new ItemStack(FoodInit.pastry, 1, 0));
		OreDictionary.registerOre("foodDough", new ItemStack(FoodInit.bread, 1, 0));
		OreDictionary.registerOre("foodRicebowl", new ItemStack(FoodInit.ricebowl, 1, 0));
		OreDictionary.registerOre("foodRicecake", new ItemStack(FoodInit.mochi, 1, 1));

		ConvertTargetList.addExclusing(new ItemStack(FoodInit.paperPack, 1, 1));
		ConvertTargetList.addExclusing(new ItemStack(FoodInit.paperPack, 1, 2));

		OreDictionary.registerOre("listAllveggie", new ItemStack(Items.CARROT, 1, 0));
		OreDictionary.registerOre("listAllveggie", new ItemStack(Items.POTATO, 1, 0));
		OreDictionary.registerOre("listAllveggie", new ItemStack(Items.BEETROOT, 1, 0));
		OreDictionary.registerOre("listAllveggie", new ItemStack(Blocks.PUMPKIN, 1, 0));
	}

	static void loadFuelData() {
		MainAPIManager.fuelRegister.registerFuel(MainInit.oil, 60);
		MainAPIManager.fuelRegister.registerFuel(MainInit.blackLiquor, 60);
	}

	static void loadDrinkData() {
		DCPluginFluid.registerPotion(MainInit.greenTea, MobEffects.HASTE);
		DCPluginFluid.registerPotion(MainInit.blackTea, MobEffects.RESISTANCE);
		DCPluginFluid.registerPotion(MainInit.coffee, MobEffects.NIGHT_VISION);
		DCPluginFluid.registerPotion(MainInit.cream, DCInit.prevFreeze);
		DCPluginFluid.registerPotion(MainInit.oil, MobEffects.SPEED);
		DCPluginFluid.registerPotion(MainInit.stock, MobEffects.FIRE_RESISTANCE);
		DCPluginFluid.registerPotion(MainInit.lemon, MobEffects.JUMP_BOOST);
		DCPluginFluid.registerPotion(MainInit.blackLiquor, MobEffects.POISON);
		DCPluginFluid.registerPotion(FluidRegistry.LAVA, MobEffects.FIRE_RESISTANCE);
		DCPluginFluid.registerPotion(MainInit.soyMilk, MobEffects.INSTANT_HEALTH);
	}

}
