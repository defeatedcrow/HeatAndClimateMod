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

public class CakeItem extends ItemEntityFood {

	public CakeItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.CAKE.get();
	}

	// 回復
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
		int taste = getTaste(stack) + 1;
		if (stack.getItem() != FoodInit.CAKE_BUTTER.get()) {
			taste *= 2;
		}
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
		if (item == FoodInit.CAKE_BUTTER.get())
			return BUTTER;
		if (item == FoodInit.CAKE_BERRY.get())
			return BERRY;
		if (item == FoodInit.CAKE_CHOCOLATE.get())
			return CHOCOLATE;
		if (item == FoodInit.CAKE_GREENTEA.get())
			return GREENTEA;
		return BUTTER;
	}

	public static final EntityRenderData BUTTER = new EntityRenderData("food/cake_butter", 1F, 0F);
	public static final EntityRenderData BERRY = new EntityRenderData("food/cake_berry", 1F, 0F);
	public static final EntityRenderData CHOCOLATE = new EntityRenderData("food/cake_chocolate", 1F, 0F);
	public static final EntityRenderData GREENTEA = new EntityRenderData("food/cake_greentea", 1F, 0F);

}
