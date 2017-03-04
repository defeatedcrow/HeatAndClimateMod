package defeatedcrow.hac.machine.recipes;

import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class MachineRecipes {

	public static void load() {
		loadBasicRecipe();
		loadMachineRecipes();
		loadClimateRecipe();
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

	}

	static void loadClimateRecipe() {}

	static void loadMachineRecipes() {
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

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.QUARTZ, 1, 0), new ItemStack(Items.QUARTZ, 1, 0), 0.2F,
				"oreQuartz");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.EMERALD, 1, 0), new ItemStack(Items.EMERALD, 1, 0), 0.2F,
				"oreEmerald");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DIAMOND, 1, 0), new ItemStack(Items.DIAMOND, 1, 0), 0.2F,
				"oreDiamond");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DYE, 3, 4), new ItemStack(MainInit.oreDust, 1, 0), 0.25F,
				"oreLapis");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.REDSTONE, 4, 0), new ItemStack(MainInit.oreDust, 1, 9),
				0.5F, "oreRedstone");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.COAL, 2, 0), new ItemStack(MainInit.miscDust, 1, 0), 0.5F,
				"oreCoal");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 0), new ItemStack(Items.COAL, 1, 0));

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), "gemQuartz");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), "gemChalcedony");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 4, 3), "logWood");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 6), "gemNiter");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 7), "gemSulfur");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 8), "gemGarnet");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), "gemSalt");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 1), "cropWheat");

		// 以下バニラ
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

		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 4, 1),
				new ItemStack(Blocks.RED_SANDSTONE, 1, 32767));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.GLOWSTONE_DUST, 4, 0), new ItemStack(Blocks.GLOWSTONE));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.BLAZE_POWDER, 1, 0), new ItemStack(Blocks.MAGMA));

		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.ICE, 1, 0), null, 0.0F,
				new ItemStack(Blocks.PACKED_ICE));
	}

}
