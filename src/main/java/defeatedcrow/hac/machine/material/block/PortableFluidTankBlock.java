package defeatedcrow.hac.machine.material.block;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidUtil;

public abstract class PortableFluidTankBlock extends ProcessTileBlock {

	public PortableFluidTankBlock(Properties prop) {
		super(prop);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		BlockEntity tile = level.getBlockEntity(pos);
		if (tile instanceof PortableFluidTankTile tank) {
			if (ClimateCore.isDebug && player.isCrouching()) {
				DCLogger.debugInfoLog("### Fluid: " + tank.tank.getFluid().getDisplayName().getString() + "/" + tank.tank.getFluid().getAmount() + " ###");
			}
			ItemStack held = player.getItemInHand(hand);
			if (level.isClientSide) {
				return InteractionResult.SUCCESS;
			} else {
				if (!DCUtil.isEmpty(held) && FluidUtil.getFluidHandler(held.copy()).isPresent()) {
					if (DCFluidUtil.exchangeFluid(level, player.position(), tank.tank, held)) {
						tile.setChanged();
						player.getInventory().setChanged();
						level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1.0F, 1.2F);
					}
					return InteractionResult.CONSUME;
				} else {
					super.use(state, level, pos, player, hand, hitRes);
				}
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

}
