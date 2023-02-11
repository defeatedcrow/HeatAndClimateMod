package defeatedcrow.hac.core.client;

import defeatedcrow.hac.core.util.DCTexturePath;
import net.minecraftforge.client.event.TextureStitchEvent;

public class DCTextureStitch {

	public static void register(TextureStitchEvent.Pre event) {
		event.addSprite(DCTexturePath.HUD.getLocation());
		event.addSprite(DCTexturePath.HOT_DISP.getLocation());
		event.addSprite(DCTexturePath.COLD_DISP.getLocation());
		event.addSprite(DCTexturePath.POTION.getLocation());
	}

}
