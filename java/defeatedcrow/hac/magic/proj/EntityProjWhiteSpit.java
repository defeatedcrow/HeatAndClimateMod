package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.main.entity.EntityProjBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityProjWhiteSpit extends EntityProjBase {

	public EntityProjWhiteSpit(World worldIn) {
		super(worldIn);
	}

	public EntityProjWhiteSpit(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjWhiteSpit(World worldIn, EntityLivingBase shooter) {
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
		float f = 8.0F + this.worldObj.rand.nextFloat() * 4.0F;
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
