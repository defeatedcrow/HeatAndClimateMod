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
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class LeavesCamellia extends LeavesCropBlockDC {

	public LeavesCamellia() {
		super(CropType.CAMELLIA, CropTier.WILD);
		this.setSeason(EnumSeason.WINTER_EARLY, EnumSeason.AUTUMN_EARLY);
		this.flowerSeasons.add(EnumSeason.WINTER_LATE);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_camellia_oil";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/camellia_oil_leaves")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/camellia_oil_leaves")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/camellia_oil_leaves")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/camellia_oil_leaves")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/camellia_oil_leaves_f")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/camellia_oil_leaves_c")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0", "0", "0", "0", "f", "c");
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("stage6=0", "stage6=1", "stage6=2", "stage6=3", "stage6=4", "stage6=5");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_0");
	}

	@Override
	protected void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		// 自然消滅なし
	}

	@Override
	protected float getSeedChance() {
		return 1F;
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.CAMELLIA;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_CM_OIL.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_CM_OIL.get();
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_CM_OIL.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_CM_SCHIMA.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_CM_TEA.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
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
		return "oil";
	}

}
