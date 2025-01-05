package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class TacoItem extends ItemEntityFood {

	public TacoItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.TACO.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.TACO_MEAT.get())
			return TACO_MEAT;
		if (item == FoodInit.TACO_AVOCADO.get())
			return TACO_AVOCADO;
		if (item == FoodInit.TACO_FISH.get())
			return TACO_FISH;
		return TACO_MEAT;
	}

	public static final EntityRenderData TACO_MEAT = new EntityRenderData("food/taco_meat", 0.85F, 0F);
	public static final EntityRenderData TACO_AVOCADO = new EntityRenderData("food/taco_avocado", 0.85F, 0F);
	public static final EntityRenderData TACO_FISH = new EntityRenderData("food/taco_fish", 0.85F, 0F);

}
