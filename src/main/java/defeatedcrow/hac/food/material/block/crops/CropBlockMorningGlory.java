package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class CropBlockMorningGlory extends ClimateCropBaseBlock {

	public CropBlockMorningGlory(CropTier t) {
		super(t);
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE6, 0).setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.STAGE6, DCState.WILD);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_morningglory_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/leaf_0")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/leaf_1")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/morningglory_" + getSpeciesName(cropTier) + "_2")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/morningglory_" + getSpeciesName(cropTier) + "_f")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/morningglory_" + getSpeciesName(cropTier) + "_c")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/morningglory_" + getSpeciesName(cropTier) + "_d")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0", "1", "2", "3", "4", "5");
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("stage6=0", "stage6=1", "stage6=2", "stage6=3", "stage6=4", "stage6=5");
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_morningglory_" + getSpeciesName(cropTier)));
	}

	/* IClimateCrop */

	@Override
	public List<ItemStack> getCropItems(BlockState state, int fortune) {
		List<ItemStack> list = Lists.newArrayList();
		int i = DCUtil.rand.nextInt(fortune + 2);
		if (i > 0) {
			ItemStack ret = new ItemStack(FoodInit.VINE.get(), i);
			list.add(ret);
		}
		list.add(new ItemStack(getCropItem(getTier())));
		return list;
	}

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState().setValue(DCState.STAGE6, 2).setValue(DCState.WILD, true);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.MORNINGGLORY;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.SINGLE;
	}

	@Override
	public BlockState getFlowerState(BlockState state) {
		return state.setValue(DCState.STAGE6, 3);
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return 5;
	}

	@Override
	public boolean canHarvest(BlockState state) {
		CropStage stage = this.getCurrentStage(state);
		if (this.getTier() == CropTier.COMMON) {
			return stage == CropStage.GROWN;
		} else {
			return stage == CropStage.GROWN || stage == CropStage.FLOWER;
		}
	}

	@Override
	public AquaticType isAquaticPlant(CropTier tier) {
		return tier == CropTier.COMMON ? AquaticType.FLOATING : AquaticType.NONE;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.BLOCK_MO_WATER.get();
		case RARE -> FoodInit.BLOCK_MO_POTATO.get();
		case EPIC -> FoodInit.BLOCK_MO_FLOWER.get();
		default -> FoodInit.BLOCK_MO_BINDWEED.get();
		};
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_MO_WATER.get();
		case RARE -> FoodInit.CROP_MO_POTATO.get();
		case EPIC -> FoodInit.CROP_MO_FLOWER.get();
		default -> FoodInit.CROP_MO_BINDWEED.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_MO_BINDWEED.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_MO_WATER.get());
		case RARE -> Optional.of(FoodInit.BLOCK_MO_POTATO.get());
		case EPIC -> Optional.of(FoodInit.BLOCK_MO_FLOWER.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
		case COMMON -> ImmutableList.of(SoilType.FARMLAND, SoilType.SAND, SoilType.MUD, SoilType.WATER);
		default -> ImmutableList.of(SoilType.FARMLAND);
		};
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.WILD) {
			return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
		}
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		if (t == CropTier.WILD) {
			return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
		} else if (t == CropTier.COMMON) {
			return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET, DCHumidity.UNDERWATER);
		}
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("BEACH", "OCEAN");
		case COMMON -> ImmutableList.of("REVER", "SWAMP");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("COLD");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "water";
		if (tier == CropTier.RARE)
			return "potato";
		if (tier == CropTier.EPIC)
			return "flower";
		return "bindweed";
	}

}
