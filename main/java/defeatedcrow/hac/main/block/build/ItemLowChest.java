package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemLowChest extends DCItemBlock {

	public ItemLowChest(Block block) {
		super(block);
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if (!DCUtil.isEmpty(stack) && stack.getItem() == this) {
			NBTTagCompound tag = new NBTTagCompound();
			NBTTagList nbttaglist = new NBTTagList();
			tag.setTag("InvItems", nbttaglist);
			stack.setTagCompound(tag);
		}
	}

}
