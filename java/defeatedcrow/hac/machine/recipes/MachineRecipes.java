package defeatedcrow.hac.machine.recipes;

import java.util.List;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.IReactorRecipe;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.ReactorRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class MachineRecipes {

	public static void load() {
		loadBasicRecipe();
		loadMachineRecipe();
		loadClimateRecipe();
		if (ModuleConfig.machine_advanced) {
			loadAdvancedRecipe();
			loadReactorRecipe();
		}
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

	}

	static void loadAdvancedRecipe() {
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
	}

	static void loadClimateRecipe() {}

	static void loadMachineRecipe() {
		// mill
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 0), new ItemStack(MainInit.gems, 1, 5),
				0.1F, "oreCopper");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 1), new ItemStack(MainInit.oreDust, 1, 5),
				0.25F, "oreZinc");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 5), new ItemStack(MainInit.oreDust, 1, 5),
				0.25F, "oreIron");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 7), new ItemStack(MainInit.oreDust, 1, 9),
				0.25F, "oreMagnetite");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 2), new ItemStack(MainInit.oreDust, 1, 5),
				0.25F, "oreNickel");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 4), new ItemStack(MainInit.oreDust, 1, 3),
				0.25F, "oreGold");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 3), new ItemStack(MainInit.oreDust, 1, 0),
				0.25F, "oreSilver");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 3), new ItemStack(MainInit.gems, 1, 6), 0.05F,
				"oreGypsum");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 0), new ItemStack(MainInit.gems, 1, 4), 0.05F,
				"oreChalcedonyB");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 2), new ItemStack(Items.QUARTZ), 0.25F,
				"oreChalcedonyW");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 1, 4), new ItemStack(MainInit.gems, 1, 4), 0.2F,
				"oreSapphire");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 2), new ItemStack(MainInit.gems, 1, 7),
				0.05F, "oreLime");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 8), new ItemStack(MainInit.gems, 1, 8), 0.5F,
				"oreSalt");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 1, 9), new ItemStack(MainInit.gems, 1, 9), 0.5F,
				"oreNiter");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 1, 10), new ItemStack(MainInit.gems, 1, 10),
				0.5F, "oreSulfur");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 8), new ItemStack(MainInit.oreDust, 1, 8),
				0.25F, "oreTin");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 1, 11), new ItemStack(MainInit.gems, 1, 11),
				0.2F, "oreSchorl");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 4, 12), new ItemStack(MainInit.gems, 1, 13),
				0.05F, "oreSerpentine");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 1, 14), new ItemStack(MainInit.gems, 1, 14),
				0.2F, "oreGarnet");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 10), new ItemStack(MainInit.gems, 1, 15),
				0.2F, "oreTitanium");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 11),
				new ItemStack(MainInit.oreDust, 1, 11), 0.5F, "oreAluminium");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), new ItemStack(MainInit.oreDust, 1, 1),
				0.75F, "oreBismuth");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.QUARTZ, 1, 0), new ItemStack(Items.QUARTZ, 1, 0), 0.2F,
				"oreQuartz");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.EMERALD, 1, 0), new ItemStack(Items.EMERALD, 1, 0), 0.2F,
				"oreEmerald");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DIAMOND, 1, 0), new ItemStack(Items.DIAMOND, 1, 0), 0.2F,
				"oreDiamond");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DYE, 3, 4), new ItemStack(MainInit.oreDust, 1, 0), 1.0F,
				"oreLapis");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.REDSTONE, 4, 0), new ItemStack(MainInit.oreDust, 1, 9),
				0.5F, "oreRedstone");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.COAL, 2, 0), new ItemStack(MainInit.miscDust, 1, 0), 0.5F,
				"oreCoal");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 4, 0), new ItemStack(MainInit.oreDust, 1, 11),
				0.5F, new ItemStack(Blocks.RED_SANDSTONE, 1, 32767));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MainInit.oreDust, 1, 11),
				0.25F, new ItemStack(Blocks.SAND, 1, 1));

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 0), new ItemStack(Items.COAL, 1, 0));

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), "gemQuartz");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), "gemChalcedony");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 4, 3), "logWood");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 6), "gemNiter");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 7), "gemSulfur");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 8), "gemGarnet");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 0), "gemSalt");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 1), "cropWheat");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 10), "gemRutile");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), "gemBauxite");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), "gemBismuth");

		// ingot粉砕
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 0), "ingotCopper");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 1), "ingotZinc");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 2), "ingotNickel");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 3), "ingotSilver");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 4), "ingotGold");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 5), "ingotIron");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 8), "ingotTin");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), "ingotAluminium");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), "ingotBismuth");

		// 以下バニラ
		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), null, 0F, "cobblestone");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Items.FLINT, 1, 0), 1.0F,
				new ItemStack(Blocks.GRAVEL, 1, 0));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.BLAZE_POWDER, 3, 0),
				new ItemStack(Items.BLAZE_POWDER, 1, 0), 0.5F, "stickBlaze");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DYE, 5, 15), new ItemStack(Items.DYE, 2, 15), 0.5F,
				"bone");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.SUGAR, 2, 0), new ItemStack(Items.SUGAR, 1, 0), 0.5F,
				"sugarcane");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 4, 0), null, 0.0F,
				new ItemStack(Blocks.SANDSTONE, 1, 32767));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.GLOWSTONE_DUST, 4, 0), new ItemStack(Blocks.GLOWSTONE));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.BLAZE_POWDER, 1, 0), new ItemStack(Blocks.MAGMA));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.ICE, 1, 0), null, 0.0F,
				new ItemStack(Blocks.PACKED_ICE));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F,
				new ItemStack(Blocks.NETHER_BRICK, 1, 0));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.NETHERBRICK, 6, 0), null, 0F,
				new ItemStack(Blocks.NETHER_BRICK_STAIRS, 1, 0));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F,
				new ItemStack(Blocks.NETHER_BRICK_FENCE, 1, 0));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.END_STONE, 4, 0), null, 0F,
				new ItemStack(Blocks.END_BRICKS, 1, 0));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.STRING, 4, 0), null, 0F,
				new ItemStack(Blocks.WOOL, 1, 32767));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F,
				new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 32767));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F,
				new ItemStack(Blocks.HARDENED_CLAY, 1, 0));

		// plugin
		List<ItemStack> dust_o = OreDictionary.getOres("dustOsmium");
		if (!dust_o.isEmpty()) {
			ItemStack ret = new ItemStack(dust_o.get(0).getItem(), 2, dust_o.get(0).getItemDamage());
			RecipeAPI.registerMills.addRecipe(ret, "oreOsmium");
		}

		List<ItemStack> dust_l = OreDictionary.getOres("dustLead");
		if (!dust_l.isEmpty()) {
			ItemStack ret = new ItemStack(dust_l.get(0).getItem(), 2, dust_l.get(0).getItemDamage());
			RecipeAPI.registerMills.addRecipe(ret, "oreLead");
		}
	}

	static void loadReactorRecipe() {
		// Ni-Al 水蒸気改質系
		IReactorRecipe r1 = new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 1), null,
				new FluidStack(MachineInit.fuelOil, 200), null, DCHeatTier.KILN, 0,
				new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(FoodInit.oil, 200),
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {});
		RecipeAPI.registerReactorRecipes.addRecipe(r1, DCHeatTier.KILN);

		IReactorRecipe r2 = new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 0), null,
				new FluidStack(MachineInit.fuelOil, 200), null, DCHeatTier.KILN, 0,
				new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(FoodInit.blackLiquor, 200),
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {});
		RecipeAPI.registerReactorRecipes.addRecipe(r2, DCHeatTier.KILN);

		IReactorRecipe r3 = new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 0),
				new ItemStack(MainInit.miscDust, 1, 7), new FluidStack(MachineInit.fuelOil, 100), null, DCHeatTier.OVEN,
				0.25F, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(FluidRegistry.WATER, 200), null,
				new Object[] {
						new ItemStack(Items.COAL, 1)
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r3, DCHeatTier.OVEN);

		IReactorRecipe r4 = new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 7), null,
				new FluidStack(MachineInit.fuelOil, 50), null, DCHeatTier.OVEN, 0,
				new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(FluidRegistry.WATER, 200), null,
				new Object[] {
						new ItemStack(MachineInit.reagent, 1, 0)
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r4, DCHeatTier.OVEN);

		// Pt 炭化水素ガス
		IReactorRecipe r5 = new ReactorRecipe(null, null, new FluidStack(MachineInit.fuelGas, 200), null,
				DCHeatTier.SMELTING, 0, new ItemStack(MachineInit.catalyst, 1, 3),
				new FluidStack(MachineInit.fuelOil, 200), new FluidStack(FluidRegistry.WATER, 200), new Object[] {});
		RecipeAPI.registerReactorRecipes.addRecipe(r5, DCHeatTier.SMELTING);

		IReactorRecipe r6 = new ReactorRecipe(null, null, new FluidStack(MachineInit.fuelGas, 400), null,
				DCHeatTier.SMELTING, 0, new ItemStack(MachineInit.catalyst, 1, 3),
				new FluidStack(MachineInit.fuelOil, 200), new FluidStack(MachineInit.hydrogen, 200), new Object[] {});
		RecipeAPI.registerReactorRecipes.addRecipe(r6, DCHeatTier.SMELTING);

		// Ni 水素ガス
		IReactorRecipe r7 = new ReactorRecipe(null, null, new FluidStack(MachineInit.hydrogen, 200), null,
				DCHeatTier.SMELTING, 0, new ItemStack(MachineInit.catalyst, 1, 0),
				new FluidStack(MachineInit.fuelGas, 200), new FluidStack(FluidRegistry.WATER, 200), new Object[] {});
		RecipeAPI.registerReactorRecipes.addRecipe(r7, DCHeatTier.SMELTING);

		// Fe ハーバーボッシュ
		IReactorRecipe r8 = new ReactorRecipe(null, null, new FluidStack(MachineInit.ammonia, 600), null,
				DCHeatTier.KILN, 0, new ItemStack(Blocks.IRON_ORE, 1, 0), new FluidStack(MachineInit.hydrogen, 1000),
				null, new Object[] {});
		RecipeAPI.registerReactorRecipes.addRecipe(r8, DCHeatTier.KILN);

		IReactorRecipe r19 = new ReactorRecipe(null, null, new FluidStack(MachineInit.ammonia, 600), null,
				DCHeatTier.KILN, 0, new ItemStack(MainInit.ores, 1, 5), new FluidStack(MachineInit.hydrogen, 1000),
				null, new Object[] {});
		RecipeAPI.registerReactorRecipes.addRecipe(r19, DCHeatTier.KILN);

		// Pt オストワルト
		IReactorRecipe r12 = new ReactorRecipe(null, null, new FluidStack(MachineInit.nitricAcid, 1000), null,
				DCHeatTier.KILN, 0, new ItemStack(MachineInit.catalyst, 1, 3),
				new FluidStack(MachineInit.ammonia, 1000), new FluidStack(FluidRegistry.WATER, 1000), new Object[] {});
		RecipeAPI.registerReactorRecipes.addRecipe(r12, DCHeatTier.KILN);

		// Bi ソハイオ
		IReactorRecipe r9 = new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 2), null, null, null,
				DCHeatTier.KILN, 0, new ItemStack(MachineInit.catalyst, 1, 2), new FluidStack(MachineInit.fuelGas, 200),
				new FluidStack(MachineInit.ammonia, 200), new Object[] {});
		RecipeAPI.registerReactorRecipes.addRecipe(r9, DCHeatTier.KILN);

		// 触媒なし
		// 硝安
		IReactorRecipe r10 = new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 4), null, null, null,
				DCHeatTier.NORMAL, 0, null, new FluidStack(MachineInit.nitricAcid, 200),
				new FluidStack(MachineInit.ammonia, 200), new Object[] {});
		RecipeAPI.registerReactorRecipes.addRecipe(r10, DCHeatTier.NORMAL);

		// 硝酸2
		IReactorRecipe r11 = new ReactorRecipe(null, null, new FluidStack(MachineInit.nitricAcid, 200), null,
				DCHeatTier.KILN, 0, null, new FluidStack(MachineInit.sulfuricAcid, 200),
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"dustNiter"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r11, DCHeatTier.KILN);

		IReactorRecipe r21 = new ReactorRecipe(null, null, new FluidStack(MachineInit.nitricAcid, 200), null,
				DCHeatTier.KILN, 0, null, new FluidStack(MachineInit.sulfuricAcid, 200),
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"dustSaltpeter"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r21, DCHeatTier.KILN);

		// 硫酸
		IReactorRecipe r13 = new ReactorRecipe(null, null, new FluidStack(MachineInit.sulfuricAcid, 200), null,
				DCHeatTier.KILN, 0, null, new FluidStack(FluidRegistry.WATER, 200), null, new Object[] {
						"dustNiter",
						"dustSulfur"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r13, DCHeatTier.KILN);

		IReactorRecipe r20 = new ReactorRecipe(null, null, new FluidStack(MachineInit.sulfuricAcid, 200), null,
				DCHeatTier.KILN, 0, null, new FluidStack(FluidRegistry.WATER, 200), null, new Object[] {
						"dustSaltpeter",
						"dustSulfur"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r20, DCHeatTier.KILN);

		// 硫酸2
		IReactorRecipe r14 = new ReactorRecipe(null, null, new FluidStack(MachineInit.sulfuricAcid, 500), null,
				DCHeatTier.KILN, 0, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(FluidRegistry.WATER, 500),
				null, new Object[] {
						"dustSulfur"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r14, DCHeatTier.KILN);

		// アルカリ1
		IReactorRecipe r15 = new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 3), null, null, null,
				DCHeatTier.KILN, 0, null, new FluidStack(FluidRegistry.WATER, 500), null, new Object[] {
						"dustSalt"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r15, DCHeatTier.KILN);

		// アルカリ2
		IReactorRecipe r16 = new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 3), null, null, null,
				DCHeatTier.KILN, 0, null, new FluidStack(FluidRegistry.WATER, 500), null, new Object[] {
						"dustLime"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r16, DCHeatTier.KILN);

		// ニトロ
		IReactorRecipe r17 = new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 5), null, null, null,
				DCHeatTier.OVEN, 0, null, new FluidStack(MachineInit.nitricAcid, 200),
				new FluidStack(MachineInit.sulfuricAcid, 200), new Object[] {
						"cropCotton"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r17, DCHeatTier.OVEN);

		IReactorRecipe r18 = new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 6), null, null, null,
				DCHeatTier.OVEN, 0, null, new FluidStack(MachineInit.nitricAcid, 200),
				new FluidStack(MachineInit.sulfuricAcid, 200), new Object[] {
						new ItemStack(MachineInit.reagent, 1, 1)
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r18, DCHeatTier.OVEN);

		// バニラ要素
		IReactorRecipe r22 = new ReactorRecipe(null, null, new FluidStack(FluidRegistry.LAVA, 200), null,
				DCHeatTier.UHT, 0, null, null, null, new Object[] {
						"dustSulfur",
						"cobblestone"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r22, DCHeatTier.UHT);

		IReactorRecipe r23 = new ReactorRecipe(null, null, new FluidStack(FluidRegistry.LAVA, 250), null,
				DCHeatTier.UHT, 0, null, null, null, new Object[] {
						"dustBlaze",
						"cobblestone"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r23, DCHeatTier.UHT);

		IReactorRecipe r24 = new ReactorRecipe(null, null, new FluidStack(FluidRegistry.LAVA, 1000), null,
				DCHeatTier.UHT, 0, null, null, null, new Object[] {
						"stickBlaze",
						"cobblestone"
				});
		RecipeAPI.registerReactorRecipes.addRecipe(r24, DCHeatTier.UHT);
	}

}
