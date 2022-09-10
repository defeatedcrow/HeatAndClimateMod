package defeatedcrow.hac.core.client;

import defeatedcrow.hac.core.util.DCTexturePath;
import net.minecraftforge.client.event.TextureStitchEvent;

public class DCTextureStitch {

	public static void register(TextureStitchEvent.Pre event) {
		event.addSprite(DCTexturePath.HUD.getLocation());
	}

}
