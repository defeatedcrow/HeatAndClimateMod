package defeatedcrow.hac.machine.material.block;

import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.material.block.ITileNBTHolder;

public abstract class MachineBaseBlockDC extends EntityBlockDC implements ITileNBTHolder {

	public MachineBaseBlockDC(Properties prop) {
		super(prop);
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
	public BlockType getDropType() {
		return BlockType.ENTITY_NBT;
	}

}
