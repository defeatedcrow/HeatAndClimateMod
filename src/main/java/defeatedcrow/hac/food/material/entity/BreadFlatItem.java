package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class BreadFlatItem extends ItemEntityFood {

	public BreadFlatItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.BREAD_FLAT.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.BREAD_FLAT_RAW_ITEM.get())
			return BREAD_FLAT_RAW;
		if (item == FoodInit.BREAD_FLAT_BAKED_ITEM.get())
			return BREAD_FLAT_BAKED;
		if (item == FoodInit.BREAD_TORTILLA_RAW_ITEM.get())
			return BREAD_TORTILLA_RAW;
		if (item == FoodInit.BREAD_TORTILLA_BAKED_ITEM.get())
			return BREAD_TORTILLA_BAKED;
		return BREAD_FLAT_RAW;
	}

	public static final EntityRenderData BREAD_FLAT_RAW = new EntityRenderData("food/bread_flat_raw", 0.8F, 0F);
	public static final EntityRenderData BREAD_FLAT_BAKED = new EntityRenderData("food/bread_flat_baked", 1.0F, 0F);
	public static final EntityRenderData BREAD_TORTILLA_RAW = new EntityRenderData("food/bread_tortilla_raw", 0.8F, 0F);
	public static final EntityRenderData BREAD_TORTILLA_BAKED = new EntityRenderData("food/bread_tortilla_baked", 1.0F, 0F);

}
