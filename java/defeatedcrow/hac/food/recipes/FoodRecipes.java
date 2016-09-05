package defeatedcrow.hac.food.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.cultivate.CropAPI;
import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.ClimateSmelting;
import defeatedcrow.hac.core.climate.recipe.FluidCraftRecipe;
import defeatedcrow.hac.core.recipe.ConvertTargetList;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;

public class FoodRecipes {

	public static void load() {
		loadOres();
		loadBasicRecipes();
		loadCropRecipes();
		loadClimateRecipes();
		loadMillRecipe();
		loadFluidRecipes();
		loadCropData();
	}

	static void loadBasicRecipes() {

		// devices
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FoodInit.potteryPot, 1, 0), new Object[] {
				"XYX",
				"X X",
				"XXX",
				'X',
				new ItemStack(Blocks.HARDENED_CLAY, 1, 0),
				'Y',
				"itemCloth" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FoodInit.steelPot, 1, 0), new Object[] {
				"XYX",
				"X X",
				"XXX",
				'X',
				"ingotSteel",
				'Y',
				new ItemStack(Blocks.GLASS, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FoodInit.cupSilver, 1, 0), new Object[] {
				"XXX",
				"XX ",
				'X',
				"ingotNickelsilver" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FoodInit.cupSilver, 1, 1), new Object[] {
				"XXX",
				"XX ",
				'X',
				new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FoodInit.paperPack, 4, 0), new Object[] {
				" X ",
				"X X",
				" X ",
				'X',
				new ItemStack(Items.PAPER, 1, 0) }));

		// materials

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.meat, 1, 1),
				new Object[] { "foodViscera" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastry, 4, 0), new Object[] {
				"dustFlour",
				"foodButter",
				"egg",
				"dustSalt" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastry, 2, 0), new Object[] {
				"foodFlour",
				"foodOil",
				"bucketWater",
				"dustSalt" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.dairy, 1, 2), new Object[] {
				"foodCream",
				"egg",
				"dustFlour" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.dairy, 1, 2), new Object[] {
				"foodCream",
				"egg",
				"dustSugar" }));

		// foods

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.bread, 2, 0), new Object[] {
				"foodFlour",
				"dustSalt",
				"bucketWater" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.bread, 1, 2), new Object[] {
				new ItemStack(FoodInit.bread, 1, 0),
				new ItemStack(Items.PAPER, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.sticks, 1, 0), new Object[] {
				"stickWood",
				new ItemStack(Items.FISH, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.sticks, 1, 2), new Object[] {
				"stickWood",
				new ItemStack(Items.CHICKEN, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.sticks, 1, 4), new Object[] {
				"stickWood",
				new ItemStack(Items.PORKCHOP, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.sticks, 1, 6), new Object[] {
				"stickWood",
				new ItemStack(Items.BEEF, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastryRound, 1, 0), new Object[] {
				"foodPastry",
				"cropApple",
				"dustSugar",
				"foodButter" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastryRound, 1, 0), new Object[] {
				"foodPastry",
				"cropApple",
				"dropHoney",
				"foodButter" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastryRound, 1, 2), new Object[] {
				"foodPastry",
				"cropLemon",
				"dustSugar",
				"foodCream" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastryRound, 1, 2), new Object[] {
				"foodPastry",
				"croplemon",
				"dropHoney",
				"foodCream" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastryRound, 1, 4), new Object[] {
				"foodPastry",
				"cropSpinach",
				"foodAnyMeat",
				"foodCustard",
				"foodCheese" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastryRound, 1, 6), new Object[] {
				"foodPastry",
				"cropPotato",
				"cropOnion",
				"foodFish",
				"foodCustard",
				"foodCheese" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastrySquare, 1, 0), new Object[] {
				"foodPastry",
				"dustSugar" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastrySquare, 1, 2), new Object[] {
				"foodPastry",
				"foodAnyMeat",
				"dustSalt",
				"cropOnion" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.pastrySquare, 1, 2), new Object[] {
				"foodPastry",
				"foodAnyMeat",
				"dustSalt",
				"cropHerb" }));

		// smelting
		GameRegistry.addSmelting(new ItemStack(FoodInit.seeds, 1, 4), new ItemStack(FoodInit.teaLeaves, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(FoodInit.crops, 1, 8), new ItemStack(FoodInit.teaLeaves, 1, 1), 0.1F);
	}

	static void loadCropRecipes() {

		for (int i = 0; i < 6; i++) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.seeds, 1, i),
					new Object[] { new ItemStack(FoodInit.crops, 1, i) }));
		}

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.saplings, 1, 0), new Object[] {
				new ItemStack(FoodInit.crops, 1, 6),
				new ItemStack(Blocks.SAPLING, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.saplings, 1, 1), new Object[] {
				new ItemStack(FoodInit.crops, 1, 7),
				new ItemStack(Blocks.SAPLING, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.saplings, 1, 2), new Object[] {
				new ItemStack(FoodInit.crops, 1, 8),
				new ItemStack(Blocks.SAPLING, 1, 0) }));

		// seeds another recipes
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.seeds, 1, 0), new Object[] {
				new ItemStack(Items.WHEAT_SEEDS, 1, 0),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.seeds, 1, 1), new Object[] {
				new ItemStack(Items.MELON_SEEDS, 1, 0),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.seeds, 1, 2), new Object[] {
				new ItemStack(Items.PUMPKIN_SEEDS, 1, 0),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.seeds, 1, 3), new Object[] {
				new ItemStack(Items.CARROT, 1, 0),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.seeds, 1, 4), new Object[] {
				new ItemStack(Items.DYE, 1, 3),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.seeds, 1, 5), new Object[] {
				new ItemStack(Items.REEDS, 1, 0),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.saplings, 1, 0), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 1),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.saplings, 1, 1), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 2),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.saplings, 1, 2), new Object[] {
				new ItemStack(Blocks.SAPLING, 1, 0),
				"gemChalcedony" }));

		String[] crops = new String[] {
				"cropRice",
				"cropOnion",
				"cropSpinach",
				"cropTomato",
				"cropCoffee",
				"cropCotton",
				"cropLemon",
				"cropOlive",
				"cropTea" };
		for (int i = 0; i < crops.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.cropBasket, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					crops[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.crops, 1, i),
					new Object[] { new ItemStack(MainInit.cropBasket, 1, i) }));
		}

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.paperPack, 4, 1), new Object[] {
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(Items.WATER_BUCKET) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FoodInit.paperPack, 4, 2), new Object[] {
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(FoodInit.paperPack, 1, 0),
				new ItemStack(Items.MILK_BUCKET) }));

	}

	static void loadClimateRecipes() {

		ClimateSmelting square = new ClimateSmelting(new ItemStack(FoodInit.bread, 1, 3), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.bread, 1, 2));
		square.requiredHeat().add(DCHeatTier.SMELTING);
		square.requiredHum().add(DCHumidity.NORMAL);
		square.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(square, DCHeatTier.OVEN);

		ClimateSmelting round = new ClimateSmelting(new ItemStack(FoodInit.bread, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.bread, 1, 0));
		round.requiredHeat().add(DCHeatTier.SMELTING);
		round.requiredHum().add(DCHumidity.NORMAL);
		round.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(round, DCHeatTier.OVEN);

		ClimateSmelting fish = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 0));
		fish.requiredHeat().add(DCHeatTier.SMELTING);
		fish.requiredHum().add(DCHumidity.NORMAL);
		fish.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(fish, DCHeatTier.OVEN);

		ClimateSmelting tori = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 3), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 2));
		tori.requiredHeat().add(DCHeatTier.SMELTING);
		tori.requiredHum().add(DCHumidity.NORMAL);
		tori.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(tori, DCHeatTier.OVEN);

		ClimateSmelting pork = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 5), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 4));
		pork.requiredHeat().add(DCHeatTier.SMELTING);
		pork.requiredHum().add(DCHumidity.NORMAL);
		pork.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(pork, DCHeatTier.OVEN);

		ClimateSmelting beef = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 7), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 6));
		beef.requiredHeat().add(DCHeatTier.SMELTING);
		beef.requiredHum().add(DCHumidity.NORMAL);
		beef.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(beef, DCHeatTier.OVEN);

		ClimateSmelting a_tart = new ClimateSmelting(new ItemStack(FoodInit.pastryRound, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastryRound, 1, 0));
		beef.requiredHeat().add(DCHeatTier.SMELTING);
		beef.requiredHum().add(DCHumidity.NORMAL);
		beef.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(a_tart, DCHeatTier.OVEN);

		ClimateSmelting l_tart = new ClimateSmelting(new ItemStack(FoodInit.pastryRound, 1, 3), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastryRound, 1, 2));
		beef.requiredHeat().add(DCHeatTier.SMELTING);
		beef.requiredHum().add(DCHumidity.NORMAL);
		beef.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(l_tart, DCHeatTier.OVEN);

		ClimateSmelting s_tart = new ClimateSmelting(new ItemStack(FoodInit.pastryRound, 1, 5), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastryRound, 1, 4));
		beef.requiredHeat().add(DCHeatTier.SMELTING);
		beef.requiredHum().add(DCHumidity.NORMAL);
		beef.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(s_tart, DCHeatTier.OVEN);

		ClimateSmelting p_tart = new ClimateSmelting(new ItemStack(FoodInit.pastryRound, 1, 7), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastryRound, 1, 6));
		beef.requiredHeat().add(DCHeatTier.SMELTING);
		beef.requiredHum().add(DCHumidity.NORMAL);
		beef.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(p_tart, DCHeatTier.OVEN);

		ClimateSmelting s_pie = new ClimateSmelting(new ItemStack(FoodInit.pastrySquare, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastrySquare, 1, 0));
		beef.requiredHeat().add(DCHeatTier.SMELTING);
		beef.requiredHum().add(DCHumidity.NORMAL);
		beef.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(s_pie, DCHeatTier.OVEN);

		ClimateSmelting m_pie = new ClimateSmelting(new ItemStack(FoodInit.pastrySquare, 1, 3), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastrySquare, 1, 2));
		beef.requiredHeat().add(DCHeatTier.SMELTING);
		beef.requiredHum().add(DCHumidity.NORMAL);
		beef.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(m_pie, DCHeatTier.OVEN);
	}

	static void loadMillRecipe() {
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), "seedRice");
		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0),
				new ItemStack(MainInit.miscDust, 1, 4), 0.25F, new ItemStack(FoodInit.seeds, 4, 5));
		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0),
				new ItemStack(MainInit.miscDust, 1, 4), 0.25F, new ItemStack(FoodInit.crops, 4, 7));
		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropCream, 2, 0), "bucketMilk");
	}

	static void loadFluidRecipes() {
		FluidCraftRecipe salt = new FluidCraftRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), null, null,
				DCHeatTier.HOT, DCHumidity.DRY, null, 0, false, new FluidStack(FluidRegistry.WATER, 1000),
				(Object[]) null) {
			@Override
			public boolean additionalRequire(World world, BlockPos pos) {
				Biome biome = world.getBiomeGenForCoords(pos);
				if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.OCEAN)
						|| BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.BEACH)) {
					return true;
				}
				return false;
			}

			@Override
			public String additionalString() {
				return "OCEAN Only";
			}

		};
		RecipeAPI.registerFluidRecipes.addRecipe(salt, DCHeatTier.HOT);

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 1, 2), null, 0F, null,
				DCHeatTier.NORMAL, DCHumidity.DRY, null, false, null, new Object[] {
						new ItemStack(MainInit.foodMaterials, 1, 0),
						new ItemStack(Items.ROTTEN_FLESH) });

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 3, 2), null, 0F, null,
				DCHeatTier.NORMAL, DCHumidity.DRY, null, false, null, new Object[] {
						new ItemStack(MainInit.foodMaterials, 1, 0),
						new ItemStack(Items.BEEF) });

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 3, 2), null, 0F, null,
				DCHeatTier.NORMAL, DCHumidity.DRY, null, false, null, new Object[] {
						new ItemStack(MainInit.foodMaterials, 1, 0),
						new ItemStack(Items.PORKCHOP) });

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 1, 1), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						new ItemStack(MainInit.foodMaterials, 1, 0),
						new ItemStack(Items.EGG) });

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.teaLeaves, 1, 0), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, null, new Object[] { "seedCoffee" });

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.teaLeaves, 1, 1), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, null, new Object[] { "cropTea" });

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.teaLeaves, 1, 2), null, 0F, null,
				DCHeatTier.NORMAL, DCHumidity.WET, null, false, null, new Object[] { new ItemStack(FoodInit.teaLeaves,
						1, 1) });

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.coffee, 1000),
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 1000),
				new Object[] { new ItemStack(FoodInit.teaLeaves, 1, 0) });

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.greenTea, 1000),
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 1000),
				new Object[] { new ItemStack(FoodInit.teaLeaves, 1, 1) });

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.blackTea, 1000),
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 1000),
				new Object[] { new ItemStack(FoodInit.teaLeaves, 1, 2) });

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1, 0), null, 0F, null,
				DCHeatTier.NORMAL, DCHumidity.WET, null, false, new FluidStack(FoodInit.cream, 1000),
				new Object[] { "foodSalt" });

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1, 1), null, 0F, null,
				DCHeatTier.NORMAL, DCHumidity.WET, null, false, new FluidStack(FoodInit.cream, 1000),
				new Object[] { "foodRennet" });

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
	}

	static void loadOres() {
		OreDictionary.registerOre("cropRice", new ItemStack(FoodInit.crops, 1, 0));
		OreDictionary.registerOre("cropOnion", new ItemStack(FoodInit.crops, 1, 1));
		OreDictionary.registerOre("cropSpinach", new ItemStack(FoodInit.crops, 1, 2));
		OreDictionary.registerOre("cropTomato", new ItemStack(FoodInit.crops, 1, 3));
		OreDictionary.registerOre("cropCoffee", new ItemStack(FoodInit.crops, 1, 4));
		OreDictionary.registerOre("cropCotton", new ItemStack(FoodInit.crops, 1, 5));
		OreDictionary.registerOre("cropLemon", new ItemStack(FoodInit.crops, 1, 6));
		OreDictionary.registerOre("cropOlive", new ItemStack(FoodInit.crops, 1, 7));
		OreDictionary.registerOre("cropTea", new ItemStack(FoodInit.crops, 1, 8));
		OreDictionary.registerOre("cropHerb", new ItemStack(FoodInit.crops, 1, 9));
		OreDictionary.registerOre("seedRice", new ItemStack(FoodInit.seeds, 1, 0));
		OreDictionary.registerOre("seedOnion", new ItemStack(FoodInit.seeds, 1, 1));
		OreDictionary.registerOre("seedSpinach", new ItemStack(FoodInit.seeds, 1, 2));
		OreDictionary.registerOre("seedTomato", new ItemStack(FoodInit.seeds, 1, 3));
		OreDictionary.registerOre("seedCoffee", new ItemStack(FoodInit.seeds, 1, 4));
		OreDictionary.registerOre("seedCotton", new ItemStack(FoodInit.seeds, 1, 5));
		OreDictionary.registerOre("saplingLemon", new ItemStack(FoodInit.saplings, 1, 0));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings, 1, 0));
		OreDictionary.registerOre("saplingOlive", new ItemStack(FoodInit.saplings, 1, 1));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings, 1, 1));
		OreDictionary.registerOre("saplingTea", new ItemStack(FoodInit.saplings, 1, 2));
		OreDictionary.registerOre("treeSapling", new ItemStack(FoodInit.saplings, 1, 2));

		OreDictionary.registerOre("bucketWater", new ItemStack(FoodInit.paperPack, 1, 1));
		OreDictionary.registerOre("bucketMilk", new ItemStack(FoodInit.paperPack, 1, 2));

		OreDictionary.registerOre("foodCream", new ItemStack(FoodInit.paperPack, 1, 3));
		OreDictionary.registerOre("foodOil", new ItemStack(FoodInit.paperPack, 1, 4));
		OreDictionary.registerOre("foodButter", new ItemStack(FoodInit.dairy, 1, 0));
		OreDictionary.registerOre("foodCheese", new ItemStack(FoodInit.dairy, 1, 1));
		OreDictionary.registerOre("foodCustard", new ItemStack(FoodInit.dairy, 1, 2));
		OreDictionary.registerOre("foodViscera", new ItemStack(FoodInit.meat, 1, 0));
		OreDictionary.registerOre("foodRennet", new ItemStack(FoodInit.meat, 1, 1));
		OreDictionary.registerOre("foodPastry", new ItemStack(FoodInit.pastry, 1, 0));

		ConvertTargetList.addExclusing(new ItemStack(FoodInit.paperPack, 1, 1));
		ConvertTargetList.addExclusing(new ItemStack(FoodInit.paperPack, 1, 2));
	}
}
