package defeatedcrow.hac.core.material.item.tool;

import java.util.function.Supplier;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.tag.TagDC;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AgateMortarItem extends CraftingItemDC {

	public AgateMortarItem(String n) {
		super(n, new Item.Properties().tab(CoreInit.MACHINE).stacksTo(1), TagDC.ItemTag.CRAFT_MORTAR);
	}

	static Supplier<Item> getSup() {
		return () -> CoreInit.MORTAR.get();
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
		return new ItemStack(getSup().get());
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}

}
