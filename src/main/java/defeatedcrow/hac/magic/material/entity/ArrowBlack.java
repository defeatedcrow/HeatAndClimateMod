package defeatedcrow.hac.magic.material.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ArrowBlack extends AbstractArrow {

	private boolean dealtDamage;

	public ArrowBlack(EntityType<? extends ArrowBlack> type, Level level) {
		super(type, level);
	}

	public ArrowBlack(Level level, LivingEntity shooter) {
		super(MagicInit.ARROW_BLACK_ENTITY.get(), shooter, level);
	}

	public ArrowBlack(Level level, double x, double y, double z) {
		super(MagicInit.ARROW_BLACK_ENTITY.get(), x, y, z, level);
	}

	@Override
	public void tick() {
		if (this.inGroundTime > 2 || this.getY() < 0 || this.isInLava()) {
			this.dealtDamage = true;
		}

		Entity entity = this.getOwner();
		if ((this.dealtDamage || this.isNoPhysics()) && entity != null && entity instanceof LivingEntity) {
			boolean flag = false;
			BlockPos pos = new BlockPos(Mth.floor(this.getX()), Mth.floor(this.getY()), Mth.floor(this.getZ()));
			if (level.getBlockState(pos).getMaterial().isReplaceable() && level.getBlockState(pos.above()).getMaterial().isReplaceable()) {
				entity.teleportToWithTicket(pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D);
				entity.resetFallDistance();
				flag = true;
			}
			if (flag) {
				this.discard();
			} else {
				if (!this.isAcceptibleReturnOwner()) {
					dropItem(this.getEyePosition());
				} else {
					dropItem(entity.getEyePosition());
				}
				this.discard();
			}
		}

		super.tick();
		if (this.level.isClientSide && !this.inGround) {
			this.level.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
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
		return this.dealtDamage ? null : super.findHitEntity(vec, vec2);
	}

	@Override
	protected void doPostHurtEffects(LivingEntity liv) {
		super.doPostHurtEffects(liv);
		if (liv != null && this.isAcceptibleReturnOwner()) {
			BlockPos pos = new BlockPos(Mth.floor(liv.getX()), Mth.floor(liv.getY()), Mth.floor(liv.getZ()));
			BlockPos pos2 = new BlockPos(Mth.floor(getOwner().getX()), Mth.floor(getOwner().getY()), Mth.floor(getOwner().getZ()));
			if (level.getBlockState(pos).getMaterial().isReplaceable() && level.getBlockState(pos.above()).getMaterial().isReplaceable()) {
				getOwner().teleportToWithTicket(pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D);
				getOwner().resetFallDistance();
				liv.teleportToWithTicket(pos2.getX() + 0.5D, pos2.getY() + 0.15D, pos2.getZ() + 0.5D);
			}
		}
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(MagicInit.ARROW_BLACK.get());
	}

	@Override
	protected float getWaterInertia() {
		return 0.99F;
	}

}
