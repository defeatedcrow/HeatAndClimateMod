package defeatedcrow.hac.main.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityIronBullet extends EntityBulletDC {

	public EntityIronBullet(World worldIn) {
		super(worldIn);
	}

	public EntityIronBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityIronBullet(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
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
