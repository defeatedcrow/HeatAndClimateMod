package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class LeavesSweet extends LeavesCropBlockDC {

	public LeavesSweet() {
		super(CropType.BEECH, CropTier.RARE);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_beech_sweet";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_spr")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_smr")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_aut")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_wtr")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_f")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_c")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_spr")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_smr")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_aut")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_wtr")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_f")),
			new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/beech_sweet_leaves_c")));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.of(new String[] { "false_0", "false_1", "false_2", "false_3", "false_4", "false_5", "true_0", "true_1", "true_2", "true_3", "true_4", "true_5" });
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_false_0");
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.BEECH;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_BH_SWEET.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_BH_SWEET.get();
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return Optional.empty();
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return Lists.newArrayList();
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.CRYOGENIC, DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL);
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
		return "sweet";
	}

}
