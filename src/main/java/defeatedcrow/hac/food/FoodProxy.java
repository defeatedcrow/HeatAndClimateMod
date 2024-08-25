package defeatedcrow.hac.food;

import defeatedcrow.hac.food.event.ClickEventDC;
import defeatedcrow.hac.food.event.CraftingFoodEvent;
import defeatedcrow.hac.food.recipe.BrewingRecipeDC;
import defeatedcrow.hac.food.recipe.PlantRecipes;
import defeatedcrow.hac.food.worldgen.TargetCropList;
import net.minecraftforge.common.MinecraftForge;

public class FoodProxy {

	public static void registerEvent() {
		MinecraftForge.EVENT_BUS.addListener(ClickEventDC::onClickBlock);
		MinecraftForge.EVENT_BUS.addListener(ClickEventDC::onClickEntity);
		MinecraftForge.EVENT_BUS.addListener(CraftingFoodEvent::onCraft);

	}

	public static void commonInit() {
		TargetCropList.INSTANCE.init();
		BrewingRecipeDC.INSTANCE.init();
		PlantRecipes.addCompostables();
	}

}
