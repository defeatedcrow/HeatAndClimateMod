package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;

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
		switch (t) {
		case COMMON:
			return FoodInit.CROP_MR_PAPER.get();
		case RARE:
			return FoodInit.CROP_MR_RUBBER.get();
		default:
			return FoodInit.CROP_MR_MULBERRY.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_MR_MULBERRY.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_MR_PAPER.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_MR_RUBBER.get());
		default:
			return Optional.empty();
		}
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
		switch (t) {
		case WILD:
			return ImmutableList.of("JUNGLE", "SWAMP");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("CONIFEROUS", "COLD");
		default:
			return Lists.newArrayList();
		}
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
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		// 各種サイズが違う
		level.random.nextInt(4);
		int h = 3 + level.random.nextInt(4);
		int r = 3;
		BlockState log = FoodInit.LOG_MR_MULBERRY.get().defaultBlockState();
		BlockState leaves = FoodInit.LEAVES_MR_MULBERRY.get().defaultBlockState().setValue(DCState.FLAG, true);
		if (t == CropTier.COMMON) {
			log = FoodInit.LOG_MR_PAPER.get().defaultBlockState();
			leaves = FoodInit.LEAVES_MR_PAPER.get().defaultBlockState().setValue(DCState.FLAG, true);
		} else if (t == CropTier.RARE) {
			log = FoodInit.LOG_MR_RUBBER.get().defaultBlockState();
			leaves = FoodInit.LEAVES_MR_RUBBER.get().defaultBlockState().setValue(DCState.FLAG, true);
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
			} else if (h > 4) {
				SaplingBeech.growBigTree(level, pos, h, log, leaves);
			} else {
				growTree(level, pos, h, log, leaves);
			}

		}
	}

}
