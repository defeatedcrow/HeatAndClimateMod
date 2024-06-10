package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PlateMeatItem extends ItemEntityFood {

	public PlateMeatItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.PLATE_MEAT.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PLATE_MEAT_RAW.get())
			return MEAT_RAW;
		if (item == FoodInit.PLATE_MEAT_COOKED.get())
			return MEAT_COOKED;
		return MEAT_RAW;
	}

	public static final EntityRenderData MEAT_RAW = new EntityRenderData("food/plate_meat_raw", 1F, 0F);
	public static final EntityRenderData MEAT_COOKED = new EntityRenderData("food/plate_meat_cooked", 1F, 0F);

}
