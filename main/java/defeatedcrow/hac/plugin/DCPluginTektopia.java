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
	}

}
