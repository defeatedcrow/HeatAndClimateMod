package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class BreadRoundItem extends ItemEntityFood {

	public BreadRoundItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
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
		if (item == FoodInit.BREAD_NUTS_RAW_ITEM.get())
			return BREAD_NUTS_RAW;
		if (item == FoodInit.BREAD_NUTS_BAKED_ITEM.get())
			return BREAD_NUTS_BAKED;
		if (item == FoodInit.BREAD_CINNAMON_RAW_ITEM.get())
			return BREAD_CINNAMON_RAW;
		if (item == FoodInit.BREAD_CINNAMON_BAKED_ITEM.get())
			return BREAD_CINNAMON_BAKED;
		if (item == FoodInit.BREAD_ANKO_RAW_ITEM.get())
			return BREAD_ANKO_RAW;
		if (item == FoodInit.BREAD_ANKO_BAKED_ITEM.get())
			return BREAD_ANKO_BAKED;
		return BREAD_ROUND_RAW;
	}

	public static final EntityRenderData BREAD_ROUND_RAW = new EntityRenderData("food/bread_round_raw", 0.8F, 0F);
	public static final EntityRenderData BREAD_ROUND_BAKED = new EntityRenderData("food/bread_round_baked", 1.0F, 0F);
	public static final EntityRenderData BREAD_NUTS_RAW = new EntityRenderData("food/bread_nuts_raw", 0.8F, 0F);
	public static final EntityRenderData BREAD_NUTS_BAKED = new EntityRenderData("food/bread_nuts_baked", 1.0F, 0F);
	public static final EntityRenderData BREAD_CINNAMON_RAW = new EntityRenderData("food/bread_cinnamon_raw", 0.8F, 0F);
	public static final EntityRenderData BREAD_CINNAMON_BAKED = new EntityRenderData("food/bread_cinnamon_baked", 1.0F, 0F);
	public static final EntityRenderData BREAD_ANKO_RAW = new EntityRenderData("food/bread_anko_raw", 0.8F, 0F);
	public static final EntityRenderData BREAD_ANKO_BAKED = new EntityRenderData("food/bread_anko_baked", 1.0F, 0F);

}
