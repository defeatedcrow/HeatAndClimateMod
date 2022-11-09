package defeatedcrow.hac.food;

import defeatedcrow.hac.food.event.ClickBlockEventDC;
import defeatedcrow.hac.food.worldgen.WildCropFeature;
import net.minecraftforge.common.MinecraftForge;

public class FoodProxy {

	public static void registerEvent() {
		MinecraftForge.EVENT_BUS.addListener(ClickBlockEventDC::onClickBlock);
	}

	public static void commonInit() {
		WildCropFeature.init();
	}

}
