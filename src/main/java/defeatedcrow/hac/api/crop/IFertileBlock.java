package defeatedcrow.hac.api.crop;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public interface IFertileBlock {

	public int getFertile(BlockGetter level, BlockPos pos, BlockState state);

}
