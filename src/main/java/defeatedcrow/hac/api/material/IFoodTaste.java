package defeatedcrow.hac.api.material;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public interface IFoodTaste {

	/**
	 * -2~2 の5段階である
	 */
	int getTaste(ItemStack item);

	void setTaste(ItemStack item, int i);

	boolean isSeasoning();

	public static int getFoodTaste(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return 0;
		if (item.getItem() instanceof IFoodTaste food) {
			return food.getTaste(item);
		} else if (item.getTag() != null && item.getTag().contains(TagKeyDC.TASTE)) {
			int taste = item.getTag().getInt(TagKeyDC.TASTE);
			taste = Mth.clamp(taste, -2, 2);
			return taste;
		} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR)) {
			if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR5)) {
				return 2;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR4)) {
				return 1;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR3)) {
				return 0;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR2)) {
				return -1;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR1)) {
				return -2;
			}
		}
		return 0;
	}

}
