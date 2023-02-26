package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class StickMeatItem extends ItemEntityFood {

	public StickMeatItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.STICK_MEAT.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.STICK_MUTTON_RAW.get())
			return MUTTON_RAW;
		if (item == FoodInit.STICK_MUTTON_COOKED.get())
			return MUTTON_COOKED;
		if (item == FoodInit.STICK_CHICKEN_RAW.get())
			return CHICKEN_RAW;
		if (item == FoodInit.STICK_CHICKEN_COOKED.get())
			return CHICKEN_COOKED;
		if (item == FoodInit.STICK_OFFAL_RAW.get())
			return OFFAL_RAW;
		if (item == FoodInit.STICK_OFFAL_COOKED.get())
			return OFFAL_COOKED;
		return MUTTON_RAW;
	}

	public static final EntityRenderData MUTTON_RAW = new EntityRenderData("food/stick_mutton_raw", 1F, 0F);
	public static final EntityRenderData MUTTON_COOKED = new EntityRenderData("food/stick_mutton_cooked", 1F, 0F);
	public static final EntityRenderData CHICKEN_RAW = new EntityRenderData("food/stick_chicken_raw", 1F, 0F);
	public static final EntityRenderData CHICKEN_COOKED = new EntityRenderData("food/stick_chicken_cooked", 1F, 0F);
	public static final EntityRenderData OFFAL_RAW = new EntityRenderData("food/stick_offal_raw", 1F, 0F);
	public static final EntityRenderData OFFAL_COOKED = new EntityRenderData("food/stick_offal_cooked", 1F, 0F);

}
