package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SaplingBeech extends SaplingBaseBlock {

	public SaplingBeech(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.BEECH;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_BH_WALNUT.get();
		case RARE -> FoodInit.CROP_BH_SWEET.get();
		default -> FoodInit.CROP_BH_COMMON.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_BH_COMMON.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_BH_WALNUT.get());
		case RARE -> Optional.of(FoodInit.BLOCK_BH_SWEET.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.COMMON) {
			return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		} else if (t == CropTier.RARE) {
			return ImmutableList.of(DCHeatTier.CRYOGENIC, DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL);
		} else {
			return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		}
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
		case WILD, COMMON -> ImmutableList.of("MOUNTAIN", "COLD");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("CONIFEROUS", "LOWLAND");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "walnut";
		if (tier == CropTier.RARE)
			return "sweet";
		return "common";
	}

	@Override
	protected BlockState getLogState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.LOG_BH_WALNUT.get().defaultBlockState().setValue(DCState.WILD, true);
		case RARE -> FoodInit.LOG_BH_SWEET.get().defaultBlockState().setValue(DCState.WILD, true);
		default -> FoodInit.LOG_BH_COMMON.get().defaultBlockState().setValue(DCState.WILD, true);
		};
	}

	@Override
	protected BlockState getLeavesState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.LEAVES_BH_WALNUT.get().defaultBlockState().setValue(DCState.FLAG, true);
		case RARE -> FoodInit.LEAVES_BH_SWEET.get().defaultBlockState().setValue(DCState.FLAG, true);
		default -> FoodInit.LEAVES_BH_COMMON.get().defaultBlockState().setValue(DCState.FLAG, true);
		};
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		RandomSource rand = level.getRandom();
		int h = 1;
		BlockState log = getLogState(t);
		BlockState leaves = getLeavesState(t);

		boolean large = false;
		if (TreeShapeGen.isLargeTree(level, pos, state))
			large = true;
		else if (TreeShapeGen.hasNextSapling(level, pos, state))
			return;

		if (t == CropTier.WILD) {
			h = 6 + level.random.nextInt(6);
			if (large) {
				h = 13 + level.random.nextInt(3);
			}
		} else if (t == CropTier.COMMON) {
			h = 6 + level.random.nextInt(4);
			if (large) {
				h = 12 + level.random.nextInt(3);
			}
		} else if (t == CropTier.RARE) {
			h = 8 + level.random.nextInt(4);
			if (large) {
				h = 16 + level.random.nextInt(5);
			}
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;
		int limit = TreeShapeGen.replaceCheck(level, pos, h);

		if (!level.isClientSide) {
			if (limit < 1)
				return;

			if (limit < 6) {
				TreeShapeGen.growTreeAS(level, pos, limit, log, leaves);
			} else if (limit < 9) {
				TreeShapeGen.growTreeAM(level, pos, limit, log, leaves);
			} else if (limit < 12) {
				TreeShapeGen.growTreeAL(level, pos, limit, log, leaves);
			} else if (limit < 15) {
				TreeShapeGen.growTreeAL(level, pos, limit, log, leaves);
				if (limit > 12) {
					TreeShapeGen.growTreeBigLog(level, pos, limit - 12, log);
				} else if (large) {
					TreeShapeGen.clearSapling(level, pos, state);
				}
				for (Direction d : Direction.Plane.HORIZONTAL) {
					if (level.getRandom().nextBoolean())
						TreeShapeGen.growBlanchAS(level, pos.above(limit - 8 + rand.nextInt(3)), d, 2, 3 + rand.nextInt(2), log, leaves);
				}
			} else {
				TreeShapeGen.growTreeAL(level, pos, limit, log, leaves);
				TreeShapeGen.growTreeBigLog(level, pos, limit - 10, log);
				for (Direction d : Direction.Plane.HORIZONTAL) {
					TreeShapeGen.growBlanchAS(level, pos.above(limit - 8), d, 2, 3, log, leaves);
					TreeShapeGen.growBlanchAL(level, pos.above(limit - 12 + rand.nextInt(2)), d, 1, 2, log, leaves);
				}
			}
		}
	}

}
