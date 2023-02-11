package defeatedcrow.hac.api.material;

import net.minecraft.world.item.ItemStack;

public interface IFoodTaste {

	/**
	 * 0~4 の5段階である
	 */
	int getTaste(ItemStack item);

	void setTaste(ItemStack item, int i);

	boolean isSeasoning();

}
