package defeatedcrow.hac.core;

import defeatedcrow.hac.core.config.ConfigLoadEventDC;
import defeatedcrow.hac.core.config.CoreConfigDC;
import defeatedcrow.hac.core.event.BiomeBaseTempEventDC;
import defeatedcrow.hac.core.event.BlockUpdateEventDC;
import defeatedcrow.hac.core.event.LivingTickEventDC;
import defeatedcrow.hac.core.event.ServerTickEventDC;
import defeatedcrow.hac.core.json.JsonInit;
import defeatedcrow.hac.core.recipe.smelting.ClimateSmeltingConfig;
import defeatedcrow.hac.core.recipe.smelting.ClimateSmeltingList;
import defeatedcrow.hac.food.FoodProxy;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxyDC {

	public void registerEvent() {
		MinecraftForge.EVENT_BUS.addListener(BiomeBaseTempEventDC::onTemp);
		MinecraftForge.EVENT_BUS.addListener(ServerTickEventDC::onTickEvent);
		MinecraftForge.EVENT_BUS.addListener(LivingTickEventDC::onLivingTick);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onBlockUpdate);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onCropUpdate);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onBlockPlacement);
		MinecraftForge.EVENT_BUS.addListener(ConfigLoadEventDC::onLoad);
		MinecraftForge.EVENT_BUS.addListener(ConfigLoadEventDC::onFileChange);

		FoodProxy.registerEvent();
	}

	public void commonInit() {
		JsonInit.init();
		CoreConfigDC.loadConfig();

		FoodProxy.commonInit();
	}

	public void updatePlayerClimate() {}

	public void registerRecipes() {
		ClimateSmeltingList.init();

		ClimateSmeltingConfig.loadFiles();
		ClimateSmeltingConfig.initFile();
	};

	public boolean keyShiftPushed() {
		return false;
	}

	public boolean keyHUDPushed() {
		return false;
	}

	public boolean keyCharmPushed() {
		return false;
	}

}
