package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class MinidishItem extends ItemEntityFood {

	public MinidishItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.MINIDISH.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.MINIDISH_TSUKEMONO.get())
			return TSUKEMONO;
		if (item == FoodInit.MINIDISH_KIMCHI.get())
			return KIMCHI;
		if (item == FoodInit.MINIDISH_UMEBOSHI.get())
			return UMEBOSHI;
		return SAUERKRAUT;
	}

	public static final EntityRenderData SAUERKRAUT = new EntityRenderData("food/minidish_sauerkraut", 0.6F, 0F);
	public static final EntityRenderData TSUKEMONO = new EntityRenderData("food/minidish_tsukemono", 0.6F, 0F);
	public static final EntityRenderData KIMCHI = new EntityRenderData("food/minidish_kimchi", 0.6F, 0F);
	public static final EntityRenderData UMEBOSHI = new EntityRenderData("food/minidish_umeboshi", 0.6F, 0F);

	public static enum ModelType {
		VEGI,
		LEAVES,
		UME;

		public static ModelType getType(Item item) {
			if (item == FoodInit.MINIDISH_TSUKEMONO.get())
				return VEGI;
			if (item == FoodInit.MINIDISH_UMEBOSHI.get())
				return UME;
			return LEAVES;
		}
	}

}
