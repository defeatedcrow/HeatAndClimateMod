package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class BreadSquareItem extends ItemEntityFood {

	public BreadSquareItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.BREAD_SQUARE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.BREAD_SQUARE_RAW_ITEM.get())
			return BREAD_SQUARE_RAW;
		if (item == FoodInit.BREAD_SQUARE_BAKED_ITEM.get())
			return BREAD_SQUARE_BAKED;
		return BREAD_SQUARE_RAW;
	}

	public static final EntityRenderData BREAD_SQUARE_RAW = new EntityRenderData("food/bread_square_raw", 0.8F, 0F);
	public static final EntityRenderData BREAD_SQUARE_BAKED = new EntityRenderData("food/bread_square_baked", 1.0F, 0F);

}
