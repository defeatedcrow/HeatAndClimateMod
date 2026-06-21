package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class DrinkDemitasseItem extends DrinkCupItem {

	public DrinkDemitasseItem(String s, int nut, float sat, int id, boolean mi, TagKey<Item> pair) {
		super(s, nut, sat, id, mi, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.DEMITASSE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return COFFEE;
	}

	public static final EntityRenderData COFFEE = new EntityRenderData("food/demitasse_coffee", 0.5F, 0F);

}
