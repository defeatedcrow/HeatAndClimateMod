package defeatedcrow.hac.core.material.block.building;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SlabStoneDC extends SlabWoodDC {

	public SlabStoneDC(String n) {
		super(n, BlockBehaviour.Properties.copy(Blocks.STONE));
		setTexDir("ore/stone_");
	}

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
	}

}
