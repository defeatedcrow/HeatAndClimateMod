package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class TempuraPrawnItem extends ItemEntityFood {

	public TempuraPrawnItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.TEMPURA_PRAWN_MODEL.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return PRAWN;
	}

	public static final EntityRenderData PRAWN = new EntityRenderData("food/tempura_prawn", 1F, 0F);

}
