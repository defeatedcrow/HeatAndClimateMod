package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class StickCornItem extends ItemEntityFood {

	public StickCornItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.STICK_CORN.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.STICK_CORN_RAW.get())
			return CORN_RAW;
		if (item == FoodInit.STICK_CORN_COOKED.get())
			return CORN_COOKED;
		return CORN_RAW;
	}

	public static final EntityRenderData CORN_RAW = new EntityRenderData("food/stick_corn_raw", 1F, 0F);
	public static final EntityRenderData CORN_COOKED = new EntityRenderData("food/stick_corn_cooked", 1F, 0F);

}
