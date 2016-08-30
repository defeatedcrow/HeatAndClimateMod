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
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.cultivate.CropAPI;
import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.ClimateSmelting;
import defeatedcrow.hac.core.climate.recipe.FluidCraftRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;

public class FoodRecipes {

	public static void load() {
		loadBasicRecipes();
		loadCropRecipes();
		loadClimateRecipes();
		loadMillRecipe();
		loadFluidRecipes();
		loadCropData();
	}

	static void loadBasicRecipes() {

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
	}

	static void loadMillRecipe() {
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), "seedRice");
		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.drop, 1, 0), new ItemStack(MainInit.miscDust, 1, 4),
				0.25F, new ItemStack(FoodInit.seeds, 4, 5));
		RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.drop, 1, 0), new ItemStack(MainInit.miscDust, 1, 4),
				0.25F, new ItemStack(FoodInit.crops, 4, 7));
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
}
