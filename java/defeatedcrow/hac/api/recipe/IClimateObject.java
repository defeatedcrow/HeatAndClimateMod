package defeatedcrow.hac.api.recipe;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import defeatedcrow.hac.api.climate.IClimate;

/**
 * アップデート処理内でClimateレシピを扱うオブジェクトに持たせる。
 * メソッド内で、Climate更新、レシピ処理などを全部行う。
 */
public interface IClimateObject {

	/** in Block#updateTick method */
	IClimate onUpdateClimate(World world, BlockPos pos, IBlockState state);

	boolean onClimateChange(World world, BlockPos pos, IBlockState state, IClimate climate);

	String getSEName(int meta);

	boolean playSEOnChanging(int meta);

	/** true: update処理後にworld#scheduleUpdateを呼ぶ。 */
	boolean isForcedTickUpdate();

	/**
	 * IClimateをチェックする範囲。<br>
	 * {heat, hum, air}になるよう返すこと。
	 */
	int[] checkingRange();

}
