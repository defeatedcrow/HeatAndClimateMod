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
	public static boolean loadedBC = false;
	public static boolean loadedIC2 = false;

	private DCIntegrationCore() {}

	public static void loadedCheck() {
		if (Loader.isModLoaded("forestry") && ModuleConfig.ffm) {
			loadedForestry = true;
		}
		if (Loader.isModLoaded("Mekanism") && ModuleConfig.mek) {
			loadedMekanism = true;
		}
		if (Loader.isModLoaded("cofhcore") && ModuleConfig.cofh) {
			loadedCoFH = true;
		}
		if (Loader.isModLoaded("BiomesOPlenty") && ModuleConfig.bop) {
			loadedBoP = true;
		}
		if (Loader.isModLoaded("JEI")) {
			loadedJEI = true;
		}
		if (Loader.isModLoaded("schr0tanpopo")) {
			loadedTanpopo = true;
		}
		if (Loader.isModLoaded("buildcraftenergy") && ModuleConfig.bc) {
			loadedBC = true;
		}
		if (Loader.isModLoaded("IC2") && ModuleConfig.ic2) {
			loadedIC2 = true;
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

		if (loadedBoP) {
			try {
				DCPluginBoP.loadInit();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: BiomesOPlenty");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: BiomesOPlenty");
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

		if (loadedIC2) {
			try {
				DCPluginIC2.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: IC2");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: IC2");
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

		if (loadedForestry) {
			try {
				DCPluginForestry.loadInit();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: forestry");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: forestry");
			}
		}

		if (loadedBC) {
			try {
				DCPluginBuildcraft.loadInit();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: buildcraftenergy");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: buildcraftenergy");
			}
		}

		if (loadedMekanism) {
			try {
				DCPluginMekanism.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: Mekanism");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: Mekanism");
			}
		}

		try {
			MobResistantData.load();
			DCLogger.infoLog("dcs_climate", "Successfully loaded mob resistant data");
		} catch (Exception e) {
			DCLogger.infoLog("dcs_climate", "Failed to load mob resistant data");
		}

		// FMLCommonHandler.instance().resetClientRecipeBook();
	}

}
