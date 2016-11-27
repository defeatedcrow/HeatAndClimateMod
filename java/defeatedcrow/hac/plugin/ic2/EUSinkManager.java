package defeatedcrow.hac.plugin.ic2;

import net.minecraft.tileentity.TileEntity;

public class EUSinkManager {

	private EUSinkManager() {
	}

	public static IEUSinkChannel getChannel(TileEntity tile, int cap, int tier) {
		return new EUSinkChannel(tile, cap, tier);
	}

}
