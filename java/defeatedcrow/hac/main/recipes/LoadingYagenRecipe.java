package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class LoadingYagenRecipe {

	public static void add(RecipeResourcesMain res) {
		// ore -> dust
		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 0), new Object[] {
				"toolNormalYagen", "oreCopper"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 1), new Object[] {
				"toolNormalYagen", "oreZinc"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 2), new Object[] {
				"toolNormalYagen", "oreNickel"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 3), new Object[] {
				"toolNormalYagen", "oreSilver"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 4), new Object[] {
				"toolNormalYagen", "oreGold"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 5), new Object[] {
				"toolNormalYagen", "oreIron"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 7), new Object[] {
				"toolNormalYagen", "oreMagnetite"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 8), new Object[] {
				"toolNormalYagen", "oreTin"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 1, 2), new Object[] {
				"toolNormalYagen", "oreLime"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 12), new Object[] {
				"toolNormalYagen", "oreBismuth"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.gems, 1, 8), new Object[] {
				"toolNormalYagen", "oreSalt"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.gems, 1, 9), new Object[] {
				"toolNormalYagen", "oreNiter"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.gems, 1, 10), new Object[] {
				"toolNormalYagen", "oreSulfur"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.gems, 1, 11), new Object[] {
				"toolNormalYagen", "oreSchorl"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.gems, 1, 12), new Object[] {
				"toolNormalYagen", "oreSerpentine"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.gems, 1, 14), new Object[] {
				"toolNormalYagen", "oreGarnet"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.gems, 1, 16), new Object[] {
				"toolNormalYagen", "oreAlminium"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.gems, 1, 16), new Object[] {
				"toolNormalYagen", "oreAlminum"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.gems, 1, 15), new Object[] {
				"toolNormalYagen", "oreTitanium"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 1, 9), new Object[] {
				"toolNormalYagen", "oreApatite"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 1, 6), new Object[] {
				"toolNormalYagen", "gemNiter"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 1, 7), new Object[] {
				"toolNormalYagen", "gemSulfur"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 1, 1), new Object[] {
				"toolNormalYagen", "gemChalcedony"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 1, 1), new Object[] {
				"toolNormalYagen", "gemQuartz"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 1, 8), new Object[] {
				"toolNormalYagen", "gemGarnet"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 11), new Object[] {
				"toolNormalYagen", "gemBauxite"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 12), new Object[] {
				"toolNormalYagen", "gemBismuth"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.oreDust, 1, 10), new Object[] {
				"toolNormalYagen", "gemRutile"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 1, 9), new Object[] {
				"toolNormalYagen", "gemApatite"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 1, 11), new Object[] {
				"toolNormalYagen", "gemSerpentine"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 1, 0), new Object[] {
				"toolNormalYagen", new ItemStack(Items.COAL, 1, 0)
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(Items.DYE, 5, 15), new Object[] {
				"toolNormalYagen", new ItemStack(Items.BONE, 1, 0)
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(Items.BLAZE_POWDER, 3, 0), new Object[] {
				"toolNormalYagen", new ItemStack(Items.BLAZE_ROD, 1, 0)
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.foodMaterials, 1, 0), new Object[] {
				"toolNormalYagen", "gemSalt"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.foodMaterials, 1, 1), new Object[] {
				"toolNormalYagen", "cropWheat"
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(Items.BLAZE_POWDER, 1, 0), new Object[] {
				"toolNormalYagen", new ItemStack(Blocks.MAGMA, 1, 0)
		});

		DCRecipe.addShapelessNBTRecipe(res.getRecipeName(), new ItemStack(MainInit.miscDust, 2, 3), new Object[] {
				"toolNormalYagen", "logWood"
		});
	}

}
