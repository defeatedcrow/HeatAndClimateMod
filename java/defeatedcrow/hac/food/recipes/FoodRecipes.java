package defeatedcrow.hac.food.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.ClimateSmelting;
import defeatedcrow.hac.core.climate.recipe.FluidCraftRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;

public class FoodRecipes {

	public static void load() {
		loadBasicRecipe();
		loadClimateRecipe();
		loadFluidRecipes();
	}

	static void loadBasicRecipe() {
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
	}

	static void loadClimateRecipe() {

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
	}
}
