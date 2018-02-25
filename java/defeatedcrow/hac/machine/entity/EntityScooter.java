package defeatedcrow.hac.machine.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class EntityScooter extends Entity implements IInventory {

	protected static final DataParameter<Boolean> POWERED = EntityDataManager.<Boolean>createKey(EntityScooter.class,
			DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> COLOR = EntityDataManager.<Integer>createKey(EntityScooter.class,
			DataSerializers.VARINT);

	public float headYaw;
	public float prevHeadYaw;

	protected int lerpSteps;
	protected double lastX;
	protected double lastY;
	protected double lastZ;
	protected double lastYaw;
	protected double lastRot;
	public double lastMoveY;

	public boolean brake;

	public Status status = Status.IN_AIR;
	protected Status prevStatus = Status.IN_AIR;

	public final DCInventory tankSlot = new DCInventory(3);
	public final InventoryBasic inv = new InventoryBasic("Items", false, 18);
	public final DCTank tank = new DCTank(5000);

	protected int currentBurnTime = 0;
	protected int maxBurnTime = 1;

	/* コンストラクタ */

	public EntityScooter(World worldIn) {
		super(worldIn);
		this.preventEntitySpawning = true;
		this.setSize(1.5F, 1.0F);
		this.stepHeight = 1.25F;
		this.setInventorySlotContents(2, new ItemStack(Items.BUCKET));
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
		this.lastMoveY = this.motionY;
		super.onUpdate();
		this.tickLerp();

		updatePowered();

		EntityPlayer rider = null;
		boolean stop = false;

		if (!this.getPassengers().isEmpty() && this.getPassengers().get(0) instanceof EntityPlayer) {
			rider = (EntityPlayer) this.getPassengers().get(0);
		}

		if (brake || rider == null) {
			stop = true;
		}

		if (rider == null) {
			if (!this.getPassengers().isEmpty()) {
				this.getPassengers().get(0).dismountRidingEntity();
			}
			if (this.getPassengers().size() > 1) {
				this.getPassengers().get(1).dismountRidingEntity();
			}
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

		updateFalling(rider);

		if (!worldObj.isRemote) {

			// 加速処理
			updateSpeed(rider, stop);

			// forward
			if (stop) {
				this.motionX *= 0.5D;
				this.motionZ *= 0.5D;
			} else {
				this.motionX *= 0.99D;
				this.motionZ *= 0.99D;
			}

			float speed = (float) Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			if (speed < 0.01D) {
				this.motionX = 0.0D;
				this.motionZ = 0.0D;
			}

		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		// collision
		this.doBlockCollisions();
		List<Entity> list = this.worldObj.getEntitiesInAABBexcluding(this,
				this.getEntityBoundingBox().expand(0.2D, 0.5D, 0.2D), EntitySelectors.IS_STANDALONE);

		if (!list.isEmpty()) {
			boolean flag = !this.worldObj.isRemote && !(this.getControllingPassenger() instanceof EntityPlayer);

			for (int j = 0; j < list.size(); ++j) {
				Entity entity = list.get(j);

				if (!entity.isPassenger(this) && entity instanceof EntityLivingBase) {
					if (rider != null && this.getPassengers().size() < 2) {
						entity.startRiding(this);
					} else {
						this.applyEntityCollision(entity);
					}
				}
			}
		}

		if (this.status == Status.IN_TILE) {
			this.setPosition(posX, posY + 1D, posZ);
		}

		if (worldObj.isRemote) {
			addParticle();
		}
	}

	protected void addParticle() {
		if (this.getPowered() && this.rand.nextInt(4) == 0) {
			double px = posX - Math.sin(-rotationYaw * 0.017453292F) * 0.75D
					- Math.cos(rotationYaw * 0.017453292F) * 0.25D;
			double pz = posZ - Math.cos(rotationYaw * 0.017453292F) * 0.75D
					- Math.sin(rotationYaw * 0.017453292F) * 0.25D;
			this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, px, this.posY + 0.25D, pz, 0.0D, 0.0D, 0.0D,
					new int[0]);
		}
	}

	public int getBurnTime(Fluid fluid) {
		int burn = MainAPIManager.fuelRegister.getBurningTime(fluid);
		return burn;
	}

	public void updateFalling(EntityPlayer rider) {
		// falling
		this.fallDistance = 0.0F;
		if (rider != null) {
			rider.fallDistance = 0.0F;
		}
		if (this.status == Status.ON_TILE) {
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
	}

	protected void updateSpeed(EntityPlayer rider, boolean brake) {
		float yaw = this.rotationYaw * 0.017453292F;

		if (rider != null && !brake && rider.moveForward > 0F) {
			double d1 = -Math.sin(yaw);
			double d2 = Math.cos(yaw);
			double d9 = this.motionX * this.motionX + this.motionZ * this.motionZ;
			double d10 = Math.sqrt(d9);

			this.motionX += d1 * 0.2D;
			this.motionZ += d2 * 0.2D;

			if (d10 > getMaxSpeed()) {
				this.motionX = d1 * getMaxSpeed();
				this.motionZ = d2 * getMaxSpeed();
			}

			// DCLogger.debugLog("foward " + rider.moveForward + ", sp " + d9);
			rider.moveForward = 0F;
		} else {
			this.motionX *= 0.5D;
			this.motionZ *= 0.5D;
		}

		if (this.status == Status.UNDER_WATER) {
			this.motionX *= 0.75D;
			this.motionZ *= 0.75D;
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
	protected int cooltime = 20;

	protected boolean updatePowered() {
		if (!worldObj.isRemote && cooltime <= 0) {
			cooltime = 20;
			// 燃料関係
			processTank();

			if (this.currentBurnTime > 0 && !this.getPassengers().isEmpty()) {
				this.currentBurnTime--;
			}

			if (this.currentBurnTime == 0) {

				FluidStack f = tank.getFluid();
				if (f != null && f.getFluid() != null && tank.getFluidAmount() > 0) {
					int i = getBurnTime(f.getFluid());
					if (i > 0) {
						this.currentBurnTime = i;
						this.maxBurnTime = i;
						tank.drain(1, true);
						this.markDirty();
					}
				}
			}

			if (this.currentBurnTime > 0 && !this.getPowered()) {
				this.setPowered(true);
			} else if (this.currentBurnTime == 0 && this.getPowered()) {
				this.setPowered(false);
			}

			// setBucketTag();

		} else {
			cooltime--;
		}
		return getPowered();
	}

	protected void setBucketTag() {

		ItemStack bu = this.getStackInSlot(2);
		if (DCUtil.isEmpty(bu)) {
			this.setInventorySlotContents(2, new ItemStack(Items.BUCKET));
		}

		NBTTagCompound tag = bu.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
		}

		if (!tank.isEmpty()) {
			tag = tank.getFluid().writeToNBT(tag);
		} else {
			tag.setString("FluidName", "NONE");
			tag.setInteger("Amount", 0);
		}
		bu.setTagCompound(tag);
		this.setInventorySlotContents(2, bu);
		this.markDirty();

	}

	public FluidStack getBucketTag() {
		ItemStack bu = this.getStackInSlot(2);
		if (!DCUtil.isEmpty(bu)) {
			NBTTagCompound tag = bu.getTagCompound();
			if (tag != null) {
				tag = new NBTTagCompound();
			}

			FluidStack f = FluidStack.loadFluidStackFromNBT(tag);
			return f;
		}
		return null;
	}

	protected Status getStatus() {
		return getUnderGround();
	}

	protected Status getUnderGround() {
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

		return flag ? Status.ON_TILE : flag2 ? Status.UNDER_WATER : Status.IN_AIR;
	}

	// ガクガク対策
	protected void tickLerp() {
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

	/* 騎乗 */

	@Override
	@Nullable
	public Entity getControllingPassenger() {
		List<Entity> list = this.getPassengers();
		return list.isEmpty() ? null : (Entity) list.get(0);
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, ItemStack held, @Nullable EnumHand hand) {
		if (player.isSneaking()) {
			int id = this.getEntityId();
			player.openGui(ClimateMain.instance, id, worldObj, getPosition().getX(), getPosition().getY(),
					getPosition().getZ());
			return true;
		} else if (this.isBeingRidden() && this.getPassengers().size() < 2)
			return true;
		else {
			if (!this.worldObj.isRemote) {
				player.startRiding(this);
			}

			return true;
		}
	}

	@Override
	public void updatePassenger(Entity passenger) {
		if (this.isPassenger(passenger) && passenger != this.getControllingPassenger()) {
			float f = 0.0F;
			float f1 = (float) ((this.isDead ? 0.01D : this.getMountedYOffset()) + passenger.getYOffset());

			if (this.getPassengers().size() > 1) {
				int i = this.getPassengers().indexOf(passenger);

				if (i == 0) {
					f = 0.2F;
				} else {
					f = -0.6F;
				}

				if (passenger instanceof EntityAnimal) {
					f = (float) (f + 0.2D);
				}
			}

			Vec3d vec3d = (new Vec3d(f, 0.0D, 0.0D))
					.rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
			passenger.setPosition(this.posX + vec3d.xCoord, this.posY + f1, this.posZ + vec3d.zCoord);
			passenger.rotationYaw = this.rotationYaw;
			passenger.setRotationYawHead(this.rotationYaw);
			this.applyYawToEntity(passenger);
		} else {
			super.updatePassenger(passenger);
		}
	}

	protected void applyYawToEntity(Entity entityToUpdate) {
		entityToUpdate.setRenderYawOffset(this.rotationYaw);
		float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
		float f1 = MathHelper.clamp_float(f, -105.0F, 105.0F);
		entityToUpdate.prevRotationYaw += f1 - f;
		entityToUpdate.rotationYaw += f1 - f;
		entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void applyOrientationToEntity(Entity entityToUpdate) {
		this.applyYawToEntity(entityToUpdate);
	}

	@Override
	protected boolean canFitPassenger(Entity passenger) {
		return this.getPassengers().size() < 2;
	}

	/* Attack */

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!this.worldObj.isRemote && !this.isDead && this.getControllingPassenger() instanceof EntityPlayer) {
			if (this.isEntityInvulnerable(source)) {
				this.setDead();
			} else if (source instanceof EntityDamageSource && !source.isProjectile()
					&& ((EntityDamageSource) source).getEntity() instanceof EntityPlayer) {
				int m = getColorID();
				ItemStack itemstack = new ItemStack(MachineInit.scooter, 1, m);

				NBTTagCompound tag = new NBTTagCompound();
				this.getNBTFromEntity(tag);
				itemstack.setTagCompound(tag);

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

		this.currentBurnTime = tag.getInteger("BurnTime");
		this.maxBurnTime = tag.getInteger("MaxTime");

		NBTTagList nbttaglist = tag.getTagList("InvItems", 10);

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.getSizeInventory()) {
				this.setInventorySlotContents(b0, ItemStack.loadItemStackFromNBT(nbttagcompound1));
			}
		}

		tank.readFromNBT(tag, "Tank");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		tag.setInteger("dcs.color", getColorID());

		tag.setInteger("BurnTime", this.currentBurnTime);
		tag.setInteger("MaxTime", this.maxBurnTime);

		NBTTagList list = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(nbttagcompound1);
				list.appendTag(nbttagcompound1);
			}
		}
		tag.setTag("InvItems", list);

		tank.writeToNBT(tag, "Tank");
	}

	public void getNBTFromEntity(NBTTagCompound tag) {
		writeEntityToNBT(tag);
	}

	public void setNBTToEntity(NBTTagCompound tag) {
		readEntityFromNBT(tag);
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
		return getPowered() ? 1.8F : 0.4F;
	}

	public int getColorID() {
		return this.dataManager.get(COLOR);
	}

	public void setColor(int i) {
		this.dataManager.set(COLOR, i);
	}

	public InventoryBasic getInventory() {
		return this.inv;
	}

	public DCTank getFuelTank() {
		return this.tank;
	}

	public boolean isActive() {
		return this.currentBurnTime > 0;
	}

	public int getCurrentBurnTime() {
		return this.currentBurnTime;
	}

	public int getMaxBurnTime() {
		return this.maxBurnTime;
	}

	public void setCurrentBurnTime(int i) {
		this.currentBurnTime = i;
	}

	public void setMaxBurnTime(int i) {
		this.maxBurnTime = i;
	}

	public static enum Status {
		IN_TILE,
		UNDER_WATER,
		IN_AIR,
		ON_TILE;
	}

	/* ==== fluid ==== */

	protected void processTank() {
		if (!this.onDrainTank(tank, 0, 1)) {
			this.onFillTank(tank, 0, 1);
		}
	}

	protected boolean onFillTank(DCTank tank, int slot1, int slot2) {
		ItemStack in = this.getStackInSlot(slot1);
		ItemStack out = this.getStackInSlot(slot2);
		if (DCUtil.isEmpty(in))
			return false;

		IFluidHandler dummy = null;
		ItemStack in2 = in.copy();
		if (in.stackSize > 1) {
			in2.stackSize = 1;
		}
		if (in.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
			dummy = in2.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		} else if (in.getItem() instanceof IFluidHandler) {
			dummy = (IFluidHandler) in2.getItem();
		}

		if (dummy != null && dummy.getTankProperties() != null) {
			boolean loose = false;
			ItemStack ret = null;

			int max = dummy.getTankProperties()[0].getCapacity();
			FluidStack fc = dummy.drain(max, false);
			// 流入の場合
			if (fc != null && fc.amount > 0 && tank.canFillTarget(fc)) {
				ret = null;
				loose = false;
				boolean b = false;
				int rem = tank.getCapacity() - tank.getFluidAmount();
				fc = dummy.drain(rem, false);
				if (fc != null && fc.amount <= rem) {
					FluidStack fill = null;
					fill = dummy.drain(rem, true);
					ret = in2;

					if (fill != null
							&& (DCUtil.isEmpty(ret) || this.isItemStackable(ret, inv.getStackInSlot(slot2)) > 0)) {
						loose = true;
						tank.fill(fill, true);
					}
				}
			}

			if (loose) {
				this.decrStackSize(slot1, 1);
				this.incrStackInSlot(slot2, ret);
				return true;
			}
		}
		return false;
	}

	protected boolean onDrainTank(DCTank tank, int slot1, int slot2) {
		ItemStack in = this.getStackInSlot(slot1);
		ItemStack out = this.getStackInSlot(slot2);
		if (DCUtil.isEmpty(in))
			return false;

		IFluidHandler dummy = null;
		ItemStack in2 = in.copy();
		if (in.stackSize > 1) {
			in2.stackSize = 1;
		}
		if (in.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
			dummy = in2.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		} else if (in.getItem() instanceof IFluidHandler) {
			dummy = (IFluidHandler) in2.getItem();
		}

		if (tank.getFluidAmount() > 0 && dummy != null && dummy.getTankProperties() != null) {
			boolean loose = false;
			ItemStack ret = null;

			int max = dummy.getTankProperties()[0].getCapacity();
			FluidStack fc = dummy.drain(max, false);
			boolean b = false;
			int rem = max;
			if (fc == null || fc.amount == 0) {
				b = true;
			} else {
				rem = max - fc.amount;
				if (tank.getFluidAmount() <= rem) {
					b = true;
				}
			}
			// 排出の場合
			if (b) {
				FluidStack drain = tank.drain(rem, false);
				int fill = 0;
				fill = dummy.fill(drain, true);
				ret = in2;

				if (fill > 0 && (DCUtil.isEmpty(ret) || this.isItemStackable(ret, inv.getStackInSlot(slot2)) > 0)) {
					loose = true;
					tank.drain(fill, true);
				}
			}

			if (loose) {
				this.decrStackSize(slot1, 1);
				this.incrStackInSlot(slot2, ret);
				return true;
			}
		}
		return false;
	}

	/* ==== inventory ==== */

	public static int isItemStackable(ItemStack target, ItemStack current) {
		if (DCUtil.isEmpty(target))
			return 0;
		else if (DCUtil.isEmpty(current))
			return target.stackSize;

		if (target.getItem() == current.getItem() && target.getMetadata() == current.getMetadata()
				&& ItemStack.areItemStackTagsEqual(target, current)) {
			int i = current.stackSize + target.stackSize;
			if (i > current.getMaxStackSize()) {
				i = current.getMaxStackSize() - current.stackSize;
				return i;
			}
			return target.stackSize;
		}

		return 0;
	}

	public void incrStackInSlot(int i, ItemStack input) {
		if (i < this.getSizeInventory() && !DCUtil.isEmpty(input)) {
			if (!DCUtil.isEmpty(this.getStackInSlot(i))) {
				if (this.getStackInSlot(i).getItem() == input.getItem()
						&& this.getStackInSlot(i).getMetadata() == input.getMetadata()
						&& ItemStack.areItemStackTagsEqual(this.getStackInSlot(i), input)) {
					DCUtil.addStackSize(this.getStackInSlot(i), input.stackSize);
					if (this.getStackInSlot(i).stackSize > this.getInventoryStackLimit()) {
						this.getStackInSlot(i).stackSize = this.getInventoryStackLimit();
					}
				}
			} else {
				this.setInventorySlotContents(i, input);
			}
		}
	}

	@Override
	public int getSizeInventory() {
		return inv.getSizeInventory() + tankSlot.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 3)
			return tankSlot.getStackInSlot(index);
		else
			return inv.getStackInSlot(index - 3);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (index < 3)
			return tankSlot.decrStackSize(index, count);
		else
			return inv.decrStackSize(index - 3, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (index < 3)
			return tankSlot.removeStackFromSlot(index);
		else
			return inv.removeStackFromSlot(index - 3);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 3) {
			tankSlot.setInventorySlotContents(index, stack);
		} else {
			inv.setInventorySlotContents(index - 3, stack);
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return inv.getInventoryStackLimit();
	}

	@Override
	public void markDirty() {
		inv.markDirty();
		tankSlot.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return inv.isUseableByPlayer(player);
	}

	@Override
	public void openInventory(EntityPlayer player) {
		inv.openInventory(player);
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		inv.closeInventory(player);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index < 3)
			return !DCUtil.isEmpty(stack) && stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		else
			return inv.isItemValidForSlot(index - 3, stack);
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.currentBurnTime;
		case 1:
			return this.maxBurnTime;
		case 2:
			return this.tank.getFluidType() == null ? -1 : FluidIDRegisterDC.getID(tank.getFluidType());
		case 3:
			return this.tank.getFluidAmount();
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.currentBurnTime = value;
			break;
		case 1:
			this.maxBurnTime = value;
			break;
		case 2:
			tank.setFluidById(value);
			break;
		case 3:
			this.tank.setAmount(value);
			break;
		default:
			return;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		inv.clear();
		tankSlot.clear();
	}

	IItemHandler handler = new InvWrapper(this.inv);

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) tank;
		else if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) handler;
		return super.getCapability(capability, facing);
	}

}
