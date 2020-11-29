package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.magic.entity.EntityFlowerTurret;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.entity.EntityBulletDC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFlowerBolt extends EntityBulletDC {

	public EntityFlowerBolt(World worldIn) {
		super(worldIn);
	}

	public EntityFlowerBolt(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityFlowerBolt(World worldIn, Entity shooter) {
		super(worldIn, shooter);
	}

	private EntityLivingBase target = null;

	public void setTarget(EntityLivingBase targetIn) {
		target = targetIn;
	}

	@Override
	public double getDamage() {
		return MainCoreConfig.flower_turret_damage;
	}

	@Override
	public double getGravity() {
		return 0D;
	}

	@Override
	public boolean getIsSilver() {
		return true;
	}

	@Override
	public BulletType getBulletType() {
		return BulletType.LIGHT;
	}

	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		Entity entity = raytraceResultIn.entityHit;

		if (entity != null && !entity.world.isRemote) {
			if (entity != target) {
				return;
			}

			float dam = (float) this.getDamage();

			// 対アンデッドで2倍
			if (entity instanceof EntityLiving && ((EntityLiving) entity).isEntityUndead()) {
				dam *= 2;
			}

			DamageSource damagesource = null;
			EntityLivingBase owner = null;

			if (entity == shootingEntity || !(shootingEntity instanceof EntityFlowerTurret)) {
				damagesource = DamageSource.CACTUS.setProjectile();
			} else {
				owner = ((EntityFlowerTurret) shootingEntity).getOwner();
				if (entity == owner) {
					return;
				}

				if (owner != null && owner instanceof EntityPlayer) {
					damagesource = DamageSource.causeIndirectDamage(this, owner).setProjectile();
				} else {
					damagesource = DamageSource.causeIndirectMagicDamage(this, shootingEntity).setProjectile();
				}
			}

			if (damagesource != null && entity.attackEntityFrom(damagesource, dam)) {

				if (entity instanceof EntityLivingBase) {
					EntityLivingBase living = (EntityLivingBase) entity;
					living.hurtResistantTime = 0;

					// ノックバック
					float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

					if (f1 > 0.0F) {
						living.addVelocity(this.motionX * 0.2D / f1, 0.1D, this.motionZ * 0.6D / f1);
					}
					this.arrowHit(living);

					if (owner == null && living != owner && living instanceof EntityPlayer && owner instanceof EntityPlayerMP) {
						((EntityPlayerMP) owner).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
					}
				}

				this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
				this.setDead();
			}
		}
	}
}
