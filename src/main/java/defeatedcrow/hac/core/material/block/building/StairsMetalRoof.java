package defeatedcrow.hac.core.material.block.building;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

public class StairsMetalRoof extends StairsWoodDC {

	public StairsMetalRoof(String n, RegistryObject<Block> baseBlock) {
		super(n, BlockBehaviour.Properties.copy(Blocks.STONE), baseBlock);
		setTexDir("build/");
	}

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
	}

}
