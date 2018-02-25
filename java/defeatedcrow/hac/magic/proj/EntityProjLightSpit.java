package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.main.entity.EntityProjBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityProjLightSpit extends EntityProjBase {

	public EntityProjLightSpit(World worldIn) {
		super(worldIn);
	}

	public EntityProjLightSpit(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjLightSpit(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return null;
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// Hit時ノーマルダメージ

	@Override
	protected float getHitDamage(Entity target, float speed) {
		float f = 10.0F + this.worldObj.rand.nextFloat();
		if (target != null && target instanceof EntityLivingBase) {
			EntityLivingBase liv = (EntityLivingBase) target;
			if (liv.isEntityUndead()) {
				f *= 2.0F;
			}
		}
		return f;
	}

	@Override
	protected DamageSource getHitSource(Entity target) {
		return this.shootingEntity == null
				? DamageSource.causeIndirectMagicDamage(this, this)
				: DamageSource.causeIndirectMagicDamage(this, this.shootingEntity);
	}

	@Override
	protected boolean onGroundHit() {
		this.setDead();
		return true;
	}

	// no gravity
	@Override
	public boolean hasNoGravity() {
		return true;
	}

	@Override
	public boolean isInWater() {
		return false;
	}

}
