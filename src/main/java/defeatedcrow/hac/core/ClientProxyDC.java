package defeatedcrow.hac.core;

import com.mojang.blaze3d.platform.InputConstants;

import defeatedcrow.hac.core.client.AdvTooltipEvent;
import defeatedcrow.hac.core.client.ClimateHUDEvent;
import defeatedcrow.hac.core.client.DCTextureStitch;
import defeatedcrow.hac.core.climate.ClientClimateData;
import defeatedcrow.hac.core.config.ConfigClientBuilder;
import defeatedcrow.hac.core.event.ClientTickEventDC;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxyDC extends CommonProxyDC {

	@Override
	public void registerEvent() {
		super.registerEvent();
		MinecraftForge.EVENT_BUS.addListener(ClientTickEventDC::onClientTick);
		MinecraftForge.EVENT_BUS.addListener(ClimateHUDEvent::render);
		MinecraftForge.EVENT_BUS.addListener(ClimateHUDEvent::renderScreen);
		MinecraftForge.EVENT_BUS.addListener(AdvTooltipEvent::render);
		MinecraftForge.EVENT_BUS.addListener(DCTextureStitch::register);
	}

	@Override
	public void commonInit() {
		super.commonInit();
	}

	@Override
	public void updatePlayerClimate() {
		ClientClimateData.INSTANCE.updatePlayerClimate();
	}

	@Override
	public boolean keyShiftPushed() {
		return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 340) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 344);
	}

	@Override
	public boolean keyHUDPushed() {
		return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), ConfigClientBuilder.INSTANCE.key_HUD.get());
	}

	@Override
	public boolean keyCharmPushed() {
		return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), ConfigClientBuilder.INSTANCE.key_Charm.get());
	}
}
