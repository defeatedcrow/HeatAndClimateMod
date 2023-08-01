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

public class SaplingCitrus extends SaplingBaseBlock {

	public SaplingCitrus(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.CITRUS;
	}

	@Override
	public Item getCropItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.CROP_CT_MANDARIN.get();
		case RARE:
			return FoodInit.CROP_CT_LEMON.get();
		default:
			return FoodInit.CROP_CT_POMELO.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_CT_POMELO.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_CT_MANDARIN.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_CT_LEMON.get());
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
		switch (t) {
		case WILD:
			return ImmutableList.of("JUNGLE", "FOREST");
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
			return "mandarin";
		if (tier == CropTier.RARE)
			return "lemon";
		return "pomelo";
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		// 高さ4~5、幅5
		level.random.nextInt(2);
		int h = 3 + level.random.nextInt(3);
		int r = 2;
		BlockState log = FoodInit.LOG_CT_POMELO.get().defaultBlockState();
		BlockState leaves = FoodInit.LEAVES_CT_POMELO.get().defaultBlockState().setValue(DCState.FLAG, true);
		if (t == CropTier.COMMON) {
			leaves = FoodInit.LEAVES_CT_MANDARIN.get().defaultBlockState().setValue(DCState.FLAG, true);
		} else if (t == CropTier.RARE) {
			leaves = FoodInit.LEAVES_CT_LEMON.get().defaultBlockState().setValue(DCState.FLAG, true);
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;

			growTree(level, pos, h, log, leaves);
		}
	}

}
