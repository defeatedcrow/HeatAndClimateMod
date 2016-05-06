package defeatedcrow.hac.main.event;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import defeatedcrow.hac.magic.item.ItemMagicalPendant;
import defeatedcrow.hac.main.block.ores.BlockOres;

public class OnMiningEventDC {

	private final Random rand = new Random();

	@SubscribeEvent
	public void preMining(PlayerEvent.BreakSpeed event) {
		if (event.entityPlayer != null) {
			boolean hasCharm = false;
			for (int i = 9; i < 18; i++) {
				ItemStack check = event.entityPlayer.inventory.getStackInSlot(i);
				if (check != null && check.getItem() != null && check.getItem() instanceof ItemMagicalPendant) {
					int m = check.getItemDamage();
					if (m == 9) {
						hasCharm = true;
					}
				}
			}

			event.newSpeed = event.originalSpeed;
			if (hasCharm) {
				event.newSpeed += 2.0F;
				event.setCanceled(false);
			}
		}
	}

	// @SubscribeEvent
	public void onMining(BlockEvent.HarvestDropsEvent event) {
		if (!event.world.isRemote && event.harvester != null) {
			boolean hasCharm = false;
			for (int i = 9; i < 18; i++) {
				ItemStack check = event.harvester.inventory.getStackInSlot(i);
				if (check != null && check.getItem() != null && check.getItem() instanceof ItemMagicalPendant) {
					int m = check.getItemDamage();
					if (m == 9) {
						hasCharm = true;
					}
				}
			}

			if (!event.isSilkTouching && hasCharm) {
				Block block = event.state.getBlock();
				if (block instanceof BlockOres) {
					// TODO 工事中
				}
			}
		}
	}

}
