package defeatedcrow.hac.machine.entity;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.ClimateMain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMinecartMotor extends EntityMinecartEmpty {

	private static final DataParameter<Boolean> POWERED = EntityDataManager
			.<Boolean>createKey(EntityMinecartMotor.class, DataSerializers.BOOLEAN);
	private boolean fuel;
	public double pushX;
	public double pushZ;

	public EntityMinecartMotor(World worldIn) {
		super(worldIn);
	}

	public EntityMinecartMotor(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(POWERED, Boolean.valueOf(false));
	}

	public static void registerFixesMinecartEmpty(DataFixer fixer) {
		EntityMinecart.registerFixesMinecart(fixer, EntityMinecartMotor.class);
	}

	@Override
	protected double getMaximumSpeed() {
		return 1.0D;
	}

	@Override
	public void killMinecart(DamageSource source) {
		this.setDead();

		if (this.world.getGameRules().getBoolean("doEntityDrops")) {
			ItemStack itemstack = new ItemStack(MachineInit.motorMinecart, 1);

			if (this.getName() != null) {
				itemstack.setStackDisplayName(this.getName());
			}

			this.entityDropItem(itemstack, 0.0F);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);

		if (entity instanceof EntityLivingBase) {
			double d6 = ((EntityLivingBase) entity).moveForward;
			DCLogger.debugLog("foward " + d6);

			if (d6 > 0.0D) {
				this.fuel = true;
				EnumFacing face = ((EntityLivingBase) entity).getHorizontalFacing();
				this.pushX = face.getFrontOffsetX() * 1D;
				this.pushZ = face.getFrontOffsetZ() * 1D;
			}
		} else {
			this.fuel = false;
		}

		if (!this.fuel) {
			this.pushX = 0.0D;
			this.pushZ = 0.0D;
		}

		this.dataManager.set(POWERED, Boolean.valueOf(fuel));

		int c = ClimateMain.proxy.getParticleCount();
		if (ClimateMain.proxy.getParticleCount() > 0 && rand.nextInt(c) == 0) {
			this.world
					.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY + 0.8D, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void moveAlongTrack(BlockPos pos, IBlockState state) {
		super.moveAlongTrack(pos, state);
		double d0 = this.pushX * this.pushX + this.pushZ * this.pushZ;

		if (d0 > 1.0E-4D && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.001D) {
			d0 = MathHelper.sqrt(d0);
			this.pushX /= d0;
			this.pushZ /= d0;

			if (this.pushX * this.motionX + this.pushZ * this.motionZ < 0.0D) {
				this.pushX = 0.0D;
				this.pushZ = 0.0D;
			} else {
				double d1 = d0 / this.getMaximumSpeed();
				this.pushX *= d1;
				this.pushZ *= d1;
			}
		}
	}

	@Override
	protected void applyDrag() {
		double d0 = this.pushX * this.pushX + this.pushZ * this.pushZ;

		if (d0 > 1.0E-4D) {
			d0 = MathHelper.sqrt(d0);
			this.pushX /= d0;
			this.pushZ /= d0;
			double d1 = 1.0D;
			this.motionX *= 0.8D;
			this.motionY *= 0.0D;
			this.motionZ *= 0.8D;
			this.motionX += this.pushX * 1.0D;
			this.motionZ += this.pushZ * 1.0D;
		} else {
			this.motionX *= 0.98D;
			this.motionY *= 0.0D;
			this.motionZ *= 0.98D;
		}

		super.applyDrag();
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setDouble("PushX", this.pushX);
		compound.setDouble("PushZ", this.pushZ);
		compound.setBoolean("Fuel", fuel);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.pushX = compound.getDouble("PushX");
		this.pushZ = compound.getDouble("PushZ");
		this.fuel = compound.getBoolean("Fuel");
	}

	protected boolean isMinecartPowered() {
		return this.dataManager.get(POWERED).booleanValue();
	}

	protected void setMinecartPowered(boolean p_94107_1_) {
		this.dataManager.set(POWERED, Boolean.valueOf(p_94107_1_));
	}

}
