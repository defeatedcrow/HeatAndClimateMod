package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class CropBlockMallow extends ClimateCropBaseBlock {

	public CropBlockMallow(CropTier t) {
		super(t);
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.DOUBLE, Boolean.valueOf(false)).setValue(DCState.STAGE5, Integer.valueOf(0)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.DOUBLE, DCState.STAGE5);
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
		return getTier() != CropTier.RARE ? state.setValue(DCState.STAGE5, 0) : state.setValue(DCState.STAGE5, 2);
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
	public boolean canHarvest(BlockState thisState) {
		return getTier() == CropTier.RARE ? DCState.getInt(thisState, DCState.STAGE5) > 2 : super.canHarvest(thisState);
	}

	@Override
	public boolean onHarvest(Level world, BlockPos pos, BlockState thisState, Player player) {
		if (thisState != null && thisState.getBlock() instanceof IClimateCrop) {
			if (DCState.getBool(thisState, DCState.DOUBLE)) {
				thisState = world.getBlockState(pos.below());
				pos = pos.below();
			}
			if (canHarvest(thisState)) {
				int f = 0;
				if (player != null && !player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
					f = player.getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
				}
				List<ItemStack> crops = this.getCropItems(thisState, f);
				boolean ret = false;
				for (ItemStack item : crops) {
					ItemEntity drop;
					item.setCount(2);
					if (player != null) {
						drop = new ItemEntity(world, player.getX(), player.getY() + 0.15D, player.getZ(), item);
					} else {
						drop = new ItemEntity(world, pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D, item);
					}
					if (drop != null && !world.isClientSide)
						world.addFreshEntity(drop);
					ret = true;
				}
				if (ret) {
					if (this.cropTier == CropTier.WILD || cropTier == CropTier.COMMON) {
						BlockState next = this.getHarvestedState(thisState);
						world.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 2);
						world.setBlock(pos, next, 2);
					} else {
						BlockState next = this.getHarvestedState(thisState);
						world.setBlock(pos.above(), next.setValue(DCState.DOUBLE, true), 2);
						world.setBlock(pos, next, 2);
					}

				}
				return ret;
			}
		}
		return false;
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_mallow_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/solanum_0")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/solanum_1")),
			new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/mallow_" + getSpeciesName(cropTier) + "_2")),
			new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/mallow_" + getSpeciesName(cropTier) + "_f")),
			new JsonModelDC("dcs_climate:block/dcs_cross_under", ImmutableMap.of("cross", "dcs_climate:block/crop/mallow_" + getSpeciesName(cropTier) + "_c")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/solanum_0")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/solanum_1")),
			new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/mallow_" + getSpeciesName(cropTier) + "_2")),
			new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/mallow_" + getSpeciesName(cropTier) + "_f")),
			new JsonModelDC("dcs_climate:block/dcs_cross_upper", ImmutableMap.of("cross", "dcs_climate:block/crop/mallow_" + getSpeciesName(cropTier) + "_c")));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.of(new String[] { "false_0", "false_1", "false_2", "false_3", "false_4", "true_0", "true_1", "true_2", "true_3", "true_4" });
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_mallow_" + getSpeciesName(cropTier)));
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.MALLOW;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.DOUBLE;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.BLOCK_ML_COTTON.get();
		case RARE:
			return FoodInit.BLOCK_ML_BLUE.get();
		default:
			return FoodInit.BLOCK_ML_JUTE.get();
		}
	}

	@Override
	public Item getCropItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.CROP_ML_COTTON.get();
		case RARE:
			return FoodInit.CROP_ML_BLUE.get();
		default:
			return FoodInit.CROP_ML_JUTE.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_ML_JUTE.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_ML_COTTON.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_ML_BLUE.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
		default:
			return ImmutableList.of(SoilType.FARMLAND);
		}
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.RARE) {
			return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
		}
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL, DCHeatTier.OVEN);
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
		switch (t) {
		case WILD:
			return ImmutableList.of("DESERT", "SAVANNA");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "cotton";
		if (tier == CropTier.RARE)
			return "blue";
		return "jute";
	}

}
