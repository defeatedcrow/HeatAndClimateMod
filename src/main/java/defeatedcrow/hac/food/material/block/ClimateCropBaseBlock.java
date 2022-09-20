package defeatedcrow.hac.food.material.block;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.crop.IFertileBlock;
import defeatedcrow.hac.api.util.DCState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class ClimateCropBaseBlock extends BushBlock implements IClimateCrop, BonemealableBlock {

	public ClimateCropBaseBlock() {
		super(getProp());
	}

	/* 基本データ */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext col) {
		int stage = DCState.getInt(state, DCState.STAGE5);
		if (stage == 0) {
			return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
		} else if (stage == 1) {
			return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
		}
		return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isAreaLoaded(pos, 2))
			return;
		CropStage stage = getCurrentStage(state);

		int c1 = getMutationChance(level, pos, state);
		if (stage == CropStage.GROUND && getTier() == CropTier.WILD && c1 > 0 && random.nextInt(c1) > 70) {
			onMutation(level, pos, state, random);
			return;
		}

		int c2 = getGrowingChance(level, pos, state);
		if (c1 > 0 && random.nextInt(c2) == 0) {
			onGrow(level, pos, state);
			return;
		}

		checkAndDropBlock(level, pos, state);

	}

	protected void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		if (!isSuitablePlace(world, pos.below(), world.getBlockState(pos.below()))) {
			dropResources(state, world, pos);
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
		}
	}

	public static List<ItemStack> getDrops(BlockState state, ServerLevel level, BlockPos pos,
			@Nullable BlockEntity tile) {
		return getDrops(state, level, pos, tile, null, null);
	}

	public static List<ItemStack> getDrops(BlockState state, ServerLevel level, BlockPos pos,
			@Nullable BlockEntity p_49878_, @Nullable Entity breaker, ItemStack tool) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state.getBlock() instanceof IClimateCrop) {
			IClimateCrop crop = (IClimateCrop) state.getBlock();

			CropStage stage = crop.getCurrentStage(state);
			CropTier tier = crop.getTier();

			float seedChance = 0.4F + stage.id * 0.2F;
			if (level.random.nextFloat() <= seedChance) {
				ret.add(crop.getSeedItem(state));
			}
			if (crop.canHarvest(level, pos, state)) {
				int f = tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
				ret.addAll(crop.getCropItems(state, f));
			}
		}
		return ret;
	}

	/* BushBlock */
	@Override
	protected boolean mayPlaceOn(BlockState under, BlockGetter level, BlockPos pos) {
		return isSuitablePlace(level, pos, under);
	}

	// 破壊処理とシェイプ更新を兼ねているよくわからないメソッド
	@Override
	public BlockState updateShape(BlockState state, Direction face, BlockState other, LevelAccessor level, BlockPos pos,
			BlockPos pos2) {
		return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() :
				super.updateShape(state, face, other, level, pos, pos2);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos down = pos.below();
		if (state.getBlock() == this)
			return level.getBlockState(down).canSustainPlant(level, down, Direction.UP, this);
		return this.mayPlaceOn(level.getBlockState(down), level, down);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
		return type == PathComputationType.AIR && !this.hasCollision ? true :
				super.isPathfindable(state, level, pos, type);
	}

	@Override
	public BlockState getPlant(BlockGetter world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		if (state.getBlock() != this)
			return defaultBlockState();
		return state;
	}

	/* ClimateCrop */
	public boolean canSustain(BlockState state, BlockGetter level, BlockPos pos) {
		BlockPos down = pos.below();
		BlockState under = level.getBlockState(down);
		return isSuitablePlace(level, down, under);
	}

	protected static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP);
	}

	/* Bonemeal */
	@Override
	public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean b) {
		CropStage stage = this.getCurrentStage(state);
		return stage.canUseBonemeal();
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		this.onGrow(level, pos, state);
	}

	/* IClimateCrop */

	@Override
	public abstract ItemStack getSeedItem(BlockState thisState);

	@Override
	public abstract List<ItemStack> getCropItems(BlockState thisState, int fortune);

	@Override
	public boolean isSuitableForGrowing(Level world, BlockPos pos, BlockState thisState) {
		ClimateSupplier spr = new ClimateSupplier(world, pos);
		IClimate climate = spr.get();
		boolean temp = this.getSuitableTemp().contains(climate.getHeat());
		boolean hum = this.getSuitableHum().contains(climate.getHumidity());
		boolean air = this.getSuitableAir().contains(climate.getAirflow());
		return temp && hum && air;
	}

	@Override
	public abstract boolean isSuitablePlace(BlockGetter level, BlockPos pos, BlockState underState);

	@Override
	public CropStage getCurrentStage(BlockState state) {
		int stage = DCState.getInt(state, DCState.STAGE5);
		if (stage == 0) {
			return CropStage.GROUND;
		} else if (stage == 3) {
			return CropStage.FLOWER;
		} else if (stage == 4)
			return CropStage.GROWN;
		return CropStage.YOUNG;
	}

	@Override
	public abstract CropTier getTier();

	@Override
	public abstract CropType getFamily();

	@Override
	public abstract CropGrowType getGrowType();

	@Override
	public BlockState getGrownState() {
		return this.defaultBlockState().setValue(DCState.STAGE5, Integer.valueOf(4));
	}

	@Override
	public BlockState getHarvestedState() {
		return this.defaultBlockState();
	}

	@Override
	public BlockState setGroundState() {
		return this.defaultBlockState();
	}

	@Override
	public int getGrowingChance(Level world, BlockPos pos, BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		if (stage != CropStage.GROWN && stage != CropStage.DEAD) {
			boolean clm = isSuitableForGrowing(world, pos, thisState);
			int ret = clm ? 8 : 50;
			BlockState under = world.getBlockState(pos.below());
			if (under.getBlock() instanceof IFertileBlock && ((IFertileBlock) under.getBlock()).isFertile(world, pos.below(), under)) {
				ret /= 2;
			}
			return ret;
		}
		return 0;
	}

	@Override
	public boolean onGrow(Level world, BlockPos pos, BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		if (stage == CropStage.DEAD) {
			return false;
		} else if (stage == CropStage.GROWN) {
			return false;
		} else {
			int age = DCState.getInt(thisState, DCState.STAGE5);
			if (age >= 0 && age < 4) {
				age++;
				BlockState next = thisState.setValue(DCState.STAGE5, age);
				return world.setBlock(pos, next, 3);
			}
		}
		return false;
	}

	@Override
	public boolean canHarvest(Level world, BlockPos pos, BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		return stage == CropStage.GROWN;
	}

	@Override
	public boolean onHarvest(Level world, BlockPos pos, BlockState thisState, Player player) {
		if (thisState != null && thisState.getBlock() instanceof IClimateCrop) {
			CropStage stage = this.getCurrentStage(thisState);
			if (stage == CropStage.GROWN) {
				int f = 0;
				if (player != null && !player.getItemInHand(InteractionHand.MAIN_HAND).isEnchantable()) {
					f = player.getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
				}
				List<ItemStack> crops = this.getCropItems(thisState, f);
				boolean ret = false;
				for (ItemStack item : crops) {
					ItemEntity drop;
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
					BlockState next = this.getHarvestedState();
					world.setBlock(pos, next, 2);
				}
				return ret;
			}
		}
		return false;
	}

	@Override
	public int getMutationChance(Level world, BlockPos pos, BlockState thisState) {
		if (getTier() == CropTier.WILD) {
			boolean clm = isSuitableForGrowing(world, pos, thisState);
			int ret = clm ? 60 : 0;
			BlockState under = world.getBlockState(pos.below());
			if (under.getBlock() instanceof FarmBlock) {
				ret += 20;
			}
			if (under.getBlock() instanceof IFertileBlock && ((IFertileBlock) under.getBlock()).isFertile(world, pos.below(), under)) {
				ret += 20;
			}
			return ret;
		}
		return 0;
	}

	@Override
	public abstract boolean onMutation(Level world, BlockPos pos, BlockState thisState, RandomSource random);

	@Override
	public abstract List<DCHeatTier> getSuitableTemp();

	@Override
	public abstract List<DCHumidity> getSuitableHum();

	@Override
	public abstract List<DCAirflow> getSuitableAir();

}
