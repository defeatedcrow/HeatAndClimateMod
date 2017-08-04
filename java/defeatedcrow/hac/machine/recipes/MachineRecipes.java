package defeatedcrow.hac.machine.recipes;

import defeatedcrow.hac.core.recipe.ShapedNBTRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class MachineRecipes {

	public static void load() {
		loadBasicRecipe();
		if (ModuleConfig.machine_advanced) {
			loadAdvancedRecipe();
		}
		MachineDeviceRecipes.load();
	}

	static void loadBasicRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.wrench, 1, 0), new Object[] {
				"X X",
				" Y ",
				" X ",
				'X',
				"ingotBrass",
				'Y',
				"gearBrass"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.shaft_s, 1, 0), new Object[] {
				"X",
				"Y",
				"X",
				'X',
				"ingotBrass",
				'Y',
				"gearBrass"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.shaft_l, 1, 0), new Object[] {
				" X",
				"XY",
				'X',
				new ItemStack(MachineInit.shaft_s, 1, 0),
				'Y',
				"gearBrass"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.shaft_t_a, 1, 0), new Object[] {
				" X ",
				"XY ",
				" X ",
				'X',
				new ItemStack(MachineInit.shaft_s, 1, 0),
				'Y',
				"gearBrass"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.shaft_t_b, 1, 0), new Object[] {
				" X ",
				"XYX",
				'X',
				new ItemStack(MachineInit.shaft_s, 1, 0),
				'Y',
				"gearBrass"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.piston, 1, 0), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.handcrank, 1, 0), new Object[] {
				"Z",
				"Y",
				"X",
				'X',
				new ItemStack(MachineInit.shaft_s, 1, 0),
				'Y',
				"ingotBrass",
				'Z',
				"itemLeather"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.gearbox, 1, 0), new Object[] {
				"ZXZ",
				"XYX",
				"ZXZ",
				'X',
				"gearBrass",
				'Y',
				"gearAlloy",
				'Z',
				"ingotIron"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.redbox, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"dustRedstone",
				'Y',
				"gearAlloy"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.windmill, 1, 0), new Object[] {
				" X ",
				"XYX",
				" X ",
				'X',
				"plankWood",
				'Y',
				"gearBrass"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.windmill_l, 1, 0), new Object[] {
				"XZX",
				"ZYZ",
				"XZX",
				'X',
				"plankWood",
				'Y',
				new ItemStack(MachineInit.windmill, 1, 0),
				'Z',
				"ingotSteel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.watermill, 1, 0), new Object[] {
				"XZX",
				"ZYZ",
				"XZX",
				'X',
				"plankWood",
				'Y',
				"gearAlloy",
				'Z',
				"stickWood"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.stonemill, 1, 0), new Object[] {
				"XXX",
				" Y ",
				"XXX",
				'X',
				"stone",
				'Y',
				"gearAlloy"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.fan, 1, 0), new Object[] {
				"XZX",
				"ZYZ",
				"XZX",
				'X',
				"ingotIron",
				'Y',
				"gearBrass",
				'Z',
				"dustRedstone"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.spinning, 1, 0), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.fauset, 1, 0), new Object[] {
				" Y ",
				"XXX",
				"X  ",
				'X',
				"ingotNickelsilver",
				'Y',
				"gearAlloy"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.IBC, 1, 0), new Object[] {
				"ZXZ",
				"YYY",
				'X',
				new ItemStack(Blocks.GLASS, 1, 0),
				'Y',
				"ingotNickelsilver",
				'Z',
				new ItemStack(Blocks.IRON_BARS, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.heatPump, 1, 0), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.shaft2_s, 1, 0), new Object[] {
				"X",
				"Y",
				"X",
				'X',
				"ingotSUS",
				'Y',
				"gearSteel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.shaft2_l, 1, 0), new Object[] {
				" X",
				"XY",
				'X',
				new ItemStack(MachineInit.shaft2_s, 1, 0),
				'Y',
				"gearSteel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.shaft2_t_a, 1, 0), new Object[] {
				" X ",
				"XY ",
				" X ",
				'X',
				new ItemStack(MachineInit.shaft2_s, 1, 0),
				'Y',
				"gearSteel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.shaft2_t_b, 1, 0), new Object[] {
				" X ",
				"XYX",
				'X',
				new ItemStack(MachineInit.shaft2_s, 1, 0),
				'Y',
				"gearSteel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.gearbox2, 1, 0), new Object[] {
				"ZXZ",
				"XYX",
				"ZXZ",
				'X',
				"gearAlloy",
				'Y',
				"gearSteel",
				'Z',
				"ingotSUS"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.waterPump, 1, 0), new Object[] {
				"XYX",
				"XZX",
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				new ItemStack(Blocks.IRON_BARS, 1, 0),
				'Z',
				"gearAlloy"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.boilerTurbine, 1, 0), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.pressMachine, 1, 0), new Object[] {
				"XYX",
				"X X",
				"XZX",
				'X',
				"ingotSUS",
				'Y',
				"gearSteel",
				'Z',
				new ItemStack(Blocks.ANVIL, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.motor, 1, 0), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.dynamo, 1, 0), new Object[] {
				"XZX",
				" Y ",
				"XZX",
				'X',
				"ingotSUS",
				'Y',
				new ItemStack(MachineInit.machimeMaterials, 1, 1),
				'Z',
				"gearSteel"
		}));

		/* == items == */

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.torqueChecker, 1, 0), new Object[] {
				"XXX",
				"YZY",
				" X ",
				'X',
				"ingotSteel",
				'Y',
				new ItemStack(Blocks.GLASS_PANE, 1, 0),
				'Z',
				"dustRedstone"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.mold, 4, 0), new Object[] {
				"XYX",
				'X',
				"ingotIron",
				'Y',
				"ingotSteel"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.mold, 1, 0), new Object[] {
				new ItemStack(MachineInit.mold, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.machimeMaterials, 1, 0), new Object[] {
				"XZX",
				"YZY",
				"XZX",
				'X',
				"ingotCopper",
				'Y',
				new ItemStack(Blocks.IRON_BARS, 1, 0),
				'Z',
				"ingotIron"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.machimeMaterials, 1, 1), new Object[] {
				"XYX",
				"YZY",
				"XYX",
				'X',
				"ingotSteel",
				'Y',
				"ingotCopper",
				'Z',
				"ingotMagnet"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.conveyor, 8, 0), new Object[] {
				"XYX",
				'X',
				"ingotSteel",
				'Y',
				"gearSteel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.catapult, 1, 0), new Object[] {
				"X",
				"Y",
				'X',
				new ItemStack(MachineInit.conveyor, 1, 0),
				'Y',
				new ItemStack(Blocks.PISTON, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.hopperFilter, 1, 0), new Object[] {
				"XZX",
				"XYX",
				" X ",
				'X',
				"ingotIron",
				'Y',
				new ItemStack(Blocks.CHEST, 1, 0),
				'Z',
				new ItemStack(Blocks.IRON_BARS, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.hopperFilter, 1, 0), new Object[] {
				"Z",
				"Y",
				'Y',
				new ItemStack(Blocks.HOPPER, 1, 0),
				'Z',
				new ItemStack(Blocks.IRON_BARS, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.hopperFluid, 1, 0), new Object[] {
				"X X",
				"XYX",
				" X ",
				'X',
				"ingotNickelsilver",
				'Y',
				new ItemStack(Blocks.CHEST, 1, 0)
		}));

		if (FluidRegistry.isUniversalBucketEnabled()) {
			ItemStack ub = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket,
					MachineInit.ammonia);
			GameRegistry.addRecipe(new ShapedNBTRecipe(new ItemStack(MachineInit.freezer, 1, 0), new Object[] {
					"WZ ",
					"WYV",
					"XXX",
					'X',
					"ingotSUS",
					'Y',
					ub,
					'Z',
					"bucketWater",
					'W',
					new ItemStack(MachineInit.IBC, 1, 0),
					'V',
					new ItemStack(MachineInit.gearbox2, 1, 0),
			}));
		} else {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.freezer, 1, 0), new Object[] {
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
			}));
		}

	}

	static void loadAdvancedRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.reactor, 1, 0), new Object[] {
				"YXX",
				"ZXX",
				"WWW",
				'X',
				new ItemStack(MachineInit.IBC, 1, 0),
				'Y',
				new ItemStack(MachineInit.gearbox2, 1, 0),
				'Z',
				new ItemStack(MachineInit.machimeMaterials, 1, 1),
				'W',
				"ingotSUS"
		}));

		if (ModuleConfig.food) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.reactor, 1, 0), new Object[] {
					"YXX",
					"ZXX",
					"WWW",
					'X',
					new ItemStack(FoodInit.steelPot, 1, 0),
					'Y',
					new ItemStack(MachineInit.gearbox2, 1, 0),
					'Z',
					new ItemStack(MachineInit.machimeMaterials, 1, 1),
					'W',
					"ingotSUS"
			}));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.synthetic, 1, 1), new Object[] {
				" X ",
				"X X",
				" X ",
				'X',
				new ItemStack(MachineInit.synthetic, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.reagent, 1, 8), new Object[] {
				new ItemStack(MachineInit.reagent, 1, 5),
				new ItemStack(MachineInit.reagent, 1, 6)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.GUNPOWDER, 4, 0), new Object[] {
				new ItemStack(MachineInit.reagent, 1, 8)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.moldAluminium, 1, 0), new Object[] {
				"XYX",
				'X',
				"ingotAluminium",
				'Y',
				"ingotSteel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.moldAlloy, 1, 0), new Object[] {
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				"dustNickel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.moldAlloy, 1, 1), new Object[] {
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				"dustTitanium"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.moldAlloy, 1, 2), new Object[] {
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				"dustBismuth"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.moldAlloy, 1, 3), new Object[] {
				"XYX",
				'X',
				"ingotNickelsilver",
				'Y',
				"dustBlaze"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.moldAluminium, 1, 1), new Object[] {
				new ItemStack(MachineInit.moldAluminium, 1, 0),
				"string"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.moldAluminium, 1, 2), new Object[] {
				new ItemStack(MachineInit.moldAluminium, 1, 0),
				new ItemStack(Blocks.GLASS_PANE, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.moldAluminium, 1, 3), new Object[] {
				new ItemStack(MachineInit.moldAluminium, 1, 0),
				"blockGlass"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.moldAluminium, 1, 0), new Object[] {
				new ItemStack(MachineInit.moldAluminium, 1, 32767)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.adapterPanel, 1, 0), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.acceptorPanel, 1, 0), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.wirelessPortal, 1, 0), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.gemcore, 1, 0), new Object[] {
				"ZXZ",
				"XYX",
				"ZXZ",
				'X',
				"gemBismuth",
				'Y',
				"gemClam",
				'Z',
				"gemSchorl"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.adapterCard, 1, 0), new Object[] {
				"ZXZ",
				" Y ",
				'X',
				"gemBismuth",
				'Y',
				"blockGlass",
				'Z',
				"gemQuartz"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.adapterCard, 1, 1), new Object[] {
				"ZXZ",
				" Y ",
				'X',
				"gemBismuth",
				'Y',
				"blockGlass",
				'Z',
				"gemSchorl"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.adapterCard, 1, 2), new Object[] {
				"X",
				"Y",
				'X',
				"bucketEmpty",
				'Y',
				new ItemStack(MachineInit.adapterCard, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.adapterCard, 1, 3), new Object[] {
				"X",
				"Y",
				'X',
				"bucketEmpty",
				'Y',
				new ItemStack(MachineInit.adapterCard, 1, 1)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.dynamite, 3, 0), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.dynamite, 3, 1), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.burner, 1, 0), new Object[] {
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
		}));

		// アナザー
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 6, 0), new Object[] {
				"Y",
				"X",
				'X',
				"stickWood",
				'Y',
				new ItemStack(MachineInit.reagent, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 6, 0), new Object[] {
				"Y",
				"X",
				'X',
				"stickWood",
				'Y',
				new ItemStack(MachineInit.reagent, 1, 1)
		}));

		// エンジン!
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.machimeMaterials, 1, 2), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"ingotAluminium",
				'Y',
				"gearSteel"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.machimeMaterials, 1, 3), new Object[] {
				"ZZZ",
				"ZYZ",
				"ZZZ",
				'X',
				"ingotSUS",
				'Y',
				new ItemStack(MachineInit.machimeMaterials, 1, 2),
				'Z',
				"gearSteel"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.machimeMaterials, 1, 4), new Object[] {
				new ItemStack(MachineInit.reagent, 1, 2),
				new ItemStack(MachineInit.reagent, 1, 7),
				"slimeball",
				"dustSulfur"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.motorMinecart, 1, 0), new Object[] {
				new ItemStack(MachineInit.machimeMaterials, 1, 3),
				new ItemStack(Items.MINECART, 1, 0)
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineInit.scooter, 1, 2), new Object[] {
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
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.scooter, 1, 0), new Object[] {
				new ItemStack(MachineInit.scooter, 1, 32767),
				"dyeOrange"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.scooter, 1, 1), new Object[] {
				new ItemStack(MachineInit.scooter, 1, 32767),
				"dyeBlue"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.scooter, 1, 2), new Object[] {
				new ItemStack(MachineInit.scooter, 1, 32767),
				"dyeWhite"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MachineInit.scooter, 1, 3), new Object[] {
				new ItemStack(MachineInit.scooter, 1, 32767),
				"dyeBlack"
		}));
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
	}

}
