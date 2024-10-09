package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class TempuraIkatenItem extends ItemEntityFood {

	public TempuraIkatenItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.TEMPURA_IKATEN_MODEL.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.TEMPURA_UMESISO.get())
			return UME;
		return IKATEN;
	}

	public static final EntityRenderData IKATEN = new EntityRenderData("food/tempura_ikaten", 1F, 0F);
	public static final EntityRenderData UME = new EntityRenderData("food/tempura_umesiso", 1F, 0F);

}
