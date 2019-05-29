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
				"ingotTitaniumAlloy",
				"gemGarnet" };
			for (int i = 0; i < name.length; i++) {
				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcPickaxe[i], 1, 0), new Object[] {
					"  X",
					"YYX",
					"  X",
					'X',
					name[i],
					'Y',
					"stickWood" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcAxe[i], 1, 0), new Object[] {
					" XX",
					"YYX",
					"   ",
					'X',
					name[i],
					'Y',
					"stickWood" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcSpade[i], 1, 0), new Object[] {
					"   ",
					"YYX",
					"   ",
					'X',
					name[i],
					'Y',
					"stickWood" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcSword[i], 1, 0), new Object[] {
					"   ",
					"YXX",
					"   ",
					'X',
					name[i],
					'Y',
					"stickWood" });
			}

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcPickaxe[8], 1, 0), new Object[] {
				"  X",
				"YYZ",
				"  Z",
				'X',
				"bladeToolSteel",
				'Y',
				"ingotSteel",
				'Z',
				"ingotToolSteel" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcAxe[8], 1, 0), new Object[] {
				" XX",
				"YYZ",
				"   ",
				'X',
				"bladeToolSteel",
				'Y',
				"ingotSteel",
				'Z',
				"ingotToolSteel" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcSpade[8], 1, 0), new Object[] {
				"   ",
				"YYX",
				"   ",
				'X',
				"bladeToolSteel",
				'Y',
				"ingotSteel" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcSword[8], 1, 0), new Object[] {
				"   ",
				"YXX",
				"   ",
				'X',
				"bladeToolSteel",
				'Y',
				"ingotSteel" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcScythe[0], 1, 0), new Object[] {
				" X ",
				"  X",
				"YYX",
				'X',
				"ingotBrass",
				'Y',
				"stickWood" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcScythe[1], 1, 0), new Object[] {
				" X ",
				"  X",
				"YYX",
				'X',
				"ingotSteel",
				'Y',
				"stickWood" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcScythe[2], 1, 0), new Object[] {
				" X ",
				"  X",
				"YYX",
				'X',
				"gemChalcedony",
				'Y',
				"stickWood" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcScythe[3], 1, 0), new Object[] {
				" X ",
				"  X",
				"YYX",
				'X',
				"gemGarnet",
				'Y',
				"stickWood" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcScythe[4], 1, 0), new Object[] {
				" X ",
				"  X",
				"YYX",
				'X',
				"cobblestone",
				'Y',
				"stickWood" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.dcScythe[5], 1, 0), new Object[] {
				" X ",
				"  X",
				"YYZ",
				'X',
				"bladeToolSteel",
				'Y',
				"ingotSteel",
				'Z',
				"ingotToolSteel" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.earthSpade, 1, 0), new Object[] {
				"YYX",
				'X',
				"gemMalachite",
				'Y',
				"stickWood" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.earthRake, 1, 0), new Object[] {
				"  X",
				"YYX",
				"  X",
				'X',
				"gemMalachite",
				'Y',
				"stickWood" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.toolsteelRake, 1, 0), new Object[] {
				"   ",
				"YYX",
				"  X",
				'X',
				"ingotToolSteel",
				'Y',
				"ingotSteel" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.shieldTitanium, 1, 0), new Object[] {
				"XYX",
				"XXX",
				" X ",
				'X',
				"ingotTitaniumAlloy",
				'Y',
				"blockGlass" });

			String[] name2 = {
				"ingotBrass",
				"ingotSteel",
				"gemChalcedony",
				"gemSapphire",
				"ingotTitaniumAlloy",
				"ingotSilver" };
			Item[][] armor = {
				MainInit.brassArmor,
				MainInit.steelArmor,
				MainInit.chalcArmor,
				MainInit.sapphireArmor,
				MainInit.titaniumArmor,
				MainInit.silverArmor };
			for (int i = 0; i < name2.length; i++) {
				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(armor[i][0], 1, 0), new Object[] {
					"XXX",
					"XYX",
					'X',
					name2[i],
					'Y',
					"itemCloth" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(armor[i][1], 1, 0), new Object[] {
					"X X",
					"XYX",
					"XXX",
					'X',
					name2[i],
					'Y',
					"itemCloth" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(armor[i][2], 1, 0), new Object[] {
					"XYX",
					"X X",
					"X X",
					'X',
					name2[i],
					'Y',
					"itemCloth" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(armor[i][3], 1, 0), new Object[] {
					"X X",
					"XYX",
					'X',
					name2[i],
					'Y',
					"itemCloth" });
			}

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.silverArmor[0], 1, 0), new Object[] {
				"XXX",
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				"itemCloth" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.silverArmor[1], 1, 0), new Object[] {
				"X X",
				"XYX",
				"XXX",
				'X',
				"ingotNickelsilver",
				'Y',
				"itemCloth" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.silverArmor[2], 1, 0), new Object[] {
				"XYX",
				"X X",
				"X X",
				'X',
				"ingotNickelsilver",
				'Y',
				"itemCloth" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.silverArmor[3], 1, 0), new Object[] {
				"X X",
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				"itemCloth" });

		}

		// pattern paper
		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.patternPaper, 1, 0), new Object[] {
			"XXX",
			"XYX",
			"X X",
			'X',
			"paper",
			'Y',
			"charcoal" });

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.patternPaper, 1, 1), new Object[] {
			"X X",
			"XYX",
			"XXX",
			'X',
			"paper",
			'Y',
			"charcoal" });

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.patternPaper, 1, 2), new Object[] {
			"X X",
			"XYX",
			"X X",
			'X',
			"paper",
			'Y',
			"charcoal" });

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.patternPaper, 1, 3), new Object[] {
			" X ",
			"XYX",
			"XXX",
			'X',
			"paper",
			'Y',
			"charcoal" });

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.patternPaper, 1, 4), new Object[] {
			"XYX",
			"XXX",
			'X',
			"paper",
			'Y',
			"charcoal" });

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.patternPaper, 1, 5), new Object[] {
			"XYX",
			"X X",
			"X X",
			'X',
			"paper",
			'Y',
			"charcoal" });

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.patternPaper, 1, 6), new Object[] {
			"XY",
			"XX",
			"XX",
			'X',
			"paper",
			'Y',
			"charcoal" });

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.patternPaper, 1, 6), new Object[] {
			"YX",
			"XX",
			"XX",
			'X',
			"paper",
			'Y',
			"charcoal" });

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.patternPaper, 1, 7), new Object[] {
			"X X",
			"XYX",
			'X',
			"paper",
			'Y',
			"charcoal" });

		DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.patternPaper, 1, 8), new Object[] {
			"XYX",
			"X X",
			'X',
			"paper",
			'Y',
			"charcoal" });

		// cloth
		// linen
		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.linenUnder, 1, 0), new Object[] {
			"patternSuit",
			"itemLinenCloth",
			"itemLinenCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.linenShirt, 1, 0), new Object[] {
			"patternShirt",
			"itemLinenCloth",
			"itemLinenCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.linenJacket, 1, 0), new Object[] {
			"patternJacket",
			"itemLinenCloth",
			"itemLinenCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.linenCoat, 1, 0), new Object[] {
			"patternCoat",
			"itemLinenCloth",
			"itemLinenCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.linenBottom, 1, 0), new Object[] {
			"patternPants",
			"itemLinenCloth",
			"itemLinenCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.flowerSkirt, 1, 0), new Object[] {
			"patternSkirt",
			"itemLinenCloth",
			"itemLinenCloth",
			"string" });

		// cotton
		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.clothUnder, 1, 0), new Object[] {
			"patternSuit",
			"itemCottonCloth",
			"itemCottonCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.clothShirt, 1, 0), new Object[] {
			"patternShirt",
			"itemCottonCloth",
			"itemCottonCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.clothJacket, 1, 0), new Object[] {
			"patternJacket",
			"itemCottonCloth",
			"itemCottonCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.clothCoat, 1, 0), new Object[] {
			"patternCoat",
			"itemCottonCloth",
			"itemCottonCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.hoodie, 1, 0), new Object[] {
			"patternHoodie",
			"itemCottonCloth",
			"itemCottonCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.clothBottom, 1, 0), new Object[] {
			"patternPants",
			"itemCottonCloth",
			"itemCottonCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.clothSkirt, 1, 0), new Object[] {
			"patternSkirt",
			"itemCottonCloth",
			"itemCottonCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.cottonHat, 1, 0), new Object[] {
			"patternWear",
			"itemCottonCloth",
			"itemCottonCloth",
			"string" });

		// worker
		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.workerSuit, 1, 0), new Object[] {
			"patternSuit",
			"itemCottonCloth",
			"itemCottonCloth",
			"ingotIron" });

		// silk
		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.blackSuit, 1, 0), new Object[] {
			"patternSuit",
			"itemSilkCloth",
			"itemSilkCloth",
			"string",
			"dyeBlack" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.blackCoat, 1, 0), new Object[] {
			"patternCoat",
			"itemSilkCloth",
			"itemSilkCloth",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.silkDress, 1, 0), new Object[] {
			"patternSuit",
			"itemSilkCloth",
			"itemSilkCloth",
			"stringMagic" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.silkCape, 1, 0), new Object[] {
			"patternWear",
			"itemSilkCloth",
			"itemSilkCloth",
			"stringMagic" });

		// wool
		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.woolJacket, 1, 0), new Object[] {
			"patternJacket",
			new ItemStack(Blocks.WOOL, 1, 32767),
			new ItemStack(Blocks.WOOL, 1, 32767),
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.peaCoat, 1, 0), new Object[] {
			"patternHoodie",
			new ItemStack(Blocks.WOOL, 1, 32767),
			new ItemStack(Blocks.WOOL, 1, 32767),
			"itemCloth" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.modsCoat, 1, 0), new Object[] {
			"patternHoodie",
			new ItemStack(Blocks.WOOL, 1, 32767),
			new ItemStack(Blocks.WOOL, 1, 32767),
			"rabbithide" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.woolBoots, 1, 0), new Object[] {
			"patternBoots",
			new ItemStack(Blocks.WOOL, 1, 32767),
			"leather" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.woolWear, 1, 0), new Object[] {
			"patternWear",
			new ItemStack(Blocks.WOOL, 1, 32767),
			new ItemStack(Blocks.WOOL, 1, 32767),
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.furWear, 1, 0), new Object[] {
			"patternWear",
			"rabbithide",
			"rabbithide",
			"string" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.furCape, 1, 0), new Object[] {
			"patternWear",
			"leather",
			"rabbithide",
			"string" });

		// leather
		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.leatherHat, 1, 0), new Object[] {
			"patternWear",
			"leather",
			"leather",
			"string" });

		// magic
		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.magicUnder, 1, 0), new Object[] {
			"patternSuit",
			"itemMagicCloth",
			"itemMagicCloth",
			"stringMagic" });

		DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.magicCoat, 1, 0), new Object[] {
			"patternCoat",
			"itemMagicCloth",
			"itemMagicCloth",
			"stringMagic" });

		if (ModuleConfig.machine_advanced) {

			DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.trackSuit, 1, 0), new Object[] {
				"patternSuit",
				"itemSyntheticCloth",
				"itemSyntheticCloth",
				"string" });

			DCRecipe.jsonShapelessRecipe("main_equip", new ItemStack(MainInit.combatDress, 1, 0), new Object[] {
				"patternSuit",
				"itemSyntheticCloth",
				"itemSyntheticCloth",
				"ingotTitaniumAlloy" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.shieldSynthetic, 1, 0), new Object[] {
				"XYX",
				"XXX",
				" X ",
				'X',
				new ItemStack(MachineInit.synthetic, 1, 2),
				'Y',
				"ingotToolSteel" });

		} else {
			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.shieldSynthetic, 1, 0), new Object[] {
				"XYX",
				"XXX",
				" X ",
				'X',
				new ItemStack(MainInit.selenite, 1, 0),
				'Y',
				"ingotToolSteel" });
		}

		// gun
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
				"plankWood" });

			DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 16, 0), new Object[] {
				" X",
				"Y ",
				'X',
				"ingotIron",
				'Y',
				"cobblestone" });

			if (ModuleConfig.machine_advanced) {

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.gun, 1, 0), new Object[] {
					"Y ",
					"ZX",
					'X',
					new ItemStack(Items.FLINT_AND_STEEL, 1, 0),
					'Y',
					"barrelGun",
					'Z',
					"plankWood" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 1), new Object[] {
					"X",
					"Y",
					"Z",
					'X',
					"ingotIron",
					'Y',
					new ItemStack(MachineInit.reagent, 1, 8),
					'Z',
					"ingotBrass" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 1), new Object[] {
					"X",
					"Y",
					"Z",
					'X',
					"ingotLead",
					'Y',
					new ItemStack(MachineInit.reagent, 1, 8),
					'Z',
					"ingotBrass" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 2), new Object[] {
					"X",
					"Y",
					"Z",
					'X',
					"gravel",
					'Y',
					new ItemStack(MachineInit.reagent, 1, 8),
					'Z',
					"ingotBrass" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 3), new Object[] {
					"X",
					"Y",
					'X',
					"ingotSilver",
					'Y',
					new ItemStack(MachineInit.reagent, 1, 8) });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 4), new Object[] {
					"X",
					"Y",
					"Z",
					'X',
					"ingotBismuth",
					'Y',
					new ItemStack(MachineInit.reagent, 1, 8),
					'Z',
					"ingotBrass" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 5), new Object[] {
					"X",
					"Y",
					"Z",
					'X',
					"dustPhosphorus",
					'Y',
					"dustSulfur",
					'Z',
					"ingotBrass" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 4, 5), new Object[] {
					"X",
					"Y",
					"Z",
					'X',
					"dustGlowstone",
					'Y',
					"gemRutile",
					'Z',
					"ingotBrass" });

				DCRecipe.jsonShapedRecipe("main_equip", new ItemStack(MainInit.cartridge, 8, 6), new Object[] {
					"X",
					"Y",
					"Z",
					'X',
					new ItemStack(MachineInit.reagent, 1, 12),
					'Y',
					new ItemStack(MachineInit.reagent, 1, 8),
					'Z',
					"ingotBrass" });

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
					"leather" });
			}
		}
	}

}
