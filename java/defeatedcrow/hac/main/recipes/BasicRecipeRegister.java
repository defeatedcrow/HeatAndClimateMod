package defeatedcrow.hac.main.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.main.MainInit;

public class BasicRecipeRegister {

	public static void load() {
		loadToolRecipe();
		loadYagenRecipe();
		loadOreRecipes();
		loadContRecipes();
		loadBuildRecipes();
		loadAnotherRecipes();
		loadEquipsRecipes();
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

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 5), new Object[] {
				" X ",
				"XYX",
				" X ",
				'Y',
				"gearWood",
				'X',
				"ingotNickelsilver" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stoneYagen, 1, 0), new Object[] {
				" X ",
				"XYX",
				"XXX",
				'X',
				new ItemStack(Blocks.cobblestone, 1, 0),
				'Y',
				new ItemStack(MainInit.materials, 1, 4) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.brassYagen, 1, 0), new Object[] {
				" X ",
				"XYX",
				"XXX",
				'X',
				"ingotBrass",
				'Y',
				new ItemStack(MainInit.materials, 1, 4) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCInit.climate_checker, 1, 0), new Object[] {
				" Z ",
				"XYX",
				" Z ",
				'X',
				new ItemStack(Items.gold_ingot, 1, 0),
				'Y',
				new ItemStack(Items.redstone, 1, 0),
				'Z',
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chamber, 1, 0), new Object[] {
				"ZXZ",
				"XYX",
				"ZXZ",
				'Y',
				new ItemStack(Blocks.furnace, 1, 0),
				'X',
				"stickBlaze",
				'Z',
				"ingotBrass" }));

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

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.coal, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.coal, 1, 1) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.dye, 5, 15), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.bone, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.blaze_rod, 1, 0) }));
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

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 6), new Object[] {
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

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 7), new Object[] {
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

	static void loadContRecipes() {
		ItemStack[] logs = new ItemStack[] {
				new ItemStack(Blocks.log, 9, 0),
				new ItemStack(Blocks.log, 9, 1),
				new ItemStack(Blocks.log, 9, 2),
				new ItemStack(Blocks.log, 9, 3),
				new ItemStack(Blocks.log2, 9, 0),
				new ItemStack(Blocks.log2, 9, 1),
				new ItemStack(Items.coal, 9, 1) };
		for (int i = 0; i < logs.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.logCont, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					logs[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(logs[i], new Object[] { new ItemStack(MainInit.logCont, 1, i) }));
		}

		ItemStack[] crops = new ItemStack[] {
				new ItemStack(Items.apple, 9, 0),
				new ItemStack(Items.potato, 9, 0),
				new ItemStack(Items.carrot, 9, 0),
				new ItemStack(Blocks.pumpkin, 9, 0),
				new ItemStack(Blocks.melon_block, 9, 0),
				new ItemStack(Blocks.cactus, 9, 0),
				new ItemStack(Items.reeds, 9, 0),
				new ItemStack(Items.nether_wart, 9, 0),
				new ItemStack(Items.dye, 9, EnumDyeColor.BROWN.getMetadata()),
				new ItemStack(MainInit.bakedApple, 9, 0),
				new ItemStack(Items.baked_potato, 9, 0) };
		for (int i = 0; i < crops.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.cropCont, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					crops[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(crops[i], new Object[] { new ItemStack(MainInit.cropCont, 1, i) }));
		}

		ItemStack[] misc = new ItemStack[] {
				new ItemStack(Items.clay_ball, 9, 0),
				new ItemStack(Items.fish, 9, 0),
				new ItemStack(Items.leather, 9, 0),
				new ItemStack(Items.rabbit_hide, 9, 0) };
		for (int i = 0; i < misc.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.miscCont, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					misc[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(misc[i], new Object[] { new ItemStack(MainInit.miscCont, 1, i) }));
		}

		ItemStack[] enemy = new ItemStack[] {
				new ItemStack(Items.rotten_flesh, 9, 0),
				new ItemStack(Items.bone, 9, 0),
				new ItemStack(Items.spider_eye, 9, 0),
				new ItemStack(Items.ender_pearl, 9, 0),
				new ItemStack(Items.gunpowder, 9, 0) };
		for (int i = 0; i < enemy.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dropCont, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					enemy[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(enemy[i], new Object[] { new ItemStack(MainInit.dropCont, 1, i) }));
		}
	}

	static void loadBuildRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.selenite, 8, 1), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0),
				'Y',
				new ItemStack(Items.glowstone_dust) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.selenite, 8, 2), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0),
				'Y',
				new ItemStack(MainInit.oreDust, 1, 3) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsGlass, 4, 0), new Object[] {
				"X  ",
				"XX ",
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsGlass, 4, 0), new Object[] {
				"  X",
				" XX",
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsGypsum, 4, 0), new Object[] {
				"X  ",
				"XX ",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 3) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsGypsum, 4, 0), new Object[] {
				"  X",
				" XX",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 3) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.halfSlab, 3, 1), new Object[] {
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.halfSlab, 3, 0), new Object[] {
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 3) }));
	}

	private static void loadEquipsRecipes() {
		String[] name = {
				"ingotBrass",
				"ingotSteel",
				"ingotSilver",
				"ingotNickelsilver",
				"gemChalcedony",
				"gemSapphire" };
		for (int i = 0; i < name.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcPickaxe[i], 1, 0), new Object[] {
					"  X",
					"YYX",
					"  X",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.stick) }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcAxe[i], 1, 0), new Object[] {
					" XX",
					"YYX",
					"   ",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.stick) }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcSpade[i], 1, 0), new Object[] {
					"   ",
					"YYX",
					"   ",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.stick) }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcSword[i], 1, 0), new Object[] {
					"   ",
					"YXX",
					"   ",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.stick) }));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.brassArmor[0], 1, 0), new Object[] {
				"XXX",
				"X X",
				'X',
				"ingotBrass" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.steelArmor[0], 1, 0), new Object[] {
				"XXX",
				"X X",
				'X',
				"ingotSteel" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalcArmor[0], 1, 0), new Object[] {
				"XXX",
				"X X",
				'X',
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sapphireArmor[0], 1, 0), new Object[] {
				"XXX",
				"X X",
				'X',
				"gemSapphire" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.brassArmor[1], 1, 0), new Object[] {
				"X X",
				"XXX",
				"XXX",
				'X',
				"ingotBrass" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.steelArmor[1], 1, 0), new Object[] {
				"X X",
				"XXX",
				"XXX",
				'X',
				"ingotSteel" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalcArmor[1], 1, 0), new Object[] {
				"X X",
				"XXX",
				"XXX",
				'X',
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sapphireArmor[1], 1, 0), new Object[] {
				"X X",
				"XXX",
				"XXX",
				'X',
				"gemSapphire" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.brassArmor[2], 1, 0), new Object[] {
				"XXX",
				"X X",
				"X X",
				'X',
				"ingotBrass" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.steelArmor[2], 1, 0), new Object[] {
				"XXX",
				"X X",
				"X X",
				'X',
				"ingotSteel" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalcArmor[2], 1, 0), new Object[] {
				"XXX",
				"X X",
				"X X",
				'X',
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sapphireArmor[2], 1, 0), new Object[] {
				"XXX",
				"X X",
				"X X",
				'X',
				"gemSapphire" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.brassArmor[3], 1, 0), new Object[] {
				"X X",
				"X X",
				'X',
				"ingotBrass" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.steelArmor[3], 1, 0), new Object[] {
				"X X",
				"X X",
				'X',
				"ingotSteel" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalcArmor[3], 1, 0), new Object[] {
				"X X",
				"X X",
				'X',
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sapphireArmor[3], 1, 0), new Object[] {
				"X X",
				"X X",
				'X',
				"gemSapphire" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.linenUnder, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"X X",
				'X',
				"itemLinenCloth" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.linenCourt, 1, 0), new Object[] {
				"X X",
				"XXX",
				"XXX",
				'X',
				"itemLinenCloth" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.clothUnder, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"X X",
				'X',
				"itemCottonCloth" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.blackSuit, 1, 0), new Object[] {
				"XYX",
				"XXX",
				"X X",
				'X',
				"itemCottonCloth",
				'Y',
				"dyeBlack" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.workerSuit, 1, 0), new Object[] {
				"XYX",
				"XXX",
				"X X",
				'X',
				"itemCottonCloth",
				'Y',
				"dyeBlue" }));

		// アクセサリー
		Object[] gems = {
				new ItemStack(MainInit.gems, 1, 0),
				new ItemStack(MainInit.gems, 1, 1),
				new ItemStack(MainInit.gems, 1, 2),
				"gemQuartz",
				"gemSapphire",
				new ItemStack(MainInit.gems, 1, 5),
				new ItemStack(MainInit.gems, 1, 6),
				new ItemStack(MainInit.gems, 1, 7),
				"gemLapis",
				"gemDiamond" };
		for (int i = 0; i < gems.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.pendant, 1, i), new Object[] {
					" X ",
					"X X",
					" Y ",
					'X',
					"ingotSilver",
					'Y',
					gems[i] }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.pendant, 1, i), new Object[] {
					" X ",
					"X X",
					" Y ",
					'X',
					"ingotNickelsilver",
					'Y',
					gems[i] }));
		}

	}

	static void loadAnotherRecipes() {
		// 矢
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.arrow, 8, 0), new Object[] {
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
				new ItemStack(Items.iron_ingot, 1, 0),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.flint_and_steel, 1, 0), new Object[] {
				new ItemStack(MainInit.ores, 1, 4),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.flint_and_steel, 1, 0), new Object[] {
				new ItemStack(MainInit.ores, 1, 4),
				new ItemStack(Items.flint) }));

		// 変換
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.dye, 1, 15),
				new Object[] { new ItemStack(MainInit.miscDust, 1, 5) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreIngot, 1, 5), new Object[] { new ItemStack(
				MainInit.oreIngot, 1, 7) }));
	}
}
