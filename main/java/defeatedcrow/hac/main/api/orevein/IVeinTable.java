package defeatedcrow.hac.main.api.orevein;

import java.util.List;

import net.minecraft.block.Block;

public interface IVeinTable {

	/**
	 * 鉱脈のレイヤーブロック
	 */
	OreSet getLayerBlock();

	/**
	 * 鉱脈のタイプ
	 */
	EnumVein getType();

	/**
	 * 鉱脈の生成テーブル
	 */
	List<OreSet> getOreTable();

	void addOreToTable(int weight, Block block, int meta);

	void addOreToTable(int weight, Block block, int meta, Block secondBlock, int secondMeta, int secondChance);

	void removeOreFromTable(Block block, int meta);

}
