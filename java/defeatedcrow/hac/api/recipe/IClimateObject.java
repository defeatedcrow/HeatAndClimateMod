package defeatedcrow.hac.api.recipe;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * アップデート処理内でClimateレシピを扱うオブジェクトに持たせる。
 * メソッド内で、Climate更新、レシピ処理などを全部行う。
 */
public interface IClimateObject {

	void onUpdateClimate(World world, BlockPos pos, IBlockState state);

}
