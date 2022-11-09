package defeatedcrow.hac.core.material.block;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.IClimateObject;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public abstract class ClimateBlock extends BlockDC implements IClimateObject {

	public final boolean forceUpdate;

	public ClimateBlock(BlockBehaviour.Properties prop, boolean f) {
		super(prop);
		forceUpdate = f;
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		super.tick(state, level, pos, rand);
		if (!level.isClientSide && state != null && state.getBlock() != null) {
			IClimate clm = this.onUpdateClimate(level, pos, state);
			if (this.onClimateChange(level, pos, state, clm)) {
				level.gameEvent((Entity) null, GameEvent.BLOCK_DEACTIVATE, pos);
			}
		}
	}

	@Override
	public IClimate onUpdateClimate(Level level, BlockPos pos, BlockState state) {
		IClimate c = new ClimateSupplier(level, pos).get();
		return c;
	}

	@Override
	public boolean onClimateChange(Level level, BlockPos pos, BlockState state, IClimate clm) {
		if (clm != null) {
			DCHeatTier heat = clm.getHeat();
			DCHumidity hum = clm.getHumidity();
			DCAirflow air = clm.getAirflow();
			ItemStack check = new ItemStack(state.getBlock(), 1);
			DCRecipes.INSTANCE.getSmeltingRecipe(clm, check).filter(recipe -> recipe.additionalRequire(level, pos) && hasBlockOutput(recipe)).map((recipe) -> {
				ItemStack output = recipe.getOutput();
				Block ret = ((BlockItem) output.getItem()).getBlock();
				BlockState retS = ret.defaultBlockState();
				if (level.setBlock(pos, retS, 2)) {
					level.updateNeighborsAt(pos, this);

					// 効果音
					if (playSEOnChanging()) {
						level.playSound(null, pos, getSE(), SoundSource.BLOCKS, 0.8F, 1.5F);
						DCLogger.debugLog("Smelting! " + output.getDisplayName().getString());
					}

					if (this.isForcedTickUpdate()) {
						level.scheduleTick(pos, retS.getBlock(), recipe.recipeFrequency());
					}
				}
				return true;
			}).orElse(false);

		}
		return false;
	}

	static boolean hasBlockOutput(IClimateSmelting recipe) {
		ItemStack output = recipe.getOutput();
		return !DCUtil.isEmpty(output) && output.getItem() instanceof BlockItem;
	}

	private void updateNeighbours(BlockState state, Level level, BlockPos pos) {
		level.updateNeighborsAt(pos, this);
	}

	@Override
	public SoundEvent getSE() {
		return SoundEvents.LAVA_EXTINGUISH;
	}

	@Override
	public boolean playSEOnChanging() {
		return true;
	}

	@Override
	public boolean isForcedTickUpdate() {
		return forceUpdate;
	}

}
