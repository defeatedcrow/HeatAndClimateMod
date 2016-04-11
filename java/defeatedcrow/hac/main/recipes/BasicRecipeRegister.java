package defeatedcrow.hac.main.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import defeatedcrow.hac.main.MainInit;

public class BasicRecipeRegister {

	public static void load() {
		loadToolRecipe();
		loadYagenRecipe();
		loadOreRecipes();
		loadAnotherRecipes();
	}

	static void loadToolRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				new ItemStack(Blocks.tallgrass, 1, 1),
				'Y',
				"stickWood" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 1), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				new ItemStack(Items.string, 1, 0),
				'Y',
				"stickWood" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 2), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.materials, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 3), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.materials, 1, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 4), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				"plankWood",
				'Y',
				new ItemStack(Items.stick, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stoneYagen, 1, 0), new Object[] {
				" X ",
				"XYX",
				"XXX",
				'X',
				new ItemStack(Blocks.cobblestone, 1, 0),
				'Y',
				new ItemStack(MainInit.materials, 1, 4) }));
	}

	static void loadYagenRecipe() {
		// ore -> dust
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 0), new Object[] {
				"toolNormalYagen",
				"oreCopper" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 1), new Object[] {
				"toolNormalYagen",
				"oreZinc" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 2), new Object[] {
				"toolNormalYagen",
				"oreNickel" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 3), new Object[] {
				"toolNormalYagen",
				"oreSilver" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 4), new Object[] {
				"toolNormalYagen",
				"oreGold" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 5), new Object[] {
				"toolNormalYagen",
				"oreIron" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 7), new Object[] {
				"toolNormalYagen",
				"oreMagnetite" }));
	}

	static void loadOreRecipes() {
		// dust -> dustBlock
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustCopper" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 1), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustZinc" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 2), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustNickel" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 3), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustSilver" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 7), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustMagnetite" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 4), new Object[] {
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustZinc",
				"dustZinc",
				"dustZinc" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 4), new Object[] {
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustNickel",
				"dustZinc",
				"dustZinc" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustCoal" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustCoal" }));

		// ingot <-> metalBlock
		String[] metal = {
				"Copper",
				"Zinc",
				"Nickel",
				"Silver",
				"Brass",
				"Steel",
				"Nickelsilver",
				"Magnet" };
		for (int i = 0; i < 8; i++) {
			ItemStack ingot = new ItemStack(MainInit.oreIngot, 9, i);
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.metalBlock, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					"ingot" + metal[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(ingot, new Object[] { new ItemStack(MainInit.metalBlock, 1, i) }));
		}

		// gem <-> gemblock
		for (int i = 0; i < 5; i++) {
			ItemStack gem = new ItemStack(MainInit.gems, 4, i);
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, i), new Object[] {
					"XX",
					"XX",
					'X',
					gem }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(gem, new Object[] { new ItemStack(MainInit.gemBlock, 1, i) }));
		}
	}

	static void loadAnotherRecipes() {
		// 矢
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.arrow), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemChalcedony",
				'Y',
				new ItemStack(Items.stick),
				'Z',
				new ItemStack(Items.feather) }));

		// 火打ち石のアナザー
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.flint_and_steel, 1, 0), new Object[] {
				new ItemStack(MainInit.ores, 1, 4),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.flint_and_steel, 1, 0), new Object[] {
				new ItemStack(MainInit.ores, 1, 4),
				new ItemStack(Items.flint) }));
	}
}
