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
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class LeavesMango extends LeavesCropBlockDC {

	public LeavesMango() {
		super(CropType.SUMAC, CropTier.COMMON, false);
		this.setSeason(EnumSeason.SPRING_EARLY, EnumSeason.SUMMER_EARLY);
		this.cropSeasons.add(EnumSeason.SUMMER_LATE);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_sumac_mango";
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.SUMAC;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_SU_MANGO.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_SU_MANGO.get();
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_SU_LACQUER.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_SU_MANGO.get());
		case RARE -> Optional.of(FoodInit.BLOCK_SU_CASHEW.get());
		case EPIC -> Optional.of(FoodInit.BLOCK_SU_PISTACHIO.get());
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
		case WILD -> ImmutableList.of("MOUNTAIN", "FOREST", "DENCE");
		case COMMON -> ImmutableList.of("JUNGLE", "LUSH");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("CONIFEROUS", "SNOWY", "HOT");
		case COMMON -> ImmutableList.of("CONIFEROUS", "COLD");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "mango";
	}

}
