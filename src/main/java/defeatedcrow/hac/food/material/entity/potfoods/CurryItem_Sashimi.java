package defeatedcrow.hac.food.material.entity.potfoods;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class CurryItem_Sashimi extends ItemEntityFood {

	public CurryItem_Sashimi(String s, int nut, float sat, boolean curry, TagKey<Item> pair) {
		super(s, prop(nut, sat, curry), pair);
	}

	private static Properties prop(int nut, float sat, boolean isCurry) {
		return new Item.Properties().tab(FoodInit.FOOD).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().build());
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.CURRY_SASHIMI_MODEL.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.LARGE_BOWL_CARPACCIO.get())
			return LARGE_BOWL_CARPACCIO;
		if (item == FoodInit.LARGE_BOWL_FISH_CARPACCIO.get())
			return LARGE_BOWL_FISH_CARPACCIO;

		return LARGE_BOWL_CAPRESE;
	}

	public static final EntityRenderData LARGE_BOWL_CARPACCIO = new EntityRenderData("food/large_bowl_carpaccio", 0.85F, -0.1F);
	public static final EntityRenderData LARGE_BOWL_FISH_CARPACCIO = new EntityRenderData("food/large_bowl_fish_carpaccio", 0.85F, -0.1F);
	public static final EntityRenderData LARGE_BOWL_CAPRESE = new EntityRenderData("food/large_bowl_caprese", 0.85F, -0.1F);

}
