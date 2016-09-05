package defeatedcrow.hac.main.event;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.magic.MagicInit;

public class OnMiningEventDC {

	private final Random rand = new Random();

	@SubscribeEvent
	public void preMining(PlayerEvent.BreakSpeed event) {
		if (event.getEntityPlayer() != null) {
			boolean hasCharm = false;
			for (int i = 9; i < 18; i++) {
				ItemStack check = event.getEntityPlayer().inventory.getStackInSlot(i);
				if (check != null && check.getItem() != null && check.getItem() == MagicInit.pendant) {
					int m = check.getMetadata();
					if (m == 9) {
						hasCharm = true;
					}
				}
			}

			event.setNewSpeed(event.getOriginalSpeed());
			if (hasCharm) {
				event.setNewSpeed(event.getNewSpeed() + 2.0F);
				event.setCanceled(false);
			}
		}
	}

	@SubscribeEvent
	public void onMining(BlockEvent.HarvestDropsEvent event) {
		if (event.getHarvester() != null && !event.getWorld().isRemote) {
			IBlockState state = event.getState();
			ItemStack held = event.getHarvester().getHeldItemMainhand();
			int level = event.getFortuneLevel() + 1;
			if (state == null || held == null)
				return;

			if (state.getBlock() == Blocks.TALLGRASS && held.getItem() instanceof ItemShears) {
				if (rand.nextFloat() < 0.25F * level) {
					event.getDrops().add(new ItemStack(FoodInit.crops, 1, 9));
				}
			}
		}
	}

}
