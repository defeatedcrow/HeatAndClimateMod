package defeatedcrow.hac.api.climate;

import net.minecraft.util.math.MathHelper;

/**
 * 湿度。
 * 基本的にバイオームによって変動する概念だが、水を置いてレベルを上げられる。<br>
 * DRY: 乾燥バイオームや乾いたスポンジから得られる。乾燥、禁水条件のレシピに要求される。<br>
 * NORMAL: 通常の環境。<br>
 * WET: 高湿度のバイオームや、範囲内に水や湿度を上げるブロックがある状態。発酵や植物育成に必要になる。<br>
 * UNDERWATER: 完全に水没しており空気ブロックが存在しない状態。水中でしか生育しない植物などに必要。
 */
public enum DCHumidity {
	DRY(0),
	NORMAL(1),
	WET(2),
	UNDERWATER(3);

	private final int id;

	private DCHumidity(int i) {
		id = i;
	}

	public int getID() {
		return id;
	}

	public static DCHumidity getTypeByID(int i) {
		MathHelper.clamp_int(i, 0, 3);
		for (DCHumidity e : values()) {
			if (i == e.id)
				return e;
		}
		return NORMAL;
	}

}
