package defeatedcrow.hac.plugin;

import java.util.List;

import com.google.common.collect.Lists;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.SpinningRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.recipes.device.RegisterBrewingDC;
import defeatedcrow.hac.main.recipes.device.RegisterCrusherRecipe;
import defeatedcrow.hac.main.recipes.device.RegisterFluidRecipe;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class DCPluginBoP {

	public static final DCPluginBoP INSTANCE = new DCPluginBoP();

	private DCPluginBoP() {}

	public static void loadInit() {

		// climate registering
		ClimateAPI.registerBlock.registerHeatBlock(BOPBlocks.flower_1, 4, DCHeatTier.FROSTBITE);
		ClimateAPI.registerBlock.registerHeatBlock(BOPBlocks.hard_ice, 0, DCHeatTier.COLD);
		ClimateAPI.registerBlock.registerHeatBlock(BOPBlocks.hot_spring_water, 0, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHeatBlock(BOPBlocks.flower_0, 15, DCHeatTier.OVEN);

		ClimateAPI.registerBlock.registerHumBlock(BOPBlocks.dried_sand, 0, DCHumidity.DRY);
		ClimateAPI.registerBlock.registerHumBlock(BOPBlocks.ash_block, 0, DCHumidity.DRY);
		ClimateAPI.registerBlock.registerHumBlock(BOPBlocks.flesh, 0, DCHumidity.WET);
		ClimateAPI.registerBlock.registerHumBlock(BOPBlocks.mud, 0, DCHumidity.WET);
		ClimateAPI.registerBlock.registerHumBlock(BOPBlocks.coral, 0, DCHumidity.UNDERWATER);

		// add ore
		addOre("dropHoney", BOPItems.jar_filled, 0);
		addOre("dropHoney", BOPItems.filled_honeycomb, 0);

		addOre("cropSeaweed", BOPBlocks.seaweed, 0);
		addOre("cropKelp", BOPBlocks.seaweed, 0);
		addOre("listAllberry", BOPItems.berries, 0);

		addOre("listAllmushroom", BOPBlocks.mushroom, 1);
		addOre("listAllmushroom", BOPBlocks.mushroom, 2);
		addOre("listAllmushroom", BOPBlocks.mushroom, 4);

		addOre("vineLeaves", BOPBlocks.ivy, 0);
		addOre("vineLeaves", BOPBlocks.willow_vine, 0);

		addOre("cropBambooshoot", BOPBlocks.sapling_0, 2);

		addOre("blockTallgrass", BOPBlocks.plant_0, 0);
		addOre("blockTallgrass", BOPBlocks.plant_0, 1);
		addOre("blockTallgrass", BOPBlocks.plant_0, 2);
		addOre("blockTallgrass", BOPBlocks.plant_0, 3);
		addOre("blockTallgrass", BOPBlocks.plant_0, 4);
		addOre("blockTallgrass", BOPBlocks.plant_0, 5);
		addOre("blockTallgrass", BOPBlocks.plant_0, 7);
		addOre("blockTallgrass", BOPBlocks.plant_0, 8);
		addOre("blockTallgrass", BOPBlocks.plant_0, 9);
		addOre("blockTallgrass", BOPBlocks.plant_0, 10);
		addOre("blockTallgrass", BOPBlocks.plant_0, 13);
		addOre("blockTallgrass", BOPBlocks.plant_0, 14);
		addOre("blockTallgrass", BOPBlocks.plant_0, 15);
		addOre("blockTallgrass", BOPBlocks.plant_1, 0);

		addOre("cropWildrice", BOPBlocks.plant_1, 3);
		addOre("listAllgrain", BOPBlocks.plant_1, 3);
		addOre("cropBarley", BOPBlocks.plant_1, 11);
		addOre("listAllgrain", BOPBlocks.plant_1, 11);

		addOre("listAllmeatraw", BOPBlocks.flesh, 0);

		List<ItemStack> leaves = Lists.newArrayList();
		leaves.add(new ItemStack(Blocks.WATERLILY, 1, 0));
		leaves.add(new ItemStack(BOPBlocks.plant_0, 1, 10));
		leaves.add(new ItemStack(BOPBlocks.plant_0, 1, 11));
		leaves.add(new ItemStack(BOPBlocks.plant_0, 1, 12));
		leaves.add(new ItemStack(BOPBlocks.waterlily, 1, 32767));
		leaves.add(new ItemStack(BOPBlocks.coral, 1, 4));

		Item wax = Item.REGISTRY.getObject(new ResourceLocation("forestry", "beeswax"));

		// machine
		if (ModuleConfig.r_mill) {
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), null, 0.0F, "cropWildrice");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), null, 0.0F, "cropBarley");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0.0F, leaves);
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), new ItemStack(Items.SLIME_BALL, 1,
					0), 0.25F, new ItemStack(BOPBlocks.mud, 1, 0));

			if (DCIntegrationCore.loadedForestry && wax != null) {
				RecipeAPI.registerMills.addRecipe(new ItemStack(wax, 1, 0), null, 0.0F, new ItemStack(
						BOPItems.honeycomb, 1, 0));
			}
		}

		if (ModuleConfig.r_crusher) {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), new ItemStack(
					MainInit.foodDust, 1, 0), 0.25F, new ItemStack(MainInit.foodDust, 1,
							1), 0.25F, null, RegisterCrusherRecipe.SUS_Blade, "cropWildrice");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), new ItemStack(
					MainInit.foodDust, 1, 0), 0.25F, new ItemStack(MainInit.foodDust, 1,
							1), 0.25F, null, RegisterCrusherRecipe.SUS_Blade, "cropBarley");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), new ItemStack(
					MainInit.foodDust, 1, 1), 0.25F, RegisterCrusherRecipe.SUS_Blade, leaves);

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), new ItemStack(Items.SLIME_BALL,
					1, 0), 0.25F, RegisterCrusherRecipe.SUS_Blade, new ItemStack(BOPBlocks.mud, 1, 0));

			if (DCIntegrationCore.loadedForestry && wax != null) {
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(wax, 1,
						0), null, 0.0F, RegisterCrusherRecipe.SUS_Blade, new ItemStack(BOPItems.honeycomb, 1, 0));
			}

		}

		if (ModuleConfig.r_spinning) {
			RecipeAPI.registerSpinningRecipes.addRecipe(new SpinningRecipe(new ItemStack(MainInit.clothes, 1, 0), 2,
					"plantFlax"));
		}

		if (ModuleConfig.r_fluid) {
			RegisterFluidRecipe.regNonFoodRecipe(new ItemStack(MainInit.foodDust, 1,
					3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(
							FluidRegistry.WATER, 200), new Object[] {
									"cropBarley"
			});

		}

		if (ModuleConfig.food) {
			RegisterFluidRecipe.regNonFoodRecipe(new ItemStack(FoodInit.crops, 1,
					18), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] {
									"plantReed",
									"dustAsh"
			});

			if (ModuleConfig.food_advanced && ModuleConfig.r_brewing) {

				RegisterBrewingDC.still(new ItemStack(FoodInit.essentialOil, 1, 0), new FluidStack(FoodInit.roseWater,
						1000), new FluidStack(FluidRegistry.WATER, 5000), new Object[] {
								new ItemStack(BOPBlocks.flower_1, 8, 5)
				});

				RegisterBrewingDC.still(new ItemStack(FoodInit.essentialOil, 1, 5), null, new FluidStack(
						FluidRegistry.WATER, 5000), new Object[] {
								new ItemStack(BOPBlocks.flower_1, 8, 0)
				});

				Fluid fl1 = FluidRegistry.getFluid("poison");
				if (fl1 != null) {
					RegisterBrewingDC.brewng(ItemStack.EMPTY, new FluidStack(MainInit.fuelGas, 500), new FluidStack(fl1,
							1000), new Object[] {
									new ItemStack(FoodInit.methanogen, 1, 0)
					});
				}

				RegisterBrewingDC.brewng(new ItemStack(Blocks.DIRT), new FluidStack(MainInit.fuelGas,
						50), new FluidStack(
								FluidRegistry.WATER,
								1000), new Object[] {
										new ItemStack(BOPBlocks.mud),
										new ItemStack(FoodInit.methanogen, 1, 0)
				});

			}
		}

	}

	private static void addOre(String string, Block block, int meta) {
		try {
			OreDictionary.registerOre(string, new ItemStack(block, 1, meta));
		} catch (Exception e) {

		}
	}

	private static void addOre(String string, Item item, int meta) {
		try {
			OreDictionary.registerOre(string, new ItemStack(item, 1, meta));
		} catch (Exception e) {

		}
	}

}
