package defeatedcrow.hac.core.material.tile;

import net.minecraft.world.item.ItemStack;

public interface ITileNBTHolder {

	public ItemStack getDropItem(ItemStack item);

	public void setNBTFromItem(ItemStack item);

}
