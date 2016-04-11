package defeatedcrow.hac.api.climate;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/** Blockに実装するもの */
public interface IAirflowTile {

	DCAirflow getAirflow(World world, BlockPos pos);

}
