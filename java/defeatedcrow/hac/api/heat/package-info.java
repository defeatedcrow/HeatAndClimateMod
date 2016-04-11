/**
 * Copyright (c) defeatedcrow, 2013
 * URL:http://defeatedcrow.jp/modwiki/Mainpage
 * defeatedcrow's mods are distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL.
 * Please check the License(MMPL_1.0).txt included in the package file of this Mod.
 */

/**
 * このAPIは、TileEntity等の装置の持つ熱エネルギーを扱うものです。<br>
 * 熱エネルギー(Heat)は、HeatTier(温度)と伝熱量(TransferAmount)のパラメータを持ち、<br>
 * Heatを利用するレシピでは、HeatTierの要件を満たさない熱源は利用できません。<br>
 * <br>
 * 実際の処理は、利用側(IHeatReceiver)のupdate処理内で熱源(IHeatSource)を走査、管理する。<br>
 */
@API(
		apiVersion = "0.1",
		owner = "dcs_climate|lib",
		provides = "DCsHaCAPI|heat")
package defeatedcrow.hac.api.heat;

import net.minecraftforge.fml.common.API;

