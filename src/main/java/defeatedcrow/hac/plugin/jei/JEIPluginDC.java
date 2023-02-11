package defeatedcrow.hac.plugin.jei;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.food.material.block.ClimateCropBaseBlock;
import defeatedcrow.hac.food.material.block.LeavesCropBlockDC;
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
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIPluginDC implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(ClimateCore.MOD_ID, "core");
	}

	@Override
	public void registerIngredients(IModIngredientRegistration registration) {
		registration.register(IngredientTypeDC.HEAT_TIER, DCHeatTier.createList(), new JeiHeatTierHelper(), new HeatTierRenderer());
		registration.register(IngredientTypeDC.HUMIDITY, DCHumidity.createList(), new JeiHumidityHelper(), new HumidityRenderer());
		registration.register(IngredientTypeDC.AIRFLOW, DCAirflow.createList(), new JeiAirflowHelper(), new AirflowRenderer());
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(SMELTING_DATA, PluginRecipeListDC.SMELTING_LIST);
		registration.addRecipes(CROP_DATA, PluginRecipeListDC.CROP_LIST);
		registration.addRecipes(TREE_DATA, PluginRecipeListDC.TREE_LIST);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new ClimateSmeltingCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new CropDataCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new TreeDataCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	public static final ResourceLocation SMELTING_ID = new ResourceLocation(ClimateCore.MOD_ID, "smelting_data");
	public static final RecipeType<IClimateSmelting> SMELTING_DATA = new RecipeType<IClimateSmelting>(SMELTING_ID, IClimateSmelting.class);

	public static final ResourceLocation CROP_ID = new ResourceLocation(ClimateCore.MOD_ID, "crop_data");
	public static final RecipeType<ClimateCropBaseBlock> CROP_DATA = new RecipeType<ClimateCropBaseBlock>(CROP_ID, ClimateCropBaseBlock.class);

	public static final ResourceLocation TREE_ID = new ResourceLocation(ClimateCore.MOD_ID, "tree_data");
	public static final RecipeType<LeavesCropBlockDC> TREE_DATA = new RecipeType<LeavesCropBlockDC>(TREE_ID, LeavesCropBlockDC.class);

}