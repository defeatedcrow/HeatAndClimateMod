package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.cofh.DCPluginCoFH;
import defeatedcrow.hac.plugin.sd.DCPluginSD;
import defeatedcrow.hac.plugin.tan.DCPluginTaN;
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
	public static boolean loadedMCE = false;
	public static boolean loadedTaN = false;
	public static boolean loadedGVC = false;
	public static boolean loadedEIO = false;
	public static boolean loadedTektopia = false;
	public static boolean loadedAnimania = false;
	public static boolean loadedUGB = false;
	public static boolean loadedRC = false;
	public static boolean loadedSD = false;

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
		if (Loader.isModLoaded("hmggvc")) {
			loadedGVC = true;
		}
		if (Loader.isModLoaded("mceconomy3") && ModuleConfig.mce) {
			loadedMCE = true;
		}
		if (Loader.isModLoaded("buildcraftenergy") && ModuleConfig.bc) {
			loadedBC = true;
		}
		if (Loader.isModLoaded("toughasnails") && ModuleConfig.tan) {
			loadedTaN = true;
		}
		if (Loader.isModLoaded("enderio") && ModuleConfig.eio) {
			loadedEIO = true;
		}
		if (Loader.isModLoaded("tektopia")) {
			loadedTektopia = true;
		}
		if (Loader.isModLoaded("animania") && ModuleConfig.ani) {
			loadedAnimania = true;
		}
		if (Loader.isModLoaded("undergroundbiomes") && ModuleConfig.ugb) {
			loadedUGB = true;
		}
		if (Loader.isModLoaded("railcraft") && ModuleConfig.rc) {
			loadedRC = true;
		}
		if (Loader.isModLoaded("simpledifficulty") && ModuleConfig.sd) {
			loadedSD = true;
		}
	}

	public static void loadPre() {

	}

	public static void loadInit() {

		DCPluginFluid.load();

		if (loadedMekanism) {
			try {
				DCPluginMekanism.sendIMC();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: Mekanism");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: Mekanism");
			}
		}

		if (loadedBC) {
			try {
				DCPluginBuildcraft.loadInit();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: buildcraftenergy");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: buildcraftenergy");
			}
		}

		if (loadedCoFH) {
			try {
				DCPluginCoFH.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: cofh");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: cofh");
			}
		}

		if (loadedTanpopo) {
			try {
				DCPluginTanpopo.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: schr0tanpopo");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: schr0tanpopo");
			}
		}

		if (loadedBoP) {
			try {
				DCPluginBoP.loadInit();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: BiomesOPlenty");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: BiomesOPlenty");
			} catch (Error e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: BiomesOPlenty");
			}
		}

		if (loadedEIO) {
			try {
				DCPluginEIO.init();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: EnderIO");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: EnderIO");
			} catch (Error e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: EnderIO");
			}
		}

		if (loadedUGB) {
			try {
				DCPluginUGB.init();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: UndergroundBiomes");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: UndergroundBiomes");
			} catch (Error e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: UndergroundBiomes");
			}
		}

		if (loadedRC) {
			try {
				DCPluginRailcraft.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: Railcraft");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: Railcraft");
			} catch (Error e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: Railcraft");
			}
		}

		if (loadedMCE) {
			try {
				DCPluginMCE.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: MCE");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: MCE");
			}
		}

		if (loadedTektopia) {
			try {
				DCPluginTektopia.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod post plugin: tektopia");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: tektopia");
			}
		}

		if (loadedSD) {
			try {
				DCPluginSD.loadInit();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: SimpleDifficulty");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: SimpleDifficulty");
			} catch (Error e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: SimpleDifficulty");
			}
		}

		DCPluginOres.loadInit();

	}

	public static void loadPost() {

		if (loadedJEI) {
			try {
				DCPluginJeiMain.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod init plugin: JEI");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: JEI");
			}
		}

		if (loadedForestry) {
			try {
				DCPluginForestry.loadInit();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod post plugin: forestry");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: forestry");
			}
		}

		if (loadedBC) {
			try {
				DCPluginBuildcraft.loadPost();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod post plugin: buildcraftenergy");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: buildcraftenergy");
			}
		}

		if (loadedMekanism) {
			try {
				DCPluginMekanism.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod post plugin: Mekanism");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: Mekanism");
			}
		}

		if (loadedTaN) {
			try {
				DCPluginTaN.loadInit();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod post plugin: ToughAsNails");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: ToughAsNails");
			}
		}

		if (loadedGVC) {
			try {
				DCPluginGVC.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod post plugin: hmggvc");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: hmggvc");
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
