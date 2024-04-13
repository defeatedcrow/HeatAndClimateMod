package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.core.material.block.AlloyDustBlockDC;
import net.minecraft.world.item.ItemStack;

public class AdobeWetBlock extends AlloyDustBlockDC {

	public AdobeWetBlock(String s) {
		super(s);
	}

	@Override
	public BlockType getDropType() {
		return BlockType.ITEM;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.SHOVEL;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(this);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return new ItemStack(this);
	}

}
