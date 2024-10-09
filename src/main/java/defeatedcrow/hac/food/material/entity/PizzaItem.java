package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PizzaItem extends ItemEntityFood {

	public PizzaItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.PIZZA.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PIZZA_TOMATO_RAW_ITEM.get())
			return PIZZA_TOMATO_RAW;
		if (item == FoodInit.PIZZA_TOMATO_BAKED_ITEM.get())
			return PIZZA_TOMATO_BAKED;
		if (item == FoodInit.PIZZA_MARGHERITA_RAW_ITEM.get())
			return PIZZA_MARGHERITA_RAW;
		if (item == FoodInit.PIZZA_MARGHERITA_BAKED_ITEM.get())
			return PIZZA_MARGHERITA_BAKED;
		if (item == FoodInit.PIZZA_ROMANA_RAW_ITEM.get())
			return PIZZA_ROMANA_RAW;
		if (item == FoodInit.PIZZA_ROMANA_BAKED_ITEM.get())
			return PIZZA_ROMANA_BAKED;
		if (item == FoodInit.PIZZA_TONNO_RAW_ITEM.get())
			return PIZZA_TONNO_RAW;
		if (item == FoodInit.PIZZA_TONNO_BAKED_ITEM.get())
			return PIZZA_TONNO_BAKED;
		if (item == FoodInit.PIZZA_SAUSAGE_RAW_ITEM.get())
			return PIZZA_SAUSAGE_RAW;
		if (item == FoodInit.PIZZA_SAUSAGE_BAKED_ITEM.get())
			return PIZZA_SAUSAGE_BAKED;
		if (item == FoodInit.PIZZA_MENTAI_RAW_ITEM.get())
			return PIZZA_MENTAI_RAW;
		if (item == FoodInit.PIZZA_MENTAI_BAKED_ITEM.get())
			return PIZZA_MENTAI_BAKED;
		return PIZZA_TOMATO_RAW;
	}

	public static final EntityRenderData PIZZA_TOMATO_RAW = new EntityRenderData("food/pizza_tomato_raw", 1.0F, 0F);
	public static final EntityRenderData PIZZA_TOMATO_BAKED = new EntityRenderData("food/pizza_tomato_baked", 1.0F, 0F);
	public static final EntityRenderData PIZZA_MARGHERITA_RAW = new EntityRenderData("food/pizza_margherita_raw", 1.0F, 0F);
	public static final EntityRenderData PIZZA_MARGHERITA_BAKED = new EntityRenderData("food/pizza_margherita_baked", 1.0F, 0F);
	public static final EntityRenderData PIZZA_ROMANA_RAW = new EntityRenderData("food/pizza_romana_raw", 1.0F, 0F);
	public static final EntityRenderData PIZZA_ROMANA_BAKED = new EntityRenderData("food/pizza_romana_baked", 1.0F, 0F);
	public static final EntityRenderData PIZZA_TONNO_RAW = new EntityRenderData("food/pizza_tonno_raw", 1.0F, 0F);
	public static final EntityRenderData PIZZA_TONNO_BAKED = new EntityRenderData("food/pizza_tonno_baked", 1.0F, 0F);
	public static final EntityRenderData PIZZA_SAUSAGE_RAW = new EntityRenderData("food/pizza_sausage_raw", 1.0F, 0F);
	public static final EntityRenderData PIZZA_SAUSAGE_BAKED = new EntityRenderData("food/pizza_sausage_baked", 1.0F, 0F);
	public static final EntityRenderData PIZZA_MENTAI_RAW = new EntityRenderData("food/pizza_mentai_raw", 1.0F, 0F);
	public static final EntityRenderData PIZZA_MENTAI_BAKED = new EntityRenderData("food/pizza_mentai_baked", 1.0F, 0F);

}
