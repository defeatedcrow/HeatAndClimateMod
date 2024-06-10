package defeatedcrow.hac.plugin.jei;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.recipe.IDeviceFuel;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.api.recipe.IHeatTreatment;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.food.material.block.crops.ClimateCropBaseBlock;
import defeatedcrow.hac.food.material.block.crops.LeavesCropBlockDC;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.plugin.jei.ingredients.AirflowRenderer;
import defeatedcrow.hac.plugin.jei.ingredients.HeatTierRenderer;
import defeatedcrow.hac.plugin.jei.ingredients.HumidityRenderer;
import defeatedcrow.hac.plugin.jei.ingredients.IngredientTypeDC;
import defeatedcrow.hac.plugin.jei.ingredients.JeiAirflowHelper;
import defeatedcrow.hac.plugin.jei.ingredients.JeiHeatTierHelper;
import defeatedcrow.hac.plugin.jei.ingredients.JeiHumidityHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class JEIPluginDC implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(ClimateCore.MOD_ID, "core");
	}

	@Override
	public void registerIngredients(IModIngredientRegistration registration) {
		registration.register(IngredientTypeDC.HEAT_TIER, DCHeatTier.elements(), new JeiHeatTierHelper(), new HeatTierRenderer());
		registration.register(IngredientTypeDC.HUMIDITY, DCHumidity.elements(), new JeiHumidityHelper(), new HumidityRenderer());
		registration.register(IngredientTypeDC.AIRFLOW, DCAirflow.elements(), new JeiAirflowHelper(), new AirflowRenderer());
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		PluginRecipeListDC.init();
		registration.addRecipes(SMELTING_DATA, PluginRecipeListDC.SMELTING_LIST);
		registration.addRecipes(HEAT_TREATMENT_DATA, PluginRecipeListDC.HEAT_TREATMENT_LIST);
		registration.addRecipes(COOKING_DATA, PluginRecipeListDC.COOKING_LIST);
		registration.addRecipes(TEA_DATA, PluginRecipeListDC.TEA_LIST);
		registration.addRecipes(FERMENTATION_DATA, PluginRecipeListDC.FERMENTATION_LIST);
		registration.addRecipes(MILL_DATA, PluginRecipeListDC.PULVERISER_LIST);
		registration.addRecipes(CROP_DATA, PluginRecipeListDC.CROP_LIST);
		registration.addRecipes(TREE_DATA, PluginRecipeListDC.TREE_LIST);
		registration.addRecipes(BIOMASS_FUEL_DATA, PluginRecipeListDC.BIOMASS_FUEL_LIST);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new ClimateSmeltingCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new HeatTreatmentCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new CropDataCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new TreeDataCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new DeviceCookingCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new DeviceTeaCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new DeviceFermentationCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new FuelBiomassCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new DeviceMillCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(MachineInit.COOKING_POT_NORMAL.get()), COOKING_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.COOKING_POT_WHITE.get()), COOKING_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.COOKING_POT_BLUE.get()), COOKING_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.COOKING_POT_BLACK.get()), COOKING_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.COOKING_POT_RED.get()), COOKING_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.COOKING_POT_GREEN.get()), COOKING_DATA);

		registration.addRecipeCatalyst(new ItemStack(MachineInit.TEA_POT_NORMAL.get()), TEA_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.TEA_POT_WHITE.get()), TEA_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.TEA_POT_BLUE.get()), TEA_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.TEA_POT_BLACK.get()), TEA_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.TEA_POT_RED.get()), TEA_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.TEA_POT_GREEN.get()), TEA_DATA);

		registration.addRecipeCatalyst(new ItemStack(MachineInit.FERMANTATION_JAR_NORMAL.get()), FERMENTATION_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.FERMANTATION_JAR_WHITE.get()), FERMENTATION_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.FERMANTATION_JAR_BLUE.get()), FERMENTATION_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.FERMANTATION_JAR_BLACK.get()), FERMENTATION_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.FERMANTATION_JAR_RED.get()), FERMENTATION_DATA);
		registration.addRecipeCatalyst(new ItemStack(MachineInit.FERMANTATION_JAR_GREEN.get()), FERMENTATION_DATA);

		registration.addRecipeCatalyst(new ItemStack(MachineInit.STONE_MILL.get()), MILL_DATA);

		registration.addRecipeCatalyst(new ItemStack(MachineInit.BOILER_BIOMASS.get()), BIOMASS_FUEL_DATA);
	}

	public static final ResourceLocation SMELTING_ID = new ResourceLocation(ClimateCore.MOD_ID, "smelting_data");
	public static final RecipeType<IClimateSmelting> SMELTING_DATA = new RecipeType<IClimateSmelting>(SMELTING_ID, IClimateSmelting.class);

	public static final ResourceLocation HEAT_TREATMENT_ID = new ResourceLocation(ClimateCore.MOD_ID, "heat_treatment_data");
	public static final RecipeType<IHeatTreatment> HEAT_TREATMENT_DATA = new RecipeType<IHeatTreatment>(HEAT_TREATMENT_ID, IHeatTreatment.class);

	public static final ResourceLocation CROP_ID = new ResourceLocation(ClimateCore.MOD_ID, "crop_data");
	public static final RecipeType<ClimateCropBaseBlock> CROP_DATA = new RecipeType<ClimateCropBaseBlock>(CROP_ID, ClimateCropBaseBlock.class);

	public static final ResourceLocation TREE_ID = new ResourceLocation(ClimateCore.MOD_ID, "tree_data");
	public static final RecipeType<LeavesCropBlockDC> TREE_DATA = new RecipeType<LeavesCropBlockDC>(TREE_ID, LeavesCropBlockDC.class);

	public static final ResourceLocation COOKING_ID = new ResourceLocation(ClimateCore.MOD_ID, "cooking_data");
	public static final RecipeType<IDeviceRecipe> COOKING_DATA = new RecipeType<IDeviceRecipe>(COOKING_ID, IDeviceRecipe.class);

	public static final ResourceLocation TEA_ID = new ResourceLocation(ClimateCore.MOD_ID, "tea_data");
	public static final RecipeType<IDeviceRecipe> TEA_DATA = new RecipeType<IDeviceRecipe>(TEA_ID, IDeviceRecipe.class);

	public static final ResourceLocation FERMENTATION_ID = new ResourceLocation(ClimateCore.MOD_ID, "fermentation_data");
	public static final RecipeType<IDeviceRecipe> FERMENTATION_DATA = new RecipeType<IDeviceRecipe>(FERMENTATION_ID, IDeviceRecipe.class);

	public static final ResourceLocation MILL_ID = new ResourceLocation(ClimateCore.MOD_ID, "mill_data");
	public static final RecipeType<IDeviceRecipe> MILL_DATA = new RecipeType<IDeviceRecipe>(MILL_ID, IDeviceRecipe.class);

	public static final ResourceLocation BIOMASS_FUEL_ID = new ResourceLocation(ClimateCore.MOD_ID, "fuel_biomass_data");
	public static final RecipeType<IDeviceFuel> BIOMASS_FUEL_DATA = new RecipeType<IDeviceFuel>(BIOMASS_FUEL_ID, IDeviceFuel.class);

}
