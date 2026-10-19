package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import org.joml.Math;

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

public class SaplingCherry extends SaplingBaseBlock {

	public SaplingCherry(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.CHERRY;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_CH_PLUM.get();
		case RARE -> FoodInit.CROP_CH_PEACH.get();
		case EPIC -> FoodInit.CROP_CH_ALMOND.get();
		default -> FoodInit.CROP_CH_WILD.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_CH_WILD.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_CH_PLUM.get());
		case RARE -> Optional.of(FoodInit.BLOCK_CH_PEACH.get());
		case EPIC -> Optional.of(FoodInit.BLOCK_CH_ALMOND.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.EPIC) {
			return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
		}
		return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		if (t == CropTier.EPIC) {
			return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL);
		}
		return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("MOUNTAIN", "FOREST", "DENCE");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("CONIFEROUS", "HOT", "LOWLAND");
		case COMMON -> ImmutableList.of("CONIFEROUS", "HOT");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "plum";
		if (tier == CropTier.RARE)
			return "peach";
		if (tier == CropTier.EPIC)
			return "almond";
		return "wild";
	}

	@Override
	protected BlockState getLogState(CropTier t) {
		return FoodInit.LOG_CH_WILD.get().defaultBlockState().setValue(DCState.WILD, true);
	}

	@Override
	protected BlockState getLeavesState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.LEAVES_CH_PLUM.get().defaultBlockState().setValue(DCState.FLAG, true);
		case RARE -> FoodInit.LEAVES_CH_PEACH.get().defaultBlockState().setValue(DCState.FLAG, true);
		case EPIC -> FoodInit.LEAVES_CH_ALMOND.get().defaultBlockState().setValue(DCState.FLAG, true);
		default -> FoodInit.LEAVES_CH_WILD.get().defaultBlockState().setValue(DCState.FLAG, true);
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
			h = 4 + level.random.nextInt(5);
			if (large) {
				h = 10 + level.random.nextInt(3);
			}
		} else if (t == CropTier.COMMON) {
			h = 2 + level.random.nextInt(6);
			if (large) {
				h = 8 + level.random.nextInt(3);
			}
		} else {
			h = 2 + level.random.nextInt(3);
			if (large) {
				h = 6 + level.random.nextInt(3);
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

			if (t == CropTier.COMMON) {
				if (large) {
					TreeShapeGen.growTreeNoLeaves(level, pos, limit - 2, log);
					for (Direction d : Direction.Plane.HORIZONTAL) {
						if (level.getRandom().nextBoolean())
							TreeShapeGen.growBlanchBS(level, pos.above(limit - 6 + rand.nextInt(4)), d, 1 + rand.nextInt(2), 2 + rand.nextInt(2), log, leaves);
					}
				} else if (limit < 6) {
					TreeShapeGen.growTreeAS(level, pos, limit, log, leaves);
				} else {
					TreeShapeGen.growTreeAS(level, pos, limit, log, leaves);
					for (Direction d : Direction.Plane.HORIZONTAL) {
						if (level.getRandom().nextBoolean() && level.getRandom().nextBoolean())
							TreeShapeGen.growBlanchBS(level, pos.above(limit - 5 + rand.nextInt(3)), d, 1, 1 + rand.nextInt(2), log, leaves);
					}
				}
			} else if (large) {
				TreeShapeGen.growTreeAM(level, pos, limit, log, leaves);
				for (Direction d : Direction.Plane.HORIZONTAL) {
					int l2 = Math.max(1, limit - 6 + rand.nextInt(3));
					if (level.getRandom().nextBoolean())
						TreeShapeGen.growBlanchAS(level, pos.above(l2), d, 2, 1 + rand.nextInt(2), log, leaves);
				}
			} else if (limit < 7) {
				TreeShapeGen.growTreeAS(level, pos, limit, log, leaves);
			} else {
				TreeShapeGen.growTreeAM(level, pos, limit, log, leaves);
			}

			if (large) {
				TreeShapeGen.clearSapling(level, pos, state);
			}
		}
	}

}
