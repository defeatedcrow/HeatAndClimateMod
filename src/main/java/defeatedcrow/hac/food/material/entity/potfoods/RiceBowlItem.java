package defeatedcrow.hac.food.material.entity.potfoods;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.json.JsonModelDC;
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
		return NORMAL;
	}

	@Override
	public EntityRenderData getPotTexture(Item item) {
		return NORMAL_LAYER;
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/food/rice_boiled"));
	}

	public static final EntityRenderData NORMAL = new EntityRenderData("food/rice_boiled", 0.8F, 0F);

	public static final EntityRenderData NORMAL_LAYER = new EntityRenderData("food/layer/rice_boiled_layer", 1F, 0F);

}
