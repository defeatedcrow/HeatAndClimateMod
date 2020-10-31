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
		int r = 8;
		for (int x = -r; x < r; x++) {
			for (int y = -r; y < r; y++) {
				for (int z = -r; z < r; z++) {
					BlockPos p = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					IBlockState state = world.getBlockState(p);
					if (state.getBlock() instanceof BlockFire || state.getBlock() == MagicInit.infernalFlame) {
						world.setBlockToAir(p);
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
