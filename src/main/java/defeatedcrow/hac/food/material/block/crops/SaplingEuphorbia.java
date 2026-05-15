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
		default -> FoodInit.CROP_EU_KUKUI.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_EU_KUKUI.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_EU_CASSAVA.get());
		case RARE -> Optional.of(FoodInit.BLOCK_EU_MANCHINEEL.get());
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
		case WILD -> ImmutableList.of("WATER", "BEACH", "LUSH");
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
		return "kukui";
	}

	@Override
	protected BlockState getLogState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.BLOCK_EU_CASSAVA.get()
		    .defaultBlockState();
		case RARE -> FoodInit.LOG_EU_MANCHINEEL.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
		default -> FoodInit.LOG_EU_KUKUI.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
		};
	}

	@Override
	protected BlockState getLeavesState(CropTier t) {
		return switch (t) {
		case RARE -> FoodInit.LEAVES_EU_MANCHINEEL.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		default -> FoodInit.LEAVES_EU_KUKUI.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		};
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		// 高さ5~8、幅5
		int h = 5 + level.random.nextInt(4);
		int r = 2;
		BlockState log = getLogState(t);
		BlockState leaves = getLeavesState(t);

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (t == CropTier.COMMON) {
				level.setBlock(pos, log, 2);
				return;
			}

			if (replaceCheck(level, pos, h))
				return;

			if (h > 6) {
				SaplingBeech.growDomeTree(level, pos, h, log, leaves);
			} else {
				growSmallDomeTree(level, pos, h, log, leaves);
			}
		}
	}

}
