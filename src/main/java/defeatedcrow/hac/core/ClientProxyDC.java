package defeatedcrow.hac.core;

import com.mojang.blaze3d.platform.InputConstants;

import defeatedcrow.hac.core.client.AdvTooltipEvent;
import defeatedcrow.hac.core.client.ClimateHUDEvent;
import defeatedcrow.hac.core.client.DCTextureStitch;
import defeatedcrow.hac.core.client.RenderPlayerEventDC;
import defeatedcrow.hac.core.climate.ClientClimateData;
import defeatedcrow.hac.core.config.ConfigClientBuilder;
import defeatedcrow.hac.core.event.ClientTickEventDC;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxyDC extends CommonProxyDC {

	@Override
	public void registerEvent() {
		super.registerEvent();
		MinecraftForge.EVENT_BUS.addListener(ClientTickEventDC::onClientTick);
		MinecraftForge.EVENT_BUS.addListener(ClimateHUDEvent::render);
		MinecraftForge.EVENT_BUS.addListener(ClimateHUDEvent::renderScreen);
		MinecraftForge.EVENT_BUS.addListener(RenderPlayerEventDC::renderWings);
		MinecraftForge.EVENT_BUS.addListener(AdvTooltipEvent::render);
		MinecraftForge.EVENT_BUS.addListener(DCTextureStitch::register);
	}

	@Override
	public void commonInit() {
		super.commonInit();

		ItemProperties.register(CoreInit.HARPOON_FLINT.get(), new ResourceLocation("throwing"), (stack, level, living, i) -> {
			return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F;
		});
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

	@SuppressWarnings("resource")
	@Override
	public boolean keyJumpPushed() {
		return Minecraft.getInstance().player.input.jumping;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean keySneakPushed() {
		return Minecraft.getInstance().player.input.shiftKeyDown;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean keyFowardPushed() {
		return Minecraft.getInstance().player.input.hasForwardImpulse();
	}

	@SuppressWarnings("resource")
	@Override
	public Vec2 getClientFoward() {
		return Minecraft.getInstance().player.input.getMoveVector();
	}

	// @Override
	// public void triggerAdvancement(LivingEntity player, String res) {}
}
