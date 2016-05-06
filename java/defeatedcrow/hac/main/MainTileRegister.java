package defeatedcrow.hac.main;

import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.main.block.device.TileNormalChamber;

public class MainTileRegister {
	private MainTileRegister() {
	}

	public static void load() {
		GameRegistry.registerTileEntity(TileNormalChamber.class, "tileentity.dcs.chamber_normal");
	}
}
