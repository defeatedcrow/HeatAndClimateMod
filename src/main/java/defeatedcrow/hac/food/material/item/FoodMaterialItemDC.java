package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FoodMaterialItemDC extends MaterialItemDC implements IFoodTaste {

	private int taste = 0;
	private boolean seasoning = false;

	public FoodMaterialItemDC(CreativeModeTab tab, String s, TagKey<Item> pair) {
		super(tab, s, pair);
	}

	public FoodMaterialItemDC(Properties prop, String s, TagKey<Item> pair) {
		super(prop, s, pair);
	}

	public TagKey<Item> getFlavorTag() {
		switch (taste) {
		case -2:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR1;
		case -1:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR2;
		case 1:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR4;
		case 2:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR5;
		default:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR3;
		}
	}

	public FoodMaterialItemDC taste(int i) {
		taste = Mth.clamp(taste, -2, 2);
		taste = i;
		return this;
	}

	public FoodMaterialItemDC seasoning() {
		seasoning = true;
		return this;
	}

	@Override
	public int getTaste(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return 0;
		if (item.getTag() != null && item.getTag().contains(TagKeyDC.TASTE)) {
			int taste = item.getTag().getInt(TagKeyDC.TASTE);
			taste = Mth.clamp(taste, -2, 2);
			return taste;
		} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR)) {
			if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR5)) {
				return 2;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR4)) {
				return 1;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR3)) {
				return 0;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR2)) {
				return -1;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR1)) {
				return -2;
			}
		}
		return 0;
	}

	@Override
	public void setTaste(ItemStack item, int i) {
		if (!DCUtil.isEmpty(item)) {
			int taste = Mth.clamp(i, -2, 2);
			CompoundTag tag = item.getOrCreateTag();
			tag.putInt(TagKeyDC.TASTE, taste);
			item.setTag(tag);
		}
	}

	@Override
	public boolean isSeasoning() {
		return seasoning;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!DCUtil.isEmpty(item) && item.is(TagDC.ItemTag.SAPS)) {
			MutableComponent text1 = Component.translatable("dcs.tip.sap");
			text1.withStyle(ChatFormatting.YELLOW);
			list.add(text1);
		}
		super.appendHoverText(item, level, list, flag);
	}

}
