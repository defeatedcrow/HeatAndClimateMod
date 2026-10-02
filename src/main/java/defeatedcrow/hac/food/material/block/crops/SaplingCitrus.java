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
import net.minecraft.util.RandomSource;
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
		return switch (t) {
		case COMMON -> FoodInit.CROP_CT_MANDARIN.get();
		case RARE -> FoodInit.CROP_CT_LEMON.get();
		case EPIC -> FoodInit.CROP_CT_PEPPER.get();
		default -> FoodInit.CROP_CT_POMELO.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_CT_POMELO.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_CT_MANDARIN.get());
		case RARE -> Optional.of(FoodInit.BLOCK_CT_LEMON.get());
		case EPIC -> Optional.of(FoodInit.BLOCK_CT_PEPPER.get());
		default -> Optional.empty();
		};
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
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("JUNGLE", "FOREST");
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
			return "mandarin";
		if (tier == CropTier.RARE)
			return "lemon";
		if (tier == CropTier.EPIC)
			return "pepper";
		return "pomelo";
	}

	@Override
	protected BlockState getLogState(CropTier t) {
		return FoodInit.LOG_CT_POMELO.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
	}

	@Override
	protected BlockState getLeavesState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.LEAVES_CT_MANDARIN.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		case RARE -> FoodInit.LEAVES_CT_LEMON.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		case EPIC -> FoodInit.LEAVES_CT_PEPPER.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		default -> FoodInit.LEAVES_CT_POMELO.get()
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

		h = 1 + level.random.nextInt(4);
		if (large) {
			h = 5 + level.random.nextInt(3);
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
			} else {
				TreeShapeGen.growTreeAM(level, pos, limit, log, leaves);
			}
		}
	}

}
