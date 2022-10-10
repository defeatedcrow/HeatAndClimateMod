package defeatedcrow.hac.food.event;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.tag.TagDC;
import defeatedcrow.hac.core.material.tag.TagUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
				if (target.is(TagDC.BlockTag.FARMLAND) && DCState.getInt(target, BlockStateProperties.MOISTURE) > 0) {
					if (!level.isClientSide) {
						int m = DCState.getInt(target, BlockStateProperties.MOISTURE);
						BlockState next = FoodInit.FERTILE.get().defaultBlockState().setValue(DCState.FERTILE, 1).setValue(BlockStateProperties.MOISTURE, m);
						level.setBlockAndUpdate(event.getPos(), next);
						item.shrink(1);
						level.levelEvent(1505, event.getPos().above(), 0);
					}
					event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
				}
			}
		}
	}

}
