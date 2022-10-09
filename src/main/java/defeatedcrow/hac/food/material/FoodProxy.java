package defeatedcrow.hac.food.material;

import defeatedcrow.hac.food.event.ClickBlockEventDC;
import net.minecraftforge.common.MinecraftForge;

public class FoodProxy {

	public static void registerEvent() {
		MinecraftForge.EVENT_BUS.addListener(ClickBlockEventDC::onClickBlock);
	}

}
