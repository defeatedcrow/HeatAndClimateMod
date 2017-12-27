package defeatedcrow.hac.main.block;

import defeatedcrow.hac.core.base.DCItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class FuelItemBlock extends DCItemBlock {

	private final int[] fuels;

	public FuelItemBlock(Block block, int[] amounts) {
		super(block);
		fuels = amounts;
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		int i = stack.getItemDamage();
		if (fuels != null && i < fuels.length) {
			return fuels[i];
		} else {
			return 0;
		}
	}

}
