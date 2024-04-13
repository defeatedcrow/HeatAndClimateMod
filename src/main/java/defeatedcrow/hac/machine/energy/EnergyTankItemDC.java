package defeatedcrow.hac.machine.energy;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class EnergyTankItemDC extends BlockItemDC {

	private int cap = 4000;

	public EnergyTankItemDC(String regName, Block block, Properties prop, TagKey<Item> pair) {
		super(regName, block, prop, pair);
	}

	public EnergyTankItemDC setCap(int c) {
		cap = c;
		return this;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(stack, level, list, flag);
		if (!DCUtil.isEmpty(stack) && stack.hasTag()) {
			if (stack.getTag().contains(TagKeyDC.ENERGY) && stack.getTag().contains(TagKeyDC.CAPACITY)) {
				int energy = stack.getTag().getInt(TagKeyDC.ENERGY);
				MutableComponent com = Component.translatable("dcs.tip.device.energy.battery.amount");
				com.append(energy + "/" + cap).withStyle(ChatFormatting.LIGHT_PURPLE);
				list.add(com);
			}
		}
	}

}
