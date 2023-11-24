package defeatedcrow.hac.food.material.entity.potfoods;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PorridgeItem extends ItemEntityFood implements IPotFoods {

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

	@Override
	public EntityRenderData getPotTexture(Item item) {
		if (item == FoodInit.PORRIDGE.get())
			return PORRIDGE_LAYER;
		if (item == FoodInit.PORRIDGE_MILK.get())
			return PORRIDGE_MILK_LAYER;
		if (item == FoodInit.MUESLI.get())
			return MUESLI_LAYER;
		return PORRIDGE_LAYER;
	}

	public static final EntityRenderData PORRIDGE = new EntityRenderData("food/porridge_simple", 0.75F, 0F, "outer");
	public static final EntityRenderData PORRIDGE_MILK = new EntityRenderData("food/porridge_milk", 0.75F, 0F, "outer");
	public static final EntityRenderData MUESLI = new EntityRenderData("food/muesli", 0.75F, 0F, "outer");

	public static final EntityRenderData PORRIDGE_LAYER = new EntityRenderData("food/layer/porridge_simple_layer", 1F, 0F, "outer");
	public static final EntityRenderData PORRIDGE_MILK_LAYER = new EntityRenderData("food/layer/porridge_milk_layer", 1F, 0F, "outer");
	public static final EntityRenderData MUESLI_LAYER = new EntityRenderData("food/layer/muesli_layer", 1F, 0F, "outer");

}
