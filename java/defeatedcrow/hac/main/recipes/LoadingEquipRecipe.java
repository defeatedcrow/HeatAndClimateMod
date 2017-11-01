package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LoadingEquipRecipe {

	public static void add(RecipeResourcesMain res) {
		String[] name = {
				"ingotBrass", "ingotSteel", "ingotSilver", "ingotNickelsilver", "gemChalcedony", "gemSapphire",
				"ingotTitanium"
		};
		for (int i = 0; i < name.length; i++) {
			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.dcPickaxe[i], 1, 0), new Object[] {
					"  X", "YYX", "  X", 'X', name[i], 'Y', "stickWood"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.dcAxe[i], 1, 0), new Object[] {
					" XX", "YYX", "   ", 'X', name[i], 'Y', "stickWood"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.dcSpade[i], 1, 0), new Object[] {
					"   ", "YYX", "   ", 'X', name[i], 'Y', "stickWood"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.dcSword[i], 1, 0), new Object[] {
					"   ", "YXX", "   ", 'X', name[i], 'Y', "stickWood"
			});
		}

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.dcScythe[0], 1, 0), new Object[] {
				" X ", "  X", "YYX", 'X', "ingotBrass", 'Y', "stickWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.dcScythe[1], 1, 0), new Object[] {
				" X ", "  X", "YYX", 'X', "ingotSteel", 'Y', "stickWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.dcScythe[2], 1, 0), new Object[] {
				" X ", "  X", "YYX", 'X', "gemChalcedony", 'Y', "stickWood"
		});

		String[] name2 = {
				"ingotBrass", "ingotSteel", "gemChalcedony", "gemSapphire", "ingotTitanium"
		};
		Item[][] armor = {
				MainInit.brassArmor, MainInit.steelArmor, MainInit.chalcArmor, MainInit.sapphireArmor,
				MainInit.titaniumArmor
		};
		for (int i = 0; i < name2.length; i++) {
			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(armor[i][0], 1, 0), new Object[] {
					"XXX", "XYX", 'X', name2[i], 'Y', "itemCloth"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(armor[i][1], 1, 0), new Object[] {
					"X X", "XYX", "XXX", 'X', name2[i], 'Y', "itemCloth"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(armor[i][2], 1, 0), new Object[] {
					"XYX", "X X", "X X", 'X', name2[i], 'Y', "itemCloth"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(armor[i][3], 1, 0), new Object[] {
					"X X", "XYX", 'X', name2[i], 'Y', "itemCloth"
			});
		}

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.linenUnder, 1, 0), new Object[] {
				"XXX", "XXX", "X X", 'X', "itemLinenCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.linenCourt, 1, 0), new Object[] {
				"X X", "XXX", "XXX", 'X', "itemLinenCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.hoodie, 1, 0), new Object[] {
				"X X", "XXX", "XXX", 'X', "itemCottonCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.clothUnder, 1, 0), new Object[] {
				"XXX", "XXX", "X X", 'X', "itemCottonCloth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.blackSuit, 1, 0), new Object[] {
				"XYX", "XXX", "X X", 'X', "itemCottonCloth", 'Y', "dyeBlack"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.workerSuit, 1, 0), new Object[] {
				"XYX", "XXX", "X X", 'X', "itemCottonCloth", 'Y', "dyeBlue"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.leatherHat, 1, 0), new Object[] {
				"XXX", "XYX", 'X', "itemLeather", 'Y', "string"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.cottonHat, 1, 0), new Object[] {
				"XXX", "XYX", 'X', "itemCottonCloth", 'Y', "string"
		});

		if (ModuleConfig.machine_advanced) {

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.trackSuit, 1, 0), new Object[] {
					"XYX", "XXX", "X X", 'X', "itemSyntheticCloth", 'Y', "dyeBlack"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.combatDress, 1, 0), new Object[] {
					"XYX", "XXX", "X X", 'X', "itemSyntheticCloth", 'Y', "itemLeather"
			});
		}

		if (ModuleConfig.weapon_advanced) {
			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.crossbow, 1, 0), new Object[] {
					"X  ", "ZY ", " ZY", 'X', new ItemStack(Items.BOW, 1, 0), 'Y', "ingotSteel", 'Z', "plankWood"
			});

			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.cartridge, 16, 0), new Object[] {
					" X", "Y ", 'X', "ingotIron", 'Y', "cobblestone"
			});

			if (ModuleConfig.weapon_advanced) {

				DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.gun, 1, 0), new Object[] {
						"Y  ", "ZY ", " ZX", 'X', new ItemStack(Items.FLINT_AND_STEEL, 1, 0), 'Y', "ingotSteel", 'Z',
						"plankWood"
				});

				DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.cartridge, 8, 1), new Object[] {
						"X", "Y", "Z", 'X', "ingotIron", 'Y', new ItemStack(MachineInit.reagent, 1, 8), 'Z',
						"ingotBrass"
				});

				DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.cartridge, 8, 1), new Object[] {
						"X", "Y", "Z", 'X', "ingotLead", 'Y', new ItemStack(MachineInit.reagent, 1, 8), 'Z',
						"ingotBrass"
				});

				DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.cartridge, 8, 2), new Object[] {
						"X", "Y", "Z", 'X', "gravel", 'Y', new ItemStack(MachineInit.reagent, 1, 8), 'Z', "ingotBrass"
				});

				DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.cartridge, 8, 3), new Object[] {
						"X", "Y", 'X', "ingotSilver", 'Y', new ItemStack(MachineInit.reagent, 1, 8)
				});

				DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.cartridge, 8, 4), new Object[] {
						"X", "Y", "Z", 'X', "ingotBismuth", 'Y', new ItemStack(MachineInit.reagent, 1, 8), 'Z',
						"ingotBrass"
				});
			}
		}
	}

}
