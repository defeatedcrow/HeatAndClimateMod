package defeatedcrow.hac.food.material.block;

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
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.DOUBLE, Boolean.valueOf(false)).setValue(DCState.STAGE5, Integer.valueOf(0)).setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.DOUBLE, DCState.STAGE5, DCState.WILD);
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
	public boolean isSuitablePlace(BlockGetter world, BlockPos pos, BlockState state) {
		BlockState avobe = world.getBlockState(pos.above());
		if (DCState.getBool(avobe, DCState.DOUBLE)) {
			return state.getBlock() == this && DCState.getInt(state, DCState.STAGE5) > 1;
		}
		return super.isSuitablePlace(world, pos, state);
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
		CropStage stage = this.getCurrentStage(thisState);
		if (stage == CropStage.DEAD) {
			return false;
		} else if (stage == CropStage.GROWN) {
			return false;
		} else if (DCState.getBool(thisState, DCState.DOUBLE)) {
			return false;
		} else {
			int age = DCState.getInt(thisState, DCState.STAGE5);
			BlockState upper = world.getBlockState(pos.above());
			if (age == 1 && upper.getBlock() == Blocks.AIR) {
				if (upper.getBlock() == Blocks.AIR) {
					BlockState up = thisState.setValue(DCState.DOUBLE, true).setValue(DCState.STAGE5, 2);
					world.setBlock(pos, up, 3);
				}
			}
			if (age >= 0 && age < 4) {
				age++;
				if (age > 1) {
					if (upper.getBlock() == Blocks.AIR || upper.getBlock() == this) {
						BlockState up = thisState.setValue(DCState.DOUBLE, true).setValue(DCState.STAGE5, age);
						world.setBlock(pos.above(), up, 3);
					}
				}
				BlockState next = thisState.setValue(DCState.STAGE5, age);
				return world.setBlock(pos, next, 3);
			}
		}
		return false;
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
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/solanum_0")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_1")),
			new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_2")),
			new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_f")),
			new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/knotweed_" + getSpeciesName(cropTier) + "_c")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("false_0", "false_1", "false_2", "false_3", "false_4", "true_0", "true_1", "true_2", "true_3", "true_4");
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_knotweed_" + getSpeciesName(cropTier)));
	}

	/* IClimateCrop */

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState().setValue(DCState.STAGE5, Integer.valueOf(2)).setValue(DCState.WILD, true);
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
	public ItemLike getSeedItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.BLOCK_KN_BUCKWHEAT.get();
		case RARE:
			return FoodInit.BLOCK_KN_INDIGO.get();
		default:
			return FoodInit.BLOCK_KN_SORREL.get();
		}
	}

	@Override
	public Item getCropItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.CROP_KN_BUCKWHEAT.get();
		case RARE:
			return FoodInit.CROP_KN_INDIGO.get();
		default:
			return FoodInit.CROP_KN_SORREL.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_KN_SORREL.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_KN_BUCKWHEAT.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_KN_INDIGO.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		switch (t) {
		case WILD, COMMON:
			return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		default:
			return ImmutableList.of(SoilType.FARMLAND);
		}
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
		switch (t) {
		case WILD:
			return ImmutableList.of("PLAINS", "MOUNTAIN");
		// case COMMON:
		// return ImmutableList.of("COLD", "DRY");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("DENCE", "DRY");
		// case COMMON:
		// return ImmutableList.of("SANDY", "HOT", "WET");
		default:
			return Lists.newArrayList();
		}
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
