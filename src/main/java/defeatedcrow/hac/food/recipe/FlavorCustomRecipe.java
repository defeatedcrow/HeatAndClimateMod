package defeatedcrow.hac.food.recipe;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class FlavorCustomRecipe extends CustomRecipe {

	public FlavorCustomRecipe(ResourceLocation res) {
		super(res);
	}

	@Override
	public boolean matches(CraftingContainer cont, Level level) {
		List<ItemStack> list = Lists.newArrayList();
		ItemStack meal = ItemStack.EMPTY;
		ItemStack seasoning = ItemStack.EMPTY;

		for (int i = 0; i < cont.getContainerSize(); ++i) {
			ItemStack stack = cont.getItem(i);
			if (!stack.isEmpty()) {
				if (stack.is(TagDC.ItemTag.HAC_SEASONING)) {
					list.add(stack);
					seasoning = stack.copy();
				} else if (stack.getItem() instanceof IFoodTaste) {
					list.add(stack);
					meal = stack.copy();
				}
			}

		}

		return !meal.isEmpty() && !seasoning.isEmpty() && list.size() == 2;
	}

	@Override
	public ItemStack assemble(CraftingContainer cont) {
		List<ItemStack> list = Lists.newArrayList();
		ItemStack meal = ItemStack.EMPTY;
		ItemStack seasoning = ItemStack.EMPTY;

		for (int i = 0; i < cont.getContainerSize(); ++i) {
			ItemStack stack = cont.getItem(i);
			if (!stack.isEmpty()) {
				if (stack.is(TagDC.ItemTag.HAC_SEASONING)) {
					list.add(stack);
					seasoning = stack.copy();
				} else if (stack.getItem() instanceof IFoodTaste) {
					list.add(stack);
					meal = stack.copy();
				}
			}

		}

		if (!meal.isEmpty() && !seasoning.isEmpty() && list.size() == 2) {
			IFoodTaste food = (IFoodTaste) meal.getItem();
			food.setTaste(meal, 2);
			return meal;
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public boolean canCraftInDimensions(int x, int z) {
		return x * z >= 2;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return CoreInit.FLAVOR_SEREALIZER.get();
	}

}
