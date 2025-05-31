package defeatedcrow.hac.core.client.event;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
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
					Vec3 vec = Vec3.atCenterOf(pos);
					double dx = (0.2D + dir.getStepX()) * (0.2D * mc.level.getRandom().nextDouble() - 0.1D);
					double dy = 0D;
					double dz = (0.2D + dir.getStepZ()) * (0.2D * mc.level.getRandom().nextDouble() - 0.1D);
					vec = vec.add(dir.getStepX() * 0.6D, dir.getStepY() * 0.6D, dir.getStepZ() * 0.6D);

					long lava = Direction.stream().filter(d -> d != dir && DCUtil.canEditPos(mc.level, pos.relative(d))).filter(d -> {
						return mc.level.getBlockState(pos.relative(d)).getFluidState().is(FluidTags.LAVA);
					}).count();
					long water = Direction.stream().filter(d -> d != dir && DCUtil.canEditPos(mc.level, pos.relative(d))).filter(d -> {
						return mc.level.getBlockState(pos.relative(d)).getFluidState().is(FluidTags.WATER);
					}).count();
					boolean air = DCUtil.canEditPos(mc.level, pos.relative(dir.getOpposite())) &&
							mc.level.getBlockState(pos.relative(dir.getOpposite())).isAir();

					if (lava > 0) {
						for (int i = 0; i < lava; i++)
							mc.level.addParticle(ParticleTypes.DRIPPING_LAVA, vec.x, vec.y, vec.z, dx, dy, dz);
					} else if (water > 0) {
						for (int i = 0; i < water; i++)
							mc.level.addParticle(ParticleTypes.SPLASH, vec.x, vec.y, vec.z, dx, dy, dz);
					} else if (air) {
						mc.level.addParticle(ParticleTypes.CLOUD, vec.x, vec.y, vec.z, dx, dy, dz);
					}
				}
			}
		}

	}

}
