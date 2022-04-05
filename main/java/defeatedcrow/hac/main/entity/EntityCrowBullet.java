package defeatedcrow.hac.main.entity;

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

	}

	@Override
	protected void onHitBlock(RayTraceResult trace) {
		if (trace.typeOfHit == RayTraceResult.Type.BLOCK) {
			BlockPos pos = trace.getBlockPos();
			EnumFacing side = trace.sideHit;
			if (side != null) {
				pos.offset(side, 2);
			}
			onEffect(pos);
			this.setDead();
		}
	}

	private void onEffect(BlockPos pos) {
		if (!this.world.isRemote) {
			EntityCrowBalloon baloon = new EntityCrowBalloon(world, pos.getX() + 0.5D, pos.getY() + 0.5D,
					pos.getZ() + 0.5D, this.rotationYaw);

			world.spawnEntity(baloon);
		}
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
