package defeatedcrow.hac.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
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
	public static final String MOD_NAME = "HeatAndClimateMod";

	@SidedProxy(
			clientSide = "defeatedcrow.hac.main.client.ClientMainProxy",
			serverSide = "defeatedcrow.hac.main.CommonMainProxy")
	public static CommonMainProxy proxy;

	@Instance("dcs_climate")
	public static ClimateMain instance;

	public static final CreativeTabs tool = new CreativeTabClimateTool(MOD_ID);

	@EventHandler
	public void construction(FMLConstructionEvent event) {
		// TextureStitch用のテクスチャパスのリストを作る
		proxy.loadConst();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// materialの登録
		proxy.loadMaterial();
		// TileEntity
		proxy.loadTE();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		// WorldGen
		proxy.loadWorldGen();
		// Recipes
		proxy.loadRecipes();
		// event
		proxy.loadInit();

		// other things
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
	}

}
