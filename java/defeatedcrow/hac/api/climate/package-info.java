/**
 * Copyright (c) defeatedcrow, 2013
 * URL:http://defeatedcrow.jp/modwiki/Mainpage
 * defeatedcrow's mods are distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL.
 * Please check the License(MMPL_1.0).txt included in the package file of this Mod.
 */

/**
 * このAPIは、World上の座標(BlockPos)の持つ気候要因を扱うものです。<br>
 * HeatTier(温度)、Humidity(湿度)、Airflow(通気)の要因があり、Biomeや周辺のBlockによって決まります。<br>
 * <br>
 * BiomeやBlockを追加したい場合はDCsClimateAPI.register、<br>
 * 座標のClimateを確認したい場合はDCsClimateAPI.calculatorを使用して下さい。
 */
@API(
		apiVersion = "0.1",
		owner = "dcs_climate|lib",
		provides = "DCsHaCAPI|climate")
package defeatedcrow.hac.api.climate;

import net.minecraftforge.fml.common.API;

