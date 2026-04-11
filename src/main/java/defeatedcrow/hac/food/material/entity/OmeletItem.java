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
	}

	private static Properties prop(int nut, float sat) {
		return new Item.Properties().tab(FoodInit.FOOD).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().build());
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.OMELET.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.OMELET_VEGI.get())
			return VEGI;
		if (item == FoodInit.FRIED_EGG.get())
			return FRIED_EGG;
		return BASIC;
	}

	public static final EntityRenderData BASIC = new EntityRenderData("food/plate_omelet_basic", 1.0F, 0F);
	public static final EntityRenderData VEGI = new EntityRenderData("food/plate_omelet_vegi", 1.0F, 0F);
	public static final EntityRenderData FRIED_EGG = new EntityRenderData("food/plate_fried_egg", 1.0F, 0F);

	public static enum ModelType {
		OMELET, EGG;

		public static ModelType getType(Item item) {
			if (item == FoodInit.FRIED_EGG.get())
				return EGG;
			return OMELET;
		}
	}

}
