package defeatedcrow.hac.api.crop;

import net.minecraft.client.resources.language.I18n;

public enum CropType {

	ALLIUM(), // ネギ
	AMARANTH, // アカザ
	APIUM, // セリ
	BEECH, // ブナ
	BRASSICA, // アブラナ
	CAPSICUM, // トウガラシ
	CEREALS, // ムギ
	CHELLY, // サクラ
	CINNAMON, // クスノキ
	CITRUS, // カンキツ
	GINGER, // ショウガ
	GRAPE, // ブドウ
	KNOTWEED, // タデ
	HERB, // シソ
	MALLOW, // アオイ
	MORNINGGROLY, // ヒルガオ
	LOTUS, // ハス
	PEAS, // マメ
	REED, // アシ
	RICE, // イネ
	SOLANUM, // ナス
	TRADING; // 交易限定種

	public String localize() {
		return I18n.get("dcs.name.croptype." + this.toString());
	}

	public static String basename() {
		return I18n.get("dcs.tip.croptype.name");
	}
}
