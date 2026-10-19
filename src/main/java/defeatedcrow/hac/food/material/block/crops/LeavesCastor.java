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

public class LeavesCastor extends LeavesCropBlockDC {

	public LeavesCastor() {
		super(CropType.EUPHORBIA, CropTier.EPIC, false);
		this.setSeason(EnumSeason.SPRING_LATE, EnumSeason.AUTUMN_LATE);
		this.flowerSeasons.add(EnumSeason.SUMMER_EARLY);
		this.flowerSeasons.add(EnumSeason.SUMMER_LATE);
		this.flowerSeasons.add(EnumSeason.AUTUMN_EARLY);
		this.cropSeasons.add(EnumSeason.WINTER_LATE);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_euphorbia_castor";
	}

	@Override
	protected void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		// 自然消滅なし
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
		return FoodInit.BLOCK_EU_CASTOR.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_EU_CASTOR.get();
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_EU_KUKUI.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_EU_CASSAVA.get());
		case RARE -> Optional.of(FoodInit.BLOCK_EU_MANCHINEEL.get());
		case EPIC -> Optional.of(FoodInit.BLOCK_EU_CASTOR.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
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
		return "castor";
	}

}
