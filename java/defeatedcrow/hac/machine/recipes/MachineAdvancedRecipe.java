package defeatedcrow.hac.machine.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MachineAdvancedRecipe {

	public static void load(RecipeResourcesMain res) {

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.freezer, 1, 0), new Object[] {
				"WZ ",
				"WYV",
				"XXX",
				'X',
				"ingotSUS",
				'Y',
				new ItemStack(Blocks.END_ROD, 1, 0),
				'Z',
				"bucketWater",
				'W',
				new ItemStack(MachineInit.IBC, 1, 0),
				'V',
				new ItemStack(MachineInit.gearbox2, 1, 0),
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.pressMachine, 1, 0), new Object[] {
				"XYX",
				"X X",
				"XZX",
				'X',
				"ingotSUS",
				'Y',
				"gearSteel",
				'Z',
				new ItemStack(Blocks.ANVIL, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.motor, 1, 0), new Object[] {
				"YZW",
				"XXX",
				'X',
				"ingotSUS",
				'Y',
				new ItemStack(MachineInit.shaft2_s, 1, 0),
				'Z',
				"gearSteel",
				'W',
				new ItemStack(MachineInit.machimeMaterials, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.dynamo, 1, 0), new Object[] {
				"XZX",
				" Y ",
				"XZX",
				'X',
				"ingotSUS",
				'Y',
				new ItemStack(MachineInit.machimeMaterials, 1, 1),
				'Z',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.waterPump, 1, 0), new Object[] {
				"XYX",
				"XZX",
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				new ItemStack(Blocks.IRON_BARS, 1, 0),
				'Z',
				"gearAlloy"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.reactor, 1, 0), new Object[] {
				"YXX",
				"ZXX",
				"WVW",
				'X',
				new ItemStack(MachineInit.IBC, 1, 0),
				'Y',
				new ItemStack(MachineInit.gearbox2, 1, 0),
				'Z',
				new ItemStack(MachineInit.machimeMaterials, 1, 0),
				'W',
				"ingotSUS",
				'V',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.crusher, 1, 0), new Object[] {
				" Y ",
				"XZX",
				"XXX",
				'X',
				"ingotSUS",
				'Y',
				new ItemStack(Blocks.HOPPER, 1, 0),
				'Z',
				new ItemStack(MachineInit.machimeMaterials, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.rotaryBlade, 1, 0), new Object[] {
				" Y ",
				"XZX",
				" Y ",
				'X',
				"ingotSUS",
				'Y',
				new ItemStack(Blocks.IRON_BARS, 1, 0),
				'Z',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.rotaryBlade, 1, 1), new Object[] {
				" Y ",
				"XZX",
				" Y ",
				'X',
				"ingotTitanium",
				'Y',
				new ItemStack(Blocks.IRON_BARS, 1, 0),
				'Z',
				"gearSteel"
		});

		if (ModuleConfig.food) {
			DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.reactor, 1, 0), new Object[] {
					"YXX",
					"ZXX",
					"WWW",
					'X',
					new ItemStack(FoodInit.steelPot, 1, 0),
					'Y',
					new ItemStack(MachineInit.gearbox2, 1, 0),
					'Z',
					new ItemStack(MachineInit.machimeMaterials, 1, 0),
					'W',
					"ingotSUS"
			});
		}

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.synthetic, 1, 1), new Object[] {
				" X ",
				"X X",
				" X ",
				'X',
				new ItemStack(MachineInit.synthetic, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.reagent, 1, 8), new Object[] {
				new ItemStack(MachineInit.reagent, 1, 5),
				new ItemStack(MachineInit.reagent, 1, 6)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(Items.GUNPOWDER, 4, 0), new Object[] {
				new ItemStack(MachineInit.reagent, 1, 8)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.moldAluminium, 1, 0), new Object[] {
				"XYX",
				'X',
				"ingotAluminium",
				'Y',
				"ingotSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.moldAluminium, 1, 0), new Object[] {
				"XYX",
				'X',
				"ingotAluminum",
				'Y',
				"ingotSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.moldAlloy, 1, 0), new Object[] {
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				"dustNickel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.moldAlloy, 1, 1), new Object[] {
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				"dustTitanium"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.moldAlloy, 1, 2), new Object[] {
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				"dustBismuth"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.moldAlloy, 1, 3), new Object[] {
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				"dustBlaze"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.moldAluminium, 1, 1), new Object[] {
				new ItemStack(MachineInit.moldAluminium, 1, 0),
				"string"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.moldAluminium, 1, 2), new Object[] {
				new ItemStack(MachineInit.moldAluminium, 1, 0),
				new ItemStack(Blocks.GLASS_PANE, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.moldAluminium, 1, 3), new Object[] {
				new ItemStack(MachineInit.moldAluminium, 1, 0),
				"blockGlass"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.moldAluminium, 1, 0), new Object[] {
				new ItemStack(MachineInit.moldAluminium, 1, 32767)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.adapterPanel, 1, 0), new Object[] {
				"XXX",
				" Y ",
				"ZWZ",
				'X',
				"blockGlass",
				'Y',
				"gemBismuth",
				'Z',
				"gemSchorl",
				'W',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.acceptorPanel, 1, 0), new Object[] {
				"XXX",
				" Y ",
				"ZWZ",
				'X',
				"blockGlass",
				'Y',
				"gemBismuth",
				'Z',
				"gemQuartz",
				'W',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.adapterFluidPanel, 1, 0), new Object[] {
				"XXX",
				" Y ",
				"ZWZ",
				'X',
				"blockGlass",
				'Y',
				"gemBismuth",
				'Z',
				"gemSchorl",
				'W',
				"bucketEmpty"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.acceptorFluidPanel, 1, 0),
				new Object[] {
						"XXX",
						" Y ",
						"ZWZ",
						'X',
						"blockGlass",
						'Y',
						"gemBismuth",
						'Z',
						"gemQuartz",
						'W',
						"bucketEmpty"
				});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.wirelessPortal, 1, 0), new Object[] {
				"WXW",
				"VYV",
				"WZW",
				'X',
				new ItemStack(MachineInit.gemcore, 1, 0),
				'Y',
				new ItemStack(MachineInit.machimeMaterials, 1, 0),
				'Z',
				new ItemStack(MachineInit.gearbox2, 1, 0),
				'W',
				"ingotSilver",
				'V',
				"ingotBSCCO"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.gemcore, 1, 0), new Object[] {
				"ZXZ",
				"XYX",
				"ZXZ",
				'X',
				"gemBismuth",
				'Y',
				"gemClam",
				'Z',
				"gemSchorl"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.gemcore, 1, 1), new Object[] {
				"ZXZ",
				"WYW",
				"ZXZ",
				'W',
				"gemSapphire",
				'X',
				"gemCelestite",
				'Y',
				"gemClam",
				'Z',
				"obsidian"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.gemcore, 1, 1), new Object[] {
				"ZXZ",
				"WYW",
				"ZXZ",
				'W',
				"gemSapphire",
				'X',
				"gemMoonstone",
				'Y',
				"dustMica",
				'Z',
				"obsidian"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.adapterCard, 1, 0), new Object[] {
				"ZXZ",
				" Y ",
				'X',
				"gemBismuth",
				'Y',
				"blockGlass",
				'Z',
				"gemQuartz"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.adapterCard, 1, 1), new Object[] {
				"ZXZ",
				" Y ",
				'X',
				"gemBismuth",
				'Y',
				"blockGlass",
				'Z',
				"gemSchorl"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.adapterCard, 1, 2), new Object[] {
				"X",
				"Y",
				'X',
				"bucketEmpty",
				'Y',
				new ItemStack(MachineInit.adapterCard, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.adapterCard, 1, 3), new Object[] {
				"X",
				"Y",
				'X',
				"bucketEmpty",
				'Y',
				new ItemStack(MachineInit.adapterCard, 1, 1)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.monitorRS, 1, 0), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(Blocks.REDSTONE_LAMP, 1, 0),
				'Y',
				"gemBismuth",
				'Z',
				new ItemStack(Blocks.REDSTONE_TORCH, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.monitorFluid, 1, 0), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(Blocks.REDSTONE_LAMP, 1, 0),
				'Y',
				"gemBismuth",
				'Z',
				new ItemStack(Items.BUCKET, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.monitorTorque, 1, 0), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(Blocks.REDSTONE_LAMP, 1, 0),
				'Y',
				"gemBismuth",
				'Z',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.monitorItem, 1, 0), new Object[] {
				"X",
				"Y",
				"Z",
				'X',
				new ItemStack(Blocks.REDSTONE_LAMP, 1, 0),
				'Y',
				"gemBismuth",
				'Z',
				new ItemStack(Blocks.CHEST, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.dynamite, 3, 0), new Object[] {
				"WWW",
				"XYZ",
				"WWW",
				'X',
				new ItemStack(MachineInit.reagent, 1, 6),
				'Y',
				new ItemStack(MachineInit.reagent, 1, 6),
				'Z',
				new ItemStack(MachineInit.reagent, 1, 5),
				'W',
				"paper"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.dynamite, 3, 1), new Object[] {
				"WWW",
				"XYZ",
				"WWW",
				'X',
				new ItemStack(MachineInit.reagent, 1, 6),
				'Y',
				new ItemStack(MachineInit.reagent, 1, 5),
				'Z',
				new ItemStack(MachineInit.reagent, 1, 4),
				'W',
				"paper"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.burner, 1, 0), new Object[] {
				"X X",
				"X X",
				"YZW",
				'X',
				"ingotSUS",
				'Y',
				"gearSteel",
				'Z',
				new ItemStack(Items.FLINT_AND_STEEL, 1, 0),
				'W',
				new ItemStack(MachineInit.IBC, 1, 0)
		});

		// アナザー
		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(Blocks.TORCH, 6, 0), new Object[] {
				"Y",
				"X",
				'X',
				"stickWood",
				'Y',
				new ItemStack(MachineInit.reagent, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(Blocks.TORCH, 6, 0), new Object[] {
				"Y",
				"X",
				'X',
				"stickWood",
				'Y',
				new ItemStack(MachineInit.reagent, 1, 1)
		});

		// エンジン!
		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.machimeMaterials, 1, 2), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"ingotAluminium",
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.machimeMaterials, 1, 2), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"ingotAluminum",
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.machimeMaterials, 1, 3), new Object[] {
				"XZX",
				"ZYZ",
				"XZX",
				'X',
				"ingotSUS",
				'Y',
				new ItemStack(MachineInit.machimeMaterials, 1, 2),
				'Z',
				"gearSteel"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.machimeMaterials, 1, 4),
				new Object[] {
						new ItemStack(MachineInit.reagent, 1, 2),
						new ItemStack(MachineInit.reagent, 1, 7),
						"slimeball",
						"dustSulfur"
				});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.machimeMaterials, 1, 5), new Object[] {
				"ZXZ",
				"X X",
				"ZXZ",
				'X',
				"ingotBSCCO",
				'Z',
				"ingotTitanium"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.motorMinecart, 1, 0), new Object[] {
				new ItemStack(MachineInit.machimeMaterials, 1, 3),
				new ItemStack(Items.MINECART, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.scooter, 1, 2), new Object[] {
				" X ",
				"WYW",
				"Z Z",
				'X',
				new ItemStack(Items.SADDLE, 1, 0),
				'Y',
				new ItemStack(MachineInit.machimeMaterials, 1, 3),
				'W',
				"gearSteel",
				'Z',
				new ItemStack(MachineInit.machimeMaterials, 1, 4)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.scooter, 1, 0), new Object[] {
				new ItemStack(MachineInit.scooter, 1, 32767),
				"dyeOrange"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.scooter, 1, 1), new Object[] {
				new ItemStack(MachineInit.scooter, 1, 32767),
				"dyeBlue"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.scooter, 1, 2), new Object[] {
				new ItemStack(MachineInit.scooter, 1, 32767),
				"dyeWhite"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.scooter, 1, 3), new Object[] {
				new ItemStack(MachineInit.scooter, 1, 32767),
				"dyeBlack"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.magneticHover, 1, 0), new Object[] {
				" X ",
				"WYW",
				"Z Z",
				'X',
				new ItemStack(Items.SADDLE, 1, 0),
				'Y',
				new ItemStack(MachineInit.freezer, 1, 0),
				'W',
				"gearSteel",
				'Z',
				new ItemStack(MachineInit.machimeMaterials, 1, 5)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.platingChrome, 1, 9), new Object[] {
				"X  ",
				"YYY",
				"Z Z",
				'X',
				"string",
				'Y',
				"plankWood",
				'Z',
				"ingotIron"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.dieselEngine, 1, 0), new Object[] {
				"WXW",
				"WYW",
				"ZZZ",
				'X',
				new ItemStack(MachineInit.IBC, 1, 0),
				'Y',
				new ItemStack(MachineInit.machimeMaterials, 1, 3),
				'W',
				new ItemStack(Blocks.PISTON, 1, 0),
				'Z',
				"ingotSUS"
		});
	}

}
