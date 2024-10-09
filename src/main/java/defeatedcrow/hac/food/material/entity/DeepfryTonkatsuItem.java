package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class DeepfryTonkatsuItem extends ItemEntityFood {

	public DeepfryTonkatsuItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.FRY_TONKATSU.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return TONKATSU;
	}

	public static final EntityRenderData TONKATSU = new EntityRenderData("food/deepfry_tonkatsu", 1F, 0F);

}
