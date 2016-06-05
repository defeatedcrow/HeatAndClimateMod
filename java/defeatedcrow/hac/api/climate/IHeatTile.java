package defeatedcrow.hac.api.climate;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Blockに実装するもの。 */
public interface IHeatTile {

	DCHeatTier getHeatTier(World world, BlockPos pos);
}
