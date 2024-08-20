package defeatedcrow.hac.api.material;

import net.minecraft.world.item.ItemStack;

/* 何かしら Client Side でアイテムを表示するEntityBlock */
public interface IDisplayTile {

	ItemStack getDisplay(int slot);

	void setDisplay(int slot, ItemStack item);

}
