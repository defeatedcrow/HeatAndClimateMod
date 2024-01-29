package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class CasseroleItem extends ItemEntityFood {

	public CasseroleItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.CASSEROLE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.CASSEROLE_GRATIN_SHRIMP_RAW_ITEM.get())
			return CASSEROLE_GRATIN_SHRIMP_RAW;
		if (item == FoodInit.CASSEROLE_GRATIN_SHRIMP_BAKED_ITEM.get())
			return CASSEROLE_GRATIN_SHRIMP_BAKED;
		if (item == FoodInit.CASSEROLE_SHEPHERDS_PIE_RAW_ITEM.get())
			return CASSEROLE_SHEPHERDS_PIE_RAW;
		if (item == FoodInit.CASSEROLE_SHEPHERDS_PIE_BAKED_ITEM.get())
			return CASSEROLE_SHEPHERDS_PIE_BAKED;
		if (item == FoodInit.CASSEROLE_DORIA_RAW_ITEM.get())
			return CASSEROLE_DORIA_RAW;
		if (item == FoodInit.CASSEROLE_DORIA_BAKED_ITEM.get())
			return CASSEROLE_DORIA_BAKED;
		if (item == FoodInit.CASSEROLE_JANSSONS_FRESTELESE_RAW_ITEM.get())
			return CASSEROLE_JANSSONS_FRESTELESE_RAW;
		if (item == FoodInit.CASSEROLE_JANSSONS_FRESTELESE_BAKED_ITEM.get())
			return CASSEROLE_JANSSONS_FRESTELESE_BAKED;
		if (item == FoodInit.CASSEROLE_PARMIGIANA_RAW_ITEM.get())
			return CASSEROLE_PARMIGIANA_RAW;
		if (item == FoodInit.CASSEROLE_PARMIGIANA_BAKED_ITEM.get())
			return CASSEROLE_PARMIGIANA_BAKED;
		if (item == FoodInit.CASSEROLE_MOUSSAKA_RAW_ITEM.get())
			return CASSEROLE_MOUSSAKA_RAW;
		if (item == FoodInit.CASSEROLE_MOUSSAKA_BAKED_ITEM.get())
			return CASSEROLE_MOUSSAKA_BAKED;
		return CASSEROLE_GRATIN_SHRIMP_RAW;
	}

	public static final EntityRenderData CASSEROLE_GRATIN_SHRIMP_RAW = new EntityRenderData("food/casserole_gratin_shrimp_raw", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_GRATIN_SHRIMP_BAKED = new EntityRenderData("food/casserole_gratin_shrimp_baked", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_SHEPHERDS_PIE_RAW = new EntityRenderData("food/casserole_shepherds_pie_raw", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_SHEPHERDS_PIE_BAKED = new EntityRenderData("food/casserole_shepherds_pie_baked", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_DORIA_RAW = new EntityRenderData("food/casserole_doria_raw", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_DORIA_BAKED = new EntityRenderData("food/casserole_doria_baked", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_JANSSONS_FRESTELESE_RAW = new EntityRenderData("food/casserole_janssons_frestelese_raw", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_JANSSONS_FRESTELESE_BAKED = new EntityRenderData("food/casserole_janssons_frestelese_baked", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_PARMIGIANA_RAW = new EntityRenderData("food/casserole_parmigiana_raw", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_PARMIGIANA_BAKED = new EntityRenderData("food/casserole_parmigiana_baked", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_MOUSSAKA_RAW = new EntityRenderData("food/casserole_moussaka_raw", 1.0F, 0F);
	public static final EntityRenderData CASSEROLE_MOUSSAKA_BAKED = new EntityRenderData("food/casserole_moussaka_baked", 1.0F, 0F);

}
