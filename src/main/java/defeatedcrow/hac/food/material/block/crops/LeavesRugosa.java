package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class LeavesRugosa extends LeavesCropBlockDC {

	public LeavesRugosa() {
		super(CropType.ROSE, CropTier.WILD, false);
		this.setSeason(EnumSeason.SPRING_LATE, EnumSeason.AUTUMN_EARLY);
		this.flowerSeasons.add(EnumSeason.SUMMER_EARLY);
		this.flowerSeasons.add(EnumSeason.SUMMER_LATE);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_rose_rugosa";
	}

	@Override
	protected void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		// 自然消滅なし
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.ROSE;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_RO_RUGOSA.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_RO_RUGOSA.get();
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
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
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
		case WILD -> ImmutableList.of("HOT", "MOUNTAIN");
		case COMMON -> ImmutableList.of("HOT", "LOWLAND");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "rugosa";
	}

}
