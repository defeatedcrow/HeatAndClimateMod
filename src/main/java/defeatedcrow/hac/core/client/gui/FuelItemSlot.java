package defeatedcrow.hac.core.client.gui;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class FuelItemSlot extends Slot {

	public FuelItemSlot(Container cont, int i1, int i2, int i3) {
		super(cont, i1, i2, i3);
	}

	@Override
	public boolean mayPlace(ItemStack item) {
		return isFuel(item);
	}

	protected boolean isFuel(ItemStack item) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(item, RecipeType.SMELTING) > 0;
	}

}
