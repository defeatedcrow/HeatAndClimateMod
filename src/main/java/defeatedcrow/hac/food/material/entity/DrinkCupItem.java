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

public class DrinkCupItem extends ItemEntityFood {

	private final int potionId;
	private final boolean milk;

	public DrinkCupItem(String s, int nut, float sat, int id, boolean mi, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
		potionId = id;
		milk = mi;
	}

	private static Supplier<MobEffectInstance> potion(int id, boolean mi, int taste) {
		final int d = 600 * (taste + 2) * (mi ? 2 : 1);
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
		if (id == 6)
			return () -> new MobEffectInstance(MobEffects.JUMP, d, a);
		if (id == 7)
			return () -> new MobEffectInstance(CoreInit.HEAVY.get(), d, a);
		if (id == 8)
			return () -> new MobEffectInstance(MobEffects.NIGHT_VISION, d, a);

		return () -> new MobEffectInstance(MobEffects.REGENERATION, d, a);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.CUP.get();
	}

	@Override
	public ItemStack finishUsingItem(ItemStack item, Level level, LivingEntity liv) {
		int taste = getTaste(item);
		if (taste > -2 && !level.isClientSide) {
			MobEffectInstance effect = potion(potionId, milk, taste).get();
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
			MobEffectInstance effect = potion(potionId, milk, taste).get();
			PotionUtils.setCustomEffects(stack, ImmutableList.of(effect));
			PotionUtils.addPotionTooltip(stack, list, 1.0F);
		}
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.TEA_GREEN.get())
			return TEA_GREEN;
		if (item == FoodInit.TEA_GREEN_MILK.get())
			return TEA_GREEN_MILK;
		if (item == FoodInit.TEA_BLUE.get())
			return TEA_BLUE;
		if (item == FoodInit.TEA_BLUE_MILK.get())
			return TEA_BLUE_MILK;
		if (item == FoodInit.TEA_BLACK.get())
			return TEA_BLACK;
		if (item == FoodInit.TEA_BLACK_MILK.get())
			return TEA_BLACK_MILK;
		if (item == FoodInit.TEA_BLACK_LEMON.get())
			return TEA_BLACK_LEMON;
		if (item == FoodInit.TEA_CHAI.get())
			return TEA_CHAI;
		if (item == FoodInit.TEA_BERRY.get())
			return TEA_BERRY;
		if (item == FoodInit.TEA_BERRY_MILK.get())
			return TEA_BERRY_MILK;
		if (item == FoodInit.TEA_APPLE.get())
			return TEA_APPLE;
		if (item == FoodInit.TEA_APPLE_MILK.get())
			return TEA_APPLE_MILK;
		if (item == FoodInit.TEA_COCOA.get())
			return TEA_COCOA;
		if (item == FoodInit.TEA_COCOA_MILK.get())
			return TEA_COCOA_MILK;
		if (item == FoodInit.TEA_MINT.get())
			return TEA_MINT;
		if (item == FoodInit.TEA_MALLOW.get())
			return TEA_MALLOW;
		if (item == FoodInit.TEA_MALLOW_LEMON.get())
			return TEA_MALLOW_LEMON;
		if (item == FoodInit.TEA_SAFFRON.get())
			return TEA_SAFFRON;
		if (item == FoodInit.TEA_ROSEHIP.get())
			return TEA_ROSEHIP;
		if (item == FoodInit.TEA_HIBISCUS.get())
			return TEA_HIBISCUS;
		return TEA_BLACK;
	}

	public static final EntityRenderData TEA_GREEN = new EntityRenderData("food/cup_tea_green", 0.5F, 0F);
	public static final EntityRenderData TEA_GREEN_MILK = new EntityRenderData("food/cup_tea_green_milk", 0.5F, 0F);
	public static final EntityRenderData TEA_BLUE = new EntityRenderData("food/cup_tea_blue", 0.5F, 0F);
	public static final EntityRenderData TEA_BLUE_MILK = new EntityRenderData("food/cup_tea_blue_milk", 0.5F, 0F);
	public static final EntityRenderData TEA_BLACK = new EntityRenderData("food/cup_tea_black", 0.5F, 0F);
	public static final EntityRenderData TEA_BLACK_MILK = new EntityRenderData("food/cup_tea_black_milk", 0.5F, 0F);
	public static final EntityRenderData TEA_BLACK_LEMON = new EntityRenderData("food/cup_tea_lemon", 0.5F, 0F);
	public static final EntityRenderData TEA_BERRY = new EntityRenderData("food/cup_tea_berry", 0.5F, 0F);
	public static final EntityRenderData TEA_BERRY_MILK = new EntityRenderData("food/cup_tea_berry_milk", 0.5F, 0F);
	public static final EntityRenderData TEA_APPLE = new EntityRenderData("food/cup_tea_apple", 0.5F, 0F);
	public static final EntityRenderData TEA_APPLE_MILK = new EntityRenderData("food/cup_tea_apple_milk", 0.5F, 0F);
	public static final EntityRenderData TEA_CHAI = new EntityRenderData("food/cup_tea_black_milk", 0.5F, 0F);
	public static final EntityRenderData TEA_COCOA = new EntityRenderData("food/cup_cocoa", 0.5F, 0F);
	public static final EntityRenderData TEA_COCOA_MILK = new EntityRenderData("food/cup_cocoa_milk", 0.5F, 0F);
	public static final EntityRenderData TEA_MINT = new EntityRenderData("food/cup_mint", 0.5F, 0F);
	public static final EntityRenderData TEA_MALLOW = new EntityRenderData("food/cup_mallow", 0.5F, 0F);
	public static final EntityRenderData TEA_MALLOW_LEMON = new EntityRenderData("food/cup_mallow_lemon", 0.5F, 0F);
	public static final EntityRenderData TEA_SAFFRON = new EntityRenderData("food/cup_saffron", 0.5F, 0F);
	public static final EntityRenderData TEA_ROSEHIP = new EntityRenderData("food/cup_rosehip", 0.5F, 0F);
	public static final EntityRenderData TEA_HIBISCUS = new EntityRenderData("food/cup_hibiscus", 0.5F, 0F);

}
