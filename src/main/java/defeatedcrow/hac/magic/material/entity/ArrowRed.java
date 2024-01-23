package defeatedcrow.hac.magic.material.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.util.CustomExplosion;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

public class ArrowRed extends AbstractArrow {

	private boolean dealtDamage;
	private float explodeRenge = 3.0F;
	private boolean isSafety = false;

	public ArrowRed(EntityType<? extends ArrowRed> type, Level level) {
		super(type, level);
	}

	public ArrowRed(Level level, LivingEntity shooter) {
		super(MagicInit.ARROW_RED_ENTITY.get(), shooter, level);
	}

	public ArrowRed(Level level, double x, double y, double z) {
		super(MagicInit.ARROW_RED_ENTITY.get(), x, y, z, level);
	}

	public void setRange(float f) {
		explodeRenge = f;
	}

	public void setSafety() {
		isSafety = true;
	}

	@Override
	public void tick() {
		if (this.inGroundTime > 2 || this.getY() < 0 || this.isInLava()) {
			this.dealtDamage = true;
		}

		if ((this.dealtDamage || this.isNoPhysics())) {
			CustomExplosion exp = new CustomExplosion(level, this, this.getX(), this.getY(), this.getZ(), explodeRenge, isSafety);
			if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(level, exp))
				return;
			exp.explode();
			exp.finalizeExplosion(true);
			this.discard();
		}

		super.tick();
		if (this.level.isClientSide && !this.inGround) {
			this.level.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
		}

	}

	@Override
	@Nullable
	protected EntityHitResult findHitEntity(Vec3 vec, Vec3 vec2) {
		return this.dealtDamage ? null : super.findHitEntity(vec, vec2);
	}

	@Override
	protected boolean canHitEntity(Entity entity) {
		return super.canHitEntity(entity) && entity != this.getOwner() && !(entity instanceof Player);
	}

	@Override
	protected void doPostHurtEffects(LivingEntity liv) {
		super.doPostHurtEffects(liv);
		CustomExplosion exp = new CustomExplosion(level, this, this.getX(), this.getY(), this.getZ(), explodeRenge, isSafety);
		if (ForgeEventFactory.onExplosionStart(level, exp))
			return;
		exp.explode();
		exp.finalizeExplosion(true);
		if (this.level.isClientSide)
			this.level.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 1.0D, 0.0D, 0.0D);
		this.dealtDamage = true;
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(MagicInit.ARROW_RED.get());
	}

	@Override
	protected float getWaterInertia() {
		return 0.99F;
	}

	@Override
	public boolean shouldBlockExplode(Explosion exp, BlockGetter level, BlockPos pos, BlockState state, float f) {
		return false;
	}

}
