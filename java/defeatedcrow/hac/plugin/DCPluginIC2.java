package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.Recipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class DCPluginIC2 {

	public static final DCPluginIC2 INSTANCE = new DCPluginIC2();

	private DCPluginIC2() {
	}

	public static void load() {

		// energy
		Recipes.semiFluidGenerator.addFluid("dcs.seed_oil", 1, 1.2D);

		// recipe registering

		NBTTagCompound tag1 = new NBTTagCompound();
		tag1.setInteger("macerator", 2000);
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreZinc"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.oreDust, 2, 1) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreNickel"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.oreDust, 2, 2) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreMagnetite"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.oreDust, 2, 7) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreGypsum"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.gems, 2, 3) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreChalcedonyB"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.gems, 2, 0) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreChalcedonyW"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.gems, 2, 2) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreSapphire"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.gems, 2, 4) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreSchorl"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.gems, 2, 11) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreSalt"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.foodMaterials, 2, 0) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreNiter"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.miscDust, 1, 6) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreSulfur"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.miscDust, 1, 7) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreLime"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.miscDust, 1, 2) });

		Recipes.macerator.addRecipe(new RecipeInputOreDict("gemNiter"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.miscDust, 1, 6) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("gemSulfur"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.miscDust, 1, 7) });
		Recipes.macerator.addRecipe(new RecipeInputOreDict("gemLime"), tag1, false, new ItemStack[] {
				new ItemStack(MainInit.miscDust, 1, 2) });

		NBTTagCompound tag2 = new NBTTagCompound();
		tag2.setInteger("compressor", 2000);
		Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(MainInit.miscDust, 1, 1), 1), tag2, false,
				new ItemStack[] {
						new ItemStack(MainInit.gems, 1, 2) });
		Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(MainInit.miscDust, 1, 2), 2), tag2, false,
				new ItemStack[] {
						new ItemStack(MainInit.ores_2, 1, 0) });
		Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(MainInit.miscDust, 1, 6), 1), tag2, false,
				new ItemStack[] {
						new ItemStack(MainInit.gems, 1, 9) });
		Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(MainInit.miscDust, 1, 7), 1), tag2, false,
				new ItemStack[] {
						new ItemStack(MainInit.gems, 1, 10) });

		if (ModuleConfig.food) {
			NBTTagCompound tag3 = new NBTTagCompound();
			tag3.setInteger("extractor", 2000);
			Recipes.extractor.addRecipe(new RecipeInputOreDict("cropOlive", 4), null, false, new ItemStack[] {
					new ItemStack(FoodInit.dropOil, 1, 0) });

			Item coffee = Item.REGISTRY.getObject(new ResourceLocation("IC2", "crop_res"));
			if (coffee != null) {
				Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(FoodInit.teaLeaves, 1, 0)), tag1,
						false, new ItemStack(coffee, 2, 1));

				RecipeAPI.registerMills.addRecipe(new ItemStack(coffee, 2, 1), new ItemStack(FoodInit.teaLeaves, 1, 0));

				RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.coffee, 1000),
						DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
								new ItemStack(coffee, 1, 1) });
			}
		}

	}

}
