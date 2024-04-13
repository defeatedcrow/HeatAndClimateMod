package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.LayerStoneBlock;
import net.minecraft.world.item.ItemStack;

public class AdobeBlock extends LayerStoneBlock {

	public AdobeBlock(String s) {
		super(s);
	}

	@Override
	public BlockType getDropType() {
		return BlockType.ITEM;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(CoreInit.ADOBE_BRICK_ITEM.get(), 4);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return new ItemStack(this);
	}

}
