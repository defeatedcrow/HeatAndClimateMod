package defeatedcrow.hac.plugin;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.gui.GuiAgingBarrel;
import defeatedcrow.hac.food.gui.GuiBrewingTank;
import defeatedcrow.hac.food.gui.GuiFluidProcessor;
import defeatedcrow.hac.food.gui.GuiSteelPot;
import defeatedcrow.hac.food.gui.GuiStillPot;
import defeatedcrow.hac.food.gui.GuiTeaPot;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.machine.gui.GuiCrusher;
import defeatedcrow.hac.machine.gui.GuiReactor;
import defeatedcrow.hac.machine.gui.GuiReactorIBC;
import defeatedcrow.hac.machine.gui.GuiSpinning;
import defeatedcrow.hac.machine.gui.GuiStoneMill;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.IDCInfoData;
import defeatedcrow.hac.main.api.IFluidFuel;
import defeatedcrow.hac.main.api.IHeatTreatment;
import defeatedcrow.hac.main.api.brewing.IAgingRecipeDC;
import defeatedcrow.hac.main.api.brewing.IBrewingRecipeDC;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import defeatedcrow.hac.main.api.brewing.IStillRecipeDC;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.jei.ClimateFluidCategory;
import defeatedcrow.hac.plugin.jei.ClimateFluidMaker;
import defeatedcrow.hac.plugin.jei.ClimateFluidWrapper;
import defeatedcrow.hac.plugin.jei.DCAgingCategory;
import defeatedcrow.hac.plugin.jei.DCAgingMaker;
import defeatedcrow.hac.plugin.jei.DCAgingWrapper;
import defeatedcrow.hac.plugin.jei.DCBrewingCategory;
import defeatedcrow.hac.plugin.jei.DCBrewingMaker;
import defeatedcrow.hac.plugin.jei.DCBrewingWrapper;
import defeatedcrow.hac.plugin.jei.DCFluidInfo;
import defeatedcrow.hac.plugin.jei.DCFuelCategory;
import defeatedcrow.hac.plugin.jei.DCFuelMaker;
import defeatedcrow.hac.plugin.jei.DCFuelWrapper;
import defeatedcrow.hac.plugin.jei.DCHeatTreatmentCategory;
import defeatedcrow.hac.plugin.jei.DCHeatTreatmentMaker;
import defeatedcrow.hac.plugin.jei.DCHeatTreatmentWrapper;
import defeatedcrow.hac.plugin.jei.DCInfoCategory;
import defeatedcrow.hac.plugin.jei.DCInfoDataMaker;
import defeatedcrow.hac.plugin.jei.DCInfoWrapper;
import defeatedcrow.hac.plugin.jei.DCPressMoldCategory;
import defeatedcrow.hac.plugin.jei.DCPressMoldMaker;
import defeatedcrow.hac.plugin.jei.DCPressMoldWrapper;
import defeatedcrow.hac.plugin.jei.DCStillCategory;
import defeatedcrow.hac.plugin.jei.DCStillMaker;
import defeatedcrow.hac.plugin.jei.DCStillWrapper;
import defeatedcrow.hac.plugin.jei.MicrobeCategory;
import defeatedcrow.hac.plugin.jei.MicrobeMaker;
import defeatedcrow.hac.plugin.jei.MicrobeWrapper;
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
		registry.addRecipeCategories(new DCInfoCategory(guiHelper), new ClimateFluidCategory(
				guiHelper), new DCFuelCategory(guiHelper), new DCHeatTreatmentCategory(
						guiHelper), new DCPressMoldCategory(guiHelper), new MicrobeCategory(
								guiHelper), new DCBrewingCategory(guiHelper), new DCStillCategory(
										guiHelper), new DCAgingCategory(guiHelper));
	}

	@Override
	public void register(IModRegistry registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();

		registry.handleRecipes(DCFluidInfo.class, recipe -> new ClimateFluidWrapper(recipe), "dcs_climate.drink");
		registry.handleRecipes(IFluidFuel.class, recipe -> new DCFuelWrapper(recipe), "dcs_climate.fuel");
		registry.handleRecipes(IHeatTreatment.class, recipe -> new DCHeatTreatmentWrapper(
				recipe), "dcs_climate.treatment");
		registry.handleRecipes(MoldItem.class, recipe -> new DCPressMoldWrapper(recipe), "dcs_climate.pressmold");
		registry.handleRecipes(IDCInfoData.class, recipe -> new DCInfoWrapper(recipe), "dcs_climate.info");
		registry.handleRecipes(IMicrobe.class, recipe -> new MicrobeWrapper(recipe), "dcs_climate.microbe");
		registry.handleRecipes(IBrewingRecipeDC.class, recipe -> new DCBrewingWrapper(recipe), "dcs_climate.brewing");
		registry.handleRecipes(IStillRecipeDC.class, recipe -> new DCStillWrapper(recipe), "dcs_climate.still");
		registry.handleRecipes(IAgingRecipeDC.class, recipe -> new DCAgingWrapper(recipe), "dcs_climate.aging");

		DCFuelMaker.register(registry);
		DCHeatTreatmentMaker.register(registry);
		DCPressMoldMaker.register(registry);
		DCInfoDataMaker.register(registry);
		ClimateFluidMaker.register(registry);
		MicrobeMaker.register(registry);
		DCBrewingMaker.register(registry);
		DCStillMaker.register(registry);
		DCAgingMaker.register(registry);

		registry.addRecipeCatalyst(new ItemStack(MainInit.fuelStove), "dcs_climate.fuel");
		if (ModuleConfig.machine) {
			registry.addRecipeCatalyst(new ItemStack(MachineInit.burner), "dcs_climate.fuel");
			if (ModuleConfig.machine_advanced) {
				registry.addRecipeCatalyst(new ItemStack(MachineInit.dieselEngine), "dcs_climate.fuel");
				registry.addRecipeCatalyst(new ItemStack(MachineInit.scooter), "dcs_climate.fuel");
				registry.addRecipeCatalyst(new ItemStack(MachineInit.pressMachine, 1, 0), "dcs_climate.pressmold");
			}
		}
		registry.addRecipeCatalyst(new ItemStack(MainInit.metalBlockAlloy, 1, 5), "dcs_climate.treatment");
		registry.addRecipeCatalyst(new ItemStack(MainInit.iconItem, 1, 0), "dcs_climate.info");
		if (ModuleConfig.food) {
			registry.addRecipeCatalyst(new ItemStack(FoodInit.paperPack, 1, 1), "dcs_climate.drink");
			if (ModuleConfig.food_advanced) {
				registry.addRecipeCatalyst(new ItemStack(FoodInit.unidentified, 1, 1), "dcs_climate.microbe");
				registry.addRecipeCatalyst(new ItemStack(FoodInit.brewingTankWood, 1, 0), "dcs_climate.brewing");
				registry.addRecipeCatalyst(new ItemStack(FoodInit.brewingTank, 1, 0), "dcs_climate.brewing");
				registry.addRecipeCatalyst(new ItemStack(FoodInit.distillator, 1, 0), "dcs_climate.still");
				registry.addRecipeCatalyst(new ItemStack(FoodInit.barrel, 1, 0), "dcs_climate.aging");
			}
		}

		if (ModuleConfig.machine) {
			registry.addRecipeClickArea(GuiStoneMill.class, 80, 32, 16, 16, new String[] { "dcs_climate.mill" });

			registry.addRecipeClickArea(GuiSpinning.class, 80, 32, 16, 16, new String[] { "dcs_climate.spinning" });

			registry.addRecipeClickArea(GuiReactor.class, 120, 18, 16, 16, new String[] { "dcs_climate.reactor" });

			registry.addRecipeClickArea(GuiReactorIBC.class, 93, 38, 16, 16, new String[] { "dcs_climate.reactor" });

			registry.addRecipeClickArea(GuiCrusher.class, 80, 22, 12, 23, new String[] { "dcs_climate.crusher" });
		}

		if (ModuleConfig.food) {
			registry.addRecipeClickArea(GuiFluidProcessor.class, 80, 35, 16, 16, new String[] {
				"dcs_climate.fluidcraft" });

			registry.addRecipeClickArea(GuiSteelPot.class, 90, 35, 16, 16, new String[] { "dcs_climate.fluidcraft" });

			registry.addRecipeClickArea(GuiTeaPot.class, 80, 35, 16, 16, new String[] { "dcs_climate.fluidcraft" });

			if (ModuleConfig.food_advanced) {
				registry.addRecipeClickArea(GuiBrewingTank.class, 80, 30, 16, 16, new String[] {
					"dcs_climate.brewing" });

				registry.addRecipeClickArea(GuiStillPot.class, 80, 30, 16, 16, new String[] { "dcs_climate.still" });

				registry.addRecipeClickArea(GuiAgingBarrel.class, 110, 35, 16, 16, new String[] {
					"dcs_climate.aging" });
			}
		}
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {}

	@Override
	public void registerIngredients(IModIngredientRegistration registry) {}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {}

}
