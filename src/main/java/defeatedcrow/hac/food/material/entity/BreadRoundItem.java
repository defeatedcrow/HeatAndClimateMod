package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class BreadRoundItem extends ItemEntityFood {

	public BreadRoundItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.BREAD_ROUND.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.BREAD_ROUND_RAW_ITEM.get())
			return BREAD_ROUND_RAW;
		if (item == FoodInit.BREAD_ROUND_BAKED_ITEM.get())
			return BREAD_ROUND_BAKED;
		return BREAD_ROUND_RAW;
	}

	public static final EntityRenderData BREAD_ROUND_RAW = new EntityRenderData("bread_round_raw", 0.8F, 0F);
	public static final EntityRenderData BREAD_ROUND_BAKED = new EntityRenderData("bread_round_baked", 1.0F, 0F);

}
