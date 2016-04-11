package defeatedcrow.hac.main.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.main.MainInit;

public class MachineRecipeRegister {

	public static void load() {
		loadOreRecipes();
	}

	static void loadOreRecipes() {
		// 金属精錬。ごく一部はKILNでも焼けるが、ほとんどがSMELTを要求する。
		// copper
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 0));
		// zinc
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 1), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 1));
		// nickel
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 2), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 2));
		// silver
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 3), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 3));
		// brass
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 4), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 4));
		// steel
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 5), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 4));
		// iron
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(Blocks.iron_block, 1, 0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 5));
		// nickelsilver
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 6), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 6));
		// magnet
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 7), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 7));

		// KILNはバニラカマドに対応する
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 0), new ItemStack(MainInit.metalBlock, 1, 0), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 1), new ItemStack(MainInit.metalBlock, 1, 1), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 3), new ItemStack(MainInit.metalBlock, 1, 3), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 4), new ItemStack(MainInit.metalBlock, 1, 4), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 5), new ItemStack(Blocks.iron_block, 1, 0), 0.3F);

		// dust個別精錬
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 0));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 1), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 1));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 2), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 2));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 3), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 3));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(Items.gold_ingot, 1, 0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 4));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(Items.iron_ingot, 1, 0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 5));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 5), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 6));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 7), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 7));

		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 0), new ItemStack(MainInit.oreIngot, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 1), new ItemStack(MainInit.oreIngot, 1, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 3), new ItemStack(MainInit.oreIngot, 1, 3), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 4), new ItemStack(Items.gold_ingot, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 5), new ItemStack(Items.iron_ingot, 1, 0), 0.1F);

		// gems
		GameRegistry.addSmelting(new ItemStack(Items.flint, 1, 0), new ItemStack(MainInit.gems, 1, 1), 0.2F);
	}
}
