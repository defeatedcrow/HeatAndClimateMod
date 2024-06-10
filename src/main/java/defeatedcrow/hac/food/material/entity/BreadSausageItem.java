package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class BreadSausageItem extends ItemEntityFood {

	public BreadSausageItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.BREAD_SAUSAGE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.BREAD_SAUSAGE_RAW_ITEM.get())
			return BREAD_SAUSAGE_RAW;
		if (item == FoodInit.BREAD_SAUSAGE_BAKED_ITEM.get())
			return BREAD_SAUSAGE_BAKED;
		return BREAD_SAUSAGE_RAW;
	}

	public static final EntityRenderData BREAD_SAUSAGE_RAW = new EntityRenderData("food/bread_sausage_raw", 0.8F, 0F);
	public static final EntityRenderData BREAD_SAUSAGE_BAKED = new EntityRenderData("food/bread_sausage_baked", 1.0F, 0F);

}
