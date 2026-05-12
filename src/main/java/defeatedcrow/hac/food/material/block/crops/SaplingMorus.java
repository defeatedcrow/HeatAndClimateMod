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

public class SaplingMorus extends SaplingBaseBlock {

	public SaplingMorus(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.MORUS;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_MR_PAPER.get();
		case RARE -> FoodInit.CROP_MR_RUBBER.get();
		default -> FoodInit.CROP_MR_MULBERRY.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_MR_MULBERRY.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_MR_PAPER.get());
		case RARE -> Optional.of(FoodInit.BLOCK_MR_RUBBER.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
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
		case WILD -> ImmutableList.of("JUNGLE", "SWAMP");
		case COMMON -> ImmutableList.of("JUNGLE", "MOUNTAIN");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("CONIFEROUS", "COLD");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "paper";
		if (tier == CropTier.RARE)
			return "rubber";
		return "mulberry";
	}

	@Override
	protected BlockState getLogState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.LOG_MR_PAPER.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
		case RARE -> FoodInit.LOG_MR_RUBBER.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
		default -> FoodInit.LOG_MR_MULBERRY.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
		};
	}

	@Override
	protected BlockState getLeavesState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.LEAVES_MR_PAPER.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		case RARE -> FoodInit.LEAVES_MR_RUBBER.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		default -> FoodInit.LEAVES_MR_MULBERRY.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		};
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		// 各種サイズが違う
		level.random.nextInt(4);
		int h = 3 + level.random.nextInt(4);
		int r = 3;
		BlockState log = getLogState(t);
		BlockState leaves = getLeavesState(t);
		if (t == CropTier.RARE) {
			h = 8 + level.random.nextInt(5);
			r = 4;
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;

			if (h > 9) {
				SaplingBeech.growBigTree2(level, pos, h, log, leaves);
			} else if (h > 5) {
				SaplingBeech.growBigTree(level, pos, h, log, leaves);
			} else {
				growTree(level, pos, h, log, leaves);
			}

		}
	}

}
