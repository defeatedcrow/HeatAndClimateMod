package defeatedcrow.hac.food.material.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class IcecreamItem extends ItemEntityFood {

	public IcecreamItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.ICECREAM.get();
	}

	// 回復
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
		int taste = getTaste(stack) + 2;
		if (taste > 0)
			living.heal(2.0F * taste);
		if (living.isOnFire()) {
			living.clearFire();
		}
		return super.finishUsingItem(stack, level, living);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent st = Component.translatable("dcs.tip.healing");
		st.withStyle(ChatFormatting.GRAY);
		list.add(st);
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.ICECREAM_VANILLA.get())
			return VANILLA;
		if (item == FoodInit.ICECREAM_NUTS.get())
			return NUTS;
		if (item == FoodInit.ICECREAM_COCOA.get())
			return COCOA;
		if (item == FoodInit.ICECREAM_BERRY.get())
			return BERRY;
		if (item == FoodInit.ICECREAM_WATERMELON.get())
			return WATERMELON;
		if (item == FoodInit.ICECREAM_MELON.get())
			return MELON;
		if (item == FoodInit.ICECREAM_GRAPE.get())
			return GRAPE;
		if (item == FoodInit.ICECREAM_CITRUS.get())
			return CITRUS;
		if (item == FoodInit.ICECREAM_MANGO.get())
			return MANGO;
		if (item == FoodInit.ICECREAM_GUAVA.get())
			return GUAVA;
		if (item == FoodInit.ICECREAM_SODA_MELON.get())
			return CREAMSODA_MELON;
		if (item == FoodInit.ICECREAM_SODA_MALLOW.get())
			return CREAMSODA_MALLOW;
		if (item == FoodInit.ICECREAM_PUDDING.get())
			return PUDDING;
		if (item == FoodInit.ICECREAM_SUNDAE_COCOA.get())
			return SUNDAE_COCOA;
		if (item == FoodInit.ICECREAM_SUNDAE_PEACH.get())
			return SUNDAE_PEACH;
		if (item == FoodInit.ICECREAM_SUNDAE_COFFEE.get())
			return SUNDAE_AFFOGATO;
		return VANILLA;
	}

	public static final EntityRenderData VANILLA = new EntityRenderData("food/icecream_vanilla", 0.6F, 0.05F);
	public static final EntityRenderData NUTS = new EntityRenderData("food/icecream_nuts", 0.6F, 0.05F);
	public static final EntityRenderData COCOA = new EntityRenderData("food/icecream_cocoa", 0.6F, 0.05F);
	public static final EntityRenderData BERRY = new EntityRenderData("food/icecream_berry", 0.6F, 0.05F);
	public static final EntityRenderData WATERMELON = new EntityRenderData("food/icecream_watermelon", 0.6F, 0.05F);
	public static final EntityRenderData MELON = new EntityRenderData("food/icecream_melon", 0.6F, 0.05F);
	public static final EntityRenderData GRAPE = new EntityRenderData("food/icecream_grape", 0.6F, 0.05F);
	public static final EntityRenderData CITRUS = new EntityRenderData("food/icecream_citrus", 0.6F, 0.05F);
	public static final EntityRenderData MANGO = new EntityRenderData("food/icecream_mango", 0.6F, 0.05F);
	public static final EntityRenderData GUAVA = new EntityRenderData("food/icecream_guava", 0.6F, 0.05F);
	public static final EntityRenderData CREAMSODA_MELON = new EntityRenderData("food/creamsoda_melon", 0.5F, 0.05F);
	public static final EntityRenderData CREAMSODA_MALLOW = new EntityRenderData("food/creamsoda_mallow", 0.5F, 0.05F);
	public static final EntityRenderData PUDDING = new EntityRenderData("food/icecream_pudding", 1.0F, 0.05F);
	public static final EntityRenderData SUNDAE_COCOA = new EntityRenderData("food/sundae_cocoa", 0.6F, 0.05F);
	public static final EntityRenderData SUNDAE_PEACH = new EntityRenderData("food/sundae_peach", 0.6F, 0.05F);
	public static final EntityRenderData SUNDAE_AFFOGATO = new EntityRenderData("food/sundae_affogato", 0.6F, 0.05F);

	public static enum ModelType {
		ICECREAM,
		FLOAT,
		PUDDING,
		SUNDAE;

		public static ModelType getType(Item item) {
			if (item == FoodInit.ICECREAM_SODA_MELON.get() || item == FoodInit.ICECREAM_SODA_MALLOW.get())
				return FLOAT;
			if (item == FoodInit.ICECREAM_PUDDING.get())
				return PUDDING;
			if (item == FoodInit.ICECREAM_SUNDAE_COCOA.get() || item == FoodInit.ICECREAM_SUNDAE_PEACH.get() || item == FoodInit.ICECREAM_SUNDAE_COFFEE.get())
				return SUNDAE;
			return ICECREAM;
		}
	}

}
