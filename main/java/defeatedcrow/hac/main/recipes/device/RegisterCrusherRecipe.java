package defeatedcrow.hac.main.recipes.device;

import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class RegisterCrusherRecipe {

	// SUS
	public static ItemStack SUS_Blade = ItemStack.EMPTY;

	// Titanium
	public static ItemStack Ti_Blade = ItemStack.EMPTY;

	// Screen
	public static ItemStack Screen_Blade = ItemStack.EMPTY;

	public static void load() {

		if (ModuleConfig.machine) {
			SUS_Blade = new ItemStack(MachineInit.rotaryBlade, 1, 0);
			Ti_Blade = new ItemStack(MachineInit.rotaryBlade, 1, 1);
			Screen_Blade = new ItemStack(MachineInit.rotaryBlade, 1, 2);
		} else {
			// 代替
			SUS_Blade = new ItemStack(MainInit.gears, 1, 2);
			Ti_Blade = new ItemStack(MainInit.gears, 1, 3);
			Screen_Blade = new ItemStack(MainInit.gears, 1, 4);
		}

		// foods
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 0), SUS_Blade, "gemSalt");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 1), new ItemStack(
				MainInit.animalFeed, 1, 2), 1F, new ItemStack(MainInit.foodDust, 1, 0), 0.5F, SUS_Blade, "cropWheat");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 2), new ItemStack(
				MainInit.foodDust, 1, 0), 0.5F, SUS_Blade, "seedRice");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 2), new ItemStack(
				MainInit.animalFeed, 1, 2), 1F, new ItemStack(MainInit.foodDust, 1,
						0), 0.5F, null, SUS_Blade, "cropRice");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(
				MainInit.lemon, 250), SUS_Blade, "cropLemon");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
				MainInit.oil, 400), SUS_Blade, "cropOlive");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
				MainInit.oil, 200), SUS_Blade, "seedCotton");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
				MainInit.oil, 200), SUS_Blade, "seedGrape");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 4), null, 0F, new FluidStack(
				MainInit.oil, 200), SUS_Blade, "cropSoybean");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
				MainInit.oil, 100), SUS_Blade, "dustBran");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
				MainInit.oil, 50), SUS_Blade, "listAllseeds");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
				MainInit.oil, 200), SUS_Blade, "cropWalnut");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.SUGAR, 6, 0), new ItemStack(MainInit.foodDust, 1,
				7), 1.0F, new ItemStack(MainInit.foodDust, 1, 2), 1.0F, SUS_Blade, "sugarcane");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.SUGAR, 2, 0), new ItemStack(MainInit.foodDust, 1,
				2), 0.2F, new ItemStack(MainInit.foodDust, 1, 1), 0.2F, SUS_Blade, "cropBeetroot");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 3), new ItemStack(
				MainInit.foodDust, 1, 1), 0.5F, SUS_Blade, "cropPotato");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 3), new ItemStack(
				MainInit.foodDust, 1, 1), 0.5F, SUS_Blade, "foodRice");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 3), new ItemStack(
				MainInit.foodDust, 1, 1), 0.5F, SUS_Blade, "foodFlour");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 3), new ItemStack(
				MainInit.foodDust, 1, 1), 0.5F, SUS_Blade, new ItemStack(Items.POISONOUS_POTATO));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(
				MainInit.foodDust, 1, 1), SUS_Blade, "feedStraw");

		// container
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 24, 1), new ItemStack(
				MainInit.animalFeed, 8, 2), 1F, new ItemStack(MainInit.foodDust, 4,
						0), 1F, SUS_Blade, "containerWheat");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 16, 2), new ItemStack(
				MainInit.animalFeed, 8, 2), 1F, new ItemStack(MainInit.foodDust, 4,
						0), 1F, null, SUS_Blade, "containerRice");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 2, 1), null, 0F, new FluidStack(
				MainInit.lemon, 2000), SUS_Blade, "containerLemon");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 4), null, 0F, new FluidStack(
				MainInit.oil, 3200), SUS_Blade, "containerOlive");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 2, 4), null, 0F, new FluidStack(
				MainInit.oil, 1600), SUS_Blade, "containerSoybean");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 4), null, 0F, new FluidStack(
				MainInit.oil, 800), SUS_Blade, "containerBran");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 4), null, 0F, new FluidStack(
				MainInit.oil, 400), SUS_Blade, "containerSeeds");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 4), null, 0F, new FluidStack(
				MainInit.oil, 1600), SUS_Blade, "containerWalnut");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.SUGAR, 42, 0), new ItemStack(MainInit.foodDust, 8,
				7), 1.0F, new ItemStack(MainInit.foodDust, 8, 2), 1.0F, SUS_Blade, "containerReeds");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.SUGAR, 16, 0), new ItemStack(MainInit.foodDust, 2,
				2), 1F, new ItemStack(MainInit.foodDust, 2, 1), 1F, SUS_Blade, "containerBeetroot");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 24, 3), new ItemStack(
				MainInit.foodDust, 4, 1), 1F, SUS_Blade, "containerPotato");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 16, 3), new ItemStack(
				MainInit.foodDust, 4, 1), 1F, SUS_Blade, "containerRiceFood");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 16, 3), new ItemStack(
				MainInit.foodDust, 4, 1), 1F, SUS_Blade, "containerFlour");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(
				MainInit.foodDust, 4, 1), SUS_Blade, "blockStraw");

		if (ModuleConfig.food) {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(FoodInit.seeds, 1, 4), new ItemStack(
					MainInit.foodDust,
					1, 1), 0.25F, null, 0F, new FluidStack(MainInit.tomatoJuice, 100), SUS_Blade, "cropCoffee");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(FoodInit.seeds, 8, 4), new ItemStack(
					MainInit.foodDust,
					2, 1), 1F, null, 0F, new FluidStack(MainInit.tomatoJuice, 800), SUS_Blade, "containerCoffee");
		}

		if (OreDictionary.doesOreNameExist("plantRoot")) {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 3), new ItemStack(
					MainInit.foodDust, 1, 0), 0.5F, new ItemStack(MainInit.foodDust, 1,
							1), 0.5F, SUS_Blade, "plantRoot");
		}

		if (OreDictionary.doesOreNameExist("cropCorn")) {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 3), new ItemStack(
					MainInit.foodDust, 1, 0), 0.5F, new ItemStack(MainInit.foodDust, 1,
							1), 0.5F, SUS_Blade, "cropCorn");
		}

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.BLAZE_POWDER, 4, 0), new ItemStack(
				MainInit.miscDust, 1, 7), 0.5F, new ItemStack(MainInit.miscDust, 1, 6), 0.5F, SUS_Blade, "stickBlaze");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DYE, 6, 15), new ItemStack(MainInit.miscDust, 1,
				9), 1.0F, SUS_Blade, "bone");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), new ItemStack(
				MainInit.miscDust, 1, 3), 0.5F, SUS_Blade, "treeSapling");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), new ItemStack(
				MainInit.miscDust, 1, 3), 0.5F, SUS_Blade, "treeLeaves");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), SUS_Blade, "blockTallgrass");

		// new ores
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 0), SUS_Blade, "oreRed");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 1), SUS_Blade, "oreGreen");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 2), SUS_Blade, "oreBlue");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 3), SUS_Blade, "oreWhite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 4), SUS_Blade, "oreBlack");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 5), SUS_Blade, "oreLargeRed");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 6), SUS_Blade, "oreLargeGreen");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 7), SUS_Blade, "oreLargeBlue");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 8), SUS_Blade, "oreLargeWhite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 9), SUS_Blade, "oreLargeBlack");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 0), new ItemStack(MainInit.oreItem,
				1, 5), 0.5F, new ItemStack(MainInit.oreItem, 1, 10), 0.1F, Ti_Blade, "oreRed");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 1), new ItemStack(MainInit.oreItem,
				1, 6), 0.5F, new ItemStack(MainInit.oreItem, 1, 11), 0.1F, Ti_Blade, "oreGreen");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 2), new ItemStack(MainInit.oreItem,
				1, 7), 0.5F, new ItemStack(MainInit.oreItem, 1, 12), 0.1F, Ti_Blade, "oreBlue");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 3), new ItemStack(MainInit.oreItem,
				1, 8), 0.5F, new ItemStack(MainInit.oreItem, 1, 13), 0.1F, Ti_Blade, "oreWhite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 4), new ItemStack(MainInit.oreItem,
				1, 9), 0.5F, new ItemStack(MainInit.oreItem, 1, 14), 0.1F, Ti_Blade, "oreBlack");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 5), new ItemStack(MainInit.oreItem,
				1, 10), 0.3F, new ItemStack(MainInit.gems_red, 1, 1), 0.05F, Ti_Blade, "oreLargeRed");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 6), new ItemStack(MainInit.oreItem,
				1, 11), 0.3F, new ItemStack(MainInit.gems_green, 1, 1), 0.05F, Ti_Blade, "oreLargeGreen");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 7), new ItemStack(MainInit.oreItem,
				1, 12), 0.3F, new ItemStack(MainInit.gems_blue, 1, 1), 0.05F, Ti_Blade, "oreLargeBlue");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 8), new ItemStack(MainInit.oreItem,
				1, 13), 0.3F, new ItemStack(MainInit.gems_white, 1, 1), 0.05F, Ti_Blade, "oreLargeWhite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 9), new ItemStack(MainInit.oreItem,
				1, 14), 0.3F, new ItemStack(MainInit.gems_black, 1, 1), 0.05F, Ti_Blade, "oreLargeBlack");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_red, 1, 0), new ItemStack(
				MainInit.gems_red, 1, 1), 0.3F, new ItemStack(MainInit.gems_red, 1, 2), 0.03F, Screen_Blade, "oreRed");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_green, 1, 0), new ItemStack(
				MainInit.gems_green, 1, 1), 0.3F, new ItemStack(MainInit.gems_green, 1,
						2), 0.03F, Screen_Blade, "oreGreen");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 1, 0), new ItemStack(
				MainInit.gems_blue, 1, 1), 0.3F, new ItemStack(MainInit.gems_blue, 1,
						2), 0.03F, Screen_Blade, "oreBlue");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_white, 1, 0), new ItemStack(
				MainInit.gems_white, 1, 1), 0.3F, new ItemStack(MainInit.gems_white, 1,
						2), 0.03F, Screen_Blade, "oreWhite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_black, 1, 0), new ItemStack(
				MainInit.gems_black, 1, 1), 0.3F, new ItemStack(MainInit.gems_black, 1,
						2), 0.03F, Screen_Blade, "oreBlack");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_red, 1, 1), new ItemStack(
				MainInit.gems_red, 1, 2), 0.3F, Screen_Blade, "oreLargeRed");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_green, 1, 1), new ItemStack(
				MainInit.gems_green, 1, 2), 0.3F, Screen_Blade, "oreLargeGreen");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 1, 1), new ItemStack(
				MainInit.gems_blue, 1, 2), 0.3F, Screen_Blade, "oreLargeBlue");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_white, 1, 1), new ItemStack(
				MainInit.gems_white, 1, 2), 0.3F, Screen_Blade, "oreLargeWhite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_black, 1, 1), new ItemStack(
				MainInit.gems_black, 1, 2), 0.3F, Screen_Blade, "oreLargeBlack");

		// ores
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 0), new ItemStack(MainInit.miscDust,
				1, 2), 0.2F, new ItemStack(Items.DYE, 1, 4), 0.1F, null, Ti_Blade, "oreCopper");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), new ItemStack(
				MainInit.miscDust, 1, 2), 0.2F, new ItemStack(MainInit.gems_blue, 1,
						1), 0.05F, null, Ti_Blade, "oreAluminum");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 14), new ItemStack(
				MainInit.miscDust, 1, 2), 0.2F, new ItemStack(MainInit.gems_red, 1,
						2), 0.05F, null, Ti_Blade, "oreManganese");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 8), new ItemStack(Items.CLAY_BALL,
				1, 0), 0.2F, new ItemStack(Items.REDSTONE, 1, 0), 0.1F, null, Ti_Blade, "oreTin");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 2), new ItemStack(Items.CLAY_BALL,
				1, 0), 0.2F, new ItemStack(Items.EMERALD, 1, 0), 0.05F, null, Ti_Blade, "oreNickel");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 9), new ItemStack(Items.CLAY_BALL,
				1, 0), 0.2F, new ItemStack(MainInit.gems_green, 1, 2), 0.05F, null, Ti_Blade, "oreChromium");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 1), new ItemStack(
				MainInit.gems_layer, 1, 0), 0.2F, new ItemStack(MainInit.gems_blue, 1,
						0), 0.1F, null, Ti_Blade, "oreZinc");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 13), new ItemStack(
				MainInit.gems_layer, 1, 0), 0.2F, new ItemStack(MainInit.oreDust, 1,
						3), 0.05F, null, Ti_Blade, "oreLead");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 12), new ItemStack(
				MainInit.gems_layer, 1, 0), 0.2F, new ItemStack(MainInit.gems_blue, 1,
						2), 0.05F, null, Ti_Blade, "oreBismuth");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 5), new ItemStack(MainInit.miscDust,
				1, 1), 0.2F, new ItemStack(MainInit.gems_white, 1, 0), 0.03F, null, Ti_Blade, "oreIron");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 4), new ItemStack(MainInit.miscDust,
				1, 1), 0.2F, new ItemStack(Items.DIAMOND, 1, 0), 0.05F, null, Ti_Blade, "oreGold");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 10), new ItemStack(
				MainInit.miscDust, 1, 1), 0.2F, new ItemStack(MainInit.gems_white, 1,
						2), 0.05F, null, Ti_Blade, "oreTitanium");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 7), new ItemStack(MainInit.miscDust,
				1, 13), 0.2F, new ItemStack(Items.BLAZE_POWDER, 1, 0), 0.05F, null, Ti_Blade, "oreMagnetite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 3), new ItemStack(MainInit.miscDust,
				1, 13), 0.2F, new ItemStack(MainInit.gems_black, 1, 1), 0.05F, null, Ti_Blade, "oreSilver");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 15), new ItemStack(
				MainInit.miscDust, 1, 13), 0.2F, new ItemStack(MainInit.gems_black, 1,
						2), 0.05F, null, Ti_Blade, "oreMolybdenum");

		// layer
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 4, 0), new ItemStack(
				MainInit.gems_layer, 1, 1), 0.1F, Ti_Blade, "oreGypsum");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 4, 2), new ItemStack(
				Items.PRISMARINE_SHARD), 0.1F, Ti_Blade, "oreLime");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 4, 1), new ItemStack(
				MainInit.oreDust, 1, 5), 0.1F, Ti_Blade, "oreSalt");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 2, 2), new ItemStack(
				MainInit.miscDust, 1, 9), 0.1F, null, Ti_Blade, "oreNiter");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 2, 3), new ItemStack(
				Items.BLAZE_POWDER, 1, 0), 0.1F, null, Ti_Blade, "oreSulfur");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_green, 2, 3), new ItemStack(
				MainInit.oreDust, 1, 2), 0.1F, null, Ti_Blade, "oreSerpentine");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 1, 3), new ItemStack(
				MainInit.gems_layer, 2, 0), 1F, null, Ti_Blade, "oreTravertine");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 3, 6), new ItemStack(
				MainInit.gems_blue, 1, 3), 1F, null, Ti_Blade, "oreApatite");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_red, 2, 5), new ItemStack(MainInit.oreItem,
				1, 10), 0.3F, null, Screen_Blade, "oreRuby");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 2, 5), new ItemStack(
				MainInit.gems_red, 1, 2), 0.3F, null, Screen_Blade, "oreAquamarine");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_white, 2, 3), new ItemStack(
				MainInit.gems_red, 1, 4), 0.3F, null, Screen_Blade, "oreSakura");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_black, 2, 3), new ItemStack(
				MainInit.gems_black, 1, 5), 0.03F, null, Screen_Blade, "oreIolite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_white, 2, 5), new ItemStack(
				MainInit.oreItem, 1, 13), 0.3F, null, Screen_Blade, "oreTopaz");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_red, 2, 3), new ItemStack(
				MainInit.gems_black, 1, 4), 0.3F, null, Screen_Blade, "oreAmethyst");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 2, 4), new ItemStack(
				MainInit.gems_layer, 1, 6), 0.3F, null, Screen_Blade, "oreLarimar");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_green, 2, 4), new ItemStack(
				MainInit.gems_green, 1, 5), 0.3F, null, Screen_Blade, "oreFluorite");

		// vanilla
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.QUARTZ, 3, 0), new ItemStack(MainInit.gems_white,
				1, 0), 0.1F, null, Screen_Blade, "oreQuartz");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.EMERALD, 3, 0), new ItemStack(MainInit.gems_red, 1,
				2), 0.05F, null, Screen_Blade, "oreEmerald");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DIAMOND, 3, 0), new ItemStack(MainInit.miscDust, 1,
				12), 0.15F, null, Screen_Blade, "oreDiamond");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DYE, 8, 4), new ItemStack(MainInit.oreDust, 1,
				0), 1F, null, Screen_Blade, "oreLapis");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.REDSTONE, 8, 0), new ItemStack(MainInit.oreDust, 1,
				9), 0.25F, null, Screen_Blade, "oreRedstone");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.COAL, 3, 0), new ItemStack(MainInit.miscDust, 1,
				0), 1F, null, Screen_Blade, "oreCoal");

		// old
		if (!ModuleConfig.delete_old) {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 2, 0), new ItemStack(
					Items.QUARTZ, 1, 0), 0.05F, new ItemStack(MainInit.gems_blue, 1,
							1), 0.1F, null, Ti_Blade, "oreChalcedonyBlue");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_white, 2, 0), new ItemStack(
					MainInit.oreDust, 1, 3), 0.25F, new ItemStack(MainInit.oreDust, 1,
							4), 0.1F, null, Ti_Blade, "oreChalcedonyWhite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 2, 1), new ItemStack(
					MainInit.oreDust, 1, 13), 0.1F, new ItemStack(MainInit.gems_blue, 1,
							0), 0.1F, null, Ti_Blade, "oreSapphire");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_black, 2, 1), new ItemStack(
					MainInit.gems_red, 1, 2), 0.03F, new ItemStack(MainInit.gems_red, 1,
							3), 0.03F, null, Ti_Blade, "oreSchorl");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_red, 2, 1), new ItemStack(
					MainInit.gems_blue, 1, 2), 0.1F, new ItemStack(MainInit.oreDust, 2,
							14), 0.03F, null, Ti_Blade, "oreGarnet");
		}

		// ex
		if (!OreDictionary.getOres("dustOsmium").isEmpty()) {
			ItemStack os = OreDictionary.getOres("dustOsmium").get(0);
			if (!OreDictionary.getOres("dustIridium").isEmpty()) {
				ItemStack gem = OreDictionary.getOres("dustIridium").get(0);
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(os.getItem(), 2, os
						.getItemDamage()), gem, 0.1F, null, 0F, null, Ti_Blade, "oreOsmium");
			} else {
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(os.getItem(), 2, os
						.getItemDamage()), null, 0F, null, 0F, null, Ti_Blade, "oreOsmium");
			}
		}

		// ingot粉砕
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 0), Ti_Blade, "ingotCopper");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 1), Ti_Blade, "ingotZinc");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 2), Ti_Blade, "ingotNickel");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 3), Ti_Blade, "ingotSilver");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 4), Ti_Blade, "ingotGold");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 5), Ti_Blade, "ingotIron");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 8), Ti_Blade, "ingotTin");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), Ti_Blade, "ingotAluminium");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), Ti_Blade, "ingotAluminum");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), Ti_Blade, "ingotBismuth");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 13), Ti_Blade, "ingotLead");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 14), Ti_Blade, "ingotManganese");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 0), Ti_Blade, new ItemStack(Items.COAL,
				1, 0));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 1), Ti_Blade, "gemQuartz");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 1), Ti_Blade, "gemChalcedony");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), Ti_Blade, "gemSapphire");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), new ItemStack(MainInit.oreDust,
				1, 9), 0.5F, Ti_Blade, "gemRuby");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 6, 3), Ti_Blade, "logWood");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 3, 6), Ti_Blade, "gemNiter");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 3, 7), Ti_Blade, "gemSulfur");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 8), Ti_Blade, "gemGarnet");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 10), Ti_Blade, "gemRutile");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), Ti_Blade, "gemBauxite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), Ti_Blade, "gemBismuth");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 3, 9), Ti_Blade, "gemApatite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 9), new ItemStack(MainInit.oreDust,
				1, 5), 1.0F, Ti_Blade, "gemVivianite");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 11), Ti_Blade, "gemSerpentine");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 12), Ti_Blade, "gemSakura");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN
				.getDyeDamage()), Ti_Blade, "gemMalachite");

		if (ModuleConfig.machine) {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MachineInit.reagent, 2, 0), new ItemStack(
					MainInit.miscDust, 1, 13), 1.0F, Ti_Blade, "gemCrudeOil");
		}

		if (!OreDictionary.getOres("dustLithium").isEmpty()) {
			ItemStack dust = OreDictionary.getOres("dustLithium").get(0).copy();
			RecipeAPI.registerCrushers.addRecipe(dust, Ti_Blade, "gemKunzite");
		}

		if (!OreDictionary.getOres("dustObsidian").isEmpty()) {
			ItemStack dust = OreDictionary.getOres("dustObsidian").get(0).copy();
			dust.setCount(2);
			RecipeAPI.registerCrushers.addRecipe(dust, new ItemStack(MainInit.miscDust, 1,
					1), 1F, Ti_Blade, "obsidian");
		}

		// 以下バニラ
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(MainInit.gems_white,
				1, 0), 0.03F, Ti_Blade, new ItemStack(Blocks.COBBLESTONE, 1, 0));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(Items.QUARTZ, 1,
				0), 0.04F, Ti_Blade, new ItemStack(Blocks.STONE, 1, 1));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(MainInit.miscDust, 1,
				12), 0.05F, Ti_Blade, new ItemStack(Blocks.STONE, 1, 3));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(MainInit.miscDust, 1,
				12), 0.05F, Ti_Blade, new ItemStack(Blocks.STONE, 1, 5));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 4, 0), Ti_Blade, new ItemStack(Blocks.SANDSTONE,
				1, 32767));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 4, 0), new ItemStack(MainInit.oreDust, 1,
				11), 1F, null, 0F, null, Ti_Blade, new ItemStack(Blocks.RED_SANDSTONE, 1, 32767));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MainInit.oreDust, 1,
				11), 1F, null, 0F, null, Ti_Blade, new ItemStack(Blocks.SAND, 1, 1));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 1, 0), new ItemStack(MainInit.miscDust,
				1, 2), 1F, new ItemStack(MainInit.miscDust, 1, 10), 0.1F, null, Ti_Blade, new ItemStack(Blocks.SAND, 1,
						0));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 4, 2), new ItemStack(
				MainInit.miscDust, 1, 9), 1F, new ItemStack(MainInit.miscDust, 1,
						6), 0.5F, null, Ti_Blade, new ItemStack(
								Blocks.BONE_BLOCK, 1, 0));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.GLOWSTONE_DUST, 4, 0), Ti_Blade, new ItemStack(
				Blocks.GLOWSTONE));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.BLAZE_POWDER, 2, 0), Ti_Blade, new ItemStack(
				Blocks.MAGMA));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.ICE, 1, 0), null, 0.0F, Ti_Blade, new ItemStack(
				Blocks.PACKED_ICE));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F, Ti_Blade, new ItemStack(
				Blocks.NETHER_BRICK, 1, 0));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.NETHERBRICK, 6, 0), null, 0F, Ti_Blade, new ItemStack(
				Blocks.NETHER_BRICK_STAIRS, 1, 0));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F, Ti_Blade, new ItemStack(
				Blocks.NETHER_BRICK_FENCE, 1, 0));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.END_STONE, 1, 0), null, 0F, Ti_Blade, new ItemStack(
				Blocks.END_BRICKS, 1, 0));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.STRING, 4, 0), null, 0F, Ti_Blade, new ItemStack(
				Blocks.WOOL, 1, 32767));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F, Ti_Blade, new ItemStack(
				Blocks.STAINED_HARDENED_CLAY, 1, 32767));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F, Ti_Blade, new ItemStack(
				Blocks.HARDENED_CLAY, 1, 0));

		// RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(MainInit.gems_white,
		// 1, 0), 0.03F, Ti_Blade, "cobblestone");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Items.FLINT, 1,
				0), 1.0F, Ti_Blade, "gravel");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 1, 0), new ItemStack(MainInit.miscDust,
				1, 1), 0.25F, null, Ti_Blade, "dirt");
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MainInit.miscDust, 1,
				7), 0.5F, null, Ti_Blade, "netherrack");
	}

}
