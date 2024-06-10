package defeatedcrow.hac.food.material.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BottleBeerItem extends ItemEntityFood {

	public BottleBeerItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	// ポーション効果付き
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
		if (!DCUtil.isEmpty(stack) && stack.getItem() instanceof IFoodTaste food) {
			MobEffect potion = getPotion(stack.getItem());
			int taste = food.getTaste(stack);
			MobEffect eff = getPotion(stack.getItem());
			int dur = 2400 + (taste * 600);
			if (living.hasEffect(potion)) {
				dur += living.getEffect(potion).getDuration();
			}
			if (taste < 0)
				taste = 0;

			MobEffectInstance ret = new MobEffectInstance(eff, dur, taste);
			if (!level.isClientSide) {
				living.addEffect(ret);
			}
		}
		return super.finishUsingItem(stack, level, living);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!DCUtil.isEmpty(stack) && stack.getItem() instanceof IFoodTaste food) {
			MobEffect potion = getPotion(stack.getItem());
			int taste = food.getTaste(stack);
			MobEffect eff = getPotion(stack.getItem());
			int dur = 2400 + (taste * 600);
			if (taste < 0)
				taste = 0;
			MobEffectInstance instance = new MobEffectInstance(eff, dur, taste);
			MutableComponent component = Component.translatable(instance.getDescriptionId());
			if (instance.getAmplifier() > 0) {
				component = Component.translatable("potion.withAmplifier", component, Component.translatable("potion.potency." + instance.getAmplifier()));
			}
			if (instance.getDuration() > 20) {
				component = Component.translatable("potion.withDuration", component, MobEffectUtil.formatDuration(instance, 1.0F));
			}
			list.add(component.withStyle(eff.getCategory().getTooltipFormatting()));
		}
		super.appendHoverText(stack, level, list, flag);
	}

	protected MobEffect getPotion(Item item) {
		if (item == FoodInit.BOTTLE_BEER.get())
			return MobEffects.DIG_SPEED;
		if (item == FoodInit.BOTTLE_SAKE.get())
			return CoreInit.PROJ_RESISTANCE.get();
		return MobEffects.DIG_SPEED;
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.BOTTLE_BEERTYPE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.BOTTLE_BEER.get())
			return BEER;
		if (item == FoodInit.BOTTLE_SAKE.get())
			return SAKE;
		return BEER;
	}

	public static final EntityRenderData BEER = new EntityRenderData("food/bottle_beer", 1F, 0F);
	public static final EntityRenderData SAKE = new EntityRenderData("food/bottle_sake", 1F, 0F);

}
