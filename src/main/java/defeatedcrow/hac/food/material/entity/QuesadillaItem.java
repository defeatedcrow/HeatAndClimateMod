package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class QuesadillaItem extends ItemEntityFood {

	public QuesadillaItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.QUESADILLA.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return QUESADILLA;
	}

	public static final EntityRenderData QUESADILLA = new EntityRenderData("food/taco_quesadilla", 0.75F, 0F);

}
