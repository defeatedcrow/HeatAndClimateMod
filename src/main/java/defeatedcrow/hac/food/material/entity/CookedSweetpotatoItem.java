package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class CookedSweetpotatoItem extends ItemEntityFood {

	public CookedSweetpotatoItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.SWEETPOTATO.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.SWEETPOTATO_RAW.get())
			return RAW;
		if (item == FoodInit.SWEETPOTATO_COOKED.get())
			return COOKED;
		return RAW;
	}

	public static final EntityRenderData RAW = new EntityRenderData("food/sweetpotato_raw", 1F, 0F);
	public static final EntityRenderData COOKED = new EntityRenderData("food/sweetpotato_cooked", 1F, 0F);

}
