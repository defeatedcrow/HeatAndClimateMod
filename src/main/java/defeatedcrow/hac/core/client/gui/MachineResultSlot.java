package defeatedcrow.hac.core.client.gui;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MachineResultSlot extends Slot {

	public MachineResultSlot(Container cont, int i1, int i2, int i3) {
		super(cont, i1, i2, i3);
	}

	@Override
	public boolean mayPlace(ItemStack item) {
		return false;
	}
}
