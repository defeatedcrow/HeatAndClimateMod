package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class DrinkCappuccinoItem extends DrinkCupItem {

	public DrinkCappuccinoItem(String s, int nut, float sat, int id, boolean mi, TagKey<Item> pair) {
		super(s, nut, sat, id, mi, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.CAPPUCCINO.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.COFFEE_LATTE.get())
			return LATTE;
		if (item == FoodInit.COFFEE_CAPPUCCINO.get())
			return CAPPUCCINO;
		if (item == FoodInit.COFFEE_CAPPUCCINO_NUTS.get())
			return NUTS;
		if (item == FoodInit.COFFEE_CAPPUCCINO_COCOA.get())
			return COCOA;
		if (item == FoodInit.COFFEE_CAPPUCCINO_SYRUP.get())
			return SYRUP;
		return LATTE;
	}

	public static final EntityRenderData LATTE = new EntityRenderData("food/cup_caffe_latte", 0.5F, 0F);
	public static final EntityRenderData CAPPUCCINO = new EntityRenderData("food/cup_cappuccino", 0.5F, 0F);
	public static final EntityRenderData NUTS = new EntityRenderData("food/cup_cappuccino_nuts", 0.5F, 0F);
	public static final EntityRenderData COCOA = new EntityRenderData("food/cup_cappuccino_cocoa", 0.5F, 0F);
	public static final EntityRenderData SYRUP = new EntityRenderData("food/cup_cappuccino_syrup", 0.5F, 0F);

}
