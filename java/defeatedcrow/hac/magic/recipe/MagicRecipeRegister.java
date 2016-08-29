package defeatedcrow.hac.magic.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;

public class MagicRecipeRegister {

	public static void load() {
		loadItemRecipes();
	}

	static void loadItemRecipes() {
		// アクセサリー
		Object[] gems = {
				new ItemStack(MainInit.gems, 1, 0),
				new ItemStack(MainInit.gems, 1, 1),
				new ItemStack(MainInit.gems, 1, 2),
				"gemQuartz",
				"gemSapphire",
				"gemMalachite",
				"gemCelestite",
				"gemClam",
				"gemLapis",
				"gemDiamond",
				"gemSchorl" };
		for (int i = 0; i < gems.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.pendant, 1, i), new Object[] {
					" X ",
					"X X",
					" Y ",
					'X',
					"ingotSilver",
					'Y',
					gems[i] }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.pendant, 1, i), new Object[] {
					" X ",
					"X X",
					" Y ",
					'X',
					"ingotNickelsilver",
					'Y',
					gems[i] }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.badge, 1, i), new Object[] {
					" Y ",
					"X X",
					" X ",
					'X',
					"ingotSilver",
					'Y',
					gems[i] }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.badge, 1, i), new Object[] {
					" Y ",
					"X X",
					" X ",
					'X',
					"ingotNickelsilver",
					'Y',
					gems[i] }));
		}
	}

}
