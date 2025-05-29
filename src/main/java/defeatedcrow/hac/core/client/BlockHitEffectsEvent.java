package defeatedcrow.hac.core.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class BlockHitEffectsEvent {

	private static boolean hasEffectBooster = false;

	public static void setBooster(boolean b) {
		hasEffectBooster = b;
	}

	@SubscribeEvent
	public static void onBlockHitEffects(InputEvent.InteractionKeyMappingTriggered event) {
		if (hasEffectBooster) {
			Minecraft mc = Minecraft.getInstance();
			if (event.isAttack() && !mc.player.isUsingItem() && mc.hitResult != null && mc.hitResult.getType() == HitResult.Type.BLOCK) {
				BlockHitResult res = (BlockHitResult) mc.hitResult;
				BlockPos pos = res.getBlockPos();
				Direction dir = res.getDirection();
				if (!mc.level.isEmptyBlock(pos)) {
					// 向こう側
					BlockState check = mc.level.getBlockState(pos.relative(dir.getOpposite()));
					BlockState check2 = mc.level.getBlockState(pos.above());
					Vec3 vec = Vec3.atCenterOf(pos);
					double dx = (0.2D + dir.getStepX()) * (0.2D * mc.level.getRandom().nextDouble() - 0.1D);
					double dy = (0.2D + dir.getStepY()) * (0.2D * mc.level.getRandom().nextDouble() - 0.1D);
					double dz = (0.2D + dir.getStepZ()) * (0.2D * mc.level.getRandom().nextDouble() - 0.1D);
					vec = vec.add(dir.getStepX() * 0.6D, dir.getStepY() * 0.6D, dir.getStepZ() * 0.6D);
					if (check.getFluidState().is(FluidTags.LAVA) && check2.getFluidState().is(FluidTags.LAVA)) {
						mc.level.addParticle(ParticleTypes.DRIPPING_LAVA, vec.x, vec.y, vec.z, dx, dy, dz);
					} else if (check.getFluidState().is(FluidTags.WATER) && check2.getFluidState().is(FluidTags.WATER)) {
						mc.level.addParticle(ParticleTypes.SPLASH, vec.x, vec.y, vec.z, dx, dy, dz);
					} else if (check.isAir()) {
						mc.level.addParticle(ParticleTypes.SMOKE, vec.x, vec.y, vec.z, dx, dy, dz);
					}
				}
			}
		}

	}

}
