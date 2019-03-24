package defeatedcrow.hac.main.recipes;

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
		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 0), new Object[] {
				"toolNormalYagen",
				"oreCopper"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 1), new Object[] {
				"toolNormalYagen",
				"oreZinc"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 2), new Object[] {
				"toolNormalYagen",
				"oreNickel"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 3), new Object[] {
				"toolNormalYagen",
				"oreSilver"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 4), new Object[] {
				"toolNormalYagen",
				"oreGold"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 5), new Object[] {
				"toolNormalYagen",
				"oreIron"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 7), new Object[] {
				"toolNormalYagen",
				"oreMagnetite"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 8), new Object[] {
				"toolNormalYagen",
				"oreTin"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 9), new Object[] {
				"toolNormalYagen",
				"oreChromium"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 10), new Object[] {
				"toolNormalYagen",
				"oreTitanium"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 11), new Object[] {
				"toolNormalYagen",
				"oreAluminum"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 12), new Object[] {
				"toolNormalYagen",
				"oreBismuth"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 13), new Object[] {
				"toolNormalYagen",
				"oreLead"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 14), new Object[] {
				"toolNormalYagen",
				"oreManganese"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 15), new Object[] {
				"toolNormalYagen",
				"oreMolybdenum"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.gems, 1, 8), new Object[] {
				"toolNormalYagen",
				"oreSalt"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.gems, 1, 9), new Object[] {
				"toolNormalYagen",
				"oreNiter"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.gems, 1, 10), new Object[] {
				"toolNormalYagen",
				"oreSulfur"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.gems, 1, 11), new Object[] {
				"toolNormalYagen",
				"oreSchorl"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.gems, 1, 12), new Object[] {
				"toolNormalYagen",
				"oreSerpentine"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.gems, 1, 14), new Object[] {
				"toolNormalYagen",
				"oreGarnet"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.gems, 1, 16), new Object[] {
				"toolNormalYagen",
				"oreAluminium"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.gems, 1, 16), new Object[] {
				"toolNormalYagen",
				"oreAluminum"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 1, 2), new Object[] {
				"toolNormalYagen",
				"oreLime"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 10), new Object[] {
				"toolNormalYagen",
				"oreTitanium"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 1, 9), new Object[] {
				"toolNormalYagen",
				"oreApatite"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 1, 6), new Object[] {
				"toolNormalYagen",
				"gemNiter"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 1, 7), new Object[] {
				"toolNormalYagen",
				"gemSulfur"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 1, 1), new Object[] {
				"toolNormalYagen",
				"gemChalcedony"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 1, 1), new Object[] {
				"toolNormalYagen",
				"gemQuartz"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 1, 8), new Object[] {
				"toolNormalYagen",
				"gemGarnet"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 11), new Object[] {
				"toolNormalYagen",
				"gemBauxite"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 12), new Object[] {
				"toolNormalYagen",
				"gemBismuth"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreDust, 1, 10), new Object[] {
				"toolNormalYagen",
				"gemRutile"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 1, 9), new Object[] {
				"toolNormalYagen",
				"gemApatite"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 1, 11), new Object[] {
				"toolNormalYagen",
				"gemSerpentine"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 1, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.COAL, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(Items.DYE, 5, 15), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.BONE, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(Items.BLAZE_POWDER, 3, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.BLAZE_ROD, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.foodMaterials, 1, 0), new Object[] {
				"toolNormalYagen",
				"gemSalt"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.foodMaterials, 1, 1), new Object[] {
				"toolNormalYagen",
				"cropWheat"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.foodMaterials, 1, 1), new Object[] {
				"toolNormalYagen",
				"seedWheat"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.foodMaterials, 1, 2), new Object[] {
				"toolNormalYagen",
				"seedRice"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(Items.BLAZE_POWDER, 1, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Blocks.MAGMA, 1, 0)
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.miscDust, 2, 3), new Object[] {
				"toolNormalYagen",
				"logWood"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(Items.FLINT, 1, 0), new Object[] {
				"toolNormalYagen",
				"gravel"
		});
	}

	static void addNewOre() {
		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreItem, 1, 0), new Object[] {
				"toolNormalYagen",
				"oreRed"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreItem, 1, 1), new Object[] {
				"toolNormalYagen",
				"oreGreen"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreItem, 1, 2), new Object[] {
				"toolNormalYagen",
				"oreBlue"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreItem, 1, 3), new Object[] {
				"toolNormalYagen",
				"oreWhite"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreItem, 1, 4), new Object[] {
				"toolNormalYagen",
				"oreBlack"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreItem, 1, 5), new Object[] {
				"toolNormalYagen",
				"oreLargeRed"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreItem, 1, 6), new Object[] {
				"toolNormalYagen",
				"oreLargeGreen"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreItem, 1, 7), new Object[] {
				"toolNormalYagen",
				"oreLargeBlue"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreItem, 1, 8), new Object[] {
				"toolNormalYagen",
				"oreLargeWhite"
		});

		DCRecipe.jsonShapelessRecipe("main_yagen", new ItemStack(MainInit.oreItem, 1, 9), new Object[] {
				"toolNormalYagen",
				"oreLargeBlack"
		});
	}

}
