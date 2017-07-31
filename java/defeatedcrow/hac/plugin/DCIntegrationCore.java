package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.cofh.DCPluginCoFH;
import net.minecraftforge.fml.common.Loader;

public class DCIntegrationCore {

	public static final DCIntegrationCore INSTANCE = new DCIntegrationCore();

	public static boolean loadedForestry = false;
	public static boolean loadedMekanism = false;
	public static boolean loadedCoFH = false;

	private DCIntegrationCore() {}

	public static void loadedCheck() {
		if (Loader.isModLoaded("forestry")) {
			loadedForestry = true;
		}
		if (Loader.isModLoaded("Mekanism")) {
			loadedMekanism = true;
		}
		if (Loader.isModLoaded("cofhcore") && Loader.isModLoaded("thermalfoundation")) {
			loadedCoFH = true;
		}
	}

	public static void load() {

		DCPluginFluid.load();

		if (Loader.isModLoaded("BiomesOPlenty") && ModuleConfig.bop) {
			try {
				DCPluginBoP.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: BiomesOPlenty");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: BiomesOPlenty");
			}
		}

		if (loadedForestry && ModuleConfig.ffm) {
			try {
				DCPluginForestry.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: forestry");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: forestry");
			}
		}

		if (Loader.isModLoaded("IC2") && ModuleConfig.ic2) {
			try {
				DCPluginIC2.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: IC2");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: IC2");
			}
		}

		if (Loader.isModLoaded("bamboomod")) {
			try {
				DCPluginBamboo.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: bamboomod");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: bamboomod");
			}
		}

		if (Loader.isModLoaded("schr0tanpopo")) {
			try {
				DCPluginTanpopo.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: schr0tanpopo");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: schr0tanpopo");
			}
		}

		if (Loader.isModLoaded("JEI")) {
			try {
				DCPluginJeiMain.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: JEI");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: JEI");
			}
		}
	}

	public static void loadIMC() {
		if (loadedMekanism && ModuleConfig.mek) {
			try {
				DCPluginMekanism.sendIMC();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: Mekanism");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: Mekanism");
			}
		}

		if (loadedCoFH && ModuleConfig.cofh) {
			try {
				DCPluginCoFH.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: cofh");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: cofh");
			}
		}

		try {
			MobResistantData.load();
			DCLogger.infoLog("dcs_climate", "Successfully loaded mob resistant data");
		} catch (Exception e) {
			DCLogger.infoLog("dcs_climate", "Failed to load mob resistant data");
		}
	}

}
