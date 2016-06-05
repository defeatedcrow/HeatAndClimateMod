package defeatedcrow.hac.api.heat;

import java.util.List;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import defeatedcrow.hac.api.climate.DCHeatTier;

/**
 * 熱利用BlockやTileEntity、Entityに持たせる伝熱機能。
 */
public interface IHeatReceiver {

	/**
	 * update処理用に持っているHeatSourceの座標リスト
	 */
	List<BlockPos> getSourceList();

	/**
	 * Climate、HeatSourceリストを加味した実際に使用中のClimate値
	 */
	int getCurrentClimateCode();

	/**
	 * 受け入れ可能なHeatTier<br>
	 * NORMALのみ受け入れ可能な装置は特に熱以外のエネルギーは電気や力学エネルギーなど、温度概念のない他MODエネルギーと互換できる。
	 */
	DCHeatTier[] getAccessibleHeatTier();

	/**
	 * HeatSourceの走査半径
	 */
	int getCheckingRange();

	/**
	 * update更新間隔<br>
	 * 毎tickの更新ではなく、インターバル中は記憶中のClimate値をもとに更新処理をする。
	 */
	int getChecikngCooltime();

	/**
	 * 受け入れ可能かどうか
	 */
	boolean canReceiveHeat(World world, BlockPos pos, int amount, DCHeatTier tier);

	/**
	 * 稼働中かどうか
	 */
	boolean isActive(World world, BlockPos pos);

}
