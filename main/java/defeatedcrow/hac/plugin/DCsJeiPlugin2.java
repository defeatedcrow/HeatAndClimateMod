package defeatedcrow.hac.plugin;

import defeatedcrow.hac.food.gui.GuiFluidProcessor;
import defeatedcrow.hac.food.gui.GuiTeaPot;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.machine.gui.GuiCrusher;
import defeatedcrow.hac.machine.gui.GuiReactor;
import defeatedcrow.hac.machine.gui.GuiSpinning;
import defeatedcrow.hac.machine.gui.GuiStoneMill;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.IFluidFuel;
import defeatedcrow.hac.main.api.IHeatTreatment;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.jei.DCFuelCategory;
import defeatedcrow.hac.plugin.jei.DCFuelMaker;
import defeatedcrow.hac.plugin.jei.DCFuelWrapper;
import defeatedcrow.hac.plugin.jei.DCHeatTreatmentCategory;
import defeatedcrow.hac.plugin.jei.DCHeatTreatmentMaker;
import defeatedcrow.hac.plugin.jei.DCHeatTreatmentWrapper;
import defeatedcrow.hac.plugin.jei.DCPressMoldCategory;
import defeatedcrow.hac.plugin.jei.DCPressMoldMaker;
import defeatedcrow.hac.plugin.jei.DCPressMoldWrapper;
import defeatedcrow.hac.plugin.jei.MoldItem;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class DCsJeiPlugin2 implements IModPlugin {

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		final IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registry.addRecipeCategories(new DCFuelCategory(guiHelper), new DCHeatTreatmentCategory(
				guiHelper), new DCPressMoldCategory(guiHelper));
	}

	@Override
	public void register(IModRegistry registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();

		registry.handleRecipes(IFluidFuel.class, recipe -> new DCFuelWrapper(recipe), "dcs_climate.fuel");
		registry.handleRecipes(IHeatTreatment.class, recipe -> new DCHeatTreatmentWrapper(
				recipe), "dcs_climate.treatment");
		registry.handleRecipes(MoldItem.class, recipe -> new DCPressMoldWrapper(recipe), "dcs_climate.pressmold");

		DCFuelMaker.register(registry);
		DCHeatTreatmentMaker.register(registry);
		DCPressMoldMaker.register(registry);

		registry.addRecipeCatalyst(new ItemStack(MainInit.fuelStove), "dcs_climate.fuel");
		registry.addRecipeCatalyst(new ItemStack(MachineInit.burner), "dcs_climate.fuel");
		registry.addRecipeCatalyst(new ItemStack(MachineInit.dieselEngine), "dcs_climate.fuel");
		registry.addRecipeCatalyst(new ItemStack(MachineInit.scooter), "dcs_climate.fuel");
		registry.addRecipeCatalyst(new ItemStack(MainInit.metalBlockAlloy, 1, 5), "dcs_climate.treatment");
		registry.addRecipeCatalyst(new ItemStack(MachineInit.pressMachine, 1, 0), "dcs_climate.pressmold");

		if (ModuleConfig.machine) {
			registry.addRecipeClickArea(GuiStoneMill.class, 80, 32, 16, 16, new String[] {
					"dcs_climate.mill"
			});

			registry.addRecipeClickArea(GuiSpinning.class, 80, 32, 16, 16, new String[] {
					"dcs_climate.spinning"
			});

			registry.addRecipeClickArea(GuiReactor.class, 120, 18, 16, 16, new String[] {
					"dcs_climate.reactor"
			});

			registry.addRecipeClickArea(GuiCrusher.class, 80, 22, 12, 23, new String[] {
					"dcs_climate.crusher"
			});
		}

		if (ModuleConfig.food) {
			registry.addRecipeClickArea(GuiFluidProcessor.class, 80, 35, 16, 16, new String[] {
					"dcs_climate.fluidcraft"
			});

			registry.addRecipeClickArea(GuiTeaPot.class, 80, 35, 16, 16, new String[] {
					"dcs_climate.fluidcraft"
			});
		}
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {}

	@Override
	public void registerIngredients(IModIngredientRegistration registry) {}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {}

}
