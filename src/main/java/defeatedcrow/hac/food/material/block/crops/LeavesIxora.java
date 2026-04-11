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
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class LeavesIxora extends LeavesCropBlockDC {

	public LeavesIxora() {
		super(CropType.RUBIA, CropTier.RARE, false);
		this.setSeason(EnumSeason.SPRING_EARLY, EnumSeason.AUTUMN_LATE);
		this.flowerSeasons.add(EnumSeason.SPRING_LATE);
		this.flowerSeasons.add(EnumSeason.SUMMER_EARLY);
		this.flowerSeasons.add(EnumSeason.SUMMER_LATE);
		this.flowerSeasons.add(EnumSeason.AUTUMN_EARLY);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_rubia_ixora";
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.RUBIA;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_RU_IXORA.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_RU_IXORA.get();
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
		return "ixora";
	}

	/* 花の収穫ができる */
	@Override
	public boolean canHarvest(BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		return stage == CropStage.FLOWER || stage == CropStage.GROWN;
	}

	@Override
	public List<ItemStack> getCropItems(BlockState state, int fortune) {
		CropStage stage = this.getCurrentStage(state);
		if (stage == CropStage.FLOWER) {
			ItemStack ret = new ItemStack(getCropItem(getTier()));
			return ImmutableList.of(ret);
		} else {
			ItemStack ret = new ItemStack(getSeedItem(getTier()));
			return ImmutableList.of(ret);
		}
	}

}
