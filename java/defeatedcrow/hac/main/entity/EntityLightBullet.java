package defeatedcrow.hac.main.entity;

import defeatedcrow.hac.main.MainInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityLightBullet extends EntityBulletDC {

	public EntityLightBullet(World worldIn) {
		super(worldIn);
	}

	public EntityLightBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityLightBullet(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	protected void onHit(RayTraceResult trace) {
		Entity entity = trace.entityHit;

		if (entity != null) {
			if (!entity.isBurning()) {
				entity.setFire(8);
				onEffect(entity.getPosition());
			}
		}
	}

	@Override
	protected void onHitBlock(RayTraceResult trace) {
		if (trace.typeOfHit == RayTraceResult.Type.BLOCK) {
			BlockPos pos = trace.getBlockPos();
			onEffect(pos);
		}
		super.onHitBlock(trace);
	}

	private void onEffect(BlockPos pos) {
		if (!this.worldObj.isRemote) {
			BlockPos p = pos.up();
			if (this.shootingEntity != null) {
				EnumFacing face = shootingEntity.getHorizontalFacing();
				if (shootingEntity.rotationPitch < -50.0F) {
					face = EnumFacing.UP;
				} else if (shootingEntity.rotationPitch > 50.0F) {
					face = EnumFacing.DOWN;
				}
				p = pos.offset(face.getOpposite());
			}

			if (this.worldObj.getBlockState(p).getMaterial().isReplaceable()) {
				this.worldObj.setBlockState(p, MainInit.lightOrb.getDefaultState(), 3);
			} else if (this.worldObj.getBlockState(pos.up()).getMaterial().isReplaceable()) {
				this.worldObj.setBlockState(pos.up(), MainInit.lightOrb.getDefaultState(), 3);
			}

		}
		this.setDead();
	}

	@Override
	public boolean getIsPenetrate() {
		return true;
	}

	@Override
	public double getGravity() {
		return 0.01D;
	}

	@Override
	public BulletType getBulletType() {
		return BulletType.BULLET;
	}

}
