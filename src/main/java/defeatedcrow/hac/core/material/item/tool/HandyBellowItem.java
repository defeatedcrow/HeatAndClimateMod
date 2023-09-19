package defeatedcrow.hac.core.material.item.tool;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.IClimateReceiver;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class HandyBellowItem extends CraftingItemDC {

	public HandyBellowItem(String n) {
		super(n, new Item.Properties().tab(CoreInit.MACHINE).stacksTo(1), null);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult res = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		if (res.getType() == HitResult.Type.BLOCK) {
			return onBlockHit(level, player, hand, itemstack, (BlockHitResult) res);
		} else {
			return InteractionResultHolder.fail(itemstack);
		}
	}

	public InteractionResultHolder<ItemStack> onBlockHit(Level level, Player player, InteractionHand hand, ItemStack card, BlockHitResult res) {
		player.startUsingItem(hand);
		if (!level.isClientSide && level.getBlockEntity(res.getBlockPos()) != null) {
			BlockEntity blockentity = level.getBlockEntity(res.getBlockPos());
			if (blockentity instanceof IClimateReceiver tile) {
				tile.receiveAirflow(DCAirflow.WIND);
				level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.SMOKER_SMOKE, SoundSource.PLAYERS, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
				player.swing(hand, true);
			}
			return InteractionResultHolder.success(card);
		}
		return InteractionResultHolder.pass(card);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tasteName = Component.translatable("dcs.tip.bellow");
		tasteName.withStyle(ChatFormatting.YELLOW);
		list.add(tasteName);
		super.appendHoverText(item, level, list, flag);
	}

}
