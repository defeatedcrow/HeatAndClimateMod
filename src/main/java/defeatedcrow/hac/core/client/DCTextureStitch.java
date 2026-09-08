package defeatedcrow.hac.core.client;

import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DCTextureStitch {

	@SubscribeEvent
	public static void register(TextureStitchEvent event) {
		// 1.20.1ではTextureStitchEventからaddSpriteが削除されたため、GUIテクスチャの事前登録は行わない(必要時に自動読込される)
	}

}
