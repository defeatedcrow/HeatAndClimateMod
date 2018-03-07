package defeatedcrow.hac.machine.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class MachineRecipes {

	private static final RecipeResourcesMain res = new RecipeResourcesMain("machine");

	public static void load() {
		loadBasicRecipe();
		if (ModuleConfig.machine_advanced) {
			MachineAdvancedRecipe.load(res);
		}
		MachineDeviceRecipes.load();
		if (ModuleConfig.r_crusher) {
			MachineCrusherRecipe.load();
		}
	}

	static void loadBasicRecipe() {
		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MainInit.wrench, 1, 0), new Object[] {
				"X X",
				" Y ",
				" X ",
				'X',
				"ingotBrass",
				'Y',
				"gearBrass"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft_s, 1, 0), new Object[] {
				"X",
				"Y",
				"X",
				'X',
				"ingotBrass",
				'Y',
				"gearBrass"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft_l, 1, 0), new Object[] {
				" X",
				"XY",
				'X',
				new ItemStack(MachineInit.shaft_s, 1, 0),
				'Y',
				"gearBrass"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft_t_a, 1, 0), new Object[] {
				" X ",
				"XY ",
				" X ",
				'X',
				new ItemStack(MachineInit.shaft_s, 1, 0),
				'Y',
				"gearBrass"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft_t_b, 1, 0), new Object[] {
				" X ",
				"XYX",
				'X',
				new ItemStack(MachineInit.shaft_s, 1, 0),
				'Y',
				"gearBrass"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.piston, 1, 0), new Object[] {
				" X ",
				"YZY",
				" W ",
				'X',
				new ItemStack(MachineInit.shaft_s, 1, 0),
				'Y',
				"ingotBrass",
				'Z',
				"gearBrass",
				'W',
				"dustRedstone"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.handcrank, 1, 0), new Object[] {
				"Z",
				"Y",
				"X",
				'X',
				new ItemStack(MachineInit.shaft_s, 1, 0),
				'Y',
				"ingotBrass",
				'Z',
				"itemLeather"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.gearbox, 1, 0), new Object[] {
				"ZXZ",
				"XYX",
				"ZXZ",
				'X',
				"gearBrass",
				'Y',
				"gearAlloy",
				'Z',
				"ingotIron"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.redbox, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"dustRedstone",
				'Y',
				"gearAlloy"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.windmill, 1, 0), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				"plankWood",
				'Y',
				"gearBrass"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.windmill_l, 1, 0), new Object[] {
				"XZX",
				"ZYZ",
				"XZX",
				'X',
				"plankWood",
				'Y',
				new ItemStack(MachineInit.windmill, 1, 0),
				'Z',
				"ingotSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.watermill, 1, 0), new Object[] {
				"XZX",
				"ZYZ",
				"XZX",
				'X',
				"plankWood",
				'Y',
				"gearAlloy",
				'Z',
				"stickWood"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.stonemill, 1, 0), new Object[] {
				"XXX",
				" Y ",
				"XXX",
				'X',
				"stone",
				'Y',
				"gearAlloy"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.fan, 1, 0), new Object[] {
				"XZX",
				"ZYZ",
				"XZX",
				'X',
				"ingotIron",
				'Y',
				"gearBrass",
				'Z',
				"dustRedstone"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.spinning, 1, 0), new Object[] {
				"XXX",
				"ZYZ",
				"WWW",
				'X',
				"gearWood",
				'Y',
				"gearBrass",
				'Z',
				"plankWood",
				'W',
				"ingotIron"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.fauset, 1, 0), new Object[] {
				" Y ",
				"XXX",
				"X  ",
				'X',
				"ingotNickelsilver",
				'Y',
				"gearAlloy"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.IBC, 1, 0), new Object[] {
				"ZXZ",
				"YYY",
				'X',
				new ItemStack(Blocks.GLASS, 1, 0),
				'Y',
				"ingotNickelsilver",
				'Z',
				new ItemStack(Blocks.IRON_BARS, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.heatPump, 1, 0), new Object[] {
				"XXX",
				"WZW",
				"YYY",
				'X',
				"ingotCopper",
				'Y',
				"ingotSteel",
				'Z',
				new ItemStack(MachineInit.fan, 1, 0),
				'W',
				new ItemStack(Blocks.IRON_BARS, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft3_s, 1, 0), new Object[] {
				"X",
				"Y",
				"X",
				'X',
				"ingotSteel",
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft3_l, 1, 0), new Object[] {
				" X",
				"XY",
				'X',
				new ItemStack(MachineInit.shaft3_s, 1, 0),
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft3_t_a, 1, 0), new Object[] {
				" X ",
				"XY ",
				" X ",
				'X',
				new ItemStack(MachineInit.shaft3_s, 1, 0),
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft3_t_b, 1, 0), new Object[] {
				" X ",
				"XYX",
				'X',
				new ItemStack(MachineInit.shaft3_s, 1, 0),
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft2_s, 1, 0), new Object[] {
				"X",
				"Y",
				"X",
				'X',
				"ingotSUS",
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft2_l, 1, 0), new Object[] {
				" X",
				"XY",
				'X',
				new ItemStack(MachineInit.shaft2_s, 1, 0),
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft2_t_a, 1, 0), new Object[] {
				" X ",
				"XY ",
				" X ",
				'X',
				new ItemStack(MachineInit.shaft2_s, 1, 0),
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.shaft2_t_b, 1, 0), new Object[] {
				" X ",
				"XYX",
				'X',
				new ItemStack(MachineInit.shaft2_s, 1, 0),
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.gearbox2, 1, 0), new Object[] {
				"ZXZ",
				"XYX",
				"ZXZ",
				'X',
				"gearAlloy",
				'Y',
				"gearSteel",
				'Z',
				"ingotSUS"
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

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.boilerTurbine, 1, 0), new Object[] {
				"XXX",
				"ZYW",
				"XXX",
				'X',
				"ingotSUS",
				'Y',
				"gearSteel",
				'Z',
				new ItemStack(MachineInit.IBC, 1, 0),
				'W',
				new ItemStack(MachineInit.heatPump, 1, 0)
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

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.conveyor, 8, 0), new Object[] {
				"XYX",
				'X',
				"ingotSteel",
				'Y',
				"gearSteel"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.catapult, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MachineInit.conveyor, 1, 0),
				'Y',
				new ItemStack(Blocks.PISTON, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.hopperFilter, 1, 0), new Object[] {
				"XZX",
				"XYX",
				" X ",
				'X',
				"ingotIron",
				'Y',
				new ItemStack(Blocks.CHEST, 1, 0),
				'Z',
				new ItemStack(Blocks.IRON_BARS, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.hopperFilter, 1, 0), new Object[] {
				"Z",
				"Y",
				'Y',
				new ItemStack(Blocks.HOPPER, 1, 0),
				'Z',
				new ItemStack(Blocks.IRON_BARS, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.hopperGold, 1, 0), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				"ingotGold",
				'Y',
				new ItemStack(Blocks.HOPPER, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.hopperFilterG, 1, 0), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				"ingotGold",
				'Y',
				new ItemStack(MachineInit.hopperFilter, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.hopperFilterG, 1, 0), new Object[] {
				"Z",
				"Y",
				'Y',
				new ItemStack(MachineInit.hopperFilter, 1, 0),
				'Z',
				new ItemStack(Blocks.IRON_BARS, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.hopperFluid, 1, 0), new Object[] {
				"X X",
				"XYX",
				" X ",
				'X',
				"ingotNickelsilver",
				'Y',
				new ItemStack(Blocks.CHEST, 1, 0)
		});

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

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.fuelCont, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"fuelCoke"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.fuelCont, 1, 1), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X',
				"gemCarbide"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.reagent, 9, 13), new Object[] {
				new ItemStack(MachineInit.fuelCont, 1, 0)
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.reagent, 9, 9), new Object[] {
				new ItemStack(MachineInit.fuelCont, 1, 1)
		});

		/* == items == */

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.torqueChecker, 1, 0), new Object[] {
				"XXX",
				"YZY",
				" X ",
				'X',
				"ingotSteel",
				'Y',
				new ItemStack(Blocks.GLASS_PANE, 1, 0),
				'Z',
				"dustRedstone"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.mold, 4, 0), new Object[] {
				"XYX",
				'X',
				"ingotIron",
				'Y',
				"ingotSteel"
		});

		DCRecipe.addShapelessRecipe(res.getRecipeName(), new ItemStack(MachineInit.mold, 1, 0), new Object[] {
				new ItemStack(MachineInit.mold, 1, 0)
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.machimeMaterials, 1, 0), new Object[] {
				"XZX",
				"YZY",
				"XZX",
				'X',
				"ingotCopper",
				'Y',
				new ItemStack(Blocks.IRON_BARS, 1, 0),
				'Z',
				"ingotIron"
		});

		DCRecipe.addShapedRecipe(res.getRecipeName(), new ItemStack(MachineInit.machimeMaterials, 1, 1), new Object[] {
				"XYX",
				"YZY",
				"XYX",
				'X',
				"ingotSteel",
				'Y',
				"ingotCopper",
				'Z',
				"ingotMagnet"
		});

		/* == Smelting == */

		GameRegistry.addSmelting(new ItemStack(Blocks.COAL_BLOCK, 1, 0), new ItemStack(MachineInit.fuelCont, 1, 0),
				0.0F);

	}

	public static void loadOres() {
		OreDictionary.registerOre("string", new ItemStack(MachineInit.synthetic, 1, 0));
		OreDictionary.registerOre("itemString", new ItemStack(MachineInit.synthetic, 1, 0));
		OreDictionary.registerOre("itemCloth", new ItemStack(MachineInit.synthetic, 1, 1));
		OreDictionary.registerOre("itemSyntheticCloth", new ItemStack(MachineInit.synthetic, 1, 1));
		OreDictionary.registerOre("blockGlass", new ItemStack(MachineInit.synthetic, 1, 2));
		OreDictionary.registerOre("dustAlkali", new ItemStack(MachineInit.reagent, 1, 3));
		OreDictionary.registerOre("dyeBlack", new ItemStack(MachineInit.reagent, 1, 7));
		OreDictionary.registerOre("gemCarbide", new ItemStack(MachineInit.reagent, 1, 9));
		OreDictionary.registerOre("dustPhosphorus", new ItemStack(MachineInit.reagent, 1, 10));
		OreDictionary.registerOre("fuelCoke", new ItemStack(MachineInit.reagent, 1, 13));
		OreDictionary.registerOre("blockFuelCoke", new ItemStack(MachineInit.fuelCont, 1, 0));
		OreDictionary.registerOre("blockCarbide", new ItemStack(MachineInit.fuelCont, 1, 1));

	}

}
