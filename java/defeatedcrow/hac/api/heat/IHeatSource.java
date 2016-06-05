package defeatedcrow.hac.api.heat;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import defeatedcrow.hac.api.climate.DCHeatTier;

/**
 * 熱源TileEntityに持たせる伝熱機能。<br>
 * instanceofチェックの対象がTileEntityのみなので、他のオブジェクトに対応できない。
 */
public interface IHeatSource {

	/**
	 * 発生HeatTier
	 */
	DCHeatTier getTier();

	/**
	 * 一度の受け渡しで伝熱出来る最大量
	 */
	int getMaxTransfer();

	/**
	 * このTileの持つ熱量を使用する
	 */
	int useHeatTransfer(World world, BlockPos pos, boolean sim);

	/**
	 * 熱源が稼働中かどうか
	 */
	boolean isActive(World world, BlockPos pos);

}
