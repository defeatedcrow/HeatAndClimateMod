package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CropBlockGrape extends CropBaseVine {

	public CropBlockGrape(CropTier t) {
		super(t);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_grape_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/grape_0")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/grape_1")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/grape_2")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/grape_" + getSpeciesName(cropTier) + "_f")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/grape_" + getSpeciesName(cropTier) + "_c")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/grape_d")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side", ImmutableMap.of("crop", "dcs_climate:block/crop/vine/grape_" + getSpeciesName(cropTier) + "_g")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side", ImmutableMap.of("crop", "dcs_climate:block/crop/vine/grape_g_d")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side", ImmutableMap.of("crop", "dcs_climate:block/crop/vine/grape_" + getSpeciesName(cropTier) + "_b")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side", ImmutableMap.of("crop", "dcs_climate:block/crop/vine/grape_b_d")),
		    new JsonModelDC("dcs_climate:block/dcs_build_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/grape_" + getSpeciesName(cropTier) + "_o_n")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side_overlay", ImmutableMap.of("overlay", "dcs_climate:block/crop/vine/grape_" + getSpeciesName(cropTier) + "_o_f")),
		    new JsonModelDC("dcs_climate:block/dcs_build_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/grape_" + getSpeciesName(cropTier) + "_o_f")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side_overlay", ImmutableMap.of("overlay", "dcs_climate:block/crop/vine/grape_" + getSpeciesName(cropTier) + "_o_c")),
		    new JsonModelDC("dcs_climate:block/dcs_build_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/grape_" + getSpeciesName(cropTier) + "_o_c")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side_overlay", ImmutableMap.of("overlay", "dcs_climate:block/crop/vine/grape_o_d")),
		    new JsonModelDC("dcs_climate:block/dcs_build_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/grape_o_d")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0", "1", "2", "f", "c", "d", "ground", "ground_d", "side", "side_d", "top", "overlay_f", "top_f", "overlay_c", "top_c", "overlay_d", "top_d");
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_grape_" + getSpeciesName(cropTier)));
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.GRAPE;
	}

	@Override
	public BlockState getFlowerState(BlockState state) {
		return state.setValue(DCState.STAGE6, 3);
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return 5;
	}

	@Override
	public float wildCropSpreadChance() {
		return 0.01F;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.BLOCK_GR_COMMON.get();
		case RARE -> FoodInit.BLOCK_GR_WHITE.get();
		default -> FoodInit.BLOCK_GR_WILD.get();
		};
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_GR_COMMON.get();
		case RARE -> FoodInit.CROP_GR_WHITE.get();
		default -> FoodInit.CROP_GR_WILD.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_GR_WILD.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_GR_COMMON.get());
		case RARE -> Optional.of(FoodInit.BLOCK_GR_WHITE.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier tier) {
		return switch (tier) {
		case WILD, COMMON -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		default -> ImmutableList.of(SoilType.FARMLAND);
		};
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
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
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("MOUNTAIN", "FOREST");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return ImmutableList.of("LOWLAND");
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "common";
		if (tier == CropTier.RARE)
			return "white";
		return "wild";
	}

}
