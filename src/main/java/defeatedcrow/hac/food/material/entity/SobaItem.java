package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class SobaItem extends ItemEntityFood {

	public SobaItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.SOBA.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.SOBA_SIMPLE.get())
			return SIMPLE;
		// if (item == FoodInit.SOBA_KITSUNE.get())
		// return KITSUNE;
		if (item == FoodInit.SOBA_TSUKIMI.get())
			return TSUKIMI;
		// if (item == FoodInit.SOBA_TENPURA.get())
		// return TENPURA;
		return SIMPLE;
	}

	public static final EntityRenderData SIMPLE = new EntityRenderData("food/soba_simple", 0.85F, 0F);
	public static final EntityRenderData KITSUNE = new EntityRenderData("food/soba_kitsune", 0.85F, 0F);
	public static final EntityRenderData TSUKIMI = new EntityRenderData("food/soba_tsukimi", 0.85F, 0F);
	public static final EntityRenderData TENPURA = new EntityRenderData("food/soba_tenpura", 0.85F, 0F);

}
