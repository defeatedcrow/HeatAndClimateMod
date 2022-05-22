package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.module.HaCModule;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.recipes.device.RegisterCrusherRecipe;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class DCPluginUGB {

	public static final DCPluginUGB INSTANCE = new DCPluginUGB();

	private DCPluginUGB() {}

	public static void init() {
		ItemStack ti = RegisterCrusherRecipe.Ti_Blade;
		ItemStack sc = RegisterCrusherRecipe.Screen_Blade;

		// dic
		Block igs = Block.REGISTRY.getObject(new ResourceLocation("undergroundbiomes", "igneous_stone"));
		if (igs != null) {
			// 花崗岩
			OreDictionary.registerOre("stoneGranite", new ItemStack(igs, 1, 0));
			OreDictionary.registerOre("stoneGranite", new ItemStack(igs, 1, 1));
			// 流紋岩
			OreDictionary.registerOre("stoneRhyolite", new ItemStack(igs, 1, 2));
			// 安山岩
			OreDictionary.registerOre("stoneAndesite", new ItemStack(igs, 1, 3));
			// 斑糲岩
			OreDictionary.registerOre("stoneGabro", new ItemStack(igs, 1, 4));
			// 玄武岩
			OreDictionary.registerOre("stoneBasalt", new ItemStack(igs, 1, 5));
			// コマチアイト
			OreDictionary.registerOre("stoneKomatite", new ItemStack(igs, 1, 6));
			// デイサイト
			OreDictionary.registerOre("stoneDacite", new ItemStack(igs, 1, 7));

			DCRecipe.jsonShapedRecipe(HaCModule.getPlugin("undergroundbiomes"), "plugin", new ItemStack(MainInit.builds, 2, 8), new Object[] {
					"XY",
					"YX",
					'X',
					"stoneDacite",
					'Y',
					"gemFlint"
			});

			DCRecipe.jsonShapedRecipe(HaCModule.getPlugin("undergroundbiomes"), "plugin", new ItemStack(MainInit.builds, 2, 9), new Object[] {
					"XY",
					"YX",
					'X',
					"stoneGabro",
					'Y',
					"gemFlint"
			});

			DCRecipe.jsonShapedRecipe(HaCModule.getPlugin("undergroundbiomes"), "plugin", 2, new ItemStack(MainInit.builds, 2, 9), new Object[] {
					"XY",
					"YX",
					'X',
					"stoneBasalt",
					'Y',
					"gemFlint"
			});

			if (ModuleConfig.r_mill) {
				RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.miscDust, 1, 7), 0.05F, "stoneBasalt");

				RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.oreDust, 1, 2), 0.05F, "stoneKomatite");
			}

			if (ModuleConfig.r_crusher) {

				RecipeAPI.registerCrushers
						.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.miscDust, 1, 7), 0.2F, new ItemStack(MainInit.oreItem, 1, 4), 0.05F, ti, "stoneBasalt");

				RecipeAPI.registerCrushers
						.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.oreItem, 1, 6), 0.2F, new ItemStack(MainInit.oreItem, 1, 4), 0.05F, ti, "stoneKomatite");
			}
		}

		Block met = Block.REGISTRY.getObject(new ResourceLocation("undergroundbiomes", "metamorphic_stone"));
		if (met != null) {

			OreDictionary.registerOre("stoneEclogite", new ItemStack(met, 1, 1));
			OreDictionary.registerOre("stoneMarble", new ItemStack(met, 1, 2));
			OreDictionary.registerOre("blockMarble", new ItemStack(met, 1, 2));
			OreDictionary.registerOre("stoneQuarzite", new ItemStack(met, 1, 3));
			OreDictionary.registerOre("stoneSchist", new ItemStack(met, 1, 4));
			OreDictionary.registerOre("stoneSchist", new ItemStack(met, 1, 5));

			if (ModuleConfig.r_mill) {
				RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.miscDust, 1, 8), 0.1F, "stoneEclogite");

				RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.miscDust, 1, 1), 0.1F, "stoneQuarzite");

				RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.miscDust, 1, 12), 0.1F, "stoneSchist");
			}
			if (ModuleConfig.r_crusher) {

				RecipeAPI.registerCrushers
						.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.gems_red, 1, 1), 0.15F, new ItemStack(MainInit.gems_green, 1, 1), 0.05F, sc, "stoneEclogite");

				RecipeAPI.registerCrushers
						.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.gems_white, 1, 0), 0.2F, new ItemStack(Items.QUARTZ, 1, 0), 0.05F, sc, "stoneQuarzite");

				RecipeAPI.registerCrushers
						.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.miscDust, 1, 12), 0.3F, new ItemStack(MainInit.miscDust, 1, 11), 0.1F, ti, new ItemStack(met, 1, 4));

				RecipeAPI.registerCrushers
						.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.miscDust, 1, 12), 0.3F, new ItemStack(MainInit.miscDust, 1, 11), 0.1F, ti, new ItemStack(met, 1, 5));
			}
		}

		Block sed = Block.REGISTRY.getObject(new ResourceLocation("undergroundbiomes", "sedimentary_stone"));
		if (sed != null) {
			OreDictionary.registerOre("oreLime", new ItemStack(sed, 1, 0));
			OreDictionary.registerOre("stoneChalk", new ItemStack(sed, 1, 1));
			OreDictionary.registerOre("stoneShele", new ItemStack(sed, 1, 2));
			OreDictionary.registerOre("stoneSilt", new ItemStack(sed, 1, 3));
			OreDictionary.registerOre("stoneChart", new ItemStack(sed, 1, 7));

			if (ModuleConfig.r_mill) {
				RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 2), "stoneChalk");

				RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MainInit.gems_black, 1, 0), 0.05F, "stoneShele");

				RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), new ItemStack(Items.FLINT), 0.2F, "stoneChart");
			}

			if (ModuleConfig.r_crusher) {
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 2), new ItemStack(Items.BONE, 1, 0), 0.1F, ti, "stoneChalk");

				RecipeAPI.registerCrushers
						.addRecipe(new ItemStack(MainInit.miscDust, 2, 2), new ItemStack(MainInit.gems_black, 1, 0), 1F, new ItemStack(Items.BONE, 1, 0), 0.1F, ti, "stoneShele");

				RecipeAPI.registerCrushers
						.addRecipe(new ItemStack(MainInit.miscDust, 2, 1), new ItemStack(Items.FLINT), 0.5F, new ItemStack(MainInit.gems_white, 1, 0), 0.1F, ti, "stoneChart");
			}
		}

		Item lig = Item.REGISTRY.getObject(new ResourceLocation("undergroundbiomes", "lignite_coal"));
		if (lig != null && ModuleConfig.r_reactor) {
			OreDictionary.registerOre("gemLignite", new ItemStack(lig, 1, 0));
			// 褐炭改質
			RecipeAPI.registerReactorRecipes
					.addRecipe(new ItemStack(Items.COAL, 1, 0), new ItemStack(MachineInit.reagent, 1, 0), 1F, null, null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(MainInit.fuelOil, 400), null, new Object[] {
							new ItemStack(lig, 4, 0)
					});
		}

		Item cob1 = Item.REGISTRY.getObject(new ResourceLocation("undergroundbiomes", "igneous_cobble"));
		if (cob1 != null) {
			OreDictionary.registerOre("cobblestone", new ItemStack(cob1, 1, 0));
		}

		Item cob2 = Item.REGISTRY.getObject(new ResourceLocation("undergroundbiomes", "metamorphic_cobble"));
		if (cob2 != null) {
			OreDictionary.registerOre("cobblestone", new ItemStack(cob2, 1, 0));
		}

	}

}
