package defeatedcrow.hac.core.recipe;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class ClearEnchantmrntRecipe extends CustomRecipe {

	public ClearEnchantmrntRecipe(ResourceLocation res) {
		super(res);
	}

	@Override
	public boolean matches(CraftingContainer cont, Level level) {
		List<ItemStack> list = Lists.newArrayList();
		ItemStack tool = ItemStack.EMPTY;
		ItemStack soap = ItemStack.EMPTY;

		for (int i = 0; i < cont.getContainerSize(); ++i) {
			ItemStack stack = cont.getItem(i);
			if (!stack.isEmpty()) {
				if (stack.is(TagDC.ItemTag.SOAP_MAGIC)) {
					list.add(stack);
					soap = stack.copy();
				} else if (stack.isEnchanted()) {
					list.add(stack);
					tool = stack.copy();
				}
			}
		}

		return !tool.isEmpty() && !soap.isEmpty() && list.size() == 2;
	}

	@Override
	public ItemStack assemble(CraftingContainer cont) {
		List<ItemStack> list = Lists.newArrayList();
		ItemStack tool = ItemStack.EMPTY;
		ItemStack soap = ItemStack.EMPTY;

		for (int i = 0; i < cont.getContainerSize(); ++i) {
			ItemStack stack = cont.getItem(i);
			if (!stack.isEmpty()) {
				if (stack.is(TagDC.ItemTag.SOAP_MAGIC)) {
					list.add(stack);
					soap = stack.copy();
				} else if (stack.isEnchanted()) {
					list.add(stack);
					tool = stack.copy();
				}
			}
		}

		if (!tool.isEmpty() && !soap.isEmpty() && list.size() == 2) {
			ItemStack ret = tool.copy();
			ret.removeTagKey("Enchantments");
			return ret;
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
		return CoreInit.CLEAR_ENCHANTMENT_SEREALIZER.get();
	}

}
