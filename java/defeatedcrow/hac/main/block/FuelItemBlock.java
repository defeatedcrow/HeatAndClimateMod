package defeatedcrow.hac.main.block;

import defeatedcrow.hac.core.base.DCItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelItemBlock extends DCItemBlock implements IFuelHandler {

	private final int[] fuels;

	public FuelItemBlock(Block block, int[] amounts) {
		super(block);
		fuels = amounts;
	}

	@Override
	public int getBurnTime(ItemStack stack) {
		int i = stack.getItemDamage();
		if (fuels != null && i < fuels.length)
			return fuels[i];
		else
			return 0;
	}

}
