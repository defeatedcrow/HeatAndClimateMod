package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class CropBlockOrchid_Epiphyte extends CropBaseEpiphyte {

	public CropBlockOrchid_Epiphyte(CropTier t) {
		super(t);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_orchid_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/orchid_0")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/orchid_1")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/orchid_" + getSpeciesName(cropTier) + "_2")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/orchid_" + getSpeciesName(cropTier) + "_3")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/orchid_" + getSpeciesName(cropTier) + "_f")),
			new JsonModelDC("dcs_climate:block/dcs_crop_epiphyte", ImmutableMap.of("crop", "dcs_climate:block/crop/orchid_0", "side", "dcs_climate:block/crop/orchid_side_0")),
			new JsonModelDC("dcs_climate:block/dcs_crop_epiphyte", ImmutableMap.of("crop", "dcs_climate:block/crop/orchid_1", "side", "dcs_climate:block/crop/orchid_side_1")),
			new JsonModelDC("dcs_climate:block/dcs_crop_epiphyte", ImmutableMap.of("crop", "dcs_climate:block/crop/orchid_" + getSpeciesName(cropTier) + "_2", "side",
				"dcs_climate:block/crop/orchid_" + getSpeciesName(cropTier) + "_side_2")),
			new JsonModelDC("dcs_climate:block/dcs_crop_epiphyte", ImmutableMap.of("crop", "dcs_climate:block/crop/orchid_" + getSpeciesName(cropTier) + "_3", "side",
				"dcs_climate:block/crop/orchid_" + getSpeciesName(cropTier) + "_side_3")),
			new JsonModelDC("dcs_climate:block/dcs_crop_epiphyte", ImmutableMap.of("crop", "dcs_climate:block/crop/orchid_" + getSpeciesName(cropTier) + "_f", "side",
				"dcs_climate:block/crop/orchid_" + getSpeciesName(cropTier) + "_side_f")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0", "1", "2", "3", "f", "side_0", "side_1", "side_2", "side_3", "side_f");
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_orchid_" + getSpeciesName(cropTier)));
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.ORCHID;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.BLOCK_OR_CYMBIDIUM.get();
		case RARE:
			return FoodInit.BLOCK_OR_VANILLA.get();
		case EPIC:
			return FoodInit.BLOCK_OR_CATTLEYA.get();
		default:
			return FoodInit.BLOCK_OR_SPIRANTHES.get();
		}
	}

	@Override
	public Item getCropItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.CROP_OR_CYMBIDIUM.get();
		case RARE:
			return FoodInit.CROP_OR_VANILLA.get();
		case EPIC:
			return FoodInit.CROP_OR_CATTLEYA.get();
		default:
			return FoodInit.CROP_OR_SPIRANTHES.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_OR_SPIRANTHES.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_OR_CYMBIDIUM.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_OR_VANILLA.get());
		case EPIC:
			return Optional.of(FoodInit.BLOCK_OR_CATTLEYA.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier tier) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.LOGS);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
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
		return Lists.newArrayList();
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "cymbidium";
		if (tier == CropTier.RARE)
			return "vanilla";
		if (tier == CropTier.EPIC)
			return "cattleya";
		return "spiranthes";
	}

}
