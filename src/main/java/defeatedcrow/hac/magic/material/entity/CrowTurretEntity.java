package defeatedcrow.hac.magic.material.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.core.util.CustomExplosion;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Rotations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec2;

public class CrowTurretEntity extends LivingEntity {

	private static final Rotations DEFAULT_BODY_POSE = new Rotations(0.0F, 0.0F, 0.0F);
	public static final EntityDataAccessor<Rotations> DATA_BODY_POSE = SynchedEntityData.defineId(CrowTurretEntity.class, EntityDataSerializers.ROTATIONS);
	public static final EntityDataAccessor<Float> EXPLOSION_POWER = SynchedEntityData.defineId(CrowTurretEntity.class, EntityDataSerializers.FLOAT);

	private final NonNullList<ItemStack> handItems = NonNullList.withSize(2, ItemStack.EMPTY);
	private final NonNullList<ItemStack> armorItems = NonNullList.withSize(4, ItemStack.EMPTY);

	private boolean invisible;
	public long lastHit;
	private Rotations bodyPose = DEFAULT_BODY_POSE;

	public CrowTurretEntity(EntityType<? extends LivingEntity> type, Level level) {
		super(type, level);
		// this.setNoGravity(true);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_BODY_POSE, DEFAULT_BODY_POSE);
		this.entityData.define(EXPLOSION_POWER, 4.0F);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return LivingEntity.createLivingAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
	}

	@Override
	public boolean isEffectiveAi() {
		return false;
	}

	@Override
	public boolean canTakeItem(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void doPush(Entity entity) {}

	@Override
	public ItemStack getItemBySlot(EquipmentSlot slot) {
		switch (slot.getType()) {
		case HAND:
			return this.handItems.get(slot.getIndex());
		case ARMOR:
			return this.armorItems.get(slot.getIndex());
		default:
			return ItemStack.EMPTY;
		}
	}

	@Override
	public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
		this.verifyEquippedItem(stack);
		switch (slot.getType()) {
		case HAND:
			this.onEquipItem(slot, this.handItems.set(slot.getIndex(), stack), stack);
			break;
		case ARMOR:
			this.onEquipItem(slot, this.armorItems.set(slot.getIndex(), stack), stack);
		}

	}

	@Override
	public Iterable<ItemStack> getHandSlots() {
		return this.handItems;
	}

	@Override
	public Iterable<ItemStack> getArmorSlots() {
		return this.armorItems;
	}

	@Override
	public HumanoidArm getMainArm() {
		return HumanoidArm.RIGHT;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		ListTag listtag = new ListTag();
		for (ItemStack itemstack : this.armorItems) {
			CompoundTag armTag = new CompoundTag();
			if (!itemstack.isEmpty()) {
				itemstack.save(armTag);
			}
			listtag.add(armTag);
		}
		tag.put("ArmorItems", listtag);

		ListTag listtag1 = new ListTag();
		for (ItemStack itemstack1 : this.handItems) {
			CompoundTag heldTag = new CompoundTag();
			if (!itemstack1.isEmpty()) {
				itemstack1.save(heldTag);
			}
			listtag1.add(heldTag);
		}
		tag.put("HandItems", listtag1);

		tag.putBoolean("Invisible", this.isInvisible());

		tag.put("Pose", this.writePose());

		tag.putFloat("Explosion", getRange());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("ArmorItems", 9)) {
			ListTag listtag = tag.getList("ArmorItems", 10);
			for (int i = 0; i < this.armorItems.size(); ++i) {
				this.armorItems.set(i, ItemStack.of(listtag.getCompound(i)));
			}
		}

		if (tag.contains("HandItems", 9)) {
			ListTag listtag1 = tag.getList("HandItems", 10);
			for (int j = 0; j < this.handItems.size(); ++j) {
				this.handItems.set(j, ItemStack.of(listtag1.getCompound(j)));
			}
		}

		this.setInvisible(tag.getBoolean("Invisible"));

		CompoundTag poseTag = tag.getCompound("Pose");
		this.readPose(poseTag);

		if (tag.contains("Explosion")) {
			setRange(tag.getFloat("Explosion"));
		}
	}

	private void readPose(CompoundTag tag) {
		ListTag listtag1 = tag.getList("Body", 5);
		this.setBodyPose(listtag1.isEmpty() ? DEFAULT_BODY_POSE : new Rotations(listtag1));
	}

	private CompoundTag writePose() {
		CompoundTag tag = new CompoundTag();

		if (!DEFAULT_BODY_POSE.equals(this.bodyPose)) {
			tag.put("Body", this.bodyPose.save());
		}
		return tag;
	}

	public void setBodyPose(Rotations rot) {
		this.bodyPose = rot;
		this.entityData.set(DATA_BODY_POSE, rot);
	}

	public Rotations getBodyPose() {
		return this.bodyPose;
	}

	public void setRange(float r) {
		this.entityData.set(EXPLOSION_POWER, Float.valueOf(r));
	}

	public float getRange() {
		return this.entityData.get(EXPLOSION_POWER);
	}

	@Override
	public LivingEntity.Fallsounds getFallSounds() {
		return new LivingEntity.Fallsounds(SoundEvents.IRON_GOLEM_STEP, SoundEvents.IRON_GOLEM_DAMAGE);
	}

	@Override
	@Nullable
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.IRON_GOLEM_DAMAGE;
	}

	@Override
	@Nullable
	protected SoundEvent getDeathSound() {
		return SoundEvents.IRON_GOLEM_DAMAGE;
	}

	@Override
	public boolean isAffectedByPotions() {
		return false;
	}

	@Override
	public boolean canBeSeenByAnyone() {
		return !this.isInvisible();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (!this.level.isClientSide && !this.isRemoved()) {
			if (DamageSource.OUT_OF_WORLD.equals(source)) {
				this.kill();
				return false;
			} else if (!this.isInvulnerableTo(source) && !this.invisible) {
				if (DamageSource.IN_FIRE.equals(source)) {
					if (this.isOnFire()) {
						if (causeDamage(source, 0.5F)) {
							this.onBroken(true);
						}
					} else {
						this.setSecondsOnFire(5);
					}
					return false;
				} else if (DamageSource.ON_FIRE.equals(source)) {
					if (causeDamage(source, 0.5F)) {
						this.onBroken(true);
					}
					return false;
				} else if (DamageSourceClimate.climateHeatDamage.equals(source) && amount > 3.0F) {
					if (causeDamage(source, 0.5F)) {
						this.onBroken(true);
					}
					return false;
				} else {
					Entity attacker = source.getEntity();
					if (attacker != null) {
						if (attacker instanceof LivingEntity && !source.isNoAggro()) {
							this.setLastHurtByMob((LivingEntity) attacker);
						}
						if (attacker instanceof Player && !source.isExplosion() && !source.isProjectile()) {
							this.onBroken(false);
						}
					}

					if (causeDamage(source, amount * 0.5F)) {
						this.level.broadcastEntityEvent(this, (byte) 32);
						this.onBroken(true);
					}
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean causeDamage(DamageSource source, float dam) {
		float f = this.getHealth();
		f -= dam;
		if (f <= 0.5F) {
			return true;
		} else {
			this.setHealth(f);
			this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
			return false;
		}

	}

	private void onBroken(boolean flag) {
		this.playBrokenSound();
		if (flag) {
			CustomExplosion explosion = new CustomExplosion(level, this, this.getX(), this.getY(), this.getZ(), 6.0F, true);
			explosion.explode();
			explosion.finalizeExplosion(true);
		}
		this.kill();
	}

	@Override
	public void kill() {
		this.remove(Entity.RemovalReason.KILLED);
		this.gameEvent(GameEvent.ENTITY_DIE);
	}

	private void playBrokenSound() {
		this.level.playSound((Player) null, this.getX(), this.getY(), this.getZ(), getDeathSound(), this.getSoundSource(), 1.0F, 1.0F);
	}

	@Override
	public void handleEntityEvent(byte b0) {
		if (b0 == 32) {
			if (this.level.isClientSide) {
				this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ARMOR_STAND_HIT, this.getSoundSource(), 0.3F, 1.0F, false);
				this.lastHit = this.level.getGameTime();
			}
		} else {
			super.handleEntityEvent(b0);
		}
	}

	@Override
	public PushReaction getPistonPushReaction() {
		return PushReaction.IGNORE;
	}

	private LivingEntity target = null;
	int count = 5;

	@Override
	public void tick() {
		super.tick();

		Rotations rotations1 = this.entityData.get(DATA_BODY_POSE);
		if (!this.bodyPose.equals(rotations1)) {
			this.setBodyPose(rotations1);
		}

		if (target != null) {
			// 見えなくなったら諦める
			if (!target.isAlive()) {
				target = null;
			} else if (this.distanceToSqr(target) > 9D && !this.hasLineOfSight(target)) {
				target = null;
			} else if (this.distanceToSqr(target) > 48D * 48D) {
				target = null;
			} else {
				// 首振り
				this.lookAt(target, 30F, 30F);
				Vec2 v1 = this.getRotationVector();
				Vec2 v2 = this.getLookVec(target);

				if (count <= 0 && v1.distanceToSqr(v2) < 16D) {
					fire();
					count = 20;
				} else if (count < -60) {
					// 3秒経ったらリセットする
					fire();
					target = null;
					count = 20;
				} else {
					count--;
				}
			}

		} else {
			if (count <= 0) {
				if (!level.isClientSide) {
					target = getTarget();
				}
				count = 5;
			} else {
				count--;
			}

		}

	}

	private void fire() {
		if (!level.isClientSide) {
			ArrowItem arrowitem = (ArrowItem) (MagicInit.ARROW_RED.get());
			ArrowRed red = (ArrowRed) arrowitem.createArrow(level, new ItemStack(arrowitem), this);
			red.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 3.0F, 1.0F);
			red.setCritArrow(true);
			red.setBaseDamage(red.getBaseDamage());
			red.setRange(getRange());
			red.setSafety();
			red.pickup = AbstractArrow.Pickup.DISALLOWED;
			level.addFreshEntity(red);
		}
	}

	private LivingEntity getTarget() {
		// 反撃
		if (getLastHurtByMob() != null) {
			if (!getLastHurtByMob().isAlive() || !(getLastHurtByMob() instanceof Enemy)) {
				this.setLastHurtByMob(null);
			} else {
				return getLastHurtByMob();
			}
		}
		if (getLastHurtMob() != null) {
			if (!getLastHurtMob().isAlive() || !(getLastHurtMob() instanceof Enemy)) {
				this.setLastHurtMob(null);
			} else {
				return getLastHurtMob();
			}
		}
		// 騎乗している
		if (this.getVehicle() instanceof LivingEntity living) {
			if (living.getLastHurtByMob() != null) {
				return living.getLastHurtByMob();
			} else if (living.getLastHurtMob() != null) {
				return living.getLastHurtMob();
			}
		}

		List<LivingEntity> list = level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat().range(48F), this, this.getBoundingBox().inflate(48F, 16F, 48F))
			.stream().filter(mob -> mob instanceof Enemy).toList();

		double dist = Double.MAX_VALUE;
		LivingEntity ret = null;
		for (LivingEntity liv : list) {
			if (liv.distanceTo(this) < dist) {
				dist = liv.distanceTo(this);
				ret = liv;
			}
		}
		return ret;

	}

	public Vec2 getLookVec(Entity entity) {
		// 射角調整
		double eX = entity.getForward().normalize().x;
		double eY = entity.getForward().normalize().y;
		double eZ = entity.getForward().normalize().z;
		double dist = Math.sqrt(this.distanceToSqr(entity)) * 0.1D;

		double d0 = entity.getX() + eX - this.getX();
		double d2 = entity.getZ() + eZ - this.getZ();
		double d1 = (entity.getBoundingBox().minY + entity.getBoundingBox().maxY) / 2.0D + eY + dist - this.getEyeY();

		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		float f = (float) (Mth.atan2(d2, d0) * (180F / (float) Math.PI)) - 90.0F;
		float f1 = (float) (-(Mth.atan2(d1, d3) * (180F / (float) Math.PI)));
		return new Vec2(f1, f);
	}

	public void lookAt(Entity entity, float float1, float float2) {
		Vec2 v2 = getLookVec(entity);
		this.setXRot(this.rotlerp(this.getXRot(), v2.x, float2));
		this.setYRot(this.rotlerp(this.getYRot(), v2.y, float1));
	}

	private float rotlerp(float float1, float float2, float limit) {
		float f = Mth.wrapDegrees(float2 - float1);
		if (f > limit) {
			f = limit;
		}
		if (f < -limit) {
			f = -limit;
		}
		if (Mth.abs(f) < 0.005F)
			f = 0F;
		return float1 + f;
	}

	private void resetLookVec() {
		this.setXRot(this.rotlerp(this.getXRot(), 0F, 45F));
		this.setYRot(this.rotlerp(this.getYRot(), bodyPose.getY(), 180F));
	}

}
