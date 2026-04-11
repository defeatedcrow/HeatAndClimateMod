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
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CropBlockAroids extends ClimateCropBaseBlock implements SimpleWaterloggedBlock {

	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public CropBlockAroids(CropTier t) {
		super(t);
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE6, 0).setValue(WATERLOGGED, false).setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.STAGE6, WATERLOGGED, DCState.WILD);
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

	/* waterlogged */

	@Override
	public BlockState updateShape(BlockState state, Direction dir, BlockState state2, LevelAccessor level, BlockPos p1, BlockPos p2) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(p1, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return super.updateShape(state, dir, state2, level, p1, p2);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return DCState.getBool(state, WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean canPlaceLiquid(BlockGetter level, BlockPos pos, BlockState state, Fluid water) {
		if (getTier() == CropTier.WILD) {
			return !state.getValue(WATERLOGGED) && water == Fluids.WATER;
		} else {
			return false;
		}
	}

	@Override
	public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState water) {
		if (getTier() == CropTier.WILD) {
			if (!state.getValue(WATERLOGGED) && water.getType() == Fluids.WATER) {
				if (!level.isClientSide()) {
					level.setBlock(pos, state.setValue(WATERLOGGED, true), 3);
					level.scheduleTick(pos, water.getType(), water.getType().getTickDelay(level));
				}
				return true;
			}
		}
		return false;
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

	/* 花の収穫ができる */
	@Override
	public boolean canHarvest(BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		if (getTier() == CropTier.RARE) {
			// 蒟蒻のみ
			return stage == CropStage.GROWN || stage == CropStage.DEAD;
		}
		return stage == CropStage.GROWN;
	}

	@Override
	public List<ItemStack> getCropItems(BlockState state, int fortune) {
		CropStage stage = this.getCurrentStage(state);
		if (getTier() == CropTier.RARE && stage == CropStage.DEAD) {
			ItemStack ret = new ItemStack(FoodInit.FLOWER_KONJAC.get());
			return ImmutableList.of(ret);
		} else {
			ItemStack ret = new ItemStack(getCropItem(getTier()));
			return ImmutableList.of(ret);
		}
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_aroids_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		if (getTier() == CropTier.WILD) {
			return ImmutableList.of(
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_0")),
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_1")),
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_2")),
			    new JsonModelDC("dcs_climate:block/dcs_crop_rosette", ImmutableMap.of("crop", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_f",
			        "leaves1", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_leaves1",
			        "leaves2", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_leaves2")),
			    new JsonModelDC("dcs_climate:block/dcs_crop_rosette", ImmutableMap.of("crop", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_2",
			        "leaves1", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_leaves1",
			        "leaves2", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_leaves2")),
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_d")));

		} else if (getTier() == CropTier.RARE) {
			return ImmutableList.of(
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_0")),
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_1")),
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_2")),
			    new JsonModelDC("dcs_climate:block/dcs_crop_large_leaves", ImmutableMap.of("crop", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_2",
			        "leaves", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_leaves1")),
			    new JsonModelDC("dcs_climate:block/dcs_crop_large_leaves", ImmutableMap.of("crop", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_2",
			        "leaves", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_leaves1")),
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_f")));
		} else {
			return ImmutableList.of(
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_0")),
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_1")),
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_2")),
			    new JsonModelDC("dcs_climate:block/dcs_crop_large_leaves", ImmutableMap.of("crop", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_f",
			        "leaves", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_leaves1")),
			    new JsonModelDC("dcs_climate:block/dcs_crop_large_leaves", ImmutableMap.of("crop", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_2",
			        "leaves", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_leaves1")),
			    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/aroids_" + getSpeciesName(cropTier) + "_d")));
		}
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0", "1", "2", "3", "4", "5");
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_aroids_" + getSpeciesName(cropTier)));
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("stage6=0", "stage6=1", "stage6=2", "stage6=3", "stage6=4", "stage6=5");
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.AROIDS;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.SINGLE;
	}

	@Override
	public BlockState getFlowerState(BlockState state) {
		if (getTier() == CropTier.RARE) {
			return state.setValue(DCState.STAGE6, 5);
		}
		return state.setValue(DCState.STAGE6, 3);
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return 3;
	}

	@Override
	public float wildCropSpreadChance() {
		return 0.01F;
	}

	@Override
	public AquaticType isAquaticPlant(CropTier tier) {
		return tier == CropTier.WILD ? AquaticType.SUBMERGED : AquaticType.NONE;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.BLOCK_AR_TARO.get();
		case RARE -> FoodInit.BLOCK_AR_KONJAC.get();
		default -> FoodInit.BLOCK_AR_BUCE.get();
		};
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_AR_TARO.get();
		case RARE -> FoodInit.CROP_AR_KONJAC.get();
		default -> FoodInit.CROP_AR_BUCE.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_AR_BUCE.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_AR_TARO.get());
		case RARE -> Optional.of(FoodInit.BLOCK_AR_KONJAC.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.MUD, SoilType.SAND);
		case COMMON -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		default -> ImmutableList.of(SoilType.FARMLAND);
		};
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of(DCHumidity.WET, DCHumidity.UNDERWATER);
		default -> ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
		};
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of(DCAirflow.TIGHT, DCAirflow.NORMAL, DCAirflow.FLOW);
		default -> ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
		};
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("JUNGLE", "SWAMP", "RIVER");
		case COMMON -> ImmutableList.of("JUNGLE", "SWAMP");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("COLD", "DRY", "MOUNTAIN");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "taro";
		if (tier == CropTier.RARE)
			return "konjac";
		return "buce";
	}

}
