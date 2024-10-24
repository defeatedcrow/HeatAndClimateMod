package defeatedcrow.hac.core.util;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class CustomExplosion extends Explosion {

	private final boolean isSafety;
	private final Level lev;
	private final float rad;
	private Entity igniter;

	public CustomExplosion(Level levelIn, @Nullable Entity bom, double x, double y, double z, float radiusIn, boolean safetyIn) {
		super(levelIn, bom, null, null, x, y, z, radiusIn, false, Explosion.BlockInteraction.NONE);
		isSafety = safetyIn;
		lev = levelIn;
		rad = radiusIn;
		if (bom instanceof OwnableEntity && ((OwnableEntity) bom).getOwner() != null) {
			igniter = ((OwnableEntity) bom).getOwner();
		} else if (bom instanceof Projectile && ((Projectile) bom).getOwner() != null) {
			igniter = ((Projectile) bom).getOwner();
		} else {
			igniter = bom;
		}
	}

	@Override
	public void explode() {
		Entity bom = this.getExploder();
		Vec3 vec = new Vec3(getPosition().x, getPosition().y, getPosition().z);
		this.lev.gameEvent(bom, GameEvent.EXPLODE, vec);
		// ブロックを破壊しない

		float f2 = this.rad * 2.0F;
		int k1 = Mth.floor(vec.x - f2 - 1.0D);
		int l1 = Mth.floor(vec.x + f2 + 1.0D);
		int i2 = Mth.floor(vec.y - f2 - 1.0D);
		int i1 = Mth.floor(vec.y + f2 + 1.0D);
		int j2 = Mth.floor(vec.z - f2 - 1.0D);
		int j1 = Mth.floor(vec.z + f2 + 1.0D);
		List<Entity> list = lev.getEntities(bom, new AABB(k1, i2, j2, l1, i1, j1))
				.stream().filter(e -> !e.equals(igniter) && !e.equals(bom)).filter(e -> !isSafety || e instanceof Enemy).toList();

		net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(lev, this, list, f2);

		for (int k2 = 0; k2 < list.size(); ++k2) {
			Entity target = list.get(k2);
			if (!target.ignoreExplosion()) {
				double d12 = Math.sqrt(target.distanceToSqr(vec)) / f2;
				if (d12 <= 1.0D) {
					double d5 = target.getX() - vec.x;
					double d7 = (target instanceof PrimedTnt ? target.getY() : target.getEyeY()) - vec.y;
					double d9 = target.getZ() - vec.z;
					double d13 = Math.sqrt(d5 * d5 + d7 * d7 + d9 * d9);
					if (d13 != 0.0D) {
						d5 /= d13;
						d7 /= d13;
						d9 /= d13;
						double d14 = getSeenPercent(vec, target);
						double d10 = (1.0D - d12) * d14;
						float damage = ((int) ((d10 * d10 + d10) / 2.0D * 7.0D * f2 + 1.0D));
						if (target instanceof Mob mob) {
							if ((mob.getTarget() != null && mob.getTarget().equals(getSourceMob())) || mob.getTarget() instanceof Player) {
								damage *= 3.0F;
							}
						}
						// 無敵時間剥がし
						target.invulnerableTime = 0;
						target.hurt(this.getDamageSource(), damage);
						double d11 = d10;
						if (target instanceof LivingEntity) {
							d11 = ProtectionEnchantment.getExplosionKnockbackAfterDampener((LivingEntity) target, d10);
						}

						target.setDeltaMovement(target.getDeltaMovement().add(d5 * d11, d7 * d11, d9 * d11));
						if (target instanceof Player) {
							Player player = (Player) target;
							if (!player.isSpectator() && (!player.isCreative() || !player.getAbilities().flying)) {
								getHitPlayers().put(player, new Vec3(d5 * d10, d7 * d10, d9 * d10));
							}
						}
					}
				}
			}
		}
	}

}
