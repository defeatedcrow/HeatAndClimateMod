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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemMosquitoCoil extends MaterialItemDC {

	public ItemMosquitoCoil(String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(CoreInit.MACHINE).defaultDurability(8).craftRemainder(CoreInit.MOSQUITO_COIL.get()), s, pair);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tasteName = Component.translatable("dcs.tip.mosquito_coil");
		tasteName.withStyle(ChatFormatting.YELLOW);
		list.add(tasteName);
		super.appendHoverText(item, level, list, flag);
	}

}
