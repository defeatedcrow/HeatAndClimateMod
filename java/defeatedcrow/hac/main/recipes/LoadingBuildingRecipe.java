package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.MainUtil;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class LoadingBuildingRecipe {

	public static void add(RecipeResourcesMain res) {
		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.selenite, 8, 1), new Object[] {
				"XXX", "XYX", "XXX", 'X', new ItemStack(MainInit.selenite, 1, 0), 'Y',
				new ItemStack(Items.GLOWSTONE_DUST)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.selenite, 8, 2), new Object[] {
				"XXX", "XYX", "XXX", 'X', new ItemStack(MainInit.selenite, 1, 0), 'Y',
				new ItemStack(MainInit.oreDust, 1, 3)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stairsGlass, 4, 0), new Object[] {
				"X  ", "XX ", "XXX", 'X', new ItemStack(MainInit.selenite, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stairsGlass, 4, 0), new Object[] {
				"  X", " XX", "XXX", 'X', new ItemStack(MainInit.selenite, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stairsGypsum, 4, 0), new Object[] {
				"X  ", "XX ", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 3)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stairsGypsum, 4, 0), new Object[] {
				"  X", " XX", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 3)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stairsMarble, 4, 0), new Object[] {
				"X  ", "XX ", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 6)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stairsMarble, 4, 0), new Object[] {
				"  X", " XX", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 6)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stairsSerpentine, 4, 0), new Object[] {
				"X  ", "XX ", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 9)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stairsSerpentine, 4, 0), new Object[] {
				"  X", " XX", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 9)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stairsBedrock, 4, 0), new Object[] {
				"X  ", "XX ", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 12)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stairsBedrock, 4, 0), new Object[] {
				"  X", " XX", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 12)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab, 6, 1), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.selenite, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab, 6, 0), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 3)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab, 6, 2), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 6)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab, 6, 3), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 9)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab, 6, 4), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 12)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab2, 6, 0), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab2, 6, 1), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 1)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab2, 6, 2), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 2)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab2, 6, 3), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.chalLamp, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab2, 6, 4), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.chalLamp, 1, 1)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab2, 6, 5), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.chalLamp, 1, 2)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.halfSlab2, 6, 6), new Object[] {
				"XXX", 'X', new ItemStack(MainInit.chalLamp, 1, 3)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceGypsum, 6, 0), new Object[] {
				"XXX", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 3)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceMarble, 6, 0), new Object[] {
				"XXX", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 6)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceSerpentine, 6, 0), new Object[] {
				"XXX", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 9)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceBedrock, 6, 0), new Object[] {
				"XXX", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 12)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.bricks, 4, 0), new Object[] {
				"XX", "XX", 'X', new ItemStack(MainInit.gemBlock, 1, 3)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.bricks, 4, 1), new Object[] {
				"XX", "XX", 'X', new ItemStack(MainInit.gemBlock, 1, 6)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.bricks, 4, 2), new Object[] {
				"XX", "XX", 'X', new ItemStack(MainInit.ores_2, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.builds, 1, 0), new Object[] {
				"dustLime", "dustLime", new ItemStack(Blocks.GRAVEL)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.builds, 1, 1), new Object[] {
				"dyeRed", new ItemStack(MainInit.builds, 1, 0), new ItemStack(Blocks.GLASS)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.builds, 1, 2), new Object[] {
				"dyeBlue", new ItemStack(MainInit.builds, 1, 0), new ItemStack(Blocks.GLASS)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.builds, 1, 3), new Object[] {
				"dyeYellow", new ItemStack(MainInit.builds, 1, 0), new ItemStack(Blocks.GLASS)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.builds, 1, 4), new Object[] {
				"dyeBlack", new ItemStack(MainInit.builds, 1, 0), new ItemStack(Blocks.GLASS)
		});

		if (ModuleConfig.machine) {
			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.builds, 1, 5), new Object[] {
					"gravel", new ItemStack(MachineInit.reagent, 1, 0)
			});
		} else {
			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.builds, 1, 5), new Object[] {
					"gravel", "slimeball", "dyeBlack"
			});
		}

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.builds, 1, 6), new Object[] {
				"dustLime", "blockTallGrass", "cropSeaweed"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.builds, 1, 6), new Object[] {
				"dustLime", "cropWheat", "cropSeaweed"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.gemBlock, 1, 3), new Object[] {
				new ItemStack(MainInit.bricks, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.gemBlock, 1, 6), new Object[] {
				new ItemStack(MainInit.bricks, 1, 1)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.ores_2, 1, 0), new Object[] {
				new ItemStack(MainInit.bricks, 1, 2)
		});

		Object[] chal = new Object[] {
				new ItemStack(MainInit.gemBlock, 1, 0), new ItemStack(MainInit.gemBlock, 1, 1),
				new ItemStack(MainInit.gemBlock, 1, 2)
		};
		for (int i = 0; i < 3; i++) {
			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chalLamp, 1, i), new Object[] {
					"X", "Y", 'X', chal[i], 'Y', "dustRedstone"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chalLamp, 1, 4 + i), new Object[] {
					" X ", "XYX", " X ", 'X', new ItemStack(Blocks.GLASS, 1, 0), 'Y',
					new ItemStack(MainInit.chalLamp, 1, i)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chalLamp, 1, 8 + i), new Object[] {
					"Z", "Y", 'Y', "ingotCopper", 'Z', new ItemStack(MainInit.chalLamp, 1, i)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chalLamp, 1, 12 + i), new Object[] {
					"Y", "Z", 'Y', "ingotCopper", 'Z', new ItemStack(MainInit.chalLamp, 1, i)
			});
		}

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chalLamp, 1, 3), new Object[] {
				" X ", "XYX", " X ", 'X', "plankWood", 'Y', new ItemStack(MainInit.chalLamp, 1, 2)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chalLamp, 1, 7), new Object[] {
				" X ", "XYX", " X ", 'X', new ItemStack(Blocks.GLASS, 1, 0), 'Y', new ItemStack(MainInit.chalLamp, 1, 3)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chalLamp, 1, 11), new Object[] {
				"X", "Y", 'X', new ItemStack(MainInit.chalLamp, 1, 3), 'Y', "ingotCopper"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chalLamp, 1, 15), new Object[] {
				"Y", "X", 'X', new ItemStack(MainInit.chalLamp, 1, 3), 'Y', "ingotCopper"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.wallLamp, 1, 0), new Object[] {
				"YX", 'X', new ItemStack(MainInit.chalLamp, 1, 0), 'Y', "ingotCopper"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.wallLamp, 1, 1), new Object[] {
				"YX", 'X', new ItemStack(MainInit.chalLamp, 1, 1), 'Y', "ingotCopper"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.wallLamp, 1, 2), new Object[] {
				"YX", 'X', new ItemStack(MainInit.chalLamp, 1, 2), 'Y', "ingotCopper"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.wallLamp, 1, 3), new Object[] {
				"YX", 'X', new ItemStack(MainInit.chalLamp, 1, 3), 'Y', "ingotCopper"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chandelierGypsum, 1, 0), new Object[] {
				" Z ", "XYX", " X ", 'X', "gemGypsum", 'Y', new ItemStack(MainInit.chalLamp, 1, 2), 'Z', "ingotCopper"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.tableMarble, 1, 0), new Object[] {
				"XXX", " X ", " X ", 'X', new ItemStack(MainInit.gemBlock, 1, 6)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.tableGypsum, 1, 0), new Object[] {
				"XXX", " X ", " X ", 'X', new ItemStack(MainInit.gemBlock, 1, 3)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.tableWood, 1, 0), new Object[] {
				"XXX", " X ", " X ", 'X', new ItemStack(Blocks.PLANKS, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.tableDark, 1, 0), new Object[] {
				"XXX", " X ", " X ", 'X', new ItemStack(Blocks.PLANKS, 1, 5)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.squaretableWood, 1, 0), new Object[] {
				"XXX", "X X", 'X', "plankWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.squaretableMarble, 1, 0), new Object[] {
				"XXX", "X X", 'X', "blockMarble"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.squaretableChecker, 1, 0), new Object[] {
				"XXX", "X X", 'X', "blockGypsum"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.squaretableBlack, 1, 0), new Object[] {
				"XXX", "X X", 'X', new ItemStack(MainInit.gemBlock, 1, 12)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.carpetRed, 1, 0), new Object[] {
				"X", "Y", 'X', new ItemStack(Blocks.CARPET, 1, 14), 'Y', "plankWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.carpetWhite, 1, 0), new Object[] {
				"X", "Y", 'X', new ItemStack(Blocks.CARPET, 1, 0), 'Y', "plankWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.carpetGray, 1, 0), new Object[] {
				"X", "Y", 'X', new ItemStack(Blocks.CARPET, 1, 15), 'Y', "plankWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.sofaBlack, 1, 0), new Object[] {
				" X ", "YYY", "ZZZ", 'X', "dyeBlack", 'Y', "itemCloth", 'Z', new ItemStack(Blocks.WOOL, 1, 32767)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.sofaRed, 1, 0), new Object[] {
				" X ", "YYY", "ZZZ", 'X', "dyeRed", 'Y', "itemCloth", 'Z', new ItemStack(Blocks.WOOL, 1, 32767)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stoolBlack, 1, 0), new Object[] {
				"X", "Y", "Z", 'X', "dyeBlack", 'Y', "itemCloth", 'Z', "blockMarble"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.stoolRed, 1, 0), new Object[] {
				"X", "Y", "Z", 'X', "dyeRed", 'Y', "itemCloth", 'Z', "plankWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chairWood, 1, 0), new Object[] {
				"  X", "XYX", "X X", 'X', "plankWood", 'Y', "itemCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chairMarble, 1, 0), new Object[] {
				"  X", "XYX", "X X", 'X', "blockMarble", 'Y', "itemCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chairChecker, 1, 0), new Object[] {
				"  X", "XYX", "X X", 'X', "blockGypsum", 'Y', "itemCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chairBlack, 1, 0), new Object[] {
				"  X", "XYX", "X X", 'X', new ItemStack(MainInit.gemBlock, 1, 12), 'Y', "itemCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chestMarble, 1, 0), new Object[] {
				"XXX", " Y ", "XXX", 'X', "blockMarble", 'Y', new ItemStack(Blocks.CHEST, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chestWood, 1, 0), new Object[] {
				"XXX", " Y ", "XXX", 'X', "plankWood", 'Y', new ItemStack(Blocks.CHEST, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chestChecker, 1, 0), new Object[] {
				"XXX", " Y ", "XXX", 'X', "blockGypsum", 'Y', new ItemStack(Blocks.CHEST, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chestBlack, 1, 0), new Object[] {
				"XXX", " Y ", "XXX", 'X', new ItemStack(MainInit.gemBlock, 1, 12), 'Y',
				new ItemStack(Blocks.CHEST, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.wallshelfMarble, 1, 0), new Object[] {
				"X X", "XYX", "X X", 'X', "blockMarble", 'Y', new ItemStack(Blocks.CHEST, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.wallshelfWood, 1, 0), new Object[] {
				"X X", "XYX", "X X", 'X', "plankWood", 'Y', new ItemStack(Blocks.CHEST, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.wallshelfChecker, 1, 0), new Object[] {
				"X X", "XYX", "X X", 'X', "blockGypsum", 'Y', new ItemStack(Blocks.CHEST, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.wallshelfBlack, 1, 0), new Object[] {
				"X X", "XYX", "X X", 'X', new ItemStack(MainInit.gemBlock, 1, 12), 'Y',
				new ItemStack(Blocks.CHEST, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.awning, 1, 0), new Object[] {
				"XXX", "Y Y", 'X', "plankWood", 'Y', "stickWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.awning, 1, 1), new Object[] {
				"XXX", "Y Y", 'X', "ingotAluminium", 'Y', "stickWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.awning, 1, 1), new Object[] {
				"XXX", "Y Y", 'X', "ingotIron", 'Y', "stickWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.awning, 1, 2), new Object[] {
				"XXX", "Y Y", 'X', "itemLinenCloth", 'Y', "stickWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.awning, 1, 3), new Object[] {
				"XXX", "Y Y", 'X', "itemCottonCloth", 'Y', "stickWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.flowerPot, 1, 0), new Object[] {
				" X ", "X X", "XXX", 'X', "gemChalcedony"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.flowerPot, 1, 1), new Object[] {
				" X ", "X X", "XXX", 'X', new ItemStack(Blocks.HARDENED_CLAY, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.itemDoorMarble, 2, 0), new Object[] {
				"XX", "XX", "XX", 'X', "blockMarble"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.itemDoorSteel, 2, 0), new Object[] {
				"XX", "YY", "XX", 'X', "ingotSteel", 'Y', new ItemStack(Blocks.IRON_BARS, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.realtimeClock, 1, 0), new Object[] {
				"X", "Y", 'X', "gemSapphire", 'Y', new ItemStack(Items.CLOCK, 1, 0)
		});

		for (int i = 0; i < 16; i++) {
			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.clayBricks, 4, i), new Object[] {
					"XX", "XX", 'X', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, i)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(Blocks.STAINED_HARDENED_CLAY, 4, i),
					new Object[] {
							"XX", "XX", 'X', new ItemStack(MainInit.clayBricks, 1, i)
					});
		}

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.curtainWhite, 1, 0), new Object[] {
				"XX", "YY", "YY", 'X', "stickWood", 'Y', "itemCloth"
		});
	}

	public static void advanced(RecipeResourcesMain res) {
		if (ModuleConfig.build_advanced) {

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chestMetal, 1, 0), new Object[] {
					"XXX", "XYX", "XXX", 'X', "ingotSteel", 'Y', new ItemStack(Blocks.CHEST, 1, 0)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chestMagnet, 1, 0), new Object[] {
					"XXX", "XYX", "XXX", 'X', "ingotMagnet", 'Y', new ItemStack(Blocks.CHEST, 1, 0)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chestVillage, 1, 0), new Object[] {
					"XZX", "XYX", "XXX", 'X', "ingotGold", 'Y', new ItemStack(Blocks.CHEST, 1, 0), 'Z', "gemEmerald"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.chestVillage, 1, 0), new Object[] {
					"XZX", "XYX", "XXX", 'X', "ingotGold", 'Y', new ItemStack(Blocks.CHEST, 1, 0), 'Z', "gemPeridot"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.plate, 2, 0), new Object[] {
					"X X", 'X', "ingotIron"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.plate, 2, 1), new Object[] {
					"X X", 'X', new ItemStack(Blocks.IRON_BARS, 1, 0)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.sinkMetal, 1, 0), new Object[] {
					"X X", "XXX", 'X', "ingotNickelsilver"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.sinkMetal, 1, 0), new Object[] {
					new ItemStack(MainInit.sinkChest, 1, 0)
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.sinkChest, 1, 0), new Object[] {
					new ItemStack(MainInit.sinkMetal, 1, 0)
			});

			for (int i = 0; i < 16; i++) {
				DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.syntheticBlock, 8, i),
						new Object[] {
								"XXX", "XYX", "XXX", 'X', new ItemStack(MainInit.syntheticBlock, 1, 32767), 'Y',
								MainUtil.DYES[i]
						});
			}

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceAluminium, 6, 0), new Object[] {
					"XXX", "XXX", 'X', "ingotAluminium"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceAluminium, 6, 0), new Object[] {
					"XXX", "XXX", 'X', "ingotAluminum"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceNet, 6, 0), new Object[] {
					"XYX", "XYX", 'X', "ingotAluminium", 'Y', new ItemStack(Blocks.IRON_BARS, 1, 0)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceGlass, 6, 0), new Object[] {
					"XYX", "XYX", 'X', "ingotAluminium", 'Y', new ItemStack(Blocks.GLASS, 1, 0)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceGlass, 6, 0), new Object[] {
					"XYX", "XYX", 'X', "ingotAluminium", 'Y', "blockGlass"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceNet, 6, 0), new Object[] {
					"XYX", "XYX", 'X', "ingotAluminum", 'Y', new ItemStack(Blocks.IRON_BARS, 1, 0)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceGlass, 6, 0), new Object[] {
					"XYX", "XYX", 'X', "ingotAluminum", 'Y', new ItemStack(Blocks.GLASS, 1, 0)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceGlass, 6, 0), new Object[] {
					"XYX", "XYX", 'X', "ingotAluminum", 'Y', "blockGlass"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceLadder, 6, 0), new Object[] {
					"X X", "XXX", "X X", 'X', "ingotAluminium"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceLadder, 6, 0), new Object[] {
					"X X", "XXX", "X X", 'X', "ingotAluminum"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.pillarSteel, 6, 0), new Object[] {
					"XX", "YY", "XX", 'X', "ingotSteel", 'Y', "ingotIron"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceSteel, 6, 0), new Object[] {
					"XYX", "XYX", 'X', "ingotSteel", 'Y', "ingotIron"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceNetSteel, 6, 0), new Object[] {
					"XYX", "XYX", 'X', "ingotSteel", 'Y', new ItemStack(Blocks.IRON_BARS, 1, 0)
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.fenceLadderSteel, 6, 0), new Object[] {
					"X X", "XYX", "X X", 'X', "ingotSteel", 'Y', "ingotIron"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.hedgeSpring, 1, 0), new Object[] {
					"XXX", "YYY", 'X', new ItemStack(Blocks.SAPLING, 1, 0), 'Y', "stickWood"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.hedgeSummer, 1, 0), new Object[] {
					"XXX", "YYY", 'X', new ItemStack(Blocks.SAPLING, 1, 5), 'Y', "stickWood"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.hedgeAutumn, 1, 0), new Object[] {
					"XXX", "YYY", 'X', new ItemStack(Blocks.SAPLING, 1, 2), 'Y', "stickWood"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.hedgeWinter, 1, 0), new Object[] {
					"XXX", "YYY", 'X', new ItemStack(Blocks.SAPLING, 1, 1), 'Y', "stickWood"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.lampCarbide, 1, 0), new Object[] {
					"XXX", "YZY", "XXX", 'X', "ingotBrass", 'Y', "blockGlass", 'Z', "gemCarbide"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.lampGas, 1, 0), new Object[] {
					"XXX", "YZY", "XXX", 'X', "ingotSteel", 'Y', "blockGlass", 'Z', "gemCarbide"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.desiccant, 1, 0), new Object[] {
					new ItemStack(Items.PAPER, 1, 0), "dustLime"
			});

			DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(Items.DYE, 1, 15), new Object[] {
					new ItemStack(MainInit.desiccant, 1, 3)
			});

			if (ModuleConfig.machine_advanced) {
				DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MainInit.freezepack, 1, 0),
						new Object[] {
								new ItemStack(Items.PAPER, 1, 0), new ItemStack(MachineInit.reagent, 1, 4),
								"bucketWater"
						});

				DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(Items.DYE, 1, 15), new Object[] {
						new ItemStack(MainInit.freezepack, 1, 3)
				});

			}

		}
	}

}
