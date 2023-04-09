package defeatedcrow.hac.core.recipe.smelting.data;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

public interface IDummySmelting extends Recipe<Container> {
	@Override
	default net.minecraft.world.item.crafting.RecipeType<?> getType() {
		return CoreInit.SMELTING.get();
	}
}
