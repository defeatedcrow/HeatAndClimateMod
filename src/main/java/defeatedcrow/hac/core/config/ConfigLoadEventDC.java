package defeatedcrow.hac.core.config;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class ConfigLoadEventDC {

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading event) {
		if (event.getConfig().getType() == Type.SERVER)
			onFileLoad();
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading event) {
		if (event.getConfig().getType() == Type.SERVER)
			onFileLoad();
	}

	static void onFileLoad() {
		ConfigServerBuilder.INSTANCE.setDateFormat();
		ConfigServerBuilder.INSTANCE.setSeasonOverYear();
	}

}
