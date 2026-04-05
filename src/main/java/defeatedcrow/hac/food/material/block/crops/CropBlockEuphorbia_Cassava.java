package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

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
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CropBlockEuphorbia_Cassava extends ClimateCropBaseBlock {

	public CropBlockEuphorbia_Cassava(CropTier t) {
		super(t);
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE6, 0).setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.STAGE6, DCState.WILD);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext col) {
		int stage = DCState.getInt(state, DCState.STAGE6);
		if (stage == 0) {
			return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D);
		} else if (stage == 1) {
			return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D);
		}
		return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	}

	/* IClimateCrop */

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState().setValue(DCState.STAGE6, 4).setValue(DCState.WILD, true);
	}

	@Override
	protected BlockState getNextState(Level level, BlockPos pos, BlockState thisState, CropStage stage) {
		int age = DCState.getInt(thisState, DCState.STAGE6);
		// FLOWERステートは低確率で出現
		if (age == 2 && level.getRandom().nextInt(12) == 0) {
			return thisState.setValue(DCState.STAGE6, 3);
		}
		return super.getNextState(level, pos, thisState, stage);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_euphorbia_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_0")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_1")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/euphorbia_" + getSpeciesName(cropTier) + "_2")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_large_leaves", ImmutableMap.of("crop", "dcs_climate:block/crop/euphorbia_" + getSpeciesName(cropTier) + "_f",
		        "leaves", "dcs_climate:block/crop/euphorbia_" + getSpeciesName(cropTier) + "_leaves1")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_large_leaves", ImmutableMap.of("crop", "dcs_climate:block/crop/euphorbia_" + getSpeciesName(cropTier) + "_2",
		        "leaves", "dcs_climate:block/crop/euphorbia_" + getSpeciesName(cropTier) + "_leaves1")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/euphorbia_" + getSpeciesName(cropTier) + "_d")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0", "1", "2", "3", "4", "5");
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_euphorbia_" + getSpeciesName(cropTier)));
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("stage6=0", "stage6=1", "stage6=2", "stage6=3", "stage6=4", "stage6=5");
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.EUPHORBIA;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.SINGLE;
	}

	@Override
	public BlockState getFlowerState(BlockState state) {
		return state.setValue(DCState.STAGE6, 3);
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return 2;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.BLOCK_EU_CASSAVA.get();
		case RARE -> FoodInit.BLOCK_EU_MANCHINEEL.get();
		default -> FoodInit.BLOCK_EU_KUKUI.get();
		};
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_EU_CASSAVA.get();
		case RARE -> FoodInit.CROP_EU_MANCHINEEL.get();
		default -> FoodInit.CROP_EU_KUKUI.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_EU_KUKUI.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_EU_CASSAVA.get());
		case RARE -> Optional.of(FoodInit.BLOCK_EU_MANCHINEEL.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
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
		return ImmutableList.of("SAVANNA", "SANDY", "JUNGLE");
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return ImmutableList.of("COLD", "WATER", "SWAMP");
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "cassava";
		if (tier == CropTier.RARE)
			return "manchineel";
		return "kukui";
	}

}
