package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.ClimateSmelting;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.ItemStack;

public class FoodClimateRecipe {

	public static void load() {
		loadClimateRecipes();
		loadSmeltingRecipe();
	}

	static void loadClimateRecipes() {

		ClimateSmelting pizza = new ClimateSmelting(new ItemStack(FoodInit.bread, 1, 7), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.bread, 1, 6));
		pizza.requiredHeat().add(DCHeatTier.SMELTING);
		pizza.requiredHum().add(DCHumidity.NORMAL);
		pizza.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(pizza);

		ClimateSmelting toast = new ClimateSmelting(new ItemStack(FoodInit.bread, 1, 5), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.bread, 1, 4));
		toast.requiredHeat().add(DCHeatTier.SMELTING);
		toast.requiredHum().add(DCHumidity.NORMAL);
		toast.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(toast);

		ClimateSmelting square = new ClimateSmelting(new ItemStack(FoodInit.bread, 1, 3), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.bread, 1, 2));
		square.requiredHeat().add(DCHeatTier.SMELTING);
		square.requiredHum().add(DCHumidity.NORMAL);
		square.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(square);

		ClimateSmelting round = new ClimateSmelting(new ItemStack(FoodInit.bread, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.bread, 1, 0));
		round.requiredHeat().add(DCHeatTier.SMELTING);
		round.requiredHum().add(DCHumidity.NORMAL);
		round.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(round);

		ClimateSmelting fish = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 0));
		fish.requiredHeat().add(DCHeatTier.SMELTING);
		fish.requiredHum().add(DCHumidity.NORMAL);
		fish.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(fish);

		ClimateSmelting tori = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 3), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 2));
		tori.requiredHeat().add(DCHeatTier.SMELTING);
		tori.requiredHum().add(DCHumidity.NORMAL);
		tori.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(tori);

		ClimateSmelting pork = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 5), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 4));
		pork.requiredHeat().add(DCHeatTier.SMELTING);
		pork.requiredHum().add(DCHumidity.NORMAL);
		pork.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(pork);

		ClimateSmelting beef = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 7), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 6));
		beef.requiredHeat().add(DCHeatTier.SMELTING);
		beef.requiredHum().add(DCHumidity.NORMAL);
		beef.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(beef);

		ClimateSmelting mutton = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 9), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 8));
		mutton.requiredHeat().add(DCHeatTier.SMELTING);
		mutton.requiredHum().add(DCHumidity.NORMAL);
		mutton.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(mutton);

		ClimateSmelting ika = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 11), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 10));
		ika.requiredHeat().add(DCHeatTier.SMELTING);
		ika.requiredHum().add(DCHumidity.NORMAL);
		ika.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(ika);

		ClimateSmelting a_tart = new ClimateSmelting(new ItemStack(FoodInit.pastryRound, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastryRound, 1, 0));
		a_tart.requiredHeat().add(DCHeatTier.SMELTING);
		a_tart.requiredHum().add(DCHumidity.NORMAL);
		a_tart.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(a_tart);

		ClimateSmelting l_tart = new ClimateSmelting(new ItemStack(FoodInit.pastryRound, 1, 3), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastryRound, 1, 2));
		l_tart.requiredHeat().add(DCHeatTier.SMELTING);
		l_tart.requiredHum().add(DCHumidity.NORMAL);
		l_tart.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(l_tart);

		ClimateSmelting s_tart = new ClimateSmelting(new ItemStack(FoodInit.pastryRound, 1, 5), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastryRound, 1, 4));
		s_tart.requiredHeat().add(DCHeatTier.SMELTING);
		s_tart.requiredHum().add(DCHumidity.NORMAL);
		s_tart.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(s_tart);

		ClimateSmelting p_tart = new ClimateSmelting(new ItemStack(FoodInit.pastryRound, 1, 7), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastryRound, 1, 6));
		p_tart.requiredHeat().add(DCHeatTier.SMELTING);
		p_tart.requiredHum().add(DCHumidity.NORMAL);
		p_tart.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(p_tart);

		ClimateSmelting c_tart = new ClimateSmelting(new ItemStack(FoodInit.pastryRound, 1, 9), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastryRound, 1, 8));
		c_tart.requiredHeat().add(DCHeatTier.SMELTING);
		c_tart.requiredHum().add(DCHumidity.NORMAL);
		c_tart.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(c_tart);

		ClimateSmelting s_pie = new ClimateSmelting(new ItemStack(FoodInit.pastrySquare, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastrySquare, 1, 0));
		s_pie.requiredHeat().add(DCHeatTier.SMELTING);
		s_pie.requiredHum().add(DCHumidity.NORMAL);
		s_pie.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(s_pie);

		ClimateSmelting m_pie = new ClimateSmelting(new ItemStack(FoodInit.pastrySquare, 1, 3), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastrySquare, 1, 2));
		m_pie.requiredHeat().add(DCHeatTier.SMELTING);
		m_pie.requiredHum().add(DCHumidity.NORMAL);
		m_pie.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(m_pie);

		ClimateSmelting c_pie = new ClimateSmelting(new ItemStack(FoodInit.pastrySquare, 1, 5), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastrySquare, 1, 4));
		c_pie.requiredHeat().add(DCHeatTier.SMELTING);
		c_pie.requiredHum().add(DCHumidity.NORMAL);
		c_pie.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(c_pie);

		ClimateSmelting f_pie = new ClimateSmelting(new ItemStack(FoodInit.pastrySquare, 1, 7), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastrySquare, 1, 6));
		f_pie.requiredHeat().add(DCHeatTier.SMELTING);
		f_pie.requiredHum().add(DCHumidity.NORMAL);
		f_pie.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(f_pie);

		ClimateSmelting g_pie = new ClimateSmelting(new ItemStack(FoodInit.pastrySquare, 1, 9), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastrySquare, 1, 8));
		g_pie.requiredHeat().add(DCHeatTier.SMELTING);
		g_pie.requiredHum().add(DCHumidity.NORMAL);
		g_pie.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(g_pie);

		ClimateSmelting cu_pie = new ClimateSmelting(new ItemStack(FoodInit.pastrySquare, 1, 11), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.pastrySquare, 1, 10));
		cu_pie.requiredHeat().add(DCHeatTier.SMELTING);
		cu_pie.requiredHum().add(DCHumidity.NORMAL);
		cu_pie.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(cu_pie);

		ClimateSmelting b_plate = new ClimateSmelting(new ItemStack(FoodInit.plateMeal, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.plateMeal, 1, 0));
		b_plate.requiredHeat().add(DCHeatTier.SMELTING);
		b_plate.requiredHum().add(DCHumidity.NORMAL);
		b_plate.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(b_plate);

		ClimateSmelting po_plate = new ClimateSmelting(new ItemStack(FoodInit.plateMeal, 1, 3), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.plateMeal, 1, 2));
		po_plate.requiredHeat().add(DCHeatTier.SMELTING);
		po_plate.requiredHum().add(DCHumidity.NORMAL);
		po_plate.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(po_plate);

		ClimateSmelting c_plate = new ClimateSmelting(new ItemStack(FoodInit.plateMeal, 1, 5), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.plateMeal, 1, 4));
		c_plate.requiredHeat().add(DCHeatTier.SMELTING);
		c_plate.requiredHum().add(DCHumidity.NORMAL);
		c_plate.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(c_plate);

		ClimateSmelting f_plate = new ClimateSmelting(new ItemStack(FoodInit.plateMeal, 1, 7), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.plateMeal, 1, 6));
		f_plate.requiredHeat().add(DCHeatTier.SMELTING);
		f_plate.requiredHum().add(DCHumidity.NORMAL);
		f_plate.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(f_plate);

		ClimateSmelting p_plate = new ClimateSmelting(new ItemStack(FoodInit.plateSoup, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.plateSoup, 1, 0));
		p_plate.requiredHeat().add(DCHeatTier.SMELTING);
		p_plate.requiredHum().add(DCHumidity.NORMAL);
		p_plate.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(p_plate);

		ClimateSmelting t_plate = new ClimateSmelting(new ItemStack(FoodInit.plateSoup, 1, 3), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.plateSoup, 1, 2));
		t_plate.requiredHeat().add(DCHeatTier.SMELTING);
		t_plate.requiredHum().add(DCHumidity.NORMAL);
		t_plate.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(t_plate);

		ClimateSmelting cake = new ClimateSmelting(new ItemStack(FoodInit.cake, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.cake, 1, 0));
		cake.requiredHeat().add(DCHeatTier.SMELTING);
		cake.requiredHum().add(DCHumidity.NORMAL);
		cake.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(cake);

		ClimateSmelting cocotte = new ClimateSmelting(new ItemStack(FoodInit.cake, 1, 9), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.cake, 1, 8));
		cocotte.requiredHeat().add(DCHeatTier.SMELTING);
		cocotte.requiredHum().add(DCHumidity.NORMAL);
		cocotte.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(cocotte);

		ClimateSmelting gohei = new ClimateSmelting(new ItemStack(FoodInit.sticks, 1, 13), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.sticks, 1, 12));
		gohei.requiredHeat().add(DCHeatTier.SMELTING);
		gohei.requiredHum().add(DCHumidity.NORMAL);
		gohei.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(gohei);

		ClimateSmelting mochi = new ClimateSmelting(new ItemStack(FoodInit.mochi, 1, 1), null, DCHeatTier.OVEN,
				DCHumidity.DRY, null, 0F, false, new ItemStack(FoodInit.mochi, 1, 0));
		mochi.requiredHeat().add(DCHeatTier.SMELTING);
		mochi.requiredHum().add(DCHumidity.NORMAL);
		mochi.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(mochi);

		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 11), new ItemStack(FoodInit.bread, 1, 10));

		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 13), new ItemStack(FoodInit.bread, 1, 12));

		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 15), new ItemStack(FoodInit.bread, 1, 14));

		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 17), new ItemStack(FoodInit.bread, 1, 16));

		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 19), new ItemStack(FoodInit.bread, 1, 18));

		addFoodRecipe(new ItemStack(FoodInit.sticks, 1, 15), new ItemStack(FoodInit.sticks, 1, 14));

		addFoodRecipe(new ItemStack(FoodInit.plateMeal, 1, 9), new ItemStack(FoodInit.plateMeal, 1, 8));

	}

	private static void addFoodRecipe(ItemStack in, ItemStack out) {
		ClimateSmelting r = new ClimateSmelting(in, null, DCHeatTier.OVEN, DCHumidity.DRY, null, 0F, false, out);
		r.requiredHeat().add(DCHeatTier.SMELTING);
		r.requiredHum().add(DCHumidity.NORMAL);
		r.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(r);
	}

	private static void loadSmeltingRecipe() {
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.bakedApple, 1,
				5), DCHeatTier.BOIL, DCHumidity.UNDERWATER, null, false, new ItemStack(FoodInit.crops, 1, 10));

		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.bakedApple, 1,
				6), DCHeatTier.BOIL, DCHumidity.UNDERWATER, null, false, new ItemStack(FoodInit.crops, 1, 12));
	}

}
