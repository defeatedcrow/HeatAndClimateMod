package defeatedcrow.hac.plugin;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FermenterFuel;
import forestry.api.fuels.FuelManager;
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

	private DCPluginForestry() {}

	public static void load() {

		if (ModuleConfig.food) {

			Item mulch = Item.REGISTRY.getObject(new ResourceLocation("forestry", "mulch"));
			Fluid seed = FluidRegistry.getFluid("seed.oil");
			Fluid juice = FluidRegistry.getFluid("juice");
			Fluid honey = FluidRegistry.getFluid("for.honey");
			Fluid bio = FluidRegistry.getFluid("biomass");

			if (mulch == null || seed == null || juice == null)
				return;

			// squeeze
			RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {
					new ItemStack(FoodInit.crops, 1, 3)
			}, new FluidStack(juice, 100), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.crops, 1, 4)
			}, new FluidStack(juice, 50), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.crops, 1, 5)
			}, new FluidStack(seed, 50), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {
					new ItemStack(FoodInit.crops, 1, 6)
			}, new FluidStack(juice, 300), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {
					new ItemStack(FoodInit.crops, 1, 7)
			}, new FluidStack(FoodInit.oil, 100), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 0)
			}, new FluidStack(seed, 50), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 1)
			}, new FluidStack(seed, 10), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 2)
			}, new FluidStack(seed, 10), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 3)
			}, new FluidStack(seed, 10), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 4)
			}, new FluidStack(seed, 20), new ItemStack(mulch), 10);

			RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[] {
					new ItemStack(FoodInit.seeds, 1, 5)
			}, new FluidStack(seed, 100), new ItemStack(mulch), 10);

			// fermentation
			if (honey != null && bio != null) {
				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.seeds, 1, 0), 100, 1.0F,
						new FluidStack(bio, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 1), 100, 1.0F,
						new FluidStack(bio, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 2), 50, 1.0F,
						new FluidStack(bio, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 8), 50, 1.0F,
						new FluidStack(bio, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 9), 50, 1.0F,
						new FluidStack(bio, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1, 0), 250, 1.0F,
						new FluidStack(bio, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1, 1), 250, 1.0F,
						new FluidStack(bio, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1, 2), 250, 1.0F,
						new FluidStack(bio, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.seeds, 1, 0), 100, 1.5F,
						new FluidStack(bio, 100), new FluidStack(honey, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 1), 100, 1.5F,
						new FluidStack(bio, 100), new FluidStack(honey, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 2), 50, 1.5F,
						new FluidStack(bio, 50), new FluidStack(honey, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 8), 50, 1.5F,
						new FluidStack(bio, 50), new FluidStack(honey, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 9), 50, 1.5F,
						new FluidStack(bio, 50), new FluidStack(honey, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1, 0), 250, 1.5F,
						new FluidStack(bio, 250), new FluidStack(honey, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1, 1), 250, 1.5F,
						new FluidStack(bio, 250), new FluidStack(honey, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1, 2), 250, 1.5F,
						new FluidStack(bio, 250), new FluidStack(honey, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.seeds, 1, 0), 100, 1.5F,
						new FluidStack(bio, 100), new FluidStack(juice, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 1), 100, 1.5F,
						new FluidStack(bio, 100), new FluidStack(juice, 100));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 2), 50, 1.5F,
						new FluidStack(bio, 50), new FluidStack(juice, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 8), 50, 1.5F,
						new FluidStack(bio, 50), new FluidStack(juice, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.crops, 1, 9), 50, 1.5F,
						new FluidStack(bio, 50), new FluidStack(juice, 50));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1, 0), 250, 1.5F,
						new FluidStack(bio, 250), new FluidStack(juice, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1, 1), 250, 1.5F,
						new FluidStack(bio, 250), new FluidStack(juice, 250));

				RecipeManagers.fermenterManager.addRecipe(new ItemStack(FoodInit.saplings, 1, 2), 250, 1.5F,
						new FluidStack(bio, 250), new FluidStack(juice, 250));

			}

			Item slice = Item.REGISTRY.getObject(new ResourceLocation("forestry", "honeyedSlice"));

			if (slice != null) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(slice, 4, 0), new Object[] {
						"XXX",
						"XYX",
						"XXX",
						'Y',
						new ItemStack(FoodInit.bread, 1, 1),
						'X',
						"dropHoney"
				}));

				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(slice, 4, 0), new Object[] {
						"XXX",
						"XYX",
						"XXX",
						'Y',
						new ItemStack(FoodInit.bread, 1, 3),
						'X',
						"dropHoney"
				}));
			}

			ItemStack oilcake = new ItemStack(MainInit.miscDust, 1, 4);
			FuelManager.fermenterFuel.put(oilcake, new FermenterFuel(oilcake, 48, 200));

			Fluid oil = FoodInit.oil;
			if (oil != null) {
				FuelManager.bronzeEngineFuel.put(oil, new EngineBronzeFuel(oil, 30, 2500, 1));
			}

			Fluid black = FoodInit.blackLiquor;
			if (black != null) {
				FuelManager.bronzeEngineFuel.put(black, new EngineBronzeFuel(black, 30, 2500, 1));
			}
		}

	}

}
