package defeatedcrow.hac.food.material.entity.potfoods;

import java.util.function.Supplier;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class SoupItem extends ItemEntityFood implements IPotFoods {

	public SoupItem(String s, int nut, float sat, boolean cold, TagKey<Item> pair) {
		super(s, prop(nut, sat, cold), pair);
	}

	private static Properties prop(int nut, float sat, boolean isCold) {
		return new Item.Properties().tab(FoodInit.FOOD).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().effect(isCold ? hot() : cold(), 1.0F).build());
	}

	private static Supplier<MobEffectInstance> hot() {
		return () -> new MobEffectInstance(CoreInit.COLD_RESISTANCE.get(), 600);
	}

	private static Supplier<MobEffectInstance> cold() {
		return () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.SOUP.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.SOUP_CREAM_POTATO.get())
			return SOUP_CREAM_POTATO;
		if (item == FoodInit.SOUP_CREAM_PUMPKIN.get())
			return SOUP_CREAM_PUMPKIN;
		if (item == FoodInit.SOUP_CREAM_CORN.get())
			return SOUP_CREAM_CORN;
		if (item == FoodInit.SOUP_CREAM_SPINACH.get())
			return SOUP_CREAM_SPINACH;
		if (item == FoodInit.SOUP_CREAM_PARSNIP.get())
			return SOUP_CREAM_PARSNIP;
		if (item == FoodInit.SOUP_CREAM_SHRIMP.get())
			return SOUP_CREAM_SHRIMP;
		if (item == FoodInit.SOUP_CHINESE_EGG.get())
			return SOUP_CHINESE_EGG;
		if (item == FoodInit.SOUP_CHINESE_CRAB.get())
			return SOUP_CHINESE_CRAB;
		if (item == FoodInit.SOUP_JUTE.get())
			return SOUP_JUTE;
		if (item == FoodInit.SOUP_GASPACHO.get())
			return SOUP_GASPACHO;
		if (item == FoodInit.SOUP_TARATOR.get())
			return SOUP_TARATOR;
		if (item == FoodInit.SOUP_MINESTRONE.get())
			return SOUP_MINESTRONE;
		if (item == FoodInit.SOUP_CHILIBEANS.get())
			return SOUP_CHILIBEANS;
		if (item == FoodInit.SOUP_SORREL.get())
			return SOUP_SORREL;
		return SOUP_CREAM_POTATO;
	}

	@Override
	public EntityRenderData getPotTexture(Item item) {
		if (item == FoodInit.SOUP_CREAM_POTATO.get())
			return SOUP_CREAM_POTATO_LAYER;
		if (item == FoodInit.SOUP_CREAM_PUMPKIN.get())
			return SOUP_CREAM_PUMPKIN_LAYER;
		if (item == FoodInit.SOUP_CREAM_CORN.get())
			return SOUP_CREAM_CORN_LAYER;
		if (item == FoodInit.SOUP_CREAM_SPINACH.get())
			return SOUP_CREAM_SPINACH_LAYER;
		if (item == FoodInit.SOUP_CREAM_PARSNIP.get())
			return SOUP_CREAM_PARSNIP_LAYER;
		if (item == FoodInit.SOUP_CREAM_SHRIMP.get())
			return SOUP_CREAM_SHRIMP_LAYER;
		if (item == FoodInit.SOUP_CHINESE_EGG.get())
			return SOUP_CHINESE_EGG_LAYER;
		if (item == FoodInit.SOUP_CHINESE_CRAB.get())
			return SOUP_CHINESE_CRAB_LAYER;
		if (item == FoodInit.SOUP_JUTE.get())
			return SOUP_JUTE_LAYER;
		if (item == FoodInit.SOUP_GASPACHO.get())
			return SOUP_GASPACHO_LAYER;
		if (item == FoodInit.SOUP_TARATOR.get())
			return SOUP_TARATOR_LAYER;
		if (item == FoodInit.SOUP_MINESTRONE.get())
			return SOUP_MINESTRONE_LAYER;
		if (item == FoodInit.SOUP_CHILIBEANS.get())
			return SOUP_CHILIBEANS_LAYER;
		if (item == FoodInit.SOUP_SORREL.get())
			return SOUP_SORREL_LAYER;
		return SOUP_LAYER;
	}

	public static final EntityRenderData SOUP_CREAM_POTATO = new EntityRenderData("food/soup_cream_potato", 0.75F, 0F, "outer");
	public static final EntityRenderData SOUP_CREAM_PUMPKIN = new EntityRenderData("food/soup_cream_pumpkin", 0.75F, 0F, "outer");
	public static final EntityRenderData SOUP_CREAM_CORN = new EntityRenderData("food/soup_cream_corn", 0.75F, 0F, "outer");
	public static final EntityRenderData SOUP_CREAM_SPINACH = new EntityRenderData("food/soup_cream_spinach", 0.75F, 0F, "outer");
	public static final EntityRenderData SOUP_CREAM_PARSNIP = new EntityRenderData("food/soup_cream_parsnip", 0.75F, 0F, "outer");
	public static final EntityRenderData SOUP_CREAM_SHRIMP = new EntityRenderData("food/soup_cream_shrimp", 0.75F, 0F, "outer");
	public static final EntityRenderData SOUP_CHINESE_EGG = new EntityRenderData("food/soup_chinese_egg", 0.75F, 0F, "outer");
	public static final EntityRenderData SOUP_CHINESE_CRAB = new EntityRenderData("food/soup_chinese_crab", 0.75F, 0F, "outer");
	public static final EntityRenderData SOUP_JUTE = new EntityRenderData("food/soup_jute", 0.75F, 0F);
	public static final EntityRenderData SOUP_GASPACHO = new EntityRenderData("food/soup_gaspacho", 0.75F, 0F, "outer");
	public static final EntityRenderData SOUP_TARATOR = new EntityRenderData("food/soup_tarator", 0.75F, 0F, "outer");
	public static final EntityRenderData SOUP_MINESTRONE = new EntityRenderData("food/soup_minestrone", 0.75F, 0F);
	public static final EntityRenderData SOUP_CHILIBEANS = new EntityRenderData("food/soup_chilibeans", 0.75F, 0F);
	public static final EntityRenderData SOUP_SORREL = new EntityRenderData("food/soup_sorrel", 0.75F, 0F, "outer");

	public static final EntityRenderData SOUP_LAYER = new EntityRenderData("food/layer/soup_simple_layer", 1F, 0F);
	public static final EntityRenderData SOUP_CREAM_POTATO_LAYER = new EntityRenderData("food/soup_cream_potato_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_CREAM_PUMPKIN_LAYER = new EntityRenderData("food/soup_cream_pumpkin_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_CREAM_CORN_LAYER = new EntityRenderData("food/soup_cream_corn_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_CREAM_SPINACH_LAYER = new EntityRenderData("food/soup_cream_spinach_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_CREAM_PARSNIP_LAYER = new EntityRenderData("food/soup_cream_parsnip_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_CREAM_SHRIMP_LAYER = new EntityRenderData("food/soup_cream_shrimp_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_CHINESE_EGG_LAYER = new EntityRenderData("food/soup_chinese_egg_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_CHINESE_CRAB_LAYER = new EntityRenderData("food/soup_chinese_crab_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_JUTE_LAYER = new EntityRenderData("food/soup_jute_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_GASPACHO_LAYER = new EntityRenderData("food/soup_gaspacho_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_TARATOR_LAYER = new EntityRenderData("food/soup_tarator_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_MINESTRONE_LAYER = new EntityRenderData("food/soup_minestrone_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_CHILIBEANS_LAYER = new EntityRenderData("food/soup_chilibeans_layer", 0.75F, 0F);
	public static final EntityRenderData SOUP_SORREL_LAYER = new EntityRenderData("food/soup_sorrel_layer", 0.75F, 0F);

}
