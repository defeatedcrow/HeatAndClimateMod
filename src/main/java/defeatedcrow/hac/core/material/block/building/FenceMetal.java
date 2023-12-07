package defeatedcrow.hac.core.material.block.building;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FenceMetal extends FenceWoodDC {

	public FenceMetal(String n) {
		super(n, BlockBehaviour.Properties.copy(Blocks.STONE));
		setTexDir("build/");
	}

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
	}

}
