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

public class WagashiItem extends ItemEntityFood {

	public WagashiItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.WAGASHI.get();
	}

	// 回復
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
		int taste = getTaste(stack) + 1;
		taste *= 2;
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
		if (item == FoodInit.WAGASHI_BOTA.get())
			return BOTA;
		if (item == FoodInit.WAGASHI_KUSA.get())
			return KUSA;
		if (item == FoodInit.WAGASHI_SAKURA.get())
			return SAKURA;
		if (item == FoodInit.WAGASHI_DAIHUKU.get())
			return DAIHUKU;

		return BOTA;
	}

	public static final EntityRenderData BOTA = new EntityRenderData("food/wagashi_bota", 0.75F, 0F);
	public static final EntityRenderData KUSA = new EntityRenderData("food/wagashi_kusa", 0.75F, 0F);
	public static final EntityRenderData SAKURA = new EntityRenderData("food/wagashi_sakura", 0.75F, 0F);
	public static final EntityRenderData DAIHUKU = new EntityRenderData("food/wagashi_daifuku", 0.75F, 0F);

}
