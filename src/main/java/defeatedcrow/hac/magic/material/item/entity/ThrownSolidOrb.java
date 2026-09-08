package defeatedcrow.hac.magic.material.item.entity;

import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class ThrownSolidOrb extends ThrowableItemProjectile {

	public final static int EXP_VALUE = 100;

	public ThrownSolidOrb(EntityType<? extends ThrowableItemProjectile> type, Level level) {
		super(type, level);
	}

	public ThrownSolidOrb(Level level, LivingEntity entity) {
		super(MagicInit.SOLID_ORB.get(), entity, level);
	}

	public ThrownSolidOrb(Level level, double dx, double dy, double dz) {
		super(MagicInit.SOLID_ORB.get(), dx, dy, dz, level);
	}

	@Override
	protected Item getDefaultItem() {
		return MagicInit.EXP_GEM.get();
	}

	@Override
	protected float getGravity() {
		return 0.10F;
	}

	@Override
	protected void onHit(HitResult hitRes) {
		super.onHit(hitRes);
			if (this.level() instanceof ServerLevel serverLevel) {
			MsgEffectToC.sendToClient(serverLevel, hitRes.getLocation(), 81);
				ExperienceOrb.award((ServerLevel) this.level(), this.position(), EXP_VALUE);
			this.discard();
		}

	}

}
