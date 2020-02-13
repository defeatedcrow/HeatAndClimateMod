package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.damage.DamageAPI;
import net.tangotek.tektopia.entities.EntityVillagerTek;

public class DCPluginTektopia {

	public static final DCPluginTektopia INSTANCE = new DCPluginTektopia();

	private DCPluginTektopia() {}

	public static void load() {
		DamageAPI.resistantData.registerEntityResistant(EntityVillagerTek.class, 3.0F, 3.0F);
	}

}
