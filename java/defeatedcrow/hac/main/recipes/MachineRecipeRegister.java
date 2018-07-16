package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.ClimateSmelting;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.MainCoreConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MachineRecipeRegister {

	public static void load() {
		loadOreRecipes();
		loadContRecipes();
		loadBuildRecipes();
		loadVanillaRecipes();
		loadSmelting();
		registerClimate();
	}

	static void loadOreRecipes() {
		// 金属精錬。ごく一部はKILNでも焼けるが、ほとんどがSMELTを要求する。
		// copper
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 0), DCHeatTier.KILN, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 0));
		// zinc
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 1), DCHeatTier.KILN, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 1));
		// nickel
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 2), DCHeatTier.SMELTING, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 2));
		// silver
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 3), DCHeatTier.SMELTING, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 3));
		// brass
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 4), DCHeatTier.KILN, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 4));
		// steel
		if (MainCoreConfig.steel) {
			RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 5), DCHeatTier.SMELTING, null,
					DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 5));
			// iron
			ClimateSmelting iron = new ClimateSmelting(new ItemStack(Blocks.IRON_BLOCK, 1, 0), null, DCHeatTier.KILN, null, DCAirflow.TIGHT, 0F, false, new ItemStack(MainInit.dustBlock, 1, 5));
			iron.requiredHeat().remove(DCHeatTier.SMELTING);
			RecipeAPI.registerSmelting.addRecipe(iron, DCHeatTier.KILN);
		} else {
			RecipeAPI.registerSmelting.addRecipe(new ItemStack(Blocks.IRON_BLOCK, 1, 0), DCHeatTier.KILN, null,
					DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 5));
		}
		// nickelsilver
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 6), DCHeatTier.SMELTING, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 6));
		// magnet
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 7), DCHeatTier.UHT, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 7));

		// tin
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 8), DCHeatTier.KILN, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 8));

		// bronze
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 9), DCHeatTier.KILN, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 9));

		// sus
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 10), DCHeatTier.UHT, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 10));

		// gold
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(Blocks.GOLD_BLOCK, 1, 0), DCHeatTier.KILN, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 11));

		// titanium
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 11), DCHeatTier.UHT, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 12));

		// aluminium
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 12), DCHeatTier.SMELTING, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 13));

		// bismuth
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 13), DCHeatTier.KILN, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 14));

		// bscco
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 14), DCHeatTier.UHT, null,
				DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 15));

		// dust個別精錬
		// RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 0),
		// DCHeatTier.KILN, null,
		// DCAirflow.TIGHT, false, new ItemStack(MainInit.oreDust, 1, 0));
		// RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 1),
		// DCHeatTier.KILN, null,
		// DCAirflow.TIGHT, false, new ItemStack(MainInit.oreDust, 1, 1));
		// RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 2),
		// DCHeatTier.SMELTING, null,
		// DCAirflow.TIGHT, false, new ItemStack(MainInit.oreDust, 1, 2));
		// RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 3),
		// DCHeatTier.SMELTING, null,
		// DCAirflow.TIGHT, false, new ItemStack(MainInit.oreDust, 1, 3));
		// RecipeAPI.registerSmelting.addRecipe(new ItemStack(Items.GOLD_INGOT, 1, 0),
		// DCHeatTier.KILN, null,
		// DCAirflow.TIGHT, false, new ItemStack(MainInit.oreDust, 1, 4));
		// RecipeAPI.registerSmelting.addRecipe(new ItemStack(Items.IRON_INGOT, 1, 0),
		// DCHeatTier.KILN, null,
		// DCAirflow.TIGHT, false, new ItemStack(MainInit.oreDust, 1, 5));
		// RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 5),
		// DCHeatTier.SMELTING, null,
		// DCAirflow.TIGHT, false, new ItemStack(MainInit.oreDust, 1, 6));
		// RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 7),
		// DCHeatTier.UHT, null,
		// DCAirflow.TIGHT, false, new ItemStack(MainInit.oreDust, 1, 7));
		// RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 8),
		// DCHeatTier.KILN, null,
		// DCAirflow.TIGHT, false, new ItemStack(MainInit.oreDust, 1, 8));

	}

	static void loadContRecipes() {
		// コンテナ類の自然変化
		// log
		for (int i = 0; i < 6; i++) {
			ClimateSmelting recipe = new ClimateSmelting(new ItemStack(MainInit.logCont, 1, 6), null, DCHeatTier.KILN, null, DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.logCont, 1, i));
			RecipeAPI.registerSmelting.addRecipe(recipe, DCHeatTier.KILN);
		}

		// burnt crops
		ClimateSmelting apple = new ClimateSmelting(new ItemStack(MainInit.cropCont, 1, 9), null, DCHeatTier.OVEN, null, DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.cropCont, 1, 0));
		apple.requiredAir().add(DCAirflow.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(apple, DCHeatTier.OVEN);

		ClimateSmelting potato = new ClimateSmelting(new ItemStack(MainInit.cropCont, 1, 10), null, DCHeatTier.OVEN, null, DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.cropCont, 1, 1));
		potato.requiredAir().add(DCAirflow.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(potato, DCHeatTier.OVEN);

		// 焼くと骨に、煮ると皮になる
		ClimateSmelting flesh1 = new ClimateSmelting(new ItemStack(MainInit.dropCont, 1, 1), null, DCHeatTier.KILN, DCHumidity.DRY, null, 0, false, new ItemStack(MainInit.dropCont, 1, 0));
		flesh1.requiredHum().add(DCHumidity.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(flesh1, DCHeatTier.OVEN);

		ClimateSmelting flesh2 = new ClimateSmelting(new ItemStack(MainInit.miscCont, 1, 2), null, DCHeatTier.KILN, DCHumidity.WET, null, 0, false, new ItemStack(MainInit.dropCont, 1, 0));
		flesh2.requiredHum().add(DCHumidity.UNDERWATER);
		RecipeAPI.registerSmelting.addRecipe(flesh2, DCHeatTier.OVEN);

		// AMTにもあった、火薬と粘土の互換
		ClimateSmelting powder = new ClimateSmelting(new ItemStack(MainInit.miscCont, 1, 0), null, DCHeatTier.HOT, DCHumidity.WET, null, 0, false, new ItemStack(MainInit.dropCont, 1, 4));
		powder.requiredHum().add(DCHumidity.UNDERWATER);
		RecipeAPI.registerSmelting.addRecipe(powder, DCHeatTier.HOT);

		ClimateSmelting clay = new ClimateSmelting(new ItemStack(MainInit.dropCont, 1, 4), null, DCHeatTier.HOT, DCHumidity.DRY, DCAirflow.NORMAL, 0, false, new ItemStack(MainInit.miscCont, 1, 0));
		clay.requiredAir().add(DCAirflow.FLOW);
		clay.requiredAir().add(DCAirflow.WIND);
		RecipeAPI.registerSmelting.addRecipe(clay, DCHeatTier.HOT);

	}

	static void loadBuildRecipes() {
		// 建材
		ClimateSmelting marble = new ClimateSmelting(new ItemStack(MainInit.gemBlock, 1, 6), null, DCHeatTier.KILN, null, DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.ores_2, 1, 0));
		marble.requiredAir().add(DCAirflow.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(marble, DCHeatTier.KILN);

		ClimateSmelting sele = new ClimateSmelting(new ItemStack(MainInit.selenite, 1, 0), null, DCHeatTier.KILN, null, DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.gemBlock, 1, 3));
		sele.requiredAir().add(DCAirflow.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(sele, DCHeatTier.KILN);

		ClimateSmelting cry = new ClimateSmelting(new ItemStack(MainInit.selenite, 1, 3), null, DCHeatTier.KILN, null, DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.dustBlock_2, 1, 1));
		cry.requiredAir().add(DCAirflow.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(cry, DCHeatTier.KILN);

		ClimateSmelting dirt = new ClimateSmelting(new ItemStack(MainInit.dustBlock_2, 1, 0), null, DCHeatTier.OVEN, DCHumidity.DRY, null, 0, false, new ItemStack(Blocks.DIRT, 1, 0));
		RecipeAPI.registerSmelting.addRecipe(dirt, DCHeatTier.OVEN);

		ClimateSmelting dirt2 = new ClimateSmelting(new ItemStack(Blocks.SAND, 1, 0), null, DCHeatTier.SMELTING, DCHumidity.DRY, null, 0, false, new ItemStack(MainInit.dustBlock_2, 1, 0));
		RecipeAPI.registerSmelting.addRecipe(dirt2, DCHeatTier.SMELTING);

		ClimateSmelting dirt3 = new ClimateSmelting(new ItemStack(Blocks.DIRT, 1, 0), null, DCHeatTier.WARM, DCHumidity.WET, null, 0, false, new ItemStack(MainInit.dustBlock_2, 1, 0));
		dirt3.requiredHum().add(DCHumidity.UNDERWATER);
		RecipeAPI.registerSmelting.addRecipe(dirt3, DCHeatTier.WARM);
	}

	static void loadSmelting() {
		// KILNはバニラカマドに対応する
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 0), new ItemStack(MainInit.metalBlock, 1, 0),
				0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 1), new ItemStack(MainInit.metalBlock, 1, 1),
				0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 4), new ItemStack(MainInit.metalBlock, 1, 4),
				0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 5), new ItemStack(Blocks.IRON_BLOCK, 1, 0), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 8), new ItemStack(MainInit.metalBlock, 1, 8),
				0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 9), new ItemStack(MainInit.metalBlock, 1, 9),
				0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 14), new ItemStack(MainInit.metalBlock, 1, 13),
				0.3F);

		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 0), new ItemStack(MainInit.oreIngot, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 1), new ItemStack(MainInit.oreIngot, 1, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 4), new ItemStack(Items.GOLD_INGOT, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 5), new ItemStack(Items.IRON_INGOT, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 8), new ItemStack(MainInit.oreIngot, 1, 8), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 12), new ItemStack(MainInit.oreIngot, 1, 13), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 13), new ItemStack(MainInit.oreIngot, 1, 15), 0.1F);

		GameRegistry.addSmelting(new ItemStack(MainInit.gemBlock, 1, 3), new ItemStack(MainInit.selenite, 1, 0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock_2, 1, 1), new ItemStack(MainInit.selenite, 1, 3),
				0.15F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock_2, 1, 0), new ItemStack(Items.CLAY_BALL, 1, 0),
				0.15F);

		GameRegistry.addSmelting(new ItemStack(MainInit.foodDust, 1, 1), new ItemStack(MainInit.miscDust, 1, 5), 0.15F);

		// gems
		GameRegistry.addSmelting(new ItemStack(Items.FLINT, 1, 0), new ItemStack(MainInit.gems, 1, 1), 0.2F);
		GameRegistry.addSmelting(new ItemStack(MainInit.miscDust, 1, 1), new ItemStack(Blocks.GLASS, 1, 0), 0.2F);
		// GameRegistry.addSmelting(new ItemStack(MainInit.gems, 1, 12), new ItemStack(Items.ENDER_PEARL, 1, 0), 0.2F);

		GameRegistry.addSmelting(new ItemStack(MainInit.ores_2, 1, 0), new ItemStack(MainInit.gemBlock, 1, 6), 0.2F);

		GameRegistry.addSmelting(new ItemStack(MainInit.desiccant, 1, 3), new ItemStack(MainInit.desiccant, 1, 0), 0F);

		GameRegistry.addSmelting(new ItemStack(FoodInit.meat, 1, 2), new ItemStack(MainInit.bakedApple, 1, 4), 0.5F);
	}

	static void loadVanillaRecipes() {
		// ゆでたまご
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.bakedApple, 1, 1), DCHeatTier.BOIL,
				DCHumidity.UNDERWATER, null, false, new ItemStack(Items.EGG, 1, 0));

	}

	static void registerClimate() {
		// climate
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.lampCarbide, 32767, DCHeatTier.WARM);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.lampGas, 32767, DCHeatTier.WARM);
	}

}
