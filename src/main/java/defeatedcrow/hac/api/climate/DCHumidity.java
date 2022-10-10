package defeatedcrow.hac.api.climate;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

/**
 * 湿度。
 * 基本的にバイオームによって変動する概念だが、水を置いてレベルを上げられる。<br>
 * DRY: 乾燥バイオームや乾いたスポンジから得られる。乾燥、禁水条件のレシピに要求される。<br>
 * NORMAL: 通常の環境。<br>
 * WET: 高湿度のバイオームや、範囲内に水や湿度を上げるブロックがある状態。発酵や植物育成に必要になる。<br>
 * UNDERWATER: 完全に水没しており空気ブロックが存在しない状態。水中でしか生育しない植物などに必要。
 */
public enum DCHumidity {
	DRY(0, 0x950000),
	NORMAL(1, 0x00E115),
	WET(2, 0x77EAFF),
	UNDERWATER(3, 0x0060FF);

	private final int id;
	private final int color;

	private DCHumidity(int i, int c) {
		id = i;
		color = c;
	}

	public int getID() {
		return id;
	}

	public int[] getColor() {
		int r = (color >> 8) & 255;
		int g = (color >> 4) & 255;
		int b = color & 255;
		return new int[] { r, g, b };
	}

	public int getColorInt() {
		return color;
	}

	public MutableComponent localize() {
		return Component.translatable("dcs.enum.hum." + toString());
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public DCHumidity addTier(int i) {
		int ret = id + i;
		return getTypeByID(ret);
	}

	public static DCHumidity getTypeByID(int i) {
		if (i < 0)
			i = 0;
		if (i > 3)
			i = 3;
		for (DCHumidity e : values()) {
			if (i == e.id)
				return e;
		}
		return NORMAL;
	}

	public DCHumidity getAverageHumidity(DCHumidity another) {
		if (another != null && another != NORMAL) {
			int i = another == DRY ? -1 : 1;
			return addTier(i);
		}
		return this;
	}

	public static List<DCHumidity> createList() {
		List<DCHumidity> tiers = new ArrayList<DCHumidity>();

		for (DCHumidity t : DCHumidity.values()) {
			tiers.add(t);
		}

		return tiers;
	}

	public static DCHumidity getFromName(String name) {
		if (name != null)
			for (DCHumidity t : DCHumidity.values()) {
				if (t.name().equalsIgnoreCase(name)) {
					return t;
				}
			}
		return NORMAL;
	}

	public static MutableComponent basename() {
		return Component.translatable("dcs.enum.humidity_name");
	}

	public static MutableComponent basename2() {
		return Component.translatable("dcs.enum.humidity_name2");
	}

}
