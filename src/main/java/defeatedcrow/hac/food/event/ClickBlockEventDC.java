package defeatedcrow.hac.food.event;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.ClimateCropBaseBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClickBlockEventDC {

	@SubscribeEvent
	public static void onClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getLevel() != null) {
			Level level = event.getLevel();
			Player player = event.getEntity();
			ItemStack item = event.getItemStack();
			BlockState target = event.getLevel().getBlockState(event.getPos());

			// fertilizer
			if (!item.isEmpty() && TagUtil.matchTag("fertilizer", item.getItem()).isPresent()) {
				int m = DCState.getInt(target, BlockStateProperties.MOISTURE);
				int f = DCState.getInt(target, DCState.FERTILE);
				if (target.is(TagDC.BlockTag.FARMLAND) && m > 0 && f < 3) {
					if (!level.isClientSide) {
						// 肥料アイテムを耕地にまく
						BlockState next = FoodInit.FERTILE.get().defaultBlockState().setValue(DCState.FERTILE, f + 1).setValue(BlockStateProperties.MOISTURE, m);
						level.setBlockAndUpdate(event.getPos(), next);
						item.shrink(1);
						level.levelEvent(1505, event.getPos().above(), 0);
					}
					event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
				}
			}
			// green manure
			else if (!item.isEmpty() && TagUtil.matchTag("hoes", item.getItem()).isPresent()) {
				int m = DCState.getInt(target, DCState.STAGE5);
				if (target.is(TagDC.BlockTag.CROP_GREEN_MANURES) && (m == -1 || m > 1)) {
					// 下のブロック
					BlockState below = level.getBlockState(event.getPos().below());
					if (below.is(BlockTags.DIRT) || below.is(TagDC.BlockTag.FARMLAND)) {
						int f = DCState.getInt(below, DCState.FERTILE);
						if (!level.isClientSide && f < 3) {
							// 緑肥をすき込む
							if (target.getBlock() instanceof ClimateCropBaseBlock) {
								((ClimateCropBaseBlock) target.getBlock()).breakAndDropSeed(level, event.getPos(), target);
							} else {
								level.setBlockAndUpdate(event.getPos(), Blocks.AIR.defaultBlockState());
							}
							BlockState next = FoodInit.FERTILE.get().defaultBlockState().setValue(DCState.FERTILE, f + 1).setValue(BlockStateProperties.MOISTURE, 7);
							level.setBlockAndUpdate(event.getPos().below(), next);
							if (player != null) {
								item.hurtAndBreak(1, player, (c) -> {
									c.broadcastBreakEvent(event.getHand());
								});
							}
							level.playSound(player, event.getPos(), SoundEvents.HOE_TILL, SoundSource.BLOCKS, 0.5F, 1.0F);
							level.levelEvent(1505, event.getPos(), 0);
						}
						event.setCancellationResult(InteractionResult.SUCCESS);
						event.setUseItem(Result.ALLOW);
					}
				} else if (target.getBlock() instanceof ClimateCropBaseBlock) {
					if (!level.isClientSide) {
						// 確実に種を取れる
						if (target.getBlock() instanceof ClimateCropBaseBlock) {
							((ClimateCropBaseBlock) target.getBlock()).breakAndDropSeed(level, event.getPos(), target);
						} else {
							level.setBlockAndUpdate(event.getPos(), Blocks.AIR.defaultBlockState());
						}
						if (player != null) {
							item.hurtAndBreak(1, player, (c) -> {
								c.broadcastBreakEvent(event.getHand());
							});
						}
						level.playSound(player, event.getPos(), SoundEvents.HOE_TILL, SoundSource.BLOCKS, 0.5F, 1.0F);
						level.levelEvent(1505, event.getPos(), 0);
					}
				}
			}
		}
	}

}
