package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.recipes.device.RegisterCrusherRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class DCPluginRailcraft {

	public static final DCPluginRailcraft INSTANCE = new DCPluginRailcraft();

	private DCPluginRailcraft() {}

	public static void load() {

		if (ModuleConfig.r_mill) {
			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(MainInit.oreDust,
					1, 5), 0.5F, "orePoorIron");

			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(MainInit.oreDust,
					1, 4), 0.5F, "orePoorGold");

			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(MainInit.oreDust,
					1, 0), 0.5F, "orePoorCopper");

			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(MainInit.oreDust,
					1, 8), 0.5F, "orePoorTin");

			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(MainInit.oreDust,
					1, 13), 0.5F, "orePoorLead");

			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(MainInit.oreDust,
					1, 2), 0.5F, "orePoorNickel");

			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(MainInit.oreDust,
					1, 3), 0.5F, "orePoorSilver");

			RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(MainInit.oreDust,
					1, 1), 0.5F, "orePoorZinc");
		}

		if (ModuleConfig.r_crusher) {
			ItemStack ti = RegisterCrusherRecipe.Ti_Blade;

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 5), ti, "orePoorIron");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 4), ti, "orePoorGold");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 0), ti, "orePoorCopper");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 8), ti, "orePoorTin");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 13), ti, "orePoorLead");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 2), ti, "orePoorNickel");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 3), ti, "orePoorSilver");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 1), ti, "orePoorZinc");
		}

	}
}
