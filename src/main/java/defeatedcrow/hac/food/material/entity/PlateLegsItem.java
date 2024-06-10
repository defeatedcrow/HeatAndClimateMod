package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PlateLegsItem extends ItemEntityFood {

	public PlateLegsItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.PLATE_LEGS.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PLATE_LEGS_RAW.get())
			return LEGS_RAW;
		if (item == FoodInit.PLATE_LEGS_COOKED.get())
			return LEGS_COOKED;
		return LEGS_RAW;
	}

	public static final EntityRenderData LEGS_RAW = new EntityRenderData("food/plate_legs_raw", 1F, 0F);
	public static final EntityRenderData LEGS_COOKED = new EntityRenderData("food/plate_legs_cooked", 1F, 0F);

}
