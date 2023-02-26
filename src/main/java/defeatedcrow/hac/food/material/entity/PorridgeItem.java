package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PorridgeItem extends ItemEntityFood {

	public PorridgeItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.SOUP.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PORRIDGE.get())
			return PORRIDGE;
		if (item == FoodInit.PORRIDGE_MILK.get())
			return PORRIDGE_MILK;
		if (item == FoodInit.MUESLI.get())
			return MUESLI;
		return PORRIDGE;
	}

	public static final EntityRenderData PORRIDGE = new EntityRenderData("food/porridge_simple", 1F, 0F, "outer");
	public static final EntityRenderData PORRIDGE_MILK = new EntityRenderData("food/porridge_milk", 1F, 0F, "outer");
	public static final EntityRenderData MUESLI = new EntityRenderData("food/muesli", 1F, 0F, "outer");

}
