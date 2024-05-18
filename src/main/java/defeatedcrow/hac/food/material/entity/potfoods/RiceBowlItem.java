package defeatedcrow.hac.food.material.entity.potfoods;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class RiceBowlItem extends ItemEntityFood implements IPotFoods {

	public RiceBowlItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.RICE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.RICE_BARLEY.get())
			return BARLEY;
		if (item == FoodInit.RICE_SEKI.get())
			return SEKI;
		if (item == FoodInit.RICE_NAPA.get())
			return NAPA;
		if (item == FoodInit.RICE_FISH.get())
			return FISH;

		return NORMAL;
	}

	@Override
	public EntityRenderData getPotTexture(Item item) {
		if (item == FoodInit.RICE_BARLEY.get())
			return BARLEY_LAYER;
		if (item == FoodInit.RICE_SEKI.get())
			return SEKI_LAYER;

		return NORMAL_LAYER;
	}

	public static final EntityRenderData NORMAL = new EntityRenderData("food/rice_boiled", 0.8F, 0F);
	public static final EntityRenderData BARLEY = new EntityRenderData("food/rice_barley", 0.8F, 0F);
	public static final EntityRenderData SEKI = new EntityRenderData("food/rice_seki", 0.8F, 0F);
	public static final EntityRenderData NAPA = new EntityRenderData("food/rice_napa", 0.8F, 0F);
	public static final EntityRenderData FISH = new EntityRenderData("food/rice_fish", 0.8F, 0F);

	public static final EntityRenderData NORMAL_LAYER = new EntityRenderData("food/layer/rice_boiled_layer", 1F, 0F);
	public static final EntityRenderData BARLEY_LAYER = new EntityRenderData("food/layer/rice_barley_layer", 1F, 0F);
	public static final EntityRenderData SEKI_LAYER = new EntityRenderData("food/layer/rice_seki_layer", 1F, 0F);

}
