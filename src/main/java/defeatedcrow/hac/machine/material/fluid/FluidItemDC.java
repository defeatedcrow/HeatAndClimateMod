package defeatedcrow.hac.machine.material.fluid;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.fluids.FluidStack;

public abstract class FluidItemDC extends ItemDC {

	public FluidItemDC(Properties prop, TagKey<Item> pair) {
		super(prop, pair);
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		if (!DCUtil.isEmpty(item) && item.hasTag() && item.getTag().contains(TagKeyDC.getTankKey(1), 10)) {
			CompoundTag tankTag = item.getTag().getCompound(TagKeyDC.getTankKey(1));
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(tankTag);
			if (fluid.isEmpty()) {
				MutableComponent com = Component.literal("EMPTY");
				list.add(com);
			} else {
				MutableComponent com = Component.literal("FLUID: ");
				com.append(fluid.getDisplayName()).append(" " + fluid.getAmount() + "mB").withStyle(ChatFormatting.AQUA);
				list.add(com);
			}
		}
	}

}
