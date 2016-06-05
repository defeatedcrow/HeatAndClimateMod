package defeatedcrow.hac.api.climate;

import java.util.Map;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

/**
 * バイオームの気候登録。
 * BiomeDictionaryの情報と異なる内容を登録したい場合に使用する。
 */
public interface IBiomeClimateRegister {

	/**
	 * Biomeの環境の登録。<br>
	 * そのPosの基礎値として加算される。<br>
	 * 
	 * @param biome
	 *            : 登録対象Biome
	 * @param temp
	 *            : 気温
	 * @param hum
	 *            : 湿度
	 * @param airflow
	 *            : 通気
	 */
	void addBiomeClimate(Biome biome, DCHeatTier temp, DCHumidity hum, DCAirflow airflow);

	Map<Integer, ? extends IClimate> getClimateList();

	/**
	 * 現在の環境チェック。
	 * 未登録Biomeの場合はTypeによって自動生成
	 */
	IClimate getClimateFromBiome(World world, BlockPos pos);

	IClimate getClimateFromBiome(Biome biome);

	DCHeatTier getHeatTier(World world, BlockPos pos);

	DCAirflow getAirflow(World world, BlockPos pos);

	DCHumidity getHumidity(World world, BlockPos pos);

	DCHeatTier getHeatTier(Biome biome);

	DCAirflow getAirflow(Biome biome);

	DCHumidity getHumidity(Biome biome);

	/**
	 * climateを0bAABBCCCのintとして表現したものと互換性を持たせる。
	 * NBT用。
	 */
	IClimate getClimateFromInt(int i);

	IClimate getClimateFromParam(DCHeatTier heat, DCHumidity hum, DCAirflow air);
}
