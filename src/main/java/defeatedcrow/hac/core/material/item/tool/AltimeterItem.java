package defeatedcrow.hac.core.material.item.tool;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class AltimeterItem extends CraftingItemDC {

	public AltimeterItem(String n) {
		super(n, new Item.Properties().tab(CoreInit.MACHINE).stacksTo(1), TagDC.ItemTag.CRAFT_ALTIMETER);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!DCUtil.isEmpty(item)) {
			ClimateCore.proxy.getClientPlayer().ifPresent(player -> {
				MutableComponent tasteName = Component.translatable("dcs.tip.altitude").append(": " + player.getBlockY());
				tasteName.withStyle(ChatFormatting.AQUA);
				list.add(tasteName);
			});
		}
		super.appendHoverText(item, level, list, flag);
	}

}
