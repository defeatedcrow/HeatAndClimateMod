package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class RamenItem extends ItemEntityFood {

	public RamenItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	public RamenItem(String s, int nut, float sat, boolean isHot, TagKey<Item> pair) {
		super(s, nut, sat, isHot, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.RAMEN.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.RAMEN_SHOYU.get())
			return SHOYU;
		if (item == FoodInit.RAMEN_MISO.get())
			return MISO;
		if (item == FoodInit.RAMEN_TONKOTSU.get())
			return TONKOTSU;
		if (item == FoodInit.RAMEN_TANTAN.get())
			return TANTAN;
		if (item == FoodInit.RAMEN_SESAMI.get())
			return SESAMI;
		if (item == FoodInit.RAMEN_BEEF.get())
			return BEEF;
		if (item == FoodInit.RAMEN_OKINAWA.get())
			return OKINAWA;
		if (item == FoodInit.RAMEN_HOUTOU.get())
			return HOUTOU;
		if (item == FoodInit.RAMEN_REIMEN.get())
			return REIMEN;
		if (item == FoodInit.RAMEN_LAKSA.get())
			return LAKSA;
		if (item == FoodInit.RAMEN_TOMYUM.get())
			return TOMYUM;
		return SHOYU;
	}

	public static final EntityRenderData SHOYU = new EntityRenderData("food/ramen_shoyu", 0.85F, 0F);
	public static final EntityRenderData MISO = new EntityRenderData("food/ramen_miso", 0.85F, 0F);
	public static final EntityRenderData TONKOTSU = new EntityRenderData("food/ramen_tonkotsu", 0.85F, 0F);
	public static final EntityRenderData TANTAN = new EntityRenderData("food/ramen_tantan", 0.85F, 0F);
	public static final EntityRenderData SESAMI = new EntityRenderData("food/ramen_tantan_sesami", 0.85F, 0F);
	public static final EntityRenderData BEEF = new EntityRenderData("food/ramen_beef", 0.85F, 0F);
	public static final EntityRenderData OKINAWA = new EntityRenderData("food/ramen_okinawa", 0.85F, 0F);
	public static final EntityRenderData HOUTOU = new EntityRenderData("food/ramen_houtou", 0.85F, 0F);
	public static final EntityRenderData REIMEN = new EntityRenderData("food/ramen_naeng_myeon", 0.85F, 0F);
	public static final EntityRenderData LAKSA = new EntityRenderData("food/ramen_laksa", 0.85F, 0F);
	public static final EntityRenderData TOMYUM = new EntityRenderData("food/ramen_tomyum", 0.85F, 0F);

}
