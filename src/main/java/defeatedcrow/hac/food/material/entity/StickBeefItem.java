package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class StickBeefItem extends ItemEntityFood {

	public StickBeefItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.STICK_BEEF.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.STICK_BEEF_RAW.get())
			return BEEF_RAW;
		if (item == FoodInit.STICK_BEEF_COOKED.get())
			return BEEF_COOKED;
		if (item == FoodInit.STICK_PORK_RAW.get())
			return PORK_RAW;
		if (item == FoodInit.STICK_PORK_COOKED.get())
			return PORK_COOKED;
		return BEEF_RAW;
	}

	public static final EntityRenderData BEEF_RAW = new EntityRenderData("food/stick_beef_raw", 1F, 0F);
	public static final EntityRenderData BEEF_COOKED = new EntityRenderData("food/stick_beef_cooked", 1F, 0F);
	public static final EntityRenderData PORK_RAW = new EntityRenderData("food/stick_pork_raw", 1F, 0F);
	public static final EntityRenderData PORK_COOKED = new EntityRenderData("food/stick_pork_cooked", 1F, 0F);

}
