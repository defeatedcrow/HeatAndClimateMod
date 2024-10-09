package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class TempuraFishItem extends ItemEntityFood {

	public TempuraFishItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.TEMPURA_FISH_MODEL.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return FISH;
	}

	public static final EntityRenderData FISH = new EntityRenderData("food/tempura_fish", 1F, 0F);

}
