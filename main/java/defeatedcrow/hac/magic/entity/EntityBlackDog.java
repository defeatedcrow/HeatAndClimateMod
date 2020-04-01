package defeatedcrow.hac.magic.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.PictureList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class EntityBlackDog extends EntityWolf {

	private BlockPos picturePos = null;

	public EntityBlackDog(World world) {
		super(world);
		this.setSize(0.6F, 0.85F);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityBlackDog.AIAvoidEntity(this, EntityGolem.class, 24.0F, 1.5D, 0.5D));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.5D, true));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(2, new EntityAIPlayerHurtByTarget(this));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMob.class, 50, false, true,
				(Predicate) null));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WOLF_GROWL;
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

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return null;
	}

	public static void registerFixesWolf(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, EntityBlackDog.class);
	}

	@Override
	public float getEyeHeight() {
		return this.height * 0.8F;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		return false;
	}

	@Override
	public boolean canMateWith(EntityAnimal otherAnimal) {
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!this.world.isRemote && this.world.isDaytime()) {
			this.setDead();
		}
	}

	// ものひろい
	@Override
	public void onLivingUpdate() {
		if (!world.isRemote) {
			if (this.picturePos == null) {
				int cx = getPosition().getX() >> 4;
				int cz = getPosition().getZ() >> 4;
				ChunkPos chunk = new ChunkPos(cx, cz);
				BlockPos pos = PictureList.INSTANCE.getPos(chunk, MagicColor.BLACK);
				if (pos != null) {
					setPicturePos(new BlockPos(pos));
				}
			} else {
				IItemHandler chest = null;
				int y = 1;
				while (chest == null && y < 10) {
					TileEntity tile = world.getTileEntity(picturePos.down(y));
					if (tile != null && tile
							.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP)) {
						chest = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
					}
					y++;
				}

				if (chest != null) {
					AxisAlignedBB bb = this.getEntityBoundingBox().grow(1.0D, 0.5D, 1.0D);
					List<EntityItem> list = this.world.getEntitiesWithinAABB(EntityItem.class, bb);

					for (int i = 0; i < list.size(); ++i) {
						EntityItem drop = list.get(i);
						if (!drop.isDead) {
							if (!DCUtil.isEmpty(drop.getItem())) {
								ItemStack ins = drop.getItem().copy();

								if (this.getHealth() < this.getMaxHealth() && ins
										.getItem() instanceof ItemFood && ((ItemFood) ins.getItem())
												.isWolfsFavoriteMeat()) {
									ins.splitStack(1);
									this.heal(16F);
									if (ins.getCount() < 1) {
										drop.setDead();
									}
								}

								for (int j = 0; j < chest.getSlots(); j++) {
									ItemStack rem = chest.insertItem(j, ins, true);
									if (rem.getCount() < ins.getCount()) {
										chest.insertItem(j, ins, false);
										if (!DCUtil.isEmpty(rem) && rem.getCount() > 0) {
											drop.getItem().setCount(rem.getCount());
										} else {
											drop.getItem().setCount(0);
											drop.setDead();
										}
									}
									if (drop.getItem().isEmpty())
										break;
								}
							}
						}
					}
				}
			}
		}
		super.onLivingUpdate();
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

	// ドロップアイテム制御
	public void setPicturePos(BlockPos pos) {
		picturePos = pos;
	}

	public BlockPos getPicturePos() {
		return picturePos;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		if (this.picturePos != null) {
			compound.setInteger("p_posx", this.picturePos.getX());
			compound.setInteger("p_posy", this.picturePos.getY());
			compound.setInteger("p_posz", this.picturePos.getZ());
		}
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		if (compound.hasKey("p_posx")) {
			int x = compound.getInteger("p_posx");
			int y = compound.getInteger("p_posy");
			int z = compound.getInteger("p_posz");
			this.picturePos = new BlockPos(x, y, z);
		}
	}

	/* AI */

	class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T> {
		private final EntityBlackDog wolf;

		public AIAvoidEntity(EntityBlackDog wolfIn, Class<T> classIn, float dist, double far, double near) {
			super(wolfIn, classIn, dist, far, near);
			this.wolf = wolfIn;
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean shouldExecute() {
			if (super.shouldExecute() && this.closestLivingEntity instanceof EntityGolem) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		@Override
		public void startExecuting() {
			EntityBlackDog.this.setAttackTarget((EntityLivingBase) null);
			super.startExecuting();
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		@Override
		public void updateTask() {
			EntityBlackDog.this.setAttackTarget((EntityLivingBase) null);
			super.updateTask();
		}
	}

	public class EntityAIPlayerHurtByTarget extends EntityAITarget {
		EntityBlackDog dog;
		EntityLivingBase attacker;
		EntityPlayer nearest;
		private int timestamp;

		public EntityAIPlayerHurtByTarget(EntityBlackDog theDefendingIn) {
			super(theDefendingIn, false);
			this.dog = theDefendingIn;
			this.setMutexBits(1);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
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

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		@Override
		public void startExecuting() {
			this.taskOwner.setAttackTarget(this.attacker);

			if (nearest != null && !nearest.isEntityAlive()) {
				this.timestamp = nearest.getRevengeTimer();
			}
			super.startExecuting();
		}
	}

}
