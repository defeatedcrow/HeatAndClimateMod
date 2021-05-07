package defeatedcrow.hac.main.entity;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/* ベースは矢だが手投げ式 */
public abstract class EntityProjBase extends EntityArrow implements IProjectile {

	private int age = 0;

	public EntityProjBase(World worldIn) {
		super(worldIn);
		this.setStart(false);
	}

	public EntityProjBase(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjBase(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		this.shootingEntity = shooter;
	}

	private int count = 0;
	private boolean start = false;

	public void setStart(boolean i) {
		this.start = i;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		age++;

		if (!this.world.isRemote) {
			if (this.start) {
				this.onGroundHit();
			} else if (this.inGround) {
				this.setStart(true);
			} else if (this.inWater) {
				this.setStart(true);
			}
		} else {
			if (this.inGround) {
				this.onGroundClient();
			} else {
				this.world
						.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}

		if ((this.motionX * this.motionX + this.motionZ * this.motionZ + this.motionY * this.motionY) < 0.1D) {
			if (age > 600) {
				this.setDead();
			}
		}
	}

	/* 着弾時のエフェクト表現用 */
	protected void onGroundClient() {

	}

	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		if (age < 1)
			return;
		Entity entity = raytraceResultIn.entityHit;

		if (entity != null && !this.world.isRemote) {

			if (entity.isEntityEqual(this.shootingEntity) || entity instanceof IProjectile)
				return;

			if (this.onEntityHit(entity))
				return;

			float speed = MathHelper
					.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			float damage = this.getHitDamage(entity, speed);
			DamageSource source = this.getHitSource(entity);

			if (source != null && damage > 0.0F && entity.attackEntityFrom(source, damage)) {
				if (entity instanceof EntityLivingBase) {
					EntityLivingBase living = (EntityLivingBase) entity;
					if (this.shootingEntity != null && living != this.shootingEntity && living instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
						((EntityPlayerMP) this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6,
								0.0F));
					}
				}

				this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

				// ヒットすると消えてしまう
				if (!canPenetrate()) {
					this.setDead();
				}

			} else {
				this.dropAndDeath();
			}
		} else {
			super.onHit(raytraceResultIn);
		}
	}

	protected boolean onGroundHit() {
		return false;
	}

	protected boolean onEntityHit(Entity entity) {
		return false;
	}

	protected float getHitDamage(Entity target, float speed) {
		return 0.0F;
	}

	protected DamageSource getHitSource(Entity target) {
		return null;
	}

	protected boolean canPenetrate() {
		return false;
	}

	public abstract ItemStack getDropStack();

	protected void dropAndDeath() {
		dropAsItem();
		this.setDead();
	}

	protected void dropAsItem() {
		if (!world.isRemote) {
			ItemStack item = this.getDropStack();
			if (!DCUtil.isEmpty(item)) {
				EntityItem drop = new EntityItem(world, posX, posY + 0.15D, posZ, item);
				drop.motionY = 0.025D;
				world.spawnEntity(drop);
			}
		}
	}

	@Override
	public boolean getIsCritical() {
		return false;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasKey("HitStart")) {
			this.start = compound.getBoolean("HitStart");
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("HitStart", this.start);
	}
}
