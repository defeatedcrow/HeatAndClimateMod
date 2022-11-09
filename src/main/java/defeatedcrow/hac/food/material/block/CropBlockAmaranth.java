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
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class CropBlockAmaranth extends ClimateCropBaseBlock {

	public CropBlockAmaranth(CropTier t) {
		super(t);
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE5, Integer.valueOf(0)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.STAGE5);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_amaranth_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/leaf_0")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/leaf_1")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaranth_" + getSpeciesName(cropTier) + "_2")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaranth_" + getSpeciesName(cropTier) + "_f")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaranth_" + getSpeciesName(cropTier) + "_c")));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_amaranth_" + getSpeciesName(cropTier)));
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.AMARANTH;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.SINGLE;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.BLOCK_AM_GLASSWORT.get();
		case RARE:
			return FoodInit.BLOCK_AM_SPINACH.get();
		default:
			return FoodInit.BLOCK_AM_GOOSEFOOT.get();
		}
	}

	@Override
	public Item getCropItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.CROP_AM_GLASSWORT.get();
		case RARE:
			return FoodInit.CROP_AM_SPINACH.get();
		default:
			return FoodInit.CROP_AM_GOOSEFOOT.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_AM_GOOSEFOOT.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_AM_GLASSWORT.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_AM_SPINACH.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		switch (t) {
		case COMMON:
			return ImmutableList.of(SoilType.FARMLAND, SoilType.SAND);
		case WILD:
			return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		default:
			return ImmutableList.of(SoilType.FARMLAND);
		}
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.COMMON) {
			return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		} else if (t == CropTier.RARE) {
			return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		} else {
			return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		}
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		if (t == CropTier.WILD) {
			return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
		}
		if (t == CropTier.COMMON) {
			return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET, DCHumidity.UNDERWATER);
		}
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("PLAINS", "SAVANNA");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "glasswort";
		if (tier == CropTier.RARE)
			return "spinach";
		return "goosefoot";
	}

}
