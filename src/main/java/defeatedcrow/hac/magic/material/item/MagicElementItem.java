package defeatedcrow.hac.magic.material.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class MagicElementItem extends MagicMaterialItemDC {

	public MagicElementItem(MagicColor c, String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(MagicInit.MAGIC).rarity(Rarity.RARE).stacksTo(1), c, s, pair);
	}

	public Enchantment getEnchantment() {
		if (getColor().isWhite)
			return Enchantments.MENDING;
		if (getColor().isBlue)
			return Enchantments.AQUA_AFFINITY;
		if (getColor().isBlack)
			return Enchantments.INFINITY_ARROWS;
		if (getColor().isRed)
			return Enchantments.SILK_TOUCH;
		if (getColor().isGreen)
			return Enchantments.BLOCK_FORTUNE;
		return Enchantments.MENDING;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent t1 = Component.translatable("dcs.tip.active_element").withStyle(ChatFormatting.GRAY);
		list.add(t1);
		MutableComponent t2 = Component.empty().append(getEnchantment().getFullname(1));
		t2.withStyle(getColor().chatColor);
		list.add(t2);
		super.appendHoverText(item, level, list, flag);
	}

}
