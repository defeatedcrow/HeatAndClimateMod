package defeatedcrow.hac.core.material.item.tool;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.ITierItem;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.core.util.TierDC;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ItemPickaxeDC extends PickaxeItem implements IItemDC, ITierItem {

	public final TierDC tier;
	final TagKey<Item> tag;

	public ItemPickaxeDC(TierDC tierIn, TagKey<Item> pair) {
		super(tierIn, (int) tierIn.getAttackDamageBonus(), -2.8F, new Item.Properties().durability(tierIn.getUses()).tab(CoreInit.MACHINE));
		tier = tierIn;
		tag = pair;
	}

	@Override
	public TagKey<Item> getPairTag() {
		return tag;
	}

	@Override
	public TierDC getTier() {
		return tier;
	}

	// pickup
	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
		if (state != null && state.getBlock() == Blocks.BEDROCK && pos.getY() > level.getMinBuildHeight()) {
			Player player = context.getPlayer();
			ItemStack itemstack = context.getItemInHand();
			if (player.isCrouching() && !DCUtil.isEmpty(itemstack) && itemstack.getItem() instanceof ItemPickaxeDC pickaxe && pickaxe.tier.getLevel() > 3) {
				if (!level.isClientSide()) {
					if (player instanceof ServerPlayer serverPlayer) {
						CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemstack);
					}
					double d0 = (double) (level.random.nextFloat() * 0.15F) * DCUtil.getRandomSign();
					double d1 = level.random.nextFloat() * 0.15F + 0.15D;
					double d2 = (double) (level.random.nextFloat() * 0.15F) * DCUtil.getRandomSign();
					ItemEntity drop = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, new ItemStack(Blocks.BEDROCK));
					drop.setDefaultPickUpDelay();
					drop.setDeltaMovement(d0, d1, d2);
					level.addFreshEntity(drop);
					level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

					if (player != null) {
						itemstack.hurtAndBreak(2, player, p -> { p.broadcastBreakEvent(context.getHand()); });
					}
				}
				level.playSound(player, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
				return InteractionResult.sidedSuccess(level.isClientSide);
			}
		}

		return super.useOn(context);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!DCUtil.isEmpty(stack) && stack.getItem() instanceof ItemPickaxeDC pickaxe && pickaxe.tier.getLevel() > 3) {
			MutableComponent text = Component.translatable("dcs.tip.pickaxe_cobalt");
			text.withStyle(ChatFormatting.YELLOW);
			list.add(text);
		}
	}

}
