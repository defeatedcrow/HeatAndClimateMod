package defeatedcrow.hac.machine.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.client.particle.ParticleBlink;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityMagneticHover extends EntityScooter {

	public EntityMagneticHover(World worldIn) {
		super(worldIn);
		this.stepHeight = 1.25F;
	}

	public EntityMagneticHover(World worldIn, double x, double y, double z) {
		super(worldIn);
	}

	public EntityMagneticHover(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ);
	}

	@Override
	public double getMountedYOffset() {
		return 0.4D;
	}

	@Override
	protected void addParticle() {
		if (this.getPowered() && this.rand.nextInt(4) == 0) {
			double px = posX - Math.sin(-rotationYaw * 0.017453292F) * 1D + worldObj.rand.nextDouble() * 0.5D;
			double pz = posZ - Math.cos(rotationYaw * 0.017453292F) * 1D + worldObj.rand.nextDouble() * 0.5D;
			Particle p = new ParticleBlink.Factory().createParticle(0, worldObj, px, posY + 0.25D, pz, 0.0D, 0.0D, 0.0D,
					new int[0]);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(p);
		}
	}

	@Override
	public void updateFalling(EntityPlayer rider) {
		// falling
		this.fallDistance = 0.0F;
		if (rider != null) {
			rider.fallDistance = 0.0F;
		}
		if (this.status == Status.ON_TILE || this.status == Status.IN_TILE) {
			if (this.motionY < 0F) {
				this.motionY *= 0.5D;
			}
			this.motionY += 0.02D;
		} else if (status == Status.UNDER_WATER) {
			if (this.motionY < 0F) {
				this.motionY *= 0.5D;
			}
			this.motionY += 0.02D;
		} else {
			if (this.onGround) {
				this.motionY *= 0.5D;
			} else {
				if (this.motionY > 0F) {
					this.motionY *= 0.75D;
				}
				this.motionY -= 0.02D;
			}
		}

		if (motionY < -0.1D) {
			this.motionY -= 0.1D;
		}
	}

	@Override
	protected void updateSpeed(EntityPlayer rider, boolean brake) {
		float yaw = this.rotationYaw * 0.017453292F;

		if (rider != null && !brake && getPowered() && rider.moveForward > 0F) {
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
			this.motionX *= 0.75D;
			this.motionZ *= 0.75D;
		}

	}

	@Override
	public int getBurnTime(Fluid fluid) {
		String s = fluid.getName();
		if (FluidDictionaryDC.matchFluid(fluid, MachineInit.nitrogen))
			return 120;
		else if (fluid.getName().contains("coolant") || fluid.getName().contains("cryotheum")
				|| fluid.getName().contains("ice"))
			return 60;
		return 0;
	}

	@Override
	protected float getMaxSpeed() {
		return 0.8F;
	}

	@Override
	protected Status getUnderGround() {
		AxisAlignedBB aabb = this.getEntityBoundingBox().expandXyz(0.5D);
		int i = MathHelper.floor_double(aabb.minX);
		int j = MathHelper.ceiling_double_int(aabb.maxX);
		int k = MathHelper.floor_double(aabb.minY - 1.0D);
		int l = MathHelper.ceiling_double_int(aabb.minY);
		int i1 = MathHelper.floor_double(aabb.minZ);
		int j1 = MathHelper.ceiling_double_int(aabb.maxZ);
		boolean flag = false;
		boolean flag2 = false;
		boolean flag3 = false;
		BlockPos.PooledMutableBlockPos mpos = BlockPos.PooledMutableBlockPos.retain();

		try {
			for (int k1 = i; k1 < j; ++k1) {
				for (int l1 = k; l1 < l; ++l1) {
					for (int i2 = i1; i2 < j1; ++i2) {
						mpos.setPos(k1, l1, i2);
						IBlockState state = this.worldObj.getBlockState(mpos);

						if (state.getMaterial() == Material.WATER && state.getBoundingBox(worldObj, mpos) != null) {
							double d = state.getBoundingBox(worldObj, mpos).maxY + mpos.getY();
							if (posY < d + 1D) {
								flag2 = true;
							}
						} else if (state.getCollisionBoundingBox(worldObj, mpos) != null) {
							double d = state.getCollisionBoundingBox(worldObj, mpos).maxY + mpos.getY();
							if (posY < d + 1D) {
								flag = true;
								if (posY < d) {
									flag3 = true;
								}
							}
						}
					}
				}
			}
		} finally {
			mpos.release();
		}

		return flag ? flag3 ? Status.IN_TILE : Status.ON_TILE : flag2 ? Status.UNDER_WATER : Status.IN_AIR;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!this.worldObj.isRemote && !this.isDead && !this.isBeingRidden()) {
			if (this.isEntityInvulnerable(source)) {
				this.setDead();
			} else if (source instanceof EntityDamageSource && !source.isProjectile()
					&& ((EntityDamageSource) source).getEntity() instanceof EntityPlayer) {
				ItemStack itemstack = new ItemStack(MachineInit.magneticHover, 1, 0);

				NBTTagCompound tag = new NBTTagCompound();
				this.getNBTFromEntity(tag);
				itemstack.setTagCompound(tag);

				this.entityDropItem(itemstack, 0.0F);

				this.setDead();

			}
		}
		return false;
	}

}
