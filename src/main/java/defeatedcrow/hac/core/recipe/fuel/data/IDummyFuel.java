package defeatedcrow.hac.core.recipe.fuel.data;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

public interface IDummyFuel extends Recipe<Container> {
	@Override
	default net.minecraft.world.item.crafting.RecipeType<?> getType() {
		return CoreInit.DEVICE_FUEL.get();
	}
}
