package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PlateStuffedVegiItem extends ItemEntityFood {

	public PlateStuffedVegiItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.PLATE_STUFFED_VEGI.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PLATE_STUFFED_BELL_RAW.get())
			return STUFFED_BELL_RAW;
		if (item == FoodInit.PLATE_STUFFED_BELL_COOKED.get())
			return STUFFED_BELL_COOKED;
		if (item == FoodInit.PLATE_STUFFED_PAPRIKA_RAW.get())
			return STUFFED_PAPRIKA_RAW;
		if (item == FoodInit.PLATE_STUFFED_PAPRIKA_COOKED.get())
			return STUFFED_PAPRIKA_COOKED;
		if (item == FoodInit.PLATE_POTATO_RAW.get())
			return POTATO_RAW;
		if (item == FoodInit.PLATE_POTATO_COOKED.get())
			return POTATO_COOKED;
		return STUFFED_BELL_RAW;
	}

	public static final EntityRenderData STUFFED_BELL_RAW = new EntityRenderData("food/plate_stuffed_bellpepper_raw", 1F, 0F);
	public static final EntityRenderData STUFFED_BELL_COOKED = new EntityRenderData("food/plate_stuffed_bellpepper_cooked", 1F, 0F);
	public static final EntityRenderData STUFFED_PAPRIKA_RAW = new EntityRenderData("food/plate_stuffed_paprika_raw", 1F, 0F);
	public static final EntityRenderData STUFFED_PAPRIKA_COOKED = new EntityRenderData("food/plate_stuffed_paprika_cooked", 1F, 0F);
	public static final EntityRenderData POTATO_RAW = new EntityRenderData("food/plate_potato_raw", 1F, 0F);
	public static final EntityRenderData POTATO_COOKED = new EntityRenderData("food/plate_potato_cooked", 1F, 0F);

}
