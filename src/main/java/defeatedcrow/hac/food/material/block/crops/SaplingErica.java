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
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SaplingErica extends SaplingBaseBlock {

	public SaplingErica(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.ERICA;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_ER_RHODODENDRON.get();
		case RARE -> FoodInit.CROP_ER_BLUEBERRY.get();
		default -> FoodInit.CROP_ER_HEATH.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_ER_HEATH.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_ER_RHODODENDRON.get());
		case RARE -> Optional.of(FoodInit.BLOCK_ER_BLUEBERRY.get());
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
			return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
		}
		return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
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
		case WILD -> ImmutableList.of("CONIFEROUS", "COLD", "MOUNTAIN");
		case COMMON -> ImmutableList.of("CONIFEROUS", "JUNGLE", "MOUNTAIN");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("HOT", "OCEAN");
		case COMMON -> ImmutableList.of("LOWLAND");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "rhododendron";
		if (tier == CropTier.RARE)
			return "blueberry";
		return "heath";
	}

	@Override
	protected BlockState getLogState(CropTier t) {
		return FoodInit.LOG_BH_COMMON.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
	}

	@Override
	protected BlockState getLeavesState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.LEAVES_ER_RHODODENDRON.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		case RARE -> FoodInit.LEAVES_ER_BLUEBERRY.get()
		    .defaultBlockState();
		default -> FoodInit.LEAVES_ER_HEATH.get()
		    .defaultBlockState();
		};
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		level.random.nextInt(2);
		int h = 1;
		int r = 2;
		BlockState log = getLogState(t);
		BlockState leaves = getLeavesState(t);
		if (t == CropTier.COMMON) {
			h = 2 + level.random.nextInt(3);
		} else if (t == CropTier.RARE) {
			h = 1 + level.random.nextInt(2);
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;

			if (t == CropTier.COMMON) {
				growTree(level, pos, h, log, leaves);
			} else {
				for (int i = 0; i < h; i++) {
					level.setBlock(pos.above(i), leaves, 2);
				}
			}
		}
	}

}
