package defeatedcrow.hac.api.climate;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * 任意範囲のHeat、Air、Humの計算。<br>
 * あまり広範囲だと重いと思うので、getClimateのrangeは0~16の制限がかかる。<br>
 * また、現時点ではIHeatSourceはカウントせず、自然環境要素の確認のみである。<br>
 * IHeatSourceについては後ほど実装する。
 */
public interface IClimateCalculator {

	/**
	 * 範囲内のClimateを求める。<br>
	 * 熱源は真下中心で、温度差計算は無し。
	 * 
	 * @param world
	 *            : 対象のWorld
	 * @param pos
	 *            : 対象のBlockPos
	 * @param range
	 *            : 走査半径
	 * @param horizontal
	 *            : posと同高度の平面範囲を調べる
	 */
	IClimate getClimate(World world, BlockPos pos, int range);

	/**
	 * 範囲内の最も高いHeatTierを求める。<br>
	 * このMODの多くの装置では、熱源の場合のみ真下の座標を中心にチェックすることが多い。
	 * 
	 * @param world
	 *            : 対象のWorld
	 * @param pos
	 *            : 対象のBlockPos
	 * @param range
	 *            : 走査半径
	 * @param horizontal
	 *            : posと同高度の平面範囲を調べる
	 */
	DCHeatTier getHeatTier(World world, BlockPos pos, int range, boolean horizontal);

	/**
	 * 範囲内の最も低いHeatTierを求める。
	 * 
	 * @param world
	 *            : 対象のWorld
	 * @param pos
	 *            : 対象のBlockPos
	 * @param range
	 *            : 走査半径
	 * @param horizontal
	 *            : posと同高度の平面範囲を調べる
	 */
	DCHeatTier getColdTier(World world, BlockPos pos, int range, boolean horizontal);

	/**
	 * 範囲内のしめりを計算する。<br>
	 * WET Biome: +1<br>
	 * Material.water +1<br>
	 * Raining +1<br>
	 * IHumidityTile -1 ~ +1<br>
	 * ~ -1: DRY<br>
	 * 0: NORMAL<br>
	 * +1 ~: WET
	 * 
	 * @param world
	 *            : 対象のWorld
	 * @param pos
	 *            : 対象のBlockPos
	 * @param range
	 *            : 走査半径
	 * @param horizontal
	 *            : posと同高度の平面範囲を調べる
	 */
	DCHumidity getHumidity(World world, BlockPos pos, int range, boolean horizontal);

	/**
	 * 範囲内の通気の有無を求める。<br>
	 * AirBlockなし: TIGHT<br>
	 * AirBlockあり: Biome標準<br>
	 * IAirflowTile(FLOW)あり: FLOW
	 * 
	 * @param world
	 *            : 対象のWorld
	 * @param pos
	 *            : 対象のBlockPos
	 * @param range
	 *            : 走査半径
	 * @param horizontal
	 *            : posと同高度の平面範囲を調べる
	 */
	DCAirflow getAirflow(World world, BlockPos pos, int range, boolean horizontal);

}
