package defeatedcrow.hac.main.recipes;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.ClimateSmelting;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.recipes.device.RegisterBrewingDC;
import defeatedcrow.hac.main.recipes.device.RegisterCrusherRecipe;
import defeatedcrow.hac.main.recipes.device.RegisterFluidRecipe;
import defeatedcrow.hac.main.recipes.device.RegisterMillRecipe;
import defeatedcrow.hac.main.recipes.device.RegisterReactorRecipe;
import defeatedcrow.hac.main.recipes.device.RegisterSpinningRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MachineRecipeRegister {

	public static void load() {

		if (ModuleConfig.r_climate) {
			loadOreRecipes();
			loadContRecipes();
			loadBuildRecipes();
			loadVanillaRecipes();
		}

		loadSmelting();
		registerClimate();

		if (ModuleConfig.machine) {
			loadMachineClimateRecipe();
		}

		if (ModuleConfig.r_mill) {
			RegisterMillRecipe.load();
		}

		if (ModuleConfig.r_spinning) {
			RegisterSpinningRecipe.load();
		}

		if (ModuleConfig.r_crusher) {
			RegisterCrusherRecipe.load();
		}

		if (ModuleConfig.r_reactor) {
			RegisterReactorRecipe.load();
		}

		if (ModuleConfig.r_fluid) {
			RegisterFluidRecipe.load();
		}

		if (ModuleConfig.r_brewing) {
			RegisterBrewingDC.load();
		}

	}

	static void loadOreRecipes() {
		// 金属精錬。ごく一部はKILNでも焼けるが、ほとんどがSMELTを要求する。
		// copper
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockNew, 1,
				1), DCHeatTier.KILN, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 0));
		// zinc
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockNew, 1,
				0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 1));

		// nickel
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockNew, 1,
				5), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 2));

		// silver
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockNew, 1,
				6), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 3));

		// brass
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockAlloy, 1,
				0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 4));

		// nickelsilver
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockAlloy, 1,
				3), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 6));

		// magnet
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockAlloy, 1,
				6), DCHeatTier.UHT, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 7));

		// tin
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockNew, 1,
				2), DCHeatTier.KILN, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 8));

		// bronze
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockAlloy, 1,
				1), DCHeatTier.KILN, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 9));

		// gold
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(Blocks.GOLD_BLOCK, 1,
				0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 11));

		// aluminium
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockNew, 1,
				4), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 13));

		// bismuth
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockNew, 1,
				7), DCHeatTier.KILN, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 14));

		// bscco
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockAlloy, 1,
				8), DCHeatTier.UHT, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 15));

		// 熱処理
		if (MainCoreConfig.steel_hardmode) {

			GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 5), new ItemStack(MainInit.heatedMetalBlock,
					1, 2), 0.3F);
			GameRegistry.addSmelting(new ItemStack(Blocks.IRON_BLOCK, 1, 0), new ItemStack(MainInit.heatedMetalBlock, 1,
					2), 0F);

			IClimate c1 = ClimateAPI.register
					.getClimateFromParam(DCHeatTier.SMELTING, DCHumidity.NORMAL, DCAirflow.NORMAL);
			IClimate c2 = ClimateAPI.register
					.getClimateFromParam(DCHeatTier.NORMAL, DCHumidity.UNDERWATER, DCAirflow.TIGHT);
			IClimate c3 = ClimateAPI.register.getClimateFromParam(DCHeatTier.OVEN, DCHumidity.NORMAL, DCAirflow.TIGHT);
			IClimate c4 = ClimateAPI.register.getClimateFromParam(DCHeatTier.UHT, DCHumidity.NORMAL, DCAirflow.TIGHT);

			List<ItemStack> l1 = Lists.newArrayList();
			l1.add(new ItemStack(MainInit.dustBlock, 1, 5));
			l1.add(new ItemStack(MainInit.heatedMetalBlock, 1, 2));
			HeatTreatment r1 = new HeatTreatment(l1, c1, new ItemStack(MainInit.heatedMetalBlock, 1, 0), c2,
					new ItemStack(MainInit.heatedMetalBlock, 1, 1), c3, new ItemStack(MainInit.metalBlockAlloy, 1, 2),
					new ItemStack(MainInit.heatedMetalBlock, 1, 2));
			r1.temp1.add(DCHeatTier.UHT);
			r1.temp2.add(DCHeatTier.COOL);
			r1.hum1.add(DCHumidity.DRY);
			r1.hum2.add(DCHumidity.WET);
			r1.hum3.add(DCHumidity.DRY);
			r1.air1.add(DCAirflow.TIGHT);
			r1.air2.add(DCAirflow.NORMAL);
			r1.air3.add(DCAirflow.NORMAL);
			MainAPIManager.heatTreatmentRegister.registerRecipe(r1);

			List<ItemStack> l2 = Lists.newArrayList();
			l2.add(new ItemStack(MainInit.dustBlock, 1, 10));
			l2.add(new ItemStack(MainInit.heatedMetalBlock, 1, 5));
			HeatTreatment r2 = new HeatTreatment(l2, c4, new ItemStack(MainInit.heatedMetalBlock, 1, 3), c2,
					new ItemStack(MainInit.heatedMetalBlock, 1, 4), c3, new ItemStack(MainInit.metalBlockAlloy, 1, 5),
					new ItemStack(MainInit.heatedMetalBlock, 1, 5));
			r2.temp1.add(DCHeatTier.INFERNO);
			r2.temp2.add(DCHeatTier.COOL);
			r2.hum1.add(DCHumidity.DRY);
			r2.hum2.add(DCHumidity.WET);
			r2.hum3.add(DCHumidity.DRY);
			r2.air1.add(DCAirflow.NORMAL);
			r2.air2.add(DCAirflow.NORMAL);
			r2.air3.add(DCAirflow.NORMAL);
			MainAPIManager.heatTreatmentRegister.registerRecipe(r2);

			List<ItemStack> l3 = Lists.newArrayList();
			l3.add(new ItemStack(MainInit.dustBlock_2, 1, 2));
			l3.add(new ItemStack(MainInit.heatedMetalBlock, 1, 11));
			HeatTreatment r3 = new HeatTreatment(l3, c4, new ItemStack(MainInit.heatedMetalBlock, 1, 9), c2,
					new ItemStack(MainInit.heatedMetalBlock, 1, 10), c3, new ItemStack(MainInit.metalBlockAlloy, 1, 4),
					new ItemStack(MainInit.heatedMetalBlock, 1, 11));
			r3.temp1.add(DCHeatTier.INFERNO);
			r3.temp2.add(DCHeatTier.COOL);
			r3.hum1.add(DCHumidity.DRY);
			r3.hum2.add(DCHumidity.WET);
			r3.hum3.add(DCHumidity.DRY);
			r3.air1.add(DCAirflow.NORMAL);
			r3.air2.add(DCAirflow.NORMAL);
			r3.air3.add(DCAirflow.NORMAL);
			MainAPIManager.heatTreatmentRegister.registerRecipe(r3);

			List<ItemStack> l4 = Lists.newArrayList();
			l4.add(new ItemStack(MainInit.dustBlock, 1, 12));
			l4.add(new ItemStack(MainInit.heatedMetalBlock, 1, 8));
			HeatTreatment r4 = new HeatTreatment(l4, c4, new ItemStack(MainInit.heatedMetalBlock, 1, 6), c2,
					new ItemStack(MainInit.heatedMetalBlock, 1, 7), c3, new ItemStack(MainInit.metalBlockAlloy, 1, 7),
					new ItemStack(MainInit.heatedMetalBlock, 1, 8));
			r4.temp1.add(DCHeatTier.INFERNO);
			r4.temp2.add(DCHeatTier.COOL);
			r4.hum1.add(DCHumidity.DRY);
			r4.hum2.add(DCHumidity.WET);
			r4.hum3.add(DCHumidity.DRY);
			r4.air1.add(DCAirflow.NORMAL);
			r4.air2.add(DCAirflow.NORMAL);
			r4.air3.add(DCAirflow.NORMAL);
			MainAPIManager.heatTreatmentRegister.registerRecipe(r4);

			List<ItemStack> l5 = Lists.newArrayList();
			l5.add(new ItemStack(MainInit.dustBlock_2, 1, 3));
			l5.add(new ItemStack(MainInit.heatedMetalBlock, 1, 14));
			HeatTreatment r5 = new HeatTreatment(l5, c4, new ItemStack(MainInit.heatedMetalBlock, 1, 12), c2,
					new ItemStack(MainInit.heatedMetalBlock, 1, 13), c1, new ItemStack(MainInit.metalBlockAlloy, 1, 9),
					new ItemStack(MainInit.heatedMetalBlock, 1, 14));
			r5.temp1.add(DCHeatTier.INFERNO);
			r5.temp2.add(DCHeatTier.COOL);
			r5.hum1.add(DCHumidity.DRY);
			r5.hum2.add(DCHumidity.WET);
			r5.hum2.add(DCHumidity.NORMAL);
			r5.hum3.add(DCHumidity.DRY);
			r5.air1.add(DCAirflow.NORMAL);
			r5.air2.add(DCAirflow.NORMAL);
			r5.air2.add(DCAirflow.FLOW);
			r5.air3.add(DCAirflow.FLOW);
			r5.air3.add(DCAirflow.WIND);
			MainAPIManager.heatTreatmentRegister.registerRecipe(r5);

		} else {

			GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 5), new ItemStack(Blocks.IRON_BLOCK, 1,
					0), 0.3F);

			// steel
			if (MainCoreConfig.steel) {
				RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockAlloy, 1,
						2), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 5));
				// iron
				RecipeAPI.registerSmelting.addRecipe(new ItemStack(Blocks.IRON_BLOCK, 1, 0), ImmutableList
						.of(DCHeatTier.KILN), null, DCAirflow.TIGHT, new ItemStack(MainInit.dustBlock, 1, 5));
			} else {
				RecipeAPI.registerSmelting.addRecipe(new ItemStack(Blocks.IRON_BLOCK, 1,
						0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 5));
			}

			// sus
			RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockAlloy, 1,
					5), DCHeatTier.UHT, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 10));

			// toolsteel
			RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockAlloy, 1,
					4), DCHeatTier.UHT, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock_2, 1, 2));

			// titanium
			RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockAlloy, 1,
					7), DCHeatTier.UHT, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock, 1, 12));

			// mangalloy
			RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlockAlloy, 1,
					9), DCHeatTier.UHT, null, DCAirflow.TIGHT, false, new ItemStack(MainInit.dustBlock_2, 1, 3));
		}

	}

	static void loadContRecipes() {
		// コンテナ類の自然変化
		// log
		for (int i = 0; i < 6; i++) {
			ClimateSmelting recipe = new ClimateSmelting(new ItemStack(MainInit.logCont, 1, 6), null, DCHeatTier.KILN,
					null, DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.logCont, 1, i));
			RecipeAPI.registerSmelting.addRecipe(recipe);
		}

		// burnt crops
		ClimateSmelting apple = new ClimateSmelting(new ItemStack(MainInit.cropCont, 1, 9), null, DCHeatTier.OVEN, null,
				DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.cropCont, 1, 0));
		apple.requiredAir().add(DCAirflow.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(apple);

		ClimateSmelting potato = new ClimateSmelting(new ItemStack(MainInit.cropCont, 1, 10), null, DCHeatTier.OVEN,
				null, DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.cropCont, 1, 1));
		potato.requiredAir().add(DCAirflow.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(potato);

		// 焼くと骨に、煮ると皮になる
		ClimateSmelting flesh1 = new ClimateSmelting(new ItemStack(MainInit.dropCont, 1, 1), null, DCHeatTier.KILN,
				DCHumidity.DRY, null, 0, false, new ItemStack(MainInit.dropCont, 1, 0));
		flesh1.requiredHum().add(DCHumidity.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(flesh1);

		ClimateSmelting flesh2 = new ClimateSmelting(new ItemStack(MainInit.miscCont, 1, 2), null, DCHeatTier.KILN,
				DCHumidity.WET, null, 0, false, new ItemStack(MainInit.dropCont, 1, 0));
		flesh2.requiredHum().add(DCHumidity.UNDERWATER);
		RecipeAPI.registerSmelting.addRecipe(flesh2);

		// AMTにもあった、火薬と粘土の互換
		ClimateSmelting powder = new ClimateSmelting(new ItemStack(MainInit.miscCont, 1, 0), null, DCHeatTier.HOT,
				DCHumidity.WET, null, 0, false, new ItemStack(MainInit.dropCont, 1, 4));
		powder.requiredHum().add(DCHumidity.UNDERWATER);
		RecipeAPI.registerSmelting.addRecipe(powder);

		ClimateSmelting clay = new ClimateSmelting(new ItemStack(MainInit.dropCont, 1, 4), null, DCHeatTier.HOT,
				DCHumidity.DRY, DCAirflow.NORMAL, 0, false, new ItemStack(MainInit.miscCont, 1, 0));
		clay.requiredAir().add(DCAirflow.FLOW);
		clay.requiredAir().add(DCAirflow.WIND);
		RecipeAPI.registerSmelting.addRecipe(clay);

		for (int i = 0; i < 6; i++) {
			if (i != 2) {
				ClimateSmelting recipe = new ClimateSmelting(new ItemStack(Items.COAL, 1, 1), null, DCHeatTier.KILN,
						null, DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.dustCake, 1, i));
				RecipeAPI.registerSmelting.addRecipe(recipe);
			}
		}

	}

	static void loadBuildRecipes() {
		// 建材
		ClimateSmelting dirt = new ClimateSmelting(new ItemStack(MainInit.dustBlock_2, 1, 0), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0, false, new ItemStack(Blocks.DIRT, 1, 0));
		RecipeAPI.registerSmelting.addRecipe(dirt);

		ClimateSmelting dirt2 = new ClimateSmelting(new ItemStack(Blocks.SAND, 1, 0), null, DCHeatTier.SMELTING,
				DCHumidity.DRY, null, 0, false, new ItemStack(MainInit.dustBlock_2, 1, 0));
		RecipeAPI.registerSmelting.addRecipe(dirt2);

		ClimateSmelting dirt3 = new ClimateSmelting(new ItemStack(Blocks.DIRT, 1, 0), null, DCHeatTier.WARM,
				DCHumidity.WET, null, 0, false, new ItemStack(MainInit.dustBlock_2, 1, 0));
		dirt3.requiredHum().add(DCHumidity.UNDERWATER);
		RecipeAPI.registerSmelting.addRecipe(dirt3);

		ClimateSmelting clay = new ClimateSmelting(new ItemStack(MainInit.miscDust, 1, 13), null, DCHeatTier.UHT, null,
				null, 0, false, new ItemStack(Items.CLAY_BALL, 1, 0));
		RecipeAPI.registerSmelting.addRecipe(clay);
	}

	// machine
	static void loadMachineClimateRecipe() {
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MachineInit.fuelCont, 1,
				0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false, "blockCoal");

		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MachineInit.fuelCont, 1,
				1), DCHeatTier.UHT, DCHumidity.DRY, DCAirflow.TIGHT, false, "blockFuelCoke");
	}

	static void loadSmelting() {
		// KILNはバニラカマドに対応する
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 0), new ItemStack(MainInit.metalBlockNew, 1,
				1), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 1), new ItemStack(MainInit.metalBlockNew, 1,
				0), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 4), new ItemStack(MainInit.metalBlockAlloy, 1,
				0), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 8), new ItemStack(MainInit.metalBlockNew, 1,
				2), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 9), new ItemStack(MainInit.metalBlockAlloy, 1,
				1), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 14), new ItemStack(MainInit.metalBlockNew, 1,
				7), 0.3F);

		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 0), new ItemStack(MainInit.oreIngot, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 1), new ItemStack(MainInit.oreIngot, 1, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 4), new ItemStack(Items.GOLD_INGOT, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 5), new ItemStack(Items.IRON_INGOT, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 8), new ItemStack(MainInit.oreIngot, 1, 8), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 12), new ItemStack(MainInit.oreIngot, 1, 13), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 13), new ItemStack(MainInit.oreIngot, 1, 15), 0.1F);

		GameRegistry.addSmelting(new ItemStack(MainInit.gemBlock, 1, 3), new ItemStack(MainInit.selenite, 1, 0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock_2, 1, 1), new ItemStack(MainInit.selenite, 1,
				3), 0.15F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock_2, 1, 0), new ItemStack(Items.CLAY_BALL, 1,
				0), 0.15F);

		GameRegistry.addSmelting(new ItemStack(MainInit.foodDust, 1, 1), new ItemStack(MainInit.miscDust, 1, 5), 0.15F);

		// gems
		GameRegistry.addSmelting(new ItemStack(Items.FLINT, 1, 0), new ItemStack(MainInit.gems_red, 1, 0), 0.2F);

		GameRegistry.addSmelting(new ItemStack(MainInit.miscDust, 1, 1), new ItemStack(Blocks.GLASS, 1, 0), 0.2F);

		GameRegistry.addSmelting(new ItemStack(MainInit.layerNew, 1, 1), new ItemStack(MainInit.gemBlock, 1, 6), 0.2F);

		// foods

		GameRegistry.addSmelting(new ItemStack(Items.APPLE, 1, 0), new ItemStack(MainInit.bakedApple, 1, 0), 0F);

		GameRegistry.addSmelting(new ItemStack(FoodInit.meat, 1, 2), new ItemStack(MainInit.bakedApple, 1, 4), 0.5F);

		GameRegistry.addSmelting(new ItemStack(FoodInit.meat, 1, 9), new ItemStack(MainInit.bakedApple, 1, 7), 0.5F);

		GameRegistry.addSmelting(new ItemStack(MainInit.cropCont, 1, 0), new ItemStack(MainInit.cropCont, 1, 9), 0.5F);
		GameRegistry.addSmelting(new ItemStack(MainInit.cropCont, 1, 1), new ItemStack(MainInit.cropCont, 1, 10), 0.5F);

		for (int i = 0; i < 6; i++) {
			GameRegistry.addSmelting(new ItemStack(MainInit.logCont, 1, i), new ItemStack(MainInit.logCont, 1, 6), 0F);
		}

		for (int i = 0; i < 6; i++) {
			if (i != 2)
				GameRegistry.addSmelting(new ItemStack(MainInit.dustCake, 1, i), new ItemStack(Items.COAL, 1, 1), 0F);
		}

		// misc

		GameRegistry.addSmelting(new ItemStack(MainInit.desiccant, 1, 3), new ItemStack(MainInit.desiccant, 1, 0), 0F);
	}

	static void loadVanillaRecipes() {
		// ゆでたまご
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.bakedApple, 1,
				1), DCHeatTier.BOIL, DCHumidity.UNDERWATER, null, false, "egg");

	}

	static void registerClimate() {
		// climate
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.lampCarbide, 32767, DCHeatTier.WARM);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.lampGas, 32767, DCHeatTier.WARM);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.oilLamp, 32767, DCHeatTier.WARM);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.geyser, 32767, DCHeatTier.OVEN);
	}

}
