package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class SandwichItem extends ItemEntityFood {

	public SandwichItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.SANDWICH.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.SANDWICH_FRUIT_ITEM.get())
			return FRUIT;
		if (item == FoodInit.SANDWICH_MARMALADE_ITEM.get())
			return MARMALADE;
		if (item == FoodInit.SANDWICH_EGG_ITEM.get())
			return EGG;
		if (item == FoodInit.SANDWICH_SALAD_ITEM.get())
			return SALAD;
		if (item == FoodInit.SANDWICH_SALMON_ITEM.get())
			return SALMON;
		return FRUIT;
	}

	public static final EntityRenderData FRUIT = new EntityRenderData("food/sandwich_fruit_jam", 0.6F, 0F);
	public static final EntityRenderData MARMALADE = new EntityRenderData("food/sandwich_marmalade", 0.6F, 0F);
	public static final EntityRenderData EGG = new EntityRenderData("food/sandwich_egg", 0.6F, 0F);
	public static final EntityRenderData SALAD = new EntityRenderData("food/sandwich_salad", 0.6F, 0F);
	public static final EntityRenderData SALMON = new EntityRenderData("food/sandwich_salmon", 0.6F, 0F);

}
