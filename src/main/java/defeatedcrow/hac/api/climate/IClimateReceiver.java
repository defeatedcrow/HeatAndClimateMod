package defeatedcrow.hac.api.climate;

/**
 * TileEntityに実装するもの。<br>
 * 外部からClimateを受け取って計算式に使う。
 */
public interface IClimateReceiver {

	void receiveHeatTier(DCHeatTier heat);

	void receiveHumidity(DCHumidity hum);

	void receiveAirflow(DCAirflow air);

	void setClientClimate(int clm);

	IClimate getClientClimate();

	IClimate resultClimate();
}
