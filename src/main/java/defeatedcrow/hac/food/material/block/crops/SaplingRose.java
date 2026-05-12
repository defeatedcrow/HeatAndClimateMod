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

public class SaplingRose extends SaplingBaseBlock {

	public SaplingRose(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.ROSE;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_RO_RUGOSA.get();
		case RARE -> FoodInit.CROP_RO_RASPBERRY.get();
		default -> FoodInit.CROP_RO_DAMASCHENA.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_RO_RUGOSA.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_RO_RASPBERRY.get());
		case RARE -> Optional.of(FoodInit.BLOCK_RO_DAMASCHENA.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		if (t == CropTier.WILD) {
			return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
		}
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.RARE) {
			return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
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
		case WILD -> ImmutableList.of("OCEAN", "BEACH", "PLAINS");
		case COMMON -> ImmutableList.of("FOREST", "MOUNTAIN");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("HOT");
		case COMMON -> ImmutableList.of("HOT", "LOWLAND");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "raspberry";
		if (tier == CropTier.RARE)
			return "damaschena";
		return "rugosa";
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
		case COMMON -> FoodInit.LEAVES_RO_RASPBERRY.get()
		    .defaultBlockState();
		case RARE -> FoodInit.LEAVES_RO_DAMASCHENA.get()
		    .defaultBlockState();
		default -> FoodInit.LEAVES_RO_RUGOSA.get()
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

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;

			for (int i = 0; i < h; i++) {
				level.setBlock(pos.above(i), leaves, 2);
			}
		}
	}

}
