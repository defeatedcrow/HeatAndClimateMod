package defeatedcrow.hac.api.crop;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum CropType {

	ALLIUM, // ネギ
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
	MORNINGGLORY, // ヒルガオ
	LOTUS, // ハス
	PEAS, // マメ
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
