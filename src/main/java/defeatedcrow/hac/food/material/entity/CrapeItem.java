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

public class CrapeItem extends ItemEntityFood {

	public CrapeItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.CRAPE.get();
	}

	// 回復
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
		int taste = getTaste(stack) * 2 + 1;
		if (taste > 0)
			living.heal(1.0F * taste);
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
		if (item == FoodInit.CRAPE_SUGAR.get())
			return SUGAR;
		if (item == FoodInit.CRAPE_CREAM.get())
			return CREAM;
		if (item == FoodInit.CRAPE_CARAMEL.get())
			return CARAMEL;
		if (item == FoodInit.CRAPE_COCOA.get())
			return COCOA;
		if (item == FoodInit.CRAPE_APPLE.get())
			return APPLE;
		if (item == FoodInit.CRAPE_BERRY.get())
			return BERRY;
		if (item == FoodInit.CRAPE_APPLE.get())
			return APPLE;
		if (item == FoodInit.CRAPE_TUNA.get())
			return TUNA;
		return SUGAR;
	}

	public static final EntityRenderData SUGAR = new EntityRenderData("food/crape_sugar", 0.6F, 0F);
	public static final EntityRenderData CREAM = new EntityRenderData("food/crape_cream", 0.6F, 0F);
	public static final EntityRenderData CARAMEL = new EntityRenderData("food/crape_caramel", 0.6F, 0F);
	public static final EntityRenderData COCOA = new EntityRenderData("food/crape_cocoa", 0.6F, 0F);
	public static final EntityRenderData BERRY = new EntityRenderData("food/crape_berry", 0.6F, 0F);
	public static final EntityRenderData APPLE = new EntityRenderData("food/crape_apple", 0.6F, 0F);
	public static final EntityRenderData TUNA = new EntityRenderData("food/crape_tuna", 0.6F, 0F);

}
