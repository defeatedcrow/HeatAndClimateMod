package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

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
				"stickWood"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"blockTallGrass",
				'Y',
				"stickWood"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 1), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				new ItemStack(Items.STRING, 1, 0),
				'Y',
				"stickWood"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 1), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"cropCotton",
				'Y',
				"stickWood"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 2), new Object[] {
				" X ",
				"X X",
				" X ",
				'X',
				new ItemStack(MainInit.materials, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 3), new Object[] {
				" X ",
				"X X",
				" X ",
				'X',
				new ItemStack(MainInit.materials, 1, 1)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 4), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				"plankWood",
				'Y',
				new ItemStack(Items.STICK, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 5), new Object[] {
				" X ",
				"XYX",
				" X ",
				'Y',
				"gearWood",
				'X',
				"ingotBrass"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 6), new Object[] {
				" X ",
				"XYX",
				" X ",
				'Y',
				"gearBrass",
				'X',
				"ingotNickelsilver"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 7), new Object[] {
				" X ",
				"XYX",
				" X ",
				'Y',
				"gearBrass",
				'X',
				"ingotSteel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 6), new Object[] {
				" X ",
				"XYX",
				" X ",
				'Y',
				"gearIron",
				'X',
				"ingotNickelsilver"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 7), new Object[] {
				" X ",
				"XYX",
				" X ",
				'Y',
				"gearIron",
				'X',
				"ingotSteel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stoneYagen, 1, 0), new Object[] {
				" X ",
				"XYX",
				"XXX",
				'X',
				new ItemStack(Blocks.COBBLESTONE, 1, 0),
				'Y',
				new ItemStack(MainInit.materials, 1, 4)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.brassYagen, 1, 0), new Object[] {
				" X ",
				"XYX",
				"XXX",
				'X',
				"ingotBrass",
				'Y',
				new ItemStack(MainInit.materials, 1, 4)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCInit.climate_checker, 1, 0), new Object[] {
				" Z ",
				"XYX",
				" Z ",
				'X',
				new ItemStack(Items.GOLD_INGOT, 1, 0),
				'Y',
				new ItemStack(Items.REDSTONE, 1, 0),
				'Z',
				"gemChalcedony"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stevenson_screen, 1, 0), new Object[] {
				"XZX",
				"ZYZ",
				"XZX",
				'X',
				"plankWood",
				'Y',
				new ItemStack(DCInit.climate_checker),
				'Z',
				"dyeWhite"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chamber, 1, 0), new Object[] {
				"ZXZ",
				"XYX",
				"ZXZ",
				'Y',
				new ItemStack(Blocks.FURNACE, 1, 0),
				'X',
				"stickBlaze",
				'Z',
				"ingotBrass"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.shitirin, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'Y',
				new ItemStack(Blocks.FURNACE, 1, 0),
				'X',
				new ItemStack(Items.CLAY_BALL, 1, 0),
				'Z',
				"ingotBrass"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.fuelStove, 1, 0), new Object[] {
				"XYX",
				"XZX",
				"XWX",
				'X',
				"ingotSteel",
				'Y',
				new ItemStack(Items.FLINT_AND_STEEL, 1, 32767),
				'Z',
				new ItemStack(Blocks.FURNACE, 1, 0),
				'W',
				new ItemStack(Items.BUCKET, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.bellow, 1, 0), new Object[] {
				"XYX",
				"XZX",
				"XWX",
				'X',
				"ingotIron",
				'Y',
				"stickWood",
				'Z',
				"gearWood",
				'W',
				new ItemStack(Blocks.PISTON, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.repairPutty, 1, 0), new Object[] {
				new ItemStack(Items.SLIME_BALL, 1, 0),
				"dustIron"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.repairPutty, 1, 1), new Object[] {
				new ItemStack(Items.CLAY_BALL, 1, 0),
				"dustGarnet"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.repairPutty, 1, 1), new Object[] {
				"dustClay",
				"dustGarnet"
		}));
	}

	static void loadYagenRecipe() {
		// ore -> dust
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 0), new Object[] {
				"toolNormalYagen",
				"oreCopper"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 1), new Object[] {
				"toolNormalYagen",
				"oreZinc"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 2), new Object[] {
				"toolNormalYagen",
				"oreNickel"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 3), new Object[] {
				"toolNormalYagen",
				"oreSilver"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 4), new Object[] {
				"toolNormalYagen",
				"oreGold"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 5), new Object[] {
				"toolNormalYagen",
				"oreIron"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 7), new Object[] {
				"toolNormalYagen",
				"oreMagnetite"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 1, 8), new Object[] {
				"toolNormalYagen",
				"oreTin"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 2), new Object[] {
				"toolNormalYagen",
				"oreLime"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 1, 8), new Object[] {
				"toolNormalYagen",
				"oreSalt"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 1, 9), new Object[] {
				"toolNormalYagen",
				"oreNiter"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 1, 10), new Object[] {
				"toolNormalYagen",
				"oreSulfur"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 1, 11), new Object[] {
				"toolNormalYagen",
				"oreSchorl"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 1, 12), new Object[] {
				"toolNormalYagen",
				"oreSerpentine"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 1, 14), new Object[] {
				"toolNormalYagen",
				"oreGarnet"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 6), new Object[] {
				"toolNormalYagen",
				"gemNiter"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 7), new Object[] {
				"toolNormalYagen",
				"gemSulfur"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 1), new Object[] {
				"toolNormalYagen",
				"gemChalcedony"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 1), new Object[] {
				"toolNormalYagen",
				"gemQuartz"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 8), new Object[] {
				"toolNormalYagen",
				"gemGarnet"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.miscDust, 1, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.COAL, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.DYE, 5, 15), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.BONE, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BLAZE_POWDER, 3, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Items.BLAZE_ROD, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), new Object[] {
				"toolNormalYagen",
				"gemSalt"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.foodMaterials, 1, 1), new Object[] {
				"toolNormalYagen",
				"cropWheat"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BLAZE_POWDER, 1, 0), new Object[] {
				"toolNormalYagen",
				new ItemStack(Blocks.MAGMA, 1, 0)
		}));
	}

	static void loadOreRecipes() {
		// dust -> dustBlock
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustCopper"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 1), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustZinc"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 2), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustNickel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustIron"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 3), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustSilver"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBlock, 1, 8), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"dustTin"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 4), new Object[] {
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustZinc",
				"dustZinc",
				"dustZinc"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 6), new Object[] {
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustNickel",
				"dustNickel",
				"dustZinc"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 9), new Object[] {
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustCopper",
				"dustTin",
				"dustTin"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustGraphite"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustGraphite"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustCoal"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustCoal"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 7), new Object[] {
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"gemCelestite",
				"dustLime"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 10), new Object[] {
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustIron",
				"dustChromium",
				"dustChromium",
				"dustNickel"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 10), new Object[] {
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustMagnetite",
				"dustChromium",
				"dustChromium",
				"dustNickel"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.dustBlock, 1, 11), new Object[] {
				"dustGold",
				"dustGold",
				"dustGold",
				"dustGold",
				"dustGold",
				"dustGold",
				"dustGold",
				"dustGold",
				"dustGold"
		}));

		// dustBlock -> dust
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 9, 0), new Object[] {
				new ItemStack(MainInit.dustBlock, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 9, 1), new Object[] {
				new ItemStack(MainInit.dustBlock, 1, 1)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 9, 2), new Object[] {
				new ItemStack(MainInit.dustBlock, 1, 2)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 9, 3), new Object[] {
				new ItemStack(MainInit.dustBlock, 1, 3)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 9, 5), new Object[] {
				new ItemStack(MainInit.dustBlock, 1, 5)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 9, 7), new Object[] {
				new ItemStack(MainInit.dustBlock, 1, 7)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 9, 8), new Object[] {
				new ItemStack(MainInit.dustBlock, 1, 8)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreDust, 9, 4), new Object[] {
				new ItemStack(MainInit.dustBlock, 1, 11)
		}));

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
				"Bronze",
				"SUS"
		};
		for (int i = 0; i < metal.length; i++) {
			ItemStack ingot = new ItemStack(MainInit.oreIngot, 9, i);
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.metalBlock, 1, i), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					'X',
					"ingot" + metal[i]
			}));

			GameRegistry.addRecipe(new ShapelessOreRecipe(ingot, new Object[] {
					new ItemStack(MainInit.metalBlock, 1, i)
			}));
		}

		// gem <-> gemblock
		for (int i = 0; i < 5; i++) {
			ItemStack gem = new ItemStack(MainInit.gems, 4, i);
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, i), new Object[] {
					"XX",
					"XX",
					'X',
					gem
			}));

			GameRegistry.addRecipe(new ShapelessOreRecipe(gem, new Object[] {
					new ItemStack(MainInit.gemBlock, 1, i)
			}));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, 5), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gems, 1, 8)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, 7), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gems, 1, 11)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, 9), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gems, 1, 12)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, 10), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gems, 1, 13)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, 11), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gems, 1, 14)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 4, 11), new Object[] {
				new ItemStack(MainInit.gemBlock, 1, 7)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 4, 12), new Object[] {
				new ItemStack(MainInit.gemBlock, 1, 9)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 4, 13), new Object[] {
				new ItemStack(MainInit.gemBlock, 1, 10)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gems, 4, 14), new Object[] {
				new ItemStack(MainInit.gemBlock, 1, 11)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 1, 8), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 2)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gemBlock, 9, 2), new Object[] {
				new ItemStack(MainInit.gemBlock, 1, 8)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.gemBlock, 4, 12), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(Blocks.BEDROCK, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.BEDROCK, 1, 0), new Object[] {
				new ItemStack(MainInit.gemBlock, 1, 12)
		}));
	}

	static void loadContRecipes() {
		ItemStack[] logs = new ItemStack[] {
				new ItemStack(Blocks.LOG, 8, 0),
				new ItemStack(Blocks.LOG, 8, 1),
				new ItemStack(Blocks.LOG, 8, 2),
				new ItemStack(Blocks.LOG, 8, 3),
				new ItemStack(Blocks.LOG2, 8, 0),
				new ItemStack(Blocks.LOG2, 8, 1),
				new ItemStack(Items.COAL, 8, 1)
		};
		for (int i = 0; i < logs.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.logCont, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					logs[i]
			}));

			GameRegistry.addRecipe(new ShapelessOreRecipe(logs[i], new Object[] {
					new ItemStack(MainInit.logCont, 1, i)
			}));
		}

		ItemStack[] crops = new ItemStack[] {
				new ItemStack(Items.APPLE, 8, 0),
				new ItemStack(Items.POTATO, 8, 0),
				new ItemStack(Items.CARROT, 8, 0),
				new ItemStack(Blocks.PUMPKIN, 8, 0),
				new ItemStack(Blocks.MELON_BLOCK, 8, 0),
				new ItemStack(Blocks.CACTUS, 8, 0),
				new ItemStack(Items.REEDS, 8, 0),
				new ItemStack(Items.NETHER_WART, 8, 0),
				new ItemStack(Items.DYE, 8, EnumDyeColor.BROWN.getDyeDamage()),
				new ItemStack(MainInit.bakedApple, 8, 0),
				new ItemStack(Items.BAKED_POTATO, 8, 0)
		};
		for (int i = 0; i < crops.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.cropCont, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					crops[i]
			}));

			GameRegistry.addRecipe(new ShapelessOreRecipe(crops[i], new Object[] {
					new ItemStack(MainInit.cropCont, 1, i)
			}));
		}

		ItemStack[] misc = new ItemStack[] {
				new ItemStack(Items.CLAY_BALL, 8, 0),
				new ItemStack(Items.FISH, 8, 0),
				new ItemStack(Items.LEATHER, 8, 0),
				new ItemStack(Items.RABBIT_HIDE, 8, 0)
		};
		for (int i = 0; i < misc.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.miscCont, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					misc[i]
			}));

			GameRegistry.addRecipe(new ShapelessOreRecipe(misc[i], new Object[] {
					new ItemStack(MainInit.miscCont, 1, i)
			}));
		}

		ItemStack[] enemy = new ItemStack[] {
				new ItemStack(Items.ROTTEN_FLESH, 8, 0),
				new ItemStack(Items.BONE, 8, 0),
				new ItemStack(Items.SPIDER_EYE, 8, 0),
				new ItemStack(Items.ENDER_PEARL, 8, 0),
				new ItemStack(Items.GUNPOWDER, 8, 0)
		};
		for (int i = 0; i < enemy.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dropCont, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					enemy[i]
			}));

			GameRegistry.addRecipe(new ShapelessOreRecipe(enemy[i], new Object[] {
					new ItemStack(MainInit.dropCont, 1, i)
			}));
		}

		ItemStack[] meat = new ItemStack[] {
				new ItemStack(Items.BEEF, 8, 0),
				new ItemStack(Items.PORKCHOP, 8, 0),
				new ItemStack(Items.CHICKEN, 8, 0),
				new ItemStack(Items.MUTTON, 8, 0),
				new ItemStack(Items.EGG, 8, 0)
		};
		for (int i = 0; i < meat.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.cardboard, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					meat[i]
			}));

			GameRegistry.addRecipe(new ShapelessOreRecipe(meat[i], new Object[] {
					new ItemStack(MainInit.cardboard, 1, i)
			}));
		}

		ItemStack[] dust = new ItemStack[] {
				new ItemStack(Items.SUGAR, 8, 0),
				new ItemStack(MainInit.foodMaterials, 8, 0),
				new ItemStack(MainInit.foodMaterials, 8, 1),
				new ItemStack(MainInit.foodMaterials, 8, 2)
		};
		for (int i = 0; i < dust.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dustBags, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					dust[i]
			}));

			GameRegistry.addRecipe(new ShapelessOreRecipe(dust[i], new Object[] {
					new ItemStack(MainInit.dustBags, 1, i)
			}));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.cardboard, 1, 5), new Object[] {
				"XXX",
				"X X",
				"XXX",
				'X',
				new ItemStack(Blocks.WOOL, 8, 32767)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.WOOL, 8, 0), new Object[] {
				new ItemStack(MainInit.cardboard, 1, 5)
		}));
	}

	static void loadBuildRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.selenite, 8, 1), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0),
				'Y',
				new ItemStack(Items.GLOWSTONE_DUST)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.selenite, 8, 2), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0),
				'Y',
				new ItemStack(MainInit.oreDust, 1, 3)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsGlass, 4, 0), new Object[] {
				"X  ",
				"XX ",
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsGlass, 4, 0), new Object[] {
				"  X",
				" XX",
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsGypsum, 4, 0), new Object[] {
				"X  ",
				"XX ",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 3)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsGypsum, 4, 0), new Object[] {
				"  X",
				" XX",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 3)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsMarble, 4, 0), new Object[] {
				"X  ",
				"XX ",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 6)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsMarble, 4, 0), new Object[] {
				"  X",
				" XX",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 6)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsSerpentine, 4, 0), new Object[] {
				"X  ",
				"XX ",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 9)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsSerpentine, 4, 0), new Object[] {
				"  X",
				" XX",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 9)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsBedrock, 4, 0), new Object[] {
				"X  ",
				"XX ",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 12)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stairsBedrock, 4, 0), new Object[] {
				"  X",
				" XX",
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 12)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.halfSlab, 3, 1), new Object[] {
				"XXX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.halfSlab, 3, 0), new Object[] {
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 3)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.halfSlab, 3, 2), new Object[] {
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 6)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.halfSlab, 3, 3), new Object[] {
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 9)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.halfSlab, 3, 4), new Object[] {
				"XXX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 12)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.bricks, 1, 0), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 3)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.bricks, 1, 1), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 6)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.bricks, 1, 2), new Object[] {
				"XX",
				"XX",
				'X',
				new ItemStack(MainInit.ores_2, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.builds, 1, 0), new Object[] {
				"dustLime",
				"dustLime",
				new ItemStack(Blocks.GRAVEL)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.builds, 1, 1), new Object[] {
				"dyeRed",
				new ItemStack(MainInit.builds, 1, 0),
				new ItemStack(Blocks.GLASS)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.builds, 1, 2), new Object[] {
				"dyeBlue",
				new ItemStack(MainInit.builds, 1, 0),
				new ItemStack(Blocks.GLASS)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.builds, 1, 3), new Object[] {
				"dyeYellow",
				new ItemStack(MainInit.builds, 1, 0),
				new ItemStack(Blocks.GLASS)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.builds, 1, 4), new Object[] {
				"dyeBlack",
				new ItemStack(MainInit.builds, 1, 0),
				new ItemStack(Blocks.GLASS)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gemBlock, 4, 3), new Object[] {
				new ItemStack(MainInit.bricks, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.gemBlock, 4, 6), new Object[] {
				new ItemStack(MainInit.bricks, 1, 1)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.ores_2, 4, 0), new Object[] {
				new ItemStack(MainInit.bricks, 1, 2)
		}));

		Object[] chal = new Object[] {
				new ItemStack(MainInit.gemBlock, 1, 0),
				new ItemStack(MainInit.gemBlock, 1, 1),
				new ItemStack(MainInit.gemBlock, 1, 2)
		};
		for (int i = 0; i < 3; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, i), new Object[] {
					"X",
					"Y",
					'X',
					chal[i],
					'Y',
					"dustRedstone"
			}));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 4 + i), new Object[] {
					" X ",
					"XYX",
					" X ",
					'X',
					new ItemStack(Blocks.GLASS, 1, 0),
					'Y',
					new ItemStack(MainInit.chalLamp, 1, i)
			}));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 8 + i), new Object[] {
					"Z",
					"Y",
					'Y',
					"ingotCopper",
					'Z',
					new ItemStack(MainInit.chalLamp, 1, i)
			}));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 12 + i), new Object[] {
					"Y",
					"Z",
					'Y',
					"ingotCopper",
					'Z',
					new ItemStack(MainInit.chalLamp, 1, i)
			}));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 3), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				"plankWood",
				'Y',
				new ItemStack(MainInit.chalLamp, 1, 2)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 7), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				new ItemStack(Blocks.GLASS, 1, 0),
				'Y',
				new ItemStack(MainInit.chalLamp, 1, 3)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 11), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MainInit.chalLamp, 1, 3),
				'Y',
				"ingotCopper"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalLamp, 1, 15), new Object[] {
				"Y",
				"X",
				'X',
				new ItemStack(MainInit.chalLamp, 1, 3),
				'Y',
				"ingotCopper"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.wallLamp, 1, 0), new Object[] {
				"YX",
				'X',
				new ItemStack(MainInit.chalLamp, 1, 0),
				'Y',
				"ingotCopper"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.wallLamp, 1, 1), new Object[] {
				"YX",
				'X',
				new ItemStack(MainInit.chalLamp, 1, 1),
				'Y',
				"ingotCopper"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.wallLamp, 1, 2), new Object[] {
				"YX",
				'X',
				new ItemStack(MainInit.chalLamp, 1, 2),
				'Y',
				"ingotCopper"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.wallLamp, 1, 3), new Object[] {
				"YX",
				'X',
				new ItemStack(MainInit.chalLamp, 1, 3),
				'Y',
				"ingotCopper"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.tableMarble, 1, 0), new Object[] {
				"XXX",
				" X ",
				" X ",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 6)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.tableGypsum, 1, 0), new Object[] {
				"XXX",
				" X ",
				" X ",
				'X',
				new ItemStack(MainInit.gemBlock, 1, 3)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.tableWood, 1, 0), new Object[] {
				"XXX",
				" X ",
				" X ",
				'X',
				new ItemStack(Blocks.PLANKS, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.tableDark, 1, 0), new Object[] {
				"XXX",
				" X ",
				" X ",
				'X',
				new ItemStack(Blocks.PLANKS, 1, 5)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.carpetRed, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(Blocks.CARPET, 1, 14),
				'Y',
				"plankWood"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.carpetWhite, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(Blocks.CARPET, 1, 0),
				'Y',
				"plankWood"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.carpetGray, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(Blocks.CARPET, 1, 15),
				'Y',
				"plankWood"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sofaBlack, 1, 0), new Object[] {
				" X ",
				"YYY",
				"ZZZ",
				'X',
				"dyeBlack",
				'Y',
				"itemCloth",
				'Z',
				new ItemStack(Blocks.WOOL, 1, 32767)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sofaRed, 1, 0), new Object[] {
				" X ",
				"YYY",
				"ZZZ",
				'X',
				"dyeRed",
				'Y',
				"itemCloth",
				'Z',
				new ItemStack(Blocks.WOOL, 1, 32767)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stoolBlack, 1, 0), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"dyeBlack",
				'Y',
				"itemCloth",
				'Z',
				"blockMarble"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.stoolRed, 1, 0), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"dyeRed",
				'Y',
				"itemCloth",
				'Z',
				"plankWood"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chestMarble, 1, 0), new Object[] {
				"XXX",
				" Y ",
				"XXX",
				'X',
				"blockMarble",
				'Y',
				new ItemStack(Blocks.CHEST, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chestWood, 1, 0), new Object[] {
				"XXX",
				" Y ",
				"XXX",
				'X',
				"plankWood",
				'Y',
				new ItemStack(Blocks.CHEST, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chestMetal, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"ingotSteel",
				'Y',
				new ItemStack(Blocks.CHEST, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chestMagnet, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"ingotMagnet",
				'Y',
				new ItemStack(Blocks.CHEST, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.plate, 2, 0), new Object[] {
				"X X",
				'X',
				"ingotIron"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.plate, 2, 1), new Object[] {
				"X X",
				'X',
				new ItemStack(Blocks.IRON_BARS, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sinkMetal, 1, 0), new Object[] {
				"X X",
				"XXX",
				'X',
				"ingotNickelsilver"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.sinkMetal, 1, 0), new Object[] {
				new ItemStack(MainInit.sinkChest, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.sinkChest, 1, 0), new Object[] {
				new ItemStack(MainInit.sinkMetal, 1, 0)
		}));

	}

	private static void loadEquipsRecipes() {
		String[] name = {
				"ingotBrass",
				"ingotSteel",
				"ingotSilver",
				"ingotNickelsilver",
				"gemChalcedony",
				"gemSapphire"
		};
		for (int i = 0; i < name.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcPickaxe[i], 1, 0), new Object[] {
					"  X",
					"YYX",
					"  X",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.STICK)
			}));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcAxe[i], 1, 0), new Object[] {
					" XX",
					"YYX",
					"   ",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.STICK)
			}));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcSpade[i], 1, 0), new Object[] {
					"   ",
					"YYX",
					"   ",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.STICK)
			}));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.dcSword[i], 1, 0), new Object[] {
					"   ",
					"YXX",
					"   ",
					'X',
					name[i],
					'Y',
					new ItemStack(Items.STICK)
			}));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.brassArmor[0], 1, 0), new Object[] {
				"XXX",
				"XYX",
				'X',
				"ingotBrass",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.steelArmor[0], 1, 0), new Object[] {
				"XXX",
				"XYX",
				'X',
				"ingotSteel",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalcArmor[0], 1, 0), new Object[] {
				"XXX",
				"X X",
				'X',
				"gemChalcedony"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sapphireArmor[0], 1, 0), new Object[] {
				"XXX",
				"X X",
				'X',
				"gemSapphire"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.brassArmor[1], 1, 0), new Object[] {
				"X X",
				"XYX",
				"XXX",
				'X',
				"ingotBrass",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.steelArmor[1], 1, 0), new Object[] {
				"X X",
				"XYX",
				"XXX",
				'X',
				"ingotSteel",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalcArmor[1], 1, 0), new Object[] {
				"X X",
				"XYX",
				"XXX",
				'X',
				"gemChalcedony",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sapphireArmor[1], 1, 0), new Object[] {
				"X X",
				"XXX",
				"XXX",
				'X',
				"gemSapphire"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.brassArmor[2], 1, 0), new Object[] {
				"XYX",
				"X X",
				"X X",
				'X',
				"ingotBrass",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.steelArmor[2], 1, 0), new Object[] {
				"XYX",
				"X X",
				"X X",
				'X',
				"ingotSteel",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalcArmor[2], 1, 0), new Object[] {
				"XYX",
				"X X",
				"X X",
				'X',
				"gemChalcedony",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sapphireArmor[2], 1, 0), new Object[] {
				"XYX",
				"X X",
				"X X",
				'X',
				"gemSapphire",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.brassArmor[3], 1, 0), new Object[] {
				"X X",
				"XYX",
				'X',
				"ingotBrass",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.steelArmor[3], 1, 0), new Object[] {
				"X X",
				"XYX",
				'X',
				"ingotSteel",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.chalcArmor[3], 1, 0), new Object[] {
				"X X",
				"XYX",
				'X',
				"gemChalcedony",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.sapphireArmor[3], 1, 0), new Object[] {
				"X X",
				"XYX",
				'X',
				"gemSapphire",
				'Y',
				"itemCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.linenUnder, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"X X",
				'X',
				"itemLinenCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.linenCourt, 1, 0), new Object[] {
				"X X",
				"XXX",
				"XXX",
				'X',
				"itemLinenCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.clothUnder, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"X X",
				'X',
				"itemCottonCloth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.blackSuit, 1, 0), new Object[] {
				"XYX",
				"XXX",
				"X X",
				'X',
				"itemCottonCloth",
				'Y',
				"dyeBlack"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.workerSuit, 1, 0), new Object[] {
				"XYX",
				"XXX",
				"X X",
				'X',
				"itemCottonCloth",
				'Y',
				"dyeBlue"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.leatherHat, 1, 0), new Object[] {
				"XXX",
				"XYX",
				'X',
				"itemLeather",
				'Y',
				"string"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.cottonHat, 1, 0), new Object[] {
				"XXX",
				"XYX",
				'X',
				"itemCottonCloth",
				'Y',
				"string"
		}));

	}

	static void loadAnotherRecipes() {
		// 火打ち石のアナザー
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.FLINT_AND_STEEL, 1, 0), new Object[] {
				new ItemStack(MainInit.ores, 1, 4),
				"gemChalcedony"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.FLINT_AND_STEEL, 1, 0), new Object[] {
				new ItemStack(MainInit.ores, 1, 4),
				new ItemStack(Items.FLINT)
		}));

		// 矢
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.ARROW, 8, 0), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				"gemChalcedony",
				'Y',
				"stickWood",
				'Z',
				"feather"
		}));

		// ブレイズロッド救済用
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.BLAZE_ROD, 1, 0), new Object[] {
				"XXX",
				"YYY",
				"ZZZ",
				'X',
				"dustCrystal",
				'Y',
				"dustBlaze",
				'Z',
				"dustSulfur"
		}));

		// 変換
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.DYE, 1, 15), new Object[] {
				new ItemStack(MainInit.miscDust, 1, 5)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainInit.oreIngot, 1, 5), new Object[] {
				new ItemStack(MainInit.oreIngot, 1, 7)
		}));
	}
}
