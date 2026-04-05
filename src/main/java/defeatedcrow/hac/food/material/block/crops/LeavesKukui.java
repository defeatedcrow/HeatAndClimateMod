package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class LeavesKukui extends LeavesCropBlockDC {

	public LeavesKukui() {
		super(CropType.EUPHORBIA, CropTier.WILD, true);
		this.setSeason(EnumSeason.SPRING_LATE, EnumSeason.SUMMER_LATE);
		this.flowerSeasons.add(EnumSeason.SUMMER_EARLY);
		this.cropSeasons.add(EnumSeason.AUTUMN_EARLY);
		this.cropSeasons.add(EnumSeason.AUTUMN_LATE);
		this.cropSeasons.add(EnumSeason.WINTER_EARLY);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_euphorbia_kukui";
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.EUPHORBIA;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_EU_KUKUI.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_EU_KUKUI.get();
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
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
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
		return ImmutableList.of("OCEAN", "BEACH", "LUSH");
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "kukui";
	}

}
