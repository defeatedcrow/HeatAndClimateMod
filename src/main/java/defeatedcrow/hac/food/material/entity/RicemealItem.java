package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class RicemealItem extends ItemEntityFood {

	public RicemealItem(String s, int nut, float sat, TagKey<Item> pair) {
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
		return FoodInit.RICEMEAL.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.RICE_CHAOFAN.get())
			return CHAOFAN;
		if (item == FoodInit.RICE_KETCHUP.get())
			return KETCHUP;
		if (item == FoodInit.RICE_OMERICE.get())
			return OMERICE;
		if (item == FoodInit.RICE_HAINAN.get())
			return HAINAN;
		if (item == FoodInit.RICE_PILAV.get())
			return PILAV;
		if (item == FoodInit.RICE_JAMBALAYA.get())
			return JAMBALAYA;
		if (item == FoodInit.RICE_JOLLOF.get())
			return JOLLOF;
		if (item == FoodInit.RICE_PAELLIA.get())
			return PAELLIA;
		return CHAOFAN;
	}

	public static final EntityRenderData CHAOFAN = new EntityRenderData("food/rice_chaofan", 0.85F, 0F);
	public static final EntityRenderData KETCHUP = new EntityRenderData("food/rice_ketchup", 0.85F, 0F);
	public static final EntityRenderData OMERICE = new EntityRenderData("food/rice_omerice", 0.85F, 0F);
	public static final EntityRenderData HAINAN = new EntityRenderData("food/rice_hainan_chicken", 0.85F, 0F);
	public static final EntityRenderData PILAV = new EntityRenderData("food/rice_pilav", 0.85F, 0F);
	public static final EntityRenderData JAMBALAYA = new EntityRenderData("food/rice_jambalaya", 0.85F, 0F);
	public static final EntityRenderData JOLLOF = new EntityRenderData("food/rice_jollof", 0.85F, 0F);
	public static final EntityRenderData PAELLIA = new EntityRenderData("food/rice_paellia", 0.85F, 0F);

	public static enum ModelType {
		CHAOFAN,
		HAINAN,
		JAMBALAYA,
		JOLLOF,
		PAELLIA;

		public static ModelType getType(Item item) {
			if (item == FoodInit.RICE_HAINAN.get())
				return HAINAN;
			if (item == FoodInit.RICE_JAMBALAYA.get() || item == FoodInit.RICE_PILAV.get())
				return JAMBALAYA;
			if (item == FoodInit.RICE_JOLLOF.get())
				return JOLLOF;
			if (item == FoodInit.RICE_PAELLIA.get())
				return PAELLIA;
			return CHAOFAN;
		}
	}

}
