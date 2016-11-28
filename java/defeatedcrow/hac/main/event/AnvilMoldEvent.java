package defeatedcrow.hac.main.event;

import defeatedcrow.hac.main.api.IPressMold;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AnvilMoldEvent {

	@SubscribeEvent
	public void onAnvilUpdate(AnvilUpdateEvent event) {
		ItemStack left = event.getLeft();
		ItemStack moldItem = event.getRight();
		ItemStack ret = event.getOutput();
		if (left != null && moldItem != null && moldItem.getItem() instanceof IPressMold && ret == null) {
			IPressMold mold = (IPressMold) moldItem.copy().getItem();
			if (mold.getOutput(moldItem) == null) {
				// DCLogger.debugLog("anvil event cycle");
				ItemStack next = new ItemStack(moldItem.getItem(), moldItem.stackSize, moldItem.getItemDamage());
				mold.setOutput(next, left);
				event.setOutput(next);
				event.setCost(1);
			}
		}
	}

}
