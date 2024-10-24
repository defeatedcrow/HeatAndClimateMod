package defeatedcrow.hac.core.material.block.building;

import java.util.function.ToIntFunction;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class ChandelierCandle extends ChandelierLamp implements IHeatTile {

	public ChandelierCandle(String s) {
		super(s, getProp());
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(DCState.LIT, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(0.1F, 6.0F).noOcclusion().lightLevel(Candlestick.emission(12));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.LIT, WATERLOGGED);
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (s1.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	@Override
	public DCHeatTier getHeatTier(Level level, BlockPos targrt, BlockPos source) {
		BlockState state = level.getBlockState(source);
		return state.getValue(DCState.LIT) ? DCHeatTier.WARM : DCHeatTier.NORMAL;
	}

	public static ToIntFunction<BlockState> emission(int light) {
		return (state) -> {
			return state.getValue(DCState.LIT) ? light : 0;
		};
	}

	@Override
	public void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile proj) {
		if (!level.isClientSide && proj.isOnFire() && !DCState.getBool(state, DCState.LIT)) {
			setLit(state, level, hit.getBlockPos(), true);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.getAbilities().mayBuild && player.getItemInHand(hand).isEmpty() && DCState.getBool(state, DCState.LIT)) {
			extinguish(player, state, level, pos);
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else if (player.getAbilities().mayBuild && player.getItemInHand(hand).is(TagDC.ItemTag.FIRESTARTER) && !DCState.getBool(state, DCState.LIT)) {
			setLit(state, level, pos, true);
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return InteractionResult.PASS;
		}
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		if (DCState.getBool(state, DCState.LIT)) {
			addParticlesAndSound(level, new Vec3(pos.getX() + 0.51565D, pos.getY() + 0.65D, pos.getZ() + 0.89065D), rand);
			addParticlesAndSound(level, new Vec3(pos.getX() - 0.51565D, pos.getY() + 0.65D, pos.getZ() + 0.89065D), rand);
			addParticlesAndSound(level, new Vec3(pos.getX() + 0.51565D, pos.getY() + 0.65D, pos.getZ() - 0.89065D), rand);
			addParticlesAndSound(level, new Vec3(pos.getX() - 0.51565D, pos.getY() + 0.65D, pos.getZ() - 0.89065D), rand);
			addParticlesAndSound(level, new Vec3(pos.getX() + 1.0315D, pos.getY() + 0.65D, pos.getZ() + 0.0D), rand);
			addParticlesAndSound(level, new Vec3(pos.getX() - 1.0315D, pos.getY() + 0.65D, pos.getZ() + 0.0D), rand);
		}
	}

	private static void addParticlesAndSound(Level level, Vec3 vec, RandomSource rand) {
		float f = rand.nextFloat();
		if (f < 0.3F) {
			level.addParticle(ParticleTypes.SMOKE, vec.x + 0.5D, vec.y, vec.z + 0.5D, 0.0D, 0.0D, 0.0D);
			if (f < 0.05F) {
				level.playLocalSound(vec.x + 0.5D, vec.y, vec.z + 0.5D, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
			}
		}
		level.addParticle(ParticleTypes.SMALL_FLAME, vec.x + 0.5D, vec.y, vec.z + 0.5D, 0.0D, 0.0D, 0.0D);
	}

	public static void extinguish(@Nullable Player player, BlockState state, LevelAccessor level, BlockPos pos) {
		setLit(state, level, pos, false);
		level.playSound((Player) null, pos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
		level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
	}

	private static void setLit(BlockState state, LevelAccessor level, BlockPos pos, boolean b) {
		level.setBlock(pos, state.setValue(DCState.LIT, Boolean.valueOf(b)), 11);
	}

	@Override
	public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluid) {
		if (!state.getValue(WATERLOGGED) && fluid.getType() == Fluids.WATER) {
			BlockState blockstate = state.setValue(WATERLOGGED, Boolean.valueOf(true));
			if (DCState.getBool(state, DCState.LIT)) {
				extinguish((Player) null, blockstate, level, pos);
			}
			return super.placeLiquid(level, pos, state, fluid);
		} else {
			return false;
		}
	}
}
