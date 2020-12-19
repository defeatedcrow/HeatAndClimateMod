package defeatedcrow.hac.main.recipes.device;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class RegisterReactorRecipe {

	public static void load() {

		// Mainのみ
		// 磁鉄鉱 ハーバーボッシュ
		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.ammonia,
				500), null, DCHeatTier.KILN, "oreMagnetite", new FluidStack(MainInit.hydrogen,
						1000), null, new Object[] {});

		// 触媒なし
		// 硝酸2
		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.nitricAcid,
				200), null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
						200), null, new Object[] { "dustNiter" });

		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.nitricAcid,
				200), null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
						200), null, new Object[] { "dustSaltpeter" });

		// 硫酸
		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.sulfuricAcid,
				200), null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(FluidRegistry.WATER,
						200), null, new Object[] { "dustNiter", "dustSulfur" });

		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.sulfuricAcid,
				200), null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(FluidRegistry.WATER,
						200), null, new Object[] { "dustSaltpeter", "dustSulfur" });

		// 硝石
		RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.miscDust, 1,
				6), null, 0, null, null, DCHeatTier.OVEN, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
						200), null, new Object[] { "dustAlkali" });

		// カーバイド
		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.fuelGas,
				1000), null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(FluidRegistry.WATER,
						200), null, new Object[] { "gemCarbide" });

		// バニラ要素
		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(FluidRegistry.LAVA,
				200), null, DCHeatTier.UHT, (ItemStack) null, null, null, new Object[] { "dustSulfur", "cobblestone" });

		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(FluidRegistry.LAVA,
				250), null, DCHeatTier.UHT, (ItemStack) null, null, null, new Object[] { "dustBlaze", "cobblestone" });

		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(FluidRegistry.LAVA,
				1000), null, DCHeatTier.UHT, (ItemStack) null, null, null, new Object[] {
					"stickBlaze",
					"cobblestone" });

		RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(
				Blocks.ICE), null, 0, null, null, DCHeatTier.FROSTBITE, (ItemStack) null, new FluidStack(
						FluidRegistry.WATER, 1000), null, new Object[] {});

		RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(
				Blocks.OBSIDIAN), null, 0, null, null, DCHeatTier.FROSTBITE, (ItemStack) null, new FluidStack(
						FluidRegistry.LAVA, 1000), null, new Object[] {});

		// 液体窒素
		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.nitrogen,
				1000), new FluidStack(MainInit.oxygen,
						500), DCHeatTier.ABSOLUTE, (ItemStack) null, null, null, new Object[] { "dustCoal" });

		RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.nitrogen,
				1000), new FluidStack(MainInit.oxygen,
						500), DCHeatTier.ABSOLUTE, (ItemStack) null, null, null, new Object[] {
							new ItemStack(Items.COAL, 1, 1) });

		// 丸石生成
		RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(
				Blocks.STONE), null, 0, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(
						FluidRegistry.WATER, 100), new FluidStack(FluidRegistry.LAVA, 100), new Object[] {});

		// 合成宝石
		RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(Items.QUARTZ, 1,
				0), null, 0F, null, null, DCHeatTier.UHT, "gemQuartz", null, null, new Object[] { "dustCrystal" });

		RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.gems_blue, 1,
				1), null, 0F, null, null, DCHeatTier.UHT, "gemSapphire", null, null, new Object[] {
					"dustAluminum",
					"dustChromium",
					"dustIron" });

		RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.gems_red, 1,
				1), null, 0F, null, null, DCHeatTier.UHT, "gemGarnet", null, null, new Object[] {
					"dustCrystal",
					"dustAluminum",
					"dustIron" });

		RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.gems_white, 1,
				4), null, 0F, null, null, DCHeatTier.UHT, "gemDiamond", null, null, new Object[] {
					"dustTitanium",
					"dustBlaze",
					"gemCelestite" });

		if (ModuleConfig.food) {
			// Ni 水蒸気添加
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
					3), null, 1.0F, null, null, DCHeatTier.OVEN, (ItemStack) null, new FluidStack(MainInit.oil,
							200), new FluidStack(FluidRegistry.WATER, 200), new Object[] { "dustSalt" });
		}

		if (ModuleConfig.machine) {
			// new ores 浮上選鉱
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 5), new ItemStack(
					MainInit.oreItem, 1, 10), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] { "oreRed", "soap" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 6), new ItemStack(
					MainInit.oreItem, 1, 11), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] { "oreGreen", "soap" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 7), new ItemStack(
					MainInit.oreItem, 1, 12), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] { "oreBlue", "soap" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 8), new ItemStack(
					MainInit.oreItem, 1, 13), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] { "oreWhite", "soap" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 9), new ItemStack(
					MainInit.oreItem, 1, 14), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] { "oreBlack", "soap" });
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 10), new ItemStack(
					MainInit.gems_red, 1, 2), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] { "oreLargeRed", "soap" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 11), new ItemStack(
					MainInit.gems_green, 1, 2), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] { "oreLargeGreen", "soap" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 12), new ItemStack(
					MainInit.gems_blue, 1, 2), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] { "oreLargeBlue", "soap" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 13), new ItemStack(
					MainInit.gems_white, 1, 2), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] { "oreLargeWhite", "soap" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 14), new ItemStack(
					MainInit.gems_black, 1, 2), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] { "oreLargeBlack", "soap" });

			// Ni-Al 水蒸気改質系
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					1), null, 0, new FluidStack(MainInit.fuelOil, 500), null, DCHeatTier.KILN, new ItemStack(
							MachineInit.catalyst, 1, 0), new FluidStack(MainInit.oil, 500), new FluidStack(
									FluidRegistry.WATER, 500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 0), new ItemStack(
					MainInit.miscDust, 1, 7), 0.5F, new FluidStack(MainInit.fuelOil,
							500), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(
									MainInit.blackLiquor, 500), new FluidStack(FluidRegistry.WATER,
											500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 0), new ItemStack(
					MachineInit.reagent, 1, 14), 0.5F, new FluidStack(MainInit.fuelOil,
							200), null, DCHeatTier.OVEN, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(
									FluidRegistry.WATER, 500), null, new Object[] { "gemCoal" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 7), new ItemStack(
					MainInit.miscDust, 1, 7), 0.5F, new FluidStack(MainInit.fuelOil,
							400), null, DCHeatTier.OVEN, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(
									FluidRegistry.WATER, 1000), null, new Object[] {
										new ItemStack(MachineInit.reagent, 1, 0) });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.miscDust, 1, 13), null, 0, new FluidStack(
					MainInit.fuelOil, 400), new FluidStack(MainInit.fuelGas, 400), DCHeatTier.OVEN, new ItemStack(
							MachineInit.catalyst, 1, 0), null, null, new Object[] { "gemCrudeOil" });

			// Pt 炭化水素ガス
			RecipeAPI.registerReactorRecipes.addRecipe(null, new ItemStack(MainInit.miscDust, 1,
					7), 0.25F, new FluidStack(MainInit.fuelGas, 1000), null, DCHeatTier.SMELTING, new ItemStack(
							MachineInit.catalyst, 1, 3), new FluidStack(MainInit.fuelOil, 500), new FluidStack(
									FluidRegistry.WATER, 500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(null, new ItemStack(MainInit.miscDust, 1,
					7), 0.25F, new FluidStack(MainInit.fuelGas, 2000), null, DCHeatTier.SMELTING, new ItemStack(
							MachineInit.catalyst, 1, 3), new FluidStack(MainInit.fuelOil, 500), new FluidStack(
									MainInit.hydrogen, 500), new Object[] {});

			// Pt アセチレン合成
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					14), null, 0, null, null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1,
							3), new FluidStack(MainInit.fuelGas, 500), null, new Object[] {});

			// Au or Ni 水素ガス
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.hydrogen,
					200), new FluidStack(MainInit.oxygen, 200), DCHeatTier.NORMAL, new ItemStack(Items.GOLD_INGOT, 1,
							0), new FluidStack(FluidRegistry.WATER, 1000), null, new Object[] { "dustAlkali" });

			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.hydrogen,
					1000), null, DCHeatTier.SMELTING, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(
							MainInit.fuelGas, 500), new FluidStack(FluidRegistry.WATER, 500), new Object[] {});

			// Bz エタノール脱水
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.fuelGas,
					1000), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
							MainInit.ethanol, 1000), new FluidStack(MainInit.sulfuricAcid, 200), new Object[] {});

			// Pt オストワルト
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.nitricAcid,
					1000), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
							MainInit.ammonia, 1000), new FluidStack(FluidRegistry.WATER, 1000), new Object[] {});

			// Bi ソハイオ
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					2), null, 0, null, null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 2), new FluidStack(
							MainInit.fuelGas, 500), new FluidStack(MainInit.ammonia, 500), new Object[] {});

			// 触媒なし
			// 硝安
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					4), null, 0, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							200), new FluidStack(MainInit.ammonia, 200), new Object[] {});

			// 硫酸2
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.sulfuricAcid,
					500), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
							FluidRegistry.WATER, 500), null, new Object[] { "dustSulfur" });

			// アルカリ1
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					3), null, 0, null, null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(FluidRegistry.WATER,
							500), null, new Object[] { "dustSalt" });

			// アルカリ2
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					3), null, 0, null, null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(FluidRegistry.WATER,
							500), null, new Object[] { "dustLime" });

			// ニトロ
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					5), null, 0, null, null, DCHeatTier.HOT, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							200), new FluidStack(MainInit.sulfuricAcid, 200), new Object[] { "cropCotton" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					6), null, 0, null, null, DCHeatTier.HOT, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							200), new FluidStack(MainInit.sulfuricAcid, 200), new Object[] {
								new ItemStack(MachineInit.reagent, 1, 1) });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(Blocks.TNT, 1,
					0), null, 0, null, null, DCHeatTier.HOT, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							500), new FluidStack(MainInit.sulfuricAcid, 200), new Object[] {
								new ItemStack(MachineInit.reagent, 1, 14),
								"sand",
								"paper" });

			// コークス
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 13), new ItemStack(
					MachineInit.reagent, 1,
					0), 0.5F, null, null, DCHeatTier.KILN, (ItemStack) null, null, null, new Object[] { "gemCoal" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 13), new ItemStack(
					MachineInit.reagent, 1,
					0), 0.5F, null, null, DCHeatTier.KILN, (ItemStack) null, null, null, new Object[] {
						new ItemStack(Items.COAL, 1, 1),
						new ItemStack(Items.COAL, 1, 1) });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 4, 13), new ItemStack(
					MachineInit.reagent, 1,
					0), 1.0F, null, null, DCHeatTier.KILN, (ItemStack) null, null, null, new Object[] {
						new ItemStack(MainInit.logCont, 1, 6) });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.fuelCont, 1, 0), new ItemStack(
					MachineInit.reagent, 4,
					0), 1F, null, null, DCHeatTier.KILN, (ItemStack) null, null, null, new Object[] { "blockCoal" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 13), new ItemStack(
					MainInit.miscDust, 1,
					7), 0.5F, null, null, DCHeatTier.SMELTING, (ItemStack) null, null, null, new Object[] {
						new ItemStack(MachineInit.reagent, 1, 0) });

			// カーバイド
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					9), null, 0, null, null, DCHeatTier.UHT, (ItemStack) null, null, null, new Object[] {
						"fuelCoke",
						"dustLime" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.fuelCont, 1,
					1), null, 0, null, null, DCHeatTier.UHT, (ItemStack) null, null, null, new Object[] {
						"blockFuelCoke",
						"dustLime" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					12), null, 0, null, null, DCHeatTier.OVEN, (ItemStack) null, new FluidStack(MainInit.ammonia,
							100), null, new Object[] { new ItemStack(MachineInit.reagent, 1, 11) });

			// plating
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					0), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							100), new FluidStack(FluidRegistry.WATER, 100), new Object[] {
								"dustChromium",
								"dustZinc",
								"dustAlkali" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					1), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							100), null, new Object[] {
								"dustNickel",
								new ItemStack(MachineInit.reagent, 1, 11),
								"dustAlkali" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					2), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							100), null, new Object[] { "dustZinc", "dustIron", "dustAlkali" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					3), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							100), new FluidStack(MainInit.ammonia, 100), new Object[] { "dustSilver", "dustAlkali" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					4), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							100), null, new Object[] {
								"dustBismuth",
								"dustTin",
								new ItemStack(MachineInit.reagent, 1, 11),
								"dustAlkali" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					5), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							100), null, new Object[] { "dustBlaze", "dustGold", "dustAlkali" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					6), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.fuelOil,
							100), null, new Object[] { new ItemStack(Items.SPIDER_EYE, 1, 0), "dustAlkali" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					7), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, null, null, new Object[] {
						new ItemStack(MainInit.miscDust, 1, 10),
						new ItemStack(MachineInit.reagent, 1, 12),
						"paper" });

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					8), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.fuelOil,
							100), new FluidStack(FluidRegistry.WATER, 100), new Object[] {
								new ItemStack(MachineInit.reagent, 1, 2) });

			// food
			if (ModuleConfig.food) {
				// Ni 水蒸気添加
				RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
						4), null, 1.0F, null, null, DCHeatTier.OVEN, new ItemStack(MachineInit.catalyst, 1,
								0), new FluidStack(MainInit.oil, 200), new FluidStack(MainInit.hydrogen,
										100), new Object[] {});
			}

		}

	}

}
