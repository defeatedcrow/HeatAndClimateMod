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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SaplingRubia extends SaplingBaseBlock {

	public SaplingRubia(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.RUBIA;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return this;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_RU_COFFEE.get();
		case RARE -> FoodInit.CROP_RU_IXORA.get();
		default -> FoodInit.CROP_RU_GARDENIA.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_RU_GARDENIA.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_RU_COFFEE.get());
		case RARE -> Optional.of(FoodInit.BLOCK_RU_IXORA.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.RARE) {
			return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
		}
		return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("MOUNTAIN", "FOREST", "JUNGLE");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("COLD", "DRY", "CONIFEROUS");
		case COMMON -> ImmutableList.of("COLD", "CONIFEROUS", "LOWLAND");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "coffee";
		if (tier == CropTier.RARE)
			return "ixora";
		return "gardenia";
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
		case COMMON -> FoodInit.LEAVES_RU_COFFEE.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		case RARE -> FoodInit.LEAVES_RU_IXORA.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		default -> FoodInit.LEAVES_RU_GARDENIA.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		};
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		level.random.nextInt(2);
		int h = 1 + level.random.nextInt(3);
		int r = 2;
		BlockState log = getLogState(t);
		BlockState leaves = getLeavesState(t);

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;
			growSmallTree(level, pos, h, log, leaves);
		}
	}

}
