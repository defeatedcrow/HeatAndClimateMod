package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class DumplingItem extends ItemEntityFood {

	public DumplingItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, prop(nut, sat), false, pair);
	}

	private static Properties prop(int nut, float sat) {
		return new Item.Properties().tab(FoodInit.FOOD)
		    .food(new FoodProperties.Builder().nutrition(nut)
		        .saturationMod(sat)
		        .alwaysEat()
		        .build());
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.DUMPLING.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.DUMPLING_TUBERS.get())
			return TUBERS;
		if (item == FoodInit.DUMPLING_CEREALS.get())
			return CEREALS;
		if (item == FoodInit.DUMPLING_BUCKWHEAT.get())
			return BUCKWHEAT;
		return TUBERS;
	}

	public static final EntityRenderData TUBERS = new EntityRenderData("food/dumpling_tubers", 1.0F, 0F);
	public static final EntityRenderData CEREALS = new EntityRenderData("food/dumpling_cereals", 1.0F, 0F);
	public static final EntityRenderData BUCKWHEAT = new EntityRenderData("food/dumpling_buckwheat", 1.0F, 0F);

}
