package defeatedcrow.hac.magic.material.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.material.entity.ChairEntity;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ArrowBindPlant extends AbstractArrow {

	private boolean dealtDamage;
	private int maxAge = 1200;

	public ArrowBindPlant(EntityType<? extends ArrowBindPlant> type, Level level) {
		super(type, level);
	}

	public ArrowBindPlant(Level level, LivingEntity shooter) {
		super(MagicInit.ARROW_BIND_ENTITY.get(), shooter, level);
	}

	public ArrowBindPlant(Level level, double x, double y, double z) {
		super(MagicInit.ARROW_BIND_ENTITY.get(), x, y, z, level);
	}

	public void setMaxAge(int a) {
		maxAge = a;
	}

	@Override
	public void tick() {
		if (this.dealtDamage || this.inGroundTime > 2 || this.getY() < 0 || this.isInLava()) {
			this.discard();
		}
		super.tick();
	}

	public void dropItem(Vec3 pos) {}

	private boolean isAcceptibleReturnOwner() {
		return false;
	}

	@Override
	@Nullable
	protected EntityHitResult findHitEntity(Vec3 vec, Vec3 vec2) {
		return this.dealtDamage ? null : super.findHitEntity(vec, vec2);
	}

	@Override
	protected void onHitEntity(EntityHitResult res) {
		Entity entity = res.getEntity();
		Entity owner = this.getOwner();

		if (entity instanceof LivingEntity) {
			LivingEntity liv = (LivingEntity) entity;

			this.dealtDamage = true;
			if (liv != null && !level.isClientSide) {
				if (liv.getVehicle() != null) {
					liv.removeVehicle();
				}
				ChairEntity bind = MagicInit.BIND_PLANT_ENTITY.get().create(level);
				bind.setPos(liv.position());
				bind.setDeltaMovement(0D, 0D, 0D);
				bind.setMaxAge(maxAge);
				if (owner instanceof Player player) {
					bind.setOwner(player.getUUID());
				}
				liv.startRiding(bind);
				level.addFreshEntity(bind);
			}

			this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
			if (this.getPierceLevel() <= 0) {
				this.discard();
				return;
			}
		}

		super.onHitEntity(res);
	}

	@Override
	protected ItemStack getPickupItem() {
		return ItemStack.EMPTY;
	}

	@Override
	protected float getWaterInertia() {
		return 0.99F;
	}

}
