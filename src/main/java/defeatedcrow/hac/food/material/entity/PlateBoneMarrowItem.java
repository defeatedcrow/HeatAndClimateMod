package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PlateBoneMarrowItem extends ItemEntityFood {

	public PlateBoneMarrowItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.PLATE_BONE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PLATE_BONE_MARROW_RAW.get())
			return BONE_MARROW_RAW;
		if (item == FoodInit.PLATE_BONE_MARROW_COOKED.get())
			return BONE_MARROW_COOKED;
		return BONE_MARROW_RAW;
	}

	public static final EntityRenderData BONE_MARROW_RAW = new EntityRenderData("food/plate_marrow_raw", 1F, 0F);
	public static final EntityRenderData BONE_MARROW_COOKED = new EntityRenderData("food/plate_marrow_cooked", 1F, 0F);

}
