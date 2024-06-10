package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PlateGarlicItem extends ItemEntityFood {

	public PlateGarlicItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.PLATE_BIG_STEAK.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PLATE_GARLIC_RAW.get())
			return GARLIC_RAW;
		if (item == FoodInit.PLATE_GARLIC_COOKED.get())
			return GARLIC_COOKED;
		return GARLIC_RAW;
	}

	public static final EntityRenderData GARLIC_RAW = new EntityRenderData("food/plate_big_garlic_raw", 1F, 0F);
	public static final EntityRenderData GARLIC_COOKED = new EntityRenderData("food/plate_big_garlic_cooked", 1F, 0F);

}
