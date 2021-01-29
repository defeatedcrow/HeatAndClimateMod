package defeatedcrow.hac.plugin;

import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.DamageAPI;

public class DCPluginAnimania {

	public static final DCPluginAnimania INSTANCE = new DCPluginAnimania();

	public static void loadInit() {
		DamageAPI.resistantData.registerEntityResistant(EntityAnimaniaChicken.class, DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(EntityHamster.class, DCHeatTier.WARM, 2.0F);
		DamageAPI.resistantData.registerEntityResistant(EntityHedgehog.class, DCHeatTier.WARM, 2.0F);
		DamageAPI.resistantData.registerEntityResistant(EntityAnimaniaPeacock.class, DCHeatTier.WARM, 2.0F);
	}

}
