package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class OmeletItem extends ItemEntityFood {

	public OmeletItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, prop(nut, sat), true, pair);
		defeatedcrow.hac.core.material.tabs.CreativeTabDC.add(FoodInit.FOOD, this);
	}

	private static Properties prop(int nut, float sat) {
		return new Item.Properties()
		    .food(new FoodProperties.Builder().nutrition(nut)
		        .saturationMod(sat)
		        .alwaysEat()
		        .build());
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.OMELET.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.OMELET_VEGI.get())
			return VEGI;
		if (item == FoodInit.SAUSAGE_SAUTE.get())
			return SAUSAGE;
		if (item == FoodInit.SAUSAGE_CURRY.get())
			return SAUSAGE_CURRY;
		if (item == FoodInit.GALETTE.get())
			return GALETTE;
		return BASIC;
	}

	public static final EntityRenderData BASIC = new EntityRenderData("food/plate_omelet_basic", 1.0F, 0F);
	public static final EntityRenderData VEGI = new EntityRenderData("food/plate_omelet_vegi", 1.0F, 0F);
	public static final EntityRenderData SAUSAGE = new EntityRenderData("food/plate_sausage_saute", 1.0F, 0F);
	public static final EntityRenderData SAUSAGE_CURRY = new EntityRenderData("food/plate_sausage_curry", 1.0F, 0F);
	public static final EntityRenderData GALETTE = new EntityRenderData("food/galette_complete", 1.0F, 0F);

	public static enum ModelType {
		OMELET,
		SAUSAGE,
		CURRY,
		GALETTE;

		public static ModelType getType(Item item) {
			if (item == FoodInit.SAUSAGE_SAUTE.get())
				return SAUSAGE;
			if (item == FoodInit.SAUSAGE_CURRY.get())
				return CURRY;
			if (item == FoodInit.GALETTE.get())
				return GALETTE;
			return OMELET;
		}
	}

}
