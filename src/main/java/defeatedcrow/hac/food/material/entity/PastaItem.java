package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PastaItem extends ItemEntityFood {

	public PastaItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.PASTA.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.PASTA_OIL.get())
			return OIL;
		if (item == FoodInit.PASTA_TOMATO.get())
			return TOMATO;
		if (item == FoodInit.PASTA_BASIL.get())
			return BASIL;
		if (item == FoodInit.PASTA_PRAWN.get())
			return PRAWN;
		if (item == FoodInit.PASTA_PUTTANESCA.get())
			return PUTTANESCA;
		if (item == FoodInit.PASTA_SQUID.get())
			return SQUID;
		if (item == FoodInit.PASTA_BEEF.get())
			return BEEF;
		if (item == FoodInit.PASTA_COD.get())
			return COD;
		if (item == FoodInit.PASTA_KRILL.get())
			return KRILL;
		if (item == FoodInit.PASTA_ROE.get())
			return ROE;
		return OIL;
	}

	public static final EntityRenderData OIL = new EntityRenderData("food/pasta_oil", 0.85F, 0F);
	public static final EntityRenderData TOMATO = new EntityRenderData("food/pasta_tomato", 0.85F, 0F);
	public static final EntityRenderData BASIL = new EntityRenderData("food/pasta_basil", 0.85F, 0F);
	public static final EntityRenderData PRAWN = new EntityRenderData("food/pasta_prawn", 0.85F, 0F);
	public static final EntityRenderData PUTTANESCA = new EntityRenderData("food/pasta_puttanesca", 0.85F, 0F);
	public static final EntityRenderData SQUID = new EntityRenderData("food/pasta_squid", 0.85F, 0F);
	public static final EntityRenderData BEEF = new EntityRenderData("food/pasta_beef", 0.85F, 0F);
	public static final EntityRenderData COD = new EntityRenderData("food/pasta_cod", 0.85F, 0F);
	public static final EntityRenderData KRILL = new EntityRenderData("food/pasta_krill", 0.85F, 0F);
	public static final EntityRenderData ROE = new EntityRenderData("food/pasta_roe", 0.85F, 0F);

}
