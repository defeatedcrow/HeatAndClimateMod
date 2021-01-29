package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.DamageAPI;
import net.minecraft.util.ResourceLocation;

public class MobResistantData {
	public static final MobResistantData INSTANCE = new MobResistantData();

	private MobResistantData() {}

	public static void load() {

		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("thermalfoundation",
				"blizz"), DCHeatTier.FROSTBITE, 4.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("thermalfoundation",
				"blitz"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("thermalfoundation",
				"basalz"), DCHeatTier.OVEN, 4.0F);

		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("schr0chastmob",
				"chast"), DCHeatTier.NORMAL, 3.0F);

		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("familiarfauna",
				"familiarfauna.deer"), DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("familiarfauna",
				"familiarfauna.turkey"), DCHeatTier.NORMAL, 3.0F);
	}

}
