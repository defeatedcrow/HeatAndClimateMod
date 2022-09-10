package defeatedcrow.hac.api.climate;

import net.minecraft.world.level.block.state.BlockState;

/*
 * 気候計算に寄与しないブロック
 */
public interface IClimateIgnoreBlock {

	boolean isActive(BlockState state);

}
