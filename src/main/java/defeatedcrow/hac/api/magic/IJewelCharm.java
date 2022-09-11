package defeatedcrow.hac.api.magic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

/**
 * チャーム<br>
 * プレイヤーのインベントリ上部9スロットにこのインターフェイスを実装したItemが入っていた場合、<br>
 * チャームであると判定され効果が発動します。
 */
public interface IJewelCharm extends IJewel {

	/**
	 * DIFFENCE<br>
	 * プレイヤーがダメージを受けたときに呼ばれ、軽減量を返す
	 */
	float reduceDamage(LivingEntity owner, DamageSource source, float damage, ItemStack charm);

	/**
	 * DIFFENCE<br>
	 * 生物がダメージを受けたときに呼ばれ、被弾時のアクションを起こす。<br>
	 */
	boolean onDiffence(LivingEntity owner, DamageSource source, float damage, ItemStack charm);

	// Attack charm
	/**
	 * ATTACK<br>
	 * プレイヤーがダメージを与えたときに呼ばれ、ダメージ増加倍率を返す。
	 */
	float increaceDamage(LivingEntity owner, LivingEntity attackTarget, DamageSource source, float damage,
			ItemStack charm);

	/**
	 * ATTACK<br>
	 * 生物がダメージを与えたときに呼ばれ、アクションを起こす。<br>
	 */
	boolean onAttacking(LivingEntity owner, LivingEntity attackTarget, DamageSource source, float damage,
			ItemStack charm);

	// Tool charm
	/**
	 * TOOL<br>
	 * プレイヤーがブロックを破壊した時に呼ばれる。<br>
	 * trueの場合、BreakEventをキャンセルする
	 */
	boolean onToolUsing(LivingEntity owner, BlockPos pos, BlockState state, ItemStack charm);

	// Constant charm
	/**
	 * CONSTANT<br>
	 * プレイヤーのTick更新ごとに呼ばれる常時効果<br>
	 */
	void constantEffect(LivingEntity owner, ItemStack charm);

	// X key using
	/**
	 * KEY<br>
	 * プレイヤーがコンフィグで設定したUseキーを押すと呼ばれ、アクションを起こす。<br>
	 *
	 * @return
	 */
	boolean onUsing(LivingEntity owner, ItemStack charm);

	// active check
	/**
	 * チャームが使用可能かどうか。<br>
	 */
	boolean isActive(LivingEntity owner, ItemStack charm);

	/**
	 * チャームが使用可能かを切り替える。<br>
	 */
	void setActive(LivingEntity owner, ItemStack charm, boolean flag);

}
