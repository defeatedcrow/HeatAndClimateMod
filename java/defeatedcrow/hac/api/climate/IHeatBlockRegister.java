package defeatedcrow.hac.api.climate;

import java.util.Map;

import net.minecraft.block.Block;

/**
 * IHeatTile等の環境に影響させるインターフェイスを実装できない、バニラBlockなどをここで登録する。<br>
 * IHeatSourceと異なり、環境要因なので燃料などは消費しない、恒久的に稼働する熱源を登録する。
 */
public interface IHeatBlockRegister {

	void registerHeatBlock(Block block, DCHeatTier temp);

	void registerHumBlock(Block block, DCHumidity hum);

	void registerAirBlock(Block block, DCAirflow air);

	DCHeatTier getHeatTier(Block block);

	DCHumidity getHumidity(Block block);

	DCAirflow getAirflow(Block block);

	boolean isRegisteredHeat(Block block);

	boolean isRegisteredHum(Block block);

	boolean isRegisteredAir(Block block);

	Map<Block, DCHeatTier> getHeatList();

	Map<Block, DCHumidity> getHumList();

	Map<Block, DCAirflow> getAirList();

}
