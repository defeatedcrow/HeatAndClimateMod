package defeatedcrow.hac.main.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityCrowBullet extends EntityBulletDC {

	public EntityCrowBullet(World worldIn) {
		super(worldIn);
	}

	public EntityCrowBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityCrowBullet(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	protected void onHit(RayTraceResult trace) {
		Entity entity = trace.entityHit;

		if (entity != null) {
			BlockPos pos = entity.getPosition();
			onEffect(pos.up());
		}
	}

	@Override
	protected void onHitBlock(RayTraceResult trace) {
		if (trace.typeOfHit == RayTraceResult.Type.BLOCK) {
			BlockPos pos = trace.getBlockPos();
			EnumFacing side = trace.sideHit;
			if (side != null) {
				pos.offset(side);
			}
			onEffect(pos);
		}
	}

	private void onEffect(BlockPos pos) {
		if (!this.world.isRemote) {
			EntityCrowBalloon baloon = new EntityCrowBalloon(world, pos.getX() + 0.5D, pos.getY() + 0.5D,
					pos.getZ() + 0.5D, this.rotationYaw);

			world.spawnEntity(baloon);
		}
		this.setDead();
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
