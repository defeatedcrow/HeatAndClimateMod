package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class TartItem extends ItemEntityFood {

	public TartItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.TART.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.TART_APPLE_RAW_ITEM.get())
			return TART_APPLE_RAW;
		if (item == FoodInit.TART_APPLE_BAKED_ITEM.get())
			return TART_APPLE_BAKED;
		if (item == FoodInit.TART_PEACH_RAW_ITEM.get())
			return TART_PEACH_RAW;
		if (item == FoodInit.TART_PEACH_BAKED_ITEM.get())
			return TART_PEACH_BAKED;
		if (item == FoodInit.TART_BERRY_RAW_ITEM.get())
			return TART_BERRY_RAW;
		if (item == FoodInit.TART_BERRY_BAKED_ITEM.get())
			return TART_BERRY_BAKED;
		if (item == FoodInit.TART_LEMON_RAW_ITEM.get())
			return TART_LEMON_RAW;
		if (item == FoodInit.TART_LEMON_BAKED_ITEM.get())
			return TART_LEMON_BAKED;
		if (item == FoodInit.TART_COCOA_RAW_ITEM.get())
			return TART_COCOA_RAW;
		if (item == FoodInit.TART_COCOA_BAKED_ITEM.get())
			return TART_COCOA_BAKED;
		if (item == FoodInit.TART_PISTACHIO_RAW_ITEM.get())
			return TART_PISTACHIO_RAW;
		if (item == FoodInit.TART_PISTACHIO_BAKED_ITEM.get())
			return TART_PISTACHIO_BAKED;
		if (item == FoodInit.TART_QUICHE_RAW_ITEM.get())
			return TART_QUICHE_RAW;
		if (item == FoodInit.TART_QUICHE_BAKED_ITEM.get())
			return TART_QUICHE_BAKED;
		return TART_APPLE_RAW;
	}

	public static final EntityRenderData TART_APPLE_RAW = new EntityRenderData("food/tart_apple_raw", 1.0F, 0F);
	public static final EntityRenderData TART_APPLE_BAKED = new EntityRenderData("food/tart_apple_baked", 1.0F, 0F);
	public static final EntityRenderData TART_PEACH_RAW = new EntityRenderData("food/tart_peach_raw", 1.0F, 0F);
	public static final EntityRenderData TART_PEACH_BAKED = new EntityRenderData("food/tart_peach_baked", 1.0F, 0F);
	public static final EntityRenderData TART_BERRY_RAW = new EntityRenderData("food/tart_berry_raw", 1.0F, 0F);
	public static final EntityRenderData TART_BERRY_BAKED = new EntityRenderData("food/tart_berry_baked", 1.0F, 0F);
	public static final EntityRenderData TART_LEMON_RAW = new EntityRenderData("food/tart_lemon_raw", 1.0F, 0F);
	public static final EntityRenderData TART_LEMON_BAKED = new EntityRenderData("food/tart_lemon_baked", 1.0F, 0F);
	public static final EntityRenderData TART_COCOA_RAW = new EntityRenderData("food/tart_cocoa_raw", 1.0F, 0F);
	public static final EntityRenderData TART_COCOA_BAKED = new EntityRenderData("food/tart_cocoa_baked", 1.0F, 0F);
	public static final EntityRenderData TART_PISTACHIO_RAW = new EntityRenderData("food/tart_pistachio_raw", 1.0F, 0F);
	public static final EntityRenderData TART_PISTACHIO_BAKED = new EntityRenderData("food/tart_pistachio_baked", 1.0F, 0F);
	public static final EntityRenderData TART_QUICHE_RAW = new EntityRenderData("food/tart_quiche_raw", 1.0F, 0F);
	public static final EntityRenderData TART_QUICHE_BAKED = new EntityRenderData("food/tart_quiche_baked", 1.0F, 0F);

}
