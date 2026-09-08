package defeatedcrow.hac.core.material.item.tool;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;

public class ItemMosquitoCoil extends MaterialItemDC {

	public ItemMosquitoCoil(String s, TagKey<Item> pair) {
		super(new Item.Properties().defaultDurability(8).craftRemainder(CoreInit.MOSQUITO_COIL.get()), s, pair);
		defeatedcrow.hac.core.material.tabs.CreativeTabDC.add(CoreInit.MACHINE, this);
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		MutableComponent tasteName = Component.translatable("dcs.tip.mosquito_coil");
		tasteName.withStyle(ChatFormatting.GRAY);
		list.add(tasteName);
	}

}
