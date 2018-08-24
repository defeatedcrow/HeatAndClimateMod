package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LoadingEquipRecipe {

	public static void add(RecipeResourcesMain res) {
		if (ModuleConfig.tool) {
			String[] name = {
					"ingotBrass",
					"ingotSteel",
					"ingotSilver",
					"ingotNickelsilver",
					"gemChalcedony",
					"gemSapphire",
					"ingotTitanium",
					"gemGarnet"
			};
			for (int i = 0; i < name.length; i++) {
				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcPickaxe[i], 1, 0), new Object[] {
						"  X",
						"YYX",
						"  X",
						'X',
						name[i],
						'Y',
						"stickWood"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcAxe[i], 1, 0), new Object[] {
						" XX",
						"YYX",
						"   ",
						'X',
						name[i],
						'Y',
						"stickWood"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcSpade[i], 1, 0), new Object[] {
						"   ",
						"YYX",
						"   ",
						'X',
						name[i],
						'Y',
						"stickWood"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcSword[i], 1, 0), new Object[] {
						"   ",
						"YXX",
						"   ",
						'X',
						name[i],
						'Y',
						"stickWood"
				});
			}

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcScythe[0], 1, 0), new Object[] {
					" X ",
					"  X",
					"YYX",
					'X',
					"ingotBrass",
					'Y',
					"stickWood"
			});

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcScythe[1], 1, 0), new Object[] {
					" X ",
					"  X",
					"YYX",
					'X',
					"ingotSteel",
					'Y',
					"stickWood"
			});

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcScythe[2], 1, 0), new Object[] {
					" X ",
					"  X",
					"YYX",
					'X',
					"gemChalcedony",
					'Y',
					"stickWood"
			});

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcScythe[3], 1, 0), new Object[] {
					" X ",
					"  X",
					"YYX",
					'X',
					"gemGarnet",
					'Y',
					"stickWood"
			});

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.earthSpade, 1, 0), new Object[] {
					"YYX",
					'X',
					"gemMalachite",
					'Y',
					"stickWood"
			});

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.earthRake, 1, 0), new Object[] {
					"  X",
					"YYX",
					"  X",
					'X',
					"gemMalachite",
					'Y',
					"stickWood"
			});

			String[] name2 = {
					"ingotBrass",
					"ingotSteel",
					"gemChalcedony",
					"gemSapphire",
					"ingotTitanium"
			};
			Item[][] armor = {
					MainInit.brassArmor,
					MainInit.steelArmor,
					MainInit.chalcArmor,
					MainInit.sapphireArmor,
					MainInit.titaniumArmor
			};
			for (int i = 0; i < name2.length; i++) {
				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(armor[i][0], 1, 0), new Object[] {
						"XXX",
						"XYX",
						'X',
						name2[i],
						'Y',
						"itemCloth"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(armor[i][1], 1, 0), new Object[] {
						"X X",
						"XYX",
						"XXX",
						'X',
						name2[i],
						'Y',
						"itemCloth"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(armor[i][2], 1, 0), new Object[] {
						"XYX",
						"X X",
						"X X",
						'X',
						name2[i],
						'Y',
						"itemCloth"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(armor[i][3], 1, 0), new Object[] {
						"X X",
						"XYX",
						'X',
						name2[i],
						'Y',
						"itemCloth"
				});
			}

		}

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.linenUnder, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"X X",
				'X',
				"itemLinenCloth"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.linenCoat, 1, 0), new Object[] {
				"X X",
				"XXX",
				"XXX",
				'X',
				"itemLinenCloth"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.hoodie, 1, 0), new Object[] {
				"X X",
				"XXX",
				"XXX",
				'X',
				"itemCottonCloth"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.clothUnder, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"X X",
				'X',
				"itemCottonCloth"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.clothCoat, 1, 0), new Object[] {
				"X X",
				"XYX",
				"XYX",
				'X',
				"itemCottonCloth",
				'Y',
				"leather"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.blackSuit, 1, 0), new Object[] {
				"XYX",
				"XXX",
				"X X",
				'X',
				"itemSilkCloth",
				'Y',
				"dyeBlack"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.workerSuit, 1, 0), new Object[] {
				"XYX",
				"XXX",
				"X X",
				'X',
				"itemCottonCloth",
				'Y',
				"dyeBlue"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.leatherHat, 1, 0), new Object[] {
				"XXX",
				"XYX",
				'X',
				"itemLeather",
				'Y',
				"string"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cottonHat, 1, 0), new Object[] {
				"XXX",
				"XYX",
				'X',
				"itemCottonCloth",
				'Y',
				"string"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.hoodieB, 1, 0), new Object[] {
				"XYX",
				"XXX",
				"XXX",
				'X',
				"itemCottonCloth",
				'Y',
				"dyeBlack"
		});

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.hoodieB, 1, 0), new Object[] {
				new ItemStack(MainInit.hoodie, 1, 0),
				"dyeBlack"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.peaCoat, 1, 0), new Object[] {
				"Y Y",
				"YXY",
				"YXY",
				'X',
				"itemCottonCloth",
				'Y',
				new ItemStack(Blocks.WOOL, 1, 32767)
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.blackCoat, 1, 0), new Object[] {
				"Y Y",
				"YXY",
				"YYY",
				'X',
				"dyeBlack",
				'Y',
				"itemSilkCloth"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.magicUnder, 1, 0), new Object[] {
				"YXY",
				"YXY",
				"Y Y",
				'X',
				"stringMagic",
				'Y',
				"itemMagicCloth"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.magicCoat, 1, 0), new Object[] {
				"Y Y",
				"YXY",
				"YXY",
				'X',
				"stringMagic",
				'Y',
				"itemMagicCloth"
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.woolBoots, 1, 0), new Object[] {
				"Y Y",
				"X X",
				'X',
				"itemLeather",
				'Y',
				new ItemStack(Blocks.WOOL, 1, 32767)
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.woolWear, 1, 0), new Object[] {
				"YYY",
				"YXY",
				'X',
				"itemCloth",
				'Y',
				new ItemStack(Blocks.WOOL, 1, 32767)
		});

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.furWear, 1, 0), new Object[] {
				"YYY",
				"YXY",
				'X',
				"itemCloth",
				'Y',
				new ItemStack(Items.RABBIT_HIDE, 1, 0)
		});

		if (ModuleConfig.machine_advanced) {

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.trackSuit, 1, 0), new Object[] {
					"XYX",
					"XXX",
					"X X",
					'X',
					"itemSyntheticCloth",
					'Y',
					"dyeBlack"
			});

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.combatDress, 1, 0), new Object[] {
					"XYX",
					"XXX",
					"X X",
					'X',
					"itemSyntheticCloth",
					'Y',
					"itemLeather"
			});
		}

		if (ModuleConfig.weapon_advanced) {
			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.crossbow, 1, 0), new Object[] {
					"X  ",
					"ZY ",
					" ZY",
					'X',
					new ItemStack(Items.BOW, 1, 0),
					'Y',
					"ingotSteel",
					'Z',
					"plankWood"
			});

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 16, 0), new Object[] {
					" X",
					"Y ",
					'X',
					"ingotIron",
					'Y',
					"cobblestone"
			});

			if (ModuleConfig.weapon_advanced) {

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.gun, 1, 0), new Object[] {
						"Y  ",
						"ZY ",
						" ZX",
						'X',
						new ItemStack(Items.FLINT_AND_STEEL, 1, 0),
						'Y',
						"ingotSteel",
						'Z',
						"plankWood"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 1), new Object[] {
						"X",
						"Y",
						"Z",
						'X',
						"ingotIron",
						'Y',
						new ItemStack(MachineInit.reagent, 1, 8),
						'Z',
						"ingotBrass"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 1), new Object[] {
						"X",
						"Y",
						"Z",
						'X',
						"ingotLead",
						'Y',
						new ItemStack(MachineInit.reagent, 1, 8),
						'Z',
						"ingotBrass"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 2), new Object[] {
						"X",
						"Y",
						"Z",
						'X',
						"gravel",
						'Y',
						new ItemStack(MachineInit.reagent, 1, 8),
						'Z',
						"ingotBrass"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 3), new Object[] {
						"X",
						"Y",
						'X',
						"ingotSilver",
						'Y',
						new ItemStack(MachineInit.reagent, 1, 8)
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 4), new Object[] {
						"X",
						"Y",
						"Z",
						'X',
						"ingotBismuth",
						'Y',
						new ItemStack(MachineInit.reagent, 1, 8),
						'Z',
						"ingotBrass"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 5), new Object[] {
						"X",
						"Y",
						"Z",
						'X',
						"dustPhosphorus",
						'Y',
						"dustSulfur",
						'Z',
						"ingotBrass"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 4, 5), new Object[] {
						"X",
						"Y",
						"Z",
						'X',
						"dustGlowstone",
						'Y',
						"gemRutile",
						'Z',
						"ingotBrass"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 6), new Object[] {
						"X",
						"Y",
						"Z",
						'X',
						new ItemStack(MachineInit.reagent, 1, 12),
						'Y',
						new ItemStack(MachineInit.reagent, 1, 8),
						'Z',
						"ingotBrass"
				});

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 4, 7), new Object[] {
						" X ",
						"VYV",
						" Z ",
						'X',
						"gemSchorl",
						'Y',
						new ItemStack(Items.CHICKEN, 1, 0),
						'Z',
						"ingotBrass",
						'V',
						"leather"
				});
			}
		}
	}

}
