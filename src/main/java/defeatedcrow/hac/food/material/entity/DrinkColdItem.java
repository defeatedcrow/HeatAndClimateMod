package defeatedcrow.hac.food.material.entity;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

public class DrinkColdItem extends ItemEntityFood {

	private final int potionId;

	public DrinkColdItem(String s, int nut, float sat, int id, TagKey<Item> pair) {
		super(s, nut, sat, pair);
		potionId = id;
	}

	private static Supplier<MobEffectInstance> potion(int id, int taste) {
		final int d = 600 * (taste + 2);
		final int a = taste > 0 ? taste : 0;

		if (id == 1)
			return () -> new MobEffectInstance(CoreInit.COLD_RESISTANCE.get(), d);
		if (id == 2)
			return () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, d);
		if (id == 3)
			return () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, d, a);
		if (id == 4)
			return () -> new MobEffectInstance(MobEffects.HEALTH_BOOST, d, a);
		if (id == 5)
			return () -> new MobEffectInstance(MobEffects.WATER_BREATHING, d);

		return () -> new MobEffectInstance(MobEffects.REGENERATION, d);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.GLASS.get();
	}

	@Override
	public ItemStack finishUsingItem(ItemStack item, Level level, LivingEntity liv) {
		int taste = getTaste(item);
		if (taste > -2 && !level.isClientSide) {
			MobEffectInstance effect = potion(potionId, taste).get();
			if (liv.hasEffect(effect.getEffect())) {
				int d = liv.getEffect(effect.getEffect()).getDuration() + effect.getDuration();
				effect = new MobEffectInstance(effect.getEffect(), d, effect.getAmplifier());
			}
			liv.addEffect(effect);
		}
		return super.finishUsingItem(item, level, liv);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!DCUtil.isEmpty(item)) {
			ItemStack stack = item.copy();
			int taste = getTaste(stack);
			MobEffectInstance effect = potion(potionId, taste).get();
			PotionUtils.setCustomEffects(stack, ImmutableList.of(effect));
			PotionUtils.addPotionTooltip(stack, list, 1.0F);
		}
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.DRINK_BERRY.get())
			return DRINK_BERRY;
		if (item == FoodInit.DRINK_BERRY_SODA.get())
			return DRINK_BERRY_SODA;
		if (item == FoodInit.DRINK_MELON.get())
			return DRINK_MELON;
		if (item == FoodInit.DRINK_MELON_SODA.get())
			return DRINK_MELON_SODA;
		if (item == FoodInit.DRINK_GRAPE.get())
			return DRINK_GRAPE;
		if (item == FoodInit.DRINK_GRAPE_SODA.get())
			return DRINK_GRAPE_SODA;
		if (item == FoodInit.DRINK_GRAPE_WHITE.get())
			return DRINK_GRAPE_WHITE;
		if (item == FoodInit.DRINK_GRAPE_WHITE_SODA.get())
			return DRINK_GRAPE_WHITE_SODA;
		if (item == FoodInit.DRINK_PLUM.get())
			return DRINK_PLUM;
		if (item == FoodInit.DRINK_PLUM_SODA.get())
			return DRINK_PLUM_SODA;
		if (item == FoodInit.DRINK_PEACH.get())
			return DRINK_PEACH;
		if (item == FoodInit.DRINK_PEACH_SODA.get())
			return DRINK_PEACH_SODA;
		if (item == FoodInit.DRINK_POMELO.get())
			return DRINK_POMELO;
		if (item == FoodInit.DRINK_MANDARIN.get())
			return DRINK_MANDARIN;
		if (item == FoodInit.DRINK_LEMON.get())
			return DRINK_LEMON;
		if (item == FoodInit.DRINK_CITRUS_SODA.get())
			return DRINK_CITRUS_SODA;
		if (item == FoodInit.DRINK_MANGO.get())
			return DRINK_MANGO;
		if (item == FoodInit.DRINK_MANGO_SODA.get())
			return DRINK_MANGO_SODA;
		if (item == FoodInit.DRINK_GUAVA.get())
			return DRINK_GUAVA;
		if (item == FoodInit.DRINK_GUAVA_SODA.get())
			return DRINK_GUAVA_SODA;
		if (item == FoodInit.DRINK_MILK_SHAKE.get())
			return DRINK_MILK_SHAKE;
		if (item == FoodInit.DRINK_TONIC.get())
			return DRINK_TONIC;
		if (item == FoodInit.DRINK_TEA_BARLEY.get())
			return DRINK_TEA_BARLEY;
		if (item == FoodInit.DRINK_TEA_SODA.get())
			return DRINK_TEA_SODA;
		return DRINK_TONIC;
	}

	public static final EntityRenderData DRINK_BERRY = new EntityRenderData("food/drink_berry", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_BERRY_SODA = new EntityRenderData("food/drink_soda_berry", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_MELON = new EntityRenderData("food/drink_melon", 0.5F, 1F);
	public static final EntityRenderData DRINK_MELON_SODA = new EntityRenderData("food/drink_soda_melon", 0.5F, 1F);
	public static final EntityRenderData DRINK_GRAPE = new EntityRenderData("food/drink_grape", 0.5F, 1F);
	public static final EntityRenderData DRINK_GRAPE_SODA = new EntityRenderData("food/drink_soda_grape", 0.5F, 1F);
	public static final EntityRenderData DRINK_GRAPE_WHITE = new EntityRenderData("food/drink_grape_white", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_GRAPE_WHITE_SODA = new EntityRenderData("food/drink_soda_grape_white", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_PLUM = new EntityRenderData("food/drink_plum", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_PLUM_SODA = new EntityRenderData("food/drink_soda_plum", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_PEACH = new EntityRenderData("food/drink_peach", 0.5F, 1F);
	public static final EntityRenderData DRINK_PEACH_SODA = new EntityRenderData("food/drink_soda_peach", 0.5F, 1F);
	public static final EntityRenderData DRINK_POMELO = new EntityRenderData("food/drink_pomelo", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_MANDARIN = new EntityRenderData("food/drink_mandarin", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_LEMON = new EntityRenderData("food/drink_lemon", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_CITRUS_SODA = new EntityRenderData("food/drink_soda_citrus", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_MANGO = new EntityRenderData("food/drink_mango", 0.5F, 1F);
	public static final EntityRenderData DRINK_MANGO_SODA = new EntityRenderData("food/drink_soda_mango", 0.5F, 1F);
	public static final EntityRenderData DRINK_GUAVA = new EntityRenderData("food/drink_guava", 0.5F, 1F);
	public static final EntityRenderData DRINK_GUAVA_SODA = new EntityRenderData("food/drink_soda_guava", 0.5F, 1F);
	public static final EntityRenderData DRINK_MILK_SHAKE = new EntityRenderData("food/drink_milk_shake", 0.5F, 1F);
	public static final EntityRenderData DRINK_TONIC = new EntityRenderData("food/drink_soda_blue", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_TEA_BARLEY = new EntityRenderData("food/drink_tea_barley", 0.5F, 0.75F);
	public static final EntityRenderData DRINK_TEA_SODA = new EntityRenderData("food/drink_soda_tea", 0.5F, 0.75F);

}
