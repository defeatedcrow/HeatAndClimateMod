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

		// dagger
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerSilver, 3, 0), new Object[] {
				"X",
				"Y",
				'X',
				"ingotSilver",
				'Y',
				"stickWood" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerSilver, 3, 0), new Object[] {
				"X",
				"Y",
				'X',
				"ingotNickelsilver",
				'Y',
				"stickWood" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 0), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(MainInit.gems, 1, 2),
				'Y',
				new ItemStack(MainInit.gems, 1, 2),
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 4), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(MainInit.gems, 1, 1),
				'Y',
				new ItemStack(MainInit.gems, 1, 2),
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 2), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(MainInit.gems, 1, 0),
				'Y',
				new ItemStack(MainInit.gems, 1, 2),
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 1), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(MainInit.gems, 1, 2),
				'Y',
				"gemSapphire",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 5), new Object[] {
				"XYW",
				" Z ",
				'X',
				new ItemStack(MainInit.gems, 1, 1),
				'Y',
				"gemSapphire",
				'W',
				"dustBlaze",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 3), new Object[] {
				"XYW",
				" Z ",
				'X',
				new ItemStack(MainInit.gems, 1, 0),
				'Y',
				"gemSapphire",
				'W',
				"enderpearl",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 6), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemMalachite",
				'Y',
				"gemQuartz",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 7), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemCelestite",
				'Y',
				"gemQuartz",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 8), new Object[] {
				"XYW",
				" Z ",
				'X',
				"gemSapphire",
				'Y',
				"gemQuartz",
				'W',
				"gemLapis",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 9), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemQuartz",
				'Y',
				"gemLapis",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 10), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemSchorl",
				'Y',
				"gemLapis",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 11), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemMalachite",
				'Y',
				"gemLapis",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 12), new Object[] {
				"XYW",
				" Z ",
				'X',
				"gemCelestite",
				'Y',
				"gemSchorl",
				'W',
				new ItemStack(MainInit.gems, 1, 0),
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 13), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemClam",
				'Y',
				"gemScorl",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MagicInit.daggerMagic, 1, 14), new Object[] {
				"XYW",
				" Z ",
				'X',
				"gemDiamond",
				'Y',
				"gemClam",
				'W',
				"gemSapphire",
				'Z',
				new ItemStack(MagicInit.daggerSilver) }));
	}

}
