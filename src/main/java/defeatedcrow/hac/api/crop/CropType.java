package defeatedcrow.hac.api.crop;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum CropType {

	ALLIUM, // ネギ
	AMARANTH, // アカザ
	APIUM, // セリ
	AROIDS, // サトイモ
	ASTER, // キク
	BEECH, // ブナ
	BRASSICA, // アブラナ
	CAMELLIA, // ツバキ
	CAPSICUM, // トウガラシ
	CASHEW, // ウルシ
	CEREALS, // ムギ
	CHERRY, // サクラ
	CINNAMON, // クスノキ
	CITRUS, // カンキツ
	ERICA, // ツツジ
	GINGER, // ショウガ
	GOURD, // ウリ
	GRAPE, // ブドウ
	KNOTWEED, // タデ
	HERB, // シソ
	IRIS, // アヤメ
	LOTUS, // ハス
	MALLOW, // アオイ
	MORNINGGLORY, // ヒルガオ
	MORUS, // クワ
	OLIVE, // モクセイ
	ORCHID, // ラン
	PALM, // ヤシ
	PEAS, // マメ
	PEDALIA, // ゴマ
	RANUNCULUS, // キンポウゲ
	REED, // アシ
	RICE, // イネ
	ROSE, // バラ
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
