package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PlateChickenItem extends ItemEntityFood {

	public PlateChickenItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.PLATE_CHICKEN.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PLATE_CHICKEN_BIG_RAW.get())
			return CHICKEN_BIG_RAW;
		if (item == FoodInit.PLATE_CHICKEN_BIG_COOKED.get())
			return CHICKEN_BIG_COOKED;
		return CHICKEN_BIG_RAW;
	}

	public static final EntityRenderData CHICKEN_BIG_RAW = new EntityRenderData("food/plate_big_chicken_raw", 1F, 0F);
	public static final EntityRenderData CHICKEN_BIG_COOKED = new EntityRenderData("food/plate_big_chicken_cooked", 1F, 0F);

}
