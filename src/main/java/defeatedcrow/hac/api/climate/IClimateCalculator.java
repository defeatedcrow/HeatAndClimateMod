package defeatedcrow.hac.api.climate;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * Heat、Air、Humの計算。
 */
public interface IClimateCalculator {

	/**
	 * 範囲内のClimateを求める。<br>
	 * 範囲は固定。
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 * @param range
	 *        : 走査半径
	 * @param horizontal
	 *        : posと同高度の平面範囲を調べる
	 */
	IClimate getClimate(Level Level, BlockPos pos);

	/**
	 * 範囲内のHeatTierを求める。<br>
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 * @param range
	 *        : 走査半径
	 * @param horizontal
	 *        : posと同高度の平面範囲を調べる
	 */
	DCHeatTier getAverageTemp(Level Level, BlockPos pos);

	/**
	 * 範囲内の最も高いHeatTierを求める。<br>
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 * @param range
	 *        : 走査半径
	 * @param horizontal
	 *        : posと同高度の平面範囲を調べる
	 */
	DCHeatTier getMostHeat(Level Level, BlockPos pos);

	/**
	 * 範囲内の最も低いHeatTierを求める。
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 * @param range
	 *        : 走査半径
	 * @param horizontal
	 *        : posと同高度の平面範囲を調べる
	 */
	DCHeatTier getMostCold(Level Level, BlockPos pos);

	/**
	 * 範囲内の湿りを計算する。<br>
	 * WET Biome: +1<br>
	 * Material.water +1<br>
	 * Raining +1<br>
	 * IHumidityTile -1 ~ +1<br>
	 * ~ -1: DRY<br>
	 * 0: NORMAL<br>
	 * +1 ~: WET
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 * @param range
	 *        : 走査半径
	 * @param horizontal
	 *        : posと同高度の平面範囲を調べる
	 */
	DCHumidity getHumidity(Level Level, BlockPos pos, int r, boolean horizontal);

	/**
	 * 範囲内の通気の有無を求める。<br>
	 * AirBlockが2個以下: TIGHT<br>
	 * AirBlockが3個以上: 屋内ならNORMAL、屋外はBiome標準<br>
	 * IAirflowTile(WIND)あり: WIND<br>
	 * 雷雨時の屋外: WIND
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 * @param range
	 *        : 走査半径
	 * @param horizontal
	 *        : posと同高度の平面範囲を調べる
	 */
	DCAirflow getAirflow(Level Level, BlockPos pos);

	/**
	 * 対象のブロックに登録された温度を得る。<br>
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 * @param b
	 *        :チェック対象がHotならtrue、coldならfalse
	 */
	DCHeatTier getBlockHeatTier(Level Level, BlockPos target, BlockPos source, boolean b);

	/**
	 * 対象のブロックに登録された湿度を得る。<br>
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 */
	DCHumidity getBlockHumidity(Level Level, BlockPos target, BlockPos source);

	/**
	 * 対象のブロックに登録された通気を得る。<br>
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 */
	DCAirflow getBlockAirflow(Level Level, BlockPos target, BlockPos source);

	/**
	 * 範囲内の最も低いHeatTierの座標を求める。
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 * @param range
	 *        : 走査半径
	 */
	BlockPos getMaxHeatPos(Level Level, BlockPos pos, int range);

	/**
	 * 範囲内の最も低いHeatTierの座標を求める。
	 *
	 * @param Level
	 *        : 対象のLevel
	 * @param pos
	 *        : 対象のBlockPos
	 * @param range
	 *        : 走査半径
	 */
	BlockPos getMaxColdPos(Level Level, BlockPos pos, int range);

}
