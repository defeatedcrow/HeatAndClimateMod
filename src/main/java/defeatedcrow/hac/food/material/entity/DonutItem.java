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

public class DonutItem extends ItemEntityFood {

	public DonutItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, false, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.DONUT.get();
	}

	// 回復
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
		int taste = getTaste(stack) + 1;
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
		if (item == FoodInit.DONUT_CINNAMON.get())
			return CINNAMON;
		if (item == FoodInit.DONUT_COCOA.get())
			return COCOA;
		if (item == FoodInit.DONUT_BERRY.get())
			return BERRY;
		if (item == FoodInit.DONUT_MINT.get())
			return MINT;
		if (item == FoodInit.DONUT_COCONUT.get())
			return COCONUT;
		if (item == FoodInit.DONUT_JELLY.get())
			return JELLY;
		if (item == FoodInit.DONUT_ROSE.get())
			return ROSE;
		return SUGAR;
	}

	public static final EntityRenderData SUGAR = new EntityRenderData("food/donut_sugar", 1.0F, 0F);
	public static final EntityRenderData CINNAMON = new EntityRenderData("food/donut_cinnamon", 1.0F, 0F);
	public static final EntityRenderData COCOA = new EntityRenderData("food/donut_cocoa", 1.0F, 0F);
	public static final EntityRenderData BERRY = new EntityRenderData("food/donut_berry", 1.0F, 0F);
	public static final EntityRenderData MINT = new EntityRenderData("food/donut_mint", 1.0F, 0F);
	public static final EntityRenderData COCONUT = new EntityRenderData("food/donut_coconut", 1.0F, 0F);
	public static final EntityRenderData JELLY = new EntityRenderData("food/donut_jelly", 1.0F, 0F);
	public static final EntityRenderData ROSE = new EntityRenderData("food/donut_rose", 1.0F, 0F);

	public static enum ModelType {
		RING,
		JELLY;

		public static ModelType getType(Item item) {
			if (item == FoodInit.DONUT_JELLY.get())
				return JELLY;
			if (item == FoodInit.DONUT_ROSE.get())
				return JELLY;
			return RING;
		}
	}

}
