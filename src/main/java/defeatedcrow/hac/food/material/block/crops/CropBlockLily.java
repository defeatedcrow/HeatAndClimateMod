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

public class CropBlockLily extends ClimateCropBaseBlock {

	public CropBlockLily(CropTier t) {
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
		return "food/cropblock_lily_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/lily_0")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/lily_1")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/lily_" + getSpeciesName(cropTier) + "_2")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/lily_" + getSpeciesName(cropTier) + "_3")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/lily_" + getSpeciesName(cropTier) + "_f")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/lily_d")));
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
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_lily_" + getSpeciesName(cropTier)));
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.LILY;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.SINGLE;
	}

	@Override
	public BlockState getFlowerState(BlockState state) {
		return state.setValue(DCState.STAGE6, 4);
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return 4;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return switch (t) {
		case WILD -> FoodInit.BLOCK_LI_AMANA.get();
		case COMMON -> FoodInit.BLOCK_LI_FAWN.get();
		default -> FoodInit.BLOCK_LI_GOLDBAND.get();
		};
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case WILD -> FoodInit.CROP_LI_AMANA.get();
		case COMMON -> FoodInit.CROP_LI_FAWN.get();
		default -> FoodInit.CROP_LI_GOLDBAND.get();
		};
	}

	@Override
	public List<ItemStack> getCropItems(BlockState state, int fortune) {
		CropStage stage = getCurrentStage(state);
		if (stage.canHarvestCrop()) {
			int chance = 5 + fortune * 5;
			if (DCUtil.rand.nextInt(chance) > 3) {
				return ImmutableList.of(new ItemStack(getCropItem(getTier())), new ItemStack(getSeedItem(getTier())));
			}
		}
		return super.getCropItems(state, fortune);
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_LI_AMANA.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_LI_FAWN.get());
		case RARE -> Optional.of(FoodInit.BLOCK_LI_GOLDBAND.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		default -> ImmutableList.of(SoilType.FARMLAND);
		};
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("MOUNTAIN", "RIVER", "PLAIN");
		case COMMON -> ImmutableList.of("MOUNTAIN", "FOREST", "COLD");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("HOT", "DRY");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "fawn";
		if (tier == CropTier.RARE)
			return "goldband";
		return "amana";
	}

}
