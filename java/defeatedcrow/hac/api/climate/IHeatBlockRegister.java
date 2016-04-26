package defeatedcrow.hac.api.climate;

import java.util.Map;

import net.minecraft.block.Block;

/**
 * IHeatTile等の環境に影響させるインターフェイスを実装できない、バニラBlockなどをここで登録する。<br>
 * IHeatSourceと異なり、環境要因なので燃料などは消費しない、恒久的に稼働する熱源を登録する。
 */
public interface IHeatBlockRegister {

	void registerHeatBlock(Block block, int meta, DCHeatTier temp);

	void registerHumBlock(Block block, int meta, DCHumidity hum);

	void registerAirBlock(Block block, int meta, DCAirflow air);

	DCHeatTier getHeatTier(Block block, int meta);

	DCHumidity getHumidity(Block block, int meta);

	DCAirflow getAirflow(Block block, int meta);

	boolean isRegisteredHeat(Block block, int meta);

	boolean isRegisteredHum(Block block, int meta);

	boolean isRegisteredAir(Block block, int meta);

	Map<BlockSet, DCHeatTier> getHeatList();

	Map<BlockSet, DCHumidity> getHumList();

	Map<BlockSet, DCAirflow> getAirList();

}
