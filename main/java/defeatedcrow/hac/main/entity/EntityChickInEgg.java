package defeatedcrow.hac.main.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityChickInEgg extends EntityEgg {

	public EntityChickInEgg(World worldIn) {
		super(worldIn);
	}

	public EntityChickInEgg(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityChickInEgg(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	// 確定
	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
		}

		if (!this.world.isRemote) {
			EntityChicken entitychicken = new EntityChicken(this.world);
			entitychicken.setGrowingAge(-24000);
			entitychicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
			this.world.spawnEntity(entitychicken);

			this.world.setEntityState(this, (byte) 3);
			this.setDead();
		}
	}

}
