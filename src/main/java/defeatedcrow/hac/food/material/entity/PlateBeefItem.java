package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PlateBeefItem extends ItemEntityFood {

	public PlateBeefItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.PLATE_STEAK.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PLATE_BEEF_RAW.get())
			return BEEF_RAW;
		if (item == FoodInit.PLATE_BEEF_COOKED.get())
			return BEEF_COOKED;
		return BEEF_RAW;
	}

	public static final EntityRenderData BEEF_RAW = new EntityRenderData("food/plate_beef_raw", 1F, 0F);
	public static final EntityRenderData BEEF_COOKED = new EntityRenderData("food/plate_beef_cooked", 1F, 0F);

}
