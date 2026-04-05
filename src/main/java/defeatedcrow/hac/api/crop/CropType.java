package defeatedcrow.hac.api.crop;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum CropType {

	ALLIUM, // ネギ
	AMARANTH, // アカザ
	AMARYLLIS, // アマリリス
	APIUM, // セリ
	AROIDS, // サトイモ
	ASTER, // キク
	BEECH, // ブナ
	BRASSICA, // アブラナ
	CAMELLIA, // ツバキ
	CAPSICUM, // トウガラシ
	CEREALS, // ムギ
	CHERRY, // サクラ
	CINNAMON, // クスノキ
	CITRUS, // カンキツ
	ERICA, // ツツジ
	EUPHORBIA, // トウダイグサ
	GINGER, // ショウガ
	GOURD, // ウリ
	GRAPE, // ブドウ
	HERB, // シソ
	IRIS, // アヤメ
	KNOTWEED, // タデ
	LILY, // ユリ
	LOTUS, // ハス
	MALLOW, // アオイ
	MORNINGGLORY, // ヒルガオ
	MORUS, // クワ
	MYRTLE, // フトモモ
	OLIVE, // モクセイ
	ORCHID, // ラン
	PALM, // ヤシ
	PEAS, // マメ
	PEDALIA, // ゴマ
	RANUNCULUS, // キンポウゲ
	REED, // アシ
	RICE, // イネ
	ROSE, // バラ
	RUBIA, // アカネ
	SOLANUM, // ナス
	SUMAC, // ウルシ
	YAM, // ヤマイモ
	TRADING; // 交易限定種

	public MutableComponent localize() {
		return Component.translatable("dcs.enum.croptype." + this.toString());
	}

	public static MutableComponent basename() {
		return Component.translatable("dcs.enum.croptype.name");
	}

	public static CropType getFromName(String name) {
		if (name != null)
			for (CropType crop : CropType.values()) {
				if (crop.toString().equalsIgnoreCase(name)) {
					return crop;
				}
			}
		return TRADING;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
