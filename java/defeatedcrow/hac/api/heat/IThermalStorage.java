package defeatedcrow.hac.api.heat;

import defeatedcrow.hac.api.climate.DCHeatTier;

/**
 * 熱量をタンクのように貯められる蓄熱機能の概念
 */
public interface IThermalStorage {

	/**
	 * 現在のHeatTier。同じでないと受け入れできない。
	 */
	DCHeatTier getCurrentHeatTier();

	/**
	 * 現在の蓄熱量。
	 */
	int getCurrentHeatAmount();

	/**
	 * 最大の蓄熱量。
	 */
	int getMaxHeatAmount();

	/**
	 * 熱量を受け入れるメソッド。
	 */
	int receiveHeatAmount(DCHeatTier tier, int amount, boolean isSimulate);

	/**
	 * 熱量を放出するメソッド。
	 */
	int extractHeatAmount(DCHeatTier tier, int amount, boolean isSimulate);

}
