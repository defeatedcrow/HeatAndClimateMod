package defeatedcrow.hac.plugin.ic2;

import net.minecraft.tileentity.TileEntity;

public class EUSourceManager {

	private EUSourceManager() {
	}

	public static IEUSourceChannel getChannel(TileEntity tile, int cap, int tier) {
		return new EUSourceChannel(tile, cap, tier);
	}

}
