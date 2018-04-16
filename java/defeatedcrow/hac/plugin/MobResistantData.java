package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.damage.DamageAPI;
import net.minecraft.entity.EntityList;

public class MobResistantData {
	public static final MobResistantData INSTANCE = new MobResistantData();

	private MobResistantData() {}

	public static void load() {
		Class blizz = EntityList.NAME_TO_CLASS.get("thermalfoundation.blizz");
		Class blitz = EntityList.NAME_TO_CLASS.get("thermalfoundation.blitz");
		Class basalz = EntityList.NAME_TO_CLASS.get("thermalfoundation.basalz");

		Class chast = EntityList.NAME_TO_CLASS.get("schr0chastmob.chast");

		DamageAPI.resistantData.registerEntityResistant(blizz, 0.0F, 6.0F);
		DamageAPI.resistantData.registerEntityResistant(blitz, 2.0F, 2.0F);
		DamageAPI.resistantData.registerEntityResistant(basalz, 6.0F, 0.0F);
		DamageAPI.resistantData.registerEntityResistant(chast, 2.0F, 2.0F);
	}

}
