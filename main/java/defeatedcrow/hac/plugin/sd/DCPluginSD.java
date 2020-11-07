package defeatedcrow.hac.plugin.sd;

import com.charles445.simpledifficulty.api.config.JsonConfig;
import com.charles445.simpledifficulty.api.config.json.JsonItemIdentity;

import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.Item;

public class DCPluginSD {

	public static void loadInit() {
		// linen
		register(MainInit.linenUnder, -1.0F);
		register(MainInit.linenShirt, -1.0F);
		register(MainInit.linenJacket, -1.0F);
		register(MainInit.linenBottom, -1.0F);
		register(MainInit.linenCoat, -1.0F);

		// cotton
		register(MainInit.clothUnder, 1.0F);
		register(MainInit.clothShirt, 1.0F);
		register(MainInit.clothJacket, 2.0F);
		register(MainInit.clothBottom, 1.0F);
		register(MainInit.clothCoat, 2.0F);
		register(MainInit.clothSkirt, 1.0F);
		register(MainInit.cottonHat, 1.0F);

		register(MainInit.workerSuit, -1.0F);

		// lether
		register(MainInit.leatherHat, -1.0F);

		// wool
		register(MainInit.woolBoots, 2.0F);
		register(MainInit.woolJacket, 2.0F);
		register(MainInit.woolWear, 3.0F);
		register(MainInit.peaCoat, 3.0F);
		register(MainInit.modsCoat, 3.0F);
		register(MainInit.hoodie, 2.0F);
		register(MainInit.furWear, 3.0F);
		register(MainInit.furCape, 2.0F);

		// silk
		register(MainInit.silkDress, -2.0F);
		register(MainInit.silkCape, -2.0F);
		register(MainInit.silkSkirt, -2.0F);
		register(MainInit.blackCoat, -1.0F);
		register(MainInit.blackSuit, -1.0F);

		register(MainInit.magicUnder, 1.0F);
		register(MainInit.magicCoat, 2.0F);

		// synthetic
		register(MainInit.trackSuit, -2.0F);
		register(MainInit.combatDress, -1.0F);
	}

	private static void register(Item item, float data) {
		if (item != null) {
			JsonConfig.registerArmorTemperature(item.getRegistryName().toString(), data, new JsonItemIdentity(-1));
		}
	}

}
