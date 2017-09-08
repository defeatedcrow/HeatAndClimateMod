package defeatedcrow.hac.main.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBulletDC extends Entity implements IProjectile {
	private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(new Predicate[] {
			EntitySelectors.NOT_SPECTATING,
			EntitySelectors.IS_ALIVE,
			new Predicate<Entity>() {
				@Override
				public boolean apply(@Nullable Entity p_apply_1_) {
					return p_apply_1_.canBeCollidedWith();
				}
			}
	});
	private int xTile;
	private int yTile;
	private int zTile;
	private Block inTile;
	private int inData;
	protected boolean inGround;
	protected int timeInGround;
	/** Seems to be some sort of timer for animating an arrow. */
	public int arrowShake;
	/** The owner of this arrow. */
	public Entity shootingEntity;
	private int ticksInGround;
	private int ticksInAir;
	protected double damage;
	/** The amount of knockback an arrow applies when it hits a mob. */
	private int knockbackStrength;

	public EntityBulletDC(World worldIn) {
		super(worldIn);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.damage = ClimateCore.isDebug ? 16.0D : 12.0D;
		if (this.getIsRangedAttack()) {
			this.setSize(2.0F, 2.0F);
		} else {
			this.setSize(0.5F, 0.5F);
		}
	}

	public EntityBulletDC(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
	}

	public EntityBulletDC(World worldIn, EntityLivingBase shooter) {
		this(worldIn, shooter.posX, shooter.posY + shooter.getEyeHeight() - 0.1D, shooter.posZ);
		this.shootingEntity = shooter;
	}

	/**
	 * Checks if the entity is in range to render.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double distance) {
		double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 10.0D;

		if (Double.isNaN(d0)) {
			d0 = 1.0D;
		}

		d0 = d0 * 64.0D * getRenderDistanceWeight();
		return distance < d0 * d0;
	}

	@Override
	protected void entityInit() {

	}

	public void setAim(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float f1 = -MathHelper.sin(pitch * 0.017453292F);
		float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		this.setThrowableHeading(f, f1, f2, velocity, inaccuracy);
	}

	@Override
	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
		float f = MathHelper.sqrt_double(x * x + y * y + z * z);
		x = x / f;
		y = y / f;
		z = z / f;
		x = x + this.rand.nextGaussian() * 0.0075D * inaccuracy;
		y = y + this.rand.nextGaussian() * 0.0075D * inaccuracy;
		z = z + this.rand.nextGaussian() * 0.0075D * inaccuracy;
		x = x * velocity;
		y = y * velocity;
		z = z * velocity;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f1 = MathHelper.sqrt_double(x * x + z * z);
		this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
		this.rotationPitch = (float) (MathHelper.atan2(y, f1) * (180D / Math.PI));
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;
		this.ticksInGround = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch,
			int posRotationIncrements, boolean teleport) {
		this.setPosition(x, y, z);
		this.setRotation(yaw, pitch);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z) {
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(x * x + z * z);
			this.rotationPitch = (float) (MathHelper.atan2(y, f) * (180D / Math.PI));
			this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
			this.rotationPitch = (float) (MathHelper.atan2(this.motionY, f) * (180D / Math.PI));
			this.prevRotationYaw = this.rotationYaw;
			this.prevRotationPitch = this.rotationPitch;
		}

		BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
		IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
		Block block = iblockstate.getBlock();

		if (iblockstate.getMaterial() != Material.AIR && !this.getIsGhost()) {
			AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.worldObj, blockpos);

			if (axisalignedbb != Block.NULL_AABB
					&& axisalignedbb.offset(blockpos).isVecInside(new Vec3d(this.posX, this.posY, this.posZ))) {
				this.inGround = true;
			}
		}

		if (this.inGround && !this.getIsGhost()) {
			int j = block.getMetaFromState(iblockstate);

			if (block == this.inTile && j == this.inData) {
				++this.ticksInGround;

				if (this.ticksInGround >= 2) {
					this.setDead();
				}
			} else {
				this.inGround = false;
				this.motionX *= this.rand.nextFloat() * 0.2F;
				this.motionY *= this.rand.nextFloat() * 0.2F;
				this.motionZ *= this.rand.nextFloat() * 0.2F;
				this.ticksInGround = 0;
			}

			++this.timeInGround;
		} else {
			this.timeInGround = 0;
			++this.ticksInAir;
			if (ticksInAir > this.getLivingLimit()) {
				this.setDead();
			}

			Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
			Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			RayTraceResult raytraceresult = this.worldObj.rayTraceBlocks(vec3d1, vec3d, false, true, false);
			vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
			vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (raytraceresult != null) {
				vec3d = new Vec3d(raytraceresult.hitVec.xCoord, raytraceresult.hitVec.yCoord,
						raytraceresult.hitVec.zCoord);
			}

			List<Entity> list = this.findEntityOnPath(vec3d1, vec3d);
			if (!list.isEmpty()) {
				for (Entity entity : list) {
					if (entity != null) {
						raytraceresult = new RayTraceResult(entity);
					}

					if (raytraceresult != null && raytraceresult.entityHit != null
							&& raytraceresult.entityHit instanceof EntityPlayer) {
						EntityPlayer entityplayer = (EntityPlayer) raytraceresult.entityHit;

						if (this.shootingEntity instanceof EntityPlayer
								&& !((EntityPlayer) this.shootingEntity).canAttackPlayer(entityplayer)) {
							raytraceresult = null;
						}
					}

					if (raytraceresult != null) {
						this.onHit(raytraceresult);
					}
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			float f4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
			this.rotationPitch = (float) (MathHelper.atan2(this.motionY, f4) * (180D / Math.PI));

			while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
				this.prevRotationPitch -= 360.0F;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float f1 = 0.99F;
			float f2 = 0.05F;

			if (this.isInWater()) {
				for (int i = 0; i < 4; ++i) {
					float f3 = 0.25F;
					this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D,
							this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX,
							this.motionY, this.motionZ, new int[0]);
				}

				f1 = 0.6F;
			}

			if (this.isWet()) {
				this.extinguish();
			}

			this.motionX *= f1;
			this.motionY *= f1;
			this.motionZ *= f1;

			if (!this.hasNoGravity()) {
				this.motionY -= getGravity();
			}

			this.setPosition(this.posX, this.posY, this.posZ);
			this.doBlockCollisions();
		}
	}

	/**
	 * Called when the arrow hits a block or an entity
	 */
	protected void onHit(RayTraceResult raytraceResultIn) {
		Entity entity = raytraceResultIn.entityHit;

		if (entity != null) {
			int dam = MathHelper.ceiling_double_int(this.damage);
			dam += this.rand.nextInt(dam / 2 + 2);

			// 対アンデッドで2倍
			if (this.getIsSilver() && entity instanceof EntityLiving && ((EntityLiving) entity).isEntityUndead()) {
				dam *= 2;
			}

			DamageSource damagesource;

			if (this.isBurning()) {
				damagesource = DamageSource.lava;
			} else if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
				if (this.getIsSilver() && entity instanceof EntityEnderman) {
					if (this.shootingEntity instanceof EntityPlayer) {
						damagesource = DamageSource.causePlayerDamage((EntityPlayer) this.shootingEntity); // player
					} else {
						damagesource = DamageSource.causeMobDamage((EntityLivingBase) this.shootingEntity); // mob
					}
					dam *= 2;
				} else {
					damagesource = DamageSource.causeIndirectDamage(this, (EntityLivingBase) this.shootingEntity);
				}
			} else {
				damagesource = DamageSource.causeIndirectMagicDamage(this, null);
			}

			if (getBulletType() == BulletType.BOLT) {
				damagesource = damagesource.setProjectile();
			}

			if (entity.attackEntityFrom(damagesource, dam)) {
				if (entity instanceof EntityLivingBase) {
					EntityLivingBase living = (EntityLivingBase) entity;
					living.hurtResistantTime = 0;

					// ノックバック
					if (this.knockbackStrength > 0) {
						float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

						if (f1 > 0.0F) {
							living.addVelocity(this.motionX * this.knockbackStrength * 0.6D / f1, 0.1D,
									this.motionZ * this.knockbackStrength * 0.6D / f1);
						}
					}

					if (this.shootingEntity instanceof EntityLivingBase) {
						EnchantmentHelper.applyThornEnchantments(living, this.shootingEntity);
						EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase) this.shootingEntity, living);
					}

					this.arrowHit(living);

					if (this.shootingEntity != null && living != this.shootingEntity && living instanceof EntityPlayer
							&& this.shootingEntity instanceof EntityPlayerMP) {
						((EntityPlayerMP) this.shootingEntity).connection
								.sendPacket(new SPacketChangeGameState(6, 0.0F));
					}
				}

				this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

				if (!this.getIsPenetrate()) {
					this.setDead();
				}
			}
		} else if (!this.getIsGhost()) {
			BlockPos blockpos = raytraceResultIn.getBlockPos();
			this.xTile = blockpos.getX();
			this.yTile = blockpos.getY();
			this.zTile = blockpos.getZ();
			IBlockState state = this.worldObj.getBlockState(blockpos);
			this.inTile = state.getBlock();
			this.inData = this.inTile.getMetaFromState(state);
			this.motionX = ((float) (raytraceResultIn.hitVec.xCoord - this.posX));
			this.motionY = ((float) (raytraceResultIn.hitVec.yCoord - this.posY));
			this.motionZ = ((float) (raytraceResultIn.hitVec.zCoord - this.posZ));
			float f2 = MathHelper.sqrt_double(
					this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			this.posX -= this.motionX / f2 * 0.05D;
			this.posY -= this.motionY / f2 * 0.05D;
			this.posZ -= this.motionZ / f2 * 0.05D;
			this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
			this.inGround = true;

			if (state.getMaterial() != Material.AIR) {
				this.inTile.onEntityCollidedWithBlock(this.worldObj, blockpos, state, this);
			}
		}
	}

	protected void arrowHit(EntityLivingBase living) {}

	@Nullable
	protected List<Entity> findEntityOnPath(Vec3d start, Vec3d end) {
		Entity entity = null;
		List<Entity> ret = Lists.newArrayList();
		List<Entity> list = this.worldObj.getEntitiesInAABBexcluding(this,
				this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expandXyz(1.0D),
				ARROW_TARGETS);
		if (list.contains(shootingEntity)) {
			list.remove(shootingEntity);
		}
		double d0 = 0.0D;
		if (this.getIsRangedAttack()) {
			ret.addAll(list);
			return ret;
		}

		for (int i = 0; i < list.size(); ++i) {
			Entity entity1 = list.get(i);

			if (entity1 != this.shootingEntity || this.ticksInAir >= 0) {
				AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expandXyz(0.3D);
				RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

				if (raytraceresult != null) {
					double d1 = start.squareDistanceTo(raytraceresult.hitVec);

					if (d1 < d0 || d0 == 0.0D) {
						ret.add(entity1);
						return ret;
					}
				}
			}
		}

		return ret;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setInteger("xTile", this.xTile);
		compound.setInteger("yTile", this.yTile);
		compound.setInteger("zTile", this.zTile);
		compound.setShort("life", (short) this.ticksInGround);
		ResourceLocation resourcelocation = Block.REGISTRY.getNameForObject(this.inTile);
		compound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
		compound.setByte("inData", (byte) this.inData);
		compound.setByte("shake", (byte) this.arrowShake);
		compound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		compound.setDouble("damage", this.damage);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		this.xTile = compound.getInteger("xTile");
		this.yTile = compound.getInteger("yTile");
		this.zTile = compound.getInteger("zTile");
		this.ticksInGround = compound.getShort("life");

		if (compound.hasKey("inTile", 8)) {
			this.inTile = Block.getBlockFromName(compound.getString("inTile"));
		} else {
			this.inTile = Block.getBlockById(compound.getByte("inTile") & 255);
		}

		this.inData = compound.getByte("inData") & 255;
		this.arrowShake = compound.getByte("shake") & 255;
		this.inGround = compound.getByte("inGround") == 1;

		if (compound.hasKey("damage", 99)) {
			this.damage = compound.getDouble("damage");
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float partialTicks) {
		return 15728880;
	}

	public void setDamage(double damageIn) {
		this.damage = damageIn;
	}

	public double getDamage() {
		return this.damage;
	}

	public void setKnockbackStrength(int knockbackStrengthIn) {
		this.knockbackStrength = knockbackStrengthIn;
	}

	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}

	@Override
	public float getEyeHeight() {
		return 0.0F;
	}

	// Override用
	public double getLivingLimit() {
		return 20;
	}

	public double getGravity() {
		return 0.01D;
	}

	public boolean getIsPenetrate() {
		return false;
	}

	public boolean getIsGhost() {
		return false;
	}

	public boolean getIsRangedAttack() {
		return false;
	}

	public boolean getIsSilver() {
		return false;
	}

	public BulletType getBulletType() {
		return BulletType.BULLET;
	}

	public enum BulletType {
		BOLT,
		BULLET,
		SHOTGUN;
	}
}
