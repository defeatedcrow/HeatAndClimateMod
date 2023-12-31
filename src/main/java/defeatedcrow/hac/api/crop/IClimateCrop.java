package defeatedcrow.hac.api.crop;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IClimateCrop extends ICropData {

	// 対応する種や苗
	ItemStack getSeedItem(BlockState thisState);

	// 収穫物
	List<ItemStack> getCropItems(BlockState thisState, int fortune);

	// 成長に使用。育つ環境かどうか
	boolean isSuitableForGrowing(Level world, BlockPos pos, BlockState thisState);

	// 植え付けに使用。土壌のチェック
	boolean isSuitablePlace(BlockGetter world, BlockPos pos, BlockState thisState);

	// bonemealや収穫判定はenumでまとめた
	CropStage getCurrentStage(BlockState thisState);

	CropTier getTier();

	// 自然生成時のステート
	BlockState getFeatureState();

	// 成長後のステート
	BlockState getGrownState();

	// 収穫後のステート
	BlockState getHarvestedState(BlockState state);

	// 基底のステート
	BlockState setGroundState();

	// 成長させる
	int getGrowingChance(Level world, BlockPos pos, BlockState thisState);

	// 成長させる
	boolean onGrow(Level world, BlockPos pos, BlockState thisState);

	// 収穫
	boolean canHarvest(BlockState thisState);

	// 収穫
	boolean onHarvest(Level world, BlockPos pos, BlockState thisState, Player player);

	void afterHarvest(Level world, BlockPos pos, BlockState thisState);

	// 変異
	int getMutationChance(Level world, BlockPos pos, BlockState thisState);

	// 変異
	boolean onMutation(Level world, BlockPos pos, BlockState thisState, RandomSource random, int fertile);

}
