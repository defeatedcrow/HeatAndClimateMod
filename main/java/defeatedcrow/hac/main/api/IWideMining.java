package defeatedcrow.hac.main.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IWideMining {

	public int getMiningRange(EntityPlayer player, ItemStack stack, int level);

}
