package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class KobachiItem extends ItemEntityFood {

	public KobachiItem(String s, int nut, float sat, boolean isHot, TagKey<Item> pair) {
		super(s, nut, sat, isHot, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.KOBACHI.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.KOBACHI_PUMPKIN.get())
			return PUMPKIN;
		if (item == FoodInit.KOBACHI_TARO.get())
			return TARO;
		if (item == FoodInit.KOBACHI_TARO_SQUID.get())
			return TARO_SQUID;
		if (item == FoodInit.KOBACHI_RADISH_SQUID.get())
			return RADISH_SQUID;
		if (item == FoodInit.KOBACHI_BAMBOO.get())
			return BAMBOO;
		if (item == FoodInit.KOBACHI_POTATO.get())
			return POTATO;
		if (item == FoodInit.KOBACHI_TIKUZEN.get())
			return TIKUZEN;
		if (item == FoodInit.KOBACHI_CHICKEN.get())
			return CHICKEN;
		if (item == FoodInit.KOBACHI_PORK.get())
			return PORK;
		if (item == FoodInit.KOBACHI_FLOWER.get())
			return FLOWER;
		if (item == FoodInit.KOBACHI_AGE.get())
			return AGE;
		if (item == FoodInit.KOBACHI_NAMASU.get())
			return NAMASU;
		if (item == FoodInit.KOBACHI_WHITE.get())
			return WHITE;
		if (item == FoodInit.KOBACHI_YAKKO.get())
			return YAKKO;
		if (item == FoodInit.KOBACHI_NAMEROU.get())
			return NAMEROU;
		if (item == FoodInit.KOBACHI_TUNA_AVOCADO.get())
			return TUNA_AVOCADO;
		return PUMPKIN;
	}

	public static final EntityRenderData PUMPKIN = new EntityRenderData("food/kobachi_pumpkin", 0.6F, 0F);
	public static final EntityRenderData TARO = new EntityRenderData("food/kobachi_taro", 0.6F, 0F);
	public static final EntityRenderData TARO_SQUID = new EntityRenderData("food/kobachi_taro_squid", 0.6F, 0F);
	public static final EntityRenderData RADISH_SQUID = new EntityRenderData("food/kobachi_radish_squid", 0.6F, 0F);
	public static final EntityRenderData BAMBOO = new EntityRenderData("food/kobachi_bamboo", 0.6F, 0F);
	public static final EntityRenderData POTATO = new EntityRenderData("food/kobachi_sweetpotato", 0.6F, 0F);
	public static final EntityRenderData TIKUZEN = new EntityRenderData("food/kobachi_tikuzen", 0.6F, 0F);
	public static final EntityRenderData CHICKEN = new EntityRenderData("food/kobachi_chicken", 0.6F, 0F);
	public static final EntityRenderData PORK = new EntityRenderData("food/kobachi_pork", 0.6F, 0F);
	public static final EntityRenderData FLOWER = new EntityRenderData("food/kobachi_flower", 0.6F, 0F);
	public static final EntityRenderData AGE = new EntityRenderData("food/kobachi_age", 0.6F, 0F);
	public static final EntityRenderData NAMASU = new EntityRenderData("food/kobachi_namasu", 0.6F, 0F);
	public static final EntityRenderData WHITE = new EntityRenderData("food/kobachi_white", 0.6F, 0F);
	public static final EntityRenderData YAKKO = new EntityRenderData("food/kobachi_yakko", 0.6F, 0F);
	public static final EntityRenderData NAMEROU = new EntityRenderData("food/kobachi_namerou", 0.6F, 0F);
	public static final EntityRenderData TUNA_AVOCADO = new EntityRenderData("food/kobachi_tuna_avocado", 0.6F, 0F);

	public static enum ModelType {
		MAIN,
		PASTE,
		TUNA,
		CHICKEN;

		public static ModelType getType(Item item) {
			if (item == FoodInit.KOBACHI_PUMPKIN.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_TARO.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_TARO_SQUID.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_RADISH_SQUID.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_BAMBOO.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_POTATO.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_TIKUZEN.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_CHICKEN.get())
				return CHICKEN;
			if (item == FoodInit.KOBACHI_PORK.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_FLOWER.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_AGE.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_NAMASU.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_WHITE.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_YAKKO.get())
				return MAIN;
			if (item == FoodInit.KOBACHI_NAMEROU.get())
				return PASTE;
			if (item == FoodInit.KOBACHI_TUNA_AVOCADO.get())
				return TUNA;
			return MAIN;
		}
	}

}
