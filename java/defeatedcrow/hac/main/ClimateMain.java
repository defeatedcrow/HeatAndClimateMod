/**
 * Copyright (c) defeatedcrow, 2016
 * URL:http://defeatedcrow.jp/modwiki/Mainpage
 * defeatedcrow's mods are distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL.
 * Please check the License.txt included in the package file of this Mod.
 */

package defeatedcrow.hac.main;

import java.util.Calendar;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.main.achievement.AchievementClimate;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.MainConfig;
import defeatedcrow.hac.main.recipes.DCFluidFuelRegister;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = ClimateMain.MOD_ID, name = ClimateMain.MOD_NAME, version = ClimateMain.MOD_MEJOR + "."
		+ ClimateMain.MOD_MINOR + "."
		+ ClimateMain.MOD_BUILD, dependencies = ClimateMain.MOD_DEPENDENCIES, acceptedMinecraftVersions = ClimateCore.MOD_ACCEPTED_MC_VERSIONS, useMetadata = true)
public class ClimateMain {
	public static final String MOD_ID = "dcs_climate";
	public static final String MOD_NAME = "HeatAndClimateMod";
	public static final int MOD_MEJOR = 1;
	public static final int MOD_MINOR = 5;
	public static final int MOD_BUILD = 6;
	public static final String MOD_DEPENDENCIES = "required-after:Forge@[12.18.3.2185,);required-after:dcs_climate|lib@[1.5.6,)";

	@SidedProxy(clientSide = "defeatedcrow.hac.main.client.ClientMainProxy", serverSide = "defeatedcrow.hac.main.CommonMainProxy")
	public static CommonMainProxy proxy;

	@Instance("dcs_climate")
	public static ClimateMain instance;

	public static final CreativeTabs tool = new CreativeTabClimateTool(MOD_ID);
	public static final CreativeTabs machine = new CreativeTabClimateMachine(MOD_ID + "_machine");
	public static final CreativeTabs food = new CreativeTabClimateFood(MOD_ID + "_food");
	public static final CreativeTabs build = new CreativeTabClimateBuild(MOD_ID + "_build");

	public static final Calendar CAL = Calendar.getInstance();
	public static int month = 0;
	public static int day = 0;

	public static final String AUTHOR = "defeatedcrow";

	@EventHandler
	public void construction(FMLConstructionEvent event) {
		// TextureStitch
		proxy.loadConst();
		FluidRegistry.enableUniversalBucket();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// config
		MainConfig.INSTANCE.load(event.getModConfigurationDirectory());
		// api
		MainAPIManager.fuelRegister = new DCFluidFuelRegister();
		MainAPIManager.isLoaded = true;

		// integration
		DCIntegrationCore.loadedCheck();

		// Material
		proxy.loadMaterial();
		// TileEntity
		proxy.loadTE();
		// Entity
		proxy.loadEntity();
		// potion
		proxy.loadPotion();
		// achievement
		AchievementClimate.load();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		// WorldGen
		proxy.loadWorldGen();
		// event
		proxy.loadInit();

		// other things
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

		DCIntegrationCore.INSTANCE.loadIMC();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Recipes
		proxy.loadRecipes();
		DCIntegrationCore.INSTANCE.load();

		// date
		month = CAL.get(CAL.MONTH);
		day = CAL.get(CAL.DATE);
	}

}
