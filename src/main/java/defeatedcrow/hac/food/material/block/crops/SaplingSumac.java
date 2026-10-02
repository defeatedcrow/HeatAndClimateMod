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

public class SaplingSumac extends SaplingBaseBlock {

	public SaplingSumac(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.SUMAC;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_SU_MANGO.get();
		case RARE -> FoodInit.CROP_SU_CASHEW.get();
		case EPIC -> FoodInit.CROP_SU_PISTACHIO.get();
		default -> FoodInit.CROP_SU_LACQUER.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_SU_LACQUER.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_SU_MANGO.get());
		case RARE -> Optional.of(FoodInit.BLOCK_SU_CASHEW.get());
		case EPIC -> Optional.of(FoodInit.BLOCK_SU_PISTACHIO.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		if (t == CropTier.EPIC) {
			return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
		} else {
			return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		}
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.WILD) {
			return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		} else if (t == CropTier.EPIC) {
			return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
		} else {
			return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
		}
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		if (t == CropTier.RARE || t == CropTier.EPIC) {
			return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL);
		} else {
			return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
		}
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("MOUNTAIN", "FOREST");
		case COMMON -> ImmutableList.of("JUNGLE");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("CONIFEROUS", "SNOWY", "HOT");
		case COMMON -> ImmutableList.of("CONIFEROUS", "COLD");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "mango";
		if (tier == CropTier.RARE)
			return "cashew";
		if (tier == CropTier.EPIC)
			return "pistachio";
		return "lacquer";
	}

	@Override
	protected BlockState getLogState(CropTier t) {
		return switch (t) {
		case WILD -> FoodInit.LOG_SU_LACQUER.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
		default -> FoodInit.LOG_SU_MANGO.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
		};
	}

	@Override
	protected BlockState getLeavesState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.LEAVES_SU_MANGO.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		case RARE -> FoodInit.LEAVES_SU_CASHEW.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		case EPIC -> FoodInit.LEAVES_SU_PISTACHIO.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		default -> FoodInit.LEAVES_SU_LACQUER.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
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
				h = 6 + level.random.nextInt(5);
			}
		} else if (t == CropTier.COMMON) {
			h = 3 + level.random.nextInt(5);
			if (large) {
				h = 6 + level.random.nextInt(5);
			}
		} else {
			h = 2 + level.random.nextInt(4);
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
			} else {
				TreeShapeGen.growTreeAM(level, pos, limit, log, leaves);
				if (limit > 10) {
					TreeShapeGen.growTreeBigLog(level, pos, limit - 10, log);
				}
				for (Direction d : Direction.Plane.HORIZONTAL) {
					if (level.getRandom()
					    .nextBoolean())
						TreeShapeGen.growBlanchBS(level, pos.above(limit - 6 + rand.nextInt(3)), d, 2, 1 + rand.nextInt(2), log, leaves);
				}
			}
			if (large) {
				TreeShapeGen.clearSapling(level, pos, state);
			}
		}
	}

}
