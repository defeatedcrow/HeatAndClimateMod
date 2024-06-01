package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class NoodleItem extends ItemEntityFood {

	public NoodleItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.NOODLE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PHAT_THAI_PRAWN.get())
			return PHAT_THAI_PRAWN;
		if (item == FoodInit.PHAT_THAI_CHICKEN.get())
			return PHAT_THAI_CHICKEN;
		return PHAT_THAI_PRAWN;
	}

	public static final EntityRenderData PHAT_THAI_PRAWN = new EntityRenderData("food/phat_thai_prawn", 0.85F, 0F);
	public static final EntityRenderData PHAT_THAI_CHICKEN = new EntityRenderData("food/phat_thai_chicken", 0.85F, 0F);

}
