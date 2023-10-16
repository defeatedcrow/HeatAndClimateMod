package defeatedcrow.hac.core.recipe.device.data;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

public interface IDummyDeviceRecipe extends Recipe<Container> {
	@Override
	default net.minecraft.world.item.crafting.RecipeType<?> getType() {
		return CoreInit.DEVICE_RECIPE.get();
	}
}
