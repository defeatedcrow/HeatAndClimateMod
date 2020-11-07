package defeatedcrow.hac.main.recipes.device;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.recipes.FoodAgingRecipe;
import defeatedcrow.hac.main.recipes.FoodBrewingRecipe;
import defeatedcrow.hac.main.recipes.FoodStillRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class RegisterBrewingDC {

	public static void load() {
		if (ModuleConfig.food && ModuleConfig.food_advanced) {
			loadFermentationRecipe();
			loadStillRecipe();
			if (ModuleConfig.liquor)
				loadLiquorRecipe();
		}
	}

	public static void loadFermentationRecipe() {
		// 麦芽
		brewng(new ItemStack(MainInit.foodDust, 2, 3), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"cropWheat" });

		brewng(new ItemStack(MainInit.foodDust, 8, 3), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"containerSeed" });

		brewng(new ItemStack(MainInit.foodDust, 18, 3), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			new ItemStack(Blocks.HAY_BLOCK, 1, 0) });

		// バイオ

		// bacillus

		brewng(new ItemStack(FoodInit.residue, 24, 5), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerMeatRaw",
			new ItemStack(FoodInit.bacillus, 1, 0) });

		brewng(new ItemStack(FoodInit.residue, 16, 5), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerFish",
			new ItemStack(FoodInit.bacillus, 1, 0) });

		brewng(new ItemStack(FoodInit.residue, 8, 5), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSoybean",
			new ItemStack(FoodInit.bacillus, 1, 0) });

		brewng(new ItemStack(FoodInit.residue, 16, 5), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerRottenFlesh",
			new ItemStack(FoodInit.bacillus, 1, 0) });

		brewng(new ItemStack(FoodInit.antibiotic, 1, 1), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.bacillus, 1, 1) });

		brewng(new ItemStack(FoodInit.salad, 8, 7), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSoybean",
			"cropRice",
			new ItemStack(FoodInit.bacillus, 1, 2) });

		// coliformes

		brewng(new ItemStack(Items.ROTTEN_FLESH, 2, 0), null, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
			"listAllmeatraw",
			new ItemStack(FoodInit.coliformes, 1, 0) });

		brewng(new ItemStack(Items.ROTTEN_FLESH, 16, 0), null, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
			"containerMeatRaw",
			new ItemStack(FoodInit.coliformes, 1, 0) });

		brewng(new ItemStack(FoodInit.antibiotic, 1, 4), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.coliformes, 1, 1) });

		brewng(new ItemStack(Blocks.SPONGE, 1, 0), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.coliformes, 1, 2) });

		// lab

		brewng(new ItemStack(FoodInit.yogurt, 3, 0), null, new FluidStack(MainInit.milk, 1000), new Object[] {
			new ItemStack(FoodInit.lab, 1, 0) });

		brewng(new ItemStack(FoodInit.yogurt, 3, 1), null, new FluidStack(MainInit.milk, 1000), new Object[] {
			new ItemStack(FoodInit.lab, 1, 2) });

		// yeast

		brewng(new ItemStack(FoodInit.residue, 1, 4), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"dustDraff",
			new ItemStack(FoodInit.beerYeast, 1, 0) });

		brewng(new ItemStack(FoodInit.residue, 8, 4), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"containerDraff",
			new ItemStack(FoodInit.beerYeast, 1, 0) });

		brewng(new ItemStack(FoodInit.residue, 8, 4), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"containerSugar",
			new ItemStack(FoodInit.beerYeast, 1, 0) });

		brewng(null, new FluidStack(MainInit.fuelOil, 250), new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSugar",
			"dustPeptone",
			new ItemStack(FoodInit.beerYeast, 1, 2) });

		brewng(null, new FluidStack(MainInit.fuelOil, 250), new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerStarch",
			"dustPeptone",
			new ItemStack(FoodInit.beerYeast, 1, 2) });

		// oryzae

		brewng(new ItemStack(FoodInit.meat, 8, 6), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSoybean",
			"dustSalt",
			new ItemStack(FoodInit.oryzae, 1, 0) });

		brewng(new ItemStack(FoodInit.meat, 8, 7), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSoybean",
			"foodMalt",
			new ItemStack(FoodInit.oryzae, 1, 0) });

		brewng(new ItemStack(FoodInit.meat, 8, 7), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerSoycake",
			"foodMalt",
			new ItemStack(FoodInit.oryzae, 1, 0) });

		brewng(new ItemStack(FoodInit.inoculum, 8, 0), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerRiceFood",
			new ItemStack(FoodInit.oryzae, 1, 1) });

		brewng(new ItemStack(FoodInit.inoculum, 8, 1), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"containerRiceFood",
			new ItemStack(FoodInit.oryzae, 1, 2) });

		// nether

		brewng(new ItemStack(Blocks.NETHERRACK, 1, 0), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"cobblestone",
			new ItemStack(FoodInit.nether, 1, 0) });

		brewng(new ItemStack(Items.GLOWSTONE_DUST, 1, 0), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"gemChalcedony",
			new ItemStack(FoodInit.nether, 1, 1) });

		brewng(new ItemStack(Items.GLOWSTONE_DUST, 1, 0), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"gemQuartz",
			new ItemStack(FoodInit.nether, 1, 1) });

		brewng(new ItemStack(FoodInit.antibiotic, 1, 5), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.nether, 1, 2) });

		// blue

		brewng(new ItemStack(FoodInit.antibiotic, 1, 0), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.blueMold, 1, 0) });

		brewng(new ItemStack(FoodInit.meat, 3, 1), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.blueMold, 1, 1) });

		brewng(new ItemStack(FoodInit.antibiotic, 1, 2), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.blueMold, 1, 2) });

		// slime

		brewng(new ItemStack(Items.SLIME_BALL, 1, 0), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.slimeMold, 1, 0) });

		brewng(new ItemStack(Items.GUNPOWDER, 1, 0), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.slimeMold, 1, 1) });

		brewng(new ItemStack(FoodInit.antibiotic, 1, 7), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.slimeMold, 1, 2) });

		// mushroom

		brewng(new ItemStack(Blocks.MYCELIUM, 1, 0), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"dirt",
			new ItemStack(FoodInit.mushroom, 1, 0) });

		brewng(new ItemStack(Blocks.MYCELIUM, 1, 0), null, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
			"dirt",
			new ItemStack(FoodInit.mushroom, 1, 1) });

		brewng(new ItemStack(FoodInit.antibiotic, 1, 6), null, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
			"brothGrow",
			new ItemStack(FoodInit.mushroom, 1, 2) });

		// increace

		increace(new ItemStack(FoodInit.bacillus, 1, 0));

		increace(new ItemStack(FoodInit.bacillus, 1, 1));

		increace(new ItemStack(FoodInit.bacillus, 1, 2));

		increace(new ItemStack(FoodInit.coliformes, 1, 0));

		increace(new ItemStack(FoodInit.coliformes, 1, 1));

		increace(new ItemStack(FoodInit.coliformes, 1, 2));

		increace(new ItemStack(FoodInit.lab, 1, 0));

		increace(new ItemStack(FoodInit.lab, 1, 1));

		increace(new ItemStack(FoodInit.lab, 1, 2));

		increace(new ItemStack(FoodInit.beerYeast, 1, 0));

		increace(new ItemStack(FoodInit.beerYeast, 1, 1));

		increace(new ItemStack(FoodInit.beerYeast, 1, 2));

		increace(new ItemStack(FoodInit.oryzae, 1, 0));

		increace(new ItemStack(FoodInit.oryzae, 1, 1));

		increace(new ItemStack(FoodInit.oryzae, 1, 2));

		increace(new ItemStack(FoodInit.nether, 1, 0));

		increace(new ItemStack(FoodInit.nether, 1, 1));

		increace(new ItemStack(FoodInit.nether, 1, 2));

		increace(new ItemStack(FoodInit.blueMold, 1, 0));

		increace(new ItemStack(FoodInit.blueMold, 1, 1));

		increace(new ItemStack(FoodInit.blueMold, 1, 2));

		increace(new ItemStack(FoodInit.slimeMold, 1, 0));

		increace(new ItemStack(FoodInit.slimeMold, 1, 1));

		increace(new ItemStack(FoodInit.slimeMold, 1, 2));

		increace(new ItemStack(FoodInit.mushroom, 1, 0));

		increace(new ItemStack(FoodInit.mushroom, 1, 1));

		increace(new ItemStack(FoodInit.mushroom, 1, 2));

	}

	public static void loadStillRecipe() {

		still(new ItemStack(FoodInit.essentialOil, 1, 0), new FluidStack(FoodInit.roseWater, 1000), new FluidStack(
				FluidRegistry.WATER, 5000), new Object[] { new ItemStack(Blocks.DOUBLE_PLANT, 8, 4) });

		still(new ItemStack(FoodInit.essentialOil, 1, 1), null, new FluidStack(FluidRegistry.WATER,
				5000), new Object[] { "containerLemon" });

		still(new ItemStack(FoodInit.essentialOil, 1, 2), null, new FluidStack(FluidRegistry.WATER,
				5000), new Object[] { new ItemStack(FoodInit.crops, 8, 9) });

		still(new ItemStack(FoodInit.essentialOil, 1, 3), null, new FluidStack(FluidRegistry.WATER,
				5000), new Object[] { new ItemStack(MainInit.logCont, 1, 2) });

		still(new ItemStack(FoodInit.essentialOil, 1, 4), null, new FluidStack(FluidRegistry.WATER,
				5000), new Object[] { new ItemStack(FoodInit.petals, 8, 0) });

	}

	public static void loadLiquorRecipe() {

		// yeast

		// 特殊レシピ
		brewng(new ItemStack(FoodInit.residue, 1, 0), new FluidStack(FoodInit.beer, 200), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] { "containerMalt" });

		brewng(new ItemStack(FoodInit.residue, 1, 2), new FluidStack(FoodInit.wine, 200), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] { "containerGrape" });

		// 通常

		brewng(new ItemStack(FoodInit.residue, 1, 0), new FluidStack(FoodInit.beer, 1000), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] { "containerMalt", new ItemStack(FoodInit.beerYeast, 1, 0) });

		brewng(new ItemStack(FoodInit.residue, 1, 1), new FluidStack(FoodInit.sake, 1000), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
					new ItemStack(FoodInit.inoculum, 8, 0),
					new ItemStack(FoodInit.beerYeast, 1, 0) });

		brewng(new ItemStack(FoodInit.residue, 1, 1), new FluidStack(FoodInit.moromiBlack, 1000), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
					new ItemStack(FoodInit.inoculum, 8, 1),
					new ItemStack(FoodInit.beerYeast, 1, 0) });

		brewng(new ItemStack(MainInit.foodDust, 1, 1), new FluidStack(FoodInit.fermentedPotato, 1000), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
					"containerPotato",
					"foodMalt",
					new ItemStack(FoodInit.beerYeast, 1, 0) });

		brewng(null, new FluidStack(FoodInit.fermentedSugar, 1000), new FluidStack(FluidRegistry.WATER,
				1000), new Object[] {
					new ItemStack(MainInit.foodDust, 8, 2),
					new ItemStack(FoodInit.beerYeast, 1, 0) });

		brewng(new ItemStack(MainInit.foodDust, 1, 7), new FluidStack(FoodInit.fermentedSugar, 1000), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] { "containerReeds", new ItemStack(FoodInit.beerYeast, 1, 0) });

		brewng(new ItemStack(FoodInit.residue, 1, 2), new FluidStack(FoodInit.wine, 1000), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] { "containerGrape", new ItemStack(FoodInit.beerYeast, 1, 1) });

		brewng(new ItemStack(MainInit.foodDust, 1, 1), new FluidStack(FoodInit.dateWine, 1000), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] { "containerDate", new ItemStack(FoodInit.beerYeast, 1, 1) });

		brewng(new ItemStack(MainInit.miscDust, 1, 7), new FluidStack(FoodInit.netherWine, 1000), new FluidStack(
				FluidRegistry.WATER, 1000), new Object[] {
					"containerNetherWart",
					new ItemStack(FoodInit.nether, 1, 1) });

		// still

		still(null, new FluidStack(FoodInit.rawWhisky, 200), new FluidStack(FoodInit.beer, 1000));

		still(null, new FluidStack(FoodInit.rawBrandy, 200), new FluidStack(FoodInit.wine, 1000));

		still(null, new FluidStack(FoodInit.pomaceBrandy, 100), null, new Object[] {
			new ItemStack(FoodInit.residue, 8, 2) });

		still(null, new FluidStack(FoodInit.shotyu, 200), new FluidStack(FoodInit.sake, 1000));

		still(null, new FluidStack(FoodInit.shotyu, 100), null, new Object[] { new ItemStack(FoodInit.residue, 8, 1) });

		still(null, new FluidStack(FoodInit.awamori, 200), new FluidStack(FoodInit.moromiBlack, 1000));

		still(null, new FluidStack(FoodInit.araq, 200), new FluidStack(FoodInit.dateWine, 1000), new Object[] {
			"seedHerb" });

		still(null, new FluidStack(FoodInit.whiteRum, 200), new FluidStack(FoodInit.fermentedSugar, 1000));

		still(null, new FluidStack(FoodInit.akvavit, 200), new FluidStack(FoodInit.fermentedPotato,
				1000), new Object[] { "seedHerb" });

		still(null, new FluidStack(FoodInit.chorusLiquor, 200), new FluidStack(FoodInit.netherWine,
				1000), new Object[] { new ItemStack(Items.CHORUS_FRUIT, 1, 0) });

		still(null, new FluidStack(FoodInit.vodka, 500), new FluidStack(FoodInit.rawWhisky, 1000), new Object[] {
			"charcoal" });

		still(null, new FluidStack(FoodInit.vodka, 500), new FluidStack(FoodInit.rawBrandy, 1000), new Object[] {
			"charcoal" });

		still(null, new FluidStack(FoodInit.vodka, 500), new FluidStack(FoodInit.akvavit, 1000), new Object[] {
			"charcoal" });

		still(null, new FluidStack(FoodInit.vodka, 500), new FluidStack(FoodInit.araq, 1000), new Object[] {
			"charcoal" });

		still(null, new FluidStack(FoodInit.vodka, 500), new FluidStack(FoodInit.whiteRum, 1000), new Object[] {
			"charcoal" });

		// aging

		aging(new FluidStack(FoodInit.whisky, 800), new FluidStack(FoodInit.rawWhisky, 1000), 30);

		aging(new FluidStack(FoodInit.brandy, 800), new FluidStack(FoodInit.rawBrandy, 1000), 30);

		aging(new FluidStack(FoodInit.darkRum, 800), new FluidStack(FoodInit.whiteRum, 1000), 30);
	}

	public static void brewng(ItemStack out, FluidStack outF, FluidStack inF, Object... input) {
		FoodBrewingRecipe recipe = new FoodBrewingRecipe(out, outF, inF, input);
		MainAPIManager.brewingRegister.addBrewingRecipe(recipe);
	}

	public static void still(ItemStack out, FluidStack outF, FluidStack inF, Object... input) {
		FoodStillRecipe recipe = new FoodStillRecipe(out, outF, inF, input);
		MainAPIManager.brewingRegister.addStillRecipe(recipe);
	}

	public static void aging(FluidStack outF, FluidStack inF, int count) {
		FoodAgingRecipe recipe = new FoodAgingRecipe(outF, inF, count);
		MainAPIManager.brewingRegister.addAgingRecipe(recipe);
	}

	public static void increace(ItemStack species) {
		ItemStack out = species.copy();
		out.setCount(8);
		FoodBrewingRecipe recipe = new FoodBrewingRecipe(out, null, new FluidStack(FluidRegistry.WATER, 1000),
				new Object[] { "brothIncreace", species });
		MainAPIManager.brewingRegister.addBrewingRecipe(recipe);
	}
}
