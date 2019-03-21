package defeatedcrow.hac.main.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityGhostBullet extends EntityBulletDC {

	public EntityGhostBullet(World worldIn) {
		super(worldIn);
	}

	public EntityGhostBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityGhostBullet(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public double getGravity() {
		return 0.01D;
	}

	@Override
	public double getLivingLimit() {
		return 50;
	}

	@Override
	public double getDamage() {
		return this.damage * 0.8D;
	}

	@Override
	public boolean getIsPenetrate() {
		return true;
	}

	@Override
	public boolean getIsRangedAttack() {
		return false;
	}

	@Override
	public boolean getIsGhost() {
		return true;
	}

	@Override
	public BulletType getBulletType() {
		return BulletType.BULLET;
	}

}
