package defeatedcrow.hac.main.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.magic.MagicInit;
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
				new ItemStack(Blocks.TALLGRASS, 1, 1),
				'Y',
				"stickWood" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 1), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				new ItemStack(Items.STRING, 1, 0),
				'Y',
				"stickWood" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 2), new Object[] {
				" X ",
				"X X",
				" X ",
				'X',
				new ItemStack(MainInit.materials, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 3), new Object[] {
				" X ",
				"X X",
				" X ",
				'X',
				new ItemStack(MainInit.materials, 1, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 4), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				"plankWood",
				'Y',
				new ItemStack(Items.STICK, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 5), new Object[] {
				" X ",
				"XYX",
				" X ",
				'Y',
				"gearWood",
				'X',
				"ingotBrass" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 6), new Object[] {
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
				new ItemStack(Blocks.COBBLESTONE, 1, 0),
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
				new ItemStack(Items.GOLD_INGOT, 1, 0),
				'Y',
				new ItemStack(Items.REDSTONE, 1, 0),
				'Z',
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chamber, 1, 0), new Object[] {
				"ZXZ",
				"XYX",
				"ZXZ",
				'Y',
				new ItemStack(Blocks.FURNACE, 1, 0),
				'X',
				"stickBlaze",
				'Z',
				"ingotBrass" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.shitirin, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'Y',
				new ItemStack(Blocks.FURNACE, 1, 0),
				'X',
				new ItemStack(Items.CLAY_BALL, 1, 0),
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

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 8), new Object[] {
				"toolNormalYagen",
				"oreTin" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 2), new Object[] {
				"toolNormalYagen",
				"oreLime" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 1, 8), new Object[] {
				"toolNormalYagen",
				"oreSalt" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 1, 9), new Object[] {
				"toolNormalYagen",
				"oreNiter" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 1, 10), new Object[] {
				"toolNormalYagen",
				"oreSulfur" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 1, 11), new Object[] {
				"toolNormalYagen",
				"oreSchorl" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 6), new Object[] {
				"toolNormalYagen",
				"gemNiter" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 7), new Object[] {
				"toolNormalYagen",
				"gemSulfur" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.COAL, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.DYE, 5, 15), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.BONE, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BLAZE_POWDER, 3, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.BLAZE_ROD, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), new Object[] {
				"toolNormalYagen",
				"gemSalt" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.foodMaterials, 1, 1), new Object[] {
				"toolNormalYagen",
				"cropWheat" }));
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

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustIron" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 3), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustSilver" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 8), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustTin" }));

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
				"dustNickel",
				"dustZinc" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 9), new Object[] {
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustTin",
				"dustTin" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustGraphite" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 7), new Object[] {
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustGraphite" }));

		// ingot <-> metalBlock
		String[] metal = {
				"Copper",
				"Zinc",
				"Nickel",
				"Silver",
				"Brass",
				"Steel",
				"Nickelsilver",
				"Magnet",
				"Tin",
				"Bronze" };
		for (int i = 0; i < metal.length; i++) {
			ItemStack ingot = new ItemStack(MainInit.oreIngot, 9, i);
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.metalBlock, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					"ingot" + metal[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(ingot,
					new Object[] { new ItemStack(MainInit.metalBlock, 1, i) }));
		}

		// gem <-> gemblock
		for (int i = 0; i < 5; i++) {
			ItemStack gem = new ItemStack(MainInit.gems, 4, i);
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, i), new Object[] {
					"XX",
					"XX",
					'X',
					gem }));

			GameRegistry
					.addRecipe(new ShapelessOreRecipe(gem, new Object[] { new ItemStack(MainInit.gemBlock, 1, i) }));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, 5), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gems, 1, 8) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, 7), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gems, 1, 11) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 4, 11),
				new Object[] { new ItemStack(MainInit.gemBlock, 1, 7) }));
	}

	static void loadContRecipes() {
		ItemStack[] logs = new ItemStack[] {
				new ItemStack(Blocks.LOG, 9, 0),
				new ItemStack(Blocks.LOG, 9, 1),
				new ItemStack(Blocks.LOG, 9, 2),
				new ItemStack(Blocks.LOG, 9, 3),
				new ItemStack(Blocks.LOG2, 9, 0),
				new ItemStack(Blocks.LOG2, 9, 1),
				new ItemStack(Items.COAL, 9, 1) };
		for (int i = 0; i < logs.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.logCont, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					logs[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(logs[i],
					new Object[] { new ItemStack(MainInit.logCont, 1, i) }));
		}

		ItemStack[] crops = new ItemStack[] {
				new ItemStack(Items.APPLE, 9, 0),
				new ItemStack(Items.POTATO, 9, 0),
				new ItemStack(Items.CARROT, 9, 0),
				new ItemStack(Blocks.PUMPKIN, 9, 0),
				new ItemStack(Blocks.MELON_BLOCK, 9, 0),
				new ItemStack(Blocks.CACTUS, 9, 0),
				new ItemStack(Items.REEDS, 9, 0),
				new ItemStack(Items.NETHER_WART, 9, 0),
				new ItemStack(Items.DYE, 9, EnumDyeColor.BROWN.getMetadata()),
				new ItemStack(MainInit.bakedApple, 9, 0),
				new ItemStack(Items.BAKED_POTATO, 9, 0) };
		for (int i = 0; i < crops.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.cropCont, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					crops[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(crops[i], new Object[] { new ItemStack(MainInit.cropCont, 1,
					i) }));
		}

		ItemStack[] misc = new ItemStack[] {
				new ItemStack(Items.CLAY_BALL, 9, 0),
				new ItemStack(Items.FISH, 9, 0),
				new ItemStack(Items.LEATHER, 9, 0),
				new ItemStack(Items.RABBIT_HIDE, 9, 0) };
		for (int i = 0; i < misc.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.miscCont, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					misc[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(misc[i],
					new Object[] { new ItemStack(MainInit.miscCont, 1, i) }));
		}

		ItemStack[] enemy = new ItemStack[] {
				new ItemStack(Items.ROTTEN_FLESH, 9, 0),
				new ItemStack(Items.BONE, 9, 0),
				new ItemStack(Items.SPIDER_EYE, 9, 0),
				new ItemStack(Items.ENDER_PEARL, 9, 0),
				new ItemStack(Items.GUNPOWDER, 9, 0) };
		for (int i = 0; i < enemy.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dropCont, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					enemy[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(enemy[i], new Object[] { new ItemStack(MainInit.dropCont, 1,
					i) }));
		}

		ItemStack[] meat = new ItemStack[] {
				new ItemStack(Items.BEEF, 9, 0),
				new ItemStack(Items.PORKCHOP, 9, 0),
				new ItemStack(Items.CHICKEN, 9, 0),
				new ItemStack(Items.MUTTON, 9, 0),
				new ItemStack(Items.EGG, 9, 0) };
		for (int i = 0; i < meat.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.cardboard, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					meat[i] }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(meat[i], new Object[] { new ItemStack(MainInit.cardboard, 1,
					i) }));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.cardboard, 1, 5), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				new ItemStack(Blocks.WOOL, 9, 32767) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.WOOL, 9, 0), new Object[] { new ItemStack(
				MainInit.cardboard, 1, 5) }));
	}

	static void loadBuildRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.selenite, 8, 1), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0),
				'Y',
				new ItemStack(Items.GLOWSTONE_DUST) }));

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

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.bricks, 1, 0), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 3) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.bricks, 1, 1), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 6) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.bricks, 1, 2), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.ores_2, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gemBlock, 4, 3),
				new Object[] { new ItemStack(MainInit.bricks, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gemBlock, 4, 6),
				new Object[] { new ItemStack(MainInit.bricks, 1, 1) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.ores_2, 4, 0),
				new Object[] { new ItemStack(MainInit.bricks, 1, 2) }));

		Object[] chal = new Object[] {
				new ItemStack(MainInit.gemBlock, 1, 0),
				new ItemStack(MainInit.gemBlock, 1, 1),
				new ItemStack(MainInit.gemBlock, 1, 2) };
		for (int i = 0; i < 3; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, i), new Object[] {
					"X",
					"Y",
					'X',
					chal[i],
					'Y',
					"dustRedstone" }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 4 + i), new Object[] {
					" X ",
					"XYX",
					" X ",
					'X',
					new ItemStack(Blocks.GLASS, 1, 0),
					'Y',
					new ItemStack(MainInit.chalLamp, 1, i) }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 8 + i), new Object[] {
					"Z",
					"Y",
					'Y',
					"ingotCopper",
					'Z',
					new ItemStack(MainInit.chalLamp, 1, i) }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 12 + i), new Object[] {
					"Y",
					"Z",
					'Y',
					"ingotCopper",
					'Z',
					new ItemStack(MainInit.chalLamp, 1, i) }));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 3), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				"plankWood",
				'Y',
				new ItemStack(MainInit.chalLamp, 1, 2) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 7), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				new ItemStack(Blocks.GLASS, 1, 0),
				'Y',
				new ItemStack(MainInit.chalLamp, 1, 3) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 11), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MainInit.chalLamp, 1, 3),
				'Y',
				"ingotCopper" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 15), new Object[] {
				"Y",
				"X",
				'X',
				new ItemStack(MainInit.chalLamp, 1, 3),
				'Y',
				"ingotCopper" }));

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
					new ItemStack(Items.STICK) }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcAxe[i], 1, 0), new Object[] {
					" XX",
					"YYX",
					"   ",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.STICK) }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcSpade[i], 1, 0), new Object[] {
					"   ",
					"YYX",
					"   ",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.STICK) }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcSword[i], 1, 0), new Object[] {
					"   ",
					"YXX",
					"   ",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.STICK) }));
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

	static void loadAnotherRecipes() {
		// 火打ち石のアナザー
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.FLINT_AND_STEEL, 1, 0), new Object[] {
				new ItemStack(MainInit.ores, 1, 4),
				"gemChalcedony" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.FLINT_AND_STEEL, 1, 0), new Object[] {
				new ItemStack(MainInit.ores, 1, 4),
				new ItemStack(Items.FLINT) }));

		// 変換
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.DYE, 1, 15), new Object[] { new ItemStack(
				MainInit.miscDust, 1, 5) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreIngot, 1, 5),
				new Object[] { new ItemStack(MainInit.oreIngot, 1, 7) }));
	}
}
