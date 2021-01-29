package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.DamageAPI;
import net.minecraft.util.ResourceLocation;

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

		// loadFoodData();
	}

	// public static void loadFoodData() {
	// try {
	// Method m = ReflectionHelper
	// .findMethod(EntityAIEatFood.class, "registerFood", "registerFood", Item.class, int.class, int.class);
	// if (m != null) {
	// m.invoke(null, MainInit.bakedApple, 18, 2);
	// m.invoke(null, FoodInit.cake, 15, 20);
	// m.invoke(null, FoodInit.bowlSoup, 40, 8);
	// m.invoke(null, FoodInit.sandwich, 35, 3);
	// m.invoke(null, FoodInit.deepFry, 60, 7);
	// m.invoke(null, FoodInit.clubsandwich, 60, 8);
	// m.invoke(null, FoodInit.dishSq, 20, 4);
	// m.invoke(null, FoodInit.dishBig, 60, 6);
	// m.invoke(null, FoodInit.icecream, 10, 12);
	// m.invoke(null, FoodInit.salad, 15, 8);
	// m.invoke(null, FoodInit.setMeal, 80, 12);
	// m.invoke(null, FoodInit.snack, 35, 8);
	// m.invoke(null, FoodInit.udon, 60, 15);
	// m.invoke(null, FoodInit.wagashi, 20, 15);
	// m.invoke(null, FoodInit.ricebowl, 30, 6);
	// m.invoke(null, FoodInit.nonEntity, 8, 6);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

}
