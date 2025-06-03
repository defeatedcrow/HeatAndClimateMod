package defeatedcrow.hac.core.material.item.tool;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;

public class LureItem extends MaterialItemDC {

	public LureItem(CreativeModeTab tab, String s, TagKey<Item> pair) {
		super(tab, s, pair);
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		Enchantment enchantment = null;
		if (item.getItem() == CoreInit.LURE_JIG_IRON.get()) {
			enchantment = Enchantments.FISHING_SPEED;
		}
		if (item.getItem() == CoreInit.LURE_JIG_GLITTER.get()) {
			enchantment = CoreInit.BIG_GAME_FISHING.get();
		}
		if (item.getItem() == CoreInit.LURE_EGI_FIRE.get()) {
			enchantment = CoreInit.SQUID_FISHING.get();
		}
		if (item.getItem() == CoreInit.LURE_WORM_CLAW.get()) {
			enchantment = CoreInit.BOTTOM_FISHING.get();
		}
		if (item.getItem() == CoreInit.LURE_MAGNET.get()) {
			enchantment = Enchantments.FISHING_LUCK;
		}
		if (enchantment != null) {
			MutableComponent t1 = Component.translatable("dcs.tip.active_element").withStyle(ChatFormatting.GRAY);
			list.add(t1);
			MutableComponent t2 = Component.literal("").append(enchantment.getFullname(1)).withStyle(ChatFormatting.GRAY);
			list.add(t2);
		}
	}

}
