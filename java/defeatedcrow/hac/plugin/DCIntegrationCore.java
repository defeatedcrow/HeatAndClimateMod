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
	public static boolean loadedBoP = false;
	public static boolean loadedTanpopo = false;
	public static boolean loadedJEI = false;

	private DCIntegrationCore() {}

	public static void loadedCheck() {
		if (Loader.isModLoaded("forestry") && ModuleConfig.ffm) {
			loadedForestry = true;
		}
		if (Loader.isModLoaded("mekanism") && ModuleConfig.mek) {
			loadedMekanism = true;
		}
		if (Loader.isModLoaded("cofhcore") && ModuleConfig.cofh) {
			loadedCoFH = true;
		}
		if (Loader.isModLoaded("biomesoplenty") && ModuleConfig.bop) {
			loadedBoP = true;
		}
		if (Loader.isModLoaded("jei")) {
			loadedJEI = true;
		}
		if (Loader.isModLoaded("schr0tanpopo")) {
			loadedTanpopo = true;
		}
	}

	public static void loadPre() {

	}

	public static void loadInit() {

		DCPluginFluid.load();

		if (loadedMekanism) {
			try {
				DCPluginMekanism.sendIMC();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: Mekanism");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: Mekanism");
			}
		}

		if (loadedCoFH) {
			try {
				DCPluginCoFH.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: cofh");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: cofh");
			}
		}

		if (loadedTanpopo) {
			try {
				DCPluginTanpopo.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: schr0tanpopo");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: schr0tanpopo");
			}
		}

		if (loadedJEI) {
			try {
				DCPluginJeiMain.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: JEI");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: JEI");
			}
		}
	}

	public static void loadPost() {

		if (loadedBoP) {
			try {
				DCPluginBoP.loadInit();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: BiomesOPlenty");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: BiomesOPlenty");
			}
		}

		if (loadedForestry) {
			try {
				DCPluginForestry.loadInit();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: forestry");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: forestry");
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
