package defeatedcrow.hac.main;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import defeatedcrow.hac.core.ClimateCore;

@Mod(
		modid = ClimateMain.MOD_ID,
		name = ClimateMain.MOD_NAME,
		version = ClimateCore.MOD_MEJOR + "." + ClimateCore.MOD_MINOR + "." + ClimateCore.MOD_BUILD,
		dependencies = ClimateCore.MOD_DEPENDENCIES,
		acceptedMinecraftVersions = ClimateCore.MOD_ACCEPTED_MC_VERSIONS,
		useMetadata = true)
public class ClimateMain {
	public static final String MOD_ID = "dcs_climate";
	public static final String MOD_NAME = "ClimateAndHeatMod";

	@SidedProxy(
			clientSide = "defeatedcrow.hac.main.client.ClientMainProxy",
			serverSide = "defeatedcrow.hac.main.CommonMainProxy")
	public static CommonMainProxy proxy;

	@Instance("dcs_climate")
	public static ClimateMain instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.loadMaterial();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.loadTE();
		proxy.loadWorldGen();
		proxy.loadRecipes();
	}

}
