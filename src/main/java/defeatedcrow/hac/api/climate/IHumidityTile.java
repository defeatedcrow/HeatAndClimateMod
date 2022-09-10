package defeatedcrow.hac.api.climate;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/** Blockに実装するもの */
public interface IHumidityTile {

	DCHumidity getHumidity(Level world, BlockPos targrt, BlockPos source);

}
