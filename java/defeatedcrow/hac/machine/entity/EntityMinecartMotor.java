package defeatedcrow.hac.machine.entity;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityMinecartMotor extends EntityMinecartEmpty {

	private static final DataParameter<Boolean> POWERED = EntityDataManager
			.<Boolean>createKey(EntityMinecartMotor.class, DataSerializers.BOOLEAN);
	private int fuel;

	public EntityMinecartMotor(World worldIn) {
		super(worldIn);
	}

	public EntityMinecartMotor(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public static void registerFixesMinecartEmpty(DataFixer fixer) {
		EntityMinecart.registerFixesMinecart(fixer, EntityMinecartMotor.class);
	}

	@Override
	protected double getMaximumSpeed() {
		return 0.8D;
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

		if (this.rand.nextInt(4) == 0) {
			double px = posX - Math.sin(-rotationYaw * 0.017453292F) * 0.75D
					- Math.cos(rotationYaw * 0.017453292F) * 0.3D;
			double pz = posZ - Math.cos(rotationYaw * 0.017453292F) * 0.75D
					- Math.sin(rotationYaw * 0.017453292F) * 0.3D;
			this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, px, this.posY + 0.45D, pz, 0.0D, 0.0D, 0.0D,
					new int[0]);
		}
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void moveAlongTrack(BlockPos pos, IBlockState state) {
		this.fallDistance = 0.0F;
		Vec3d vec3d = this.getPos(this.posX, this.posY, this.posZ);
		this.posY = pos.getY();
		boolean flag = false;
		boolean flag1 = false;
		BlockRailBase blockrailbase = (BlockRailBase) state.getBlock();

		if (blockrailbase == Blocks.GOLDEN_RAIL) {
			flag = state.getValue(BlockRailPowered.POWERED).booleanValue();
			flag1 = !flag;
		}

		double slopeAdjustment = getSlopeAdjustment();
		BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = blockrailbase.getRailDirection(world, pos,
				state, this);

		switch (blockrailbase$enumraildirection) {
		case ASCENDING_EAST:
			this.motionX -= slopeAdjustment;
			++this.posY;
			break;
		case ASCENDING_WEST:
			this.motionX += slopeAdjustment;
			++this.posY;
			break;
		case ASCENDING_NORTH:
			this.motionZ += slopeAdjustment;
			++this.posY;
			break;
		case ASCENDING_SOUTH:
			this.motionZ -= slopeAdjustment;
			++this.posY;
		}

		int[][] aint = MainUtil.MATRIX[blockrailbase$enumraildirection.getMetadata()];
		double d1 = aint[1][0] - aint[0][0];
		double d2 = aint[1][2] - aint[0][2];
		double d3 = Math.sqrt(d1 * d1 + d2 * d2);
		double d4 = this.motionX * d1 + this.motionZ * d2;

		if (d4 < 0.0D) {
			d1 = -d1;
			d2 = -d2;
		}

		double d5 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

		if (d5 > 2.0D) {
			d5 = 2.0D;
		}

		this.motionX = d5 * d1 / d3;
		this.motionZ = d5 * d2 / d3;
		Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);

		if (entity instanceof EntityLivingBase) {
			double d6 = ((EntityLivingBase) entity).moveForward;
			DCLogger.debugLog("foward " + d6);

			if (d6 > 0.0D) {
				double d7 = -Math.sin(entity.rotationYaw * 0.017453292F);
				double d8 = Math.cos(entity.rotationYaw * 0.017453292F);
				double d9 = this.motionX * this.motionX + this.motionZ * this.motionZ;

				if (d9 < 0.64D) {
					this.motionX += d7 * 1.0D;
					this.motionZ += d8 * 1.0D;
					flag1 = false;
				}
			}
		}

		if (flag1 && shouldDoRailFunctions()) {
			double d17 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

			if (d17 < 0.03D) {
				this.motionX *= 0.0D;
				this.motionY *= 0.0D;
				this.motionZ *= 0.0D;
			} else {
				this.motionX *= 0.5D;
				this.motionY *= 0.0D;
				this.motionZ *= 0.5D;
			}
		}

		double d18 = pos.getX() + 0.5D + aint[0][0] * 0.5D;
		double d19 = pos.getZ() + 0.5D + aint[0][2] * 0.5D;
		double d20 = pos.getX() + 0.5D + aint[1][0] * 0.5D;
		double d21 = pos.getZ() + 0.5D + aint[1][2] * 0.5D;
		d1 = d20 - d18;
		d2 = d21 - d19;
		double d10;

		if (d1 == 0.0D) {
			this.posX = pos.getX() + 0.5D;
			d10 = this.posZ - pos.getZ();
		} else if (d2 == 0.0D) {
			this.posZ = pos.getZ() + 0.5D;
			d10 = this.posX - pos.getX();
		} else {
			double d11 = this.posX - d18;
			double d12 = this.posZ - d19;
			d10 = (d11 * d1 + d12 * d2) * 2.0D;
		}

		this.posX = d18 + d1 * d10;
		this.posZ = d19 + d2 * d10;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.moveMinecartOnRail(pos);

		if (aint[0][1] != 0 && MathHelper.floor(this.posX) - pos.getX() == aint[0][0]
				&& MathHelper.floor(this.posZ) - pos.getZ() == aint[0][2]) {
			this.setPosition(this.posX, this.posY + aint[0][1], this.posZ);
		} else if (aint[1][1] != 0 && MathHelper.floor(this.posX) - pos.getX() == aint[1][0]
				&& MathHelper.floor(this.posZ) - pos.getZ() == aint[1][2]) {
			this.setPosition(this.posX, this.posY + aint[1][1], this.posZ);
		}

		this.applyDrag();
		Vec3d vec3d1 = this.getPos(this.posX, this.posY, this.posZ);

		if (vec3d1 != null && vec3d != null) {
			double d14 = (vec3d.y - vec3d1.y) * 0.05D;
			d5 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

			if (d5 > 0.0D) {
				this.motionX = this.motionX / d5 * (d5 + d14);
				this.motionZ = this.motionZ / d5 * (d5 + d14);
			}

			this.setPosition(this.posX, vec3d1.y, this.posZ);
		}

		int j = MathHelper.floor(this.posX);
		int i = MathHelper.floor(this.posZ);

		if (j != pos.getX() || i != pos.getZ()) {
			d5 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.motionX = d5 * (j - pos.getX());
			this.motionZ = d5 * (i - pos.getZ());
		}

		if (shouldDoRailFunctions()) {
			((BlockRailBase) state.getBlock()).onMinecartPass(world, this, pos);
		}

		if (flag && shouldDoRailFunctions()) {
			double d15 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

			if (d15 > 0.01D) {
				double d16 = 0.06D;
				this.motionX += this.motionX / d15 * 0.06D;
				this.motionZ += this.motionZ / d15 * 0.06D;
			} else if (blockrailbase$enumraildirection == BlockRailBase.EnumRailDirection.EAST_WEST) {
				if (this.world.getBlockState(pos.west()).isNormalCube()) {
					this.motionX = 0.02D;
				} else if (this.world.getBlockState(pos.east()).isNormalCube()) {
					this.motionX = -0.02D;
				}
			} else if (blockrailbase$enumraildirection == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
				if (this.world.getBlockState(pos.north()).isNormalCube()) {
					this.motionZ = 0.02D;
				} else if (this.world.getBlockState(pos.south()).isNormalCube()) {
					this.motionZ = -0.02D;
				}
			}
		}
	}

}
