package defeatedcrow.hac.main.entity;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityIronBolt extends EntityBulletDC {

	public EntityIronBolt(World worldIn) {
		super(worldIn);
		this.damage = ClimateCore.isDebug ? 12.0D : 8.0D;
	}

	public EntityIronBolt(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityIronBolt(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
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
