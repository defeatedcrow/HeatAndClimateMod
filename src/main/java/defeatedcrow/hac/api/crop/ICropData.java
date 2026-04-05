package defeatedcrow.hac.api.crop;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public interface ICropData {

	CropType getFamily();

	ItemLike getSeedItem(CropTier tier);

	Item getCropItem(CropTier tier);

	Optional<Block> getMutationTarget(CropTier tier);

	CropGrowType getGrowType(CropTier tier);

	List<SoilType> getSoilTypes(CropTier tier);

	List<DCHeatTier> getSuitableTemp(CropTier tier);

	List<DCHumidity> getSuitableHum(CropTier tier);

	List<DCAirflow> getSuitableAir(CropTier tier);

	List<String> getGeneratedBiomeTag(CropTier tier);

	default int getContinuousRegistance(CropTier tier) {
		return 5;
	}

	default float wildCropSpreadChance() {
		return 0.05F;
	}

	default List<String> getAvoidBiomeTag(CropTier tier) {
		return Lists.newArrayList();
	}

	String getSpeciesName(CropTier tier);

	default AquaticType isAquaticPlant(CropTier tier) {
		return AquaticType.NONE;
	}

	public enum SoilType {
		FARMLAND, DIRT, SAND, MUD, WATER, LOGS;
	}

	public enum AquaticType {
		NONE, EMERGED, SUBMERGED, FLOATING, FORCED_EMERGED, FORCED_SUBMERGED;
	}

}
