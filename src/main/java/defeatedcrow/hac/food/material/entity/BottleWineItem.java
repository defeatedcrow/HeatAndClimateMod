package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class BottleWineItem extends BottleBeerItem {

	public BottleWineItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	protected MobEffect getPotion(Item item) {
		if (item == FoodInit.BOTTLE_WINE.get())
			return MobEffects.DAMAGE_RESISTANCE;
		if (item == FoodInit.BOTTLE_WINE_WHITE.get())
			return MobEffects.DAMAGE_RESISTANCE;
		return MobEffects.DAMAGE_RESISTANCE;
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.BOTTLE_WINETYPE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.BOTTLE_WINE.get())
			return WINE;
		if (item == FoodInit.BOTTLE_WINE_WHITE.get())
			return WINE_WHITE;
		return BEER;
	}

	public static final EntityRenderData WINE = new EntityRenderData("food/bottle_wine", 1F, 0F);
	public static final EntityRenderData WINE_WHITE = new EntityRenderData("food/bottle_wine_white", 1F, 0F);
}
