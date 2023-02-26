package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

public class LeavesPalm extends LeavesCropBlockDC {

	public LeavesPalm() {
		super(CropType.PALM, CropTier.WILD);
		this.setSeason(EnumSeason.SUMMER_EARLY, EnumSeason.SUMMER_LATE);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return false;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

	}

	@Override
	protected void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		// 自然消滅なし
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_palm";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/palm_leaves")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("stage6=0", "stage6=1", "stage6=2", "stage6=3", "stage6=4", "stage6=5");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_0");
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.PALM;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	// ドロップはなし
	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		return ret;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return null;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return null;
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return Optional.empty();
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.WARM, DCHeatTier.BOIL);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return Lists.newArrayList();
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "palm";
	}

	@Override
	public BlockState getGrownState() {
		return this.defaultBlockState();
	}

	@Override
	public BlockState getHarvestedState(BlockState state) {
		return this.defaultBlockState();
	}

	@Override
	public int getSeasonLeafStage(Level world, BlockPos pos) {
		return 0;
	}

	@Override
	public boolean canHarvest(BlockState thisState) {
		return false;
	}

	@Override
	public boolean onHarvest(Level world, BlockPos pos, BlockState thisState, Player player) {
		return false;
	}

}
