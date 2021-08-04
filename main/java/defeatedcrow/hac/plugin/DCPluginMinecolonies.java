package defeatedcrow.hac.plugin;

import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMob;
import com.minecolonies.coremod.items.ItemPirateGear;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.DamageAPI;

public class DCPluginMinecolonies {

	public static final DCPluginMinecolonies INSTANCE = new DCPluginMinecolonies();

	private DCPluginMinecolonies() {}

	public static void load() {
		DamageAPI.resistantData.registerEntityResistant(AbstractEntityCitizen.class, DCHeatTier.NORMAL, 3.0F);
		DamageAPI.resistantData.registerEntityResistant(AbstractEntityMinecoloniesMob.class, DCHeatTier.NORMAL, 3.0F);

		DamageAPI.armorRegister.registerMaterial(ItemPirateGear.PIRATE_ARMOR_1, 2.0F, 2.0F);
		DamageAPI.armorRegister.registerMaterial(ItemPirateGear.PIRATE_ARMOR_2, 2.0F, 2.0F);
	}

}
