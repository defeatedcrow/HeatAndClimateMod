package defeatedcrow.hac.food.material.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.util.DCUtil;
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

public class SaladItem extends ItemEntityFood {

	public SaladItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	// 悪性ポーション効果の解除
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
		DCUtil.removeBadPotion(living);
		return super.finishUsingItem(stack, level, living);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent st = Component.translatable("dcs.tip.remove_bad_potion");
		st.withStyle(ChatFormatting.GRAY);
		list.add(st);
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.SALAD.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.SALAD_GREEN.get())
			return GREEN;
		if (item == FoodInit.SALAD_POTATO.get())
			return POTATO;
		if (item == FoodInit.SALAD_NUTS.get())
			return NUTS;
		if (item == FoodInit.SALAD_MELON.get())
			return MELON;
		if (item == FoodInit.SALAD_TOFU.get())
			return TOFU;
		if (item == FoodInit.SALAD_SALMON.get())
			return SALMON;
		return GREEN;
	}

	public static final EntityRenderData GREEN = new EntityRenderData("food/salad_green", 0.85F, 0F);
	public static final EntityRenderData POTATO = new EntityRenderData("food/salad_potato", 0.85F, 0F);
	public static final EntityRenderData NUTS = new EntityRenderData("food/salad_nuts", 0.85F, 0F);
	public static final EntityRenderData MELON = new EntityRenderData("food/salad_watermelon", 0.85F, 0F);
	public static final EntityRenderData TOFU = new EntityRenderData("food/salad_tofu", 0.85F, 0F);
	public static final EntityRenderData SALMON = new EntityRenderData("food/salad_salmon", 0.85F, 0F);

}
