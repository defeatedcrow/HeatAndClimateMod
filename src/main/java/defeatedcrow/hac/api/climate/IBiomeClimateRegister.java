package defeatedcrow.hac.api.climate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

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
	 *        : 登録対象Biome
	 * @param temp
	 *        : 気温
	 * @param hum
	 *        : 湿度
	 * @param airflow
	 *        : 通気
	 */
	void addBiomeClimate(Biome biome, DCHeatTier temp, DCHumidity hum, DCAirflow airflow);

	void addBiomeClimate(Biome biome, DCHeatTier temp, DCHumidity hum, DCAirflow airflow, boolean hasSeason);

	void setNoSeason(Biome biome);

	Map<ResourceLocation, ? extends IClimate> getClimateList();

	List<ResourceLocation> getNoSeasonList();

	/**
	 * 現在の環境チェック。
	 * 未登録Biomeの場合はTypeによって自動生成
	 */
	Optional<IClimate> getClimateFromBiome(Level world, BlockPos pos);

	Optional<IClimate> getClimateFromBiome(ResourceLocation biomeID);

	/** 補正後の値を得る */
	DCHeatTier getHeatTier(Level world, BlockPos pos);

	/** 補正後の値を得る */
	DCAirflow getAirflow(Level world, BlockPos pos);

	/** 補正後の値を得る */
	DCHumidity getHumidity(Level world, BlockPos pos);

	/** 登録情報の確認 */
	Optional<DCHeatTier> getRegisteredHeatTier(ResourceLocation biomeID);

	/** 登録情報の確認 */
	Optional<DCHumidity> getRegisteredHumidity(ResourceLocation biomeID);

	/** 登録情報の確認 */
	Optional<DCAirflow> getRegisteredAirflow(ResourceLocation biomeID);

}
