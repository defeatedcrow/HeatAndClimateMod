package defeatedcrow.hac.api.climate;

import net.minecraft.core.BlockPos;

/**
 * TileEntityに実装するもの。<br>
 * BlockPosや内部の状況によって発生するClimateが細かく変化する。<br>
 * ターゲットがClimateReceiveTileの場合は任意のタイミングでPos登録することで通常の走査エリア外から干渉できる。<br>
 * ちなみにCalculatorはこれを走査しない。そのためBlock側にもIHeatTile等Block用インターフェイスを別途で実装する。
 */
public interface IClimateTileEntity {

	DCHeatTier getHeatTier(BlockPos target);

	DCHumidity getHumidity(BlockPos target);

	DCAirflow getAirflow(BlockPos target);

	boolean isActive();
}
