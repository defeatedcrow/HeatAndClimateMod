package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class StickVegiItem extends ItemEntityFood {

	public StickVegiItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.STICK_VEGI.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.STICK_VEGI_RAW.get())
			return VEGI_RAW;
		if (item == FoodInit.STICK_VEGI_COOKED.get())
			return VEGI_COOKED;
		return VEGI_RAW;
	}

	public static final EntityRenderData VEGI_RAW = new EntityRenderData("food/stick_vegi_raw", 1F, 0F);
	public static final EntityRenderData VEGI_COOKED = new EntityRenderData("food/stick_vegi_cooked", 1F, 0F);

}
