package defeatedcrow.hac.main.entity;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class EntityDummyWeather extends EntityWeatherEffect {

	private int count = 0;

	private final Random rand = new Random();

	/* コンストラクタ */

	public EntityDummyWeather(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 0.5F);
	}

	public EntityDummyWeather(World worldIn, double posX, double posY, double posZ) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}

	public EntityDummyWeather(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		this(worldIn, posX, posY, posZ);
		if (player != null)
			this.rotationYaw = player.rotationYaw;
	}

	/* update処理 */

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.posY < -16.0D) {
			this.setDead();
		}

		BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY),
				MathHelper.floor(this.posZ));

		// 動作
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

		if (this.isFallable()) {

			this.motionY -= 0.04D;
			this.handleWaterMovement();
			float f = 0.98F;

			IBlockState in = world.getBlockState(pos);
			IBlockState under = world.getBlockState(pos.down());

			// 水中
			if (this.inWater && this.isFloatOnWater()) {
				this.motionY += 0.08D;
				if (this.motionY > 0.1D) {
					this.motionY = 0.1D;
				}
				this.motionX *= 0.93D;
				this.motionZ *= 0.93D;
			} else {
				if (this.onGround) {
					f = under.getBlock().slipperiness;
					this.motionX *= f * 0.5D;
					this.motionY *= 0.5D;
					this.motionZ *= f * 0.5D;
				} else {
					this.motionX *= 0.5D;
					this.motionY *= 0.95D;
					this.motionZ *= 0.5D;
				}
			}

			this.doBlockCollisions();

			if (this.motionX * this.motionX < 0.0005D) {
				this.motionX = 0.0D;
			}
			if (this.motionY * this.motionY < 0.0005D) {
				this.motionY = 0.0D;
			}
			if (this.motionZ * this.motionZ < 0.0005D) {
				this.motionZ = 0.0D;
			}

			collideWithNearbyEntities();

		}
	}

	protected void collideWithNearbyEntities() {
		List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this
				.getEntityBoundingBox(), EntitySelectors.IS_STANDALONE);

		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); ++i) {
				Entity entity = list.get(i);
				this.collideWithEntity(entity);
			}
		}
	}

	@Override
	protected boolean pushOutOfBlocks(double x, double y, double z) {
		BlockPos blockpos = new BlockPos(x, y, z);
		double d0 = x - blockpos.getX();
		double d1 = y - blockpos.getY();
		double d2 = z - blockpos.getZ();
		List<AxisAlignedBB> list = this.world.getCollisionBoxes(this, this.getEntityBoundingBox());

		if (list.isEmpty()) {
			return false;
		} else {
			EnumFacing enumfacing = EnumFacing.UP;
			double d3 = Double.MAX_VALUE;

			if (!this.world.isBlockFullCube(blockpos.west()) && d0 < d3) {
				d3 = d0;
				enumfacing = EnumFacing.WEST;
			}

			if (!this.world.isBlockFullCube(blockpos.east()) && 1.0D - d0 < d3) {
				d3 = 1.0D - d0;
				enumfacing = EnumFacing.EAST;
			}

			if (!this.world.isBlockFullCube(blockpos.north()) && d2 < d3) {
				d3 = d2;
				enumfacing = EnumFacing.NORTH;
			}

			if (!this.world.isBlockFullCube(blockpos.south()) && 1.0D - d2 < d3) {
				d3 = 1.0D - d2;
				enumfacing = EnumFacing.SOUTH;
			}

			if (!this.world.isBlockFullCube(blockpos.up()) && 1.0D - d1 < d3) {
				d3 = 1.0D - d1;
				enumfacing = EnumFacing.UP;
			}

			float f = 0.1F;
			float f1 = enumfacing.getAxisDirection().getOffset();

			if (enumfacing.getAxis() == EnumFacing.Axis.X) {
				this.motionX += f1 * f;
			} else if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
				this.motionY += f1 * f;
			} else if (enumfacing.getAxis() == EnumFacing.Axis.Z) {
				this.motionZ += f1 * f;
			}

			return true;
		}
	}

	protected void collideWithEntity(Entity entityIn) {
		entityIn.applyEntityCollision(this);
	}

	public boolean collideable() {
		return true;
	}

	/* 動き */

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		return entityIn.getEntityBoundingBox();
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox() {
		return this.getEntityBoundingBox();
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	@Override
	public boolean handleWaterMovement() {
		if (this.world.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.WATER, this)) {
			if (!this.inWater && !this.firstUpdate) {
				this.doWaterSplashEffect();
			}

			this.inWater = true;

		} else {
			this.inWater = false;
		}

		return this.inWater;
	}

	public static float getBlockLiquidHeight(IBlockState state, IBlockAccess world, BlockPos pos) {
		int i = state.getValue(BlockLiquid.LEVEL).intValue();
		return (i & 7) == 0 && world.getBlockState(pos.up()).getMaterial() == Material.WATER ? 1.0F : 1.0F - BlockLiquid
				.getLiquidHeightPercent(i);
	}

	public static float getLiquidHeight(IBlockState state, IBlockAccess world, BlockPos pos) {
		return pos.getY() + getBlockLiquidHeight(state, world, pos);
	}

	@Override
	protected void dealFireDamage(int amount) {
		this.attackEntityFrom(DamageSource.IN_FIRE, amount);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else if (source.isFireDamage() || source.isMagicDamage()) {
			return false;
		} else {
			this.markVelocityChanged();
			return false;
		}
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		return false;
	}

	/* パラメータ各種 */

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	protected boolean isFallable() {
		return true;
	}

	protected boolean isFloatOnWater() {
		return true;
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {}

	public void setRotation(float f) {
		this.rotationYaw = f;
	}

}
