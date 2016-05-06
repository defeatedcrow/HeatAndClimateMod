package defeatedcrow.hac.api.magic;

public enum CharmType {

	/* 常時発動型 */
	CONSTANT,
	/* 被ダメージ時 追加効果 */
	DEFFENCE,
	/* 攻撃追加効果 */
	ATTACK,
	/* ツール使用時追加効果 */
	TOOL,
	/* 任意発動型 */
	INSTANT,
	/* どれにも当てはまらず、直接指定で効果を扱うもの */
	SPECIAL;

}
