package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import net.minecraft.ChatFormatting;
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

	private int taste = 2;
	private boolean seasoning = false;

	public FoodMaterialItemDC(CreativeModeTab tab, String s, TagKey<Item> pair) {
		super(tab, s, pair);
	}

	public FoodMaterialItemDC taste(int i) {
		taste = Mth.clamp(0, taste, 4);
		taste = i;
		return this;
	}

	public FoodMaterialItemDC seasoning() {
		seasoning = true;
		return this;
	}

	@Override
	public int getTaste(ItemStack item) {
		return taste;
	}

	@Override
	public void setTaste(ItemStack item, int i) {
		taste = Mth.clamp(0, taste, 4);
		taste = i;
	}

	@Override
	public boolean isSeasoning() {
		return seasoning;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		int taste = getTaste(item) + 1;
		MutableComponent tasteName = Component.translatable("dcs.tip.foodtaste." + taste);
		tasteName.withStyle(ChatFormatting.YELLOW);
		list.add(tasteName);
		super.appendHoverText(item, level, list, flag);
	}

}
