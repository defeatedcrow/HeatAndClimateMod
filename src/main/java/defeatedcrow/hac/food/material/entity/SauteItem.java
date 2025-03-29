package defeatedcrow.hac.food.material.entity;

import java.util.function.Supplier;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class SauteItem extends ItemEntityFood {

	public SauteItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, prop(nut, sat), true, pair);
	}

	private static Properties prop(int nut, float sat) {
		return new Item.Properties().tab(FoodInit.FOOD).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().effect(hot(), 1.0F).build());
	}

	private static Supplier<MobEffectInstance> hot() {
		return () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.SAUTE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.SAUTE_CHILI_GREEN.get())
			return GREEN;
		if (item == FoodInit.SAUTE_GREEN_SQUID.get())
			return GREEN_SQUID;
		if (item == FoodInit.SAUTE_MABO.get())
			return MABO;
		if (item == FoodInit.SAUTE_CHILI_NASU.get())
			return NASU;
		if (item == FoodInit.SAUTE_CHILI_MEAT.get())
			return MEAT;
		if (item == FoodInit.SAUTE_CHILI_FISH.get())
			return FISH;
		if (item == FoodInit.SAUTE_CHILI_PRAWN.get())
			return PRAWN;
		if (item == FoodInit.SAUTE_WHITE_CHICKEN.get())
			return WHITE_CHICKEN;
		if (item == FoodInit.SAUTE_HOT_CHICKEN.get())
			return HOT_CHICKEN;
		if (item == FoodInit.SAUTE_SANBEIJI.get())
			return SANBEIJI;
		if (item == FoodInit.SAUTE_CRAB_EGG.get())
			return CRAB_EGG;
		if (item == FoodInit.SAUTE_CASHEW_CHICKEN.get())
			return CASHEW_CHICKEN;
		return GREEN;
	}

	public static final EntityRenderData GREEN = new EntityRenderData("food/saute_green", 1.0F, 0F);
	public static final EntityRenderData GREEN_SQUID = new EntityRenderData("food/saute_green_squid", 1.0F, 0F);
	public static final EntityRenderData MABO = new EntityRenderData("food/saute_mabo_tofu", 1.0F, 0F);
	public static final EntityRenderData NASU = new EntityRenderData("food/saute_yuxiang_qiezi", 1.0F, 0F);
	public static final EntityRenderData MEAT = new EntityRenderData("food/saute_yuxiang_rousi", 1.0F, 0F);
	public static final EntityRenderData FISH = new EntityRenderData("food/saute_gan_shao_yu", 1.0F, 0F);
	public static final EntityRenderData PRAWN = new EntityRenderData("food/saute_gan_shao_xia", 1.0F, 0F);
	public static final EntityRenderData WHITE_CHICKEN = new EntityRenderData("food/saute_baiqieji", 1.0F, 0F);
	public static final EntityRenderData HOT_CHICKEN = new EntityRenderData("food/saute_koushuiji", 1.0F, 0F);
	public static final EntityRenderData SANBEIJI = new EntityRenderData("food/saute_sanbeiji", 1.0F, 0F);
	public static final EntityRenderData CRAB_EGG = new EntityRenderData("food/saute_fuyung_dan", 1.0F, 0F);
	public static final EntityRenderData CASHEW_CHICKEN = new EntityRenderData("food/saute_cashew_chicken", 1.0F, 0F);

	public static enum ModelType {
		TOFU,
		CHICKEN,
		EGG,
		NASU;

		public static ModelType getType(Item item) {
			if (item == FoodInit.SAUTE_CHILI_GREEN.get())
				return TOFU;
			if (item == FoodInit.SAUTE_GREEN_SQUID.get())
				return NASU;
			if (item == FoodInit.SAUTE_MABO.get())
				return TOFU;
			if (item == FoodInit.SAUTE_CHILI_NASU.get())
				return NASU;
			if (item == FoodInit.SAUTE_CHILI_MEAT.get())
				return NASU;
			if (item == FoodInit.SAUTE_CHILI_FISH.get())
				return CHICKEN;
			if (item == FoodInit.SAUTE_CHILI_PRAWN.get())
				return NASU;
			if (item == FoodInit.SAUTE_WHITE_CHICKEN.get())
				return CHICKEN;
			if (item == FoodInit.SAUTE_HOT_CHICKEN.get())
				return CHICKEN;
			if (item == FoodInit.SAUTE_SANBEIJI.get())
				return NASU;
			if (item == FoodInit.SAUTE_CRAB_EGG.get())
				return EGG;
			if (item == FoodInit.SAUTE_CASHEW_CHICKEN.get())
				return NASU;
			return TOFU;
		}
	}

}
