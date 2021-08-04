package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.api.module.HaCModule;
import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class LoadingYagenRecipe {

	public static void add(RecipeResourcesMain res) {
		addOldOre();
		addNewOre();
	}

	static void addOldOre() {
		// ore -> dust
		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1, 0), new Object[] {
				"toolNormalYagen",
				"oreCopper"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1, 1), new Object[] {
				"toolNormalYagen",
				"oreZinc"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1, 2), new Object[] {
				"toolNormalYagen",
				"oreNickel"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1, 3), new Object[] {
				"toolNormalYagen",
				"oreSilver"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1, 4), new Object[] {
				"toolNormalYagen",
				"oreGold"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1, 5), new Object[] {
				"toolNormalYagen",
				"oreIron"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1, 7), new Object[] {
				"toolNormalYagen",
				"oreMagnetite"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1, 8), new Object[] {
				"toolNormalYagen",
				"oreTin"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1, 9), new Object[] {
				"toolNormalYagen",
				"oreChromium"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", 2, new ItemStack(MainInit.oreDust, 1,
				10), new Object[] {
						"toolNormalYagen",
						"gemRutile"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1,
				10), new Object[] {
						"toolNormalYagen",
						"oreTitanium"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1,
				11), new Object[] {
						"toolNormalYagen",
						"oreAluminum"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", 2, new ItemStack(MainInit.oreDust, 1,
				11), new Object[] {
						"toolNormalYagen",
						"gemBauxite"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1,
				12), new Object[] {
						"toolNormalYagen",
						"oreBismuth"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", 2, new ItemStack(MainInit.oreDust, 1,
				12), new Object[] {
						"toolNormalYagen",
						"gemBismuth"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1,
				13), new Object[] {
						"toolNormalYagen",
						"oreLead"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1,
				14), new Object[] {
						"toolNormalYagen",
						"oreManganese"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreDust, 1,
				15), new Object[] {
						"toolNormalYagen",
						"oreMolybdenum"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.gems_layer, 2,
				0), new Object[] {
						"toolNormalYagen",
						"oreGypsum"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.gems_layer, 1,
				1), new Object[] {
						"toolNormalYagen",
						"oreSalt"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.gems_layer, 1,
				2), new Object[] {
						"toolNormalYagen",
						"oreNiter"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.gems_layer, 1,
				3), new Object[] {
						"toolNormalYagen",
						"oreSulfur"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.gems_black, 1,
				1), new Object[] {
						"toolNormalYagen",
						"oreSchorl"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.gems_green, 1,
				3), new Object[] {
						"toolNormalYagen",
						"oreSerpentine"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.gems_red, 1,
				1), new Object[] {
						"toolNormalYagen",
						"oreGarnet"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.gems_layer, 1,
				4), new Object[] {
						"toolNormalYagen",
						"oreAluminium"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.gems_layer, 1,
				4), new Object[] {
						"toolNormalYagen",
						"oreAluminum"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.miscDust, 1,
				0), new Object[] {
						"toolNormalYagen",
						new ItemStack(Items.COAL, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.miscDust, 1,
				1), new Object[] {
						"toolNormalYagen",
						"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", 2, new ItemStack(MainInit.miscDust, 1,
				1), new Object[] {
						"toolNormalYagen",
						"gemQuartz"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.miscDust, 1,
				2), new Object[] {
						"toolNormalYagen",
						"oreLime"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.miscDust, 2,
				3), new Object[] {
						"toolNormalYagen",
						"logWood"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.miscDust, 1,
				6), new Object[] {
						"toolNormalYagen",
						"gemNiter"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.miscDust, 1,
				7), new Object[] {
						"toolNormalYagen",
						"gemSulfur"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", 2, new ItemStack(MainInit.miscDust, 1,
				7), new Object[] {
						"toolNormalYagen",
						"oreTravertine"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.miscDust, 1,
				8), new Object[] {
						"toolNormalYagen",
						"gemGarnet"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.miscDust, 1,
				9), new Object[] {
						"toolNormalYagen",
						"oreApatite"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", 2, new ItemStack(MainInit.miscDust, 1,
				9), new Object[] {
						"toolNormalYagen",
						"gemApatite"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.miscDust, 1,
				11), new Object[] {
						"toolNormalYagen",
						"gemSerpentine"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.miscDust, 1,
				12), new Object[] {
						"toolNormalYagen",
						"gemSakura"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(Items.DYE, 5, 15), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.BONE, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(Items.BLAZE_POWDER, 1,
				0), new Object[] {
						"toolNormalYagen",
						new ItemStack(Blocks.MAGMA, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", 2, new ItemStack(Items.BLAZE_POWDER, 3,
				0), new Object[] {
						"toolNormalYagen",
						new ItemStack(Items.BLAZE_ROD, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.foodMaterials, 1,
				0), new Object[] {
						"toolNormalYagen",
						"gemSalt"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.foodMaterials, 1,
				1), new Object[] {
						"toolNormalYagen",
						"cropWheat"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", 2, new ItemStack(MainInit.foodMaterials, 1,
				1), new Object[] {
						"toolNormalYagen",
						new ItemStack(Items.WHEAT_SEEDS)
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.foodMaterials, 1,
				2), new Object[] {
						"toolNormalYagen",
						"seedRice"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(Items.FLINT, 1, 0), new Object[] {
				"toolNormalYagen",
				"gravel"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(Items.STRING, 3, 0), new Object[] {
				"toolNormalYagen",
				"blockWool"
		});
	}

	static void addNewOre() {
		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreItem, 1, 0), new Object[] {
				"toolNormalYagen",
				"oreRed"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreItem, 1, 1), new Object[] {
				"toolNormalYagen",
				"oreGreen"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreItem, 1, 2), new Object[] {
				"toolNormalYagen",
				"oreBlue"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreItem, 1, 3), new Object[] {
				"toolNormalYagen",
				"oreWhite"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreItem, 1, 4), new Object[] {
				"toolNormalYagen",
				"oreBlack"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreItem, 1, 5), new Object[] {
				"toolNormalYagen",
				"oreLargeRed"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreItem, 1, 6), new Object[] {
				"toolNormalYagen",
				"oreLargeGreen"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreItem, 1, 7), new Object[] {
				"toolNormalYagen",
				"oreLargeBlue"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreItem, 1, 8), new Object[] {
				"toolNormalYagen",
				"oreLargeWhite"
		});

		DCRecipe.jsonShapelessRecipe(HaCModule.CORE, "main_yagen", new ItemStack(MainInit.oreItem, 1, 9), new Object[] {
				"toolNormalYagen",
				"oreLargeBlack"
		});
	}

}
