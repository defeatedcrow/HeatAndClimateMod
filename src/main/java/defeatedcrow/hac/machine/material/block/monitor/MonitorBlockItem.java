package defeatedcrow.hac.machine.material.block.monitor;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class MonitorBlockItem extends BlockItemDC {

	public MonitorBlockItem(String regName, Block block, Properties prop, TagKey<Item> pair) {
		super(regName, block, prop, pair);
	}

	@Override
	public InteractionResult place(BlockPlaceContext cont) {
		if (cont != null && cont.getPlayer() != null && !cont.getPlayer().isCrouching()) {
			return onBlockHit(cont.getLevel(), cont.getPlayer(), cont.getHand(), cont.getItemInHand(), cont.getClickedPos(), cont.getClickedFace()).getResult();
		} else {
			return super.place(cont);
		}
	}

	public InteractionResultHolder<ItemStack> onBlockHit(Level level, Player player, InteractionHand hand, ItemStack stack, BlockPos pos, Direction dir) {
		if (!DCUtil.isEmpty(stack) && level instanceof ServerLevel serverLevel) {
			CompoundTag tag = stack.getOrCreateTag();
			BlockPos p = pos.relative(dir.getOpposite());
			tag.putInt(TagKeyDC.DIRECTION, dir.getOpposite().get3DDataValue());
			tag.putInt(TagKeyDC.POS_X, p.getX());
			tag.putInt(TagKeyDC.POS_Y, p.getY());
			tag.putInt(TagKeyDC.POS_Z, p.getZ());
			stack.setTag(tag);

			if (player instanceof ServerPlayer sp) {
				MutableComponent mes = Component.translatable("dcs.tip.coodinate");
				MutableComponent mes2 = Component.literal(" X:" + p.getX());
				mes2.append(Component.literal(" Y:" + p.getY()));
				mes2.append(Component.literal(" Z:" + p.getZ()));
				sp.sendSystemMessage(mes);
				sp.sendSystemMessage(mes2);
			}
		}
		return InteractionResultHolder.success(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(stack, level, list, flag);
		MutableComponent tex1 = Component.translatable("dcs.tip.energy.indicator").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.BOLD);
		MutableComponent tex2 = Component.translatable("dcs.tip.energy.indicator.desc");
		if (ClimateCore.proxy.keyShiftPushed()) {
			list.add(tex1);
			list.add(tex2);

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
				MutableComponent mes3 = Component.translatable("dcs.tip.energy.indicator.desc2");
				list.add(mes3);
			}

		} else {
			list.add(tex1);
			list.add(Component.translatable("dcs.tip.shift"));
		}

	}

}
