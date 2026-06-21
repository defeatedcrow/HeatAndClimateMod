package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class FriedEggItem extends ItemEntityFood {

	public FriedEggItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, prop(nut, sat), true, pair);
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
		return FoodInit.FRIED_EGG_ENTITY.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.FRIED_EGG.get())
			return FRIED_EGG;
		if (item == FoodInit.BACON_EGG.get())
			return BACON_EGG;
		return FRIED_EGG;
	}

	public static final EntityRenderData FRIED_EGG = new EntityRenderData("food/plate_fried_egg", 1.0F, 0F);
	public static final EntityRenderData BACON_EGG = new EntityRenderData("food/plate_bacon_egg", 1.0F, 0F);

}
