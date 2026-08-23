package defeatedcrow.hac.food.material.entity.potfoods;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class HotPotItem extends ItemEntityFood implements IPotFoods {

	public HotPotItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.HOTPOT.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.HOTPOT_TOFU.get())
			return TOFU;
		if (item == FoodInit.HOTPOT_CHICKEN.get())
			return CHICKEN;
		if (item == FoodInit.HOTPOT_CRAB.get())
			return CRAB;
		if (item == FoodInit.HOTPOT_MALATANG.get())
			return MALATANG;

		return TOFU;
	}

	@Override
	public EntityRenderData getPotTexture(Item item) {
		if (item == FoodInit.HOTPOT_TOFU.get())
			return TOFU_LAYER;
		if (item == FoodInit.HOTPOT_CHICKEN.get())
			return CHICKEN_LAYER;
		if (item == FoodInit.HOTPOT_CRAB.get())
			return CRAB_LAYER;
		if (item == FoodInit.HOTPOT_MALATANG.get())
			return MALATANG_LAYER;

		return TOFU_LAYER;
	}

	@Override
	public LayerType getPotLayerModel(Item item) {
		return LayerType.POT;
	}

	public static final EntityRenderData TOFU = new EntityRenderData("food/hotpot_tofu", 0.75F, 0F);
	public static final EntityRenderData CHICKEN = new EntityRenderData("food/hotpot_chicken", 0.75F, 0F);
	public static final EntityRenderData CRAB = new EntityRenderData("food/hotpot_crab", 0.75F, 0F);
	public static final EntityRenderData MALATANG = new EntityRenderData("food/hotpot_malatang", 0.75F, 0F);

	public static final EntityRenderData TOFU_LAYER = new EntityRenderData("food/layer/hotpot_tofu_layer", 1F, 0F);
	public static final EntityRenderData CHICKEN_LAYER = new EntityRenderData("food/layer/hotpot_chicken_layer", 1F, 0F);
	public static final EntityRenderData CRAB_LAYER = new EntityRenderData("food/layer/hotpot_crab_layer", 1F, 0F);
	public static final EntityRenderData MALATANG_LAYER = new EntityRenderData("food/layer/hotpot_malatang_layer", 1F, 0F);

}
