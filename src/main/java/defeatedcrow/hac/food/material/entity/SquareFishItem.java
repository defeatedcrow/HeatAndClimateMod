package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class SquareFishItem extends ItemEntityFood {

	public SquareFishItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.SQUARE_FISH.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.FISH_MEUNIERE.get())
			return MEUNIERE;
		if (item == FoodInit.GRILLED_SALMON.get())
			return SALMON;
		if (item == FoodInit.GRILLED_WHITE_FISH.get())
			return WHITE;
		if (item == FoodInit.IWASHI_MENTAI.get())
			return IWASHI;

		return SIMMER;
	}

	public static final EntityRenderData MEUNIERE = new EntityRenderData("food/fish_meuniere", 0.8F, 0F);
	public static final EntityRenderData SIMMER = new EntityRenderData("food/simmered_fish", 0.8F, 0F);
	public static final EntityRenderData SALMON = new EntityRenderData("food/grilled_salmon", 0.8F, 0F);
	public static final EntityRenderData WHITE = new EntityRenderData("food/grilled_white_fish", 0.8F, 0F);
	public static final EntityRenderData IWASHI = new EntityRenderData("food/iwashi_mentai", 0.8F, 0F);

}
