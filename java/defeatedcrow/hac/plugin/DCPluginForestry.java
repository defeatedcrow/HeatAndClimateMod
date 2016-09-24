package defeatedcrow.hac.plugin;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class DCPluginForestry {

	public static final DCPluginForestry INSTANCE = new DCPluginForestry();

	private DCPluginForestry() {
	}

	public static void load() {

		if (ModuleConfig.food) {

			Item mulch = Item.REGISTRY.getObject(new ResourceLocation("forestry", "mulch"));
			Fluid seed = FluidRegistry.getFluid("seed.oil");
			Fluid juice = FluidRegistry.getFluid("juice");

			if (mulch == null || seed == null || juice == null) {
				return;
			}

			// recipes
			RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {
					new ItemStack(FoodInit.crops, 1, 3) }, new FluidStack(juice, 100), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.crops, 1, 4) }, new FluidStack(juice, 50), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.crops, 1, 5) }, new FluidStack(seed, 50), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {
					new ItemStack(FoodInit.crops, 1, 6) }, new FluidStack(juice, 300), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {
					new ItemStack(FoodInit.crops, 1, 7) }, new FluidStack(FoodInit.oil, 100), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 0) }, new FluidStack(seed, 50), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 1) }, new FluidStack(seed, 10), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 2) }, new FluidStack(seed, 10), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 3) }, new FluidStack(seed, 10), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 4) }, new FluidStack(seed, 20), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 5) }, new FluidStack(seed, 100), new ItemStack(mulch), 10);

			Item slice = Item.REGISTRY.getObject(new ResourceLocation("forestry", "honeyedSlice"));

			if (slice != null) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(slice, 4, 0), new Object[] {
						"XXX",
						"XYX",
						"XXX",
						'X',
						new ItemStack(FoodInit.bread, 1, 1),
						'Y',
						"dropHoney" }));

				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(slice, 4, 0), new Object[] {
						"XXX",
						"XYX",
						"XXX",
						'X',
						new ItemStack(FoodInit.bread, 1, 3),
						'Y',
						"dropHoney" }));
			}
		}

	}

}
