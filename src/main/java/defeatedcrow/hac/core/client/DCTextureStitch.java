package defeatedcrow.hac.core.client;

import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DCTextureStitch {

	@SubscribeEvent
	public static void register(TextureStitchEvent.Pre event) {
		event.addSprite(DCTexturePath.HUD.getLocation());
		event.addSprite(DCTexturePath.HOT_DISP.getLocation());
		event.addSprite(DCTexturePath.COLD_DISP.getLocation());
		event.addSprite(DCTexturePath.POTION.getLocation());
		event.addSprite(DCTexturePath.GUI_INV_SINGLE.getLocation());
		event.addSprite(DCTexturePath.GUI_INV_DOUBLE.getLocation());
		event.addSprite(DCTexturePath.GUI_CHAMBER_ITEM.getLocation());
	}

}
