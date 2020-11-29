package defeatedcrow.hac.magic.entity;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Optional;

import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.magic.proj.EntityFlowerBolt;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityFlowerTurret extends DCEntityBase {

	protected static final DataParameter<Boolean> TAMED = EntityDataManager
			.<Boolean>createKey(EntityFlowerTurret.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager
			.<Optional<UUID>>createKey(EntityFlowerTurret.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public int maxLivingTime = 30;
	public int livingDay = 0;
	public int lastDay = 0;

	public EntityLivingBase targetEntity = null;
	public EntityLivingBase targetClose = null;

	public EntityFlowerTurret(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 1.0F);
	}

	@Override
	public double getMountedYOffset() {
		return 1.0D;
	}

	public EntityFlowerTurret(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public EntityFlowerTurret(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	public void setMaxLivingTime(int i) {
		maxLivingTime = i;
	}

	@Override
	protected ItemStack drops() {
		return ItemStack.EMPTY;
	}

	private int coolTime = 12;

	private int count = 0;

	@Override
	public void onUpdate() {
		super.onUpdate();
		// this.prevRotationPitch = this.rotationPitch;
		// this.prevRotationYaw = this.rotationYaw;

		if (!world.isRemote) {
			if (coolTime <= 0) {
				if (keepMeleeAttack()) {
					count = 0;
					onMeleeAttack();
					coolTime = 4;
				} else if (keepRangedAttack()) {
					onRangedAttack();
					count++;
					if (count > 2) {
						count = 0;
						coolTime = 20;
					} else {
						coolTime = 2;
					}
				} else {
					count = 0;
					if (updateTarget()) {
						coolTime = 20;
					} else {
						coolTime = 80;
					}
				}
			} else {
				coolTime--;
			}

			// 頭をまわす
			EntityLivingBase owner = getOwner();
			EntityLivingBase look = null;

			if (targetClose != null) {
				look = targetClose;
			} else if (targetEntity != null) {
				look = targetEntity;
			} else if (owner != null) {
				look = owner;
			}

			if (look != null) {

				double lookX = look.posX;
				double lookY = look.posY + look.getEyeHeight();
				double lookZ = look.posZ;

				double d0 = lookX - this.posX;
				double d1 = lookY - (this.posY + 1.0D);
				double d2 = lookZ - this.posZ;
				double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
				float f = (float) (MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
				float f1 = (float) (-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
				this.rotationPitch = this.updateRotation(this.rotationPitch, f1, 40F);
				this.rotationYaw = this.updateRotation(this.rotationYaw, f, 10F);

			}

			if (this.livingDay > maxLivingTime) {
				this.setDead();
			} else {
				int day = (int) (DCTimeHelper.totalTime(world) / 24000L);
				if (day > lastDay) {
					livingDay++;
					lastDay = day;
				}
			}

			// 落下制御
			this.motionY -= 0.04D;
			this.handleWaterMovement();

			// 水中
			if (this.inWater) {
				this.motionY += 0.08D;
				if (this.motionY > 0.15D) {
					this.motionY = 0.15D;
				}
			} else {
				if (this.onGround) {
					this.motionY *= 0.5D;
				} else {
					this.motionY *= 0.95D;
				}
			}

		}

		this.motionX = 0D;
		this.motionZ = 0D;

	}

	private boolean canAttackPlayer(@Nonnull EntityLivingBase target) {
		if (getOwner() instanceof EntityPlayer && target instanceof EntityPlayer) {
			return ((EntityPlayer) getOwner()).canAttackPlayer((EntityPlayer) target);
		}
		return true;
	}

	private boolean keepMeleeAttack() {
		if (targetClose != null && targetClose.isEntityAlive() && canAttackPlayer(targetClose)) {
			if (!this.getPassengers().isEmpty() && this.getPassengers().size() > 0) {
				if (this.getPassengers().get(0) == targetClose) {
					return true;
				} else {
					this.removePassengers();
				}
			} else if (targetClose != null && !this.isBeingRidden()) {
				if (targetClose.isRiding()) {
					targetClose.dismountRidingEntity();
				}
				targetClose.startRiding(this);
			}
			return true;
		}
		return false;
	}

	private boolean onMeleeAttack() {
		return targetClose.attackEntityFrom(DamageSource.CACTUS, 20.0F);
	}

	private boolean keepRangedAttack() {
		return targetClose == null && targetEntity != null && targetEntity.isEntityAlive() && targetEntity
				.canEntityBeSeen(this);
	}

	private boolean onRangedAttack() {
		EntityFlowerBolt entityarrow = new EntityFlowerBolt(world, this);
		entityarrow.setAim(this, rotationPitch, rotationYaw, 0.0F, 5.0F, 0.1F);
		this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.75F, 1.5F);
		return world.spawnEntity(entityarrow);
	}

	private boolean updateTarget() {
		// Target更新

		updateCloseTarget();
		updateRangedTarget();

		return targetClose != null || targetEntity != null;
	}

	private void updateRangedTarget() {
		if (targetEntity != null) {
			float dist = MathHelper.sqrt(targetEntity.getDistanceSq(this));
			// 続行不能判定
			if (targetEntity.isEntityAlive() && dist < 5F) {
				targetClose = targetEntity;
				targetEntity = null;
			} else if (!targetEntity.isEntityAlive() || dist > 36F) {
				targetEntity = null;
			}
		} else {
			// オーナーの敵が最優先
			EntityLivingBase owner = getOwner();

			if (owner != null) {
				if (owner.getLastAttackedEntity() != null && owner.getLastAttackedEntity() != targetClose && MathHelper
						.sqrt(owner.getLastAttackedEntity().getDistanceSq(this)) <= 36F) {
					targetEntity = owner.getLastAttackedEntity();
					return;
				} else if (owner.getRevengeTarget() != null && owner.getRevengeTarget() != targetClose && MathHelper
						.sqrt(owner.getRevengeTarget().getDistanceSq(this)) <= 36F) {
					targetEntity = owner.getRevengeTarget();
					return;
				}
			}

			AxisAlignedBB aabb = new AxisAlignedBB(posX - 32D, posY - 12D, posZ - 32D, posX + 32D, posY + 12D,
					posZ + 32D);
			List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
			float d0 = 36F;
			for (int i = 0; i < list.size(); i++) {
				EntityLivingBase mob = list.get(i);
				if (mob == null)
					continue;
				float d1 = MathHelper.sqrt(mob.getDistanceSq(this));
				if (owner != null && mob.getRevengeTarget() == owner && d1 < 36F && mob != targetClose) {
					targetEntity = mob;
					return;
				}
				if (mob instanceof IMob && mob != targetClose) {
					if (d1 > 5F && d1 < d0 && mob.canEntityBeSeen(this)) {
						targetEntity = mob;
						d0 = d1;
					}
				}
			}
		}
	}

	private void updateCloseTarget() {
		if (targetClose != null) {
			float dist = MathHelper.sqrt(targetClose.getDistanceSq(this));
			// 続行不能判定
			if (targetClose.isEntityAlive() && dist >= 5F && dist <= 36F) {
				targetEntity = targetClose;
				targetClose = null;
			} else if (!targetClose.isEntityAlive() || dist > 36F) {
				targetClose = null;
			}
		} else {
			// 既に着座しているとき
			if (!this.getPassengers().isEmpty() && this.getPassengers().size() > 0 && this.getPassengers()
					.get(0) instanceof EntityLivingBase) {
				targetClose = (EntityLivingBase) this.getPassengers().get(0);
			}

			// オーナーの敵が最優先
			EntityLivingBase owner = getOwner();

			if (owner != null) {
				if (owner.getLastAttackedEntity() != null && MathHelper.sqrt(owner.getLastAttackedEntity()
						.getDistanceSq(this)) < 5F) {
					targetClose = owner.getLastAttackedEntity();
					return;
				} else if (owner.getRevengeTarget() != null && MathHelper.sqrt(owner.getRevengeTarget()
						.getDistanceSq(this)) < 5F) {
					targetClose = owner.getRevengeTarget();
					return;
				}
			}

			AxisAlignedBB aabb = new AxisAlignedBB(posX - 3D, posY - 2D, posZ - 3D, posX + 3D, posY + 2D, posZ + 3D);
			List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
			float d0 = 6F;
			for (int i = 0; i < list.size(); i++) {
				EntityLivingBase mob = list.get(i);
				if (mob == null)
					continue;
				float d1 = MathHelper.sqrt(mob.getDistanceSq(this));
				if (owner != null && mob.getRevengeTarget() == owner & d1 < 5F) {
					targetClose = mob;
					return;
				}
				if (mob instanceof IMob) {
					if (d1 < d0) {
						targetClose = mob;
						d0 = d1;
					}
				}
			}
		}
	}

	private float updateRotation(float f1, float f2, float f3) {
		float f = MathHelper.wrapDegrees(f2 - f1);

		if (f > f3) {
			f = f3;
		}

		if (f < -f3) {
			f = -f3;
		}

		return f1 + f;
	}

	// tame

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(TAMED, false);
		this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		livingDay = tag.getInteger("LivingDay");
		lastDay = tag.getInteger("LastDay");
		String s;
		if (tag.hasKey("OwnerUUID", 8)) {
			s = tag.getString("OwnerUUID");
		} else {
			String s1 = tag.getString("Owner");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty()) {
			try {
				this.setOwnerId(UUID.fromString(s));
				this.setTamed(true);
			} catch (Throwable var4) {
				this.setTamed(false);
			}
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setInteger("LivingDay", livingDay);
		tag.setInteger("LastDay", lastDay);
		if (this.getOwnerId() == null) {
			tag.setString("OwnerUUID", "");
		} else {
			tag.setString("OwnerUUID", this.getOwnerId().toString());
		}
	}

	public boolean isTamed() {
		return this.dataManager.get(TAMED).booleanValue();
	}

	public void setTamed(boolean tamed) {
		boolean b0 = this.dataManager.get(TAMED).booleanValue();

		if (tamed != b0) {
			this.dataManager.set(TAMED, tamed);
		}
	}

	@Nullable
	public UUID getOwnerId() {
		return (UUID) ((Optional) this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
	}

	public void setOwnerId(@Nullable UUID p_184754_1_) {
		this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(p_184754_1_));
	}

	public void setTamedBy(EntityPlayer player) {
		this.setTamed(true);
		this.setOwnerId(player.getUniqueID());
	}

	@Nullable
	public EntityLivingBase getOwner() {
		try {
			UUID uuid = this.getOwnerId();
			return uuid == null ? null : this.world.getPlayerEntityByUUID(uuid);
		} catch (IllegalArgumentException var2) {
			return null;
		}
	}

	public boolean isOwner(EntityLivingBase entityIn) {
		return entityIn == this.getOwner();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
	}

	@Override
	public boolean isCollectable(@Nullable ItemStack item) {
		return false;
	}

	@Override
	protected boolean isFallable() {
		return false;
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		if (world.isRemote) {
			return true;
		}
		EntityLivingBase owner = getOwner();
		if (owner != null && player == owner) {
			this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			if (world instanceof WorldServer) {
				((WorldServer) world)
						.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, posX, posY, posZ, 8, 0.5D, 0.5D, 0.5D, 0.5D, new int[0]);
			}
			deadPos = this.getPosition();
			return true;
		}
		return false;
	}

}
