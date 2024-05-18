package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class SquareFishItem extends ItemEntityFood {

	public SquareFishItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.SQUARE_FISH.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.FISH_MEUNIERE.get())
			return MEUNIERE;

		return SIMMER;
	}

	public static final EntityRenderData MEUNIERE = new EntityRenderData("food/fish_meuniere", 1F, 0F);
	public static final EntityRenderData SIMMER = new EntityRenderData("food/simmered_fish", 1F, 0F);

}
