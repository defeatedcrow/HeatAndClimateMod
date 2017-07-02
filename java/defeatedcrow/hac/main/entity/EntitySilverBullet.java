package defeatedcrow.hac.main.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntitySilverBullet extends EntityBulletDC {

	public EntitySilverBullet(World worldIn) {
		super(worldIn);
	}

	public EntitySilverBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntitySilverBullet(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public double getGravity() {
		return 0.01D;
	}

	@Override
	public boolean getIsPenetrate() {
		return false;
	}

	@Override
	public boolean getIsRangedAttack() {
		return false;
	}

	@Override
	public boolean getIsSilver() {
		return true;
	}

	@Override
	public BulletType getBulletType() {
		return BulletType.BULLET;
	}

}
