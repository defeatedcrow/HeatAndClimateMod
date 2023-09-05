package defeatedcrow.hac.api.climate;

/**
 * このMODでは、気候の要素(温度、湿度、通気)をClimateとしてセットで扱う。<br>
 * また、通気、湿度、温度をintに変換して0bAABBCCCという形にもできるため、int型でNBTなどに持たせておくことが出来る。
 */
public interface IClimate {

	DCHeatTier getHeat();

	DCHumidity getHumidity();

	DCAirflow getAirflow();

	int getClimateInt();

	IClimate addTempTier(int tier);

	IClimate addHumTier(int tier);

	IClimate addAirTier(int tier);

	IClimate setHeat(DCHeatTier heat);

	IClimate setHumidity(DCHumidity hum);

	IClimate setAirflow(DCAirflow air);

}
