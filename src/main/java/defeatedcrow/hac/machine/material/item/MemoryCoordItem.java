package defeatedcrow.hac.machine.material.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MemoryCoordItem extends MachineMaterialItem {

	public MemoryCoordItem(Rarity rare, String s) {
		super(rare, s, null);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(stack, level, list, flag);
		MutableComponent tex1 = Component.translatable("dcs.tip.memory_coord").withStyle(ChatFormatting.GRAY);
		if (ClimateCore.proxy.keyShiftPushed()) {
			list.add(tex1);

			if (!DCUtil.isEmpty(stack) && stack.hasTag()) {
				CompoundTag tag = stack.getTag();
				if (tag.contains(TagKeyDC.POS_X) && tag.contains(TagKeyDC.DIRECTION)) {
					int x = 0;
					if (tag.contains(TagKeyDC.POS_X))
						x = tag.getInt(TagKeyDC.POS_X);
					int y = 0;
					if (tag.contains(TagKeyDC.POS_Y))
						y = tag.getInt(TagKeyDC.POS_Y);
					int z = 0;
					if (tag.contains(TagKeyDC.POS_Z))
						z = tag.getInt(TagKeyDC.POS_Z);
					BlockPos pairPos = new BlockPos(x, y, z);
					int dir = -1;
					Direction side = Direction.DOWN;
					if (tag.contains(TagKeyDC.DIRECTION))
						dir = tag.getInt(TagKeyDC.DIRECTION);
					if (dir >= 0) {
						side = Direction.from3DDataValue(dir);
					}

					MutableComponent mes = Component.translatable("dcs.tip.coodinate");
					MutableComponent mes2 = (Component.literal("" + x));
					mes2.append(Component.literal("," + y));
					mes2.append(Component.literal("," + z));
					mes2.append(Component.literal("," + side));
					list.add(mes);
					list.add(mes2);
				}
			} else {
				MutableComponent mes3 = Component.translatable("dcs.tip.energy.indicator.desc2").withStyle(ChatFormatting.GRAY);
				list.add(mes3);
			}

		} else {
			list.add(tex1);
			list.add(Component.translatable("dcs.tip.shift"));
		}

	}

}
