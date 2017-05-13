package defeatedcrow.hac.main.entity;

import defeatedcrow.hac.main.MainInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBulletDC extends EntityProjBase {

	public EntityBulletDC(World worldIn) {
		super(worldIn);
	}

	public EntityBulletDC(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityBulletDC(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MainInit.cartridge, 1, 0);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// Hit時ノーマルダメージ

	@Override
	protected float getHitDamage(Entity target, float speed) {
		float f = (float) this.getDamage() + 3.0F + this.worldObj.rand.nextFloat();
		return f;
	}

	@Override
	protected DamageSource getHitSource(Entity target) {
		if (this.isBurning())
			return DamageSource.onFire;
		return this.shootingEntity == null ? DamageSource.causeArrowDamage(this, this)
				: DamageSource.causeArrowDamage(this, this.shootingEntity);
	}

	@Override
	public boolean isInWater() {
		return false;
	}

}
