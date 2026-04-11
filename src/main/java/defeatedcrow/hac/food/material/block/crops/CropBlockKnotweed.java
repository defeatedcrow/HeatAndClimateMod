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
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class CropBlockKnotweed extends ClimateCropBaseBlock {

	public CropBlockKnotweed(CropTier t) {
		super(t);
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.DOUBLE, false).setValue(DCState.STAGE6, 0).setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.DOUBLE, DCState.STAGE6, DCState.WILD);
	}

	/* double */

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult res) {
		if (player.getItemInHand(hand).isEmpty()) {
			BlockPos upper = DCState.getBool(state, DCState.DOUBLE) ? pos : pos.above();
			BlockPos under = DCState.getBool(state, DCState.DOUBLE) ? pos.below() : pos;
			if (onHarvest(level, upper, level.getBlockState(upper), player) || onHarvest(level, under, level.getBlockState(under), player))
				return InteractionResult.SUCCESS;
		}
		return super.use(state, level, pos, player, hand, res);
	}

	@Override
	protected boolean mayPlaceOn(BlockState under, BlockGetter level, BlockPos pos) {
		if (under != null && under.getBlock() == this) {
			BlockState avobe = level.getBlockState(pos.above());
			return DCState.getBool(avobe, DCState.DOUBLE) && DCState.getInt(under, DCState.STAGE6) > 1;
		}
		return super.mayPlaceOn(under, level, pos);
	}

	@Override
	public ItemStack getSeedItem(BlockState state) {
		return DCState.getBool(state, DCState.DOUBLE) ? ItemStack.EMPTY : new ItemStack(getSeedItem(cropTier));
	}

	@Override
	public BlockState getHarvestedState(BlockState state) {
		return this.defaultBlockState();
	}

	@Override
	public boolean onGrow(Level world, BlockPos pos, BlockState thisState) {
		if (DCState.getBool(thisState, DCState.DOUBLE)) {
			return false;
		}
		return super.onGrow(world, pos, thisState);
	}

	@Override
	public void afterHarvest(Level world, BlockPos pos, BlockState thisState) {
		if (thisState != null && thisState.getBlock() instanceof IClimateCrop) {
			boolean d = DCState.getBool(thisState, DCState.DOUBLE);
			// 自然生成物は再収穫できない
			boolean w = DCState.getBool(thisState, DCState.WILD);
			if (!w || forceDefault()) {
				BlockState next = this.getHarvestedState(thisState);
				if (d) {
					next = Blocks.AIR.defaultBlockState();
				}
				world.setBlock(pos, next, 2);
			} else {
				world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
			}
		}
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_knotweed_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/solanum_0")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/leaf_1")),
		    new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_2")),
		    new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_f")),
		    new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_c")),
		    new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_d")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/solanum_0")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_1")),
		    new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_2")),
		    new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_f")),
		    new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_c")),
		    new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_d")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("false_0", "false_1", "false_2", "false_3", "false_4", "false_5", "true_0", "true_1", "true_2", "true_3", "true_4", "true_5");
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("double=false,stage6=0", "double=false,stage6=1", "double=false,stage6=2", "double=false,stage6=3", "double=false,stage6=4", "double=false,stage6=5",
		    "double=true,stage6=0", "double=true,stage6=1", "double=true,stage6=2", "double=true,stage6=3", "double=true,stage6=4", "double=true,stage6=5");
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_knotweed_" + getSpeciesName(cropTier)));
	}

	/* IClimateCrop */

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState().setValue(DCState.STAGE6, 2).setValue(DCState.WILD, true);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.KNOTWEED;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.DOUBLE;
	}

	@Override
	public BlockState getFlowerState(BlockState state) {
		if (this.getTier() == CropTier.WILD) {
			return state.setValue(DCState.STAGE6, 4);
		}
		return state.setValue(DCState.STAGE6, 3);
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return switch (t) {
		case WILD -> 5;
		default -> 3;
		};
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.BLOCK_KN_BUCKWHEAT.get();
		case RARE -> FoodInit.BLOCK_KN_INDIGO.get();
		default -> FoodInit.BLOCK_KN_SORREL.get();
		};
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_KN_BUCKWHEAT.get();
		case RARE -> FoodInit.CROP_KN_INDIGO.get();
		default -> FoodInit.CROP_KN_SORREL.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_KN_SORREL.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_KN_BUCKWHEAT.get());
		case RARE -> Optional.of(FoodInit.BLOCK_KN_INDIGO.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		default -> ImmutableList.of(SoilType.FARMLAND);
		};
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.COMMON) {
			return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		}
		return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		if (t == CropTier.COMMON) {
			return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL);
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
		case WILD -> ImmutableList.of("PLAINS", "MOUNTAIN");
		case COMMON -> ImmutableList.of("COLD", "DRY");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("DENCE", "DRY");
		case COMMON -> ImmutableList.of("SANDY", "HOT", "WET", "WATER");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "buckwheat";
		if (tier == CropTier.RARE)
			return "indigo";
		return "sorrel";
	}

}
