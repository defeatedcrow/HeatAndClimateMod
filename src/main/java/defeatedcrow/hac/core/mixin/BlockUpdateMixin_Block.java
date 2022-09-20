package defeatedcrow.hac.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import defeatedcrow.hac.api.event.DCBlockUpdateEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(BlockBehaviour.class)
public class BlockUpdateMixin_Block {

	@Inject(method = "randomTick", at = @At(value = "HEAD"), cancellable = true)
	public void onRandomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand,
			CallbackInfo callback) {
		DCBlockUpdateEvent event = new DCBlockUpdateEvent(state, world, pos, rand);
		if (event.post()) {
			callback.cancel();
		}
	}

}
