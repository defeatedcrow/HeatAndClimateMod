package defeatedcrow.hac.api.recipe;

import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Climateを条件に進む、1:1交換のレシピ。<br>
 * 材料は辞書対応。<br>
 */
public interface IClimateSmelting {

	/**
	 * Input登録内容
	 */
	Object getInput();

	ItemStack getOutput();

	/**
	 * macth条件判定用、鉱石辞書変換後のInputリスト
	 */
	List<ItemStack> getProcessedInput();

	/**
	 * Input条件判定
	 */
	boolean matcheInput(ItemStack item);

	boolean matchClimate(int code);

	/**
	 * Climate条件判定
	 */
	boolean matchClimate(IClimate climate);

	/**
	 * 追加条件
	 * trueで条件クリア
	 */
	boolean additionalRequire(Level level, BlockPos pos);

	/**
	 * ブロック設置状態で進行する
	 */
	boolean hasBlockProcess();

	/**
	 * エンティティ設置状態で進行する
	 */
	boolean hasEntityProcess();

	/**
	 * ドロップアイテム状態で進行する
	 */
	boolean hasDropItemProcess();

	/**
	 * recipeの更新tick間隔
	 */
	int recipeFrequency();

	/**
	 * randomTickのブロックかどうか
	 */
	boolean hasRandomTick();

	/**
	 * おもにレシピ条件表示機能用
	 * HeatTierのみ、Tier+1まで対応範囲になる
	 */
	List<DCHeatTier> requiredHeat();

	List<DCHumidity> requiredHum();

	List<DCAirflow> requiredAir();

	/** レシピが有効かどうか */
	boolean isActive();
}
