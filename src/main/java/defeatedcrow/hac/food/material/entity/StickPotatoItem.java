package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class StickPotatoItem extends ItemEntityFood {

	public StickPotatoItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.STICK_POTATO.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.STICK_POTATO_RAW.get())
			return POTATO_RAW;
		if (item == FoodInit.STICK_POTATO_COOKED.get())
			return POTATO_COOKED;
		if (item == FoodInit.STICK_EGGPLANT_RAW.get())
			return EGGPLANT_RAW;
		if (item == FoodInit.STICK_EGGPLANT_COOKED.get())
			return EGGPLANT_COOKED;
		if (item == FoodInit.STICK_LETTUCE_RAW.get())
			return LETTUCE_RAW;
		if (item == FoodInit.STICK_LETTUCE_COOKED.get())
			return LETTUCE_COOKED;
		return POTATO_RAW;
	}

	public static final EntityRenderData POTATO_RAW = new EntityRenderData("food/stick_potato_raw", 1F, 0F);
	public static final EntityRenderData POTATO_COOKED = new EntityRenderData("food/stick_potato_cooked", 1F, 0F);
	public static final EntityRenderData EGGPLANT_RAW = new EntityRenderData("food/stick_eggplant_raw", 1F, 0F);
	public static final EntityRenderData EGGPLANT_COOKED = new EntityRenderData("food/stick_eggplant_cooked", 1F, 0F);
	public static final EntityRenderData LETTUCE_RAW = new EntityRenderData("food/stick_lettuce_raw", 1F, 0F);
	public static final EntityRenderData LETTUCE_COOKED = new EntityRenderData("food/stick_lettuce_cooked", 1F, 0F);

}
