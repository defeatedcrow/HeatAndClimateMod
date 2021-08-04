package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.DamageAPI;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.util.ResourceLocation;
import net.tangotek.tektopia.entities.ai.EntityAIEatFood;
import net.tangotek.tektopia.entities.ai.EntityAIEatFood.VillagerFood;

public class DCPluginTektopia {

	public static final DCPluginTektopia INSTANCE = new DCPluginTektopia();

	private DCPluginTektopia() {}

	public static void load() {
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"lumberjack"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"farmer"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"miner"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"blacksmith"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"guard"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"architect"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"tradesman"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"merchant"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"nomad"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"rancher"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"butcher"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"enchanter"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"child"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"chef"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"teacher"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"nitwit"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"cleric"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"druid"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia",
				"bard"), DCHeatTier.NORMAL, 3.0F);

		loadFoodData();
	}

	public static void loadFoodData() {
		EntityAIEatFood.villagerFood.put(FoodInit.cake, new VillagerFood(FoodInit.cake, 10, 18, null));
		EntityAIEatFood.villagerFood.put(FoodInit.clubsandwich, new VillagerFood(FoodInit.clubsandwich, 35, 5, null));
		EntityAIEatFood.villagerFood.put(FoodInit.curry, new VillagerFood(FoodInit.curry, 70, 12, null));
		EntityAIEatFood.villagerFood.put(FoodInit.deepFry, new VillagerFood(FoodInit.deepFry, 40, 3, null));
		EntityAIEatFood.villagerFood.put(FoodInit.dishBig, new VillagerFood(FoodInit.dishBig, 55, 10, null));
		EntityAIEatFood.villagerFood.put(FoodInit.sandwich, new VillagerFood(FoodInit.sandwich, 20, 4, null));
		EntityAIEatFood.villagerFood.put(FoodInit.pasta, new VillagerFood(FoodInit.pasta, 50, 8, null));
		EntityAIEatFood.villagerFood.put(FoodInit.udon, new VillagerFood(FoodInit.udon, 40, 5, null));
		EntityAIEatFood.villagerFood.put(FoodInit.setMeal, new VillagerFood(FoodInit.setMeal, 80, 8, null));
		EntityAIEatFood.villagerFood.put(FoodInit.wagashi, new VillagerFood(FoodInit.wagashi, 12, 15, null));
		EntityAIEatFood.villagerFood.put(FoodInit.ricebowl, new VillagerFood(FoodInit.ricebowl, 35, 3, null));
		EntityAIEatFood.villagerFood.put(FoodInit.snack, new VillagerFood(FoodInit.snack, 25, 5, null));
		EntityAIEatFood.villagerFood.put(FoodInit.bowlSoup, new VillagerFood(FoodInit.bowlSoup, 30, 5, null));
		EntityAIEatFood.villagerFood.put(FoodInit.yogurt, new VillagerFood(FoodInit.yogurt, 10, 15, null));
	}

}
