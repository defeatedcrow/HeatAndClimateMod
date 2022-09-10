package defeatedcrow.hac.api.climate;

import java.util.Map;
import java.util.Optional;

import defeatedcrow.hac.api.util.BlockSet;
import net.minecraft.world.level.block.state.BlockState;

/**
 * IHeatTile等の環境に影響させるインターフェイスを実装できない、バニラBlockなどをここで登録する。<br>
 * IHeatSourceと異なり、環境要因なので燃料などは消費しない、恒久的に稼働する熱源を登録する。
 */
public interface IHeatBlockRegister {

	void registerHeatBlock(BlockSet block, DCHeatTier temp);

	void registerHumBlock(BlockSet block, DCHumidity hum);

	void registerAirBlock(BlockSet block, DCAirflow air);

	Optional<DCHeatTier> getHeatTier(BlockState block);

	Optional<DCHumidity> getHumidity(BlockState block);

	Optional<DCAirflow> getAirflow(BlockState block);

	boolean isRegisteredHeat(BlockState block);

	boolean isRegisteredHum(BlockState block);

	boolean isRegisteredAir(BlockState block);

	Map<BlockSet, DCHeatTier> getHeatList();

	Map<BlockSet, DCHumidity> getHumList();

	Map<BlockSet, DCAirflow> getAirList();

}
