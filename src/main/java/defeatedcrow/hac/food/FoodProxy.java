package defeatedcrow.hac.food;

import defeatedcrow.hac.core.event.HaCDispenseItemBehavior;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.food.event.ClickEventDC;
import defeatedcrow.hac.food.event.CraftingFoodEvent;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import defeatedcrow.hac.food.recipe.BrewingRecipeDC;
import defeatedcrow.hac.food.recipe.PlantRecipes;
import defeatedcrow.hac.food.worldgen.TargetCropList;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.RegistryObject;

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
		registerDispenser();
	}

	static void registerDispenser() {
		CoreInit.ITEMS.getEntries().stream().filter(item -> item.get() instanceof ItemEntityFood)
				.map(RegistryObject::get).forEach(i -> {
					DispenserBlock.registerBehavior(i, new HaCDispenseItemBehavior());
				});
	}

}
