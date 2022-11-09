package defeatedcrow.hac.api.recipe;

import defeatedcrow.hac.api.climate.IClimate;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IClimateObject {

	IClimate onUpdateClimate(Level level, BlockPos pos, BlockState state);

	/** in Block#tick method */
	boolean onClimateChange(Level level, BlockPos pos, BlockState state, IClimate climate);

	SoundEvent getSE();

	boolean playSEOnChanging();

	/** true: update処理後にworld#scheduleUpdateを呼ぶ。 */
	boolean isForcedTickUpdate();

}
