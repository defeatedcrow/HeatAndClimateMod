package defeatedcrow.hac.magic.material.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ArrowWhite extends AbstractArrow {

	private boolean dealtDamage;
	private boolean putLight;

	public ArrowWhite(EntityType<? extends ArrowWhite> type, Level level) {
		super(type, level);
	}

	public ArrowWhite(Level level, LivingEntity shooter) {
		super(MagicInit.ARROW_WHITE_ENTITY.get(), shooter, level);
	}

	public ArrowWhite(Level level, double x, double y, double z) {
		super(MagicInit.ARROW_WHITE_ENTITY.get(), x, y, z, level);
	}

	@Override
	public void tick() {
		if (this.inGroundTime > 0 || this.getY() < this.getLevel().getMinBuildHeight() || this.isInLava()) {
			this.dealtDamage = true;
		}

		if ((this.dealtDamage || this.isNoPhysics())) {
			if (putLight) {
				this.discard();
			} else {
				if (!this.isAcceptibleReturnOwner()) {
					dropItem(this.getEyePosition());
				} else {
					dropItem(getOwner().getEyePosition());
				}
				this.discard();
			}
		}

		super.tick();

	}

	@Override
	protected void onHitBlock(BlockHitResult hit) {
		super.onHitBlock(hit);
		BlockPos pos = hit.getBlockPos();
		Direction dir = hit.getDirection();
		BlockState state = level.getBlockState(pos.relative(dir));
		if (state.getMaterial().isReplaceable()) {
			BlockState light = MagicInit.SMALL_LIGHT.get().defaultBlockState().setValue(DCState.DIRECTION, dir.getOpposite());
			level.setBlock(pos.relative(dir), light, 3);
			this.putLight = true;
			if (this.level.isClientSide && !this.inGround) {
				this.level.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	public void dropItem(Vec3 pos) {
		if (!level.isClientSide && !getPickupItem().isEmpty()) {
			ItemEntity drop = new ItemEntity(level, pos.x, pos.y + 0.1D, pos.z, this.getPickupItem());
			level.addFreshEntity(drop);
		}
	}

	private boolean isAcceptibleReturnOwner() {
		Entity entity = this.getOwner();
		if (entity != null && entity.isAlive()) {
			return !entity.isSpectator();
		} else {
			return false;
		}
	}

	@Override
	@Nullable
	protected EntityHitResult findHitEntity(Vec3 vec, Vec3 vec2) {
		return null;
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(MagicInit.ARROW_WHITE.get());
	}

	@Override
	protected float getWaterInertia() {
		return 0.99F;
	}

}
