package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
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
		if (!DCUtil.isEmpty(item) && item.getItem() instanceof IFoodTaste && item.getTag() != null && item.getTag().contains(TagKeyDC.TASTE)) {
			int taste = item.getTag().getInt(TagKeyDC.TASTE);
			taste = Mth.clamp(taste, -2, 2);
			return taste;
		}
		return taste;
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
		int taste = getTaste(item) + 3;
		MutableComponent tasteName = Component.translatable("dcs.tip.foodtaste." + taste);
		tasteName.withStyle(ChatFormatting.YELLOW);
		list.add(tasteName);
		super.appendHoverText(item, level, list, flag);
	}

}
