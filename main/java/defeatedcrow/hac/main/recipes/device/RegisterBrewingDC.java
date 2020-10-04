package defeatedcrow.hac.main.recipes.device;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.recipes.FoodBrewingRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class RegisterBrewingDC {

	public static void load() {
		if (ModuleConfig.food && ModuleConfig.food_advanced) {
			loadFermentationRecipe();
			loadBrewingRecipe();
		}
	}

	public static void loadFermentationRecipe() {
		// 麦芽
		reg(new ItemStack(MainInit.foodDust, 2, 3), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"cropWheat" });

		reg(new ItemStack(MainInit.foodDust, 8, 3), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"containerSeed" });

		reg(new ItemStack(MainInit.foodDust, 18, 3), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			new ItemStack(Blocks.HAY_BLOCK, 1, 0) });

		// バイオ

		// bacillus

		reg(new ItemStack(FoodInit.residue, 24, 5), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerMeatRaw",
			new ItemStack(FoodInit.bacillus, 1, 0) });

		reg(new ItemStack(FoodInit.residue, 16, 5), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerFish",
			new ItemStack(FoodInit.bacillus, 1, 0) });

		reg(new ItemStack(FoodInit.residue, 8, 5), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSoybean",
			new ItemStack(FoodInit.bacillus, 1, 0) });

		reg(new ItemStack(FoodInit.residue, 16, 5), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerRottenFlesh",
			new ItemStack(FoodInit.bacillus, 1, 0) });

		reg(new ItemStack(FoodInit.antibiotic, 1, 1), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"dustPeptone",
			"dustYeast",
			new ItemStack(FoodInit.bacillus, 1, 1) });

		reg(new ItemStack(FoodInit.salad, 8, 7), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSoybean",
			"cropRice",
			new ItemStack(FoodInit.bacillus, 1, 2) });

		// coliformes

		reg(new ItemStack(Items.ROTTEN_FLESH, 2, 0), null, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
			"listAllmeatraw",
			new ItemStack(FoodInit.coliformes, 1, 0) });

		reg(new ItemStack(Items.ROTTEN_FLESH, 16, 0), null, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
			"containerMeatRaw",
			new ItemStack(FoodInit.coliformes, 1, 0) });

		reg(new ItemStack(Blocks.SPONGE, 1, 0), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"dustPeptone",
			"dustYeast",
			new ItemStack(FoodInit.coliformes, 1, 2) });

		// yeast

		reg(new ItemStack(FoodInit.residue, 1, 4), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"dustDraff",
			new ItemStack(FoodInit.beerYeast, 1, 0) });

		reg(new ItemStack(FoodInit.residue, 8, 4), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"containerDraff",
			new ItemStack(FoodInit.beerYeast, 1, 0) });

		reg(new ItemStack(FoodInit.residue, 8, 4), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"containerSugar",
			new ItemStack(FoodInit.beerYeast, 1, 0) });

		reg(null, new FluidStack(MainInit.fuelOil, 250), new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSugar",
			"dustPeptone",
			new ItemStack(FoodInit.beerYeast, 1, 2) });

		reg(null, new FluidStack(MainInit.fuelOil, 250), new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerStarch",
			"dustPeptone",
			new ItemStack(FoodInit.beerYeast, 1, 2) });

		// oryzae

		reg(new ItemStack(FoodInit.meat, 8, 6), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSoybean",
			"dustSalt",
			new ItemStack(FoodInit.oryzae, 1, 0) });

		reg(new ItemStack(FoodInit.meat, 8, 7), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSoybean",
			"foodMalt",
			new ItemStack(FoodInit.oryzae, 1, 0) });

		reg(new ItemStack(FoodInit.meat, 8, 7), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSoycake",
			"foodMalt",
			new ItemStack(FoodInit.oryzae, 1, 0) });

		// nether

		reg(new ItemStack(Blocks.NETHERRACK, 1, 0), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"cobblestone",
			new ItemStack(FoodInit.nether, 1, 0) });

		reg(new ItemStack(Items.GLOWSTONE_DUST, 1, 0), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"gemChalcedony",
			new ItemStack(FoodInit.nether, 1, 1) });

		reg(new ItemStack(Items.GLOWSTONE_DUST, 1, 0), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"gemQuartz",
			new ItemStack(FoodInit.nether, 1, 1) });

		// blue

		reg(new ItemStack(FoodInit.antibiotic, 1, 0), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"dustPeptone",
			"dustYeast",
			new ItemStack(FoodInit.blueMold, 1, 0) });

		reg(new ItemStack(FoodInit.meat, 3, 1), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"dustPeptone",
			"dustYeast",
			new ItemStack(FoodInit.blueMold, 1, 1) });

		// slime

		reg(new ItemStack(Items.SLIME_BALL, 1, 0), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"dustPeptone",
			"dustYeast",
			new ItemStack(FoodInit.slimeMold, 1, 0) });

		reg(new ItemStack(Items.GUNPOWDER, 1, 0), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"dustPeptone",
			"dustYeast",
			new ItemStack(FoodInit.slimeMold, 1, 1) });

		// mushroom

		reg(new ItemStack(Blocks.MYCELIUM, 1, 0), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"dirt",
			new ItemStack(FoodInit.mushroom, 1, 0) });

		reg(new ItemStack(Blocks.MYCELIUM, 1, 0), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"dirt",
			new ItemStack(FoodInit.mushroom, 1, 1) });

	}

	public static void loadBrewingRecipe() {

		// yeast

		reg(new ItemStack(FoodInit.residue, 1, 0), new FluidStack(FoodInit.beer, 1000), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] { "containerMalt", new ItemStack(FoodInit.beerYeast, 1, 0) });

		reg(new ItemStack(FoodInit.residue, 1, 2), new FluidStack(FoodInit.wine, 1000), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] { "containerGrape", new ItemStack(FoodInit.beerYeast, 1, 1) });

		reg(new ItemStack(FoodInit.residue, 1, 0), new FluidStack(FoodInit.beer, 200), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] { "containerMalt" });

		reg(new ItemStack(FoodInit.residue, 1, 2), new FluidStack(FoodInit.wine, 200), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] { "containerGrape" });
	}

	public static void reg(ItemStack out, FluidStack outF, FluidStack inF, Object... input) {
		FoodBrewingRecipe recipe = new FoodBrewingRecipe(out, outF, inF, input);
		MainAPIManager.brewingRegister.addBrewingRecipe(recipe);
	}
}
