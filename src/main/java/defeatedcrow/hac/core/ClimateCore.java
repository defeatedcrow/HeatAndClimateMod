package defeatedcrow.hac.core;

import java.io.File;
import java.nio.file.Path;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.core.client.DCTextureStitch;
import defeatedcrow.hac.core.climate.ClimateCalculator;
import defeatedcrow.hac.core.climate.ClimateHUDEvent;
import defeatedcrow.hac.core.climate.ClimateHelper;
import defeatedcrow.hac.core.climate.register.BiomeClimateRegister;
import defeatedcrow.hac.core.climate.register.BlockClimateRegister;
import defeatedcrow.hac.core.event.ClimateTickEvent;
import defeatedcrow.hac.core.network.packet.DCPacket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(ClimateCore.MOD_ID)
public class ClimateCore {
	public static final String MOD_ID = "dcs_climate";
	public static final String MOD_NAME = "HeatAndClimateMod";
	public static final int MOD_MAJOR = 4;
	public static final int MOD_MINOR = 0;
	public static final int MOD_BUILD = 0;
	public static final String MOD_DEPENDENCIES = "";
	public static final String MOD_ACCEPTED_MC_VERSIONS = "[1.19, 1.19.2]";
	public static final String PACKAGE_BASE = "dcs";
	public static final String PACKAGE_ID = "dcs_climate";
	public static final String UPDATE_JSON = "https://defeatedcrow.jp/version/heatandclimate.json";

	public static final CommonProxyDC proxy =
			DistExecutor.unsafeRunForDist(() -> ClientProxyDC::new, () -> CommonProxyDC::new);

	public static File configDir = null;
	public static File assetsDir = null;

	public static boolean isDebug = true;

	public ClimateCore() {
		initAPI();
		configDir = new File(FMLPaths.CONFIGDIR.get().toFile() + "defeatedcrow/climate");
		BlockClimateRegister.setDir(configDir);
		if (isDebug) {
			Path path = FMLPaths.GAMEDIR.get();
			DCLogger.debugInfoLog("path test: " + path);
			if (path.toString().contains("run")) {
				path = path.normalize();
				assetsDir = new File(path.toFile() + "/src/main/resources/assets/json_sample/");
			}
		}

		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);

		proxy.registerEvent();
	}

	void initAPI() {
		ClimateAPI.calculator = new ClimateCalculator();
		ClimateAPI.helper = new ClimateHelper();
		ClimateAPI.registerBiome = BiomeClimateRegister.INSTANCE;
		ClimateAPI.registerBlock = new BlockClimateRegister();
	}

	public void commonSetup(FMLCommonSetupEvent event) {
		DCPacket.INSTANCE.init();
	}

	public void clientSetup(FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.addListener(ClimateTickEvent::onClientTick);
		MinecraftForge.EVENT_BUS.addListener(ClimateHUDEvent::render);
		MinecraftForge.EVENT_BUS.addListener(DCTextureStitch::register);
	}
}
