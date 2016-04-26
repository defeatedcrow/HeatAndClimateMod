package defeatedcrow.hac.api.climate;

import net.minecraft.util.MathHelper;

/**
 * エアフロー。
 * 通常バイオームではNormalで、周辺ブロックの要因によって変化する。<br>
 * TIGHT: 範囲内に通気がない状態。密閉状態のかまどなど。<br>
 * NORMAL: 空が見えない状態。屋内判定。<br>
 * FLOW: 範囲内のいずれかのブロックで空が見えている状態。通気のある屋外。<br>
 * WIND: 風を起こすブロックなどが範囲内にある状態。空気を消費したり、換気が必要なレシピに要求される。
 */
public enum DCAirflow {
	TIGHT(0), NORMAL(1), FLOW(2), WIND(3);

	private final int id;

	private DCAirflow(int i) {
		id = i;
	}

	public int getID() {
		return id;
	}

	public static DCAirflow getTypeByID(int i) {
		MathHelper.clamp_int(i, 0, 3);
		for (DCAirflow e : values()) {
			if (i == e.id)
				return e;
		}
		return NORMAL;
	}

}
