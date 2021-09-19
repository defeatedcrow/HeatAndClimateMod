package defeatedcrow.hac.magic.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.proj.EntityProjDogSpit;
import defeatedcrow.hac.main.util.EntitySelectorsDC;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFTDog extends EntityWolf implements IRangedAttackMob {

	public EntityFTDog(World world) {
		super(world);
		this.setSize(0.6F, 0.85F);
		this.isImmuneToFire = true;
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityFTDog.AIAvoidEntity(this, EntityGolem.class, 24.0F, 1.5D, 0.5D));
		this.tasks.addTask(3, new EntityAIAttackRanged(this, 0.5D, 10, 20.0F));
		this.tasks.addTask(4, new EntityAIFollowOwner(this, 2.0D, 10.0F, 2.0F));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityMob.class, 20, true, false,
				EntitySelectorsDC.NOT_NAMED));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WOLF_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_WOLF_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_WOLF_DEATH;
	}

	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return null;
	}

	public static void registerFixesWolf(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, EntityFTDog.class);
	}

	@Override
	public float getEyeHeight() {
		return this.height * 0.8F;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (this.isTamed()) {
			if (!itemstack.isEmpty()) {
				if (itemstack.getItem() instanceof ItemFood) {
					ItemFood itemfood = (ItemFood) itemstack.getItem();

					if (itemfood.isWolfsFavoriteMeat()) {
						if (!player.capabilities.isCreativeMode) {
							itemstack.shrink(1);
						}

						this.heal(itemfood.getHealAmount(itemstack));
						return true;
					}
				}
			}

		}
		return false;
	}

	@Override
	public boolean canMateWith(EntityAnimal otherAnimal) {
		return false;
	}

	@Override
	public void onLivingUpdate() {
		if (!world.isRemote) {
			/*
			 * ものひろいによる回復
			 */
			AxisAlignedBB bb = this.getEntityBoundingBox().grow(1.0D, 0.5D, 1.0D);
			List<EntityItem> list = this.world.getEntitiesWithinAABB(EntityItem.class, bb);
			for (int i = 0; i < list.size(); ++i) {
				EntityItem drop = list.get(i);
				if (!drop.isDead) {
					if (!DCUtil.isEmpty(drop.getItem())) {
						ItemStack ins = drop.getItem().copy();

						if (this.getHealth() < this.getMaxHealth() && ins
								.getItem() instanceof ItemFood && ((ItemFood) ins.getItem()).isWolfsFavoriteMeat()) {
							ins.splitStack(1);
							this.heal(8F);
							if (ins.getCount() < 1 || ins.isEmpty()) {
								drop.setDead();
							}
						}
					}
				}
			}
			/*
			 * 時間経過で消滅
			 */
			if (this.getGrowingAge() < 1) {
				world.playEvent(2004, this.getPosition(), 0);
				this.setDead();
			}
		}
		super.onLivingUpdate();
	}

	@Override
	public void onDeath(DamageSource cause) {
		// 死亡時に持ち物をドロップ
		if (!world.isRemote) {
			for (ItemStack item : this.getEquipmentAndArmor()) {
				if (!DCUtil.isEmpty(item)) {
					if (!item.isEmpty()) {
						EntityItem drop = new EntityItem(world, getPosition().getX(), getPosition().getY() + 0.125D,
								getPosition().getZ(), item.copy());
						world.spawnEntity(drop);
					}
				}
			}
		}
		super.onDeath(cause);
	}

	// クリーパー許すまじ
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (entityIn instanceof EntityCreeper) {
			boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), ((EntityCreeper) entityIn)
					.getMaxHealth() * 2F);
			return flag;
		}
		return super.attackEntityAsMob(entityIn);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else {
			Entity entity = source.getTrueSource();
			if (entity != null && entity instanceof EntityZombie) {
				amount /= 2.0F;
			}
			return super.attackEntityFrom(source, amount);
		}
	}

	/* AI */

	class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T> {
		private final EntityFTDog wolf;

		public AIAvoidEntity(EntityFTDog wolfIn, Class<T> classIn, float dist, double far, double near) {
			super(wolfIn, classIn, dist, far, near);
			this.wolf = wolfIn;
		}

		@Override
		public boolean shouldExecute() {
			if (super.shouldExecute() && this.closestLivingEntity instanceof EntityGolem) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void startExecuting() {
			EntityFTDog.this.setAttackTarget((EntityLivingBase) null);
			super.startExecuting();
		}

		@Override
		public void updateTask() {
			EntityFTDog.this.setAttackTarget((EntityLivingBase) null);
			super.updateTask();
		}
	}

	public class EntityAIPlayerHurtByTarget extends EntityAITarget {
		EntityFTDog dog;
		EntityLivingBase attacker;
		EntityPlayer nearest;
		private int timestamp;

		public EntityAIPlayerHurtByTarget(EntityFTDog theDefendingIn) {
			super(theDefendingIn, false);
			this.dog = theDefendingIn;
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			nearest = this.dog.world.getClosestPlayer(posX, posY, posZ, 16D, false);
			if (nearest == null || !nearest.isEntityAlive()) {
				return false;
			} else {
				this.attacker = nearest.getRevengeTarget();
				int i = nearest.getRevengeTimer();
				return i != this.timestamp && this.isSuitableTarget(this.attacker, false);
			}
		}

		@Override
		public void startExecuting() {
			this.taskOwner.setAttackTarget(this.attacker);

			if (nearest != null && !nearest.isEntityAlive()) {
				this.timestamp = nearest.getRevengeTimer();
			}
			super.startExecuting();
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		if (target != null) {
			double dist = target.getDistanceSq(this);
			if (dist < 2.0D) {
				this.attackEntityAsMob(target);
			} else {
				EntityProjDogSpit spit = new EntityProjDogSpit(this.world, this);
				float dam = ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
				double d0 = target.posX - this.posX;
				double d1 = target.getEntityBoundingBox().minY + target.height / 3.0F - this.posY;
				double d2 = target.posZ - this.posZ;
				float f = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
				spit.shoot(d0, d1, d2, 2.0F, 1.0F);
				spit.setExplodeRange(dam);
				this.world
						.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_FIREWORK_BLAST, this
								.getSoundCategory(), 1.0F, 2.0F + (this.rand.nextFloat() - this.rand
										.nextFloat()) * 0.2F);
				this.world.spawnEntity(spit);
			}
		}
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}

}
