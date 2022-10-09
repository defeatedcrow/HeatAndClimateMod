package defeatedcrow.hac.core.json;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.CoreInit;

public class JsonInit {

	public static void init() {

		if (!ClimateCore.isDebug || ClimateCore.assetsDir == null || ClimateCore.dataDir == null)
			return;

		DCLogger.debugInfoLog("check");

		CoreInit.BLOCKS.getEntries().stream().forEach(key -> {
			if (key != null && key.get() instanceof IJsonDataDC) {
				JsonBuilderDC.INSTANCE.BuildBlockJsonModel(key.get());
			}
		});

		CoreInit.ITEMS.getEntries().stream().forEach(key -> {
			if (key != null && key.get() instanceof IJsonDataDC) {
				JsonBuilderDC.INSTANCE.BuildItemJsonModel(key.get());
			}
		});
	}

}
