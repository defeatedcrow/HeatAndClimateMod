package defeatedcrow.hac.core.config;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class ConfigLoadEventDC {

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		onFileLoad();
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
		onFileLoad();
	}

	static void onFileLoad() {
		ConfigCommonBuilder.INSTANCE.setDateFormat();
		ConfigCommonBuilder.INSTANCE.setSeasonOverYear();
	}

}
