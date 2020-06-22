package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.damage.DamageAPI;
import net.minecraft.util.ResourceLocation;

public class DCPluginTektopia {

	public static final DCPluginTektopia INSTANCE = new DCPluginTektopia();

	private DCPluginTektopia() {}

	public static void load() {
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "lumberjack"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "farmer"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "miner"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "blacksmith"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "guard"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "architect"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "tradesman"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "merchant"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "nomad"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "rancher"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "butcher"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "enchanter"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "child"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "chef"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "teacher"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "nitwit"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "cleric"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "druid"), 3.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("tektopia", "bard"), 3.0F, 3.0F);

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
