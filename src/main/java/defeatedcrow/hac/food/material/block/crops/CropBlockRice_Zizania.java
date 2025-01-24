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
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class CropBlockRice_Zizania extends ClimateCropBaseBlock implements SimpleWaterloggedBlock {

	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public CropBlockRice_Zizania(CropTier t) {
		super(t);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(DCState.DOUBLE, Boolean.valueOf(false))
				.setValue(DCState.STAGE6, Integer.valueOf(0))
				.setValue(WATERLOGGED, false)
				.setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.DOUBLE, DCState.STAGE6, WATERLOGGED, DCState.WILD);
	}

	/* waterlogged */

	@Override
	public BlockState updateShape(BlockState state, Direction dir, BlockState state2, LevelAccessor level, BlockPos p1, BlockPos p2) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(p1, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return super.updateShape(state, dir, state2, level, p1, p2);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return DCState.getBool(state, WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean canPlaceLiquid(BlockGetter level, BlockPos pos, BlockState state, Fluid water) {
		return !state.getValue(BlockStateProperties.WATERLOGGED) && water == Fluids.WATER;
	}

	@Override
	public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState water) {
		if (!state.getValue(BlockStateProperties.WATERLOGGED) && water.getType() == Fluids.WATER) {
			if (!level.isClientSide()) {
				level.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
				level.scheduleTick(pos, water.getType(), water.getType().getTickDelay(level));
			}
			return true;
		}
		return false;
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
		CropStage stage = this.getCurrentStage(thisState);
		if (stage == CropStage.DEAD) {
			return false;
		} else if (stage == CropStage.GROWN) {
			return false;
		} else if (DCState.getBool(thisState, DCState.DOUBLE)) {
			return false;
		} else {
			int age = DCState.getInt(thisState, DCState.STAGE6);
			BlockState upper = world.getBlockState(pos.above());
			if (age == 1 && upper.getBlock() == Blocks.AIR) {
				if (upper.getBlock() == Blocks.AIR) {
					BlockState up = thisState.setValue(DCState.DOUBLE, true).setValue(WATERLOGGED, false).setValue(DCState.STAGE6, 2);
					world.setBlock(pos, up, 3);
				}
			}
			if (age >= 0 && age < 4) {
				age++;
				if (age > 1) {
					if (upper.getBlock() == Blocks.AIR || upper.getBlock() == this) {
						BlockState up = thisState.setValue(DCState.DOUBLE, true).setValue(WATERLOGGED, false).setValue(DCState.STAGE6, age);
						world.setBlock(pos.above(), up, 3);
					}
				}
				BlockState next = thisState.setValue(DCState.STAGE6, age);
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
		return "food/cropblock_rice_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
				new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_0")),
				new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_1")),
				new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_zizania_2")),
				new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_zizania_f")),
				new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_zizania_c")),
				new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_zizania_d")),
				new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_0")),
				new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_1")),
				new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_zizania_2")),
				new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_zizania_f")),
				new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_zizania_c")),
				new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/rice_zizania_d")));
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
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_rice_zizania"));
	}

	/* IClimateCrop */

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState().setValue(DCState.STAGE6, Integer.valueOf(2)).setValue(DCState.WILD, true);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.RICE;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.DOUBLE;
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return 5;
	}

	@Override
	public AquaticType isAquaticPlant(CropTier tier) {
		return tier == CropTier.WILD ? AquaticType.EMERGED : AquaticType.NONE;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.BLOCK_RI_SHORT.get();
		case RARE:
			return FoodInit.BLOCK_RI_AROMA.get();
		default:
			return FoodInit.BLOCK_RI_ZIZANIA.get();
		}
	}

	@Override
	public Item getCropItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.CROP_RI_SHORT.get();
		case RARE:
			return FoodInit.CROP_RI_AROMA.get();
		default:
			return FoodInit.CROP_RI_ZIZANIA.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_RI_ZIZANIA.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_RI_SHORT.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_RI_AROMA.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		switch (t) {
		case COMMON:
			return ImmutableList.of(SoilType.FARMLAND, SoilType.MUD);
		case WILD:
			return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.MUD, SoilType.SAND);
		default:
			return ImmutableList.of(SoilType.FARMLAND, SoilType.MUD);
		}
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET, DCHumidity.UNDERWATER);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return DCAirflow.elements();
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("SWAMP", "RIVER");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("COLD");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "zizania";
	}

}
