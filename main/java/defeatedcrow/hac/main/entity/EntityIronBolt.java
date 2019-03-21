package defeatedcrow.hac.main.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityIronBolt extends EntityBulletDC {

	public EntityIronBolt(World worldIn) {
		super(worldIn);
	}

	public EntityIronBolt(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityIronBolt(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public double getDamage() {
		return this.damage * 0.5D;
	}

	@Override
	public double getGravity() {
		return 0.05D;
	}

	@Override
	public BulletType getBulletType() {
		return BulletType.BOLT;
	}
}
