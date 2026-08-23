package defeatedcrow.hac.api.event;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

// for Mixin
@Cancelable
public class DCItemUpdateEvent extends Event {

	public final ItemEntity entity;

	public DCItemUpdateEvent(ItemEntity en) {
		this.entity = en;
	}

	public boolean post() {
		MinecraftForge.EVENT_BUS.post(this);
		return this.isCanceled();
	}
}
