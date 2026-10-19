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

public class SaplingEuphorbia extends SaplingBaseBlock {

	public SaplingEuphorbia(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.EUPHORBIA;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_EU_CASSAVA.get();
		case RARE -> FoodInit.CROP_EU_MANCHINEEL.get();
		case EPIC -> FoodInit.CROP_EU_CASTOR.get();
		default -> FoodInit.CROP_EU_KUKUI.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_EU_KUKUI.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_EU_CASSAVA.get());
		case RARE -> Optional.of(FoodInit.BLOCK_EU_MANCHINEEL.get());
		case EPIC -> Optional.of(FoodInit.BLOCK_EU_CASTOR.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return switch (t) {
		case COMMON -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
		default -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
		};
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return switch (t) {
		case COMMON -> ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
		default -> ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
		};
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return switch (t) {
		case COMMON -> ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
		default -> ImmutableList.of(DCAirflow.FLOW, DCAirflow.WIND);
		};
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("OCEAN", "BEACH", "LUSH");
		case COMMON -> ImmutableList.of("SAVANNA", "SANDY", "JUNGLE");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("SNOWY", "DRY", "MOUNTAIN");
		case COMMON -> ImmutableList.of("COLD", "WATER", "SWAMP");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "cassava";
		if (tier == CropTier.RARE)
			return "manchineel";
		if (tier == CropTier.EPIC)
			return "castor";
		return "kukui";
	}

	@Override
	protected BlockState getLogState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.BLOCK_EU_CASSAVA.get().defaultBlockState();
		case RARE -> FoodInit.LOG_EU_MANCHINEEL.get().defaultBlockState().setValue(DCState.WILD, true);
		case EPIC -> FoodInit.LOG_EU_KUKUI.get().defaultBlockState().setValue(DCState.WILD, true);
		default -> FoodInit.LOG_EU_KUKUI.get().defaultBlockState().setValue(DCState.WILD, true);
		};
	}

	@Override
	protected BlockState getLeavesState(CropTier t) {
		return switch (t) {
		case RARE -> FoodInit.LEAVES_EU_MANCHINEEL.get().defaultBlockState().setValue(DCState.FLAG, true);
		case EPIC -> FoodInit.LEAVES_EU_CASTOR.get().defaultBlockState().setValue(DCState.FLAG, true);
		default -> FoodInit.LEAVES_EU_KUKUI.get().defaultBlockState().setValue(DCState.FLAG, true);
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
			h = 3 + level.random.nextInt(4);
			if (large) {
				h = 7 + level.random.nextInt(3);
			}
		} else if (t == CropTier.COMMON) {
			h = 0;
		} else if (t == CropTier.RARE) {
			h = 3 + level.random.nextInt(5);
			if (large) {
				h = 6 + level.random.nextInt(3);
			}
		} else if (t == CropTier.EPIC) {
			h = 1 + level.random.nextInt(2);
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;
		int limit = TreeShapeGen.replaceCheck(level, pos, h);

		if (!level.isClientSide) {
			if (t == CropTier.COMMON) {
				level.setBlock(pos, log, 2);
				return;
			}

			if (t == CropTier.EPIC) {
				for (int i = 0; i < h; i++) {
					level.setBlock(pos.above(i), leaves, 2);
				}
				return;
			}

			if (limit < 1)
				return;

			if (limit < 6) {
				TreeShapeGen.growTreeAS(level, pos, limit, log, leaves);
			} else {
				TreeShapeGen.growTreeAS(level, pos, limit, log, leaves);
				for (Direction d : Direction.Plane.HORIZONTAL) {
					if (level.getRandom().nextBoolean())
						TreeShapeGen.growBlanchBS(level, pos.above(limit - 6 + rand.nextInt(2)), d, 1 + rand.nextInt(2), 1 + rand.nextInt(2), log, leaves);
				}
			}
			if (large) {
				TreeShapeGen.clearSapling(level, pos, state);
			}
		}
	}

}
