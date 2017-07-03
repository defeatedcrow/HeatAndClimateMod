package defeatedcrow.hac.main.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityShotgunBullet extends EntityBulletDC {

	public EntityShotgunBullet(World worldIn) {
		super(worldIn);
	}

	public EntityShotgunBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityShotgunBullet(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public double getGravity() {
		return 0.01D;
	}

	@Override
	public double getLivingLimit() {
		return 3;
	}

	@Override
	public boolean getIsPenetrate() {
		return true;
	}

	@Override
	public boolean getIsRangedAttack() {
		return true;
	}

	@Override
	public BulletType getBulletType() {
		return BulletType.SHOTGUN;
	}

}
