package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
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
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE6, 0).setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.STAGE6, DCState.WILD);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_amaranth_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList
		    .of(new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/leaf_0")), new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/leaf_1")),
		        new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaranth_" + getSpeciesName(cropTier) + "_2")),
		        new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaranth_" + getSpeciesName(cropTier) + "_f")),
		        new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaranth_" + getSpeciesName(cropTier) + "_c")),
		        new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaranth_" + getSpeciesName(cropTier) + "_d")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0", "1", "2", "3", "4", "5");
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("stage6=0", "stage6=1", "stage6=2", "stage6=3", "stage6=4", "stage6=5");
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
	public BlockState getFlowerState(BlockState state) {
		if (getTier() == CropTier.COMMON) {
			return state.setValue(DCState.STAGE6, 4);
		}
		return state.setValue(DCState.STAGE6, 3);
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return switch (t) {
		case RARE -> 2;
		default -> 5;
		};
	}

	@Override
	public float wildCropSpreadChance() {
		return 0.03F;
	}

	@Override
	public boolean canHarvest(BlockState state) {
		CropStage stage = this.getCurrentStage(state);
		if (this.getTier() == CropTier.RARE) {
			return stage == CropStage.GROWN || stage == CropStage.FLOWER;
		} else {
			return stage == CropStage.GROWN;
		}
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.BLOCK_AM_GLASSWORT.get();
		case RARE -> FoodInit.BLOCK_AM_SPINACH.get();
		default -> FoodInit.BLOCK_AM_GOOSEFOOT.get();
		};
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_AM_GLASSWORT.get();
		case RARE -> FoodInit.CROP_AM_SPINACH.get();
		default -> FoodInit.CROP_AM_GOOSEFOOT.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_AM_GOOSEFOOT.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_AM_GLASSWORT.get());
		case RARE -> Optional.of(FoodInit.BLOCK_AM_SPINACH.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return switch (t) {
		case COMMON -> ImmutableList.of(SoilType.FARMLAND, SoilType.SAND, SoilType.MUD);
		case WILD -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		default -> ImmutableList.of(SoilType.FARMLAND);
		};
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
		return switch (t) {
		case WILD -> ImmutableList.of("PLAINS", "SAVANNA");
		case COMMON -> ImmutableList.of("SWAMP", "BEACH");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("COLD");
		default -> Lists.newArrayList();
		};
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
