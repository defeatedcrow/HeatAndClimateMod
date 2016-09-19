package defeatedcrow.hac.magic.proj;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import defeatedcrow.hac.magic.MagicInit;

public class EntityProjChalW extends EntityMagicProjBase {

	public EntityProjChalW(World worldIn) {
		super(worldIn);
	}

	public EntityProjChalW(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjChalW(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 0);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// Hit時ノーマルダメージ

	@Override
	protected float getHitDamage(Entity target, float speed) {
		float f = 15.0F + this.worldObj.rand.nextFloat();
		if (target != null && target instanceof EntityLivingBase) {
			EntityLivingBase liv = (EntityLivingBase) target;
			if (!liv.onGround) {
				f *= 2.0F;
			}
		}
		return f;
	}

	@Override
	protected DamageSource getHitSource(Entity target) {
		return this.shootingEntity == null ? DamageSource.causeIndirectMagicDamage(this, this) : DamageSource
				.causeIndirectMagicDamage(this, this.shootingEntity);
	}

}
