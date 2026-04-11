package defeatedcrow.hac.food.event;

import defeatedcrow.hac.food.material.item.RawFishItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemTossEventDC {

	@SubscribeEvent
	public static void onClickEntity(ItemTossEvent event) {
		Player player = event.getPlayer();
		ItemEntity target = event.getEntity();
		if (player != null && target != null && !target.getItem().isEmpty()) {
			if (target.getItem().getItem() instanceof RawFishItem) {
				CompoundTag tag = target.getItem().getTag();
				tag.putBoolean("Released_Fish", true);
				target.getItem().setTag(tag);

			}
		}
	}

}
