package defeatedcrow.hac.api.magic;

public enum CharmType {

	/* カードなど右クリック使用のアイテム */
	INSTANT,
	/* 常時発動型 */
	CONSTANT,
	/* 被ダメージ時 追加効果 */
	DEFFENCE,
	/* 攻撃追加効果 */
	ATTACK,
	/* ツール使用時追加効果 */
	DIG,
	/* Xキーによる任意発動型 */
	KEY,
	/* どれにも当てはまらず、直接指定で効果を扱うもの */
	SPECIAL,
	/* Typeを問わない検索など */
	ALL;

	public boolean match(CharmType target) {
		return target == this || target == ALL;
	}

}
