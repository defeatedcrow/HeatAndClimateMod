package defeatedcrow.hac.magic;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.JsonRegisterHelper;

@SideOnly(Side.CLIENT)
public class MagicClientProxy {

	public static void loadTE() {
	}

	public static void regJson(JsonRegisterHelper instance) {
		// item

		instance.regSimpleItem(MagicInit.pendant, ClimateCore.PACKAGE_ID, "dcs_jewel_pendant", "equip", 10);
		instance.regSimpleItem(MagicInit.badge, ClimateCore.PACKAGE_ID, "dcs_jewel_badge", "equip", 10);
	}

}
