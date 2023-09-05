package defeatedcrow.hac.core.material.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface ITileNBTHolder {

	public ItemStack getDropItem(ItemStack item, BlockEntity tile);

	public void setNBTFromItem(ItemStack item, BlockEntity tile);

}
