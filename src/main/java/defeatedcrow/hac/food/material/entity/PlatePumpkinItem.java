package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PlatePumpkinItem extends ItemEntityFood {

	public PlatePumpkinItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.PLATE_PUMPKIN.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PLATE_PUMPKIN_RAW.get())
			return PUMPKIN_RAW;
		if (item == FoodInit.PLATE_PUMPKIN_COOKED.get())
			return PUMPKIN_COOKED;
		return PUMPKIN_RAW;
	}

	public static final EntityRenderData PUMPKIN_RAW = new EntityRenderData("food/plate_pumpkin_raw", 1F, 0F);
	public static final EntityRenderData PUMPKIN_COOKED = new EntityRenderData("food/plate_pumpkin_cooked", 1F, 0F);

}
