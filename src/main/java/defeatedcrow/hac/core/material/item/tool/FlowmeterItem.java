package defeatedcrow.hac.core.material.item.tool;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.fluids.FluidUtil;

public class FlowmeterItem extends CraftingItemDC {

	public FlowmeterItem(String n) {
		super(n, new Item.Properties().tab(CoreInit.MACHINE).stacksTo(1), TagDC.ItemTag.CRAFT_FLOWMETER);
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
		if (res != null) {
			FluidUtil.getFluidHandler(level, res.getBlockPos(), res.getDirection()).ifPresent(handler -> {
				if (!level.isClientSide && player instanceof ServerPlayer sp) {
					int cap = handler.getTankCapacity(0);
					int fluid = handler.getFluidInTank(0) == null ? 0 : handler.getFluidInTank(0).getAmount();
					MutableComponent mes = Component.translatable("dcs.tip.amount").append(Component.literal(": ")).append(Component.literal(fluid + "/" + cap + "mB")).withStyle(ChatFormatting.AQUA);
					sp.sendSystemMessage(mes);
					player.swing(hand, true);
				} else {
					level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
				}
			});
			return InteractionResultHolder.success(card);
		}
		return InteractionResultHolder.pass(card);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tasteName = Component.translatable("dcs.tip.checker");
		tasteName.withStyle(ChatFormatting.GRAY);
		list.add(tasteName);
		super.appendHoverText(item, level, list, flag);
	}

}
