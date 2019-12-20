package defeatedcrow.hac.plugin;

import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;

import defeatedcrow.hac.api.damage.DamageAPI;

public class DCPluginAnimania {

	public static final DCPluginAnimania INSTANCE = new DCPluginAnimania();

	private DCPluginAnimania() {}

	public static void load() {
		DamageAPI.resistantData.registerEntityResistant(EntityAnimaniaChicken.class, 1.0F, 0.0F);
		DamageAPI.resistantData.registerEntityResistant(EntityHamster.class, 2.0F, 0.0F);
		DamageAPI.resistantData.registerEntityResistant(EntityHedgehog.class, 1.0F, 1.0F);
		DamageAPI.resistantData.registerEntityResistant(EntityAnimaniaPeacock.class, 1.0F, 0.0F);
	}

}
