package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.DCLogger;
import net.minecraftforge.fml.common.Loader;

public class DCIntegrationCore {

	public static final DCIntegrationCore INSTANCE = new DCIntegrationCore();

	private DCIntegrationCore() {
	}

	public static void load() {

		DCPluginFluid.load();

		if (Loader.isModLoaded("BiomeOPlenty")) {
			try {
				DCPluginBoP.load();
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: BiomeOPlenty");
			}
		}

		if (Loader.isModLoaded("forestry")) {
			try {
				DCPluginForestry.load();
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: forestry");
			}
		}

		if (Loader.isModLoaded("IC2")) {
			try {
				DCPluginIC2.load();
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: IC2");
			}
		}

	}

}
