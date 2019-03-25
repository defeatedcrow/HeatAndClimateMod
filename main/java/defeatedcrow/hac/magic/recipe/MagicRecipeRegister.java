package defeatedcrow.hac.magic.recipe;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class MagicRecipeRegister {

	private static final RecipeResourcesMain res = new RecipeResourcesMain("magic");

	public static void load() {
		loadItemRecipes();
		loadPotionRecipe();
	}

	static void loadItemRecipes() {

		// drop
		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 3, 0), new Object[] {
				"gemChalcedonyBlue",
				"dyeBlue"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 0), new Object[] {
				"gemLapis",
				"dyeBlue"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 3, 1), new Object[] {
				"gemMalachite",
				"dyeGreen"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 1), new Object[] {
				"gemSerpentine",
				"dyeGreen"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 3, 2), new Object[] {
				"gemChalcedonyRed",
				"dyeRed"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 2), new Object[] {
				"dustRedstone",
				"dyeRed"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 3, 3), new Object[] {
				"oreCrudeOil",
				"dyeBlack"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 3), new Object[] {
				"obsidian",
				"dyeBlack"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 4), new Object[] {
				"gemQuartz",
				"dyeWhite"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 3, 4), new Object[] {
				"gemChalcedonyWhite",
				"dyeWhite"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 5), new Object[] {
				"gemSapphire",
				"dropBlue"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 5), new Object[] {
				"gemAquamarine",
				"dropBlue"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 6), new Object[] {
				"gemPeridot",
				"dropGreen"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 6), new Object[] {
				"gemEmerald",
				"dropGreen"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 7), new Object[] {
				"gemGarnet",
				"dropRed"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 7), new Object[] {
				"gemRuby",
				"dropRed"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 8), new Object[] {
				"gemSchorl",
				"dropBlack"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 9), new Object[] {
				"gemRutile",
				"dropWhite"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 9), new Object[] {
				"gemDiamond",
				"dropWhite"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorCube, 1, 0), new Object[] {
				"gemMoonstone",
				"dustBismuth",
				"blockElestial"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorCube, 1, 1), new Object[] {
				"gemJadeite",
				"dustChromium",
				"blockElestial"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorCube, 1, 2), new Object[] {
				"gemKunzite",
				"dustManganese",
				"blockElestial"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorCube, 1, 3), new Object[] {
				"gemOpal",
				"dustMolybdenum",
				"blockElestial"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorCube, 1, 4), new Object[] {
				"gemCelestite",
				"dustTitanium",
				"blockElestial"
		});

		// ring
		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing, 1, 0), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"dustCrystal",
				'Y',
				"dropBlue",
				'Z',
				"ingotSilver"
		});

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing, 1, 1), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"dustCrystal",
				'Y',
				"dropGreen",
				'Z',
				"ingotSilver"
		});

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing, 1, 2), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"dustCrystal",
				'Y',
				"dropRed",
				'Z',
				"ingotSilver"
		});

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing, 1, 3), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"dustCrystal",
				'Y',
				"dropBlack",
				'Z',
				"ingotSilver"
		});

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing, 1, 4), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"dustCrystal",
				'Y',
				"dropWhite",
				'Z',
				"ingotSilver"
		});

		// advanced
		if (ModuleConfig.magic_advanced) {
			// pendant
			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant, 1, 0), new Object[] {
					"ZZZ",
					" Y ",
					" X ",
					'X',
					"dustCrystal",
					'Y',
					"extractBlue",
					'Z',
					"ingotSilver"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant, 1, 1), new Object[] {
					"ZZZ",
					" Y ",
					" X ",
					'X',
					"dustCrystal",
					'Y',
					"extractGreen",
					'Z',
					"ingotSilver"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant, 1, 2), new Object[] {
					"ZZZ",
					" Y ",
					" X ",
					'X',
					"dustCrystal",
					'Y',
					"extractRed",
					'Z',
					"ingotSilver"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant, 1, 3), new Object[] {
					"ZZZ",
					" Y ",
					" X ",
					'X',
					"dustCrystal",
					'Y',
					"extractBlack",
					'Z',
					"ingotSilver"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant, 1, 4), new Object[] {
					"ZZZ",
					" Y ",
					" X ",
					'X',
					"dustCrystal",
					'Y',
					"extractWhite",
					'Z',
					"ingotSilver"
			});

			// badge
			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorBadge, 1, 0), new Object[] {
					" X ",
					"ZYZ",
					" Z ",
					'X',
					"gemDiamond",
					'Y',
					"cubeBlue",
					'Z',
					"ingotSilver"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorBadge, 1, 1), new Object[] {
					" X ",
					"ZYZ",
					" Z ",
					'X',
					"gemDiamond",
					'Y',
					"cubeGreen",
					'Z',
					"ingotSilver"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorBadge, 1, 2), new Object[] {
					" X ",
					"ZYZ",
					" Z ",
					'X',
					"gemDiamond",
					'Y',
					"cubeRed",
					'Z',
					"ingotSilver"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorBadge, 1, 3), new Object[] {
					" X ",
					"ZYZ",
					" Z ",
					'X',
					"gemDiamond",
					'Y',
					"cubeBlack",
					'Z',
					"ingotSilver"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorBadge, 1, 4), new Object[] {
					" X ",
					"ZYZ",
					" Z ",
					'X',
					"gemDiamond",
					'Y',
					"cubeWhite",
					'Z',
					"ingotSilver"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 0), new Object[] {
					"paper",
					"paper",
					"paper",
					"dropBlue"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 1), new Object[] {
					"paper",
					"paper",
					"paper",
					"dropGreen"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 2), new Object[] {
					"paper",
					"paper",
					"paper",
					"dropRed"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 3), new Object[] {
					"paper",
					"paper",
					"paper",
					"dropBlack"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 4), new Object[] {
					"paper",
					"paper",
					"paper",
					"dropWhite"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 5), new Object[] {
					"paper",
					"paper",
					"paper",
					"extractBlue"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 6), new Object[] {
					"paper",
					"paper",
					"paper",
					"extractGreen"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 7), new Object[] {
					"paper",
					"paper",
					"paper",
					"extractRed"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 8), new Object[] {
					"paper",
					"paper",
					"paper",
					"extractBlack"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 9), new Object[] {
					"paper",
					"paper",
					"paper",
					"extractWhite"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 10), new Object[] {
					"paper",
					"paper",
					"paper",
					"cubeBlue"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 11), new Object[] {
					"paper",
					"paper",
					"paper",
					"cubeGreen"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 12), new Object[] {
					"paper",
					"paper",
					"paper",
					"cubeRed"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 13), new Object[] {
					"paper",
					"paper",
					"paper",
					"cubeBlack"
			});

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 14), new Object[] {
					"paper",
					"paper",
					"paper",
					"cubeWhite"
			});

			// buimeOrb
			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.biomeOrb, 1, 0), new Object[] {
					"XYX",
					"YZY",
					"XYX",
					'X',
					new ItemStack(MainInit.selenite, 1, 0),
					'Y',
					"gemPeridot",
					'Z',
					"cubeWhite"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.biomeOrb, 1, 1), new Object[] {
					"XYX",
					"YZY",
					"XYX",
					'X',
					new ItemStack(MainInit.selenite, 1, 0),
					'Y',
					"gemPeridot",
					'Z',
					"cubeGreen"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.biomeOrb, 1, 2), new Object[] {
					"XYX",
					"YZY",
					"XYX",
					'X',
					new ItemStack(MainInit.selenite, 1, 0),
					'Y',
					"gemPeridot",
					'Z',
					"cubeRed"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.biomeOrb, 1, 3), new Object[] {
					"XYX",
					"YZY",
					"XYX",
					'X',
					new ItemStack(MainInit.selenite, 1, 0),
					'Y',
					"gemPeridot",
					'Z',
					"cubeBlue"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.timeCage, 1, 0), new Object[] {
					"XYX",
					"YZY",
					"XYX",
					'X',
					"ingotSilver",
					'Y',
					new ItemStack(MainInit.selenite, 1, 0),
					'Z',
					new ItemStack(MachineInit.gemcore, 1, 1)
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.gemBootsBird, 1, 0), new Object[] {
					"XYX",
					"XZX",
					'X',
					"stringMagic",
					'Y',
					"cubeWhite",
					'Z',
					"ingotSilver"
			});

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.gemBootsFish, 1, 0), new Object[] {
					"XYX",
					"XZX",
					'X',
					"stringMagic",
					'Y',
					"cubeBlue",
					'Z',
					"ingotSilver"
			});
		}

		if (ModuleConfig.food) {
			DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.lotusCandle, 1, 0), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					new ItemStack(FoodInit.petals, 1, 0)
			});

			DCRecipe.jsonShapelessRecipe("magic", new ItemStack(FoodInit.petals, 8, 0), new Object[] {
					new ItemStack(MagicInit.lotusCandle, 1, 0)
			});

			DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.lotusCandleBlack, 1, 0), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					new ItemStack(FoodInit.petals, 1, 1)
			});

			DCRecipe.jsonShapelessRecipe("magic", new ItemStack(FoodInit.petals, 8, 1), new Object[] {
					new ItemStack(MagicInit.lotusCandleBlack, 1, 0)
			});
		}

		// elestial
		GameRegistry.addSmelting(new ItemStack(MainInit.gemBlock, 1, 8), new ItemStack(MagicInit.elestial, 1, 0), 0.5F);

	}

	public static void loadOres() {
		OreDictionary.registerOre("dropBlue", new ItemStack(MagicInit.colorDrop, 1, 0));
		OreDictionary.registerOre("dropGreen", new ItemStack(MagicInit.colorDrop, 1, 1));
		OreDictionary.registerOre("dropRed", new ItemStack(MagicInit.colorDrop, 1, 2));
		OreDictionary.registerOre("dropBlack", new ItemStack(MagicInit.colorDrop, 1, 3));
		OreDictionary.registerOre("dropWhite", new ItemStack(MagicInit.colorDrop, 1, 4));
		OreDictionary.registerOre("extractBlue", new ItemStack(MagicInit.colorDrop, 1, 5));
		OreDictionary.registerOre("extractGreen", new ItemStack(MagicInit.colorDrop, 1, 6));
		OreDictionary.registerOre("extractRed", new ItemStack(MagicInit.colorDrop, 1, 7));
		OreDictionary.registerOre("extractBlack", new ItemStack(MagicInit.colorDrop, 1, 8));
		OreDictionary.registerOre("extractWhite", new ItemStack(MagicInit.colorDrop, 1, 9));
		OreDictionary.registerOre("cubeBlue", new ItemStack(MagicInit.colorCube, 1, 5));
		OreDictionary.registerOre("cubeGreen", new ItemStack(MagicInit.colorCube, 1, 6));
		OreDictionary.registerOre("cubeRed", new ItemStack(MagicInit.colorCube, 1, 7));
		OreDictionary.registerOre("cubeBlack", new ItemStack(MagicInit.colorCube, 1, 8));
		OreDictionary.registerOre("cubeWhite", new ItemStack(MagicInit.colorCube, 1, 9));
		OreDictionary.registerOre("blockElestial", new ItemStack(MagicInit.elestial, 1, 0));
	}

	static void loadOldrecipe() {

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
				"gemSchorl",
				"gemSerpentine",
				"gemOlivine",
				"gemGarnet",
				new ItemStack(MagicInit.elestial, 1, 0),
				"gemRutile",
				"gemBismuth",
				"gemJadeite",
				"gemMoonstone",
				"gemKunzite"
		};
		for (int i = 0; i < gems.length; i++) {
			DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.pendant, 1, i), new Object[] {
					" X ",
					"X X",
					" Y ",
					'X',
					"ingotSilver",
					'Y',
					gems[i]
			});

			DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.pendant, 1, i), new Object[] {
					" X ",
					"X X",
					" Y ",
					'X',
					"ingotNickelsilver",
					'Y',
					gems[i]
			});

			DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.badge, 1, i), new Object[] {
					" Y ",
					"X X",
					" X ",
					'X',
					"ingotSilver",
					'Y',
					gems[i]
			});

			DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.badge, 1, i), new Object[] {
					" Y ",
					"X X",
					" X ",
					'X',
					"ingotNickelsilver",
					'Y',
					gems[i]
			});
		}

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.pendant, 1, 12), new Object[] {
				" X ",
				"X X",
				" Y ",
				'X',
				"ingotSilver",
				'Y',
				"gemPeridot"
		});

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.pendant, 1, 12), new Object[] {
				" X ",
				"X X",
				" Y ",
				'X',
				"ingotNickelsilver",
				'Y',
				"gemPeridot"
		});

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.badge, 1, 12), new Object[] {
				" Y ",
				"X X",
				" X ",
				'X',
				"ingotSilver",
				'Y',
				"gemPeridot"
		});

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.badge, 1, 12), new Object[] {
				" Y ",
				"X X",
				" X ",
				'X',
				"ingotNickelsilver",
				'Y',
				"gemPeridot"
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.badge, 1, 7), new Object[] {
				new ItemStack(MagicInit.badge, 1, 7)
		});

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.badge, 1, 16), new Object[] {
				new ItemStack(MagicInit.badge, 1, 16)
		});

		// dagger
		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerSilver, 3, 0), new Object[] {
				"X",
				"Y",
				'X',
				"ingotSilver",
				'Y',
				"stickWood"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerSilver, 3, 0), new Object[] {
				"X",
				"Y",
				'X',
				"ingotNickelsilver",
				'Y',
				"stickWood"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 0), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(MainInit.gems, 1, 2),
				'Y',
				new ItemStack(MainInit.gems, 1, 2),
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 4), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(MainInit.gems, 1, 1),
				'Y',
				new ItemStack(MainInit.gems, 1, 2),
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 2), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(MainInit.gems, 1, 0),
				'Y',
				new ItemStack(MainInit.gems, 1, 2),
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 1), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(MainInit.gems, 1, 2),
				'Y',
				"gemSapphire",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 5), new Object[] {
				"XYW",
				" Z ",
				'X',
				new ItemStack(MainInit.gems, 1, 1),
				'Y',
				"gemSapphire",
				'W',
				"dustBlaze",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 3), new Object[] {
				"XYW",
				" Z ",
				'X',
				new ItemStack(MainInit.gems, 1, 0),
				'Y',
				"gemSapphire",
				'W',
				"enderpearl",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 6), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemMalachite",
				'Y',
				"gemQuartz",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 7), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemCelestite",
				'Y',
				"gemQuartz",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 8), new Object[] {
				"XYW",
				" Z ",
				'X',
				"gemSapphire",
				'Y',
				"gemQuartz",
				'W',
				"gemLapis",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 8), new Object[] {
				"XYW",
				" Z ",
				'X',
				"gemMoonstone",
				'Y',
				"gemQuartz",
				'W',
				"gemLapis",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 9), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemQuartz",
				'Y',
				"gemLapis",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 10), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemSchorl",
				'Y',
				"gemLapis",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 11), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemMalachite",
				'Y',
				"gemLapis",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 11), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemJadeite",
				'Y',
				"gemLapis",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 12), new Object[] {
				"XYW",
				" Z ",
				'X',
				"gemCelestite",
				'Y',
				"gemSchorl",
				'W',
				new ItemStack(MainInit.gems, 1, 0),
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 13), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemClam",
				'Y',
				"gemSchorl",
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 14), new Object[] {
				"XYW",
				" Z ",
				'X',
				"gemDiamond",
				'Y',
				"gemClam",
				'W',
				new ItemStack(Items.NETHER_STAR),
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.daggerMagic, 1, 15), new Object[] {
				"XYW",
				" Z ",
				'X',
				"gemLapis",
				'Y',
				"gemClam",
				'W',
				new ItemStack(Items.NETHER_STAR),
				'Z',
				new ItemStack(MagicInit.daggerSilver)
		});

		// mace

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.macehandle, 1, 0), new Object[] {
				"  X",
				" Y ",
				"X  ",
				'X',
				"ingotGold",
				'Y',
				"ingotSilver"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 0), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemChalcedonyRed",
				'Z',
				"gemRutile",
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 0), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				new ItemStack(MainInit.gems, 1, 1),
				'Z',
				new ItemStack(Items.ENDER_EYE, 1, 0),
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 1), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemLapis",
				'Z',
				"gemCelestite",
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 1), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemLapis",
				'Z',
				"gemMoonstone",
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 2), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemSapphire",
				'Z',
				"gemSchorl",
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 3), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				new ItemStack(MainInit.gems, 1, 0),
				'Z',
				new ItemStack(Blocks.PACKED_ICE, 1, 0),
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 4), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemClam",
				'Z',
				new ItemStack(Items.EMERALD, 1, 0),
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 4), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemJadeite",
				'Z',
				new ItemStack(Items.PRISMARINE_CRYSTALS, 1, 0),
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 5), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemGarnet",
				'Z',
				new ItemStack(MainInit.gems, 1, 1),
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 5), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"dustBlaze",
				'Z',
				"dustPhosphorus",
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 6), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemMalachite",
				'Z',
				new ItemStack(MainInit.gems, 1, 0),
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 7), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemDiamond",
				'Z',
				"gemCelestite",
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 7), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemDiamond",
				'Z',
				"gemJadeite",
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 8), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemRutile",
				'Z',
				"gemPeridot",
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 8), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemRutile",
				'Z',
				"gemTopaz",
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceStarItem, 1, 9), new Object[] {
				"XYX",
				"ZWZ",
				"XYX",
				'X',
				"ingotGold",
				'Y',
				"gemSchorl",
				'Z',
				"gemBismuth",
				'W',
				"blockElestial"
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceSun, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MagicInit.maceStarItem, 1, 0),
				'Y',
				new ItemStack(MagicInit.macehandle, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceMoon, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MagicInit.maceStarItem, 1, 1),
				'Y',
				new ItemStack(MagicInit.macehandle, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceBird, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MagicInit.maceStarItem, 1, 2),
				'Y',
				new ItemStack(MagicInit.macehandle, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceIce, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MagicInit.maceStarItem, 1, 3),
				'Y',
				new ItemStack(MagicInit.macehandle, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceOcean, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MagicInit.maceStarItem, 1, 4),
				'Y',
				new ItemStack(MagicInit.macehandle, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceBurn, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MagicInit.maceStarItem, 1, 5),
				'Y',
				new ItemStack(MagicInit.macehandle, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceFlower, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MagicInit.maceStarItem, 1, 6),
				'Y',
				new ItemStack(MagicInit.macehandle, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceGlory, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MagicInit.maceStarItem, 1, 7),
				'Y',
				new ItemStack(MagicInit.macehandle, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceDrought, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MagicInit.maceStarItem, 1, 8),
				'Y',
				new ItemStack(MagicInit.macehandle, 1, 0)
		});

		DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.maceImpact, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MagicInit.maceStarItem, 1, 9),
				'Y',
				new ItemStack(MagicInit.macehandle, 1, 0)
		});
	}

	static void loadPotionRecipe() {
		PotionType awkward = PotionType.getPotionTypeForName("awkward");
		PotionType bird = ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("dcs_climate", "dcs.bird"));
		PotionType fish = ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("dcs_climate", "dcs.ocean"));
		ItemStack awk = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM, 1, 0), awkward);
		ItemStack awk_spr = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION, 1, 0), awkward);
		ItemStack awk_lng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION, 1, 0), awkward);
		ItemStack bir = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM, 1, 0), bird);
		ItemStack bir_spr = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION, 1, 0), bird);
		ItemStack bir_lng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION, 1, 0), bird);
		ItemStack fi = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM, 1, 0), fish);
		ItemStack fi_spr = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION, 1, 0), fish);
		ItemStack fi_lng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION, 1, 0), fish);

		BrewingRecipeRegistry.addRecipe(awk, "dropWhite", bir);
		BrewingRecipeRegistry.addRecipe(awk, "dropBlue", fi);

		BrewingRecipeRegistry.addRecipe(awk_spr, "dropWhite", bir_spr);
		BrewingRecipeRegistry.addRecipe(awk_spr, "dropBlue", fi_spr);

		BrewingRecipeRegistry.addRecipe(awk_lng, "dropWhite", bir_lng);
		BrewingRecipeRegistry.addRecipe(awk_lng, "dropBlue", fi_lng);

		BrewingRecipeRegistry.addRecipe(bir, "gunpowder", bir_spr);
		BrewingRecipeRegistry.addRecipe(fi, "gunpowder", fi_spr);

		BrewingRecipeRegistry.addRecipe(bir_spr, new ItemStack(Items.DRAGON_BREATH), bir_lng);
		BrewingRecipeRegistry.addRecipe(fi_spr, new ItemStack(Items.DRAGON_BREATH), fi_lng);
	}

}
