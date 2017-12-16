package defeatedcrow.hac.main.api.orevein;

import java.util.List;

import net.minecraft.block.Block;

public interface IVeinTable {

	/**
	 * 鉱脈のレイヤーブロック
	 */
	OreSet getLayerBlock1();

	/**
	 * 鉱脈のレイヤーブロックが2種類ある場合に使用
	 */
	OreSet getLayerBlock2();

	/**
	 * 鉱脈のタイプ
	 */
	EnumVein getType();

	/**
	 * 鉱脈の生成テーブル
	 */
	List<OreSet> getOreTable1();

	/**
	 * 鉱脈の生成テーブル2<br>
	 * 一部の鉱脈は上半分と下半分で生成テーブルが異なる
	 */
	List<OreSet> getOreTable2();

	void addOreToTable1(int weight, Block block, int meta);

	void addOreToTable2(int weight, Block block, int meta);

	void addOreToTable1(int weight, Block block, int meta, Block secondBlock, int secondMeta, int secondChance);

	void addOreToTable2(int weight, Block block, int meta, Block secondBlock, int secondMeta, int secondChance);

	void removeOreFromTable1(Block block, int meta);

	void removeOreFromTable2(Block block, int meta);

}
