package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class ZarusobaItem extends ItemEntityFood {

	public ZarusobaItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.ZARUSOBA.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.ZARUSOBA_SIMPLE.get())
			return SIMPLE;
		if (item == FoodInit.ZARUSOBA_TEA.get())
			return TEA;
		if (item == FoodInit.ZARUSOBA_SISO.get())
			return SISO;
		return SIMPLE;
	}

	public static final EntityRenderData SIMPLE = new EntityRenderData("food/zarusoba", 0.85F, 0F);
	public static final EntityRenderData TEA = new EntityRenderData("food/zarusoba_tea", 0.85F, 0F);
	public static final EntityRenderData SISO = new EntityRenderData("food/zarusoba_siso", 0.85F, 0F);

}
