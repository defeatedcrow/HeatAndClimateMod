package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.damage.DamageAPI;
import net.minecraft.util.ResourceLocation;

public class MobResistantData {
	public static final MobResistantData INSTANCE = new MobResistantData();

	private MobResistantData() {}

	public static void load() {

		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("thermalfoundation", "blizz"), 0.0F, 6.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("thermalfoundation", "blitz"), 2.0F, 2.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("thermalfoundation",
				"basalz"), 6.0F, 0.0F);

		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("schr0chastmob", "chast"), 2.0F, 2.0F);

		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("familiarfauna",
				"familiarfauna.deer"), 1.0F, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(new ResourceLocation("familiarfauna",
				"familiarfauna.turkey"), 2.0F, 1.0F);
	}

}
