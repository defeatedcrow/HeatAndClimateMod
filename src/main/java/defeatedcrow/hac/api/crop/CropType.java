package defeatedcrow.hac.api.crop;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum CropType {

	ALLIUM, // ネギ
	AMARANTH, // アカザ
	APIUM, // セリ
	BEECH, // ブナ
	BRASSICA, // アブラナ
	CAMELLIA, // ツバキ
	CAPSICUM, // トウガラシ
	CEREALS, // ムギ
	CHERRY, // サクラ
	CINNAMON, // クスノキ
	CITRUS, // カンキツ
	ERICA, // ツツジ
	GINGER, // ショウガ
	GRAPE, // ブドウ
	KNOTWEED, // タデ
	HERB, // シソ
	LOTUS, // ハス
	MALLOW, // アオイ
	MORNINGGLORY, // ヒルガオ
	MORUS, // クワ
	OLIVE, // モクセイ
	PALM, // ヤシ
	PEAS, // マメ
	PEDARIA, // ゴマ
	REED, // アシ
	RICE, // イネ
	SOLANUM, // ナス
	TRADING; // 交易限定種

	public MutableComponent localize() {
		return Component.translatable("dcs.enum.croptype." + this.toString());
	}

	public static MutableComponent basename() {
		return Component.translatable("dcs.enum.croptype.name");
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
