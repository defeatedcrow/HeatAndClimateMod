package defeatedcrow.hac.core.client;

import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DCTextureStitch {

	@SubscribeEvent
	public static void register(TextureStitchEvent.Pre event) {
		for (DCTexturePath path : DCTexturePath.elements()) {
			event.addSprite(path.getLocation());
		}
	}

}
