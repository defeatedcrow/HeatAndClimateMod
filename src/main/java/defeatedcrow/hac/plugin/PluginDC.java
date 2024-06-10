package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.DCLogger;
import net.minecraftforge.fml.loading.LoadingModList;

public class PluginDC {

	public static void init() {

		LoadingModList.get().getMods().forEach(info -> {
			if (info.getModId().equalsIgnoreCase("curios")) {
				DCLogger.debugInfoLog("Loading plugin: curios");
				CuriosPluginDC.init();
			}
		});

	}

}
