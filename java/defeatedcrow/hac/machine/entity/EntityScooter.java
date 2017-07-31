package defeatedcrow.hac.machine.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.machine.MachineInit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityScooter extends Entity {

	private static final DataParameter<Boolean> POWERED = EntityDataManager.<Boolean>createKey(EntityScooter.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> COLOR = EntityDataManager.<Integer>createKey(EntityScooter.class,
			DataSerializers.VARINT);

	public float headYaw;
	public float prevHeadYaw;

	private int lerpSteps;
	private double lastX;
	private double lastY;
	private double lastZ;
	private double lastYaw;
	private double lastRot;

	public boolean brake;

	private Status status = Status.IN_AIR;
	private Status prevStatus = Status.IN_AIR;

	/* コンストラクタ */

	public EntityScooter(World worldIn) {
		super(worldIn);
		this.preventEntitySpawning = true;
		this.setSize(1.5F, 1.0F);
		this.stepHeight = 1.25F;
	}

	public EntityScooter(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	public EntityScooter(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		this(worldIn, posX, posY, posZ);
		if (player != null) {
			this.rotationYaw = player.rotationYaw;
		}
	}

	@Override
	public double getMountedYOffset() {
		return 0.6D;
	}

	@Override
	public void onUpdate() {

		this.prevStatus = this.status;
		this.status = this.getStatus();

		prevHeadYaw = headYaw;
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		super.onUpdate();
		this.tickLerp();

		updatePowered();

		EntityPlayer rider = null;
		boolean stop = false;

		if (brake || !this.getPowered() || this.getPassengers().isEmpty()
				|| !(this.getPassengers().get(0) instanceof EntityPlayer)) {
			stop = true;
		} else if (!this.getPassengers().isEmpty() && this.getPassengers().get(0) instanceof EntityPlayer) {
			rider = (EntityPlayer) this.getPassengers().get(0);
		}

		// 向き決定
		updateYaw(rider);

		while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}

		while (this.headYaw - this.prevHeadYaw < -180.0F) {
			this.prevHeadYaw -= 360.0F;
		}

		while (this.headYaw - this.prevHeadYaw >= 180.0F) {
			this.prevHeadYaw += 360.0F;
		}

		// 移動処理

		// falling
		this.fallDistance = 0.0F;
		if (rider != null) {
			rider.fallDistance = 0.0F;
		}
		if (this.status == Status.IN_TILE) {
			this.motionY += 0.1D;
		} else if (status == Status.UNDER_WATER) {
			this.motionY -= 0.02D;
		} else {
			if (this.onGround) {
				this.motionY *= 0.5D;
			} else {
				this.motionY -= 0.05D;
			}
		}

		if (!worldObj.isRemote) {

			// 加速処理
			updateSpeed(rider, stop);

			// forward
			if (stop) {
				this.motionX *= 0.5D;
				this.motionZ *= 0.5D;
			} else {
				if (status == Status.UNDER_WATER) {
					this.motionX *= 0.75D;
					this.motionZ *= 0.75D;
				} else {
					this.motionX *= 0.98D;
					this.motionZ *= 0.98D;
				}
			}

			float speed = (float) Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			if (speed < 0.03D) {
				this.motionX = 0.0D;
				this.motionZ = 0.0D;
			}

		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		// collision
		this.doBlockCollisions();
		List<Entity> list = this.worldObj.getEntitiesInAABBexcluding(this,
				this.getEntityBoundingBox().expand(0.2D, -0.01D, 0.2D),
				EntitySelectors.<Entity>getTeamCollisionPredicate(this));

		if (!list.isEmpty()) {
			boolean flag = !this.worldObj.isRemote && !(this.getControllingPassenger() instanceof EntityPlayer);

			for (int j = 0; j < list.size(); ++j) {
				Entity entity = list.get(j);

				if (!entity.isPassenger(this)) {
					this.applyEntityCollision(entity);
				}
			}
		}

		if (this.rand.nextInt(4) == 0) {
			double px = posX - Math.sin(-rotationYaw * 0.017453292F) * 0.75D
					- Math.cos(rotationYaw * 0.017453292F) * 0.25D;
			double pz = posZ - Math.cos(rotationYaw * 0.017453292F) * 0.75D
					- Math.sin(rotationYaw * 0.017453292F) * 0.25D;
			this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, px, this.posY + 0.25D, pz, 0.0D, 0.0D, 0.0D,
					new int[0]);
		}
	}

	protected void updateSpeed(EntityPlayer rider, boolean brake) {
		float yaw = this.rotationYaw * 0.017453292F;

		if (rider != null && !brake && rider.moveForward > 0F) {
			double d1 = -Math.sin(yaw);
			double d2 = Math.cos(yaw);
			double d9 = this.motionX * this.motionX + this.motionZ * this.motionZ;

			if (d9 < getMaxSpeed()) {
				this.motionX += d1 * 0.5D;
				this.motionZ += d2 * 0.5D;
			}
			// DCLogger.debugLog("foward " + rider.moveForward + ", sp " + d9);
			rider.moveForward = 0F;
		} else {
			this.motionX *= 0.5D;
			this.motionZ *= 0.5D;
		}

	}

	protected void updateYaw(EntityPlayer rider) {
		if (rider == null)
			return;

		float f1 = this.rotationYaw;
		double d3 = MathHelper.wrapDegrees(rider.rotationYaw - (double) this.rotationYaw);
		this.rotationYaw = (float) (this.rotationYaw + d3 / 2.0D);

		if ((this.rotationYaw - f1) > 5F) {
			this.headYaw = 45F;
		} else if ((this.rotationYaw - f1) < -5F) {
			this.headYaw = -45F;
		} else {
			this.headYaw = 0F;
		}

	}

	// 現時点ではtrue
	protected boolean updatePowered() {
		boolean last = this.getPowered();
		if (!last) {
			this.setPowered(true);
		}
		return true;
	}

	private Status getStatus() {
		// AxisAlignedBB aabb = this.getEntityBoundingBox();
		// int ix = MathHelper.floor_double(posX);
		// int iy = MathHelper.floor_double(posY);
		// int iz = MathHelper.floor_double(posZ);
		// BlockPos p = new BlockPos(ix, iy, iz);
		// IBlockState state = worldObj.getBlockState(new BlockPos(ix, iy, iz));
		// if (state.getMaterial() == Material.WATER && state.getBoundingBox(worldObj, p) != null) {
		// double d = state.getBoundingBox(worldObj, p).maxY + p.getY();
		// if (posY < d)
		// return Status.UNDER_WATER;
		// } else if (state.getCollisionBoundingBox(worldObj, p) != null) {
		// double d = state.getCollisionBoundingBox(worldObj, p).maxY + p.getY();
		// if (posY < d)
		// return Status.IN_TILE;
		// }

		return getUnderGround();
	}

	private Status getUnderGround() {
		AxisAlignedBB aabb = this.getEntityBoundingBox();
		int i = MathHelper.floor_double(aabb.minX);
		int j = MathHelper.ceiling_double_int(aabb.maxX);
		int k = MathHelper.floor_double(aabb.minY);
		int l = MathHelper.ceiling_double_int(aabb.minY + 0.001D);
		int i1 = MathHelper.floor_double(aabb.minZ);
		int j1 = MathHelper.ceiling_double_int(aabb.maxZ);
		boolean flag = false;
		boolean flag2 = false;
		BlockPos.PooledMutableBlockPos mpos = BlockPos.PooledMutableBlockPos.retain();

		try {
			for (int k1 = i; k1 < j; ++k1) {
				for (int l1 = k; l1 < l; ++l1) {
					for (int i2 = i1; i2 < j1; ++i2) {
						mpos.setPos(k1, l1, i2);
						IBlockState state = this.worldObj.getBlockState(mpos);

						if (state.getMaterial() == Material.WATER && state.getBoundingBox(worldObj, mpos) != null) {
							double d = state.getBoundingBox(worldObj, mpos).maxY + mpos.getY();
							if (posY < d) {
								flag2 = true;
							}
						} else if (state.getCollisionBoundingBox(worldObj, mpos) != null) {
							double d = state.getCollisionBoundingBox(worldObj, mpos).maxY + mpos.getY();
							if (posY < d) {
								flag = true;
							}
						}
					}
				}
			}
		} finally {
			mpos.release();
		}

		return flag ? Status.IN_TILE : flag2 ? Status.UNDER_WATER : Status.IN_AIR;
	}

	// ガクガク対策
	private void tickLerp() {
		if (this.lerpSteps > 0 && !this.canPassengerSteer()) {
			double d0 = this.posX + (this.lastX - this.posX); // / this.lerpSteps;
			double d1 = this.posY + (this.lastY - this.posY); // / this.lerpSteps;
			double d2 = this.posZ + (this.lastZ - this.posZ); // / this.lerpSteps;
			double d3 = MathHelper.wrapDegrees(this.lastYaw - this.rotationYaw);
			this.rotationYaw = (float) (this.rotationYaw + d3);
			this.rotationPitch = (float) (this.rotationPitch + (this.lastRot - this.rotationPitch));
			// --this.lerpSteps;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch,
			int posRotationIncrements, boolean teleport) {
		this.lastX = x;
		this.lastY = y;
		this.lastZ = z;
		this.lastYaw = yaw;
		this.lastRot = pitch;
		this.lerpSteps = 5;
	}

	@Override
	@Nullable
	public Entity getControllingPassenger() {
		List<Entity> list = this.getPassengers();
		return list.isEmpty() ? null : (Entity) list.get(0);
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, @Nullable ItemStack stack, EnumHand hand) {
		if (player.isSneaking())
			return false;
		else if (this.isBeingRidden())
			return true;
		else {
			if (!this.worldObj.isRemote) {
				player.startRiding(this);
			}

			return true;
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!this.worldObj.isRemote && !this.isDead && !this.isBeingRidden()) {
			if (this.isEntityInvulnerable(source)) {
				this.setDead();
			} else if (source instanceof EntityDamageSource && !source.isProjectile()
					&& ((EntityDamageSource) source).getEntity() instanceof EntityPlayer) {
				int m = getColorID();
				ItemStack itemstack = new ItemStack(MachineInit.scooter, 1, m);
				this.entityDropItem(itemstack, 0.0F);

				this.setDead();

			}
		}
		return false;
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(COLOR, Integer.valueOf(0));
		this.dataManager.register(POWERED, Boolean.valueOf(false));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		int c = tag.getInteger("dcs.color");
		this.setColor(c);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		tag.setInteger("dcs.color", getColorID());
	}

	public void setPowered(boolean b) {
		this.dataManager.set(POWERED, b);
	}

	public boolean getPowered() {
		return this.dataManager.get(POWERED);
	}

	@Override
	protected boolean canTriggerWalking() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	protected float getMaxSpeed() {
		return 3.5F;
	}

	public int getColorID() {
		return this.dataManager.get(COLOR);
	}

	public void setColor(int i) {
		this.dataManager.set(COLOR, i);
	}

	public static enum Status {
		IN_TILE,
		UNDER_WATER,
		IN_AIR;
	}

}
