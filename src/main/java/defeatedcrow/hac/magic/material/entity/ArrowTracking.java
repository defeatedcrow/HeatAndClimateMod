package defeatedcrow.hac.magic.material.entity;

import java.util.List;

import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class ArrowTracking extends AbstractArrow {

	private boolean dealtDamage;
	private LivingEntity target;

	public ArrowTracking(EntityType<? extends ArrowTracking> type, Level level) {
		super(type, level);
	}

	public ArrowTracking(Level level, LivingEntity shooter) {
		super(MagicInit.ARROW_GREEN_ENTITY.get(), shooter, level);
	}

	public ArrowTracking(Level level, double x, double y, double z) {
		super(MagicInit.ARROW_GREEN_ENTITY.get(), x, y, z, level);
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(Items.ARROW);
	}

	public void setTarget(LivingEntity entity) {
		target = entity;
	}

	@Override
	public void tick() {
		if (target != null && target.isAlive() && this.tickCount > 10) {
			Vec3 v1 = target.getEyePosition();
			Vec3 v2 = this.getDeltaMovement();
			double x1 = (v1.x - v2.x) * 0.25D;
			double y1 = (v1.y - v2.y) * 0.25D;
			double z1 = (v1.z - v2.z) * 0.25D;
			this.setDeltaMovement(v2.x + x1, v2.y + y1, v2.z + z1);
		}
		super.tick();
	}

	public static LivingEntity getTarget(LivingEntity player) {
		AABB aabb = new AABB(player.blockPosition()).inflate(64F);
		List<LivingEntity> list = player.getLevel().getEntitiesOfClass(LivingEntity.class, aabb, (target) -> {
			return player.hasLineOfSight(target);
		});
		double d = 128D;
		LivingEntity ret = null;
		for (LivingEntity liv : list) {
			Vec3 v1 = player.getEyePosition();
			Vec3 v2 = liv.getEyePosition();
			if (v1.distanceTo(v2) > d)
				ret = liv;
		}
		return ret;
	}

}
