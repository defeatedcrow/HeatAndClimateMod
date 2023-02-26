package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class BreadCreamItem extends ItemEntityFood {

	public BreadCreamItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.BREAD_CREAM.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.BREAD_CREAM_RAW_ITEM.get())
			return BREAD_CREAM_RAW;
		if (item == FoodInit.BREAD_CREAM_BAKED_ITEM.get())
			return BREAD_CREAM_BAKED;
		return BREAD_CREAM_RAW;
	}

	public static final EntityRenderData BREAD_CREAM_RAW = new EntityRenderData("food/bread_cream_raw", 0.8F, 0F);
	public static final EntityRenderData BREAD_CREAM_BAKED = new EntityRenderData("food/bread_cream_baked", 1.0F, 0F);

}
