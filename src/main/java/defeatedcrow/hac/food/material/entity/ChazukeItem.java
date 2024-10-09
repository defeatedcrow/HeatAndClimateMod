package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class ChazukeItem extends ItemEntityFood {

	public ChazukeItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.CHAZUKE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.CHAZUKE_SAKE.get())
			return SAKE;
		if (item == FoodInit.CHAZUKE_TARAKO.get())
			return TARAKO;
		if (item == FoodInit.CHAZUKE_TAI.get())
			return TAI;

		return UME;
	}

	public static final EntityRenderData UME = new EntityRenderData("food/chazuke_ume", 0.75F, 0F);
	public static final EntityRenderData SAKE = new EntityRenderData("food/chazuke_sake", 0.75F, 0F);
	public static final EntityRenderData TAI = new EntityRenderData("food/chazuke_tai", 0.75F, 0F);
	public static final EntityRenderData TARAKO = new EntityRenderData("food/chazuke_tarako", 0.75F, 0F);

}
