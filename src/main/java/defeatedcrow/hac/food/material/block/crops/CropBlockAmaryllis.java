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

public class CropBlockAmaryllis extends ClimateCropBaseBlock {

	public CropBlockAmaryllis(CropTier t) {
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
		return "food/cropblock_amaryllis_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaryllis_0")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaryllis_1")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaryllis_" + getSpeciesName(cropTier) + "_2")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaryllis_" + getSpeciesName(cropTier) + "_3")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaryllis_" + getSpeciesName(cropTier) + "_f")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/amaryllis_d")));
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
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_amaryllis_" + getSpeciesName(cropTier)));
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.AMARYLLIS;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.SINGLE;
	}

	@Override
	public BlockState getFlowerState(BlockState state) {
		return state.setValue(DCState.STAGE6, 4);
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return 4;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return switch (t) {
		case WILD -> FoodInit.BLOCK_AMR_SNOWDROP.get();
		case COMMON -> FoodInit.BLOCK_AMR_AMARYLLIS.get();
		case RARE -> FoodInit.BLOCK_AMR_DAFFODIL.get();
		default -> FoodInit.BLOCK_AMR_LYCORIS.get();
		};
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case WILD -> FoodInit.CROP_AMR_SNOWDROP.get();
		case COMMON -> FoodInit.CROP_AMR_AMARYLLIS.get();
		case RARE -> FoodInit.CROP_AMR_DAFFODIL.get();
		default -> FoodInit.CROP_AMR_LYCORIS.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_AMR_SNOWDROP.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_AMR_AMARYLLIS.get());
		case RARE -> Optional.of(FoodInit.BLOCK_AMR_DAFFODIL.get());
		default -> Optional.of(FoodInit.BLOCK_AMR_LYCORIS.get());
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		default -> ImmutableList.of(SoilType.FARMLAND);
		};
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return switch (t) {
		case COMMON, EPIC -> ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
		default -> ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL);
		};
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return switch (t) {
		case EPIC -> ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL);
		default -> ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
		};
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("FOREST", "COLD", "SNOWY");
		case COMMON -> ImmutableList.of("FOREST", "JUNGLE", "LUSH");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("HOT", "WATER");
		case COMMON -> ImmutableList.of("COLD", "DRY");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "amaryllis";
		if (tier == CropTier.RARE)
			return "daffodil";
		if (tier == CropTier.EPIC)
			return "lycoris";
		return "snowdrop";
	}

}
