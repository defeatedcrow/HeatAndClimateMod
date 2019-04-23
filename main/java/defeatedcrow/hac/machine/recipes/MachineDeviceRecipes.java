package defeatedcrow.hac.machine.recipes;

import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.SpinningRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class MachineDeviceRecipes {

	public static void load() {
		loadMachineRecipe();
		loadClimateRecipe();
		if (ModuleConfig.machine_advanced) {
			loadReactorRecipe();
		}
	}

	static void loadClimateRecipe() {
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MachineInit.fuelCont, 1,
				0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false, "blockCoal");

		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MachineInit.fuelCont, 1,
				1), DCHeatTier.UHT, DCHumidity.DRY, DCAirflow.TIGHT, false, "blockFuelCoke");
	}

	static void loadMachineRecipe() {
		// mill
		if (ModuleConfig.r_mill) {
			// new ores
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 0), new ItemStack(MainInit.oreItem, 1,
					5), 0.15F, "oreRed");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 1), new ItemStack(MainInit.oreItem, 1,
					6), 0.15F, "oreGreen");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 2), new ItemStack(MainInit.oreItem, 1,
					7), 0.15F, "oreBlue");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 3), new ItemStack(MainInit.oreItem, 1,
					8), 0.15F, "oreWhite");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 4), new ItemStack(MainInit.oreItem, 1,
					9), 0.15F, "oreBlack");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 5), new ItemStack(MainInit.oreItem, 1,
					10), 0.15F, "oreLargeRed");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 6), new ItemStack(MainInit.oreItem, 1,
					11), 0.15F, "oreLargeGreen");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 7), new ItemStack(MainInit.oreItem, 1,
					12), 0.15F, "oreLargeBlue");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 8), new ItemStack(MainInit.oreItem, 1,
					13), 0.15F, "oreLargeWhite");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 9), new ItemStack(MainInit.oreItem, 1,
					14), 0.15F, "oreLargeBlack");

			// old ores
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 0), "oreCopper");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 1), "oreZinc");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 2), "oreNickel");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 3), "oreSilver");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 4), "oreGold");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 5), "oreIron");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 7), "oreMagnetite");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 8), "oreTin");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 9), "oreChromium");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 10), "oreTitanium");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), "oreAluminium");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), "oreAluminum");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 12), "oreBismuth");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 13), "oreLead");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 14), "oreManganese");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 15), "oreMolybdenum");

			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 0), "oreChalcedonyBlue");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 2), "oreChalcedonyWhite");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 3, 3), "oreGypsum");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 4), "oreSapphire");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 3, 8), "oreSalt");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 9), "oreNiter");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 10), "oreSulfur");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 11), "oreSchorl");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 4, 12), "oreSerpentine");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems, 2, 14), "oreGarnet");

			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 3, 2), "oreLime");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 2, 9), "oreApatite");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.QUARTZ, 2, 0), "oreQuartz");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.EMERALD, 2, 0), "oreEmerald");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DIAMOND, 2, 0), "oreDiamond");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DYE, 3, 4), "oreLapis");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.REDSTONE, 4, 0), "oreRedstone");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.COAL, 2, 0), "oreCoal");

			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 4, 0), new ItemStack(MainInit.oreDust, 1,
					11), 0.5F, new ItemStack(Blocks.RED_SANDSTONE, 1, 32767));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MainInit.oreDust, 1,
					11), 0.25F, new ItemStack(Blocks.SAND, 1, 1));
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 4, 2), new ItemStack(MainInit.miscDust,
					1, 9), 1F, new ItemStack(Blocks.BONE_BLOCK, 1, 0));

			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 0), new ItemStack(Items.COAL, 1, 0));
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), "gemQuartz");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), "gemChalcedony");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 6), "gemNiter");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 7), "gemSulfur");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 8), "gemGarnet");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 0), "gemSalt");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 10), "gemRutile");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), "gemBauxite");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), "gemBismuth");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 9), "gemApatite");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 11), "gemSerpentine");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN
					.getDyeDamage()), "gemMalachite");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MachineInit.reagent, 2, 0), "gemCrudeOil");

			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 4, 3), "logWood");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 1), "cropWheat");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 3), "cropPotato");

			// ingot粉砕
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 0), "ingotCopper");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 1), "ingotZinc");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 2), "ingotNickel");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 3), "ingotSilver");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 4), "ingotGold");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 5), "ingotIron");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 8), "ingotTin");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), "ingotAluminium");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), "ingotAluminum");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), "ingotBismuth");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 13), "ingotLead");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 14), "ingotMagnanese");

			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), "treeSapling");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), "blockTallGrass");
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), "treeLeaves");

			// 以下バニラ
			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), null, 0F, "cobblestone");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Items.FLINT, 1,
					0), 1.0F, new ItemStack(Blocks.GRAVEL, 1, 0));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.BLAZE_POWDER, 3, 0), new ItemStack(Items.BLAZE_POWDER,
					1, 0), 0.5F, "stickBlaze");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DYE, 5, 15), new ItemStack(Items.DYE, 2,
					15), 0.5F, "bone");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.SUGAR, 3, 0), new ItemStack(Items.SUGAR, 1,
					0), 0.5F, "sugarcane");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 4, 0), null, 0.0F, new ItemStack(
					Blocks.SANDSTONE, 1, 32767));
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), new ItemStack(MainInit.miscDust,
					1, 10), 0.5F, new ItemStack(Blocks.SAND, 1, 0));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.GLOWSTONE_DUST, 4, 0), new ItemStack(
					Blocks.GLOWSTONE));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.BLAZE_POWDER, 1, 0), new ItemStack(Blocks.MAGMA));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.ICE, 1, 0), null, 0.0F, new ItemStack(
					Blocks.PACKED_ICE));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F, new ItemStack(
					Blocks.NETHER_BRICK, 1, 0));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.NETHERBRICK, 6, 0), null, 0F, new ItemStack(
					Blocks.NETHER_BRICK_STAIRS, 1, 0));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F, new ItemStack(
					Blocks.NETHER_BRICK_FENCE, 1, 0));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.END_STONE, 4, 0), null, 0F, new ItemStack(
					Blocks.END_BRICKS, 1, 0));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.STRING, 4, 0), null, 0F, new ItemStack(Blocks.WOOL, 1,
					32767));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.STRING, 4, 0), "blockWool");
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F, new ItemStack(
					Blocks.STAINED_HARDENED_CLAY, 1, 32767));
			RecipeAPI.registerMills.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F, new ItemStack(
					Blocks.HARDENED_CLAY, 1, 0));

			// plugin
			List<ItemStack> dust_o = OreDictionary.getOres("dustOsmium");
			if (!dust_o.isEmpty()) {
				ItemStack ret = new ItemStack(dust_o.get(0).getItem(), 2, dust_o.get(0).getItemDamage());
				RecipeAPI.registerMills.addRecipe(ret, "oreOsmium");
			}
		}

		// spinning machine
		if (ModuleConfig.r_spinning) {
			RecipeAPI.registerSpinningRecipes.addRecipe(new SpinningRecipe(new ItemStack(MainInit.clothes, 1, 0), 4,
					"blockTallGrass"));
			RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 1), 4, new ItemStack(
					Items.STRING));
			RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 1), 4, "cropCotton");
			RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 2, 0), 1, new ItemStack(
					Blocks.HAY_BLOCK, 1, 0));
			RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 2, 1), 1, new ItemStack(
					MainInit.cropBasket, 1, 5));
			RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 4), 4, "dustAsbest");
			RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 6), 2, new ItemStack(
					MainInit.silkworm, 1, 2));
			RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 4, 6), 1, new ItemStack(
					MainInit.cropBasket, 1, 11));
		}
	}

	static void loadReactorRecipe() {
		if (ModuleConfig.r_reactor) {
			// new ores 浮上選鉱
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 5), new ItemStack(
					MainInit.oreItem, 1, 10), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] {
													"oreRed",
													"soap"
			});
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 6), new ItemStack(
					MainInit.oreItem, 1, 11), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] {
													"oreGreen",
													"soap"
			});
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 7), new ItemStack(
					MainInit.oreItem, 1, 12), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] {
													"oreBlue",
													"soap"
			});
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 8), new ItemStack(
					MainInit.oreItem, 1, 13), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] {
													"oreWhite",
													"soap"
			});
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 9), new ItemStack(
					MainInit.oreItem, 1, 14), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] {
													"oreBlack",
													"soap"
			});
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 10), new ItemStack(
					MainInit.gems, 1, 21), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] {
													"oreLargeRed",
													"soap"
			});
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 11), new ItemStack(
					MainInit.gems, 1, 19), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] {
													"oreLargeGreen",
													"soap"
			});
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 12), new ItemStack(
					MainInit.gems, 1, 20), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] {
													"oreLargeBlue",
													"soap"
			});
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 13), new ItemStack(
					MainInit.gems, 1, 6), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] {
													"oreLargeWhite",
													"soap"
			});
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.oreItem, 2, 14), new ItemStack(
					MainInit.gems, 1, 22), 0.5F, new FluidStack(MainInit.blackLiquor,
							200), null, DCHeatTier.NORMAL, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
									FluidRegistry.WATER, 200), new FluidStack(MainInit.sulfuricAcid,
											200), new Object[] {
													"oreLargeBlack",
													"soap"
			});

			// Ni-Al 水蒸気改質系
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					1), null, 0, new FluidStack(MainInit.fuelOil, 500), null, DCHeatTier.KILN, new ItemStack(
							MachineInit.catalyst, 1, 0), new FluidStack(MainInit.oil, 500), new FluidStack(
									FluidRegistry.WATER, 500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					0), null, 0, new FluidStack(MainInit.fuelOil, 500), null, DCHeatTier.KILN, new ItemStack(
							MachineInit.catalyst, 1, 0), new FluidStack(MainInit.blackLiquor, 500), new FluidStack(
									FluidRegistry.WATER, 500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 0), new ItemStack(
					MachineInit.reagent, 1, 14), 0.5F, new FluidStack(MainInit.fuelOil,
							200), null, DCHeatTier.OVEN, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(
									FluidRegistry.WATER, 500), null, new Object[] {
											"gemCoal"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					7), null, 0, new FluidStack(MainInit.fuelOil, 400), null, DCHeatTier.OVEN, new ItemStack(
							MachineInit.catalyst, 1, 0), new FluidStack(FluidRegistry.WATER, 1000), null, new Object[] {
									new ItemStack(MachineInit.reagent, 1, 0)
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.miscDust, 1, 13), null, 0, new FluidStack(
					MainInit.fuelOil, 400), new FluidStack(MainInit.fuelGas, 400), DCHeatTier.OVEN, new ItemStack(
							MachineInit.catalyst, 1, 0), null, null, new Object[] {
									"gemCrudeOil"
			});

			// Pt 炭化水素ガス
			RecipeAPI.registerReactorRecipes.addRecipe(null, new ItemStack(MainInit.miscDust, 1,
					7), 0.25F, new FluidStack(MainInit.fuelGas, 1000), null, DCHeatTier.SMELTING, new ItemStack(
							MachineInit.catalyst, 1, 3), new FluidStack(MainInit.fuelOil, 500), new FluidStack(
									FluidRegistry.WATER, 500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(null, new ItemStack(MainInit.miscDust, 1,
					7), 0.25F, new FluidStack(MainInit.fuelGas, 2000), null, DCHeatTier.SMELTING, new ItemStack(
							MachineInit.catalyst, 1, 3), new FluidStack(MainInit.fuelOil, 500), new FluidStack(
									MainInit.hydrogen, 500), new Object[] {});

			// Pt アセチレン合成
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					14), null, 0, null, null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1,
							3), new FluidStack(MainInit.fuelGas, 500), null, new Object[] {});

			// Ni 水素ガス
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.hydrogen,
					1000), null, DCHeatTier.SMELTING, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(
							MainInit.fuelGas, 500), new FluidStack(FluidRegistry.WATER, 500), new Object[] {});

			// Bz エタノール脱水
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.fuelGas,
					1000), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
							MainInit.ethanol, 1000), new FluidStack(MainInit.sulfuricAcid, 200), new Object[] {});

			// 磁鉄鉱 ハーバーボッシュ
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.ammonia,
					500), null, DCHeatTier.KILN, "oreMagnetite", new FluidStack(MainInit.hydrogen,
							1000), null, new Object[] {});

			// Pt オストワルト
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.nitricAcid,
					1000), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
							MainInit.ammonia, 1000), new FluidStack(FluidRegistry.WATER, 1000), new Object[] {});

			// Bi ソハイオ
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					2), null, 0, null, null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 2), new FluidStack(
							MainInit.fuelGas, 500), new FluidStack(MainInit.ammonia, 500), new Object[] {});

			// 触媒なし
			// 硝安
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					4), null, 0, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							200), new FluidStack(MainInit.ammonia, 200), new Object[] {});

			// 硝酸2
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.nitricAcid,
					200), null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							200), new FluidStack(FluidRegistry.WATER, 200), new Object[] {
									"dustNiter"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.nitricAcid,
					200), null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							200), new FluidStack(FluidRegistry.WATER, 200), new Object[] {
									"dustSaltpeter"
			});

			// 硫酸
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.sulfuricAcid,
					200), null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(FluidRegistry.WATER,
							200), null, new Object[] {
									"dustNiter",
									"dustSulfur"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.sulfuricAcid,
					200), null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(FluidRegistry.WATER,
							200), null, new Object[] {
									"dustSaltpeter",
									"dustSulfur"
			});

			// 硫酸2
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.sulfuricAcid,
					500), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(
							FluidRegistry.WATER, 500), null, new Object[] {
									"dustSulfur"
			});

			// アルカリ1
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					3), null, 0, null, null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(FluidRegistry.WATER,
							500), null, new Object[] {
									"dustSalt"
			});

			// アルカリ2
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					3), null, 0, null, null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(FluidRegistry.WATER,
							500), null, new Object[] {
									"dustLime"
			});

			// ニトロ
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					5), null, 0, null, null, DCHeatTier.HOT, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							200), new FluidStack(MainInit.sulfuricAcid, 200), new Object[] {
									"cropCotton"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					6), null, 0, null, null, DCHeatTier.HOT, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							200), new FluidStack(MainInit.sulfuricAcid, 200), new Object[] {
									new ItemStack(MachineInit.reagent, 1, 1)
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(Blocks.TNT, 1,
					0), null, 0, null, null, DCHeatTier.HOT, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							500), new FluidStack(MainInit.sulfuricAcid, 200), new Object[] {
									new ItemStack(MachineInit.reagent, 1, 14),
									"sand",
									"paper"
			});

			// 硝石
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.miscDust, 1,
					6), null, 0, null, null, DCHeatTier.OVEN, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							200), null, new Object[] {
									"dustAlkali"
			});

			// コークス

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 13), new ItemStack(
					MachineInit.reagent, 1,
					0), 0.5F, null, null, DCHeatTier.KILN, (ItemStack) null, null, null, new Object[] {
							"gemCoal"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 13), new ItemStack(
					MachineInit.reagent, 1,
					0), 0.5F, null, null, DCHeatTier.KILN, (ItemStack) null, null, null, new Object[] {
							new ItemStack(Items.COAL, 1, 1),
							new ItemStack(Items.COAL, 1, 1)
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 4, 13), new ItemStack(
					MachineInit.reagent, 1,
					0), 1.0F, null, null, DCHeatTier.KILN, (ItemStack) null, null, null, new Object[] {
							new ItemStack(MainInit.logCont, 1, 6)
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.fuelCont, 1, 0), new ItemStack(
					MachineInit.reagent, 4,
					0), 1F, null, null, DCHeatTier.KILN, (ItemStack) null, null, null, new Object[] {
							"blockCoal"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 13), new ItemStack(
					MainInit.miscDust, 1,
					7), 0.5F, null, null, DCHeatTier.SMELTING, (ItemStack) null, null, null, new Object[] {
							new ItemStack(MachineInit.reagent, 1, 0)
			});

			// カーバイド

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					9), null, 0, null, null, DCHeatTier.UHT, (ItemStack) null, null, null, new Object[] {
							"fuelCoke",
							"dustLime"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.fuelCont, 1,
					1), null, 0, null, null, DCHeatTier.UHT, (ItemStack) null, null, null, new Object[] {
							"blockFuelCoke",
							"dustLime"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.fuelGas,
					1000), null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(FluidRegistry.WATER,
							200), null, new Object[] {
									"gemCarbide"
			});

			// バニラ要素
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(FluidRegistry.LAVA,
					200), null, DCHeatTier.UHT, (ItemStack) null, null, null, new Object[] {
							"dustSulfur",
							"cobblestone"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(FluidRegistry.LAVA,
					250), null, DCHeatTier.UHT, (ItemStack) null, null, null, new Object[] {
							"dustBlaze",
							"cobblestone"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(FluidRegistry.LAVA,
					1000), null, DCHeatTier.UHT, (ItemStack) null, null, null, new Object[] {
							"stickBlaze",
							"cobblestone"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(
					Blocks.ICE), null, 0, null, null, DCHeatTier.FROSTBITE, (ItemStack) null, new FluidStack(
							FluidRegistry.WATER, 1000), null, new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(
					Blocks.OBSIDIAN), null, 0, null, null, DCHeatTier.FROSTBITE, (ItemStack) null, new FluidStack(
							FluidRegistry.LAVA, 1000), null, new Object[] {});

			// nitrogen
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.nitrogen,
					1000), null, DCHeatTier.ABSOLUTE, (ItemStack) null, null, null, new Object[] {
							"dustCoal"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.nitrogen,
					1000), null, DCHeatTier.ABSOLUTE, (ItemStack) null, null, null, new Object[] {
							new ItemStack(Items.COAL, 1, 1)
			});

			// 丸石生成
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(
					Blocks.STONE), null, 0, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(
							FluidRegistry.WATER, 100), new FluidStack(FluidRegistry.LAVA, 100), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					12), null, 0, null, null, DCHeatTier.OVEN, (ItemStack) null, new FluidStack(MainInit.ammonia,
							100), null, new Object[] {
									new ItemStack(MachineInit.reagent, 1, 11)
			});

			// 合成宝石
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(Items.QUARTZ, 1,
					0), null, 0F, null, null, DCHeatTier.UHT, "gemQuartz", null, null, new Object[] {
							"dustCrystal"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.gems, 1,
					4), null, 0F, null, null, DCHeatTier.UHT, "gemSapphire", null, null, new Object[] {
							"dustAluminum",
							"dustChromium",
							"dustIron"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.gems, 1,
					14), null, 0F, null, null, DCHeatTier.UHT, "gemGarnet", null, null, new Object[] {
							"dustCrystal",
							"dustAluminum",
							"dustIron"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.gems, 1,
					24), null, 0F, null, null, DCHeatTier.UHT, "gemDiamond", null, null, new Object[] {
							"dustTitanium",
							"dustBlaze",
							"gemCelestite"
			});

			// plating
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					0), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							100), new FluidStack(FluidRegistry.WATER, 100), new Object[] {
									"dustChromium",
									"dustZinc",
									"dustAlkali"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					1), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							100), null, new Object[] {
									"dustNickel",
									new ItemStack(MachineInit.reagent, 1, 11),
									"dustAlkali"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					2), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							100), null, new Object[] {
									"dustZinc",
									"dustIron",
									"dustAlkali"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					3), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.nitricAcid,
							100), new FluidStack(MainInit.ammonia, 100), new Object[] {
									"dustSilver",
									"dustAlkali"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					4), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							100), null, new Object[] {
									"dustBismuth",
									"dustTin",
									new ItemStack(MachineInit.reagent, 1, 11),
									"dustAlkali"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					5), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.sulfuricAcid,
							100), null, new Object[] {
									"dustBlaze",
									"dustGold",
									"dustAlkali"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					6), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.fuelOil,
							100), null, new Object[] {
									new ItemStack(Items.SPIDER_EYE, 1, 0),
									"dustAlkali"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					7), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, null, null, new Object[] {
							new ItemStack(MainInit.miscDust, 1, 10),
							new ItemStack(MachineInit.reagent, 1, 12),
							"paper"
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.platingChrome, 1,
					8), null, 0F, null, null, DCHeatTier.NORMAL, (ItemStack) null, new FluidStack(MainInit.fuelOil,
							100), new FluidStack(FluidRegistry.WATER, 100), new Object[] {
									new ItemStack(MachineInit.reagent, 1, 2)
			});

			// food
			if (ModuleConfig.food) {
				// Ni 水蒸気添加
				RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
						3), null, 1.0F, null, null, DCHeatTier.OVEN, (ItemStack) null, new FluidStack(MainInit.oil,
								200), new FluidStack(FluidRegistry.WATER, 200), new Object[] {
										"dustSalt"
				});

				RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1,
						4), null, 1.0F, null, null, DCHeatTier.OVEN, new ItemStack(MachineInit.catalyst, 1,
								0), new FluidStack(MainInit.oil, 200), new FluidStack(MainInit.hydrogen,
										100), new Object[] {});
			}
		}
	}

}
