package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.damage.DamageAPI;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.base.ClimateCropBase;
import defeatedcrow.hac.core.base.ClimateDoubleCropBase;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.forestry.DCFarmable;
import defeatedcrow.hac.plugin.forestry.DCFarmableDouble;
import forestry.api.farming.Farmables;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FermenterFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.recipes.RecipeManagers;
import forestry.lepidopterology.entities.EntityButterfly;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class DCPluginForestry {

	public static final DCPluginForestry INSTANCE = new DCPluginForestry();

	private DCPluginForestry() {}

	public static void load() {

		if (ModuleConfig.food) {

			Item mulch = Item.REGISTRY.getObject(new ResourceLocation("forestry", "mulch"));
			Item fer = Item.REGISTRY.getObject(new ResourceLocation("forestry", "fertilizerCompound"));
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

			if (fer != null) {
				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(fer, 1, 0), new Object[] {
						"dustPresscake",
						"dustAsh",
						new ItemStack(Items.DYE, 1, 15)
				}));
			}

			ItemStack oilcake = new ItemStack(MainInit.miscDust, 1, 4);
			FuelManager.fermenterFuel.put(oilcake, new FermenterFuel(oilcake, 48, 200));
			Farmables.registerFertilizer(oilcake, Integer.valueOf(200));

			Fluid oil = FoodInit.oil;
			if (oil != null) {
				FuelManager.bronzeEngineFuel.put(oil, new EngineBronzeFuel(oil, 30, 2500, 1));
			}

			Fluid black = FoodInit.blackLiquor;
			if (black != null) {
				FuelManager.bronzeEngineFuel.put(black, new EngineBronzeFuel(black, 30, 2500, 1));
			}

			Fluid liq = MachineInit.fuelGas;
			if (liq != null) {
				FuelManager.bronzeEngineFuel.put(liq, new EngineBronzeFuel(liq, 40, 4000, 1));
			}

			Fluid gas = MachineInit.fuelGas;
			if (gas != null) {
				FuelManager.bronzeEngineFuel.put(gas, new EngineBronzeFuel(gas, 60, 2500, 1));
			}

			Farmables.farmables.put("farmVegetables", new DCFarmable((ClimateCropBase) FoodInit.cropRice));
			Farmables.farmables.put("farmVegetables", new DCFarmable((ClimateCropBase) FoodInit.cropOnion));
			Farmables.farmables.put("farmVegetables", new DCFarmable((ClimateCropBase) FoodInit.cropSpinach));
			Farmables.farmables.put("farmVegetables",
					new DCFarmableDouble((ClimateDoubleCropBase) FoodInit.cropTomato));
			Farmables.farmables.put("farmVegetables",
					new DCFarmableDouble((ClimateDoubleCropBase) FoodInit.cropCoffee));
			Farmables.farmables.put("farmVegetables",
					new DCFarmableDouble((ClimateDoubleCropBase) FoodInit.cropCotton));
		}

		DamageAPI.resistantData.registerEntityResistant(EntityButterfly.class, 2.0F, 2.0F);

		// HaC側のレシピ

		if (ModuleConfig.machine && ModuleConfig.r_mill) {
			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 2, 0),
					new ItemStack(MainInit.miscDust, 1, 4), 0.25F, "cropChestnut");

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 2, 0),
					new ItemStack(MainInit.miscDust, 1, 4), 0.25F, "cropWalnut");

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0),
					new ItemStack(MainInit.miscDust, 1, 4), 0.25F, "cropHazelnut");

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0),
					new ItemStack(MainInit.miscDust, 1, 4), 0.25F, "cropAlmond");
		}

	}

}
