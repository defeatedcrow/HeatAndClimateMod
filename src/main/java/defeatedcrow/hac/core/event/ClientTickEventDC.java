package defeatedcrow.hac.core.event;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.config.ConfigClientBuilder;
import defeatedcrow.hac.core.network.packet.message.MsgCharmKeyToS;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class ClientTickEventDC {

	public static int i = 10;

	private static boolean releaseHUDKey = true;

	private static boolean releaseCharmKey = true;

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.side == LogicalSide.CLIENT) {
			if (i <= 0) {
				i = 10;
				ClimateCore.proxy.updatePlayerClimate();
			} else {
				i--;
			}

			if (ClimateCore.proxy.keyHUDPushed()) {
				if (!releaseHUDKey) {
					onHUDKeyPushed();
					releaseHUDKey = true;
				}
			} else {
				releaseHUDKey = false;
			}

			if (ClimateCore.proxy.keyCharmPushed()) {
				if (!releaseCharmKey) {
					onCharmKeyPushed();
					releaseCharmKey = true;
				}
			} else {
				releaseCharmKey = false;
			}

		}
	}

	private static void onHUDKeyPushed() {
		// GUI展開中は切り替えしない
		if (Minecraft.getInstance().screen == null) {
			ConfigClientBuilder.INSTANCE.HUD_type++;
			if (ConfigClientBuilder.INSTANCE.HUD_type > 4) {
				ConfigClientBuilder.INSTANCE.HUD_type = 0;
			}
		}
	}

	private static void onCharmKeyPushed() {
		MsgCharmKeyToS.sendToServer(Minecraft.getInstance().player);
	}

}
