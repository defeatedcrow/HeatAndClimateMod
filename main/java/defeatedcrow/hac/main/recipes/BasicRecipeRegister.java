package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.api.module.HaCModule;
import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.MainUtil;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BasicRecipeRegister {

	public static void load() {
		LoadingToolRecipe.add(RecipeResourcesMain.MAIN);
		LoadingYagenRecipe.add(RecipeResourcesMain.MAIN);
		LoadingOreRecipe.add(RecipeResourcesMain.MAIN);
		LoadingBuildingRecipe.add(RecipeResourcesMain.MAIN);
		LoadingBuildingRecipe.advanced(RecipeResourcesMain.MAIN);
		LoadingEquipRecipe.add(RecipeResourcesMain.MAIN);
		loadFoodRecipes(RecipeResourcesMain.MAIN);
		loadAnotherRecipes(RecipeResourcesMain.MAIN);
		if (ModuleConfig.build_advanced) {
			LoadingContRecipe.add(RecipeResourcesMain.MAIN);
		}
		DCInfoDataRegister.registerInfo();
	}

	static void loadFoodRecipes(RecipeResourcesMain res) {
		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", new ItemStack(MainInit.animalFeed, 4, 0), new Object[] {
				"listAllgrain", "dustBran", "dustPresscake",
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", 2, new ItemStack(MainInit.animalFeed, 4, 0), new Object[] {
				"listAllgrain", "dustDraff", "dustPresscake",
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", 3, new ItemStack(MainInit.animalFeed, 4, 0), new Object[] {
				"listAllgrain", "dustPlant", "dustPresscake",
		});

		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(MainInit.animalFeed, 1, 1), new Object[] {
				"XX", "XX", 'X', "blockTallgrass"
		});

		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", 2, new ItemStack(MainInit.animalFeed, 1, 1), new Object[] {
				"XX", "XX", 'X', "dustBaggase"
		});
	}

	static void loadAnotherRecipes(RecipeResourcesMain res) {
		// 火打ち石のアナザー
		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", new ItemStack(Items.FLINT_AND_STEEL, 1, 0), new Object[] {
				new ItemStack(MainInit.oreItem, 1, 3), "gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", 2, new ItemStack(Items.FLINT_AND_STEEL, 1, 0), new Object[] {
				new ItemStack(MainInit.oreItem, 1, 3), new ItemStack(Items.FLINT)
		});

		// 矢
		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Items.ARROW, 8, 0), new Object[] {
				"X", "Y", "Z", 'X', "gemChalcedony", 'Y', "stickWood", 'Z', "feather"
		});

		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", 2, new ItemStack(Items.ARROW, 4, 0), new Object[] {
				"X", "Y", "Z", 'X', "gemChalcedony", 'Y', "stickWood", 'Z', "paper"
		});

		// ブレイズロッド救済用
		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Items.BLAZE_ROD, 1, 0), new Object[] {
				"XXX", "YYY", "ZZZ", 'X', "dustCrystal", 'Y', "dustBlaze", 'Z', "dustSulfur"
		});

		// 棒の処理
		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Blocks.PLANKS, 1, 0), new Object[] {
				"XX", "XX", 'X', "stickWood"
		});

		// 変換
		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", new ItemStack(Items.DYE, 1, 15), new Object[] {
				new ItemStack(MainInit.miscDust, 1, 5)
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", 2, new ItemStack(Items.DYE, 1, 15), new Object[] {
				new ItemStack(MainInit.miscDust, 1, 4)
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", 2, new ItemStack(MainInit.oreIngot, 1, 5), new Object[] {
				new ItemStack(MainInit.oreIngot, 1, 7)
		});

		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Blocks.GLASS, 8, 0), new Object[] {
				"XXX", "XYX", "XXX", 'X', new ItemStack(Blocks.STAINED_GLASS, 1, 32767), 'Y', new ItemStack(MainInit.repairPutty, 1, 2)
		});

		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Blocks.HARDENED_CLAY, 8, 0), new Object[] {
				"XXX", "XYX", "XXX", 'X', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 32767), 'Y', new ItemStack(MainInit.repairPutty, 1, 2)
		});

		for (int i = 0; i < 16; i++) {
			DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Blocks.STAINED_HARDENED_CLAY, 8, i), new Object[] {
					"XXX", "XYX", "XXX", 'X', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0), 'Y', MainUtil.DYES[i]
			});
		}

		// 土
		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", new ItemStack(Blocks.DIRT, 1, 0), new Object[] {
				new ItemStack(Blocks.SAND, 1, 32767), new ItemStack(Items.DYE, 1, 15), "dustAsh", "dustPresscake",
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", 2, new ItemStack(Blocks.DIRT, 1, 0), new Object[] {
				new ItemStack(Blocks.SAND, 1, 32767), new ItemStack(Items.DYE, 1, 15), "dustAsh", "dustPlant",
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", new ItemStack(Blocks.MYCELIUM, 1, 0), new Object[] {
				new ItemStack(Blocks.DIRT, 1, 0), "dustBran", "dustPlant"
		});

		// ウール
		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Blocks.WOOL, 1, 0), new Object[] {
				"XXX", "XXX", "XXX", 'X', "dustChrysotile"
		});

		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", 2, new ItemStack(Blocks.WOOL, 1, 0), new Object[] {
				"XXX", "XXX", "XXX", 'X', "cropCotton"
		});

		// 紙
		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Items.PAPER, 3, 0), new Object[] {
				"X", "X", "X", 'X', "dustWood"
		});

		// アルミ関連
		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Items.BUCKET, 1, 0), new Object[] {
				"X X", " X ", 'X', "ingotAluminum"
		});

		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Blocks.PISTON, 1, 0), new Object[] {
				"XXX", "YZY", "YWY", 'X', "plankWood", 'Y', "cobblestone", 'Z', "ingotAluminum", 'W', "dustRedstone"
		});

		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Blocks.HOPPER, 1, 0), new Object[] {
				"X X", "XYX", " X ", 'X', "ingotAluminum", 'Y', new ItemStack(Blocks.CHEST, 1, 0)
		});

		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Blocks.TRIPWIRE_HOOK, 1, 0), new Object[] {
				"X", "Y", "Z", 'X', "ingotAluminum", 'Y', "stickWood", 'Z', "plankWood"
		});

		// libと同内容
		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", new ItemStack(Items.GUNPOWDER, 4, 0), new Object[] {
				"dustGraphite", "dustGraphite", "dustSulfur", "dustNiter"
		});

		DCRecipe.jsonShapedRecipe(HaCModule.CORE, "main", new ItemStack(Items.COMPASS, 1, 0), new Object[] {
				" X ", "XYX", " X ", 'X', "ingotIron", 'Y', "oreMagnetite"
		});

		// なんでバニラにねぇのこれ
		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main", new ItemStack(Items.PRISMARINE_SHARD, 4, 0), new Object[] {
				new ItemStack(Blocks.PRISMARINE, 1, 0)
		});
	}
}
