package defeatedcrow.hac.food;

import defeatedcrow.hac.food.event.ClickEventDC;
import defeatedcrow.hac.food.event.CraftingFoodEvent;
import defeatedcrow.hac.food.event.LivingDropEventDC;
import defeatedcrow.hac.food.recipe.BrewingRecipeDC;
import defeatedcrow.hac.food.worldgen.WildCropFeature;
import net.minecraftforge.common.MinecraftForge;

public class FoodProxy {

	public static void registerEvent() {
		MinecraftForge.EVENT_BUS.addListener(ClickEventDC::onClickBlock);
		MinecraftForge.EVENT_BUS.addListener(ClickEventDC::onClickEntity);
		MinecraftForge.EVENT_BUS.addListener(CraftingFoodEvent::onCraft);
		MinecraftForge.EVENT_BUS.addListener(LivingDropEventDC::onDrop);
	}

	public static void commonInit() {
		WildCropFeature.init();
		BrewingRecipeDC.INSTANCE.init();
	}

}
