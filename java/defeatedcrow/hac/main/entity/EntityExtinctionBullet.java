package defeatedcrow.hac.main.entity;

import defeatedcrow.hac.magic.MagicInit;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityExtinctionBullet extends EntityBulletDC {

	public EntityExtinctionBullet(World worldIn) {
		super(worldIn);
	}

	public EntityExtinctionBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityExtinctionBullet(World worldIn, EntityLivingBase shooter) {
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
		for (int x = -5; x < 5; x++) {
			for (int y = -5; y < 5; y++) {
				for (int z = -5; z < 5; z++) {
					BlockPos p = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					IBlockState state = worldObj.getBlockState(p);
					if (state.getBlock() instanceof BlockFire || state.getBlock() == MagicInit.infernalFlame) {
						worldObj.setBlockToAir(p);
					}
				}
			}
		}
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
