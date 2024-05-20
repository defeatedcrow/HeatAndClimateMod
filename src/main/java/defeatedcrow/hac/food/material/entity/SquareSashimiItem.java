package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class SquareSashimiItem extends ItemEntityFood {

	public SquareSashimiItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.SQUARE_SASHIMI.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.SASHIMI_WHITE.get())
			return WHITE;
		if (item == FoodInit.SASHIMI_SALMON.get())
			return SALMON;
		if (item == FoodInit.SASHIMI_BLUE.get())
			return BLUE;
		if (item == FoodInit.SASHIMI_TUNA.get())
			return TUNA;
		if (item == FoodInit.SASHIMI_SQUID.get())
			return SQUID;
		return WHITE;
	}

	public static final EntityRenderData WHITE = new EntityRenderData("food/sashimi_white", 0.8F, 0F);
	public static final EntityRenderData SALMON = new EntityRenderData("food/sashimi_salmon", 0.8F, 0F);
	public static final EntityRenderData BLUE = new EntityRenderData("food/sashimi_blue", 0.8F, 0F);
	public static final EntityRenderData TUNA = new EntityRenderData("food/sashimi_tuna", 0.8F, 0F);
	public static final EntityRenderData SQUID = new EntityRenderData("food/sashimi_squid", 0.8F, 0F);

}
