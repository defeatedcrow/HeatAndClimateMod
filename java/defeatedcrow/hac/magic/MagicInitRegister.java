package defeatedcrow.hac.magic;

import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.magic.item.ItemMagicalBadge;
import defeatedcrow.hac.magic.item.ItemMagicalPendant;
import defeatedcrow.hac.main.ClimateMain;

public class MagicInitRegister {

	private MagicInitRegister() {
	}

	public static void load() {
		loadItems();
	}

	static void loadItems() {
		MagicInit.pendant = new ItemMagicalPendant(10).setCreativeTab(ClimateMain.tool).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_jewel_pendant");
		GameRegistry.register(MagicInit.pendant.setRegistryName(ClimateCore.PACKAGE_BASE + "_jewel_pendant"));

		MagicInit.badge = new ItemMagicalBadge(10).setCreativeTab(ClimateMain.tool).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_jewel_badge");
		GameRegistry.register(MagicInit.badge.setRegistryName(ClimateCore.PACKAGE_BASE + "_jewel_badge"));
	}

}
