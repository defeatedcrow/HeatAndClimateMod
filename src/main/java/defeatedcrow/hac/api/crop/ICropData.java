package defeatedcrow.hac.api.crop;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

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

	default List<String> getAvoidBiomeTag(CropTier tier) {
		return Lists.newArrayList();
	}

	String getSpeciesName(CropTier tier);

	public enum SoilType {
		FARMLAND,
		DIRT,
		SAND,
		MUD,
		WATER;
	}

}
